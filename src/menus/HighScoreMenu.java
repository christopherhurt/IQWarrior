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
import main.SoundBank;
import main.SpriteSheet;
import main.Texture;
import main.TexturedGameObject;

/**
 * Record top scores 1 through 5
 * @author Jarod
 *
 */

public class HighScoreMenu {
    
	private static final float FATBOI_X = .65f;
	private static final float FATBOI_Y = .55f;
	private static final float FATBOI_HEIGHT = .55f;
	private static final float FATBOI_WIDTH = FATBOI_HEIGHT * 2f / 3f;
	 
    private static final float TITLE_LABEL_Y = .1f;
    private static final float TITLE_LABEL_HEIGHT = .1f;    
    private static final float TITLE_LABEL_WIDTH = .8f;
    private static final float TITLE_LABEL_X = .5f - TITLE_LABEL_WIDTH / 2;
    
    private static final float HOME_X = .025f;
    private static final float HOME_Y = .875f;
    private static final float HOME_HEIGHT = .1f;    
    private static final float HOME_WIDTH = HOME_HEIGHT * 3 / 4;
    
    private static final float BUTTON_X = .42f;  
    
    private static final float BUTTON_SEPARATION = .125f;
    private static final float SCORE1_BUTTON_Y = .36f; 
    private static final float SCORE2_BUTTON_Y = SCORE1_BUTTON_Y + BUTTON_SEPARATION;
    private static final float SCORE3_BUTTON_Y = SCORE2_BUTTON_Y + BUTTON_SEPARATION;
    private static final float SCORE4_BUTTON_Y = SCORE3_BUTTON_Y + BUTTON_SEPARATION;
    private static final float SCORE5_BUTTON_Y = SCORE4_BUTTON_Y + BUTTON_SEPARATION;
    private static final String SCORE_FONT_TYPE = "arial";
    private static final int SCORE_FONT_STYLE = 1;
    private static final float SCORE_FONT_SIZE = .05f;
    private static final Color SCORE_COLOR = Color.WHITE;
    
    private int score1;
    private int score2;
    private int score3;
    private int score4;
    private int score5;
    
    private static MenuLabel score1Label;
    private static MenuLabel score2Label;
    private static MenuLabel score3Label;
    private static MenuLabel score4Label;
    private static MenuLabel score5Label;
    
    private Texture title = new Texture("menus/high_score_text.png");
    
    private static Texture homeUnclicked;
    private static Texture homeHovered;
    private static Texture homeClicked;
    
    private Manager manager;
    
    public HighScoreMenu(Texture homeUnclicked, Texture homeHovered, Texture homeClicked)
    {
        HighScoreMenu.homeUnclicked = homeUnclicked;
        HighScoreMenu.homeHovered = homeHovered;
        HighScoreMenu.homeClicked = homeClicked;
        score1 = 0;
        score2 = 0;
        score3 = 0;
        score4 = 0;
        score5 = 0;
        manager = new Manager("high score menu");
        constructMenu();
    }
    
    public static void setHighScore(int[] scores)
    {
        score1Label.setText(scoreToString(scores[0]));
        score2Label.setText(scoreToString(scores[1]));
        score3Label.setText(scoreToString(scores[2]));
        score4Label.setText(scoreToString(scores[3]));
        score5Label.setText(scoreToString(scores[4]));
    }
    
    public void constructMenu()
    {
        Menu menu = new Menu("high score menu");
        
        TexturedGameObject titleLabel = new TexturedGameObject(TITLE_LABEL_X, TITLE_LABEL_Y, 0f, 0f, 0f, 0f, TITLE_LABEL_WIDTH, TITLE_LABEL_HEIGHT, "high score title", title);
        
        score1Label = new MenuLabel(scoreToString(score1), SCORE_FONT_TYPE, SCORE_FONT_STYLE, SCORE_FONT_SIZE, SCORE_COLOR, BUTTON_X, SCORE1_BUTTON_Y, "score 1");
        
        score2Label = new MenuLabel(scoreToString(score2), SCORE_FONT_TYPE, SCORE_FONT_STYLE, SCORE_FONT_SIZE, SCORE_COLOR, BUTTON_X, SCORE2_BUTTON_Y, "score 2");
        
        score3Label = new MenuLabel(scoreToString(score3), SCORE_FONT_TYPE, SCORE_FONT_STYLE, SCORE_FONT_SIZE, SCORE_COLOR, BUTTON_X, SCORE3_BUTTON_Y, "score 3");
        
        score4Label = new MenuLabel(scoreToString(score4), SCORE_FONT_TYPE, SCORE_FONT_STYLE, SCORE_FONT_SIZE, SCORE_COLOR, BUTTON_X, SCORE4_BUTTON_Y, "score 4");
        
        score5Label = new MenuLabel(scoreToString(score5), SCORE_FONT_TYPE, SCORE_FONT_STYLE, SCORE_FONT_SIZE, SCORE_COLOR, BUTTON_X, SCORE5_BUTTON_Y, "score 5");
        
        ButtonPlacer homeButton = new ButtonPlacer(HOME_X, HOME_Y, HOME_WIDTH, HOME_HEIGHT, homeUnclicked, homeHovered, homeClicked, "home", menu);
        
        SpriteSheet fatBoiMenu = new SpriteSheet("textures/fatBoiSpriteAction1.png", 8, 8);
        Texture t0 = fatBoiMenu.getTexture(0, 5);
        Texture t1 = fatBoiMenu.getTexture(1, 5);
        Texture t2 = fatBoiMenu.getTexture(2, 5);
        Texture t3 = fatBoiMenu.getTexture(3, 5);
        Texture t4 = fatBoiMenu.getTexture(4, 5);
        Texture t5 = fatBoiMenu.getTexture(5, 5);
        Animation fatBoiAnim = new Animation(.2f, t0, t1, t2, t3, t4, t5, t0, t0, t0, t0, t0, t0);
        GameObject fatBoi = new AnimatedGameObject(FATBOI_X, FATBOI_Y, 0f, 0f, 0f, 0f, FATBOI_WIDTH, FATBOI_HEIGHT, "fatBoi", fatBoiAnim);
        
        manager.addObject(fatBoi);
        manager.addObject(titleLabel);
        menu.addLabel(score1Label);
        menu.addLabel(score2Label);
        menu.addLabel(score3Label);
        menu.addLabel(score4Label);
        menu.addLabel(score5Label);
        menu.addButton(homeButton);
    } 
    
    public static String scoreToString(int score)
    {
        Integer scoreInt = new Integer(score);        
        Integer minute = new Integer(scoreInt / 60);
        Integer second = new Integer(scoreInt % 60);
        String minutes = Integer.toString(minute);
        String seconds = Integer.toString(second);
        if (minute < 10)
        {
            minutes = "0" + minutes;
        }
        if (second < 10)
        {
            seconds = "0" + seconds;
        }
        return minutes + ":" + seconds;
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
                if (!menu.getButton("home").isHeld())
                {
                    texture = hovered;
                }          
            }
            else if (isHeld())
            {
                texture = clicked;
                pressed = true;
            }
            else if (isReleased() && pressed == true)
            {
                
            }
            g.drawImage(texture.getImage(), (int)(getX() * width), (int)(getY() * height), (int)(getWidth() * width), (int)(getHeight() * height), null);  
        }
        
        @Override
        public void update()
        {
            if (isReleased() && pressed == true)
            {
                Game.setCurrentManager("main menu");
                Game.setCurrentMenu("main menu");
                SoundBank.getSound("mouseClick").play();
            }
        }
        
        
    }

}
