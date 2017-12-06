package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import main.Game;
import main.MenuButton;
import main.MenuLabel;
import main.SoundBank;
import main.Texture;

public class VolumeButton extends MenuButton {
    
    public static final MenuLabel BLANK_LABEL = new MenuLabel("", "", 0, 0, Color.BLACK, 0, 0, "unused0");
    
    private Texture texture;
    private float minX;
    private float maxX;
    
    public VolumeButton(float y, float w, float h, String id, Texture texture, float minX, float maxX){
        super(BLANK_LABEL, 0, y, w, h, null, null, null, 0, 0, id);
        this.texture = texture;
        this.minX = minX;
        this.maxX = maxX;
        
        float volume;
        
        if(id.equals("musicButton")){
            volume = Settings.getMusicVolume();
        }else{
            volume = Settings.getSFXVolume();
        }
        
        setX(volume * (maxX - minX) + minX);
    }
    
    @Override
    protected void update(){
        if(isHeld()){
            Point mouseLoc = Game.getMouseLocation();
            
            if(mouseLoc != null){
                double mouseX = Game.getMouseLocation().getX() / Game.getWindowSize().getWidth();
                float grabbedX = (float)Math.min(Math.max(mouseX, minX), maxX);
                setX(grabbedX);
            }
        }
        
        float volume = (getX() - minX) / (maxX - minX);
        
        if(getID().equals("musicButton")){
            SoundBank.setVolume(volume, true);
        }else{
            SoundBank.setVolume(volume, false);
        }
        
        if(isReleased()){
            if(getID().equals("musicButton")){
                Settings.setMusicVolume(volume);
                Settings.writeOptions();;
            }else if(getID().equals("sfxButton")){
                Settings.setSFXVolume(volume);
                Settings.writeOptions();
            }
        }
    }
    
    @Override
    protected void render(Graphics2D g){
        Dimension size = Game.getWindowSize();
        double wWidth = size.getWidth();
        double wHeight = size.getHeight();
        g.drawImage(texture.getImage(), (int)(getX() * wWidth), (int)(getY() * wHeight), (int)(getWidth() * wWidth), (int)(getHeight() * wHeight), null);
    }
    
}
