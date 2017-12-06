package play;

import main.Manager;
import main.Texture;
import main.TexturedGameObject;

public class AscendingGirl extends TexturedGameObject {
    
    private static final float ACC_Y = -0.0008f;
    
    private Manager manager;
    
    public AscendingGirl(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Texture texture, Manager manager) {
        super(x, y, vX, vY, aX, aY, w, h, id, texture);
        this.manager = manager;
        
        setVelX(0);
        setVelY(0);
        setAccX(0);
        setAccY(ACC_Y);
    }
    
    @Override
    protected void update(){
        if(getY() < -Levels.PLAY_FIELD_SIZE / 2 - getHeight()){
            manager.removeObject(this);
        }
    }
    
}
