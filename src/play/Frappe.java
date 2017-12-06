package play;

import main.AnimatedGameObject;
import main.Animation;
import main.GameObject;
import main.LerpedFloat;
import main.Manager;
import main.Physics;
import main.Sound;

public class Frappe extends AnimatedGameObject {
    
    public static final float DETECTION_RADIUS = 0.4f;
    public static final float LIFESPAN = 15;
    
    private Manager manager;
    private GameObject radius;
    private LerpedFloat lifeRemaining;
    private Sound girlHitSound;
    
    public Frappe(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Animation animation, Sound girlHitSound, Manager manager, GameObject radius) {
        super(x, y, vX, vY, aX, aY, w, h, id, animation);
        this.manager = manager;
        this.radius = radius;
        this.girlHitSound = girlHitSound;
        lifeRemaining = new LerpedFloat(0, 1, LIFESPAN);
        lifeRemaining.start();
    }
    
    @Override
    protected void update(){
        
        // Checking Girl collisions
        
        for(int i = 0; i < manager.getObjectCount(); i++){
            GameObject girl = manager.getObject(i);
            
            if(girl.getID().equals("girl") && Physics.checkCollision(this, girl, Physics.LOWER_BOUNDS, Physics.WHOLE_BOUNDS)){
                ((Girl)girl).ascend(manager);
                i--;
                girlHitSound.play();
            }
        }
        
        // Checking remaining life
        
        if(lifeRemaining.isFinished()){
            manager.removeObject(radius);
            manager.removeObject(this);
        }
    }
    
}
