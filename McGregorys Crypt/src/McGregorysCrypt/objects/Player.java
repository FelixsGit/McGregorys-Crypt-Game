package McGregorysCrypt.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.swing.plaf.synth.SynthSeparatorUI;

import McGregorysCrypt.frameWorks.GameObject;
import McGregorysCrypt.frameWorks.ObjectId;
import McGregorysCrypt.frameWorks.Texture;
import McGregorysCrypt.window.Animation;
import McGregorysCrypt.window.BufferedImageLoader;
import McGregorysCrypt.window.Game;
import McGregorysCrypt.window.Game.GAMESTATE;
import McGregorysCrypt.window.Handler;



public class Player extends GameObject{

	private Handler handler;
	private int height = 128;
	private int width = 64;
	private int gravity = 1;
	private int drinkingTimer = 0;
	private int powerPotionAuraTimer = 420;
	private int levelUpTimer = 0;
	private int deathTimer = 0;
	public static boolean levelUp = false;
	public static float stamina = 200;
	public static boolean staminaError = false;
	public static boolean performingAbility = false;
	public static boolean performingInterrupt = false;
	public static int lookingForIntterruptTarget = 0;
	public static int healthPotionInInventory = 0;
	public static int staminaPotionInInventory = 0;
	public static int powerPotionInInventory = 0;
	public static int playerLevel = 0;
	public int currentLevel = getCurrentLevel();
	private File GameMusic = new File("res/GameMusic.wav");
	private File CryptGameMusic = new File("res/CryptGameMusic.wav");

	public static int xp = 0;
	public static float health = 200;
	private int faintTimer = 0;
	private int skeletonSpawnTimer;
	private int errorTimer;
	private Animation playerRunRight;
	private Animation playerRunLeft;
	private Animation playerIdleRight;
	private Animation playerIdleLeft;
	private Animation playerJumpRight;
	private Animation playerJumpLeft;
	private Animation playerSlashingRight;
	private Animation playerSlashingLeft;
	private Animation playerSlashingRightPowerAura;
	private Animation playerSlashingLeftPowerAura;
	private Animation playerChargeRight;
	private Animation playerChargeLeft;
	private Animation playerChargeRightPowerAura;
	private Animation playerChargeLeftPowerAura;
	private Animation playerFaintRight;
	private Animation playerFaintLeft;
	private Animation slashAbility;
	private Animation slashAbilityCant;
	private Animation chargeAbility;
	private Animation chargeAbilityCant;
	private Animation chargeAbilityMissingStamina;
	
	private Animation slashAttackAbility;
	private Animation chargeAttackAbility;
	
	private Animation interruptNormal;
	private Animation useingInterrupt;
	private Animation interruptCant;
	private Animation interruptAbilityMissingStamina;
	private Animation playerInterruptRight;
	private Animation playerInterruptLeft;
	
	private Animation healthPotion;
	private Animation staminaPotion;
	private Animation powerPotion;
	
	private Animation useingHealthPotion;
	private Animation useingStaminaPotion;
	private Animation useingPowerPotion;
	
	private Animation healthPotionBlack;
	private Animation staminaPotionBlack;
	private Animation powerPotionBlack;
	
	private Animation playerDeath;
	
