package play;

import main.Manager;
import main.MenuLabel;
import main.Texture;
import main.TexturedGameObject;

public class IQBar extends TexturedGameObject {
    
    public static final int MIN_IQ = 0;
    public static final int MAX_IQ = 300;
    
    private static final float IQ_INCREASE_RATE = 0.1f;
    
    private Manager manager;
    private MenuLabel iqLabel;
    private float iq;
    
    public IQBar(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Texture texture, Manager manager, MenuLabel iqLabel) {
        super(x, y, vX, vY, aX, aY, w, h, id, texture);
        setCameraAffected(false);
        this.manager = manager;
        this.iqLabel = iqLabel;
        iq = MIN_IQ;
    }
    
    @Override
    protected void update(){
        iq += IQ_INCREASE_RATE;
        
        if(iq > MAX_IQ){
            iq = MAX_IQ;
        }
        
        iqLabel.setText("IQ : " + getIQ());
        
        manager.removeObject(this);
        manager.addObject(this);
        manager.readdObjects("iqlevel");
    }
    
    public void lowerIQ(int amount){
        iq = Math.max(iq - amount, MIN_IQ);
    }
    
    public int getIQ(){
        return (int)iq;
    }
    
}
