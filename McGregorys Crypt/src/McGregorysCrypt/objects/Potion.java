package McGregorysCrypt.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;

import McGregorysCrypt.frameWorks.GameObject;
import McGregorysCrypt.frameWorks.ObjectId;
import McGregorysCrypt.frameWorks.Texture;
import McGregorysCrypt.window.Animation;
import McGregorysCrypt.window.Game;
import McGregorysCrypt.window.Handler;

public class Potion extends GameObject{

	private Handler handler;

	private int gravity = 1;
	private Texture tex = Game.getInstance();
	private int potionType = 0;
	
	private Animation healthPotion;
	private Animation staminaPotion;
	private Animation powerPotion;
	
	private File PotionBounceSound = new File("res/PotionBounceSound.wav");

	
	public Potion(float x, float y, Handler handler, ObjectId id, int potionType) {
		super(x, y, id);
		this.handler = handler;
		this.potionType = potionType;
		healthPotion = new Animation(1, tex.Potions[1]);
		staminaPotion = new Animation(1, tex.Potions[0]);
		powerPotion = new Animation(1, tex.Potions[2]);
	}

	public void tick(LinkedList<GameObject> object) {
	
		setPotionType(potionType); 
		healthPotion.runAnimation();
		staminaPotion.runAnimation();
		powerPotion.runAnimation();
		
		y += velY;


		if(!getStationary()){
			velY += gravity; 
		}
		
		
		if(!getGiveYspeed()){
			velY = -27;
			setGiveYspeed(true);
		}

		collision(object);

	}
		
	public void collision(LinkedList<GameObject> object){
		
		//Potion ground collsion + bounce
		if(!getStationary()){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.POTION){
					if(tempObject.getVelY() > 0  && !tempObject.getDoneBouncing()){
						tempObject.setDoneBouncing(true);
					}
					else if(!tempObject.getFirstBounce() && tempObject.getY() >= Game.HEIGHT/2 + 85 && !tempObject.getStationary() && tempObject.getDoneBouncing()){
						tempObject.setVelY(-25);
						tempObject.setFirstBounce(true);
						tempObject.playSound(PotionBounceSound, true);
						//Game.playSound(PotionBounceSound, true);
					}
					else if(tempObject.getY() >= Game.HEIGHT/2 + 85 && !tempObject.getSecondBounce() && tempObject.getFirstBounce() && !tempObject.getStationary()){
						tempObject.setVelY(-20);
						tempObject.setSecondBounce(true);
						tempObject.playSound(PotionBounceSound, true);
						//Game.playSound(PotionBounceSound, true);
					}
					else if(tempObject.getY() >= Game.HEIGHT/2 + 85 && !tempObject.getThirdBounce() && tempObject.getSecondBounce() && tempObject.getFirstBounce() && !tempObject.getStationary()){
						tempObject.setVelY(-15);
						tempObject.setThirdBounce(true);
						tempObject.playSound(PotionBounceSound, true);
						//Game.playSound(PotionBounceSound, true);
					}
					else if(tempObject.getY() >= Game.HEIGHT/2 + 85 && tempObject.getThirdBounce() && tempObject.getSecondBounce() && tempObject.getFirstBounce() && !tempObject.getStationary()){
						tempObject.setVelY(0);
						tempObject.setY(Game.HEIGHT/2 + 85);
						tempObject.playSound(PotionBounceSound, true);
						//Game.playSound(PotionBounceSound, true);
						tempObject.setStationary(true);
					}
				}
			}
		}

	}

	public void render(Graphics g) {
		
		//draw health potion
		if(getPotionType() == 1){
			healthPotion.drawAnimations(g, (int) x, (int) y + 30, 100, 100);
		}
		if(getPotionType() == 2){
			staminaPotion.drawAnimations(g, (int) x, (int) y + 30, 100, 100);
		}
		if(getPotionType() == 3){
			powerPotion.drawAnimations(g, (int) x, (int) y + 30, 100, 100);
		}
		/*
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.YELLOW);
		g2d.draw(getBounds());
		*/
		
	}


	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x  , (int) y , 20, 20);
		
	}
	public Rectangle getBounds() {

		return new Rectangle((int) x + 12, (int) y + 45 , 75, 80);	
	
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