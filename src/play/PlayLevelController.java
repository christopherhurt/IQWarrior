package play;

import java.awt.Color;
import java.awt.event.KeyEvent;
import main.ColoredGameObject;
import main.Game;
import main.GameObject;
import main.Input;
import main.Manager;
import main.MenuLabel;
import main.SpriteSheet;
import main.Texture;
import main.TexturedGameObject;

public class PlayLevelController extends ColoredGameObject {
    
    private static final float INITIAL_SPAWN_RATE = 0.33f;
    private static final float MIN_IQ_SPAWN_RATE_MULTIPLIER = 1;
    private static final float MAX_IQ_SPAWN_RATE_MULTIPLIER = 5;
    private static final float SPAWN_RATE_INCREASE = 0.0000003f;
    
    private static final float FRAPPE_SPAWN_CHANCE = 0.0005f;
    private static final float VAPE_SPAWN_CHANCE = 0.00025f;
    private static final float GOLDEN_FEDORA_SPAWN_CHANCE = 0.000125f;
    private static final float POWER_UP_WIDTH = 0.04f;
    private static final float POWER_UP_HEIGHT = POWER_UP_WIDTH * Runner.ASPECT_RATIO;
    private static final float MIN_SPAWN_X = -Levels.PLAY_FIELD_SIZE / 2 + 0.5f;
    private static final float MAX_SPAWN_X = Levels.PLAY_FIELD_SIZE / 2 - POWER_UP_WIDTH - 0.5f;
    private static final float MIN_SPAWN_Y = -Levels.PLAY_FIELD_SIZE / 2 + 0.5f;
    private static final float MAX_SPAWN_Y = Levels.PLAY_FIELD_SIZE / 2 - POWER_UP_HEIGHT - 0.5f;
    
    private static final float ITEM_WIDTH = 0.04f;
    private static final float ITEM_HEIGHT = ITEM_WIDTH * Runner.ASPECT_RATIO;
    private static final float MIN_ITEM_SPAWN_X = -Levels.PLAY_FIELD_SIZE / 2 + 0.5f;
    private static final float MAX_ITEM_SPAWN_X = Levels.PLAY_FIELD_SIZE / 2 - ITEM_WIDTH - 0.5f;
    private static final float MIN_ITEM_SPAWN_Y = -Levels.PLAY_FIELD_SIZE / 2 + 0.5f;
    private static final float MAX_ITEM_SPAWN_Y = Levels.PLAY_FIELD_SIZE / 2 - ITEM_HEIGHT - 0.5f;
    private static final float HAMMER_SPAWN_CHANCE = 0.00075f;
    private static final float DEODERANT_SPAWN_CHANCE = 0.000375f;
    private static final float DUMBELL_SPAWN_CHANCE = 0.0001875f;
    
    private float girlSpawnRate;
    private Manager manager;
    private Player player;
    private IQBar bar;
    private MenuLabel timeAliveLabel;
    private MenuLabel currentPowerUpLabel;
    private GameObject pauseGraphic;
    private TexturedGameObject powerUpDisplay;
    private int timeAlive;
    
    private Texture frappeSprite;
    private Texture vapeSprite;
    private Texture goldenFedoraSprite;
    private Texture hammerSprite;
    private Texture deoderantSprite;
    private Texture dumbellSprite;
    private Texture blankSprite;
    
    private Texture ascending;
    
    private Texture gU0;
    private Texture gU1;
    private Texture gU2;
    private Texture gU3;
    private Texture gU4;
    private Texture gU5;
    private Texture gU6;
    private Texture gU7;
    
    private Texture gD0;
    private Texture gD1;
    private Texture gD2;
    private Texture gD3;
    private Texture gD4;
    private Texture gD5;
    private Texture gD6;
    private Texture gD7;
    
    private Texture gL0;
    private Texture gL1;
    private Texture gL2;
    private Texture gL3;
    private Texture gL4;
    private Texture gL5;
    private Texture gL6;
    private Texture gL7;
    
    private Texture gR0;
    private Texture gR1;
    private Texture gR2;
    private Texture gR3;
    private Texture gR4;
    private Texture gR5;
    private Texture gR6;
    private Texture gR7;
    
