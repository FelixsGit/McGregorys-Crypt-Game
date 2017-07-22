package McGregorysCrypt.frameWorks;

import java.awt.image.BufferedImage;

import McGregorysCrypt.window.BufferedImageLoader;

public class Texture {
	
	private SpriteSheet prrs, prls, pirs, pils, pjrs, pjls, psrs, psls, pfrs, pfls, srs, sls, pcrs, pcls, pas, dmrs, dmls, sbs, ps, psrpas, pslpas, pcrpas, pclpas, lus, mgrs, mgls;
	private BufferedImage PlayerRunRight_sheet = null;
	private BufferedImage PlayerRunLeft_sheet = null;
	private BufferedImage PlayerIdleRight_sheet = null;
	private BufferedImage PlayerIdleLeft_sheet = null;
	private BufferedImage PlayerJumpRight_sheet = null;
	private BufferedImage PlayerJumpLeft_sheet = null;
	private BufferedImage PlayerSlashingRight_sheet = null;
	private BufferedImage PlayerSlashingLeft_sheet = null;
	private BufferedImage PlayerFaintRight_sheet = null;
	private BufferedImage PlayerFaintLeft_sheet = null;
	private BufferedImage SkeletonRight_sheet = null;
	private BufferedImage SkeletonLeft_sheet = null;
	private BufferedImage PlayerChargeRight_sheet = null;
	private BufferedImage PlayerChargeLeft_sheet = null;
	private BufferedImage PlayerAbility_sheet = null;
	private BufferedImage DarkMageRight_sheet = null;
	private BufferedImage DarkMageLeft_sheet = null;
	private BufferedImage ShadowBall_sheet = null;
	private BufferedImage Potions_sheet = null;
	private BufferedImage PlayerSlashingRightPowerAura_sheet = null;
	private BufferedImage PlayerSlashingLeftPowerAura_sheet = null;
	private BufferedImage PlayerChargeRightPowerAura_sheet = null;
	private BufferedImage PlayerChargeLeftPowerAura_sheet = null;
	private BufferedImage LevelUp_sheet = null;
	private BufferedImage McGregoryRight_sheet = null;
	private BufferedImage McGregoryLeft_sheet = null;
	
	
	public BufferedImage[] PlayerRunRight = new BufferedImage[6];
	public BufferedImage[] PlayerRunLeft = new BufferedImage[6];
	public BufferedImage[] PlayerIdleRight= new BufferedImage[5];
	public BufferedImage[] PlayerIdleLeft= new BufferedImage[5];
	public BufferedImage[] PlayerJumpRight = new BufferedImage[9];
	public BufferedImage[] PlayerJumpLeft = new BufferedImage[9];
	public BufferedImage[] PlayerSlashingRight = new BufferedImage[5];
	public BufferedImage[] PlayerSlashingLeft = new BufferedImage[5];
	public BufferedImage[] PlayerFaintRight = new BufferedImage[9];
	public BufferedImage[] PlayerFaintLeft = new BufferedImage[9];
	public BufferedImage[] SkeletonRight = new BufferedImage[22];
	public BufferedImage[] SkeletonLeft = new BufferedImage[17];
	public BufferedImage[] PlayerChargeRight = new BufferedImage[5];
	public BufferedImage[] PlayerChargeLeft = new BufferedImage[10];//player death
	public BufferedImage[] PlayerAbility = new BufferedImage[13];
	public BufferedImage[] DarkMageRight = new BufferedImage[13];
	public BufferedImage[] DarkMageLeft = new BufferedImage[13];
	public BufferedImage[] ShadowBall = new BufferedImage[19];
	public BufferedImage[] Potions = new BufferedImage[9];
	public BufferedImage[] PlayerSlashingRightPowerAura = new BufferedImage[5];
	public BufferedImage[] PlayerSlashingLeftPowerAura = new BufferedImage[5];
	public BufferedImage[] PlayerChargeRightPowerAura = new BufferedImage[5];
	public BufferedImage[] PlayerChargeLeftPowerAura = new BufferedImage[5];
	public BufferedImage[] LevelUp = new BufferedImage[3];
	public BufferedImage[] McGregoryRight = new BufferedImage[31];
	public BufferedImage[] McGregoryLeft = new BufferedImage[31];


	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try{
		PlayerRunRight_sheet = loader.loadImage("/PlayerRight_sheet.png");
		PlayerRunLeft_sheet = loader.loadImage("/PlayerLeft_sheet.png");
		PlayerIdleRight_sheet = loader.loadImage("/PlayerRight_sheet.png");
		PlayerIdleLeft_sheet = loader.loadImage("/PlayerLeft_sheet.png");
		PlayerJumpRight_sheet = loader.loadImage("/PlayerJumping_sheet.png");
		PlayerJumpLeft_sheet = loader.loadImage("/PlayerJumping_sheet.png");
		PlayerSlashingRight_sheet = loader.loadImage("/PlayerSlashingRight_sheet.png");
		PlayerSlashingLeft_sheet = loader.loadImage("/PlayerSlashingLeft_sheet.png");
		PlayerFaintRight_sheet = loader.loadImage("/PlayerFaintRight_sheet.png");
		PlayerFaintLeft_sheet = loader.loadImage("/PlayerFaintLeft_sheet.png");
		SkeletonRight_sheet = loader.loadImage("/PurpleSkeletonRight_sheet.png");
		SkeletonLeft_sheet = loader.loadImage("/PurpleSkeletonLeft_sheet.png");
		PlayerChargeRight_sheet = loader.loadImage("/PlayerChargeRight_sheet.png");
		PlayerChargeLeft_sheet = loader.loadImage("/PlayerChargeLeft_sheet.png");
		PlayerAbility_sheet = loader.loadImage("/PlayerAbility_sheet.png");
		DarkMageRight_sheet = loader.loadImage("/DarkMageRight_sheet.png");
		DarkMageLeft_sheet = loader.loadImage("/DarkMageLeft_sheet.png");
		ShadowBall_sheet = loader.loadImage("/ShadowBall_sheet.png");
		Potions_sheet = loader.loadImage("/Potions_sheet.png");
		
		PlayerSlashingRightPowerAura_sheet = loader.loadImage("/PlayerSlashingRightPowerAura_sheet.png");
		PlayerSlashingLeftPowerAura_sheet = loader.loadImage("/PlayerSlashingLeftPowerAura_sheet.png");
		PlayerChargeRightPowerAura_sheet = loader.loadImage("/PlayerChargeRightPowerAura_sheet.png");
		PlayerChargeLeftPowerAura_sheet = loader.loadImage("/PlayerChargeLeftPowerAura_sheet.png");
		
		LevelUp_sheet = loader.loadImage("/PlayerLevelUp_sheet.png");
		
		McGregoryRight_sheet = loader.loadImage("/McGregoryRight_sheet.png");
		McGregoryLeft_sheet = loader.loadImage("/McGregoryLeft_sheet.png");
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		prrs = new SpriteSheet(PlayerRunRight_sheet);
		prls = new SpriteSheet(PlayerRunLeft_sheet);
		pirs = new SpriteSheet(PlayerIdleRight_sheet);
		pils = new SpriteSheet(PlayerIdleLeft_sheet);
		pjrs = new SpriteSheet(PlayerJumpRight_sheet);
		pjls = new SpriteSheet(PlayerJumpLeft_sheet);
		psrs = new SpriteSheet(PlayerSlashingRight_sheet);
		psls = new SpriteSheet(PlayerSlashingLeft_sheet);
		pfrs = new SpriteSheet(PlayerFaintRight_sheet);
		pfls = new SpriteSheet(PlayerFaintLeft_sheet);
		srs = new SpriteSheet(SkeletonRight_sheet);
		sls = new SpriteSheet(SkeletonLeft_sheet);
		pcrs = new SpriteSheet(PlayerChargeRight_sheet);
		pcls = new SpriteSheet(PlayerChargeLeft_sheet);
		pas = new SpriteSheet(PlayerAbility_sheet);
		dmrs = new SpriteSheet(DarkMageRight_sheet);
		dmls = new SpriteSheet(DarkMageLeft_sheet);
		sbs = new SpriteSheet(ShadowBall_sheet);
		ps = new SpriteSheet(Potions_sheet);
		psrpas = new SpriteSheet(PlayerSlashingRightPowerAura_sheet);
		pslpas = new SpriteSheet(PlayerSlashingLeftPowerAura_sheet);
		pcrpas = new SpriteSheet(PlayerChargeRightPowerAura_sheet);
		pclpas = new SpriteSheet(PlayerChargeLeftPowerAura_sheet);
		lus = new SpriteSheet(LevelUp_sheet);
		mgrs = new SpriteSheet(McGregoryRight_sheet);
		mgls = new SpriteSheet(McGregoryLeft_sheet);
			
		getTextures();
	}
	private void getTextures(){
		
		PlayerRunRight[0] = prrs.grabImage(1, 1, 84, 84);
		PlayerRunRight[1] = prrs.grabImage(2, 1, 84, 84);
		PlayerRunRight[2] = prrs.grabImage(3, 1, 84, 84);
		PlayerRunRight[3] = prrs.grabImage(4, 1, 84, 84);
		PlayerRunRight[4] = prrs.grabImage(5, 1, 84, 84);
		PlayerRunRight[5] = prrs.grabImage(6, 1, 84, 84);
		
		PlayerRunLeft[0] = prls.grabImage(1, 1, 84, 84);
		PlayerRunLeft[1] = prls.grabImage(2, 1, 84, 84);
		PlayerRunLeft[2] = prls.grabImage(3, 1, 84, 84);
		PlayerRunLeft[3] = prls.grabImage(4, 1, 84, 84);
		PlayerRunLeft[4] = prls.grabImage(5, 1, 84, 84);
		PlayerRunLeft[5] = prls.grabImage(6, 1, 84, 84);
		
		PlayerIdleRight[0] = pirs.grabImage(1, 2, 47, 86);
		PlayerIdleRight[1] = pirs.grabImage(2, 2, 47, 86);
		PlayerIdleRight[2] = pirs.grabImage(3, 2, 47, 86);
		PlayerIdleRight[3] = pirs.grabImage(4, 2, 47, 86);
		PlayerIdleRight[4] = pirs.grabImage(5, 2, 47, 86);
		
		PlayerIdleLeft[0] = pils.grabImage(1, 2, 47, 86);
		PlayerIdleLeft[1] = pils.grabImage(2, 2, 47, 86);
		PlayerIdleLeft[2] = pils.grabImage(3, 2, 47, 86);
		PlayerIdleLeft[3] = pils.grabImage(4, 2, 47, 86);
		PlayerIdleLeft[4] = pils.grabImage(5, 2, 47, 86);
		
		PlayerJumpRight[0] = pjrs.grabImage(1, 1, 84, 100);
		PlayerJumpRight[1] = pjrs.grabImage(2, 1, 84, 100);
		PlayerJumpRight[2] = pjrs.grabImage(3, 1, 84, 100);
		PlayerJumpRight[3] = pjrs.grabImage(4, 1, 84, 100);
		PlayerJumpRight[4] = pjrs.grabImage(5, 1, 84, 100);
		PlayerJumpRight[5] = pjrs.grabImage(6, 1, 84, 100);
		PlayerJumpRight[6] = pjrs.grabImage(7, 1, 84, 100);
		PlayerJumpRight[7] = pjrs.grabImage(8, 1, 84, 100);
		PlayerJumpRight[8] = pjrs.grabImage(9, 1, 84, 100);
		
		PlayerJumpLeft[0] = pjls.grabImage(1, 2, 84, 100);
		PlayerJumpLeft[1] = pjls.grabImage(2, 2, 84, 100);
		PlayerJumpLeft[2] = pjls.grabImage(3, 2, 84, 100);
		PlayerJumpLeft[3] = pjls.grabImage(4, 2, 84, 100);
		PlayerJumpLeft[4] = pjls.grabImage(5, 2, 84, 100);
		PlayerJumpLeft[5] = pjls.grabImage(6, 2, 84, 100);
		PlayerJumpLeft[6] = pjls.grabImage(7, 2, 84, 100);
		PlayerJumpLeft[7] = pjls.grabImage(8, 2, 84, 100);
		PlayerJumpLeft[8] = pjls.grabImage(9, 2, 84, 100);
		
		PlayerSlashingRight[0] = psrs.grabImage(1, 1, 145, 145);
		PlayerSlashingRight[1] = psrs.grabImage(2, 1, 145, 145);
		PlayerSlashingRight[2] = psrs.grabImage(3, 1, 145, 145);
		PlayerSlashingRight[3] = psrs.grabImage(4, 1, 145, 145);
		PlayerSlashingRight[4] = psrs.grabImage(5, 1, 145, 145);
		
		PlayerSlashingLeft[0] = psls.grabImage(1, 1, 145, 145);
		PlayerSlashingLeft[1] = psls.grabImage(2, 1, 145, 145);
		PlayerSlashingLeft[2] = psls.grabImage(3, 1, 145, 145);
		PlayerSlashingLeft[3] = psls.grabImage(4, 1, 145, 145);
		PlayerSlashingLeft[4] = psls.grabImage(5, 1, 145, 145);
		
		PlayerSlashingRightPowerAura[0] = psrpas.grabImage(1, 1, 145, 145);
		PlayerSlashingRightPowerAura[1] = psrpas.grabImage(2, 1, 145, 145);
		PlayerSlashingRightPowerAura[2] = psrpas.grabImage(3, 1, 145, 145);
		PlayerSlashingRightPowerAura[3] = psrpas.grabImage(4, 1, 145, 145);
		PlayerSlashingRightPowerAura[4] = psrpas.grabImage(5, 1, 145, 145);
		
		PlayerSlashingLeftPowerAura[0] = pslpas.grabImage(1, 1, 145, 145);
		PlayerSlashingLeftPowerAura[1] = pslpas.grabImage(2, 1, 145, 145);
		PlayerSlashingLeftPowerAura[2] = pslpas.grabImage(3, 1, 145, 145);
		PlayerSlashingLeftPowerAura[3] = pslpas.grabImage(4, 1, 145, 145);
		PlayerSlashingLeftPowerAura[4] = pslpas.grabImage(5, 1, 145, 145);
		
		PlayerFaintRight[0] = pfrs.grabImage(1, 1, 100, 100);
		PlayerFaintRight[1] = pfrs.grabImage(2, 1, 100, 100);
		PlayerFaintRight[2] = pfrs.grabImage(3, 1, 100, 100);
		PlayerFaintRight[3] = pfrs.grabImage(4, 1, 100, 100);
		PlayerFaintRight[4] = pfrs.grabImage(5, 1, 100, 100);
		PlayerFaintRight[5] = pfrs.grabImage(6, 1, 100, 100);
		PlayerFaintRight[6] = pfrs.grabImage(7, 1, 100, 100);
		PlayerFaintRight[7] = pfrs.grabImage(8, 1, 100, 100);
		PlayerFaintRight[8] = pfrs.grabImage(9, 1, 100, 100);
		
		PlayerFaintLeft[0] = pfls.grabImage(1, 1, 100, 100);
		PlayerFaintLeft[1] = pfls.grabImage(2, 1, 100, 100);
		PlayerFaintLeft[2] = pfls.grabImage(3, 1, 100, 100);
		PlayerFaintLeft[3] = pfls.grabImage(4, 1, 100, 100);
		PlayerFaintLeft[4] = pfls.grabImage(5, 1, 100, 100);
		PlayerFaintLeft[5] = pfls.grabImage(6, 1, 100, 100);
		PlayerFaintLeft[6] = pfls.grabImage(7, 1, 100, 100);
		PlayerFaintLeft[7] = pfls.grabImage(8, 1, 100, 100);
		PlayerFaintLeft[8] = pfls.grabImage(9, 1, 100, 100);
		
		//PlayerAbility slash can be done icon
		PlayerAbility[0] = pas.grabImage(1, 1, 64, 64);
		PlayerAbility[1] = pas.grabImage(2, 1, 64, 64);
		//PlayerAbility slash cant be done icon
		PlayerAbility[2] = pas.grabImage(1, 2, 64, 64);
		//PlayerAbility charge can be done icon
		PlayerAbility[3] = pas.grabImage(1, 3, 64, 64);
		PlayerAbility[4] = pas.grabImage(2, 3, 64, 64);
		//PlayerAbility charge cant be done icon
		PlayerAbility[5] = pas.grabImage(1, 4, 64, 64);
		//PlayerAbility charge not enough stamina
		PlayerAbility[6] = pas.grabImage(1, 5, 64, 64);
		//PlayerAbility interrupt can be done icon
		PlayerAbility[7] = pas.grabImage(1, 6, 64, 64);
		//playerAbility using interrupt
		PlayerAbility[8] = pas.grabImage(2, 6, 64, 64);
		//player Ability interrupt cant be done
		PlayerAbility[9] = pas.grabImage(1, 7, 64, 64);
		//playerAbility interrupt not enough stamina
		PlayerAbility[10] = pas.grabImage(1, 8, 64, 64);
		//interrupt animation
		PlayerAbility[11] = pas.grabImage(1, 9, 64, 64);
		PlayerAbility[12] = pas.grabImage(1, 10, 64, 64);
	
		//PlayerCharge Right
		PlayerChargeRight[0] = pcrs.grabImage(1, 1, 250, 250);
		PlayerChargeRight[1] = pcrs.grabImage(2, 1, 250, 250);
		PlayerChargeRight[2] = pcrs.grabImage(3, 1, 250, 250);
		PlayerChargeRight[3] = pcrs.grabImage(4, 1, 250, 250);
		PlayerChargeRight[4] = pcrs.grabImage(5, 1, 250, 250);
		
		//PlayerCharge Left
		PlayerChargeLeft[0] = pcls.grabImage(1, 1, 250, 250);
		PlayerChargeLeft[1] = pcls.grabImage(2, 1, 250, 250);
		PlayerChargeLeft[2] = pcls.grabImage(3, 1, 250, 250);
		PlayerChargeLeft[3] = pcls.grabImage(4, 1, 250, 250);
		PlayerChargeLeft[4] = pcls.grabImage(5, 1, 250, 250);
		
		//Player Death 
		PlayerChargeLeft[5] = pcls.grabImage(1, 2, 250, 250);
		PlayerChargeLeft[6] = pcls.grabImage(2, 2, 250, 250);
		PlayerChargeLeft[7] = pcls.grabImage(3, 2, 250, 250);
		PlayerChargeLeft[8] = pcls.grabImage(4, 2, 250, 250);
		PlayerChargeLeft[9] = pcls.grabImage(5, 2, 250, 250);
		
		//PlayerCharge Right Power Aura
		PlayerChargeRightPowerAura[0] = pcrpas.grabImage(1, 1, 250, 250);
		PlayerChargeRightPowerAura[1] = pcrpas.grabImage(2, 1, 250, 250);
		PlayerChargeRightPowerAura[2] = pcrpas.grabImage(3, 1, 250, 250);
		PlayerChargeRightPowerAura[3] = pcrpas.grabImage(4, 1, 250, 250);
		PlayerChargeRightPowerAura[4] = pcrpas.grabImage(5, 1, 250, 250);
		
		//PlayerCharge Left Power Aura
		PlayerChargeLeftPowerAura[0] = pclpas.grabImage(1, 1, 250, 250);
		PlayerChargeLeftPowerAura[1] = pclpas.grabImage(2, 1, 250, 250);
		PlayerChargeLeftPowerAura[2] = pclpas.grabImage(3, 1, 250, 250);
		PlayerChargeLeftPowerAura[3] = pclpas.grabImage(4, 1, 250, 250);
		PlayerChargeLeftPowerAura[4] = pclpas.grabImage(5, 1, 250, 250);
		
		
		//skelton right idle
		SkeletonRight[0] = srs.grabImage(1, 1, 100, 100);
		SkeletonRight[1] = srs.grabImage(2, 1, 100, 100);
		SkeletonRight[2] = srs.grabImage(3, 1, 100, 100);
		SkeletonRight[3] = srs.grabImage(4, 1, 100, 100);
		SkeletonRight[4] = srs.grabImage(5, 1, 100, 100);
		SkeletonRight[5] = srs.grabImage(6, 1, 100, 100);
		
		//skeleton right run
		SkeletonRight[6] = srs.grabImage(1, 2, 100, 100);
		SkeletonRight[7] = srs.grabImage(2, 2, 100, 100);
		SkeletonRight[8] = srs.grabImage(3, 2, 100, 100);
		SkeletonRight[9] = srs.grabImage(4, 2, 100, 100);
		SkeletonRight[10] = srs.grabImage(5, 2, 100, 100);
		SkeletonRight[11] = srs.grabImage(6, 2, 100, 100);
		
		//skeleton right attack
		SkeletonRight[12] = srs.grabImage(2, 3, 100, 100);
		SkeletonRight[13] = srs.grabImage(3, 3, 100, 100);
		SkeletonRight[14] = srs.grabImage(4, 3, 100, 100);
		SkeletonRight[15] = srs.grabImage(5, 3, 100, 100);
		SkeletonRight[16] = srs.grabImage(6, 3, 100, 100);
		
		//skeleton death
		SkeletonRight[17] = srs.grabImage(1, 4, 100, 100);
		SkeletonRight[18] = srs.grabImage(2, 4, 100, 100);
		SkeletonRight[19] = srs.grabImage(3, 4, 100, 100);
		SkeletonRight[20] = srs.grabImage(4, 4, 100, 100);
		SkeletonRight[21] = srs.grabImage(5, 4, 100, 100);
		
		
		//skelton left idle
		SkeletonLeft[0] = sls.grabImage(1, 1, 100, 100);
		SkeletonLeft[1] = sls.grabImage(2, 1, 100, 100);
		SkeletonLeft[2] = sls.grabImage(3, 1, 100, 100);
		SkeletonLeft[3] = sls.grabImage(4, 1, 100, 100);
		SkeletonLeft[4] = sls.grabImage(5, 1, 100, 100);
		SkeletonLeft[5] = sls.grabImage(6, 1, 100, 100);
		
		//skeleton left run
		SkeletonLeft[6] = sls.grabImage(1, 2, 100, 100);
		SkeletonLeft[7] = sls.grabImage(2, 2, 100, 100);
		SkeletonLeft[8] = sls.grabImage(3, 2, 100, 100);
		SkeletonLeft[9] = sls.grabImage(4, 2, 100, 100);
		SkeletonLeft[10] = sls.grabImage(5, 2, 100, 100);
		SkeletonLeft[11] = sls.grabImage(6, 2, 100, 100);
		
		//skeleton left attack
		SkeletonLeft[12] = sls.grabImage(1, 3, 100, 100);
		SkeletonLeft[13] = sls.grabImage(2, 3, 100, 100);
		SkeletonLeft[14] = sls.grabImage(3, 3, 100, 100);
		SkeletonLeft[15] = sls.grabImage(4, 3, 100, 100);
		SkeletonLeft[16] = sls.grabImage(5, 3, 100, 100);
		
		//Dark Mage right run
		DarkMageRight[0] = dmrs.grabImage(1, 1, 100, 100);
		DarkMageRight[1] = dmrs.grabImage(2, 1, 100, 100);
		DarkMageRight[2] = dmrs.grabImage(3, 1, 100, 100);
		DarkMageRight[3] = dmrs.grabImage(4, 1, 100, 100);
		DarkMageRight[4] = dmrs.grabImage(5, 1, 100, 100);
		DarkMageRight[5] = dmrs.grabImage(6, 1, 100, 100);
		DarkMageRight[6] = dmrs.grabImage(7, 1, 100, 100);
		
		//Dark Mage right attack
		DarkMageRight[7] = dmrs.grabImage(1, 2, 100, 100);
		DarkMageRight[8] = dmrs.grabImage(2, 2, 100, 100);
		DarkMageRight[9] = dmrs.grabImage(3, 2, 100, 100);
		DarkMageRight[10] = dmrs.grabImage(4, 2, 100, 100);
		DarkMageRight[11] = dmrs.grabImage(5, 2, 100, 100);
		
		//Dark Mage left run
		DarkMageLeft[0] = dmls.grabImage(1, 1, 100, 100);
		DarkMageLeft[1] = dmls.grabImage(2, 1, 100, 100);
		DarkMageLeft[2] = dmls.grabImage(3, 1, 100, 100);
		DarkMageLeft[3] = dmls.grabImage(4, 1, 100, 100);
		DarkMageLeft[4] = dmls.grabImage(5, 1, 100, 100);
		DarkMageLeft[5] = dmls.grabImage(6, 1, 100, 100);
		DarkMageLeft[6] = dmls.grabImage(7, 1, 100, 100);
		
		//Dark Mage left attack
		DarkMageLeft[7] = dmls.grabImage(3, 2, 100, 100);
		DarkMageLeft[8] = dmls.grabImage(4, 2, 100, 100);
		DarkMageLeft[9] = dmls.grabImage(5, 2, 100, 100);
		DarkMageLeft[10] = dmls.grabImage(6, 2, 100, 100);
		DarkMageLeft[11] = dmls.grabImage(7, 2, 100, 100);
		
		//Dark Mage left death
		DarkMageLeft[12] = dmls.grabImage(2, 2, 100, 140);
		//Dark Mage right death
		DarkMageRight[12] = dmrs.grabImage(6, 2, 100, 140);
		
		//ShadowBall
		ShadowBall[0] = sbs.grabImage(1, 1, 63, 63);
		ShadowBall[1] = sbs.grabImage(2, 1, 63, 63);
		ShadowBall[2] = sbs.grabImage(3, 1, 63, 63);
		ShadowBall[3] = sbs.grabImage(4, 1, 63, 63);
		ShadowBall[4] = sbs.grabImage(5, 1, 63, 63);
		ShadowBall[5] = sbs.grabImage(6, 1, 63, 63);
		ShadowBall[6] = sbs.grabImage(7, 1, 63, 63);
		ShadowBall[7] = sbs.grabImage(8, 1, 63, 63);
		ShadowBall[8] = sbs.grabImage(1, 2, 63, 63);
		ShadowBall[9] = sbs.grabImage(2, 2, 63, 63);
		ShadowBall[10] = sbs.grabImage(3, 2, 63, 63);
		ShadowBall[11] = sbs.grabImage(4, 2, 63, 63);
		ShadowBall[12] = sbs.grabImage(5, 2, 63, 63);
		ShadowBall[13] = sbs.grabImage(6, 2, 63, 63);
		ShadowBall[14] = sbs.grabImage(7, 2, 63, 63);
		ShadowBall[15] = sbs.grabImage(8, 2, 63, 63);
		ShadowBall[16] = sbs.grabImage(1, 3, 63, 63);
		ShadowBall[17] = sbs.grabImage(2, 3, 63, 63);
		ShadowBall[18] = sbs.grabImage(3, 3, 63, 63);
	
		//Stamina Potion 
		Potions[0] = ps.grabImage(1, 1, 211, 211);
		//Health Potion
		Potions[1] = ps.grabImage(2, 1, 211, 211);
		//Strenght Potion
		Potions[2] = ps.grabImage(3, 1, 211, 211);
		
		//Stamina Potionblack
		Potions[3] = ps.grabImage(1, 2, 211, 211);
		//Health Potionblack
		Potions[4] = ps.grabImage(2, 2, 211, 211);
		//Strenght Potionblack
		Potions[5] = ps.grabImage(3, 2, 211, 211);
		
		//Useing Stamina Potion
		Potions[6] = ps.grabImage(1, 3, 211, 211);
		//Useing Health Potion
		Potions[7] = ps.grabImage(2, 3, 211, 211);
		//Useing Strenght Potion
		Potions[8] = ps.grabImage(3, 3, 211, 211);
		
		//Level up
		LevelUp[0] = lus.grabImage(3, 1, 225, 350);
		LevelUp[1] = lus.grabImage(2, 1, 225, 350);
		LevelUp[2] = lus.grabImage(1, 1, 225, 350);
		
		//McGregory Idle Right
		McGregoryRight[0] = mgrs.grabImage(1, 1, 60, 73);
		McGregoryRight[1] = mgrs.grabImage(2, 1, 60, 73);
		McGregoryRight[2] = mgrs.grabImage(3, 1, 60, 73);
		McGregoryRight[3] = mgrs.grabImage(4, 1, 60, 73);
		McGregoryRight[4] = mgrs.grabImage(5, 1, 60, 73);
		McGregoryRight[5] = mgrs.grabImage(6, 1, 60, 73);
		McGregoryRight[6] = mgrs.grabImage(7, 1, 60, 73);
		
		//McGregory Walk Right
		McGregoryRight[7] = mgrs.grabImage(1, 2, 66, 80);
		McGregoryRight[8] = mgrs.grabImage(2, 2, 66, 80);
		McGregoryRight[9] = mgrs.grabImage(3, 2, 66, 80);
		McGregoryRight[10] = mgrs.grabImage(4, 2, 66, 80);
		McGregoryRight[11] = mgrs.grabImage(5, 2, 66, 80);
		McGregoryRight[12] = mgrs.grabImage(6, 2, 66, 80);
		McGregoryRight[13] = mgrs.grabImage(7, 2, 66, 80);
		McGregoryRight[14] = mgrs.grabImage(8, 2, 66, 80);
		McGregoryRight[15] = mgrs.grabImage(9, 2, 66, 80);
		McGregoryRight[16] = mgrs.grabImage(10, 2, 66, 80);
		
		//McGregory Attacking Right
		McGregoryRight[17] = mgrs.grabImage(1, 3, 66, 80);
		McGregoryRight[18] = mgrs.grabImage(2, 3, 66, 80);
		McGregoryRight[19] = mgrs.grabImage(3, 3, 66, 80);
		McGregoryRight[20] = mgrs.grabImage(4, 3, 66, 80);
		McGregoryRight[21] = mgrs.grabImage(5, 3, 66, 80);
		
		//McGregory Death right
		McGregoryRight[22] = mgrs.grabImage(9, 4, 100, 80);
		McGregoryRight[23] = mgrs.grabImage(8, 4, 100, 80);
		McGregoryRight[24] = mgrs.grabImage(7, 4, 100, 80);
		McGregoryRight[25] = mgrs.grabImage(6, 4, 100, 80);
		McGregoryRight[26] = mgrs.grabImage(5, 4, 100, 80);
		McGregoryRight[27] = mgrs.grabImage(4, 4, 100, 80);
		McGregoryRight[28] = mgrs.grabImage(3, 4, 100, 80);
		McGregoryRight[29] = mgrs.grabImage(2, 4, 100, 80);
		McGregoryRight[30] = mgrs.grabImage(1, 4, 100, 80);
		
		//McGregory Idle Left
		McGregoryLeft[0] = mgls.grabImage(7, 1, 60, 73);
		McGregoryLeft[1] = mgls.grabImage(6, 1, 60, 73);
		McGregoryLeft[2] = mgls.grabImage(5, 1, 60, 73);
		McGregoryLeft[3] = mgls.grabImage(4, 1, 60, 73);
		McGregoryLeft[4] = mgls.grabImage(3, 1, 60, 73);
		McGregoryLeft[5] = mgls.grabImage(2, 1, 60, 73);
		McGregoryLeft[6] = mgls.grabImage(1, 1, 60, 73);
		
		//McGregory Walk Left
		McGregoryLeft[7] = mgls.grabImage(10, 2, 66, 80);
		McGregoryLeft[8] = mgls.grabImage(9, 2, 66, 80);
		McGregoryLeft[9] = mgls.grabImage(8, 2, 66, 80);
		McGregoryLeft[10] = mgls.grabImage(7, 2, 66, 80);
		McGregoryLeft[11] = mgls.grabImage(6, 2, 66, 80);
		McGregoryLeft[12] = mgls.grabImage(5, 2, 66, 80);
		McGregoryLeft[13] = mgls.grabImage(4, 2, 66, 80);
		McGregoryLeft[14] = mgls.grabImage(3, 2, 66, 80);
		McGregoryLeft[15] = mgls.grabImage(2, 2, 66, 80);
		McGregoryLeft[16] = mgls.grabImage(1, 2, 66, 80);
		
		//McGregory Attacking Left
		McGregoryLeft[17] = mgls.grabImage(5, 3, 66, 80);
		McGregoryLeft[18] = mgls.grabImage(4, 3, 66, 80);
		McGregoryLeft[19] = mgls.grabImage(3, 3, 66, 80);
		McGregoryLeft[20] = mgls.grabImage(2, 3, 66, 80);
		McGregoryLeft[21] = mgls.grabImage(1, 3, 66, 80);
		
		//McGregory Death left
		McGregoryLeft[22] = mgls.grabImage(1, 4, 100, 80);
		McGregoryLeft[23] = mgls.grabImage(2, 4, 100, 80);
		McGregoryLeft[24] = mgls.grabImage(3, 4, 100, 80);
		McGregoryLeft[25] = mgls.grabImage(4, 4, 100, 80);
		McGregoryLeft[26] = mgls.grabImage(5, 4, 100, 80);
		McGregoryLeft[27] = mgls.grabImage(6, 4, 100, 80);
		McGregoryLeft[28] = mgls.grabImage(7, 4, 100, 80);
		McGregoryLeft[29] = mgls.grabImage(8, 4, 100, 80);
		McGregoryLeft[30] = mgls.grabImage(9, 4, 100, 80);
		

		
		

	}
}
