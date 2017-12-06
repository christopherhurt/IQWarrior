package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import main.AnimatedGameObject;
import main.Animation;
import main.Game;
import main.GameObject;
import main.Manager;
import main.Menu;
import main.MenuButton;
import main.MenuLabel;
import main.Sound;
import main.SoundBank;
import main.SpriteSheet;
import main.Texture;
import main.TexturedGameObject;
import play.Levels;

public class MainMenu
{          
    private static final float FATBOI_X = .405f;
    private static final float FATBOI_Y = .325f;
    private static final float FATBOI_WIDTH = .6f;
    private static final float FATBOI_HEIGHT = .8f;
    
    private static final float TITLE_X = .02f;
    private static final float TITLE_Y = .03f;
    private static final float TITLE_WIDTH = .96f;
    private static final float TITLE_HEIGHT = .2f;
    
    private static final float BUTTON_X = .055f;
    private static final float BUTTON_HEIGHT = .1f;    
    private static final float BUTTON_WIDTH = .35f;
    
    private static final float BUTTON_SEPARATION = .125f;
    private static final float PLAY_BUTTON_Y = .4f - BUTTON_HEIGHT * 3 / 4; 
    private static final float HIGH_SCORE_BUTTON_Y = PLAY_BUTTON_Y + BUTTON_SEPARATION;
    private static final float OPTIONS_BUTTON_Y = HIGH_SCORE_BUTTON_Y + BUTTON_SEPARATION;
    private static final float HELP_BUTTON_Y = OPTIONS_BUTTON_Y + BUTTON_SEPARATION;
    private static final float QUIT_BUTTON_Y = HELP_BUTTON_Y + BUTTON_SEPARATION;
    
    private static Texture playUnclicked;
    private static Texture playHovered;
    private static Texture playClicked;
    private static Texture highScoreUnclicked;
    private static Texture highScoreHovered;
    private static Texture highScoreClicked;
    private static Texture optionsUnclicked;
    private static Texture optionsHovered;
    private static Texture optionsClicked;
    private static Texture helpUnclicked;
    private static Texture helpHovered;
    private static Texture helpClicked;
    private static Texture quitUnclicked;
    private static Texture quitHovered;
    private static Texture quitClicked;
    
    private Manager manager;
    
    public MainMenu(Texture playUnclicked, Texture playHovered, Texture playClicked, Texture highScoreUnclicked, Texture highScoreHovered, Texture highScoreClicked, Texture optionsUnclicked, Texture optionsHovered, 
        Texture optionsClicked, Texture helpUnclicked, Texture helpHovered, Texture helpClicked, Texture quitUnclicked, Texture quitHovered, Texture quitClicked)
    {
        MainMenu.playUnclicked = playUnclicked;
        MainMenu.playHovered = playHovered;
        MainMenu.playClicked = playClicked;
        MainMenu.highScoreUnclicked = highScoreUnclicked;
        MainMenu.highScoreHovered = highScoreHovered;
        MainMenu.highScoreClicked = highScoreClicked;
        MainMenu.optionsUnclicked = optionsUnclicked;
        MainMenu.optionsHovered = optionsHovered;
        MainMenu.optionsClicked = optionsClicked;
        MainMenu.helpUnclicked = helpUnclicked;
        MainMenu.helpHovered = helpHovered;
        MainMenu.helpClicked = helpClicked;
        MainMenu.quitUnclicked = quitUnclicked;
        MainMenu.quitHovered = quitHovered;
        MainMenu.quitClicked = quitClicked;
        manager = new Manager("main menu");
        constructMenu();
    }
    
