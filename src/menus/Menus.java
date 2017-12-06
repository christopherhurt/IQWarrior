package menus;

import java.awt.Color;
import main.ColoredGameObject;
import main.Game;
import main.GameObject;
import main.Manager;
import main.Menu;
import main.Texture;
import main.TexturedGameObject;
import play.Runner;

public class Menus {
    
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    
    public static void createOptionsMenu(Manager manager, Menu menu){
        // Preparing Construction
        manager.clearObjects();
        menu.clearAll();
        
        // Background
        GameObject background = new ColoredGameObject(0, 0, 0, 0, 0, 0, 1, 1, "optionsBackgroud", BACKGROUND_COLOR);
        manager.addObject(background);
        
        // Options Header
        float optionsHeaderWidth = 0.5f;
        float optionsHeaderY = 0.1f;
        float optionsHeaderHeight = 11f / 75 * optionsHeaderWidth * Runner.ASPECT_RATIO;
        float optionsHeaderX = 0.5f - optionsHeaderWidth / 2;
        TexturedGameObject optionsHeader = new TexturedGameObject(optionsHeaderX, optionsHeaderY, 0, 0, 0, 0, optionsHeaderWidth, optionsHeaderHeight, "optionsHeader", new Texture("menus/options_text.png"));
        manager.addObject(optionsHeader);
        
        // Music Volume Slider and Button
        Texture volumeSliderTexture = new Texture("menus/volume_bar.png");
        Texture volumeButtonTexture = new Texture("menus/volume_bar_bar.png");
        float sliderWidth = 0.2f;
        float sliderSpacing = 0.3f;
        float sliderY = 0.4f;
        float buttonWidth = 0.01f;
        float buttonMaxOffset = 0.001f;
        float buttonMinOffset = -0.001f;
        float sliderHeight = 16f / 116 * sliderWidth * Runner.ASPECT_RATIO;
        float musicSliderX = 1f / 2 * (1 - sliderSpacing - 2 * sliderWidth);
        TexturedGameObject musicSlider = new TexturedGameObject(musicSliderX, sliderY, 0, 0, 0, 0, sliderWidth, sliderHeight, "musicSlider", volumeSliderTexture);
        manager.addObject(musicSlider);
        float buttonHeight = 16f / 3 * buttonWidth * Runner.ASPECT_RATIO;
        float buttonY = sliderY + (sliderHeight - buttonHeight) / 2;
        float musicButtonMinX = musicSliderX + 2f / 29 * sliderWidth + buttonMinOffset;
        float musicButtonMaxX = musicSliderX + sliderWidth - 2f / 29 * sliderWidth - buttonWidth + buttonMaxOffset;
        VolumeButton musicButton = new VolumeButton(buttonY, buttonWidth, buttonHeight, "musicButton", volumeButtonTexture, musicButtonMinX, musicButtonMaxX);
        menu.addButton(musicButton);
        
        // SFX Volume Slider and Button
        float sfxSliderX = musicSliderX + sliderWidth + sliderSpacing;
        TexturedGameObject sfxSlider = new TexturedGameObject(sfxSliderX, sliderY, 0, 0, 0, 0, sliderWidth, sliderHeight, "sfxSlider", volumeSliderTexture);
        manager.addObject(sfxSlider);
        float sfxButtonMinX = sfxSliderX + 2f / 29 * sliderWidth + buttonMinOffset;
        float sfxButtonMaxX = sfxSliderX + sliderWidth - 2f / 29 * sliderWidth - buttonWidth + buttonMaxOffset;
        VolumeButton sfxButton = new VolumeButton(buttonY, buttonWidth, buttonHeight, "sfxButton", volumeButtonTexture, sfxButtonMinX, sfxButtonMaxX);
        menu.addButton(sfxButton);
        
        // Music Slider Label
        Texture musicLabelTexture = new Texture("menus/music_text.png");
        float labelHeight = 0.06f;
        float volumeLabelY = 0.25f;
        float sizeLabelY = 0.5f;
        float musicLabelWidth = 53f / 11 * labelHeight / Runner.ASPECT_RATIO;
        float musicLabelX = musicSliderX + (sliderWidth - musicLabelWidth) / 2;
        TexturedGameObject musicLabel = new TexturedGameObject(musicLabelX, volumeLabelY, 0, 0, 0, 0, musicLabelWidth, labelHeight, "musicLabel", musicLabelTexture);
        manager.addObject(musicLabel);
        
        // SFX Slider Label
        Texture sfxLabelTexture = new Texture("menus/sfx_text.png");
        float sfxLabelWidth = 31f / 11 * labelHeight / Runner.ASPECT_RATIO;
        float sfxLabelX = sfxSliderX + (sliderWidth - sfxLabelWidth) / 2;
        TexturedGameObject sfxLabel = new TexturedGameObject(sfxLabelX, volumeLabelY, 0, 0, 0, 0, sfxLabelWidth, labelHeight, "sfxLabel", sfxLabelTexture);
        manager.addObject(sfxLabel);
        
        // Window Size Label
        Texture sizeLabelTexture = new Texture("menus/window_size_text.png");
        float sizeLabelWidth = 112f / 11 * labelHeight / Runner.ASPECT_RATIO;
        float sizeLabelX = 0.5f - sizeLabelWidth / 2;
        TexturedGameObject sizeLabel = new TexturedGameObject(sizeLabelX, sizeLabelY, 0, 0, 0, 0, sizeLabelWidth, labelHeight, "sizeLabel", sizeLabelTexture);
        manager.addObject(sizeLabel);
        
        // Window Size Button 400
        Texture sizeButton400TextureIdle = new Texture("menus/400x300_button.png");
        Texture sizeButton400TextureHovered = new Texture("menus/400x300_button_hovered.png");
        Texture sizeButton400TextureClicked = new Texture("menus/400x300_button_pressed.png");
        float sizeButtonSpacing = 0.04f;
        float sizeButtonHeight = 0.05f;
        float sizeButtonY = 0.7f;
        float sizeButtonWidth = 115f / 25 * sizeButtonHeight / Runner.ASPECT_RATIO;
        float sizeButton400X = 0.5f * (1 - 4 * sizeButtonWidth - 3 * sizeButtonSpacing);
        WidthButton widthButton400 = new WidthButton(sizeButton400X, sizeButtonY, sizeButtonWidth, sizeButtonHeight, "sizeButton400", sizeButton400TextureIdle, sizeButton400TextureHovered, sizeButton400TextureClicked, 400);
        menu.addButton(widthButton400);
        
        // Window Size Button 800
        Texture sizeButton800TextureIdle = new Texture("menus/800x600_button.png");
        Texture sizeButton800TextureHovered = new Texture("menus/800x600_button_hovered.png");
        Texture sizeButton800TextureClicked = new Texture("menus/800x600_button_pressed.png");
        float sizeButton800X = sizeButton400X + sizeButtonWidth + sizeButtonSpacing;
        WidthButton widthButton800 = new WidthButton(sizeButton800X, sizeButtonY, sizeButtonWidth, sizeButtonHeight, "sizeButton800", sizeButton800TextureIdle, sizeButton800TextureHovered, sizeButton800TextureClicked, 800);
        menu.addButton(widthButton800);
        
        // Window Size Button 1200
        Texture sizeButton1200TextureIdle = new Texture("menus/1200x900_button.png");
        Texture sizeButton1200TextureHovered = new Texture("menus/1200x900_button_hovered.png");
        Texture sizeButton1200TextureClicked = new Texture("menus/1200x900_button_pressed.png");
        float sizeButton1200X = sizeButton800X + sizeButtonWidth + sizeButtonSpacing;
        WidthButton widthButton1200 = new WidthButton(sizeButton1200X, sizeButtonY, sizeButtonWidth, sizeButtonHeight, "sizeButton1200", sizeButton1200TextureIdle, sizeButton1200TextureHovered, sizeButton1200TextureClicked, 1200);
        menu.addButton(widthButton1200);
        
        // Window Size Button 1600
        Texture sizeButton1600TextureIdle = new Texture("menus/1600x1200_button.png");
        Texture sizeButton1600TextureHovered = new Texture("menus/1600x1200_button_hovered.png");
        Texture sizeButton1600TextureClicked = new Texture("menus/1600x1200_button_pressed.png");
        float sizeButton1600X = sizeButton1200X + sizeButtonWidth + sizeButtonSpacing;
        WidthButton widthButton1600 = new WidthButton(sizeButton1600X, sizeButtonY, sizeButtonWidth, sizeButtonHeight, "sizeButton1600", sizeButton1600TextureIdle, sizeButton1600TextureHovered, sizeButton1600TextureClicked, 1600);
        menu.addButton(widthButton1600);
        
        // Home Button
        Texture homeButtonTextureIdle = new Texture("menus/home_button.png");
        Texture homeButtonTextureHovered = new Texture("menus/home_button_hovered.png");
        Texture homeButtonTextureClicked = new Texture("menus/home_button_pressed.png");
        float homeButtonWidth = 0.05f;
        float homeButtonCornerOffsetX = 0.05f;
        float homeButtonCornerOffsetY = homeButtonCornerOffsetX * Runner.ASPECT_RATIO;
        float homeButtonHeight = homeButtonWidth * Runner.ASPECT_RATIO;
        float homeButtonX = homeButtonCornerOffsetX;
        float homeButtonY = 1 - homeButtonCornerOffsetY - homeButtonHeight;
        HomeButton homeButton = new HomeButton(homeButtonX, homeButtonY, homeButtonWidth, homeButtonHeight, "homeButton", homeButtonTextureIdle, homeButtonTextureHovered, homeButtonTextureClicked);
        menu.addButton(homeButton);
        
        Game.setCurrentManager("options");
        Game.setCurrentMenu("options");
    }
    
}
