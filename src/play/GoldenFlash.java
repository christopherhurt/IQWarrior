package play;

import java.awt.Color;
import main.ColoredGameObject;
import main.GameObject;
import main.Manager;

public class GoldenFlash extends ColoredGameObject {
    
    private static final float RATE = 6.4f;
    private static final float MAX_ALPHA = 128;
    private static final Color RGB_COLOR = new Color(255, 215, 0, 0);
    
    private Manager manager;
    private float alpha;
    private boolean goingDown;
    
    public GoldenFlash(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Color color, Manager manager) {
        super(x, y, vX, vY, aX, aY, w, h, id, color);
        setCameraAffected(false);
        this.manager = manager;
        alpha = 0;
        goingDown = false;
        updateColor();
    }
    
    @Override
    protected void update(){
        if(goingDown){
            alpha -= RATE;
        }else{
            alpha += RATE;
        }
        alpha = Math.max(alpha, 0);
        alpha = Math.min(alpha, 255.9999f);
        
        if(alpha >= MAX_ALPHA){
            alpha = MAX_ALPHA;
            goingDown = true;
            
            for(int i = 0; i < manager.getObjectCount(); i++){
                GameObject girl = manager.getObject(i);
                
                if(girl.getID().equals("girl")){
                    ((Girl)girl).ascend(manager);
                    i--;
                }
            }
            
            manager.removeObject(this);
            manager.addObject(this);
        }
        
        updateColor();
    }
    
    private void updateColor(){
        setColor(new Color(RGB_COLOR.getRed(), RGB_COLOR.getGreen(), RGB_COLOR.getBlue(), (int)alpha));
    }
    
    public boolean flashFinished(){
        return goingDown && alpha <= 0;
    }
    
}
