package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The FloorLowerPart class represents the FloorUpperPart.
 *
 * @author Patrick Sterneder
 */
public class FloorLowerPart extends CollidingGameObject implements ShiftableGameObject {


    /**
     * Creates FloorUpperPart Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public FloorLowerPart(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.0;
        position.updateCoordinates(0, 490);
        hitBoxOffsets(0, 40, 0, 0);
        distanceToBackground = '7';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "FloorLowerPart: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("floor_2.png", position.getX(), position.getY(), size, rotation);
    }
}
