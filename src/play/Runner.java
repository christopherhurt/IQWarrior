package play;

import java.awt.Color;
import main.Game;
import main.Manager;
import main.Menu;
import main.MenuLabel;
import main.Sound;
import main.SoundBank;
import main.Texture;
import menus.HelpMenu;
import menus.HighScoreMenu;
import menus.HomeButton;
import menus.MainMenu;
import menus.RestartButton;
import menus.Settings;

public class Runner {
    
    public static final float ASPECT_RATIO = 4.0f / 3;
    public static final int FPS = 60;
    
    public static final Texture homeButton = new Texture("menus/home_button.png");
    public static final Texture homeButtonHovered = new Texture("menus/home_button_hovered.png");
    public static final Texture homeButtonPressed = new Texture("menus/home_button_pressed.png");
    public static final Sound MOUSE_CLICK = new Sound("mouseClick", "sounds/mouseClickSound.wav", false);
    public static final MenuLabel scoreLabel = new MenuLabel("00:00", "arial", 1, 0.05f, Color.WHITE, 0.43f, 0.5f - 0.05f / 2, "scoreLabel");
    public static final Manager playManager = new Manager("play");
    
    public static void main(String[] args){
        Game.setResourcesFolder("");
        Settings.readOptions();
        Settings.readHighScores();
        
        new MainMenu(new Texture("menus/play_button.png"), 
            new Texture("menus/play_button_hovered.png"),
            new Texture("menus/play_button_pressed.png"),
            new Texture("menus/high_score_button.png"),
            new Texture("menus/high_score_button_hovered.png"),
            new Texture("menus/high_score_button_pressed.png"),
            new Texture("menus/options_button.png"),
            new Texture("menus/options_button_hovered.png"),
            new Texture("menus/options_button_pressed.png"),
            new Texture("menus/help_button.png"),
            new Texture("menus/help_button_hovered.png"),
            new Texture("menus/help_button_pressed.png"),
            new Texture("menus/quit_button.png"),
            new Texture("menus/quit_button_hovered.png"),
            new Texture("menus/quit_button_pressed.png"));
        new HighScoreMenu(homeButton, homeButtonHovered, homeButtonPressed);
        
        // Help Menu
        new HelpMenu(homeButton, homeButtonHovered, homeButtonPressed);
        
        // Play Menu and Manager
        new Menu("play");
        
        // Options Menu and Manager
        new Manager("options");
        new Menu("options");
        
        // Game Over Menu
        Menu gameOverMenu = new Menu("gameOver");
        float homeButtonWidth = 0.05f;
        float homeButtonOffset = 0.272f;
        float homeButtonHeight = homeButtonWidth * ASPECT_RATIO;
        float homeButtonX = 1 - homeButtonOffset - homeButtonWidth;
        float homeButtonY = 1 - homeButtonOffset - homeButtonHeight;
        HomeButton homeButton = new HomeButton(homeButtonX, homeButtonY, homeButtonWidth, homeButtonHeight, "game over home button", Runner.homeButton, homeButtonHovered, homeButtonPressed);
        gameOverMenu.addButton(homeButton);
        float restartButtonSpacing = 0.03f;
        float restartButtonX = homeButtonX - restartButtonSpacing - homeButtonWidth;
        RestartButton restartButton = new RestartButton(restartButtonX, homeButtonY, homeButtonWidth, homeButtonHeight, "game over restart button", new Texture("menus/restart_button.png"), new Texture("menus/restart_button_hovered.png"), new Texture("menus/restart_button_pressed.png"));
        gameOverMenu.addButton(restartButton);
        gameOverMenu.addLabel(scoreLabel);
        
        int windowWidth = Settings.getWindowWidth();
        
        Sound menuMusic = new Sound("menu music", "sounds/menuMusic.wav", true);
        menuMusic.play();
        SoundBank.setVolume(Settings.getMusicVolume(), true);
        SoundBank.setVolume(Settings.getSFXVolume(), false);
        HighScoreMenu.setHighScore(Settings.getHighScores());
        
        Game.setCurrentManager("main menu");
        Game.setCurrentMenu("main menu");
        
        Game.start(windowWidth, (int)(windowWidth / ASPECT_RATIO), "IQ Warrior", "textures/icon.png", FPS, Color.DARK_GRAY);
    }
    
}
