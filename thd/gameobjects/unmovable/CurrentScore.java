package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * The CurrentScore class represents the CurrentScore.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects.base.Position
 * @see java.awt
 * @see #addToCanvas()
 * @see #CurrentScore(GameView, GamePlayManager)
 * @see #toString()
 */
public class CurrentScore extends GameObject {
    private int points;

    /**
     * Creates CurrentScore Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public CurrentScore(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 30;
        width = 150;
        height = 33;
        position.updateCoordinates(300, -10);
        distanceToBackground = '2';
    }

    @Override
    public String toString() {
        return "CurrentScore: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(String.valueOf(points), position.getX(), position.getY(), size, true, Color.BLACK, rotation);
    }

    /**
     * Updates current points.
     *
     * @param points points that will be displayed
     */
    public void updatePoints(int points) {
        this.points = points;
    }

}
