package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The StartSign class represents the key.
 *
 * @author Patrick Sterneder
 */
public class StartSign extends CollidingGameObject implements ShiftableGameObject {
    /**
     * Startposition of Start sign.
     */
    public Position startPosition;

    /**
     * Creates start sign Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public StartSign(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(0, 370);
        startPosition = new Position(getPosition());
        size = 1.0;
        //hitbox
        width = 17.0;
        height = 100.0;
        hitBoxOffsets(0, -50, width, height);
        distanceToBackground = '4';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "StartSign: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("start_sign.png", position.getX(), position.getY(), size, rotation);
    }

}
