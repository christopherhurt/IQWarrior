package play;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import main.AnimatedGameObject;
import main.Animation;
import main.Camera;
import main.Game;
import main.GameObject;
import main.Input;
import main.Manager;
import main.Physics;
import main.Sound;
import main.SpriteSheet;
import main.Texture;
import main.TexturedGameObject;

public class Player extends AnimatedGameObject {
    
    public static final float WIDTH = 0.075f;
    public static final float HEIGHT_RATIO = 1f;
    
    public static final int NONE = 0;
    public static final int FRAPPE = 1;
    public static final int VAPE = 2;
    public static final int FEDORA = 3;
    
    public static final float WALK_ANIMATION_SPEED = 0.3f;
    
    private static final float SPEED = 0.01f;
    
    private static final int HAMMER_REDUCTION = 50;
    private static final int DEODERANT_REDUCTION = 100;
    private static final int DUMBELL_REDUCTION = 150;
    
    private IQBar bar;
    private Manager manager;
    private boolean fedoraThrown;
    private int currentPowerUp;
    private boolean tipping;
    private boolean goldenFedoraEquipped;
    private Fedora fedora;
    
    private Animation blackFedoraAnimation;
    private Animation goldenFedoraAnimation;
    private SpriteSheet frappeSprites;
    private Texture cloudSprite;
    
    private Sound playMusic;
    private Sound fedoraThrowSound;
    private Sound hammerSound;
    private Sound deoderantSound;
    private Sound dumbellSound;
    private Sound powerUpPickupSound;
    private Sound frappeSound;
    private Sound vapeSound;
    private Sound goldenFedoraSound;
    private Sound girlHitSound;
    
    private Animation idleForward;
    private Animation idleBackward;
    private Animation idleLeft;
    private Animation idleRight;
    
    private Animation walkForward;
    private Animation walkBackward;
    private Animation walkLeft;
    private Animation walkRight;
    
    private Animation hatlessIdleForward;
    private Animation hatlessIdleBackward;
    private Animation hatlessIdleLeft;
    private Animation hatlessIdleRight;
    
    private Animation hatlessWalkForward;
    private Animation hatlessWalkBackward;
    private Animation hatlessWalkLeft;
    private Animation hatlessWalkRight;
    
    private Animation goldIdleForward;
    private Animation goldIdleBackward;
    private Animation goldIdleLeft;
    private Animation goldIdleRight;
    
    private Animation goldWalkForward;
    private Animation goldWalkBackward;
    private Animation goldWalkLeft;
    private Animation goldWalkRight;
    
    private Animation tip;
    
    private Animation death;
    private Animation hatlessDeath;
    private Animation goldDeath;
    
    private TexturedGameObject background;
    private TexturedGameObject overlay;
    
    private int direction;
    
