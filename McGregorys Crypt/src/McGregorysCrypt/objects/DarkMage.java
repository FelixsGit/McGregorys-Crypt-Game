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

public class DarkMage extends GameObject{

	private Handler handler;
	private int height = 128;
	private int width = 64;
	private float health = 400;
	private boolean toClose = false;
	private int gravity = 1;
	private int castingShadowBallTimer = 0;
	private int deathTimer = 0;
	private boolean interrupted = false;
	private boolean castCompleted = false;
	private int showGreenBar = 0;
	private int lockOutTimer = 0;
	private Random random = new Random();
	private int rand = random.nextInt(6) + 1;
	private int randPotion = random.nextInt(100) + 1;
	private Texture tex = Game.getInstance();
	private int shadowBallDirectionLeft = 1;
	private int shadowBallDirectionRight = 2;
	private int healthPotion = 1;
	private int staminaPotion = 2;
	private int powerPotion = 3;
	private File InterruptSound = new File("res/InterruptSound.wav");
	private File ShadowBallSound = new File("res/ShadowBallSound.wav");
	private File ShadowBallSound2 = new File("res/ShadowBallSound2.wav");
	private File DarkMageDeathSound = new File("res/DarkMageDeathSound.wav");
	private File PotionSpawnSound = new File("res/PotionSpawnSound.wav");
	
	
	private Animation darkMageRunningRight;
	private Animation darkMageAttackingRight;
	private Animation darkMageRunningLeft;
	private Animation darkMageAttackingLeft;
	private Animation darkMageDeathLeft;
	private Animation darkMageDeathRight;
	




	
	public DarkMage(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		darkMageRunningRight = new Animation(5, tex.DarkMageRight[0], tex.DarkMageRight[1], tex.DarkMageRight[2], tex.DarkMageRight[3], tex.DarkMageRight[4], tex.DarkMageRight[5], tex.DarkMageRight[6]);
		darkMageAttackingRight = new Animation(5, tex.DarkMageRight[7], tex.DarkMageRight[8], tex.DarkMageRight[9], tex.DarkMageRight[10], tex.DarkMageRight[11]);
		
		darkMageRunningLeft = new Animation(5, tex.DarkMageLeft[0], tex.DarkMageLeft[1], tex.DarkMageLeft[2], tex.DarkMageLeft[3], tex.DarkMageLeft[4], tex.DarkMageLeft[5], tex.DarkMageLeft[6]);
		darkMageAttackingLeft = new Animation(5, tex.DarkMageLeft[11], tex.DarkMageLeft[10], tex.DarkMageLeft[9], tex.DarkMageLeft[8], tex.DarkMageLeft[7]);
		
		darkMageDeathLeft = new Animation(1, tex.DarkMageLeft[12]);
		darkMageDeathRight = new Animation(1, tex.DarkMageRight[12]);

	}

	public void tick(LinkedList<GameObject> object) {
	
		darkMageRunningRight.runAnimation();
		darkMageAttackingRight.runAnimation();
		
		darkMageRunningLeft.runAnimation();
		darkMageAttackingLeft.runAnimation();
		
		darkMageDeathLeft.runAnimation();
		darkMageDeathRight.runAnimation();
		

		
		if(!getAttacking() && !getDeath()){
			x += velX;
			
		}
		
	
		if(getDeath()){
			castingShadowBallTimer = 0;
		}
		if(getAttacking() && !interrupted){
			castingShadowBallTimer++;
		}
		
		if(interrupted){
			lockOutTimer++;
			if(lockOutTimer >= 75){
				interrupted = false;
				lockOutTimer = 0;
			}
		}
		
		if(castingShadowBallTimer > 0){
			setAttacking(true);
		}
		
		collision(object);
		
		//calculates distance to player
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER && !tempObject.getDeath()){
				float dx = tempObject.getX() - getX(), dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);
			
				//slashing dmg to DarkMage
				if(tempObject.getSlashing() && distance < 100 && tempObject.getFacingRight() && getFacingLeft()){
					if(tempObject.getPowerPotionAura()){
						health -= 10;
					}
					else{
						health -= 5;
					}
				}
				if(tempObject.getSlashing() && distance < 100 && tempObject.getFacingLeft() && getFacingRight()){
					if(tempObject.getPowerPotionAura()){
						health -= 10;
					}
					else{
						health -= 5;
					}
				}
				
				//charge dmg to DarkMage
				if(Player.performingAbility&& distance < 300 && tempObject.getFacingRight() && getFacingLeft()){
					if(tempObject.getPowerPotionAura()){
						health -= 16;
					}
					else{
						health -= 8;
					}
				}
				if(Player.performingAbility && distance < 300 && tempObject.getFacingLeft() && getFacingRight()){
					if(tempObject.getPowerPotionAura()){
						health -= 16;
					}
					else{
						health -= 8;
					}	
				}
				//interrupting DarkMage
				if(Player.performingInterrupt && distance < 140 && tempObject.getFacingRight() && getFacingLeft()){
					castingShadowBallTimer = 0;
					interrupted = true;
					Game.playSound(InterruptSound, true);
				}
				if(Player.performingInterrupt && distance < 140 && tempObject.getFacingLeft() && getFacingRight()){
					castingShadowBallTimer = 0;
					interrupted = true;
					Game.playSound(InterruptSound, true);
				}
					
				//the speed the DarkMage moves to the player with
				velX = (float) (dx * rand / distance);
				