    public PlayLevelController(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Color color, Manager manager, Player player, IQBar bar, MenuLabel timeAliveLabel, MenuLabel currentPowerUpLabel, GameObject pauseGraphic, TexturedGameObject powerUpDisplay, Texture frappeSprite, Texture vapeSprite, Texture goldenFedoraSprite, Texture hammerSprite, Texture deoderantSprite, Texture dumbellSprite, Texture blankSprite, SpriteSheet girlWalkingSheet){
        super(x, y, vX, vY, aX, aY, w, h, id, color);
        setUpdateWhilePaused(true);
        girlSpawnRate = INITIAL_SPAWN_RATE;
        this.manager = manager;
        this.bar = bar;
        this.timeAliveLabel = timeAliveLabel;
        this.currentPowerUpLabel = currentPowerUpLabel;
        this.pauseGraphic = pauseGraphic;
        this.powerUpDisplay = powerUpDisplay;
        timeAlive = 0;
        this.frappeSprite = frappeSprite;
        this.vapeSprite = vapeSprite;
        this.goldenFedoraSprite = goldenFedoraSprite;
        this.hammerSprite = hammerSprite;
        this.deoderantSprite = deoderantSprite;
        this.dumbellSprite = dumbellSprite;
        this.blankSprite = blankSprite;
        this.player = player;
        
        ascending = girlWalkingSheet.getTexture(0, 5);
        
        gU0 = girlWalkingSheet.getTexture(0, 1);
        gU1 = girlWalkingSheet.getTexture(1, 1);
        gU2 = girlWalkingSheet.getTexture(2, 1);
        gU3 = girlWalkingSheet.getTexture(3, 1);
        gU4 = girlWalkingSheet.getTexture(4, 1);
        gU5 = girlWalkingSheet.getTexture(5, 1);
        gU6 = girlWalkingSheet.getTexture(6, 1);
        gU7 = girlWalkingSheet.getTexture(7, 1);
        
        gD0 = girlWalkingSheet.getTexture(0, 0);
        gD1 = girlWalkingSheet.getTexture(1, 0);
        gD2 = girlWalkingSheet.getTexture(2, 0);
        gD3 = girlWalkingSheet.getTexture(3, 0);
        gD4 = girlWalkingSheet.getTexture(4, 0);
        gD5 = girlWalkingSheet.getTexture(5, 0);
        gD6 = girlWalkingSheet.getTexture(6, 0);
        gD7 = girlWalkingSheet.getTexture(7, 0);
        
        gL0 = girlWalkingSheet.getTexture(0, 3);
        gL1 = girlWalkingSheet.getTexture(1, 3);
        gL2 = girlWalkingSheet.getTexture(2, 3);
        gL3 = girlWalkingSheet.getTexture(3, 3);
        gL4 = girlWalkingSheet.getTexture(4, 3);
        gL5 = girlWalkingSheet.getTexture(5, 3);
        gL6 = girlWalkingSheet.getTexture(6, 3);
        gL7 = girlWalkingSheet.getTexture(7, 3);
        
        gR0 = girlWalkingSheet.getTexture(0, 2);
        gR1 = girlWalkingSheet.getTexture(1, 2);
        gR2 = girlWalkingSheet.getTexture(2, 2);
        gR3 = girlWalkingSheet.getTexture(3, 2);
        gR4 = girlWalkingSheet.getTexture(4, 2);
        gR5 = girlWalkingSheet.getTexture(5, 2);
        gR6 = girlWalkingSheet.getTexture(6, 2);
        gR7 = girlWalkingSheet.getTexture(7, 2);
    }
    
