package McGregorysCrypt.frameWorks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public abstract class GameObject {
	
	protected float x, y;
	protected float velX = 0 , velY = 0;
	protected ObjectId id;
	protected boolean death = false;
	protected boolean jumping = false;
	protected boolean facingRight = false;
	protected boolean facingLeft = true;
	protected boolean attacking = false;
	protected boolean slashing = false;
	protected boolean charging = false;
	protected boolean faint = false;
	protected boolean toClose = false;
	protected boolean doneBouncing = false;
	protected int idNummer;
	protected int potionType;
	protected boolean drinkingHealthPotion = false;
	protected boolean drinkingStaminaPotion = false;
	protected boolean drinkingPowerPotion = false;
	protected boolean giveYspeed = false;
	protected boolean stationary = false;
	protected boolean firstBounce = false;
	protected boolean secondBounce = false;
	protected boolean thridBounce = false;
	protected boolean powerPotionAura = false;
	protected Clip MusicClip;
	protected Clip SoundClip;
	protected boolean run = false;
	protected int currentLevel = 1;
	protected boolean mcGregoryCastingShadowBall = false;
	
	public GameObject(float x, float y, ObjectId id){
		this.x = x;
		this.y = y;	
		this.id = id;
	}
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	public ObjectId getId(){
		return id;
	}
	
	public boolean getDeath(){
		return death;
	}
	public void setDeath(boolean death){
		this.death = death;
	}
	
	public boolean getJumping(){
		return jumping;
	}
	public void setJumping(boolean jumping){
		this.jumping = jumping;
	}
	public boolean getFacingRight(){
		return facingRight;
	}
	public void setFacingRight(boolean facingRight){
		this.facingRight = facingRight;
	}	
	public boolean getFacingLeft(){
		return facingLeft;
	}
	public void setFacingLeft(boolean facingLeft){
		this.facingLeft = facingLeft;
	}	
	public boolean getSlashing(){
		return slashing;
	}
	public void setSlashing(boolean slashing){
		this.slashing = slashing;
	}
	public boolean getCharging(){
		return charging;
	}
	public void setCharging(boolean charging){
		this.charging = charging;
	}
	public boolean getAttacking(){
		return attacking;
	}
	public void setAttacking(boolean attacking){
		this.attacking = attacking;
	}
	public boolean getFaint(){
		return faint;
	}
	public void setFaint(boolean faint){
		this.faint = faint;
	}
	public boolean getToClose(){
		return toClose;
	}
	public void setToClose(boolean toClose){
		this.toClose = toClose;
	}
	public int getIdNummer(){
		return idNummer;
	}
	
	public void setIdNummer(int idNummer){
		this.idNummer = idNummer;
	}
	public int getPotionType(){
		return potionType;
	}
	public void setPotionType(int potionType){
		this.potionType = potionType;
	}
	public boolean getDoneBouncing(){
		return doneBouncing;
	}
	public void setDoneBouncing(boolean doneBouncing){
		this.doneBouncing = doneBouncing;
	}

	public boolean getDrinkingHealthPotion(){
		return drinkingHealthPotion;
	}
	public void setDrinkingHealthPotion(boolean drinkingHealthPotion){
		this.drinkingHealthPotion = drinkingHealthPotion;
	}
	
	public boolean getDrinkingStaminaPotion(){
		return drinkingStaminaPotion;
	}
	public void setDrinkingStaminaPotion(boolean drinkingStaminaPotion){
		this.drinkingStaminaPotion = drinkingStaminaPotion;
	}
	
	public boolean getDrinkingPowerPotion(){
		return drinkingPowerPotion;
	}
	public void setDrinkingPowerPotion(boolean drinkingPowerPotion){
		this.drinkingPowerPotion = drinkingPowerPotion;
	}
	public boolean getGiveYspeed(){
		return giveYspeed;
	}
	public void setGiveYspeed(boolean giveYspeed){
		this.giveYspeed = giveYspeed;
	}
	
	public boolean getStationary(){
		return stationary;
	}
	public void setStationary(boolean stationary){
		this.stationary = stationary;
	}
	
	public boolean getFirstBounce(){
		return firstBounce;
	}
	public void setFirstBounce(boolean firstBounce){
		this.firstBounce = firstBounce;
	}
	
	public boolean getSecondBounce(){
		return secondBounce;
	}
	public void setSecondBounce(boolean secondBounce){
		this.secondBounce = secondBounce;
	}
	
	public boolean getThirdBounce(){
		return thridBounce;
	}
	public void setThirdBounce(boolean thirdBounce){
		this.thridBounce= thirdBounce;
	}
	public boolean getPowerPotionAura(){
		return powerPotionAura;
	}
	public void setPowerPotionAura(boolean powerPotionAura){
		this.powerPotionAura = powerPotionAura;
	}
	public int getCurrentLevel(){
		return currentLevel;
	}
	public void setCurrentLevel(int currentLevel){
		this.currentLevel = currentLevel;
	}
	public boolean getMcGregoryCastingShadowBall(){
		return mcGregoryCastingShadowBall;
	}
	public void setMcGregoryCastingShadowBall(boolean mcGregoryCastingShadowBall){
		this.mcGregoryCastingShadowBall = mcGregoryCastingShadowBall;
	}
	public void playSound(File Sound, boolean run){
		this.run = run;
		if(run){
			try{
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				SoundClip = AudioSystem.getClip();
				SoundClip.open(audioInputStream);
				SoundClip.start();
				
			}catch(Exception e){
				
			}
		}
		else{
			SoundClip.stop();
		}
			
	}
	public void playMusic(File Sound, boolean run){
		this.run = run;
		if(run){
			try{
	
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				MusicClip = AudioSystem.getClip();
				MusicClip.open(audioInputStream);
				MusicClip.start();
			
			}catch(Exception e){
				
			}
		}
		else
			MusicClip.stop();
	}
	
}