    public Player(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id,
        Sound playMusic, Sound fedoraThrowSound, Sound hammerSound, Sound deoderantSound, Sound dumbellSound, Sound powerUpPickupSound, Sound frappeSound, Sound vapeSound, Sound goldenFedoraSound, Sound girlHitSound,
        Animation idleForward, Animation idleBackward, Animation idleLeft, Animation idleRight, Animation walkForward, Animation walkBackward, Animation walkLeft, Animation walkRight,
        Animation hatlessIdleForward, Animation hatlessIdleBackward, Animation hatlessIdleLeft, Animation hatlessIdleRight,
        Animation hatlessWalkForward, Animation hatlessWalkBackward, Animation hatlessWalkLeft, Animation hatlessWalkRight,
        Animation goldIdleForward, Animation goldIdleBackward, Animation goldIdleLeft, Animation goldIdleRight,
        Animation goldWalkForward, Animation goldWalkBackward, Animation goldWalkLeft, Animation goldWalkRight, Animation tip,
        Animation death, Animation hatlessDeath, Animation goldDeath, TexturedGameObject background, TexturedGameObject overlay,
        Manager manager, IQBar bar, Animation blackFedoraAnimation, Animation goldenFedoraAnimation, SpriteSheet frappeSprites, Texture cloudSprite) {
        super(x, y, vX, vY, aX, aY, w, h, id, idleForward);
        setCameraAffected(false);
        setBoundsSize(0.6f);
        this.manager = manager;
        this.bar = bar;
        fedoraThrown = false;
        currentPowerUp = NONE;
        tipping = false;
        goldenFedoraEquipped = false;
        fedora = null;
        this.blackFedoraAnimation = blackFedoraAnimation;
        this.goldenFedoraAnimation = goldenFedoraAnimation;
        this.frappeSprites = frappeSprites;
        this.cloudSprite = cloudSprite;
        
        this.playMusic = playMusic;
        this.fedoraThrowSound = fedoraThrowSound;
        this.hammerSound = hammerSound;
        this.deoderantSound = deoderantSound;
        this.dumbellSound = dumbellSound;
        this.powerUpPickupSound = powerUpPickupSound;
        this.frappeSound = frappeSound;
        this.vapeSound = vapeSound;
        this.goldenFedoraSound = goldenFedoraSound;
        this.girlHitSound = girlHitSound;
        
        this.idleForward = idleForward;
        this.idleBackward = idleBackward;
        this.idleLeft = idleLeft;
        this.idleRight = idleRight;
        
        this.walkForward = walkForward;
        this.walkBackward = walkBackward;
        this.walkLeft = walkLeft;
        this.walkRight = walkRight;
        
        this.hatlessIdleForward = hatlessIdleForward;
        this.hatlessIdleBackward = hatlessIdleBackward;
        this.hatlessIdleLeft = hatlessIdleLeft;
        this.hatlessIdleRight = hatlessIdleRight;
        
        this.hatlessWalkForward = hatlessWalkForward;
        this.hatlessWalkBackward = hatlessWalkBackward;
        this.hatlessWalkLeft = hatlessWalkLeft;
        this.hatlessWalkRight = hatlessWalkRight;
        
        this.goldIdleForward = goldIdleForward;
        this.goldIdleBackward = goldIdleBackward;
        this.goldIdleLeft = goldIdleLeft;
        this.goldIdleRight = goldIdleRight;
        
        this.goldWalkForward = goldWalkForward;
        this.goldWalkBackward = goldWalkBackward;
        this.goldWalkLeft = goldWalkLeft;
        this.goldWalkRight = goldWalkRight;
        
        this.tip = tip;
        
        this.death = death;
        this.hatlessDeath = hatlessDeath;
        this.goldDeath = goldDeath;
        
        this.background = background;
        this.overlay = overlay;
        
        direction = Girl.DOWN;
    }
    