    @Override
    protected void update(){
        
        // Pausing
        
        if(Input.isKeyPressed(KeyEvent.VK_ESCAPE)){
            if(Game.isPaused()){
                manager.removeObject(pauseGraphic);
                Game.setPaused(false);
            }else{
                Game.setPaused(true);
                manager.addObject(pauseGraphic);
            }
        }
        
        if(!Game.isPaused()){
            
            // Time Alive
            
            timeAlive++;
            int secondsAlive = timeAlive / Runner.FPS;
            int minutes = secondsAlive / 60;
            int seconds = secondsAlive % 60;
            String formattedTime = (minutes < 10 ? "0" : "") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds;
            timeAliveLabel.setText("Time Alive : " + formattedTime);
            
            // Power-Up Display Bar
            
            int currentPowerUp = ((Player)manager.getObjects("player").get(0)).getCurrentPowerUp();
            
            if(currentPowerUp == Player.NONE){
                currentPowerUpLabel.setText("Power-Up : NONE");
                powerUpDisplay.setTexture(blankSprite);
            }else{
                currentPowerUpLabel.setText("Power-Up :");
                
                if(currentPowerUp == Player.FRAPPE){
                    powerUpDisplay.setTexture(frappeSprite);
                }else if(currentPowerUp == Player.VAPE){
                    powerUpDisplay.setTexture(vapeSprite);
                }else{
                    powerUpDisplay.setTexture(goldenFedoraSprite);
                }
            }
            
            manager.readdObjects("powerUpDisplay");
            
            // Power-Up Spawning
            
            if(Math.random() < FRAPPE_SPAWN_CHANCE){
                float x = generateRandomInterpolation(MIN_SPAWN_X, MAX_SPAWN_X);
                float y = generateRandomInterpolation(MIN_SPAWN_Y, MAX_SPAWN_Y);
                GameObject powerUp = new TexturedGameObject(x, y, 0, 0, 0, 0, POWER_UP_WIDTH, POWER_UP_HEIGHT, "frappe", frappeSprite);
                manager.addObject(powerUp);
            }
            if(Math.random() < VAPE_SPAWN_CHANCE){
                float x = generateRandomInterpolation(MIN_SPAWN_X, MAX_SPAWN_X);
                float y = generateRandomInterpolation(MIN_SPAWN_Y, MAX_SPAWN_Y);
                GameObject powerUp = new TexturedGameObject(x, y, 0, 0, 0, 0, POWER_UP_WIDTH, POWER_UP_HEIGHT, "vape", vapeSprite);
                manager.addObject(powerUp);
            }
            if(Math.random() < GOLDEN_FEDORA_SPAWN_CHANCE){
                float x = generateRandomInterpolation(MIN_SPAWN_X, MAX_SPAWN_X);
                float y = generateRandomInterpolation(MIN_SPAWN_Y, MAX_SPAWN_Y);
                GameObject powerUp = new TexturedGameObject(x, y, 0, 0, 0, 0, POWER_UP_WIDTH, POWER_UP_HEIGHT, "goldenFedora", goldenFedoraSprite);
                manager.addObject(powerUp);
            }
            
            // IQ Item Spawning
            
            if(Math.random() < HAMMER_SPAWN_CHANCE){
                float x = generateRandomInterpolation(MIN_ITEM_SPAWN_X, MAX_ITEM_SPAWN_X);
                float y = generateRandomInterpolation(MIN_ITEM_SPAWN_Y, MAX_ITEM_SPAWN_Y);
                GameObject item = new TexturedGameObject(x, y, 0, 0, 0, 0, ITEM_WIDTH, ITEM_HEIGHT, "hammer", hammerSprite);
                manager.addObject(item);
            }
            if(Math.random() < DEODERANT_SPAWN_CHANCE){
                float x = generateRandomInterpolation(MIN_ITEM_SPAWN_X, MAX_ITEM_SPAWN_X);
                float y = generateRandomInterpolation(MIN_ITEM_SPAWN_Y, MAX_ITEM_SPAWN_Y);
                GameObject item = new TexturedGameObject(x, y, 0, 0, 0, 0, ITEM_WIDTH, ITEM_HEIGHT, "deoderant", deoderantSprite);
                manager.addObject(item);
            }
            if(Math.random() < DUMBELL_SPAWN_CHANCE){
                float x = generateRandomInterpolation(MIN_ITEM_SPAWN_X, MAX_ITEM_SPAWN_X);
                float y = generateRandomInterpolation(MIN_ITEM_SPAWN_Y, MAX_ITEM_SPAWN_Y);
                GameObject item = new TexturedGameObject(x, y, 0, 0, 0, 0, ITEM_WIDTH, ITEM_HEIGHT, "dumbell", dumbellSprite);
                manager.addObject(item);
            }
            
            // Girl Spawning
            
            int iq = bar.getIQ();
            float normedIQ = (float)(iq - IQBar.MIN_IQ) / (IQBar.MAX_IQ - IQBar.MIN_IQ);
            float spawnRateMultiplier = normedIQ * (MAX_IQ_SPAWN_RATE_MULTIPLIER - MIN_IQ_SPAWN_RATE_MULTIPLIER) + MIN_IQ_SPAWN_RATE_MULTIPLIER;
            float spawnChance = girlSpawnRate * spawnRateMultiplier / Runner.FPS;
            
            if(Math.random() < spawnChance){
                float girlX;
                float girlY;
                float girlWidth = Girl.WIDTH;
                float girlHeight = Girl.HEIGHT_RATIO * girlWidth * Runner.ASPECT_RATIO;
                
                int side = (int)(Math.random() * 4);
                float inwardDistance = (float)(Math.random() * Levels.PLAY_FIELD_SIZE);
                float halfFieldSize = Levels.PLAY_FIELD_SIZE / 2;
                
                if(side == 0){
                    float topLeftX = -halfFieldSize - girlWidth / 2;
                    float topLeftY = -halfFieldSize - girlHeight;
                    girlX = topLeftX + inwardDistance;
                    girlY = topLeftY;
                }else if(side == 1){
                    float bottomLeftX = -halfFieldSize - girlWidth;
                    float bottomLeftY = halfFieldSize - girlHeight / 2;
                    girlX = bottomLeftX;
                    girlY = bottomLeftY - inwardDistance;
                }else if(side == 2){
                    float topRightX = halfFieldSize;
                    float topRightY = -halfFieldSize - girlHeight / 2;
                    girlX = topRightX;
                    girlY = topRightY + inwardDistance;
                }else{
                    float bottomRightX = halfFieldSize - girlWidth / 2;
                    float bottomRightY = halfFieldSize;
                    girlX = bottomRightX - inwardDistance;
                    girlY = bottomRightY;
                }
                
                Girl girl = new Girl(girlX, girlY, 0, 0, 0, 0, girlWidth, girlHeight, "girl", manager, player, bar, ascending,
                    gU0, gU1, gU2, gU3, gU4, gU5, gU6, gU7,
                    gD0, gD1, gD2, gD3, gD4, gD5, gD6, gD7,
                    gL0, gL1, gL2, gL3, gL4, gL5, gL6, gL7,
                    gR0, gR1, gR2, gR3, gR4, gR5, gR6, gR7);
                manager.addObject(girl);
                manager.readdObjects("overlay");
            }
            
            girlSpawnRate += SPAWN_RATE_INCREASE;
        }
    }
    
    private static float generateRandomInterpolation(float min, float max){
        return (float)(Math.random() * (max - min) + min);
    }
    
    public MenuLabel getTimeAliveLabel(){
        return timeAliveLabel;
    }
    
}
