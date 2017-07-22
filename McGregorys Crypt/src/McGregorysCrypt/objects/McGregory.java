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
import McGregorysCrypt.window.Game.GAMESTATE;
import McGregorysCrypt.window.Handler;



public class McGregory extends GameObject{

	private Handler handler;
	private int height = 128;
	private int width = 64;
	private float health = 1000;
	private boolean toClose = false;
	public static int deathTimer = 0;
	private int shadowBallDirectionLeft = 1;
	private int shadowBallDirectionRight = 2;
	private boolean interrupted = false;
	private boolean castCompleted = false;
	private int showGreenBar = 0;
	private int lockOutTimer = 0;
	private int castingShadowBallTimer = 0;
	Random random = new Random();
	private int rand = random.nextInt(6) + 1;
	private Texture tex = Game.getInstance();
	private boolean aggro = false;
	private int darkMageSpawnTimer = 0;
	private int skeletonSpawnTimer = 0;
	private File ShadowBallSound2 = new File("res/ShadowBallSound2.wav");
	private File InterruptSound = new File("res/InterruptSound.wav");
	private File CryptGameMusic = new File("res/CryptGameMusic.wav");
	private File VictoryMusic = new File("res/VictoryMusic.wav");
	private File McGregoryGruntOne = new File("res/McGregoryGruntOne.wav");
	private File McGregoryGruntTwo = new File("res/McGregoryGruntTwo.wav");
	private File McGregoryGruntThree = new File("res/McGregoryGruntThree.wav");
	private File McGregoryGruntFour = new File("res/McGregoryGruntFour.wav");
	
	private Animation mcGregoryIdleRight;
	private Animation mcGregoryWalkRight;
	private Animation mcGregoryAttackRight;
	private Animation mcGregoryIdleLeft;
	private Animation mcGregoryWalkLeft;
	private Animation mcGregoryAttackLeft;
	private Animation mcGregoryLeftDeath;
	private Animation mcGregoryRightDeath;

	
	public McGregory(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

		mcGregoryIdleRight = new Animation(5, tex.McGregoryRight[0], tex.McGregoryRight[1], tex.McGregoryRight[2], tex.McGregoryRight[3],  tex.McGregoryRight[4],  tex.McGregoryRight[5],  tex.McGregoryRight[6]);
		mcGregoryWalkRight = new Animation(1, tex.McGregoryRight[7], tex.McGregoryRight[8], tex.McGregoryRight[9], tex.McGregoryRight[10], tex.McGregoryRight[11], tex.McGregoryRight[12], tex.McGregoryRight[13], 
				tex.McGregoryRight[14], tex.McGregoryRight[15], tex.McGregoryRight[16]);
		mcGregoryAttackRight = new Animation(3, tex.McGregoryRight[17], tex.McGregoryRight[18], tex.McGregoryRight[19], tex.McGregoryRight[20], tex.McGregoryRight[21]);
		
		mcGregoryIdleLeft = new Animation(5, tex.McGregoryLeft[0], tex.McGregoryLeft[1], tex.McGregoryLeft[2], tex.McGregoryLeft[3], tex.McGregoryLeft[4], tex.McGregoryLeft[5], tex.McGregoryLeft[6]);
		mcGregoryWalkLeft = new Animation(1, tex.McGregoryLeft[7], tex.McGregoryLeft[8], tex.McGregoryLeft[9], tex.McGregoryLeft[10], tex.McGregoryLeft[11], tex.McGregoryLeft[12], tex.McGregoryLeft[13],
				tex.McGregoryLeft[14], tex.McGregoryLeft[15], tex.McGregoryLeft[16]);
		mcGregoryAttackLeft = new Animation(3, tex.McGregoryLeft[17], tex.McGregoryLeft[18], tex.McGregoryLeft[19], tex.McGregoryLeft[20], tex.McGregoryLeft[21]);
		mcGregoryLeftDeath = new Animation(28.5, tex.McGregoryLeft[22], tex.McGregoryLeft[23], tex.McGregoryLeft[24], tex.McGregoryLeft[25], tex.McGregoryLeft[26], tex.McGregoryLeft[27],
				tex.McGregoryLeft[28]
				, tex.McGregoryLeft[29], tex.McGregoryLeft[30]);
		mcGregoryRightDeath = new Animation(28.5, tex.McGregoryRight[22], tex.McGregoryRight[23], tex.McGregoryRight[24], tex.McGregoryRight[25], tex.McGregoryRight[26], tex.McGregoryRight[27], 
				tex.McGregoryRight[28], tex.McGregoryRight[29], tex.McGregoryRight[30]);
		
	}

