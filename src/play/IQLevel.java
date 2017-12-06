package play;

import java.awt.Color;
import main.ColoredGameObject;

public class IQLevel extends ColoredGameObject {
    
    private IQBar bar;
    private float maxWidth;
    
    public IQLevel(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Color color, IQBar bar) {
        super(x, y, vX, vY, aX, aY, w, h, id, color);
        setCameraAffected(false);
        this.bar = bar;
        maxWidth = w;
    }
    
    @Override
    protected void update(){
        int iq = bar.getIQ();
        float normedIQ = (float)(iq - IQBar.MIN_IQ) / (IQBar.MAX_IQ - IQBar.MIN_IQ);
        setWidth(normedIQ * maxWidth);
    }
    
}
