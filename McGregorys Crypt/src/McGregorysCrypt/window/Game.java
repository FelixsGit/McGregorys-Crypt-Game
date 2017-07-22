package McGregorysCrypt.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import McGregorysCrypt.frameWorks.GameObject;
import McGregorysCrypt.frameWorks.KeyInput;
import McGregorysCrypt.frameWorks.MouseInput;
import McGregorysCrypt.frameWorks.ObjectId;
import McGregorysCrypt.frameWorks.Texture;
import McGregorysCrypt.objects.DarkMage;
import McGregorysCrypt.objects.McGregory;
import McGregorysCrypt.objects.Player;
import McGregorysCrypt.objects.PurpleSkeleton;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -4568906930464775450L;
	public static int WIDTH;
	public static int HEIGHT; 
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	static Texture tex;
	private Camera cam;
	private BufferedImage cemetaryBackground = null;
	private BufferedImage cemetaryBackgroundToCrypt = null;
	private BufferedImage cemetaryToCryptBlackScreen = null;
	private BufferedImage cryptBackground = null;
	private BufferedImage menuBackground = null;
	private BufferedImage pauseBackground = null;
	private BufferedImage helpBackground = null;
	private BufferedImage defeatBackgrund = null;
	private BufferedImage victoryBackground = null;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private boolean showBlackScreen = false;
	private int showBlackScreenTimer = 0;
	private int skeletonSpawnTimer = 0;
	private int darkMageSpawnTimer = 0;
	private int playerLevel = 0;
	public static Clip MusicClip;
	public static Clip SoundClip;
	private File CryptDoorSound = new File("res/CryptDoorSound.wav");
	private File GameMusic = new File("res/GameMusic.wav");
	private File CryptGameMusic = new File("res/CryptGameMusic.wav");
	public static boolean doRestart = false;
	

	
	public static enum GAMESTATE{
		
		CEMETARY,
		CEMETARYTOCRYPT,
		CRYPT,
		PAUSED,
		MENU,
		HELP,
		SCOREBOARD,
		VICTORY,
		
	}

	public static GAMESTATE state = GAMESTATE.MENU;
	
	public void init() {
		
		handler = new Handler();
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		cam = new Camera(0, 0);
		BufferedImageLoader loader = new BufferedImageLoader();
		
		cemetaryBackground = loader.loadImage("/CemetaryBackGround.png");
		cemetaryBackgroundToCrypt = loader.loadImage("/CemetaryBackGroundToCrypt.png");
		cryptBackground = loader.loadImage("/CryptBackGround.png");
		cemetaryToCryptBlackScreen = loader.loadImage("/CemetaryToCryptBlackScreen.png");
		menuBackground = loader.loadImage("/MenuBackGround.png");
		pauseBackground = loader.loadImage("/PauseBackGround.png");
		helpBackground = loader.loadImage("/HelpBackGround.png");
		defeatBackgrund = loader.loadImage("/DefeatBackGround.png");
		victoryBackground = loader.loadImage("/VictoryBackGround.png");
		
		this.addKeyListener(new KeyInput(handler));	
		this.addMouseListener(new MouseInput());
		
		handler.addObject(new Player(getWidth()/2 + 85, getHeight()/2 + 85, handler, ObjectId.PLAYER));
		//handler.addObject(new PurpleSkeleton(getWidth()/2, getHeight()/2 + 85, handler, ObjectId.PURPLESKELETON));
		//handler.addObject(new DarkMage(getWidth()/2, getHeight()/2 + 85, handler, ObjectId.DARKMAGE ));
		//handler.addObject(new McGregory(1500 , Game.HEIGHT/2 , handler, ObjectId.MCGREGORY));
		

	}

	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 45;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		//int updates = 0;
		//int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				//updates++;
				delta--;
			}
			render();
		
			//frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				//frames = 0;
				//updates = 0;
			}
		}
	}
	private void tick(){
		
		if(doRestart){
			Player.levelUp = false;
			Player.stamina = 200;
			Player.staminaError = false;
			Player.performingAbility = false;
			Player.performingInterrupt = false;
			Player.lookingForIntterruptTarget = 0;
			Player.healthPotionInInventory = 0;
			Player.staminaPotionInInventory = 0;
			Player.powerPotionInInventory = 0;
			Player.xp = 0;
			Player.health = 200;
			McGregory.deathTimer = 0;
			showBlackScreenTimer = 0;
			skeletonSpawnTimer = 0;
			darkMageSpawnTimer = 0;
			doRestart = false;
			tex = new Texture();
			handler = new Handler();
			this.addKeyListener(new KeyInput(handler));	
			cam = new Camera(0,0);
			handler.addObject(new Player(getWidth()/2 + 85, getHeight()/2 + 85, handler, ObjectId.PLAYER));
		}
		
		if(Game.state == GAMESTATE.CEMETARY || Game.state == GAMESTATE.CRYPT || Game.state == GAMESTATE.CEMETARYTOCRYPT || McGregory.deathTimer > 0){
			//removing units if you walk downthe crypt
			if(Game.state == GAMESTATE.CEMETARYTOCRYPT || (Game.state == GAMESTATE.CEMETARY && Player.playerLevel == 4) || McGregory.deathTimer > 0 ){
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getId() == ObjectId.PURPLESKELETON || tempObject.getId() == ObjectId.DARKMAGE || tempObject.getId() == ObjectId.POTION || tempObject.getId() == ObjectId.SHADOWBALL){
						handler.removeObject(tempObject);
					}
				}
			}
		
			for(int i = 0; i < handler.object.size(); i++){
				if(handler.object.get(i).getId() == ObjectId.PLAYER){
					cam.tick(handler.object.get(i));
				}
			}
			
			if(Player.levelUp){
				skeletonSpawnTimer = 0;
				darkMageSpawnTimer = 0;
			}
			
			if(showBlackScreen){
				showBlackScreenTimer++;
			}
			handler.tick();	
		}
	}
	
	public static Texture getInstance(){
		return tex;
	}
	
	private void render(){

		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 7300, 720);

		//Draw here
		g2d.translate(cam.getX(), cam.getY()); //Begin of cam
		
		if(Game.state == GAMESTATE.CEMETARY || Game.state == GAMESTATE.CRYPT || Game.state == GAMESTATE.CEMETARYTOCRYPT){
			
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.PLAYER){
					if(state == GAMESTATE.CEMETARY && tempObject.getCurrentLevel() == 4 && tempObject.getX() >= 6210){
						state = GAMESTATE.CEMETARYTOCRYPT;
						showBlackScreen = true;
						Game.playMusic(GameMusic, false);
						Game.playSound(CryptDoorSound, true);
					}
					if(state == GAMESTATE.CEMETARY && tempObject.getCurrentLevel() < 4){
						g.setColor(Color.BLACK);	
						g.drawImage(cemetaryBackground, 0, 0, 7680, 720, null);
					}
					if(state == GAMESTATE.CEMETARY && tempObject.getCurrentLevel() == 4){
						g.setColor(Color.BLACK);	
						g.drawImage(cemetaryBackgroundToCrypt, 0, 0, 7680, 720, null);
					}
					if(state == GAMESTATE.CEMETARYTOCRYPT && showBlackScreen){
						if(showBlackScreen == true && showBlackScreenTimer <= 300){
							g.setColor(Color.BLACK);	
							g.drawImage(cemetaryToCryptBlackScreen, 0, 0, 7680, 720, null);
						}
						if(showBlackScreenTimer > 300){
							showBlackScreen = false;
							showBlackScreenTimer = 0;
							state = GAMESTATE.CRYPT;
							Game.playMusic(CryptGameMusic, true);
							tempObject.setX(Game.WIDTH/2 + 85);
							tempObject.setY(Game.HEIGHT/2 + 60);
							handler.addObject(new McGregory(6000 , Game.HEIGHT/2 , handler, ObjectId.MCGREGORY));
						}
					}
					if(state == GAMESTATE.CRYPT && !showBlackScreen){
						g.setColor(Color.BLACK);	
						g.drawImage(cryptBackground, 0, 0, 7680, 720, null);
					}
				}
			}
			
			//spawning skeletons
		
			if(Game.state == GAMESTATE.CEMETARY){
				for(int i = 0; i < handler.object.size(); i++){
					GameObject tempObject = handler.object.get(i);
					if(tempObject.getId() == ObjectId.PLAYER){
						skeletonSpawnTimer++;
						if((tempObject.getCurrentLevel() == 1 && skeletonSpawnTimer == 5000) || (tempObject.getCurrentLevel() == 2 && skeletonSpawnTimer == 3500) || (tempObject.getCurrentLevel() == 3 && skeletonSpawnTimer == 2000)){
							Random rand = new Random();
							int randomNum = rand.nextInt((1 - -1) + 1) + -1;
							if(randomNum != 0){
								handler.addObject(new PurpleSkeleton(tempObject.getX() + (1000*randomNum), Game.HEIGHT/2 + 85, handler, ObjectId.PURPLESKELETON));
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
						if((tempObject.getCurrentLevel() == 1 && darkMageSpawnTimer == 15000) || (tempObject.getCurrentLevel() == 2 && darkMageSpawnTimer == 12000) || (tempObject.getCurrentLevel() == 3 && darkMageSpawnTimer == 8000)){
							Random rand = new Random();
							int randomNum = rand.nextInt((1 - -1) + 1) + -1;
							if(randomNum != 0){
								handler.addObject(new DarkMage(tempObject.getX() + (1000*randomNum), Game.HEIGHT/2 + 85, handler, ObjectId.DARKMAGE));
							}
							darkMageSpawnTimer = 0;
						}
					}
				}
			}
			
			handler.render(g);
		}
		
		if(Game.state == GAMESTATE.MENU){
			g.drawImage(menuBackground, 0, 0, null);
			g.setColor(Color.white);
			g.drawString("By Felix Toppar 21/07/20117", 5, Game.HEIGHT - 5);
			
		}
		if(Game.state == GAMESTATE.PAUSED){
			for(int i = 0; i < handler.object.size(); i++){
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ObjectId.PLAYER){
					g.drawImage(pauseBackground, (int) tempObject.getX() - 563, 0,  null);
				}
				
			}
		}
		if(Game.state == GAMESTATE.HELP){
			g.drawImage(helpBackground,  0, 0,null);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
			g.setColor(Color.white);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt3);
			g.drawString("You decided to enter the local cemetary", 180, 335);
			g.drawString("where the McGregor family is burried.", 180, 360);
			g.drawString("You start to hear noises and see herds of", 180, 400);
			g.drawString("undead moving towards you!", 180, 425);
			g.drawString("Your objective is clear your way to", 180, 460);
			g.drawString("McGregorys Crypt and slay the demon!", 180, 485);
			g.drawString("Level up and put your abilitys to use, dont", 180, 525);
			g.drawString("forget to drink potions. Good Luck!", 180, 555);
			
			g.drawString("LeftArrow - move left", 830, 335);
			g.drawString("RightArrow - move right", 830, 360);
			g.drawString("Space - jump", 830, 385);
			g.drawString("Q - healing potion", 830, 410);
			g.drawString("W - stamina potion", 830, 435);
			g.drawString("E - power potion", 830, 460);
			g.drawString("1 - slash", 830, 485);
			g.drawString("2 - charge", 830, 510);
			g.drawString("3 - interrupt", 830, 535);
			g.drawString("ESC - pause", 830, 560);	
			
		}
		
		if(Game.state == GAMESTATE.SCOREBOARD){
			g.drawImage(defeatBackgrund, (int) 0, 0,  null);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt3);
			g.drawString("The undead army together with",Game.WIDTH/2 - 100, Game.HEIGHT/2 - 115);
			g.drawString("McGregory was to much for you", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 90);
			g.drawString("to handle.", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 65);
			g.drawString("McGregorys minions will now spread", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 25);
			g.drawString("and take over the world", Game.WIDTH/2 - 100, Game.HEIGHT/2 );			
		}
		if(Game.state == GAMESTATE.VICTORY){
			g.drawImage(victoryBackground, (int) 0, 0,  null);
			Font fnt2 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 25);
			Rectangle backButton = new Rectangle(Game.WIDTH - 150, Game.HEIGHT - 100 , 100, 50);
			g.setFont(fnt2);
			g.setColor(Color.black);
			g.drawString("Menu", Game.WIDTH - 135 , Game.HEIGHT - 65);
			g2d.draw(backButton);
			Font fnt3 = new Font("Comic Sans MS 10 Bold", Font.BOLD, 20);
			g.setFont(fnt3);
			g.setColor(Color.GREEN);
			g.drawString("With McGregory and his minions dead",Game.WIDTH/2 - 530, Game.HEIGHT/2 - 140);
			g.drawString("the night was over and peace was returned", Game.WIDTH/2 - 530, Game.HEIGHT/2 - 115);
			g.drawString("to the cemetary.", Game.WIDTH/2 - 530, Game.HEIGHT/2 - 90);
			g.drawString("McGregorys will never again wake and bring", Game.WIDTH/2 - 530, Game.HEIGHT/2 - 65);
			g.drawString("horror on this world again. Good joob hero!", Game.WIDTH/2 - 530, Game.HEIGHT/2 - 40);			
		}
		
	
		g2d.translate(-cam.getX(), -cam.getY() ); //End of cam
		
		//Stop Drawing here
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String[] args){
		File MenuBackGroundMusic = new File("res/MenuBackGroundMusic.wav");
		new Window(1280, 720, "McGregorys Crypt", new Game());
		playMusic(MenuBackGroundMusic, true);
	}
	
	public static void playSound(File Sound, boolean run){
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
	public static void playMusic(File Sound, boolean run){
		if(run){
			try{
	
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound);
				MusicClip = AudioSystem.getClip();
				MusicClip.open(audioInputStream);
				MusicClip.start();
				MusicClip.loop(3);
				
			}catch(Exception e){
				
			}
		}
		else
			MusicClip.stop();
	}
}