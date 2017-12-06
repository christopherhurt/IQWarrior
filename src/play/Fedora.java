package play;

import main.AnimatedGameObject;
import main.Animation;
import main.GameObject;
import main.Manager;
import main.Physics;
import main.Sound;

public class Fedora extends AnimatedGameObject {
    
    public static final float WIDTH = 0.06f;
    
    private static final float INITIAL_SPEED = 0.03f;
    private static final float OPPOSING_ACCELERATION = 0.001f;
    private static final float RETURN_SPEED = 0.006f;
    private static final float RETURN_SPEED_INCREASE = 0.25f;
    
    private Player player;
    private Manager manager;
    private Sound girlHitSound;
    private boolean directionChanged;
    private int initialXSign;
    private int initialYSign;
    private float timeSinceReversed;
    
    public Fedora(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Animation animation, Sound girlHitSound, float trajX, float trajY, Manager manager, Player player) {
        super(x, y, vX, vY, aX, aY, w, h, id, animation);
        
        float length = (float)Math.sqrt(trajX * trajX + trajY * trajY);
        if(length != 0){
            trajX = trajX / length;
            trajY = trajY / length;
        }
        
        this.manager = manager;
        this.player = player;
        this.girlHitSound = girlHitSound;
        directionChanged = false;
        timeSinceReversed = 0;
        
        setVelX(trajX * INITIAL_SPEED);
        setVelY(trajY * INITIAL_SPEED);
        setAccX(-trajX * OPPOSING_ACCELERATION);
        setAccY(-trajY * OPPOSING_ACCELERATION);
        
        initialXSign = trajX > 0 ? 1 : (trajX < 0 ? -1 : 0);
        initialYSign = trajY > 0 ? 1 : (trajY < 0 ? -1 : 0);
    }
    
    @Override
    protected void update(){
        
        // Movement
        
        if(!directionChanged){
            float vX = getVelX();
            float vY = getVelY();
            
            int xSign = vX > 0 ? 1 : (vX < 0 ? -1 : 0);
            int ySign = vY > 0 ? 1 : (vY < 0 ? -1 : 0);
            
            directionChanged = xSign != initialXSign || ySign != initialYSign || (xSign == 0 && ySign == 0);
            
            if(directionChanged){
                setVelX(0);
                setVelY(0);
            }
        }else{
            timeSinceReversed += RETURN_SPEED_INCREASE;
            
            float fedoraCenterX = getX() + getWidth() / 2;
            float fedoraCenterY = getY() + getHeight() / 2;
            float playerCenterX = player.getX() + player.getWidth() / 2;
            float playerCenterY = player.getY() + player.getHeight() / 2;
            
            float velTrajX = playerCenterX - fedoraCenterX;
            float velTrajY = playerCenterY - fedoraCenterY;
            
            float distance = (float)Math.sqrt(velTrajX * velTrajX + velTrajY * velTrajY);
            
            float velX = velTrajX / distance * RETURN_SPEED;
            float velY = velTrajY / distance * RETURN_SPEED;
            
            setVelX(velX * timeSinceReversed);
            setVelY(velY * timeSinceReversed);
        }
        
        // Girl Collision
        
        for(int i = 0; i < manager.getObjectCount(); i++){
            GameObject girl = manager.getObject(i);
            
            if(girl.getID().equals("girl") && Physics.checkCollision(this, girl, Physics.WHOLE_BOUNDS, Physics.WHOLE_BOUNDS)){
                ((Girl)girl).ascend(manager);
                i--;
                girlHitSound.play();
            }
        }
    }
    
    public boolean getDirectionChanged(){
        return directionChanged;
    }
    
}
