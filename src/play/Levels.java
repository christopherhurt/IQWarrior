package play;

import java.awt.Color;
import java.awt.Font;
import main.Animation;
import main.Camera;
import main.Game;
import main.GameObject;
import main.Manager;
import main.Menu;
import main.MenuLabel;
import main.Sound;
import main.SoundBank;
import main.SpriteSheet;
import main.Texture;
import main.TexturedGameObject;

public class Levels {
    
    public static final float PLAY_FIELD_SIZE = 3.5f;
    private static boolean playedOnce = false;
    private static boolean lostOnce = false;
    
    public static void buildPlayLevel(Manager manager, Menu menu){
        // Resetting Scene
        manager.clearObjects();
        menu.clearAll();
        SoundBank.stopAll();
        
        Sound playMusic;
        Sound deoderantSound;
        Sound dumbellSound;
        Sound fedoraThrowSound;
        Sound frappeSound;
        Sound girlHitSound;
        Sound goldenFedoraSound;
        Sound hammerSound;
        Sound powerUpPickupSound;
        Sound vapeSound;
        
        // Sounds and Music
        if(!playedOnce){
            playMusic = new Sound("menuMusic", "sounds/playMusic.wav", true);
            deoderantSound = new Sound("deoderant", "sounds/deoderantSound.wav", false);
            dumbellSound = new Sound("dumbell", "sounds/dumbellSound.wav", false);
            fedoraThrowSound = new Sound("fedoraThrow", "sounds/fedoraThrowSound.wav", false);
            frappeSound = new Sound("frappe", "sounds/frappeSound.wav", false);
            girlHitSound = new Sound("girlHit", "sounds/girlHitSound.wav", false);
            goldenFedoraSound = new Sound("goldenFedora", "sounds/goldenFedoraSound.wav", false);
            hammerSound = new Sound("hammer", "sounds/hammerSound.wav", false);
            powerUpPickupSound = new Sound("powerUpPickup", "sounds/powerUpPickupSound.wav", false);
            vapeSound = new Sound("vape", "sounds/vapeSound.wav", false);
            playedOnce = true;
        }else{
            playMusic = SoundBank.getSound("menuMusic");
            deoderantSound = SoundBank.getSound("deoderant");
            dumbellSound = SoundBank.getSound("dumbell");
            fedoraThrowSound = SoundBank.getSound("fedoraThrow");
            frappeSound = SoundBank.getSound("frappe");
            girlHitSound = SoundBank.getSound("girlHit");
            goldenFedoraSound = SoundBank.getSound("goldenFedora");
            hammerSound = SoundBank.getSound("hammer");
            powerUpPickupSound = SoundBank.getSound("powerUpPickup");
            vapeSound = SoundBank.getSound("vape");
        }
        
        // Sprite Sheets
        SpriteSheet itemSprites = new SpriteSheet("textures/itemSprite.png", 8, 8);
        SpriteSheet frappeSprites = new SpriteSheet("textures/itemSprite.png", 4, 8);
        SpriteSheet cloudSheet = new SpriteSheet("textures/cloudSprite.png", 1, 1);
        SpriteSheet girlWalkingSheet = new SpriteSheet("textures/girlSprite.png", 8, 8);
        float spinDuration = 2;
        Animation blackFedoraAnimation = new Animation(spinDuration, itemSprites.getTexture(0, 0), itemSprites.getTexture(1, 0), itemSprites.getTexture(2, 0), itemSprites.getTexture(3, 0));
        Animation goldFedoraAnimation = new Animation(spinDuration, itemSprites.getTexture(0, 1), itemSprites.getTexture(1, 1), itemSprites.getTexture(2, 1), itemSprites.getTexture(3, 1));
        Texture cloudSprite = cloudSheet.getTexture(0, 0);
        Texture frappeSprite = itemSprites.getTexture(0, 6);
        Texture vapeSprite = itemSprites.getTexture(0, 5);
        Texture goldenFedoraSprite = itemSprites.getTexture(0, 1);
        Texture hammerSprite = itemSprites.getTexture(0, 2);
        Texture deoderantSprite = itemSprites.getTexture(0, 3);
        Texture dumbellSprite = itemSprites.getTexture(0, 4);
        Texture blankSprite = new Texture("textures/blank.png");
        SpriteSheet fatBoiSheet = new SpriteSheet("textures/fatBoiSprite.png", 8, 8);
        SpriteSheet fatBoiGoldSheet = new SpriteSheet("textures/fatBoiSpriteGold.png", 8, 8);
        SpriteSheet fatBoiDeathSheet = new SpriteSheet("textures/fatBoiSpriteDeath.png", 8, 8);
        Animation idleForward = new Animation(1, fatBoiSheet.getTexture(0, 0));
        Animation idleBackward = new Animation(1, fatBoiSheet.getTexture(0, 1));
        Animation idleRight = new Animation(1, fatBoiSheet.getTexture(0, 2));
        Animation idleLeft = new Animation(1, fatBoiSheet.getTexture(0, 3));
        Animation walkForward = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 0), fatBoiSheet.getTexture(1, 0), fatBoiSheet.getTexture(2, 0), fatBoiSheet.getTexture(3, 0), fatBoiSheet.getTexture(4, 0), fatBoiSheet.getTexture(5, 0), fatBoiSheet.getTexture(6, 0), fatBoiSheet.getTexture(7, 0));
        Animation walkBackward = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 1), fatBoiSheet.getTexture(1, 1), fatBoiSheet.getTexture(2, 1), fatBoiSheet.getTexture(3, 1), fatBoiSheet.getTexture(4, 1), fatBoiSheet.getTexture(5, 1), fatBoiSheet.getTexture(6, 1), fatBoiSheet.getTexture(7, 1));
        Animation walkRight = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 2), fatBoiSheet.getTexture(1, 2), fatBoiSheet.getTexture(2, 2), fatBoiSheet.getTexture(3, 2), fatBoiSheet.getTexture(4, 2), fatBoiSheet.getTexture(5, 2), fatBoiSheet.getTexture(6, 2), fatBoiSheet.getTexture(7, 2));
        Animation walkLeft = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 3), fatBoiSheet.getTexture(1, 3), fatBoiSheet.getTexture(2, 3), fatBoiSheet.getTexture(3, 3), fatBoiSheet.getTexture(4, 3), fatBoiSheet.getTexture(5, 3), fatBoiSheet.getTexture(6, 3), fatBoiSheet.getTexture(7, 3));
        Animation hatlessIdleForward = new Animation(1, fatBoiSheet.getTexture(0, 4));
        Animation hatlessIdleBackward = new Animation(1, fatBoiSheet.getTexture(0, 5));
        Animation hatlessIdleRight = new Animation(1, fatBoiSheet.getTexture(0, 6));
        Animation hatlessIdleLeft = new Animation(1, fatBoiSheet.getTexture(0, 7));
        Animation hatlessWalkForward = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 4), fatBoiSheet.getTexture(1, 4), fatBoiSheet.getTexture(2, 4), fatBoiSheet.getTexture(3, 4), fatBoiSheet.getTexture(4, 4), fatBoiSheet.getTexture(5, 4), fatBoiSheet.getTexture(6, 4), fatBoiSheet.getTexture(7, 4));
        Animation hatlessWalkBackward = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 5), fatBoiSheet.getTexture(1, 5), fatBoiSheet.getTexture(2, 5), fatBoiSheet.getTexture(3, 5), fatBoiSheet.getTexture(4, 5), fatBoiSheet.getTexture(5, 5), fatBoiSheet.getTexture(6, 5), fatBoiSheet.getTexture(7, 5));
        Animation hatlessWalkRight = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 6), fatBoiSheet.getTexture(1, 6), fatBoiSheet.getTexture(2, 6), fatBoiSheet.getTexture(3, 6), fatBoiSheet.getTexture(4, 6), fatBoiSheet.getTexture(5, 6), fatBoiSheet.getTexture(6, 6), fatBoiSheet.getTexture(7, 6));
        Animation hatlessWalkLeft = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiSheet.getTexture(0, 7), fatBoiSheet.getTexture(1, 7), fatBoiSheet.getTexture(2, 7), fatBoiSheet.getTexture(3, 7), fatBoiSheet.getTexture(4, 7), fatBoiSheet.getTexture(5, 7), fatBoiSheet.getTexture(6, 7), fatBoiSheet.getTexture(7, 7));
        Animation goldIdleForward = new Animation(1, fatBoiGoldSheet.getTexture(0, 0));
        Animation goldIdleBackward = new Animation(1, fatBoiGoldSheet.getTexture(0, 1));
        Animation goldIdleRight = new Animation(1, fatBoiGoldSheet.getTexture(0, 2));
        Animation goldIdleLeft = new Animation(1, fatBoiGoldSheet.getTexture(0, 3));
        Animation goldWalkForward = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiGoldSheet.getTexture(0, 0), fatBoiGoldSheet.getTexture(1, 0), fatBoiGoldSheet.getTexture(2, 0), fatBoiGoldSheet.getTexture(3, 0), fatBoiGoldSheet.getTexture(4, 0), fatBoiGoldSheet.getTexture(5, 0), fatBoiGoldSheet.getTexture(6, 0), fatBoiGoldSheet.getTexture(7, 0));
        Animation goldWalkBackward = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiGoldSheet.getTexture(0, 1), fatBoiGoldSheet.getTexture(1, 1), fatBoiGoldSheet.getTexture(2, 1), fatBoiGoldSheet.getTexture(3, 1), fatBoiGoldSheet.getTexture(4, 1), fatBoiGoldSheet.getTexture(5, 1), fatBoiGoldSheet.getTexture(6, 1), fatBoiGoldSheet.getTexture(7, 1));
        Animation goldWalkRight = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiGoldSheet.getTexture(0, 2), fatBoiGoldSheet.getTexture(1, 2), fatBoiGoldSheet.getTexture(2, 2), fatBoiGoldSheet.getTexture(3, 2), fatBoiGoldSheet.getTexture(4, 2), fatBoiGoldSheet.getTexture(5, 2), fatBoiGoldSheet.getTexture(6, 2), fatBoiGoldSheet.getTexture(7, 2));
        Animation goldWalkLeft = new Animation(Player.WALK_ANIMATION_SPEED, fatBoiGoldSheet.getTexture(0, 3), fatBoiGoldSheet.getTexture(1, 3), fatBoiGoldSheet.getTexture(2, 3), fatBoiGoldSheet.getTexture(3, 3), fatBoiGoldSheet.getTexture(4, 3), fatBoiGoldSheet.getTexture(5, 3), fatBoiGoldSheet.getTexture(6, 3), fatBoiGoldSheet.getTexture(7, 3));
        float tipDuration = 0.62f;
        Animation tip = new Animation(tipDuration, fatBoiGoldSheet.getTexture(0, 4), fatBoiGoldSheet.getTexture(1, 4), fatBoiGoldSheet.getTexture(2, 4), fatBoiGoldSheet.getTexture(3, 4), fatBoiGoldSheet.getTexture(4, 4), fatBoiGoldSheet.getTexture(5, 4), fatBoiGoldSheet.getTexture(6, 4), fatBoiGoldSheet.getTexture(7, 4));
        float deathDuration = 1f;
        Animation death = new Animation(deathDuration, fatBoiDeathSheet.getTexture(0, 1), fatBoiDeathSheet.getTexture(1, 1), fatBoiDeathSheet.getTexture(2, 1), fatBoiDeathSheet.getTexture(3, 1), fatBoiDeathSheet.getTexture(4, 1), fatBoiDeathSheet.getTexture(5, 1), fatBoiDeathSheet.getTexture(6, 1), fatBoiDeathSheet.getTexture(7, 1));
        Animation hatlessDeath = new Animation(deathDuration, fatBoiDeathSheet.getTexture(0, 0), fatBoiDeathSheet.getTexture(1, 0), fatBoiDeathSheet.getTexture(2, 0), fatBoiDeathSheet.getTexture(3, 0), fatBoiDeathSheet.getTexture(4, 0), fatBoiDeathSheet.getTexture(5, 0), fatBoiDeathSheet.getTexture(6, 0), fatBoiDeathSheet.getTexture(7, 0));
        Animation goldDeath = new Animation(deathDuration, fatBoiDeathSheet.getTexture(0, 2), fatBoiDeathSheet.getTexture(1, 2), fatBoiDeathSheet.getTexture(2, 2), fatBoiDeathSheet.getTexture(3, 2), fatBoiDeathSheet.getTexture(4, 2), fatBoiDeathSheet.getTexture(5, 2), fatBoiDeathSheet.getTexture(6, 2), fatBoiDeathSheet.getTexture(7, 2));
        
        // Camera
        float camOffset = -0.5f;
        Camera.setX(camOffset);
        Camera.setY(camOffset);
        
        // Background
        TexturedGameObject background = new TexturedGameObject(-PLAY_FIELD_SIZE / 2, -PLAY_FIELD_SIZE / 2, 0, 0, 0, 0, PLAY_FIELD_SIZE, PLAY_FIELD_SIZE, "background", new Texture("textures/background.png"));
        TexturedGameObject overlay = new TexturedGameObject(-PLAY_FIELD_SIZE / 2, -PLAY_FIELD_SIZE / 2, 0, 0, 0, 0, PLAY_FIELD_SIZE, PLAY_FIELD_SIZE, "overlay", new Texture("textures/backgroundOverlay.png"));
        manager.addObject(background);
        manager.addObject(overlay);
        
        // IQ Bar and Menu
        float displayX = 0.17f;
        float displayY = 0.935f;
        float displayWidth = 0.03f;
        float displayHeight = displayWidth * Runner.ASPECT_RATIO;
        TexturedGameObject powerUpDisplay = new TexturedGameObject(displayX, displayY, 0, 0, 0, 0, displayWidth, displayHeight, "powerUpDisplay", blankSprite);
        powerUpDisplay.setCameraAffected(false);
        manager.addObject(powerUpDisplay);
        MenuLabel timeAliveLabel = new MenuLabel("Time Alive : 00:00", "helvetica", Font.BOLD, 0.02f, Color.WHITE, 0.8f, 0.055f, "timeAliveLabel");
        MenuLabel iqLabel = new MenuLabel("IQ : 0", "helvetica", Font.BOLD, 0.03f, Color.WHITE, 0.7985f, 0.865f, "iqLabel");
        menu.addLabel(timeAliveLabel);
        menu.addLabel(iqLabel);
        MenuLabel currentPowerUp = new MenuLabel("Power-Up : NONE", "helvetica", Font.BOLD, 0.025f, Color.WHITE, 0.03f, 0.965f, "currentPowerUp");
        menu.addLabel(currentPowerUp);
        float barX = 0.8f;
        float barY = 0.9f;
        float barWidth = 0.15f;
        float barHeight = 7f / 37 * barWidth * Runner.ASPECT_RATIO;
        float levelDimensionAdjust = 0.001f;
        IQBar bar = new IQBar(barX, barY, 0, 0, 0, 0, barWidth, barHeight, "iqbar", new Texture("textures/iqbar.png"), manager, iqLabel);
        IQLevel level = new IQLevel(barX + 1f / 37 * barWidth, barY + 3f / 7 * barHeight, 0, 0, 0, 0, 35f / 37 * barWidth + levelDimensionAdjust, 1f / 7 * barHeight + levelDimensionAdjust, "iqlevel", Color.RED, bar);
        manager.addObject(bar);
        manager.addObject(level);
        
        // Player
        float playerWidth = Player.WIDTH;
        float playerHeight = Player.HEIGHT_RATIO * playerWidth * Runner.ASPECT_RATIO;
        Player player = new Player(0.5f - playerWidth / 2, 0.5f - playerHeight / 2, 0, 0, 0, 0, playerWidth, playerHeight, "player",
            playMusic, fedoraThrowSound, hammerSound, deoderantSound, dumbellSound, powerUpPickupSound, frappeSound, vapeSound, goldenFedoraSound, girlHitSound,
            idleForward, idleBackward, idleLeft, idleRight, walkForward, walkBackward, walkLeft, walkRight,
            hatlessIdleForward, hatlessIdleBackward, hatlessIdleLeft, hatlessIdleRight,
            hatlessWalkForward, hatlessWalkBackward, hatlessWalkLeft, hatlessWalkRight,
            goldIdleForward, goldIdleBackward, goldIdleLeft, goldIdleRight,
            goldWalkForward, goldWalkBackward, goldWalkLeft, goldWalkRight, tip,
            death, hatlessDeath, goldDeath, background, overlay,
            manager, bar, blackFedoraAnimation, goldFedoraAnimation, frappeSprites, cloudSprite);
        manager.addObject(player);
        
        // Level Controller
        float pauseGraphicWidth = 0.3f;
        float pauseGraphicHeight = 25f / 82 * pauseGraphicWidth * Runner.ASPECT_RATIO;
        float pauseGraphicX = 0.5f - pauseGraphicWidth / 2;
        float pauseGraphicY = 0.15f;
        GameObject pauseGraphic = new TexturedGameObject(pauseGraphicX, pauseGraphicY, 0, 0, 0, 0, pauseGraphicWidth, pauseGraphicHeight, "pauseGraphic", new Texture("textures/pause.png"));
        pauseGraphic.setCameraAffected(false);
        GameObject levelController = new PlayLevelController(0, 0, 0, 0, 0, 0, 0, 0, "controller", new Color(0, 0, 0), manager, player, bar, timeAliveLabel, currentPowerUp, pauseGraphic, powerUpDisplay, frappeSprite, vapeSprite, goldenFedoraSprite, hammerSprite, deoderantSprite, dumbellSprite, blankSprite, girlWalkingSheet);
        manager.addObject(levelController);
        
        Game.setCurrentManager("play");
        Game.setCurrentMenu("play");
        
        playMusic.play();
    }
    
    public static void lose(Player player, Animation animation, TexturedGameObject background, TexturedGameObject overlay){
        Camera.setVelX(0);
        Camera.setVelY(0);
        
        PlayLevelController controller = (PlayLevelController)Runner.playManager.getObjects("controller").get(0);
        
        Manager loseManager;
        Sound gameOverSound;
        if(!lostOnce){
            loseManager = new Manager("lose");
            new Menu("lose");
            gameOverSound = new Sound("gameOver", "sounds/gameOverSound.wav", false);
            lostOnce = true;
        }else{
            loseManager = Game.getManager("lose");
            gameOverSound = SoundBank.getSound("gameOver");
        }
        
        GameObject deadPlayer = new DeadPlayer(player.getX(), player.getY(), 0, 0, 0, 0, player.getWidth(), player.getHeight(), "deadPlayer", animation, loseManager, controller);
        loseManager.addObject(background);
        loseManager.addObject(deadPlayer);
        loseManager.addObject(overlay);
        
        Game.setCurrentManager("lose");
        Game.setCurrentMenu("lose");
        SoundBank.stopAll(); 
        
        gameOverSound.play();
    }
    
}
