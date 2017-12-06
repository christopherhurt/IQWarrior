package menus;

import java.awt.Dimension;
import java.awt.Graphics2D;
import main.Camera;
import main.Game;
import main.MenuButton;
import main.SoundBank;
import main.Texture;

public class HomeButton extends MenuButton {
    
    private boolean wasHovered;
    private boolean selectable;
    
    private Texture idleTexture;
    private Texture hoveredTexture;
    private Texture clickedTexture;
    
    public HomeButton(float x, float y, float w, float h, String id, Texture idleTexture, Texture hoveredTexture, Texture clickedTexture) {
        super(VolumeButton.BLANK_LABEL, x, y, w, h, null, null, null, 0, 0, id);
        this.idleTexture = idleTexture;
        this.hoveredTexture = hoveredTexture;
        this.clickedTexture = clickedTexture;
        wasHovered = false;
        selectable = true;
    }
    
    @Override
    protected void update(){
        if(wasHovered && !isHovered() && isHeld()){
            selectable = false;
        }
        
        wasHovered = isHovered();
        if(wasHovered && isReleased() && selectable){
            SoundBank.getSound("mouseClick").play();
            if(getID().equals("game over home button")){
                SoundBank.getSound("menu music").play();
            }
            Game.setCurrentMenu("main menu");
            Game.setCurrentManager("main menu");
            Camera.setX(0);
            Camera.setY(0);
            Camera.setVelX(0);
            Camera.setVelY(0);
        }
        
        if(isReleased()){
            selectable = true;
        }
    }
    
    @Override
    protected void render(Graphics2D g){
        Texture currentTexture;
        
        if(isHovered() && selectable){
            if(isHeld()){
                currentTexture = clickedTexture;
            }else{
                currentTexture = hoveredTexture;
            }
        }else{
            currentTexture = idleTexture;
        }
        
        Dimension size = Game.getWindowSize();
        g.drawImage(currentTexture.getImage(), (int)(getX() * size.getWidth()), (int)(getY() * size.getHeight()), (int)(getWidth() * size.getWidth()), (int)(getHeight() * size.getHeight()), null);
    }
    
}