    @Override
    protected void update(){
        if(!tipping){
            
            // Movement
            
            boolean wDown = Input.isKeyDown(KeyEvent.VK_W);
            boolean sDown = Input.isKeyDown(KeyEvent.VK_S);
            
            boolean aDown = Input.isKeyDown(KeyEvent.VK_A);
            boolean dDown = Input.isKeyDown(KeyEvent.VK_D);
            
            float diagonalSpeed = (float)(SPEED / Math.sqrt(2));
            
            boolean wWithoutS = wDown && !sDown;
            boolean sWithoutW = !wDown && sDown;
            boolean aWithoutD = aDown && !dDown;
            boolean dWithoutA = !aDown && dDown;
            
            boolean idle = Camera.getVelX() == 0 && Camera.getVelY() == 0;
            
            if(wWithoutS){
                if(aWithoutD || dWithoutA){
                    Camera.setVelY(-diagonalSpeed);
                    if(idle){
                        if(aWithoutD){
                            direction = Girl.LEFT;
                            
                            if(fedoraThrown){
                                hatlessWalkLeft.restart();
                                setAnimation(hatlessWalkLeft);
                            }else if(goldenFedoraEquipped){
                                goldWalkLeft.restart();
                                setAnimation(goldWalkLeft);
                            }else{
                                walkLeft.restart();
                                setAnimation(walkLeft);
                            }
                        }else{
                            direction = Girl.RIGHT;
                            
                            if(fedoraThrown){
                                hatlessWalkRight.restart();
                                setAnimation(hatlessWalkRight);
                            }else if(goldenFedoraEquipped){
                                goldWalkRight.restart();
                                setAnimation(goldWalkRight);
                            }else{
                                walkRight.restart();
                                setAnimation(walkRight);
                            }
                        }
                    }
                }else{
                    Camera.setVelY(-SPEED);
                    if(direction != Girl.UP || idle){
                        direction = Girl.UP;
                        if(fedoraThrown){
                            hatlessWalkBackward.restart();
                            setAnimation(hatlessWalkBackward);
                        }else if(goldenFedoraEquipped){
                            goldWalkBackward.restart();
                            setAnimation(goldWalkBackward);
                        }else{
                            walkBackward.restart();
                            setAnimation(walkBackward);
                        }
                    }
                }
            }else if(sWithoutW){
                if(aWithoutD || dWithoutA){
                    Camera.setVelY(diagonalSpeed);
                    if(idle){
                        if(aWithoutD){
                            direction = Girl.LEFT;
                            
                            if(fedoraThrown){
                                hatlessWalkLeft.restart();
                                setAnimation(hatlessWalkLeft);
                            }else if(goldenFedoraEquipped){
                                goldWalkLeft.restart();
                                setAnimation(goldWalkLeft);
                            }else{
                                walkLeft.restart();
                                setAnimation(walkLeft);
                            }
                        }else{
                            direction = Girl.RIGHT;
                            
                            if(fedoraThrown){
                                hatlessWalkRight.restart();
                                setAnimation(hatlessWalkRight);
                            }else if(goldenFedoraEquipped){
                                goldWalkRight.restart();
                                setAnimation(goldWalkRight);
                            }else{
                                walkRight.restart();
                                setAnimation(walkRight);
                            }
                        }
                    }
                }else{
                    Camera.setVelY(SPEED);
                    if(direction != Girl.DOWN || idle){
                        direction = Girl.DOWN;
                        if(fedoraThrown){
                            hatlessWalkForward.restart();
                            setAnimation(hatlessWalkForward);
                        }else if(goldenFedoraEquipped){
                            goldWalkForward.restart();
                            setAnimation(goldWalkForward);
                        }else{
                            walkForward.restart();
                            setAnimation(walkForward);
                        }
                    }
                }
            }else{
                Camera.setVelY(0);
            }
            
            if(aWithoutD){
                if(wWithoutS || sWithoutW){
                    Camera.setVelX(-diagonalSpeed);
                    if(idle){
                        direction = Girl.LEFT;
                        
                        if(fedoraThrown){
                            hatlessWalkLeft.restart();
                            setAnimation(hatlessWalkLeft);
                        }else if(goldenFedoraEquipped){
                            goldWalkLeft.restart();
                            setAnimation(goldWalkLeft);
                        }else{
                            walkLeft.restart();
                            setAnimation(walkLeft);
                        }
                    }
                }else{
                    Camera.setVelX(-SPEED);
                    if(direction != Girl.LEFT || idle){
                        direction = Girl.LEFT;
                        if(fedoraThrown){
                            hatlessWalkLeft.restart();
                            setAnimation(hatlessWalkLeft);
                        }else if(goldenFedoraEquipped){
                            goldWalkLeft.restart();
                            setAnimation(goldWalkLeft);
                        }else{
                            walkLeft.restart();
                            setAnimation(walkLeft);
                        }
                    }
                }
            }else if(dWithoutA){
                if(wWithoutS || sWithoutW){
                    Camera.setVelX(diagonalSpeed);
                    if(idle){
                        direction = Girl.RIGHT;
                        
                        if(fedoraThrown){
                            hatlessWalkRight.restart();
                            setAnimation(hatlessWalkRight);
                        }else if(goldenFedoraEquipped){
                            goldWalkRight.restart();
                            setAnimation(goldWalkRight);
                        }else{
                            walkRight.restart();
                            setAnimation(walkRight);
                        }
                    }
                }else{
                    Camera.setVelX(SPEED);
                    if(direction != Girl.RIGHT || idle){
                        direction = Girl.RIGHT;
                        if(fedoraThrown){
                            hatlessWalkRight.restart();
                            setAnimation(hatlessWalkRight);
                        }else if(goldenFedoraEquipped){
                            goldWalkRight.restart();
                            setAnimation(goldWalkRight);
                        }else{
                            walkRight.restart();
                            setAnimation(walkRight);
                        }
                    }
                }
            }else{
                Camera.setVelX(0);
            }
            
            idle = Camera.getVelX() == 0 && Camera.getVelY() == 0;
            
            if(idle){
                if(direction == Girl.UP){
                    if(fedoraThrown){
                        setAnimation(hatlessIdleBackward);
                    }else if(goldenFedoraEquipped){
                        setAnimation(goldIdleBackward);
                    }else{
                        setAnimation(idleBackward);
                    }
                }else if(direction == Girl.DOWN){
                    if(fedoraThrown){
                        setAnimation(hatlessIdleForward);
                    }else if(goldenFedoraEquipped){
                        setAnimation(goldIdleForward);
                    }else{
                        setAnimation(idleForward);
                    }
                }else if(direction == Girl.LEFT){
                    if(fedoraThrown){
                        setAnimation(hatlessIdleLeft);
                    }else if(goldenFedoraEquipped){
                        setAnimation(goldIdleLeft);
                    }else{
                        setAnimation(idleLeft);                        
                    }
                }else{
                    if(fedoraThrown){
                        setAnimation(hatlessIdleRight);
                    }else if(goldenFedoraEquipped){
                        setAnimation(goldIdleRight);
                    }else{
                        setAnimation(idleRight);
                    }
                }
            }
            
            // Fedora Throw
            
            if(Input.isButtonPressed(MouseEvent.BUTTON1) && !fedoraThrown){
                Point mouseLoc = Game.getMouseLocation();
                
                if(mouseLoc != null){
                    Dimension windowSize = Game.getWindowSize();
                    
                    float mouseX = (float)(mouseLoc.getX() / windowSize.getWidth());
                    float mouseY = (float)(mouseLoc.getY() / windowSize.getHeight());
                    
                    float line1 = mouseX;
                    float line2 = 1 - mouseX;
                    
                    int quadrant;
                    if(mouseY < line1 && mouseY < line2){
                        quadrant = 1;
                    }else if(mouseY < line2){
                        quadrant = 2;
                    }else if(mouseY < line1){
                        quadrant = 3;
                    }else{
                        quadrant = 4;
                    }
                    
                    float playerX = getX();
                    float playerY = getY();
                    float playerWidth = getWidth();
                    float playerHeight= getHeight();
                    
                    float fedoraX;
                    float fedoraY;
                    float fedoraWidth = Fedora.WIDTH;
                    float fedoraHeight = fedoraWidth * Runner.ASPECT_RATIO;
                    
                    if(quadrant == 1){
                        fedoraX = (playerX + playerWidth / 2) - fedoraWidth / 2;
                        fedoraY = (playerY + playerHeight / 2) - fedoraHeight;
                    }else if(quadrant == 2){
                        fedoraX = (playerX + playerWidth / 2) - fedoraWidth;
                        fedoraY = (playerY + playerHeight / 2) - fedoraHeight / 2;
                    }else if(quadrant == 3){
                        fedoraX = (playerX + playerWidth / 2);
                        fedoraY = (playerY + playerHeight / 2) - fedoraHeight / 2;
                    }else{
                        fedoraX = (playerX + playerWidth / 2) - fedoraWidth / 2;
                        fedoraY = (playerY + playerHeight / 2);
                    }
                    
                    float fedoraTrajX = mouseX - 0.5f;
                    float fedoraTrajY = mouseY - 0.5f;
                    
                    Animation anim = goldenFedoraEquipped ? goldenFedoraAnimation : blackFedoraAnimation;
                    anim.restart();
                    
                    fedora = new Fedora(fedoraX, fedoraY, 0, 0, 0, 0, fedoraWidth, fedoraHeight, "fedora", anim, girlHitSound, fedoraTrajX, fedoraTrajY, manager, this);
                    manager.addObject(fedora);
                    
                    if(quadrant == 1){
                        manager.removeObject(this);
                        manager.addObject(this);
                    }
                    
                    if(direction == Girl.UP){
                        if(idle){
                            setAnimation(hatlessIdleBackward);
                        }else{
                            hatlessWalkBackward.restart();
                            setAnimation(hatlessWalkBackward);
                        }
                    }else if(direction == Girl.DOWN){
                        if(idle){
                            setAnimation(hatlessIdleForward);
                        }else{
                            hatlessWalkForward.restart();
                            setAnimation(hatlessWalkForward);
                        }
                    }else if(direction == Girl.LEFT){
                        if(idle){
                            setAnimation(hatlessIdleLeft);
                        }else{
                            hatlessWalkLeft.restart();
                            setAnimation(hatlessWalkLeft);
                        }
                    }else{
                        if(idle){
                            setAnimation(hatlessIdleRight);
                        }else{
                            hatlessWalkRight.restart();
                            setAnimation(hatlessWalkRight);
                        }
                    }
                    
                    fedoraThrown = true;
                    fedoraThrowSound.play();
                }
            }
            
            // Fedora Collision Check
            
            if(fedora != null && fedora.getDirectionChanged() && Physics.checkCollision(this, fedora, Physics.WHOLE_BOUNDS, Physics.WHOLE_BOUNDS)){
                if(direction == Girl.UP){
                    if(goldenFedoraEquipped){
                        if(idle){
                            setAnimation(goldIdleBackward);
                        }else{
                            goldWalkBackward.restart();
                            setAnimation(goldWalkBackward);
                        }
                    }else{
                        if(idle){
                            setAnimation(idleBackward);
                        }else{
                            walkBackward.restart();
                            setAnimation(walkBackward);
                        }
                    }
                }else if(direction == Girl.DOWN){
                    if(goldenFedoraEquipped){
                        if(idle){
                            setAnimation(goldIdleForward);
                        }else{
                            goldWalkForward.restart();
                            setAnimation(goldWalkForward);
                        }
                    }else{
                        if(idle){
                            setAnimation(idleForward);
                        }else{
                            walkForward.restart();
                            setAnimation(walkForward);
                        }
                    }
                }else if(direction == Girl.LEFT){
                    if(goldenFedoraEquipped){
                        if(idle){
                            setAnimation(goldIdleLeft);
                        }else{
                            goldWalkLeft.restart();
                            setAnimation(goldWalkLeft);
                        }
                    }else{
                        if(idle){
                            setAnimation(idleLeft);
                        }else{
                            walkLeft.restart();
                            setAnimation(walkLeft);
                        }
                    }
                }else{
                    if(goldenFedoraEquipped){
                        if(idle){
                            setAnimation(goldIdleRight);
                        }else{
                            goldWalkRight.restart();
                            setAnimation(goldWalkRight);
                        }
                    }else{
                        if(idle){
                            setAnimation(idleRight);
                        }else{
                            walkRight.restart();
                            setAnimation(walkRight);
                        }
                    }
                }
                
                fedoraThrown = false;
                manager.removeObject(fedora);
                fedora = null;
            }
            
            // Girl, IQ Item, and Power-Up Collision Checks
            
            for(int i = 0; i < manager.getObjectCount(); i++){
                GameObject obj = manager.getObject(i);
                String id = obj.getID();
                
                if(Physics.checkCollision(this, obj, Physics.WHOLE_BOUNDS, Physics.WHOLE_BOUNDS)){
                    if(id.equals("girl")){
                        Animation animation;
                        if(fedoraThrown){
                            animation = hatlessDeath;
                        }else if(goldenFedoraEquipped){
                            animation = goldDeath;
                        }else{
                            animation = death;
                        }
                        Levels.lose(this, animation, background, overlay);
                    }else if(id.equals("hammer")){
                        bar.lowerIQ(HAMMER_REDUCTION);
                        manager.removeObject(obj);
                        hammerSound.play();
                    }else if(id.equals("deoderant")){
                        bar.lowerIQ(DEODERANT_REDUCTION);
                        manager.removeObject(obj);
                        deoderantSound.play();
                    }else if(id.equals("dumbell")){
                        bar.lowerIQ(DUMBELL_REDUCTION);
                        manager.removeObject(obj);
                        dumbellSound.play();
                    }else if(currentPowerUp == NONE){
                        if(id.equals("frappe")){
                            currentPowerUp = FRAPPE;
                            manager.removeObject(obj);
                            powerUpPickupSound.play();
                        }else if(id.equals("vape")){
                            currentPowerUp = VAPE;
                            manager.removeObject(obj);
                            powerUpPickupSound.play();
                        }else if(id.equals("goldenFedora")){
                            if(fedoraThrown){
                                goldenFedoraAnimation.restart();
                                fedora.setAnimation(goldenFedoraAnimation);
                            }else{
                                if(direction == Girl.UP){
                                    if(idle){
                                        setAnimation(goldIdleBackward);
                                    }else{
                                        goldWalkBackward.restart();
                                        setAnimation(goldWalkBackward);
                                    }
                                }else if(direction == Girl.DOWN){
                                    if(idle){
                                        setAnimation(goldIdleForward);
                                    }else{
                                        goldWalkForward.restart();
                                        setAnimation(goldWalkForward);
                                    }
                                }else if(direction == Girl.LEFT){
                                    if(idle){
                                        setAnimation(goldIdleLeft);
                                    }else{
                                        goldWalkLeft.restart();
                                        setAnimation(goldWalkLeft);
                                    }
                                }else{
                                    if(idle){
                                        setAnimation(goldIdleRight);
                                    }else{
                                        goldWalkRight.restart();
                                        setAnimation(goldWalkRight);
                                    }
                                }  
                            }
                            
                            currentPowerUp = FEDORA;
                            manager.removeObject(obj);
                            goldenFedoraEquipped = true;
                            powerUpPickupSound.play();
                        }
                    }
                }
            }
            
            // Power-Up Activations
            
            if(currentPowerUp != NONE){
                boolean rightClicked = Input.isButtonPressed(MouseEvent.BUTTON3);
                
                if(rightClicked){
                    if(currentPowerUp == FRAPPE){
                        Point mouseLoc = Game.getMouseLocation();
                        
                        if(mouseLoc != null){
                            Dimension windowSize = Game.getWindowSize();
                            float mouseX = (float)(mouseLoc.getX() / windowSize.getWidth());
                            float mouseY = (float)(mouseLoc.getY() / windowSize.getHeight());
                            
                            float frappeWidth = 0.06f;
                            float frappeHeight = 2 * frappeWidth * Runner.ASPECT_RATIO;
                            float frappeX = mouseX - frappeWidth / 2 + Camera.getX();
                            float frappeY = mouseY - frappeHeight + Camera.getY();
                            float frappeRadiusWidth = Frappe.DETECTION_RADIUS;
                            float frappeRadiusHeight = frappeRadiusWidth * Runner.ASPECT_RATIO;
                            float frappeRadiusX = frappeX + (frappeWidth / 2) - frappeRadiusWidth;
                            float frappeRadiusY = frappeY + (frappeHeight * 3f / 4) - frappeRadiusHeight;
                            
                            float frappeDuration = 2;
                            Animation frappeAnimation = new Animation(frappeDuration, frappeSprites.getTexture(0, 7), frappeSprites.getTexture(1, 7), frappeSprites.getTexture(2, 7), frappeSprites.getTexture(3, 7));
                            
                            GameObject frappeRadius = new TexturedGameObject(frappeRadiusX, frappeRadiusY, 0, 0, 0, 0, frappeRadiusWidth * 2, frappeRadiusHeight * 2, "frappeRadius", new Texture("textures/frappeRadius.png"));
                            Frappe frappe = new Frappe(frappeX, frappeY, 0, 0, 0, 0, frappeWidth, frappeHeight, "placedFrappe", frappeAnimation, girlHitSound, manager, frappeRadius);
                            manager.addObject(frappeRadius);
                            manager.addObject(frappe);
                            
                            currentPowerUp = NONE;
                            frappeSound.play();
                        }
                    }else if(currentPowerUp == VAPE){
                        Point mouseLoc = Game.getMouseLocation();
                        
                        if(mouseLoc != null){
                            float cloudWidth = 0.3f;
                            float cloudHeight = cloudWidth * Runner.ASPECT_RATIO;
                            float cloudX = Camera.getX() + 0.5f - cloudWidth / 2;
                            float cloudY = Camera.getY() + 0.5f  - cloudHeight / 2;
                            
                            Dimension windowSize = Game.getWindowSize();
                            
                            float mouseX = (float)(mouseLoc.getX() / windowSize.getWidth());
                            float mouseY = (float)(mouseLoc.getY() / windowSize.getHeight());
                            
                            float line1 = mouseX;
                            float line2 = 1 - mouseX;
                            
                            if(mouseY < line1 && mouseY < line2){
                                cloudY -= (getHeight() + cloudHeight) / 2;
                            }else if(mouseY < line2){
                                cloudX -= (getWidth() + cloudWidth) / 2;
                            }else if(mouseY < line1){
                                cloudX += (getWidth() + cloudWidth) / 2;
                            }else{
                                cloudY += (getHeight() + cloudHeight) / 2;
                            }
                            
                            float trajX = mouseX - 0.5f;
                            float trajY = mouseY - 0.5f;
                            
                            VapeCloud cloud = new VapeCloud(cloudX, cloudY, 0, 0, 0, 0, cloudWidth, cloudHeight, "burpCloud", cloudSprite, girlHitSound, trajX, trajY, manager);
                            manager.addObject(cloud);
                            
                            currentPowerUp = NONE;
                            vapeSound.play();
                        }
                    }else{
                        Camera.setVelX(0);
                        Camera.setVelY(0);
                        
                        manager.removeObject(fedora);
                        
                        GoldenFlash flash = new GoldenFlash(0, 0, 0, 0, 0, 0, 1, 1, "flash", Color.WHITE, manager);
                        manager.addObject(flash);
                        
                        tip.restart();
                        setAnimation(tip);
                        
                        fedoraThrown = false;
                        tipping = true;
                        currentPowerUp = NONE;
                        goldenFedoraSound.play();
                        playMusic.pause();
                    }
                }
            }
            
            // Bounds Check
            
            float negHalfFieldSize = -Levels.PLAY_FIELD_SIZE / 2;
            float halfFieldSizeMinusWindow = Levels.PLAY_FIELD_SIZE / 2 - 1;
            
            if(Camera.getX() < negHalfFieldSize){
                Camera.setX(negHalfFieldSize);
            }else if(Camera.getX() > halfFieldSizeMinusWindow){
                Camera.setX(halfFieldSizeMinusWindow);
            }
            
            if(Camera.getY() < negHalfFieldSize){
                Camera.setY(negHalfFieldSize);
            }else if(Camera.getY() > halfFieldSizeMinusWindow){
                Camera.setY(halfFieldSizeMinusWindow);
            }
        }else{
            GoldenFlash flash = (GoldenFlash)manager.getObjects("flash").get(0);
            if(flash.flashFinished()){
                direction = Girl.DOWN;
                setAnimation(idleForward);
                manager.removeObject(flash);
                tipping = false;
                goldenFedoraEquipped = false;
                playMusic.continuePlaying();
            }
        }
    }
    
    public int getCurrentPowerUp(){
        return currentPowerUp;
    }
    
}
