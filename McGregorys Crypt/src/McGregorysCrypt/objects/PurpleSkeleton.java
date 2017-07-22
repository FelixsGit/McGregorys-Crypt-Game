package McGregorysCrypt.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import McGregorysCrypt.frameWorks.GameObject;
import McGregorysCrypt.frameWorks.ObjectId;
import McGregorysCrypt.frameWorks.Texture;
import McGregorysCrypt.window.Animation;
import McGregorysCrypt.window.Game;
import McGregorysCrypt.window.Handler;



public class PurpleSkeleton extends GameObject{

	private Handler handler;
	private int height = 128;
	private int width = 64;
	private float health = 100;
	private boolean toClose = false;
	private int gravity = 1;
	private int idNummer;
	private int deathTimer = 0;
	Random random = new Random();
	private int rand = random.nextInt(6) + 1;
	private int randPotion = random.nextInt(100) + 1;
	private int healthPotion = 1;
	private int staminaPotion = 2;
	private File PotionSpawnSound = new File("res/PotionSpawnSound.wav");
	private Animation skeletonRunRight;
	private Animation  skeletonRunLeft;
	private Animation  skeletonIdleRight;
	private Animation  skeletonIdleLeft;

	private Animation  skeletonSlashingRight;
	private Animation  skeletonSlashingLeft;
	
	private Animation skeletonDeath;
	
	private File SkeletonDeathSound = new File("res/SkeletonDeathSound.wav");

	private Texture tex = Game.getInstance();


	
	public PurpleSkeleton(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

		skeletonRunRight = new Animation(3, tex.SkeletonRight[6], tex.SkeletonRight[7], tex.SkeletonRight[8], tex.SkeletonRight[9], tex.SkeletonRight[10], tex.SkeletonRight[11]);
		skeletonRunLeft = new Animation(3, tex.SkeletonLeft[6], tex.SkeletonLeft[7], tex.SkeletonLeft[8], tex.SkeletonLeft[9], tex.SkeletonLeft[10], tex.SkeletonLeft[11]);
		
		skeletonIdleRight = new Animation(5, tex.SkeletonRight[0], tex.SkeletonRight[1], tex.SkeletonRight[2], tex.SkeletonRight[3], tex.SkeletonRight[4]);
		skeletonIdleLeft = new Animation(5, tex.SkeletonLeft[0], tex.SkeletonLeft[1], tex.SkeletonLeft[2], tex.SkeletonLeft[3], tex.SkeletonLeft[4]);
		
		skeletonSlashingRight = new Animation(3, tex.SkeletonRight[16], tex.SkeletonRight[15],  tex.SkeletonRight[14],  tex.SkeletonRight[13], tex.SkeletonRight[12]);
		skeletonSlashingLeft = new Animation(3, tex.SkeletonLeft[12], tex.SkeletonLeft[13],  tex.SkeletonLeft[14],  tex.SkeletonLeft[15], tex.SkeletonLeft[16]);
		
		skeletonDeath = new Animation(35, tex.SkeletonRight[21], tex.SkeletonRight[20], tex.SkeletonRight[19], tex.SkeletonRight[18], tex.SkeletonRight[17]);

	}

	public void tick(LinkedList<GameObject> object) {
	
		
		skeletonRunRight.runAnimation();
		skeletonRunLeft.runAnimation();
		
		skeletonIdleRight.runAnimation();
		skeletonIdleLeft.runAnimation();	
	
		skeletonSlashingRight.runAnimation();
		skeletonSlashingLeft.runAnimation();
		
		skeletonDeath.runAnimation();
		
		if(!getAttacking() && !getDeath()){
			x += velX;
			
		}


		collision(object);
		
		//calculates distance to player
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER && !tempObject.getDeath()){
				float dx = tempObject.getX() - getX(), dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);
			
				//slashing dmg to skeleton
				if(tempObject.getSlashing() && distance < 150 && tempObject.getFacingRight() && getFacingLeft()){
					if(tempObject.getPowerPotionAura()){
						health -= 8;
					}
					else{
						health -= 4;
					}
				}
				if(tempObject.getSlashing() && distance < 150 && tempObject.getFacingLeft() && getFacingRight()){
					if(tempObject.getPowerPotionAura()){
						health -= 8;
					}
					else{
						health -= 4;
					}
				}
				
				//charge dmg to skeleton
				if(Player.performingAbility && distance < 300 && tempObject.getFacingRight() && getFacingLeft()){
					if(tempObject.getPowerPotionAura()){
						health -= 20;
					}
					else{
						health -= 10;
					}
				}
				if(Player.performingAbility && distance < 300 && tempObject.getFacingLeft() && getFacingRight()){
					if(tempObject.getPowerPotionAura()){
						health -= 20;
					}
					else{
						health -= 10;
					}
				}
				//the speed the skeleton moves to the player with
				velX = (float) (dx * rand / distance);
				
