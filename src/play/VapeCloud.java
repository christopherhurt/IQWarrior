package play;

import main.GameObject;
import main.Manager;
import main.Physics;
import main.Sound;
import main.Texture;
import main.TexturedGameObject;

public class VapeCloud extends TexturedGameObject {
    
    private static final float EXPANSION_RATE = 0.008f;
    private static final float ACCELERATION = 0.0002f;
    
    private Manager manager;
    private Sound girlHitSound;
    
    public VapeCloud(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Texture texture, Sound girlHitSound, float trajX, float trajY, Manager manager) {
        super(x, y, vX, vY, aX, aY, w, h, id, texture);
        this.manager = manager;
        this.girlHitSound = girlHitSound;
        
        float length = (float)(Math.sqrt(trajX * trajX + trajY * trajY));
        setAccX(trajX / length * ACCELERATION);
        setAccY(trajY / length * ACCELERATION);
    }
    
    @Override
    public void update(){
        
        // Expansion
        
        setX(getX() - EXPANSION_RATE / 2 * Runner.ASPECT_RATIO);
        setY(getY() - EXPANSION_RATE / 2 * Runner.ASPECT_RATIO);
        setWidth(getWidth() + EXPANSION_RATE);
        setHeight(getHeight() + EXPANSION_RATE * Runner.ASPECT_RATIO);
        
        // Girl collision
        
        for(int i = 0; i < manager.getObjectCount(); i++){
            GameObject girl = manager.getObject(i);
            
            if(girl.getID().equals("girl") && Physics.checkCollision(this, girl, Physics.WHOLE_BOUNDS, Physics.WHOLE_BOUNDS)){
                ((Girl)girl).ascend(manager);
                i--;
                girlHitSound.play();
            }
        }
        
        // Offscreen Removal
        
        boolean offscreenX = getX() > Levels.PLAY_FIELD_SIZE / 2 || getX() < -Levels.PLAY_FIELD_SIZE / 2 - getWidth();
        boolean offscreenY = getY() > Levels.PLAY_FIELD_SIZE / 2 || getY() < -Levels.PLAY_FIELD_SIZE / 2 - getHeight();
        if(offscreenX || offscreenY){
            manager.removeObject(this);
        }
    }
    
}
