package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * The Icon class represents the Icon at the top right.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects.base.Position
 * @see java.awt
 * @see #addToCanvas()
 * @see #Icon(GameView, GamePlayManager)
 * @see #toString()
 */
public class Icon extends GameObject {

    /**
     * Creates Icon Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Icon(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 0.15;
        position.updateCoordinates(GameView.WIDTH - 40, 1);
        distanceToBackground = '2';
    }

    @Override
    public String toString() {
        return "Icon: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("pitfall_icon.png", position.getX(), position.getY(), size, rotation);
    }

}
