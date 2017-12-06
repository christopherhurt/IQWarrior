package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import main.Game;
import main.Manager;
import main.Menu;
import main.MenuButton;
import main.MenuLabel;
import main.SoundBank;
import main.Texture;
import main.TexturedGameObject;

//Controls page button, Items page button

public class HelpMenu {
    private static final float TEXT_SIZE = .025f;
    private static final float TEXT_SIZE2 = .018f;
    private static final float ESC_LABEL_X = .026f;
    private static final float ESC_LABEL_Y = .36f;
    
    private static final float W_X = .275f;
    private static final float W_Y = .46f;
    
    private static final float A_X = .11f;
    private static final float A_Y = .8f;
    
    private static final float S_X = .257f;
    private static final float S_Y = .8f;
    
    private static final float D_X = .42f;
    private static final float D_Y = .8f;
    
    private static final float LEFT_CLICK_X = .68f;
    private static final float LEFT_CLICK_Y = .36f;
    
    private static final float RIGHT_CLICK_X = .825f;
    private static final float RIGHT_CLICK_Y = .36f;
	
    private static final float TITLE_LABEL_HEIGHT = .1f;    
    private static final float TITLE_LABEL_WIDTH = .6f;
    private static final float TITLE2_LABEL_WIDTH = .37f;
    private static final float TITLE_LABEL_X = (1 - TITLE_LABEL_WIDTH) / 2f;
    private static final float TITLE2_LABEL_X = (1 - TITLE2_LABEL_WIDTH) / 2f;
    private static final float TITLE_LABEL_Y = .1f;
    
    private static final float WASD_X = .2f;
    private static final float WASD_Y = .5f;
    private static final float WASD_WIDTH = .25f;
    private static final float WASD_HEIGHT = .23f;
    
    private static final float MOUSE_X = .7f;
    private static final float MOUSE_Y = .4f;
    private static final float MOUSE_WIDTH = .23f;
    private static final float MOUSE_HEIGHT = .4f;
    
    private static final float ESC_X = .075f;
    private static final float ESC_Y = .4f;
    private static final float ESC_WIDTH = .075f;
    private static final float ESC_HEIGHT = .1f;
    
    private static final float HOME_X = .025f;
    private static final float HOME_Y = .875f;
    private static final float HOME_HEIGHT = .1f;    
    private static final float HOME_WIDTH = HOME_HEIGHT * 3 / 4;
    
    private static final float BUTTON_HEIGHT = .1f;    
    private static final float BUTTON_WIDTH = .35f;
    
    private static final float CONTROLS_X = .25f;
    private static final float CONTROLS_Y = .875f;
    
    private static final float ITEMS_X = .625f;
    private static final float ITEMS_Y = .875f;
    
    private static final float LABEL_X = .055f;    
    private static final float LABEL_HEIGHT = .1f;    
    private static final float LABEL_WIDTH = .1f * 3 / 4;
    private static final float LABEL_X2 = .60f;
    
    private static final float LABEL_SEPARATION = .2f;
    private static final float HAMMER_LABEL_Y = .4f - LABEL_HEIGHT * 3 / 4; 
    private static final float DEODERANT_LABEL_Y = HAMMER_LABEL_Y + LABEL_SEPARATION;
    private static final float DUMBBELL_LABEL_Y = DEODERANT_LABEL_Y + LABEL_SEPARATION;
    private static final float LATTE_LABEL_Y = .4f - LABEL_HEIGHT * 3 / 4;
    private static final float VAPE_LABEL_Y = LATTE_LABEL_Y + LABEL_SEPARATION;
    private static final float GOLDEN_FEDORA_LABEL_Y = VAPE_LABEL_Y + LABEL_SEPARATION;
    
    private static final float TITLE_X = LABEL_X;
    private static final float TITLE_X2 = LABEL_X2;
    private static final float LABEL_TITLE_SEPARATION = -.01f;
    private static final float HAMMER_TITLE_Y = HAMMER_LABEL_Y + LABEL_TITLE_SEPARATION;
    private static final float DEODERANT_TITLE_Y = DEODERANT_LABEL_Y + LABEL_TITLE_SEPARATION;
    private static final float DUMBBELL_TITLE_Y = DUMBBELL_LABEL_Y  + LABEL_TITLE_SEPARATION;
    private static final float LATTE_TITLE_Y = LATTE_LABEL_Y + LABEL_TITLE_SEPARATION;
    private static final float VAPE_TITLE_Y = VAPE_LABEL_Y + LABEL_TITLE_SEPARATION;
    private static final float GOLDEN_FEDORA_TITLE_Y = GOLDEN_FEDORA_LABEL_Y + LABEL_TITLE_SEPARATION;
    