	private Animation levelUpArrow;
	private Texture tex = Game.getInstance();
	private File SleepingSound = new File("res/SleepingSound.wav");
	private File LootingSound = new File("res/LootingSound.wav");
	private File LevelUpSound = new File("res/LevelUpSound.wav");
	private File DeafeatSound = new File("res/DefeatMusic.wav");
	

	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		
		playerRunRight = new Animation(3, tex.PlayerRunRight[0], tex.PlayerRunRight[1], tex.PlayerRunRight[2], tex.PlayerRunRight[3], tex.PlayerRunRight[4], tex.PlayerRunRight[5]);
		playerRunLeft = new Animation(3, tex.PlayerRunLeft[0], tex.PlayerRunLeft[1], tex.PlayerRunLeft[2], tex.PlayerRunLeft[3], tex.PlayerRunLeft[4], tex.PlayerRunLeft[5]);
		playerIdleRight = new Animation(10, tex.PlayerIdleRight[0], tex.PlayerIdleRight[1], tex.PlayerIdleRight[2], tex.PlayerIdleRight[3], tex.PlayerIdleRight[4]);
		playerIdleLeft = new Animation(10, tex.PlayerIdleLeft[0], tex.PlayerIdleLeft[1], tex.PlayerIdleLeft[2], tex.PlayerIdleLeft[3], tex.PlayerIdleLeft[4]);
		playerJumpRight = new Animation(1.35, tex.PlayerJumpRight[3], tex.PlayerJumpRight[4], tex.PlayerJumpRight[5], tex.PlayerJumpRight[6]);
		playerJumpLeft = new Animation(1.35,  tex.PlayerJumpLeft[3], tex.PlayerJumpLeft[4], tex.PlayerJumpLeft[5], tex.PlayerJumpLeft[6]);
		playerSlashingRight = new Animation(1, tex.PlayerSlashingRight[0], tex.PlayerSlashingRight[1],  tex.PlayerSlashingRight[2],  tex.PlayerSlashingRight[3], tex.PlayerSlashingRight[4]);
		playerSlashingLeft = new Animation(1, tex.PlayerSlashingLeft[0], tex.PlayerSlashingLeft[1],  tex.PlayerSlashingLeft[2],  tex.PlayerSlashingLeft[3], tex.PlayerSlashingLeft[4]);
		playerSlashingRightPowerAura = new Animation(1, tex.PlayerSlashingRightPowerAura[0], tex.PlayerSlashingRightPowerAura[1],  tex.PlayerSlashingRightPowerAura[2],  tex.PlayerSlashingRightPowerAura[3], tex.PlayerSlashingRightPowerAura[4]);
		playerSlashingLeftPowerAura = new Animation(1, tex.PlayerSlashingLeftPowerAura[0], tex.PlayerSlashingLeftPowerAura[1],  tex.PlayerSlashingLeftPowerAura[2],  tex.PlayerSlashingLeftPowerAura[3], tex.PlayerSlashingLeftPowerAura[4]);
		playerChargeRight = new Animation(1, tex.PlayerChargeRight[0], tex.PlayerChargeRight[1], tex.PlayerChargeRight[2], tex.PlayerChargeRight[3], tex.PlayerChargeRight[4]);
		playerChargeLeft = new Animation(1, tex.PlayerChargeLeft[0], tex.PlayerChargeLeft[1], tex.PlayerChargeLeft[2], tex.PlayerChargeLeft[3], tex.PlayerChargeLeft[4]);
		playerDeath = new Animation(60, tex.PlayerChargeLeft[5], tex.PlayerChargeLeft[6], tex.PlayerChargeLeft[7], tex.PlayerChargeLeft[8], tex.PlayerChargeLeft[9]);
		playerChargeRightPowerAura = new Animation(1, tex.PlayerChargeRightPowerAura[0], tex.PlayerChargeRightPowerAura[1], tex.PlayerChargeRightPowerAura[2], tex.PlayerChargeRightPowerAura[3], tex.PlayerChargeRightPowerAura[4]);
		playerChargeLeftPowerAura = new Animation(1, tex.PlayerChargeLeftPowerAura[0], tex.PlayerChargeLeftPowerAura[1], tex.PlayerChargeLeftPowerAura[2], tex.PlayerChargeLeftPowerAura[3], tex.PlayerChargeLeftPowerAura[4]);
		playerFaintRight = new Animation(10, tex.PlayerFaintRight[4], tex.PlayerFaintRight[3], tex.PlayerFaintRight[2], tex.PlayerFaintRight[1], tex.PlayerFaintRight[0]);
		playerFaintLeft = new Animation(10, tex.PlayerFaintLeft[4], tex.PlayerFaintLeft[5], tex.PlayerFaintLeft[6], tex.PlayerFaintLeft[7], tex.PlayerFaintLeft[8]);
		slashAbility = new Animation(1, tex.PlayerAbility[0]);
		slashAttackAbility = new Animation(1, tex.PlayerAbility[1]);
		chargeAttackAbility = new Animation(1, tex.PlayerAbility[4]);
		slashAbilityCant = new Animation(1, tex.PlayerAbility[2]);
		chargeAbility = new Animation(35, tex.PlayerAbility[3]);
		chargeAbilityCant = new Animation(1, tex.PlayerAbility[5]);
		chargeAbilityMissingStamina = new Animation(1, tex.PlayerAbility[6]);
		
