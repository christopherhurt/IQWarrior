package menus;

import java.awt.Dimension;
import java.awt.Graphics2D;
import main.Game;
import main.Menu;
import main.MenuButton;
import main.SoundBank;
import main.Texture;
import play.Runner;

public class WidthButton extends MenuButton {
    
    private int width;
    private boolean selected;
    private boolean wasHovered;
    private boolean selectable;
    
    private Texture idleTexture;
    private Texture hoveredTexture;
    private Texture clickedTexture;
    
    public WidthButton(float x, float y, float w, float h, String id, Texture idleTexture, Texture hoveredTexture, Texture clickedTexture, int width){
        super(VolumeButton.BLANK_LABEL, x, y, w, h, null, null, null, 0, 0, id);
        this.width = width;
        this.idleTexture = idleTexture;
        this.hoveredTexture = hoveredTexture;
        this.clickedTexture = clickedTexture;
        wasHovered = false;
        selectable = true;
        selected = Settings.getWindowWidth() == width;
    }
    
    @Override
    protected void update(){
        if(wasHovered && !isHovered() && isHeld()){
            selectable = false;
        }
        
        wasHovered = isHovered();
        if(wasHovered && isReleased() && selectable){
            deselectAll();
            selected = true;
            SoundBank.getSound("mouseClick").play();
            Game.setWindowSize(width, (int)(width / Runner.ASPECT_RATIO));
            Settings.setWindowWidth(width);
            Settings.writeOptions();
        }
        
        if(isReleased()){
            selectable = true;
        }
    }
    
    private void deselectAll(){
        Menu menu = Game.getMenu("options");
        ((WidthButton)menu.getButton("sizeButton400")).setSelected(false);
        ((WidthButton)menu.getButton("sizeButton800")).setSelected(false);
        ((WidthButton)menu.getButton("sizeButton1200")).setSelected(false);
        ((WidthButton)menu.getButton("sizeButton1600")).setSelected(false);
    }
    
    @Override
    protected void render(Graphics2D g){
        Texture currentTexture;
        
        if(selected){
            currentTexture = clickedTexture;
        }else{
            if(isHovered() && selectable){
                if(isHeld()){
                    currentTexture = clickedTexture;
                }else{
                    currentTexture = hoveredTexture;
                }
            }else{
                currentTexture = idleTexture;
            }
        }
        
        Dimension size = Game.getWindowSize();
        g.drawImage(currentTexture.getImage(), (int)(getX() * size.getWidth()), (int)(getY() * size.getHeight()), (int)(getWidth() * size.getWidth()), (int)(getHeight() * size.getHeight()), null);
    }
    
    public void setSelected(boolean selected){
        this.selected = selected;
    }
    
}