    private static final float DES_X = LABEL_X + .1f;
    private static final float DES_X2 = LABEL_X2 + .1f;
    private static final float LABEL_DES_SEPARATION = .08f;
    private static final float HAMMER_DES_Y = HAMMER_TITLE_Y + LABEL_DES_SEPARATION;
    private static final float DEODERANT_DES_Y = DEODERANT_TITLE_Y + LABEL_DES_SEPARATION;
    private static final float DUMBBELL_DES_Y = DUMBBELL_TITLE_Y  + LABEL_DES_SEPARATION;
    private static final float LATTE_DES_Y = LATTE_TITLE_Y + LABEL_DES_SEPARATION;
    private static final float VAPE_DES_Y = VAPE_TITLE_Y + LABEL_DES_SEPARATION;
    private static final float GOLDEN_FEDORA_DES_Y = GOLDEN_FEDORA_TITLE_Y + LABEL_DES_SEPARATION;
    
    private static Texture homeUnclicked;
    private static Texture homeHovered;
    private static Texture homeClicked;
    
    private Manager manager;
    private Manager manager2;
    
    public HelpMenu(Texture homeUnclicked, Texture homeHovered, Texture homeClicked)
    {
        HelpMenu.homeUnclicked = homeUnclicked;
        HelpMenu.homeHovered = homeHovered;
        HelpMenu.homeClicked = homeClicked;
        manager = new Manager("controls menu");
        manager2 = new Manager("items menu");
        constructControlsMenu();
        constructItemsMenu();
    }
    
    private void constructControlsMenu()
    {
        Menu menu = new Menu("controls menu");
               
        ButtonPlacer homeButton = new ButtonPlacer(HOME_X, HOME_Y, HOME_WIDTH, HOME_HEIGHT, homeUnclicked, homeHovered, homeClicked, "home", menu);
        
        Texture controls = new Texture("menus/controls_button.png");
        Texture controlsHovered = new Texture("menus/controls_button_hovered.png");
        Texture controlsPressed = new Texture("menus/controls_button_pressed.png");
        ButtonPlacer controlsButton = new ButtonPlacer(CONTROLS_X, CONTROLS_Y, BUTTON_WIDTH, BUTTON_HEIGHT, controls, controlsHovered, controlsPressed, "controls", menu);
        
        Texture items = new Texture("menus/items_button.png");
        Texture itemsHovered = new Texture("menus/items_button_hovered.png");
        Texture itemsPressed = new Texture("menus/items_button_pressed.png");
        ButtonPlacer itemsButton = new ButtonPlacer(ITEMS_X, ITEMS_Y, BUTTON_WIDTH, BUTTON_HEIGHT, items, itemsHovered, itemsPressed, "items", menu);
        
        MenuLabel escLabel = new MenuLabel("Pause/Unpause", "arial", 1, TEXT_SIZE, Color.WHITE, ESC_LABEL_X, ESC_LABEL_Y, "esc label");
        
        MenuLabel w = new MenuLabel("Move Up", "arial", 1, TEXT_SIZE, Color.WHITE, W_X, W_Y, "w");
        
        MenuLabel a = new MenuLabel("Move Left", "arial", 1, TEXT_SIZE, Color.WHITE, A_X, A_Y, "a");
        
        MenuLabel s = new MenuLabel("Move Down", "arial", 1, TEXT_SIZE, Color.WHITE, S_X, S_Y, "s");
        
        MenuLabel d = new MenuLabel("Move Right", "arial", 1, TEXT_SIZE, Color.WHITE, D_X, D_Y, "d");
        
        MenuLabel leftClick = new MenuLabel("Throw Hat", "arial", 1, TEXT_SIZE, Color.WHITE, LEFT_CLICK_X, LEFT_CLICK_Y, "left click");
        
        MenuLabel rightClick = new MenuLabel("Use Powerup", "arial", 1, TEXT_SIZE, Color.WHITE, RIGHT_CLICK_X, RIGHT_CLICK_Y, "right click");
                
        menu.addButton(homeButton);
        menu.addButton(controlsButton);
        menu.addButton(itemsButton);
        menu.addLabel(escLabel);
        menu.addLabel(w);
        menu.addLabel(a);
        menu.addLabel(s);
        menu.addLabel(d);
        menu.addLabel(leftClick);
        menu.addLabel(rightClick);
        
        constructControlsManager();
    }
    