		interruptNormal = new Animation(1, tex.PlayerAbility[7]);
		useingInterrupt = new Animation(1, tex.PlayerAbility[8]);
		interruptCant = new Animation(1, tex.PlayerAbility[9]);
		interruptAbilityMissingStamina = new Animation(1, tex.PlayerAbility[10]);
		
		playerInterruptRight = new Animation(1, tex.PlayerAbility[11]);
		playerInterruptLeft = new Animation(1, tex.PlayerAbility[12]);
		
		healthPotion = new Animation(1, tex.Potions[1]);
		staminaPotion = new Animation(1, tex.Potions[0]);
		powerPotion = new Animation(1, tex.Potions[2]);
		
		useingHealthPotion = new Animation(1, tex.Potions[7]);
		useingStaminaPotion = new Animation(1, tex.Potions[6]);
		useingPowerPotion = new Animation(1, tex.Potions[8]);
		
		healthPotionBlack = new Animation(1, tex.Potions[4]);
		staminaPotionBlack = new Animation(1, tex.Potions[3]);
		powerPotionBlack = new Animation(1, tex.Potions[5]);
		
		levelUpArrow = new Animation(10, tex.LevelUp[0], tex.LevelUp[1], tex.LevelUp[2]);
		
	
	
	}

	public void tick(LinkedList<GameObject> object) {
		
		//LevelUpTimmer
		if(levelUp){
			levelUpTimer++;
			if(levelUpTimer >= 130){
				levelUpTimer = 0;
				levelUp = false;
			}
		}
		
		//Power potion aura timer
		if(getPowerPotionAura()){
			powerPotionAuraTimer--;
			if(powerPotionAuraTimer == 0){
				powerPotionAuraTimer = 420;
				setPowerPotionAura(false);
			}
		}
	
		//drinking potion timer 
		if(getDrinkingHealthPotion() || getDrinkingStaminaPotion() || getDrinkingPowerPotion() ){
			drinkingTimer++;
			if(drinkingTimer ==  90){
				drinkingTimer = 0;
				setDrinkingHealthPotion(false);
				setDrinkingStaminaPotion(false);
				setDrinkingPowerPotion(false);
			}
		}
		
		if(performingInterrupt){
			lookingForIntterruptTarget++;
		}
		if(lookingForIntterruptTarget == 10){
			performingInterrupt = false;
			lookingForIntterruptTarget = 0;
		}
		playerRunRight.runAnimation();
		playerRunLeft.runAnimation();
		
		playerIdleRight.runAnimation();
		playerIdleLeft.runAnimation();	
		
		playerJumpRight.runAnimation();
		playerJumpLeft.runAnimation();
		
		playerSlashingRight.runAnimation();
		playerSlashingLeft.runAnimation();
		playerChargeRight.runAnimation();
		playerChargeLeft.runAnimation();
		
		playerSlashingRightPowerAura.runAnimation();
		playerSlashingLeftPowerAura.runAnimation();
		playerChargeRightPowerAura.runAnimation();
		playerChargeLeftPowerAura.runAnimation();
	
		playerFaintRight.runAnimation();
		playerFaintLeft.runAnimation();
		
		interruptNormal.runAnimation();
		useingInterrupt.runAnimation();
		interruptCant.runAnimation();
		interruptAbilityMissingStamina.runAnimation();
		slashAbility.runAnimation();
		slashAbilityCant.runAnimation();
		chargeAbility.runAnimation();
		chargeAbilityCant.runAnimation();
		chargeAbilityMissingStamina.runAnimation();
		
		slashAttackAbility.runAnimation();
		chargeAttackAbility.runAnimation();
		
		playerInterruptRight.runAnimation();
		playerInterruptLeft.runAnimation();
		
		healthPotion.runAnimation();
		staminaPotion.runAnimation();
		powerPotion.runAnimation();
		
		useingHealthPotion.runAnimation();
		useingStaminaPotion.runAnimation();
		useingPowerPotion.runAnimation();
		
		healthPotionBlack.runAnimation();
		staminaPotionBlack.runAnimation();
		powerPotionBlack.runAnimation();
		
		levelUpArrow.runAnimation();
		playerDeath.runAnimation();
		
		if(deathTimer == 0){
			y += velY;
			x += velX;
		}
		else{
			y += velY;
			velY = (float) -2.5;
			velX = 0;
			health = 0;
		}

		
		if(stamina >= 200){
			stamina = 200;
		}
		if(stamina <= 0){
			stamina = 0;
			performingAbility = false;
		}
		if(health <= 0){
			health = 0;
			deathTimer++;
			if(deathTimer == 200){
				setDeath(true);
				//deathTimer = 0;
			}
		}
		if(deathTimer == 1 && getCurrentLevel() < 4){
			Game.playMusic(GameMusic, false);
			Game.playMusic(DeafeatSound, true);
		}
		if(deathTimer == 1 && getCurrentLevel() == 4){
			Game.playMusic(CryptGameMusic, false);
			Game.playMusic(DeafeatSound, true);
		}
		
		if(health >= 200){
			health = 200;
		}

		
		//player affected by gravity
		if(getJumping()){
			velY += gravity;
		}
		
		//check for faint
		if(stamina <= 0.5){
			setFaint(true);
			Game.playSound(SleepingSound, true);
		}
		//incremet faintTimer
		if(getFaint()){
			faintTimer++;
			health += 0.5;
			velX = 0;
		}
		//check for stop faint
		if(faintTimer == 150){
			setFaint(false);
			faintTimer = 0;
		}
		if(velX >= 30){
			velX = 30;
		}
		
		collision(object);
	}
	
	public void collision(LinkedList<GameObject> object){
		
		//player collsion with environment
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER){
				//checks for collision left wall
				if(tempObject.getX() <= 600 && Game.state == GAMESTATE.CEMETARY){
					tempObject.setVelX(0);
					tempObject.setX(600);
				}
				if(tempObject.getX() <= 485 && Game.state == GAMESTATE.CRYPT){
					tempObject.setVelX(0);
					tempObject.setX(485);
				}
				//checks for collision right wall
				if(tempObject.getX() >= 6210 && Game.state == GAMESTATE.CEMETARY){
					tempObject.setVelX(0);
					tempObject.setX(6210);
				}
				if(tempObject.getX() >= 6190 && Game.state == GAMESTATE.CRYPT){
					tempObject.setVelX(0);
					tempObject.setX(6190);
				}
				//checks for collision ground
				if(tempObject.getY() >= Game.HEIGHT/2 + 85 && Game.state == GAMESTATE.CEMETARY && deathTimer == 0){
					tempObject.setVelY(0);
					tempObject.setY(Game.HEIGHT/2 + 85);
					tempObject.setJumping(false);
				}
				if(tempObject.getY() >= Game.HEIGHT/2 + 60 && Game.state == GAMESTATE.CRYPT && deathTimer == 0){
					tempObject.setVelY(0);
					tempObject.setY(Game.HEIGHT/2 + 60);
					tempObject.setJumping(false);
				}
				//Go to scoreboard if death
				if(getDeath()){
					Game.state = GAMESTATE.SCOREBOARD;
					Game.doRestart = true;
				}
			}
		}
		
		//Player picks up Health Potion
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.POTION){
				if(tempObject.getBounds().intersects(getBounds()) && tempObject.getPotionType() == 1 && tempObject.getDoneBouncing()){
					handler.removeObject(tempObject);
					Player.healthPotionInInventory++;
					Game.playSound(LootingSound, true);
				}
			}
		}
		//Player picks up Stamina Potion
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.POTION){
				if(tempObject.getBounds().intersects(getBounds()) && tempObject.getPotionType() == 2 && tempObject.getDoneBouncing()){
					handler.removeObject(tempObject);
					Player.staminaPotionInInventory++;
					Game.playSound(LootingSound, true);
				}
			}
		}
		//Player picks up Power Potion
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.POTION){
				if(tempObject.getBounds().intersects(getBounds()) && tempObject.getPotionType() == 3 && tempObject.getDoneBouncing()){
					handler.removeObject(tempObject);
					Player.powerPotionInInventory++;
					Game.playSound(LootingSound, true);
				}
			}
		}
	}

	public void render(Graphics g) {
		
	
		if(Game.state == GAMESTATE.CEMETARY || Game.state == GAMESTATE.CRYPT){
			//if skeleton attack player
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.PURPLESKELETON && !tempObject.getDeath()){
					if(tempObject.getAttacking()){
						health -= 0.003;
					}
				}	
			}
			
			//if ShadoBall hit player
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.SHADOWBALL && !getDeath()){
					if(tempObject.getBounds().intersects(getBounds())){
						handler.removeObject(tempObject);
						health -= 30;
					}
				}	
			}
			
			//if McGregory meele attack hit player
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.MCGREGORY && !getDeath()){
					if(tempObject.getAttacking()){
						health -= 0.006;
					}
				}	
			}
				
			/*
			g.setColor(Color.red);
			g.drawRect((int)x, 0, 1, 720);
			*/
			
			if(deathTimer > 0){
				playerDeath.drawAnimations(g, (int) x - 150 , (int) y + 50  , 350, 350);		
			}
			else{
				
				//faint right animation
				if(getFaint() && faintTimer < 150 && getFacingRight()){
					stamina += 0.02f;
					playerFaintRight.drawAnimations(g, (int) x , (int) y, 160, 160);
				}
				
				//faint left animation
				if(getFaint() && faintTimer < 150 && getFacingLeft()){
					stamina += 0.02f;
					playerFaintLeft.drawAnimations(g, (int) x, (int) y, 160, 160);
				}
				else if(!getFaint()){
					//slash right animation
					if(getSlashing() && getFacingRight() && stamina > 0.5 ){
						if(getPowerPotionAura()){
							playerSlashingRightPowerAura.drawAnimations(g, (int) x - 80, (int) y - 75  , 220, 220);
						}
						else{
							playerSlashingRight.drawAnimations(g, (int) x - 80, (int) y - 75  , 220, 220);
						}
						stamina -= 0.01;
					}
					//slash left animation
					if(getSlashing() && getFacingLeft() && stamina > 0.5){
						if(getPowerPotionAura()){
							playerSlashingLeftPowerAura.drawAnimations(g, (int) x - 80 , (int) y - 75  , 220, 220);
						}
						else{
							playerSlashingLeft.drawAnimations(g, (int) x - 80 , (int) y - 75  , 220, 220);
						}
						stamina -= 0.01;
					}
					
					//charge right animation
					if(performingAbility && getFacingRight() && stamina > 0){
						if(getPowerPotionAura()){
							playerChargeRightPowerAura.drawAnimations(g, (int) x - 15, (int) y - 95  , 350, 350);
						}
						else{
							playerChargeRight.drawAnimations(g, (int) x - 15, (int) y - 95  , 350, 350);
						}
						velX += 0.01;
						if(velX > 30){
							performingAbility = false;
							velX = 10;
						}
					}
					//charge left animation
					if(performingAbility && getFacingLeft() && stamina > 0){
						if(getPowerPotionAura()){
							playerChargeLeftPowerAura.drawAnimations(g, (int) x - 270, (int) y - 95  , 350, 350);
						}
						else{
							playerChargeLeft.drawAnimations(g, (int) x - 270, (int) y - 95  , 350, 350);
						}
						velX -= 0.01;
						if(velX < -30){
							performingAbility = false;
							velX = -10;
						}
					}
				
					//jumping right animation
					else if(getJumping() && getFacingRight() && !getSlashing() && !Player.performingAbility){
						playerJumpRight.drawAnimations(g, (int) x -5, (int) y  , 128, 128);
						stamina += 0.005;
					}
					//jumping left animation
					else if(getJumping() && getFacingLeft() && !getSlashing() && !Player.performingAbility){
						playerJumpLeft.drawAnimations(g, (int) x - 5, (int) y , 128, 128);
						stamina += 0.005;
					}
					else if(!getJumping() && !getSlashing() && !performingAbility){
						stamina += 0.005;
						//run right animation
						if(velX > 0){
							playerRunRight.drawAnimations(g, (int) x -5, (int) y , 128, 128);
						}
						//run left animation
						if(velX < 0){
							playerRunLeft.drawAnimations(g,(int) x-5, (int) y, 128, 128);
						}
						//idle left animation
						if(velX == 0 && getFacingLeft()){
							playerIdleLeft.drawAnimations(g, (int) x-5 , (int) y + 20 , 100, 100);
						}
						//idle right animation
						if(velX == 0 && getFacingRight()){
							playerIdleRight.drawAnimations(g, (int) x -5 , (int) y + 20 , 100, 100);
						}		
					}
				}
			}
							
			Graphics2D g2d = (Graphics2D) g;
			/*
			g2d.setColor(Color.BLUE);
			g2d.draw(getBounds());
			*/
			if(deathTimer == 0){
				Font fnt0 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 15);
				g2d.setFont(fnt0);
				//stamina bar
				g.setColor(Color.black);
				g.fillRect((int) (x - 40 ), 26, 200, 16);
				g.setColor(Color.blue);
				g.fillRect((int) (x - 40), 26, (int) stamina, 16);
				
				//health bar
				g.setColor(Color.black);
				g.fillRect((int) (x - 40 ), 5, 200, 16);
				g.setColor(Color.green);
				g.fillRect((int) (x - 40), 5, (int) health, 16);
				
				//XP bar
				g.setColor(Color.DARK_GRAY);
				Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
				g.fillRect((int) (x - 240), Game.HEIGHT - 90, 600, 10);
				g.setColor(Color.pink);
				g.fillRect((int) (x - 240), Game.HEIGHT - 90, xp*3, 10);
				g2d.setFont(fnt2);
				g.setColor(Color.pink);
				g.drawString("XP", (int) x + 40, Game.HEIGHT - 95);
				
				//action bar	
				g.setColor(Color.DARK_GRAY);
				//g.fillRect((int) (x - 27), Game.HEIGHT - 67, 155, 55);
				//g.clearRect((int) (x - 50), Game.HEIGHT - 65, 45, 45);
				g.clearRect((int) (x -25), Game.HEIGHT - 65, 45, 45);
				g.clearRect((int) (x + 25), Game.HEIGHT - 65, 45, 45);
				g.clearRect((int) (x + 75), Game.HEIGHT - 65, 45, 45);
				
				if(!getFaint() && !getDeath() && !getSlashing()){
					slashAbility.drawAnimations(g,(int) (x -25), Game.HEIGHT - 65, 45, 45);
				}
				if(getSlashing()){
					slashAttackAbility.drawAnimations(g, (int) (x -25), Game.HEIGHT - 65, 45, 45);
				}
				if(getFaint()){
					slashAbilityCant.drawAnimations(g, (int) (x -25), Game.HEIGHT - 65, 45, 45);
				}
				if(!getFaint() && !performingAbility && stamina > 51){
					chargeAbility.drawAnimations(g, (int) (x + 25), Game.HEIGHT - 65, 45, 45);
				}
				if(getFaint()){
					chargeAbilityCant.drawAnimations(g, (int) (x + 25), Game.HEIGHT - 65, 45, 45);
				}
				if(performingAbility && stamina > 50){
					chargeAttackAbility.drawAnimations(g, (int) (x + 25), Game.HEIGHT - 65, 45, 45);
				}
				if(!getFaint() && stamina < 50){
					chargeAbilityMissingStamina.drawAnimations(g,(int) (x + 25), Game.HEIGHT - 65, 45, 45);
				}
				
				
				if(!getFaint() && !performingInterrupt && stamina > 10){
					interruptNormal.drawAnimations(g,(int) (x + 75), Game.HEIGHT - 65, 45, 45);
				}
				if(getFaint()){
					interruptCant.drawAnimations(g, (int) (x + 75), Game.HEIGHT - 65, 45, 45);
				}
				if(performingInterrupt && stamina > 10){
					useingInterrupt.drawAnimations(g, (int) (x + 75), Game.HEIGHT - 65, 45, 45);
				}
				if(!getFaint() && stamina < 10){
					interruptAbilityMissingStamina.drawAnimations(g,(int) (x + 75), Game.HEIGHT - 65, 45, 45);
				}
			

			
				
				//StaminaError
				if(staminaError && errorTimer < 2000){
					g.setColor(Color.red);
					Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 20);
					g2d.setFont(fnt1);
					g.drawString("To low on stamina!", (int) x - 28,  150);
					errorTimer++;
					if(errorTimer == 2000){
						errorTimer = 0;
						staminaError = false;
					}
				}
				
				//level up
				if(xp >= 200){
					playerLevel++;
					xp = 0;
					levelUp = true;
					Game.playSound(LevelUpSound, true);
					health = 200;
					stamina = 200;
					setCurrentLevel(getCurrentLevel() + 1);
				}
				if(levelUp){
					levelUpArrow.drawAnimations(g, (int) (x - 150) , (int) (y - 50), 150, 150);
				}
				Font fnt1 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 18);
				g2d.setFont(fnt1);
				g.setColor(Color.PINK);
				g.drawString("Player Level : " + getCurrentLevel(), (int) x - 5, 60);
				
				
				
				//Potion "bar"
				g.setColor(Color.WHITE);
				Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 10);
				g2d.setFont(fnt3);
				g.setColor(Color.RED);
				g.drawString("Health Potion", (int) (x - 550), 60);
				g.setColor(Color.blue);
				g.drawString("Stamina Potion", (int) (x- 450), 60);
				g.setColor(Color.YELLOW);
				g.drawString("Power Potion", (int) (x- 340), 60);
				g.setColor(Color.white);
				Font fnt4 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 15);
				g2d.setFont(fnt4);
				g.drawString("Q", (int) (x - 545), 77);
				g.drawString("W", (int) (x- 445), 77);
				g.drawString("E", (int) (x- 335), 77);
				
				
				g.setColor(Color.WHITE);
				g.drawString("x   " + healthPotionInInventory, (int) (x - 510), 40);
				g.drawString("x   " + staminaPotionInInventory, (int) (x - 410), 40);
				g.drawString("x   " + powerPotionInInventory, (int) (x - 300), 40);
				g.setColor(Color.yellow);
				Font fnt5 = new Font("Comic Sans MS 10 Bold", Font.PLAIN, 12);
				g2d.setFont(fnt5);
				if(getPowerPotionAura()){
					g.setColor(Color.yellow);
					g.drawString("Aura active :  " +  + (powerPotionAuraTimer/40) ,(int) (x - 250), 40);
					
				}
				
				
				
				if(healthPotionInInventory > 0 && !getDrinkingHealthPotion()){
					healthPotion.drawAnimations(g,(int) (x - 560), 0, 50, 50);
				}
				if(getDrinkingHealthPotion()){
					useingHealthPotion.drawAnimations(g,(int) (x - 560), 0, 50, 50);
					if(health < 200){
						health += 0.05;
					}
				}
				if(healthPotionInInventory < 1 && !getDrinkingHealthPotion()){
					healthPotionBlack.drawAnimations(g,(int) (x - 560), 0, 50, 50);
				}
				
				
				if(staminaPotionInInventory > 0 && !getDrinkingStaminaPotion()){
					staminaPotion.drawAnimations(g,(int) (x- 460), 0, 50, 50);
				}
				if(getDrinkingStaminaPotion()){
					useingStaminaPotion.drawAnimations(g,(int) (x- 460), 0, 50, 50);
					if(stamina < 200){
						stamina += 0.05;
					}
				}
				if(staminaPotionInInventory < 1 && !getDrinkingStaminaPotion()){
					staminaPotionBlack.drawAnimations(g,(int) (x- 460), 0, 50, 50);
				}
				
				
				if(powerPotionInInventory > 0 && !getDrinkingPowerPotion()){
					powerPotion.drawAnimations(g,(int) (x- 350), 0, 50, 50);
				}
				if(getDrinkingPowerPotion()){
					useingPowerPotion.drawAnimations(g,(int) (x- 350), 0, 50, 50);
				}
				if(powerPotionInInventory < 1 && !getDrinkingPowerPotion()){
					powerPotionBlack.drawAnimations(g,(int) (x- 350), 0, 50, 50);
				}
			}
		}
	}
	public Rectangle getExplosionBounds(){
		return new Rectangle( (int) x - 80 , (int) y - 130 , 180, 180);
		
	}
	public Rectangle getBounds() {
		if((velX == 0 && velY == 0)){
			return new Rectangle((int) x , (int) y, 100, 128);	
		}
		if(getJumping() && !getSlashing()){
			return new Rectangle((int) x , (int) y, 90, 100);	
		}
		if(performingAbility && getFacingRight()){
			return new Rectangle((int) x + 100, (int) y, 100, 130);	
		}
		if(performingAbility && getFacingLeft()){
			return new Rectangle((int) x - 100, (int) y, 100, 130);	
		}
		else{
			return new Rectangle((int) x , (int) y, 128, 128);	
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