package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Enemy;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.FloorUpperPart;

/**
 * The Apple class represents the apple entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects.base.Position
 * @see #addToCanvas()
 * @see #updatePosition()
 */
public class Apple extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private final CollidingGameObject referenceObject;


    /**
     * Creates Apple.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param referenceObject Import Object which will be followed
     */
    public Apple(GameView gameView, GamePlayManager gamePlayManager, CollidingGameObject referenceObject) {
        super(gameView, gamePlayManager);
        this.referenceObject = referenceObject;
        size = 1.2;
        speedInPixel = 2;
        width = 10.0;
        height = 10.0;
        hitBoxOffsets(8, 4, width, height);
        distanceToBackground = '5';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof FloorUpperPart) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public String toString() {
        return "Apple: " + position;
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            position.moveToPosition(referenceObject.getPosition(), speedInPixel);
            position.down(speedInPixel);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("apple.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }
}