    private void constructItemsMenu()
    {
    	Menu menu2 = new Menu("items menu");
    	
    	ButtonPlacer homeButton = new ButtonPlacer(HOME_X, HOME_Y, HOME_WIDTH, HOME_HEIGHT, homeUnclicked, homeHovered, homeClicked, "home", menu2);
        
        Texture controls = new Texture("menus/controls_button.png");
        Texture controlsHovered = new Texture("menus/controls_button_hovered.png");
        Texture controlsPressed = new Texture("menus/controls_button_pressed.png");
        ButtonPlacer controlsButton = new ButtonPlacer(CONTROLS_X, CONTROLS_Y, BUTTON_WIDTH, BUTTON_HEIGHT, controls, controlsHovered, controlsPressed, "controls", menu2);
        
        Texture items = new Texture("menus/items_button.png");
        Texture itemsHovered = new Texture("menus/items_button_hovered.png");
        Texture itemsPressed = new Texture("menus/items_button_pressed.png");
        ButtonPlacer itemsButton = new ButtonPlacer(ITEMS_X, ITEMS_Y, BUTTON_WIDTH, BUTTON_HEIGHT, items, itemsHovered, itemsPressed, "items", menu2);
        
        MenuLabel hammerTitle = new MenuLabel("Hammer", "arial", 1, TEXT_SIZE, Color.WHITE, TITLE_X - .002f, HAMMER_TITLE_Y, "hammer title");
        MenuLabel deoderantTitle = new MenuLabel("Deoderant", "arial", 1, TEXT_SIZE, Color.WHITE, TITLE_X - .02f, DEODERANT_TITLE_Y, "deoderant title");
        MenuLabel dumbbellTitle = new MenuLabel("Dumbbell", "arial", 1, TEXT_SIZE, Color.WHITE, TITLE_X - .015f, DUMBBELL_TITLE_Y, "dumbbell title");
        MenuLabel latteTitle = new MenuLabel("Latte", "arial", 1, TEXT_SIZE, Color.WHITE, TITLE_X2 + .01f, LATTE_TITLE_Y, "latte title");
        MenuLabel vapeTitle = new MenuLabel("Vape", "arial", 1, TEXT_SIZE, Color.WHITE, TITLE_X2 + .0125f, VAPE_TITLE_Y, "vape title");
        MenuLabel goldenFedoraTitle = new MenuLabel("Golden Fedora", "arial", 1, TEXT_SIZE, Color.WHITE, TITLE_X2 - .045f, GOLDEN_FEDORA_TITLE_Y, "golden fedora title");
        
        MenuLabel hammerDes = new MenuLabel("Smash your brains in (-50 IQ)", "arial", 1, TEXT_SIZE2, Color.WHITE, DES_X, HAMMER_DES_Y, "hammer description");
        MenuLabel deoderantDes = new MenuLabel("Rub on the stench of mediocrity (-100 IQ)", "arial", 1, TEXT_SIZE2, Color.WHITE, DES_X, DEODERANT_DES_Y, "deoderant description");
        MenuLabel dumbbellDes = new MenuLabel("Sweat that intelligence (-150 IQ)", "arial", 1, TEXT_SIZE2, Color.WHITE, DES_X, DUMBBELL_DES_Y, "dumbbell description");
        MenuLabel latteDes = new MenuLabel("Attracts girls within a radius", "arial", 1, TEXT_SIZE2, Color.WHITE, DES_X2 , LATTE_DES_Y, "latte description");
        MenuLabel vapeDes = new MenuLabel("Vape away girls in a direction", "arial", 1, TEXT_SIZE2, Color.WHITE, DES_X2, VAPE_DES_Y, "vape description");
        MenuLabel goldenFedoraDes = new MenuLabel("Tip away all girls in sight", "arial", 1, TEXT_SIZE2, Color.WHITE, DES_X2, GOLDEN_FEDORA_DES_Y, "golden fedora description");
    	
    	menu2.addButton(homeButton);
        menu2.addButton(controlsButton);
        menu2.addButton(itemsButton);
        
        menu2.addLabel(hammerTitle);
        menu2.addLabel(deoderantTitle);
        menu2.addLabel(dumbbellTitle);
        menu2.addLabel(latteTitle);
        menu2.addLabel(vapeTitle);
        menu2.addLabel(goldenFedoraTitle);
        menu2.addLabel(hammerDes);
        menu2.addLabel(deoderantDes);
        menu2.addLabel(dumbbellDes);
        menu2.addLabel(latteDes);
        menu2.addLabel(vapeDes);
        menu2.addLabel(goldenFedoraDes);
        
    	constructItemsManager();
    }
    
