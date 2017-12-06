package play;

import main.AnimatedGameObject;
import main.Animation;
import main.GameObject;
import main.Manager;
import main.Texture;

public class Girl extends AnimatedGameObject {
    
    public static final float WIDTH = 0.075f;
    public static final float HEIGHT_RATIO = 1f;
    
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    private static final float MIN_SPEED = 0.003f;
    private static final float MAX_SPEED = 0.0075f;
    private static final float WALK_ANIMATION_SPEED = 2f;
    
    private static float speed = MIN_SPEED;
    
    private Manager manager;
    private Player player;
    private IQBar bar;
    private int currentDirection;
    
    private Animation walkingUp;
    private Animation walkingDown;
    private Animation walkingLeft;
    private Animation walkingRight;
    private Texture ascensionSprite;
    
    public Girl(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Manager manager, Player player, IQBar bar, Texture ascending,
        Texture gU0, Texture gU1, Texture gU2, Texture gU3, Texture gU4, Texture gU5, Texture gU6, Texture gU7,
        Texture gD0, Texture gD1, Texture gD2, Texture gD3, Texture gD4, Texture gD5, Texture gD6, Texture gD7,
        Texture gL0, Texture gL1, Texture gL2, Texture gL3, Texture gL4, Texture gL5, Texture gL6, Texture gL7,
        Texture gR0, Texture gR1, Texture gR2, Texture gR3, Texture gR4, Texture gR5, Texture gR6, Texture gR7) {
        super(x, y, vX, vY, aX, aY, w, h, id, null);
        setBoundsSize(0.6f);
        this.manager = manager;
        this.player = player;
        this.bar = bar;
        walkingUp = new Animation(WALK_ANIMATION_SPEED, gU0, gU1, gU2, gU3, gU4, gU5, gU6, gU7);
        walkingDown = new Animation(WALK_ANIMATION_SPEED, gD0, gD1, gD2, gD3, gD4, gD5, gD6, gD7);
        walkingLeft = new Animation(WALK_ANIMATION_SPEED, gL0, gL1, gL2, gL3, gL4, gL5, gL6, gL7);
        walkingRight = new Animation(WALK_ANIMATION_SPEED, gR0, gR1, gR2, gR3, gR4, gR5, gR6, gR7);
        ascensionSprite = ascending;
        setAnimation(walkingDown);
        currentDirection = DOWN;
    }
    
    @Override
    protected void update(){
        
        // Adjusting Speed
        
        int iq = bar.getIQ();
        float normedIQ = (float)(iq - IQBar.MIN_IQ) / (IQBar.MAX_IQ - IQBar.MIN_IQ);
        speed = normedIQ * (MAX_SPEED - MIN_SPEED) + MIN_SPEED;
        
        // Checking if player or frappe attraction
        
        boolean closeToFrappe = false;
        GameObject closeFrappe = null;
        float minDistance = Float.MAX_VALUE;
        
        for(int i = 0; i < manager.getObjectCount(); i++){
            GameObject frappe = manager.getObject(i);
            
            if(frappe.getID().equals("placedFrappe")){
                float dX = (frappe.getX() + frappe.getWidth() / 2) - (getX() + getWidth() / 2);
                float dY = ((frappe.getY() + frappe.getHeight() / 2) - (getY() + getHeight() / 2)) / Runner.ASPECT_RATIO;
                float dist = (float)Math.sqrt(dX * dX + dY * dY);
                
                if(dist <= Frappe.DETECTION_RADIUS && dist < minDistance){
                    minDistance = dist;
                    closeFrappe = frappe;
                    closeToFrappe = true;
                }
            }
        }
        
        float dirX;
        float dirY;
        if(closeToFrappe){
            dirX = (closeFrappe.getX() + closeFrappe.getWidth() / 2) - (getX() + getWidth() / 2);
            dirY = (closeFrappe.getY() + closeFrappe.getHeight() / 2) - (getY() + getHeight() / 2);
        }else{
            dirX = (player.getX() + player.getWidth() / 2) - (getX() + getWidth() / 2);
            dirY = (player.getY() + player.getHeight() / 2) - (getY() + getHeight() / 2);
        }
        
        // Adjusting direction of movement
        
        float distance = (float)Math.sqrt(dirX * dirX + dirY * dirY);
        if(distance != 0){
            dirX = dirX / distance;
            dirY = dirY / distance;
            
            setVelX(dirX * speed);
            setVelY(dirY * speed);
        }
        
        // Updating Animation
        
        double x = getVelX();
        double y = getVelY();
        double absX = Math.abs(getVelX());
        double absY = Math.abs(getVelY());
        boolean quad1 = y < 0 && absY >= absX;
        boolean quad2 = x < 0 && absY < absX;
        boolean quad3 = x > 0 && absY < absX;
        int direction;
        
        if(quad1){
            direction = UP;
        }else if(quad2){
            direction = LEFT;
        }else if(quad3){
            direction = RIGHT;
        }else{
            direction = DOWN;
        }
        
        if(direction != currentDirection){
            if(direction == UP){
                walkingUp.restart();
                setAnimation(walkingUp);
            }else if(direction == DOWN){
                walkingDown.restart();
                setAnimation(walkingDown);
            }else if(direction == LEFT){
                walkingLeft.restart();
                setAnimation(walkingLeft);
            }else{
                walkingRight.restart();
                setAnimation(walkingRight);
            }
            
            currentDirection = direction;
        }
    }
    
    public void ascend(Manager manager){
        manager.removeObject(this);
        AscendingGirl ascendingGirl = new AscendingGirl(this.getX(), this.getY(), 0, 0, 0, 0, this.getWidth(), this.getHeight(), "ascendingGirl", ascensionSprite, manager);
        manager.addObject(ascendingGirl);
    }

}
