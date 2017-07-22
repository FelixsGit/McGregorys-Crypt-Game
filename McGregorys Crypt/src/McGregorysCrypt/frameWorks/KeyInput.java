package McGregorysCrypt.frameWorks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import McGregorysCrypt.objects.Player;
import McGregorysCrypt.window.Game;
import McGregorysCrypt.window.Handler;
import McGregorysCrypt.window.Game.GAMESTATE;


public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private File JumpSound = new File("res/JumpSound.wav");
	private File ChargeSound = new File("res/ChargeSound.wav");
	private File PotionDrinkingSound = new File("res/PotionDrinkingSound.wav");
	private File GameMusic = new File("res/GameMusic.wav");
	private File CryptGameMusic = new File("res/CryptGameMusic.wav");
	private File MenuBackGroundMusic = new File("res/MenuBackGroundMusic.wav");
	private boolean pausedFromCemetary = false;
	private boolean pausedFromCrypt = false;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER){
				if(key == KeyEvent.VK_LEFT && !tempObject.faint){
					tempObject.setFacingLeft(true);
					tempObject.setFacingRight(false);
					tempObject.setVelX(-10);
					Player.performingAbility = false;
				}
				if(key == KeyEvent.VK_RIGHT && !tempObject.faint ){
					tempObject.setFacingLeft(false);
					tempObject.setFacingRight(true);
					tempObject.setVelX(10);
					Player.performingAbility = false;
				}
				if(key == KeyEvent.VK_SPACE && !tempObject.getJumping() && !tempObject.faint && !Player.performingAbility){
					tempObject.setVelY(-26);
					tempObject.setJumping(true);
					Game.playSound(JumpSound, true);
				}
				if(key == KeyEvent.VK_SPACE && !tempObject.getJumping() && !tempObject.faint && Player.performingAbility){
					tempObject.setVelY(-26);
					tempObject.setJumping(true);
					Game.playSound(JumpSound, true);
				}

				
				if(key == KeyEvent.VK_1 && !tempObject.faint){
					tempObject.setSlashing(true);
					Player.performingAbility = false;
				}
				
				//Button 2
				if(key == KeyEvent.VK_2 && !tempObject.getFaint() && !Player.performingAbility && Player.stamina > 51){ 
					tempObject.setSlashing(false);
					tempObject.setCharging(true);
					Player.performingAbility = true;
					Player.stamina -= 50;
					Game.playSound(ChargeSound, true);
				}
				if(key == KeyEvent.VK_2 && !tempObject.getFaint() && !Player.performingAbility && Player.stamina < 51){ 
					Player.staminaError = true;
				}
				if(key == KeyEvent.VK_2 && !tempObject.getFaint() && Player.performingAbility && !tempObject.getCharging() && Player.stamina > 51){ 
					Player.performingAbility = false;
				}
				
				//Button 3
				if(key == KeyEvent.VK_3 && !tempObject.getFaint() && !Player.performingInterrupt && Player.stamina > 31){ 
					Player.performingInterrupt= true;
					Player.performingAbility = false;
					Player.stamina -= 30;
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_3 && !tempObject.getFaint() && !Player.performingInterrupt && Player.stamina < 31){ 
					Player.staminaError = true;
				}
			}
		}
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.PLAYER){
				if(key == KeyEvent.VK_LEFT && !Player.performingAbility){
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_RIGHT && !Player.performingAbility){
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_1){
					tempObject.setSlashing(false);
				}
				if(key == KeyEvent.VK_2 && !tempObject.faint && Player.stamina > 51){
					tempObject.setSlashing(false);
					tempObject.setCharging(false);
					tempObject.setVelX(0);
				}
				if(key == KeyEvent.VK_Q && !tempObject.getDrinkingHealthPotion() && !tempObject.getDrinkingStaminaPotion() && !tempObject.getDrinkingPowerPotion() && Player.healthPotionInInventory > 0 && !tempObject.getFaint()){
					tempObject.setDrinkingHealthPotion(true);
					Game.playSound(PotionDrinkingSound, true);
					Player.healthPotionInInventory --;
				}
				if(key == KeyEvent.VK_W && !tempObject.getDrinkingHealthPotion() && !tempObject.getDrinkingStaminaPotion() && !tempObject.getDrinkingPowerPotion() && Player.staminaPotionInInventory > 0 && !tempObject.getFaint()){
					tempObject.setDrinkingStaminaPotion(true);
					Game.playSound(PotionDrinkingSound, true);
					Player.staminaPotionInInventory --;
				}
				if(key == KeyEvent.VK_E && !tempObject.getDrinkingHealthPotion() && !tempObject.getDrinkingStaminaPotion() && !tempObject.getDrinkingPowerPotion() && Player.powerPotionInInventory > 0 && !tempObject.getFaint()){
					tempObject.setDrinkingPowerPotion(true);
					tempObject.setPowerPotionAura(true);
					Game.playSound(PotionDrinkingSound, true);
					Player.powerPotionInInventory --;
				}
				if(key == KeyEvent.VK_ESCAPE && Game.state == GAMESTATE.CEMETARY){ 
					pausedFromCemetary = true;
					Game.state = GAMESTATE.PAUSED;
					Game.playMusic(GameMusic, false);
				}
				if(key == KeyEvent.VK_ESCAPE && Game.state == GAMESTATE.CRYPT){ 
					pausedFromCrypt = true;
					Game.state = GAMESTATE.PAUSED;
					Game.playMusic(CryptGameMusic, false);
				}
				if(Game.state == GAMESTATE.PAUSED && key == KeyEvent.VK_ENTER && pausedFromCemetary){
					Game.state = GAMESTATE.CEMETARY;
					Game.playMusic(GameMusic, true);
					pausedFromCemetary = false;
				}
				if(Game.state == GAMESTATE.PAUSED && key == KeyEvent.VK_ENTER && pausedFromCrypt){
					Game.state = GAMESTATE.CRYPT;
					Game.playMusic(CryptGameMusic, true);
					pausedFromCrypt = false;
				}
				if(Game.state == GAMESTATE.PAUSED && key == KeyEvent.VK_M){
					Game.playMusic(CryptGameMusic, false);
					Game.playMusic(GameMusic, false);
					Game.playMusic(MenuBackGroundMusic, true);
					Game.state = GAMESTATE.MENU;
					Game.doRestart = true;
					pausedFromCrypt = false;
					pausedFromCemetary = false;
				}
			}
		}
	}
}