    private void constructItemsManager()
    {
        Texture itemsTitle = new Texture("menus/items_text.png");
        TexturedGameObject itemsTitleLabel = new TexturedGameObject(TITLE2_LABEL_X, TITLE_LABEL_Y, 0f, 0f, 0f, 0f, TITLE2_LABEL_WIDTH, TITLE_LABEL_HEIGHT, "items title", itemsTitle);        
        Texture hammer = new Texture("menus/hammer.png");
        TexturedGameObject hammerLabel = new TexturedGameObject(LABEL_X, HAMMER_LABEL_Y, 0f, 0f, 0f, 0f, LABEL_WIDTH, LABEL_HEIGHT, "hammer label", hammer);  
        Texture deoderant = new Texture("menus/deoderant.png");
        TexturedGameObject deoderantLabel = new TexturedGameObject(LABEL_X, DEODERANT_LABEL_Y, 0f, 0f, 0f, 0f, LABEL_WIDTH, LABEL_HEIGHT, "deoderant label", deoderant);
        Texture dumbbell = new Texture("menus/dumbbell.png");
        TexturedGameObject dumbbellLabel = new TexturedGameObject(LABEL_X, DUMBBELL_LABEL_Y, 0f, 0f, 0f, 0f, LABEL_WIDTH, LABEL_HEIGHT, "dumbbell label", dumbbell);
        Texture latte = new Texture("menus/latte.png");
        TexturedGameObject latteLabel = new TexturedGameObject(LABEL_X2, LATTE_LABEL_Y, 0f, 0f, 0f, 0f, LABEL_WIDTH, LABEL_HEIGHT, "latte label", latte);
        Texture vape = new Texture("menus/vape.png");
        TexturedGameObject vapeLabel = new TexturedGameObject(LABEL_X2, VAPE_LABEL_Y, 0f, 0f, 0f, 0f, LABEL_WIDTH, LABEL_HEIGHT, "vape label", vape);
        Texture goldenFedora = new Texture("menus/golden_fedora.png");
        TexturedGameObject goldenFedoraLabel = new TexturedGameObject(LABEL_X2, GOLDEN_FEDORA_LABEL_Y, 0f, 0f, 0f, 0f, LABEL_WIDTH, LABEL_HEIGHT, "golden fedora label", goldenFedora);
                
        manager2.addObject(itemsTitleLabel);
        manager2.addObject(hammerLabel);
        manager2.addObject(deoderantLabel);
        manager2.addObject(dumbbellLabel);
        manager2.addObject(latteLabel);
        manager2.addObject(vapeLabel);
        manager2.addObject(goldenFedoraLabel);
    } 
    
    private void constructControlsManager()
    {
    	Texture title = new Texture("menus/controls_text.png");
        TexturedGameObject titleLabel = new TexturedGameObject(TITLE_LABEL_X, TITLE_LABEL_Y, 0f, 0f, 0f, 0f, TITLE_LABEL_WIDTH, TITLE_LABEL_HEIGHT, "controls title", title);
        
        Texture wasd = new Texture("menus/wasd.png");
        TexturedGameObject wasdObject = new TexturedGameObject(WASD_X, WASD_Y, 0f, 0f, 0f, 0f, WASD_WIDTH, WASD_HEIGHT, "wasd", wasd);
        
        Texture mouse = new Texture("menus/mouse.png");
        TexturedGameObject mouseObject = new TexturedGameObject(MOUSE_X, MOUSE_Y, 0f, 0f, 0f, 0f, MOUSE_WIDTH, MOUSE_HEIGHT, "mouse", mouse);
        
        Texture esc = new Texture("menus/esc.png");
        TexturedGameObject escObject = new TexturedGameObject(ESC_X, ESC_Y, 0f, 0f, 0f, 0f, ESC_WIDTH, ESC_HEIGHT, "esc", esc);
              
        manager.addObject(titleLabel);
        manager.addObject(wasdObject);
        manager.addObject(mouseObject);
        manager.addObject(escObject);
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
                if (!menu.getButton("home").isHeld() && !menu.getButton("controls").isHeld() && !menu.getButton("items").isHeld())
                {
                    texture = hovered;
                }          
            }
            else if (isHeld())
            {
                texture = clicked;
                pressed = true;
            }
            else if (isReleased())
            {
            	texture = unclicked;
            	pressed = false;
            }
            g.drawImage(texture.getImage(), (int)(getX() * width), (int)(getY() * height), (int)(getWidth() * width), (int)(getHeight() * height), null);  
        }
        
        @Override
        public void update()
        {
            if (isReleased() && pressed == true)
            {
                if (getID().equals("controls"))
                {
                    SoundBank.getSound("mouseClick").play();
                	Game.setCurrentMenu("controls menu");
                    Game.setCurrentManager("controls menu");
                }
                else if (getID().equals("items"))
                {
                    SoundBank.getSound("mouseClick").play();
                	Game.setCurrentMenu("items menu");
                    Game.setCurrentManager("items menu");
                }
                else if (getID().equals("home"))
                {
                    SoundBank.getSound("mouseClick").play();
                	Game.setCurrentManager("main menu");
                    Game.setCurrentMenu("main menu");
                }
            }
        }
          
    }

}
