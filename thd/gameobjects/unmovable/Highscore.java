package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * The Highscore class represents the Highscore.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects.base.Position
 * @see java.awt
 * @see #addToCanvas()
 * @see #Highscore(GameView, GamePlayManager)
 * @see #toString()
 */
public class Highscore extends GameObject {
    /**
     * Score.
     */
    public int score;

    /**
     * Creates Highscore Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Highscore(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 30;
        width = 150;
        height = 33;
        position.updateCoordinates(GameView.WIDTH - (width + 10), -10);
        distanceToBackground = '2';
    }

    @Override
    public String toString() {
        return "Highscore: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(String.valueOf(score), position.getX() + 35, position.getY(), size, true, Color.BLACK, rotation);
    }

}
