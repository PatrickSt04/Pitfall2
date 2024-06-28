package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The FloorUpperPart class represents the FloorUpperPart.
 *
 * @author Patrick Sterneder
 */
public class FloorUpperPart extends CollidingGameObject implements ShiftableGameObject {


    /**
     * Creates FloorUpperPart Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public FloorUpperPart(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.0;
        position.updateCoordinates(0, 420);
        width = GameView.WIDTH;
        height = 1;
        hitBoxOffsets(0, 40, 0, 0);
        distanceToBackground = '2';

    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "FloorUpperPart: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("floor_1.png", position.getX(), position.getY(), size, rotation);
    }
}