				if(getToClose()){
					
				}
				if(distance > 500){
					setToClose(false);
				}
				
				if(velX > 0 && !getDeath()){
					setFacingRight(true);
					setFacingLeft(false);
				}
				
				if(velX < 0 && !getDeath()){
					setFacingLeft(true);
					setFacingRight(false);
				}
				//distance to start attacking from
				if(distance < 450 && getFacingRight() ){
					setAttacking(true);
				}
				if(distance < 550 && getFacingLeft()){
					setAttacking(true);
				}
				//distance to start running from
				if(distance > 512 && getFacingLeft()){
					castingShadowBallTimer = 0;
					setAttacking(false);
				}
				if(distance > 550 && getFacingRight()){
					castingShadowBallTimer = 0;
					setAttacking(false);
				}
			}
		}
		
		//removing dark mage + spawning potions
		if(getDeath()){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.DARKMAGE){
					if(deathTimer >= 3500 && tempObject.getDeath()){
						handler.removeObject(tempObject);
						deathTimer = 0;		
						if(randPotion >= 0 && randPotion <= 15){
							handler.addObject(new Potion((int) x, (int) y, handler, ObjectId.POTION, healthPotion));
							Game.playSound(PotionSpawnSound, true);
						}
						if(randPotion > 15 && randPotion <= 30){
							handler.addObject(new Potion((int) x, (int) y, handler, ObjectId.POTION, staminaPotion));
							Game.playSound(PotionSpawnSound, true);
						}
						if(randPotion > 30 && randPotion <= 40){
							handler.addObject(new Potion((int) x, (int) y, handler, ObjectId.POTION, powerPotion));
							Game.playSound(PotionSpawnSound, true);
						}
						else{
						}			
					}
				}
			}
		}

	}
	
	public void collision(LinkedList<GameObject> object){
		
		//DarkMage collsion with other skeletons
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.DARKMAGE && !tempObject.getDeath()){
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
		
		
		
		g.setColor(Color.blue);
		g.drawRect((int)x, 0, 1, 720);
		*/
		
		if(health <= 0){
			health = 0;
			setDeath(true);
			deathTimer++;
		}
		
		if(deathTimer == 1){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.PLAYER){
					if(tempObject.getCurrentLevel() < 4){
						Player.xp += 15;
					}
					Game.playSound(DarkMageDeathSound, true);
				}
			}
		}
		
		if(deathTimer > 0 && deathTimer < 3500 && getFacingLeft()){
			darkMageDeathLeft.drawAnimations(g, (int) x - 70, (int) y + 57, 220, 220);
		}
		
		if(deathTimer > 0 && deathTimer < 3500 && getFacingRight()){
			darkMageDeathRight.drawAnimations(g, (int) x - 120, (int) y + 57, 220, 220);
		}
		

		
		if(getAttacking() && getFacingRight() && !getDeath()){
			darkMageAttackingRight.drawAnimations(g, (int) x - 70, (int) y - 55, 200, 200);
		}
		
		else if(getAttacking() && getFacingLeft() && !getDeath()){
			darkMageAttackingLeft.drawAnimations(g, (int) x - 70, (int) y - 55, 200, 200);
		}
		
		else{
			if(velX >= 0 && getFacingRight() && !getDeath()){
				darkMageRunningRight.drawAnimations(g, (int) x - 70, (int) y - 55, 200, 200);
			}
			
			if(velX <= 0 && getFacingLeft() && !getDeath()){
				darkMageRunningLeft.drawAnimations(g, (int) x - 70, (int) y - 55, 200, 200);
			}
			
		}
		
		
		//Casting ShadowBall
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.DARKMAGE){
				if(tempObject.getAttacking() && castingShadowBallTimer >= 150 && !getDeath()){
					castingShadowBallTimer = 0;
					if(getFacingLeft()){
						handler.addObject(new ShadowBall(x , y, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						Game.playSound(ShadowBallSound2, true);
						castCompleted = true;
					}
					if(getFacingRight()){
						handler.addObject(new ShadowBall(x , y, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						Game.playSound(ShadowBallSound2, true);
						castCompleted = true;
					}		
				}
			}
		}
		
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 15);
		g2d.setFont(fnt0);
		
		
		//health bar
		g.setColor(Color.black);
		g.fillRect((int) (x), (int) (y - 55), 50, 5);
		g.setColor(Color.red);
		g.fillRect((int) (x), (int) (y - 55),  (int) (health/8), 5);
		g.setColor(Color.white);
		if(!getDeath()){
			g.drawString(health+"/400", (int) x, (int) y - 60);
		}
		if(getDeath()){
			g.drawString("Dead", (int) x, (int) y - 60);
		}
		
		
		
		//castBar
		if(castCompleted){
			showGreenBar++;
			if(showGreenBar >= 250){
				showGreenBar = 0;
				castCompleted = false;
			}
		}

		g.setColor(Color.black);
		g.fillRect((int) (x - 10), (int) (y - 45), 70, 10);
		g.setColor(Color.orange);
		if(!castCompleted){
			g.fillRect((int) (x - 10), (int) (y - 45), (int) (castingShadowBallTimer/2.14), 10);
		}
		if(castCompleted){
			g.setColor(Color.green);
			g.fillRect((int) (x - 10), (int) (y - 45), (int) (75/1.1), 10);
		}
	}


	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x - 80 , (int) y - 130 , 180, 180);
		
	}
	public Rectangle getBounds() {

		return new Rectangle((int) x - 30, (int) y - 42, 110, 175);	
	
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