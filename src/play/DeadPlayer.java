package play;

import java.util.ArrayList;
import java.util.List;
import main.AnimatedGameObject;
import main.Animation;
import main.Game;
import main.LerpedFloat;
import main.Manager;
import main.MenuLabel;
import main.Texture;
import main.TexturedGameObject;
import menus.HighScoreMenu;
import menus.Settings;

public class DeadPlayer extends AnimatedGameObject {
    
    private static final float DEATH_TIME = 2.0f;
    
    private Manager manager;
    private boolean menuSwitched;
    private LerpedFloat deathTimer;
    private PlayLevelController controller;
    
    public DeadPlayer(float x, float y, float vX, float vY, float aX, float aY, float w, float h, String id, Animation animation, Manager manager, PlayLevelController controller) {
        super(x, y, vX, vY, aX, aY, w, h, id, animation);
        this.manager = manager;
        this.controller = controller;
        menuSwitched = false;
        deathTimer = new LerpedFloat(0, 1, DEATH_TIME);
        deathTimer.start();
    }
    
    @Override
    public void update(){
        if(!menuSwitched && deathTimer.isFinished()){
            Texture gameOverTexture = new Texture("menus/game_over_message.png");
            float gameOverSize = 0.5f;
            float gameOverCorner = 0.5f - gameOverSize / 2;
            TexturedGameObject gameOver = new TexturedGameObject(gameOverCorner, gameOverCorner, 0, 0, 0, 0, gameOverSize, gameOverSize, "game over display", gameOverTexture);
            gameOver.setCameraAffected(false);
            manager.addObject(gameOver);
            MenuLabel timeAliveLabel = controller.getTimeAliveLabel();
            String text = timeAliveLabel.getText().substring(13, 18);
            Game.getMenu("gameOver").getLabel("scoreLabel").setText(text);
            Game.setCurrentMenu("gameOver");
            menuSwitched = true;
            int seconds = 60 * Integer.parseInt(text.substring(0, 2)) + Integer.parseInt(text.substring(3, 5));
            int[] highScores = Settings.getHighScores();
            List<Integer> scores = new ArrayList<>();
            for(int i = 0; i < 5; i++){
                scores.add(highScores[i]);
            }
            for(int i = 0; i < scores.size(); i++){
                if(seconds > scores.get(i)){
                    scores.add(i, seconds);
                    break;
                }
            }
            for(int i = 0; i < 5; i++){
                highScores[i] = scores.get(i);
            }
            Settings.writeHighScores();
            HighScoreMenu.setHighScore(highScores);
        }
    }
    
}