				if(getToClose()){
					
				}
				if(distance > 100){
					setToClose(false);
				}
				
				if(velX > 0){
					setFacingRight(true);
					setFacingLeft(false);
				}
				
				if(velX < 0){
					setFacingLeft(true);
					setFacingRight(false);
				}
				//distance to start attacking from
				if(distance < 50 && getFacingRight() ){
					setAttacking(true);
				}
				if(distance < 150 && getFacingLeft()){
					setAttacking(true);
				}
				//distance to start running from
				if(distance > 150 && getFacingLeft()){
					setAttacking(false);
				}
				if(distance > 50 && getFacingRight()){
					setAttacking(false);
				}
			}
		}

		if(getDeath()){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.PURPLESKELETON){
					if(deathTimer >= 5000 && tempObject.getDeath()){
						handler.removeObject(tempObject);
						deathTimer = 0;
						if(randPotion >= 0 && randPotion <= 5){
							handler.addObject(new Potion((int) x, (int) y, handler, ObjectId.POTION, healthPotion));
							Game.playSound(PotionSpawnSound, true);
						}
						if(randPotion >= 0 && randPotion <= 2){
							handler.addObject(new Potion((int) x, (int) y, handler, ObjectId.POTION, staminaPotion));
							Game.playSound(PotionSpawnSound, true);
						}
					}
				}
			}
		}
	}
	
	public void collision(LinkedList<GameObject> object){
		
		//skeleton collsion with other skeletons
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PURPLESKELETON && !tempObject.getDeath()){
				if(tempObject.getBounds().intersects(getBounds()) && !getToClose()){
					setToClose(true);
				}
				else if(!getAttacking()){
					setToClose(false);
				}
			}
		}
	}

	public void render(Graphics g) {
		
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.YELLOW);
		g2d.draw(getBounds());
		*/
		
		/*
		g.setColor(Color.blue);
		g.drawRect((int)x, 0, 1, 720);
		*/
		
		if(health <= 0){
			health = 0;
			setDeath(true);
			deathTimer++;
		}
		
		if(deathTimer > 0 && deathTimer < 5000){
			skeletonDeath.drawAnimations(g, (int) x - 95, (int) y - 35, 190, 190);
		}
		if(deathTimer == 1){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.PLAYER){
					if(tempObject.getCurrentLevel() < 4){
						Player.xp += 10;
					}
					Game.playSound(SkeletonDeathSound, true);
				}
			}
		}
		

		
		if(getAttacking() && getFacingRight() && !getDeath()){
			skeletonSlashingRight.drawAnimations(g, (int) x - 95, (int) y - 35, 190, 190);
		}
		else if(getAttacking() && getFacingLeft() && !getDeath()){
			skeletonSlashingLeft.drawAnimations(g, (int) x - 95, (int) y - 35, 190, 190);
		}
		else{
			if(velX >= 0 && getFacingRight() && !getDeath()){
				skeletonRunRight.drawAnimations(g, (int) x - 95, (int) y - 35, 190, 190);
			}
			if(velX <= 0 && getFacingLeft() && !getDeath()){
				skeletonRunLeft.drawAnimations(g, (int) x - 95, (int) y - 35, 190, 190);
			}	
		}
		
		

		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 15);
		g2d.setFont(fnt0);
		
		
		//health bar
		g.setColor(Color.black);
		g.fillRect((int) (x - 20 ), (int) (y - 15), 50, 5);
		g.setColor(Color.red);
		g.fillRect((int) (x - 20), (int) (y - 15),  (int) (health/2), 5);
		g.setColor(Color.white);
		if(!getDeath()){
			g.drawString(health+"/50", (int) x - 20, (int) y - 20);
		}
		if(getDeath()){
			g.drawString("Dead", (int) x - 20, (int) y - 20);
		}
		
		/*
		//health + stamina text
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
		g.setColor(Color.GRAY);
		g.setFont(fnt0);
		g.drawString("Health", (int) (x - 625) + 207, 21);
		g.drawString("Stamina", (int) (x - 625) + 207, 41);
		*/
		
		
	}


	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x - 80 , (int) y - 130 , 180, 180);
		
	}
	public Rectangle getBounds() {

		return new Rectangle((int) x - 45, (int) y, 80, 128);	
	
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + 5, (int) y, 64 - 10, 10);
		
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + 5, (int) y + 45, 64 - 10 , 10);
		
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) x + 5, (int) y + 7, 5, 64 - 20);	
		
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + 54, (int) y + 7, 5, 64 - 20);	
	}
}