    public void constructMenu()
    {
        Menu menu = new Menu("main menu");
        
        SpriteSheet fatBoiMainMenu = new SpriteSheet("textures/fatBoiSpriteAction1.png", 8, 8);
        Texture t0 = fatBoiMainMenu.getTexture(0, 4);
        Texture t1 = fatBoiMainMenu.getTexture(1, 4);
        Texture t2 = fatBoiMainMenu.getTexture(2, 4);
        Texture t3 = fatBoiMainMenu.getTexture(3, 4);
        Animation fatBoiAnim = new Animation(.8f, t0, t0, t0, t0, t0, t0, t0, t0, t0, t0, t0, t0, t1, t2, t2, t2, t2, t2, t2, t2, t3);
        GameObject fatBoi = new AnimatedGameObject(FATBOI_X, FATBOI_Y, 0f, 0f, 0f, 0f, FATBOI_WIDTH, FATBOI_HEIGHT, "fatBoi", fatBoiAnim);
        
        Texture titleTexture = new Texture("menus/game_title.png");
        GameObject title = new TexturedGameObject(TITLE_X, TITLE_Y, 0f, 0f, 0f, 0f, TITLE_WIDTH, TITLE_HEIGHT, "title", titleTexture);
        
        ButtonPlacer playButton = new ButtonPlacer(BUTTON_X, PLAY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, playUnclicked, playHovered, playClicked, "play", menu);
        
        ButtonPlacer highScoreButton = new ButtonPlacer(BUTTON_X, HIGH_SCORE_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, highScoreUnclicked, highScoreHovered, highScoreClicked, "high score", menu);
        
        ButtonPlacer optionsButton = new ButtonPlacer(BUTTON_X, OPTIONS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, optionsUnclicked, optionsHovered, optionsClicked, "options", menu);
        
        ButtonPlacer helpButton = new ButtonPlacer(BUTTON_X, HELP_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, helpUnclicked, helpHovered, helpClicked, "help", menu);
        
        ButtonPlacer quitButton = new ButtonPlacer(BUTTON_X, QUIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT, quitUnclicked, quitHovered, quitClicked, "quit", menu);
        
        manager.addObject(fatBoi);
        manager.addObject(title);
        menu.addButton(playButton);
        menu.addButton(highScoreButton);
        menu.addButton(optionsButton);
        menu.addButton(helpButton);
        menu.addButton(quitButton);
    }  
    
    private class ButtonPlacer extends MenuButton
    {
        private Menu menu;
        
        private Texture unclicked;
        private Texture hovered;
        private Texture clicked;
        private Texture texture;
        private boolean pressed;
        
        public ButtonPlacer(float x, float y, float w, float h, Texture unclicked, Texture hovered, Texture clicked, String id, Menu menu) {
            super(new MenuLabel("", "arial", 1, .1f, Color.BLACK, .1f, .1f, "as"), x, y, w, h, Color.BLACK, Color.BLACK, Color.BLACK, 0, 0, id);
            this.menu = menu;
            this.unclicked = unclicked;
            this.hovered = hovered;
            this.clicked = clicked;
            texture = unclicked;
            pressed = false;
        }
        
        @Override
        public void render(Graphics2D g)
        {
            Dimension size = Game.getWindowSize();
            int width = (int)size.getWidth();
            int height = (int)size.getHeight();
            if (!isHovered())
            {
                pressed = false;
            }
            if (!isClicked() && !isHovered() && !isReleased())
            {
                texture = unclicked;
            }
            else if (!isClicked() && isHovered() && !isReleased())
            {
                if (!menu.getButton("play").isHeld() && !menu.getButton("high score").isHeld() && !menu.getButton("options").isHeld() 
                    && !menu.getButton("help").isHeld() && !menu.getButton("quit").isHeld())
                {
                    texture = hovered;
                }          
            }
            else if (isHeld())
            {
                texture = clicked;
                pressed = true;
            }
            g.drawImage(texture.getImage(), (int)(getX() * width), (int)(getY() * height), (int)(getWidth() * width), (int)(getHeight() * height), null);  
        }
        
        @Override
        public void update()
        {
            if (isReleased() && pressed == true)
            {
                if (getID().equals("play"))
                {
                    SoundBank.getSound("mouseClick").play();
                    Sound menuMusic = SoundBank.getSound("menu music");
                    menuMusic.stop();
                    Levels.buildPlayLevel(Game.getManager("play"), Game.getMenu("play"));
                }
                else if (getID().equals("high score"))
                {
                    SoundBank.getSound("mouseClick").play();
                    Game.setCurrentMenu("high score menu");
                    Game.setCurrentManager("high score menu");
                }
                else if (getID().equals("options"))
                {
                    SoundBank.getSound("mouseClick").play();
                    Menus.createOptionsMenu(Game.getManager("options"), Game.getMenu("options"));
                }
                else if (getID().equals("help"))
                {
                    SoundBank.getSound("mouseClick").play();
                    Game.setCurrentMenu("controls menu");
                    Game.setCurrentManager("controls menu");
                }
                else if (getID().equals("quit"))
                {
                    SoundBank.getSound("mouseClick").play();
                    SoundBank.deleteAll();
                    System.exit(0);
                }
            }
        }
    }
}
