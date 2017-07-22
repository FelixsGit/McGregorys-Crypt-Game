package McGregorysCrypt.frameWorks;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


import McGregorysCrypt.window.Game;
import McGregorysCrypt.window.Game.GAMESTATE;


public class MouseInput implements MouseListener{


	private File GameMusic = new File("res/GameMusic.wav");
	private File MenuBackGroundMusic = new File("res/MenuBackGroundMusic.wav");
	private File buttonClick = new File("res/ClickSound.wav");
	private File DeafeatSound = new File("res/DefeatMusic.wav");
	private File VictoryMusic = new File("res/VictoryMusic.wav");
	
	public void mouseClicked(MouseEvent e) {
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		
	}


	public void mousePressed(MouseEvent e) {
	
		if(Game.state == GAMESTATE.MENU){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing Play Button
			if(xm >= 600 && xm <= 724){
				if(ym >= 347 && ym <= 401 ){
					Game.state = GAMESTATE.CEMETARY;
					Game.playMusic(MenuBackGroundMusic, false);
					Game.playSound(buttonClick, true);
					Game.playMusic(GameMusic, true);
				}
			}
			//Pressing Help Button
			if(xm >= 601 && xm <= 720){
				if(ym >= 425 && ym <= 476 ){
					Game.state = GAMESTATE.HELP;
					Game.playSound(buttonClick, true);
				}
			}
		}
		if(Game.state == GAMESTATE.HELP){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing Play Button
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					//Pressed backButton
					Game.playSound(buttonClick, true);
					Game.state = GAMESTATE.MENU;
				}
			}
		}
		if(Game.state == GAMESTATE.SCOREBOARD){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing Menu Button
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					//Pressed MenuButton
					Game.playSound(buttonClick, true);
					Game.playMusic(DeafeatSound, false);
					Game.playMusic(MenuBackGroundMusic, true);
					Game.state = GAMESTATE.MENU;
				}
			}
		}
		if(Game.state == GAMESTATE.VICTORY){
			int xm = e.getX();
			int ym = e.getY();	
			//Pressing Menu Button
			if((xm >= Game.WIDTH - 150) && (xm <= Game.WIDTH - 50)){
				if((ym >= Game.HEIGHT - 100 ) && (ym <= Game.HEIGHT - 50)){
					//Pressed MenuButton
					Game.playSound(buttonClick, true);
					Game.playMusic(VictoryMusic, false);
					Game.playMusic(MenuBackGroundMusic, true);
					Game.state = GAMESTATE.MENU;
				}
			}
		}
	}

	
	public void mouseReleased(MouseEvent e) {
	
		
	}

}