	public void tick(LinkedList<GameObject> object) {
		
		mcGregoryIdleRight.runAnimation();
		mcGregoryWalkRight.runAnimation();
		mcGregoryAttackRight.runAnimation();
		
		mcGregoryIdleLeft.runAnimation();
		mcGregoryWalkLeft.runAnimation();
		mcGregoryAttackLeft.runAnimation();
		
		mcGregoryLeftDeath.runAnimation();
		mcGregoryRightDeath.runAnimation();
		
		if(!getAttacking() && !getDeath()){
			x += velX;		
		}
		collision(object);
		
		if(getDeath()){
			castingShadowBallTimer = 0;
		}
		if(getMcGregoryCastingShadowBall() && !interrupted){
			castingShadowBallTimer++;
		}
		
		if(interrupted){
			lockOutTimer++;
			if(lockOutTimer >= 75){
				interrupted = false;
				lockOutTimer = 0;
				Game.playSound(McGregoryGruntOne, true);
			}
		}
		
		if(castingShadowBallTimer > 0){
			setMcGregoryCastingShadowBall(true);
		}
		
		//calculates distance to player
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER && !tempObject.getDeath()){
				float dx = tempObject.getX() - getX(), dy = tempObject.getY() - getY();
				float distance = (float)Math.sqrt(dx*dx + dy*dy);
			
				//slashing dmg to McGregor
				if(tempObject.getSlashing() && distance < 150 && tempObject.getFacingRight() && getFacingLeft()){
					if(tempObject.getPowerPotionAura()){
						health -= 2;
					}
					else{
						health -= 1;
					}
				}
				if(tempObject.getSlashing() && distance < 150 && tempObject.getFacingLeft() && getFacingRight()){
					if(tempObject.getPowerPotionAura()){
						health -= 2;
					}
					else{
						health -= 1;
					}
				}
				
				//charge dmg to McGregor
				if(Player.performingAbility && distance < 300 && tempObject.getFacingRight() && getFacingLeft()){
					if(tempObject.getPowerPotionAura()){
						health -= 3;
					}
					else{
						health -= 1.50;
					}
				}
				if(Player.performingAbility && distance < 300 && tempObject.getFacingLeft() && getFacingRight()){
					if(tempObject.getPowerPotionAura()){
						health -= 2.25;
					}
					else{
						health -= 1.25;
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
				//the speed McGregor moves to the player with
				if(aggro){
					velX = (float) (dx * 2 / distance);
				}
				
				if(getToClose()){
					
				}
				if(distance > 100){
					setToClose(false);
				}
				
				if(distance > 300){
					aggro = false;
				}
				if(distance < 300){
					aggro = true;
				}
				if(distance == 700){
					Game.playSound(McGregoryGruntTwo, true);
				}
				
				if(velX > 0 && deathTimer == 0){
					setFacingRight(true);
					setFacingLeft(false);
				}
				
				if(velX < 0 && deathTimer == 0){
					setFacingLeft(true);
					setFacingRight(false);	
				}
				//distance to start attacking from
				if(distance < 100 && getFacingRight() ){
					setAttacking(true);
				}
				if(distance < 200 && getFacingLeft()){
					setAttacking(true);
				}
				//distance to start running from
				if(distance > 150 && getFacingLeft()){
					setAttacking(false);
				}
				if(distance > 150 && getFacingRight()){
					setAttacking(false);
				}
			}
		}
		//spawning skeletons
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER){
				skeletonSpawnTimer++;
				if((health < 700 && health > 600 && skeletonSpawnTimer == 200) || (health < 400 && health > 100 && skeletonSpawnTimer == 200) || (health <= 1000 && health > 800 && skeletonSpawnTimer == 200)){
					Random rand = new Random();
					int randomNum = rand.nextInt((1 - -1) + 1) + -1;
					if(randomNum != 0){
						handler.addObject(new PurpleSkeleton(tempObject.getX() + (1000*randomNum), Game.HEIGHT/2 + 60, handler, ObjectId.PURPLESKELETON));
						Game.playSound(McGregoryGruntThree, true);
					}
					skeletonSpawnTimer = 0;
				}
			}
		}
		
		//spawning DarkMage
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER){
				darkMageSpawnTimer++;
				if((health < 700 && health > 600 && darkMageSpawnTimer == 450) || (health < 400 && health > 100 && darkMageSpawnTimer == 450) || (health <= 1000 && health > 800 && darkMageSpawnTimer == 450)){
					Random rand = new Random();
					int randomNum = rand.nextInt((1 - -1) + 1) + -1;
					if(randomNum != 0){
						handler.addObject(new DarkMage(tempObject.getX() + (1000*randomNum), Game.HEIGHT/2 + 60, handler, ObjectId.DARKMAGE));
						Game.playSound(McGregoryGruntThree, true);
					}
					darkMageSpawnTimer = 0;
				}
			}
		}

		if(getDeath()){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.MCGREGORY){
					if(deathTimer >= 10000 && tempObject.getDeath()){
						deathTimer = 0;
						Game.state = GAMESTATE.VICTORY;
						Game.doRestart = true;
					}
				}
			}
		}
		//When to start casting shadowBalls
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.MCGREGORY){
				if(health < 800 && health > 700){
					tempObject.setMcGregoryCastingShadowBall(true);
					darkMageSpawnTimer = 0;
					skeletonSpawnTimer = 0;
				}
				if(health < 700 && health > 600){
					tempObject.setMcGregoryCastingShadowBall(false);
					castingShadowBallTimer = 0;
				}
				if(health < 600 && health > 400){
					tempObject.setMcGregoryCastingShadowBall(true);
					darkMageSpawnTimer = 0;
					skeletonSpawnTimer = 0;
				}
				if(health < 400 && health > 100){
					tempObject.setMcGregoryCastingShadowBall(false);
					castingShadowBallTimer = 0;
				}
				if(health < 100 && health > 0){
					tempObject.setMcGregoryCastingShadowBall(true);
					darkMageSpawnTimer = 0;
					skeletonSpawnTimer = 0;
				}
			}
		}
		//refresh music
	}
	
	public void collision(LinkedList<GameObject> object){
		

	}

	public void render(Graphics g) {
		
		
		
		//Casting ShadowBall
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.MCGREGORY){
				if(tempObject.getMcGregoryCastingShadowBall() && castingShadowBallTimer >= 150 && !getDeath()){
					castingShadowBallTimer = 0;
					if(getFacingLeft()){
						handler.addObject(new ShadowBall(x - 50, y - 110, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y - 70, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y - 30, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 10, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 50, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 90, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 130, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x + 80, y - 110, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y - 70, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y - 30, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y + 10, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x  + 80, y + 50, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y + 90, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y + 130, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						Game.playSound(ShadowBallSound2, true);
						castCompleted = true;
					}
					if(getFacingRight()){
						handler.addObject(new ShadowBall(x + 80, y - 110, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y - 70, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y - 30, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y + 10, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x  + 80, y + 50, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y + 90, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x + 80, y + 130, handler, ObjectId.SHADOWBALL, shadowBallDirectionRight));
						handler.addObject(new ShadowBall(x - 50, y - 110, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y - 70, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y - 30, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 10, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 50, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 90, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						handler.addObject(new ShadowBall(x - 50, y + 130, handler, ObjectId.SHADOWBALL, shadowBallDirectionLeft));
						Game.playSound(ShadowBallSound2, true);
						castCompleted = true;
					}		
				}
			}
		}
		
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
			Game.playMusic(CryptGameMusic, false);
			Game.playMusic(VictoryMusic, true);
			Game.playSound(McGregoryGruntFour, true);
		}
		
		if(deathTimer == 1){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.PLAYER){
					if(tempObject.getCurrentLevel() == 4){
						Player.xp += 600;
					}
				}
			}
		}
		
		
		if(deathTimer > 0 && deathTimer < 10000 && getFacingLeft()){
			mcGregoryLeftDeath.drawAnimations(g,  (int) x - 90 , (int) y - 90, 340, 340);
		}
		
		if(deathTimer > 0 && deathTimer < 10000 && getFacingRight()){
			mcGregoryRightDeath.drawAnimations(g,  (int) x - 150 , (int) y - 70, 340, 340);
		}

		//McGregory Movement
		if(getAttacking() && getFacingRight() && !getDeath()){
			mcGregoryAttackRight.drawAnimations(g,  (int) x - 90 , (int) y - 53, 300, 300);
		}
		else if(getAttacking() && getFacingLeft() && !getDeath()){
			mcGregoryAttackLeft.drawAnimations(g,  (int) x - 90 , (int) y - 53, 300, 300);
		}
		else{
			if(velX > 0 && getFacingRight() && !getDeath()){
				mcGregoryWalkRight.drawAnimations(g,  (int) x - 100 , (int) y - 83, 300, 300);
			}
			if(velX < 0 && getFacingLeft() && !getDeath()){
				mcGregoryWalkLeft.drawAnimations(g,  (int) x - 100 , (int) y - 83, 300, 300);
			}
			else{
				if(velX == 0 && getFacingRight()){
					mcGregoryIdleRight.drawAnimations(g,  (int) x - 100 , (int) y - 115, 300, 300);
				}
				if(velX == 0 && getFacingLeft()){
					mcGregoryIdleLeft.drawAnimations(g,  (int) x - 100 , (int) y - 115, 300, 300);
				}
			}
		}
		
		

		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 15);
		g2d.setFont(fnt0);
		
		
		//health bar
		g.setColor(Color.black);
		g.fillRect((int) (x - 55 ), (int) (y - 70), 200, 7);
		g.setColor(Color.red);
		g.fillRect((int) (x - 55), (int) (y - 70),  (int) (health/5), 7);
		g.setColor(Color.white);
		if(!getDeath()){
			g.drawString(health+"/1000", (int) x + 10, (int) y - 75);
		}
		if(getDeath()){
			g.drawString("Dead", (int) x +10, (int) y - 75);
		}
		Font fnt1= new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
		g2d.setFont(fnt1);
		g.setColor(Color.red);
		g.drawString("Hedrich McGregory", (int) x - 46 , (int) y - 95);
		
		
		//castBar
		if(castCompleted){
			showGreenBar++;
		if(showGreenBar >= 250){
			showGreenBar = 0;
			castCompleted = false;
			}
		}

		g.setColor(Color.black);
		g.fillRect((int) (x - 55 ), (int) (y - 60), 200, 10);
		g.setColor(Color.orange);
		if(!castCompleted){
			g.fillRect((int) (x - 55), (int) (y - 60), (int) (castingShadowBallTimer*1.33), 10);
		}
		if(castCompleted){
			g.setColor(Color.green);
			g.fillRect((int)  (x - 55 ), (int) (y - 60), 200, 10);
		}
	}


	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x - 80 , (int) y - 130 , 180, 180);
		
	}
	public Rectangle getBounds() {

		if(getFacingRight() && getAttacking()){
			return new Rectangle((int) x - 50, (int) y - 35 , 180, 220);	
		}
		else{
			return new Rectangle((int) x - 50, (int) y - 55 , 180, 240);
		}
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