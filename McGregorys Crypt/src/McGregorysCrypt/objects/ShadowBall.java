package McGregorysCrypt.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import McGregorysCrypt.frameWorks.GameObject;
import McGregorysCrypt.frameWorks.ObjectId;
import McGregorysCrypt.frameWorks.Texture;
import McGregorysCrypt.window.Animation;
import McGregorysCrypt.window.Game;
import McGregorysCrypt.window.Handler;
import McGregorysCrypt.window.Game.GAMESTATE;

public class ShadowBall extends GameObject{

	private Handler handler;
	private int height = 128;
	private int width = 64;
	private Texture tex = Game.getInstance();
	private int shadowBallSpeed = 3;
	private Animation shadowBall;
	private int direction;
	
	
	public ShadowBall(float x, float y, Handler handler, ObjectId id, int direction) {
		super(x, y, id);
		this.handler = handler;
		this.direction = direction;
		
		shadowBall = new Animation(0.1, tex.ShadowBall[0], tex.ShadowBall[1], tex.ShadowBall[2], tex.ShadowBall[3], tex.ShadowBall[4], tex.ShadowBall[5], tex.ShadowBall[6], tex.ShadowBall[7], tex.ShadowBall[8], 
				tex.ShadowBall[9], tex.ShadowBall[10], tex.ShadowBall[11], tex.ShadowBall[12], tex.ShadowBall[13], tex.ShadowBall[14], tex.ShadowBall[15], tex.ShadowBall[16], tex.ShadowBall[17], tex.ShadowBall[18]);
		
	}

	public void tick(LinkedList<GameObject> object) {
	
		shadowBall.runAnimation();
		
		x += velX;
		y += velY;
			
		collision(object);
		
		if(direction == 1){
			velX = -shadowBallSpeed;
		}
		if(direction == 2){
			velX = shadowBallSpeed;
		}
		
		if(Game.state == GAMESTATE.CRYPT){
			shadowBallSpeed = 10;
		}
		//calculates distance to player
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER && !tempObject.getDeath()){
				float dx = tempObject.getX() - getX(), dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);
				//velX = (float) (dx * shadowBallSpeed / distance);
				//velY = (float) (dy * shadowBallSpeed / distance);
			}
		}
		//removes shadowBall if outside map
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.SHADOWBALL){
				if(tempObject.getX() < -500 || tempObject.getX() > 7000){
					handler.removeObject(tempObject);
				}
			}
		}
	}
		
	public void collision(LinkedList<GameObject> object){
		
	}

	public void render(Graphics g) {
		
		shadowBall.drawAnimations(g,(int) x, (int) y);
		
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

		return new Rectangle((int) x + 12, (int) y + 12 , 50, 50);	
	
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