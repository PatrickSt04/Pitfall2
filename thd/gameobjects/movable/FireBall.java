package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.FloorUpperPart;

/**
 * The FireBall class represents the fireball entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #updatePosition()
 */
public class FireBall extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private final StraightMovementPattern straightMovementPattern;
    private final boolean jumping;

    /**
     * Creates FireBall.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param jumping         Import Boolean
     */
    public FireBall(GameView gameView, GamePlayManager gamePlayManager, boolean jumping) {
        super(gameView, gamePlayManager);
        this.jumping = jumping;
        straightMovementPattern = new StraightMovementPattern(360);
        if (jumping) {
            position.updateCoordinates(straightMovementPattern.startPosition());
            targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
            speedInPixel = 2;
        } else {
            speedInPixel = 5;
        }
        size = 1;
        rotation = 2;
        //hitbox
        width = 10.0;
        height = 10.0;
        hitBoxOffsets(4, 10, width, height);
        distanceToBackground = '5';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (!jumping && other instanceof FloorUpperPart) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public String toString() {
        return "FireBall: " + position;
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            if (jumping) {
                position.up(Math.round(50 * Math.sin(Math.toRadians(position.getX()))));
                if (position.similarTo(targetPosition)) {
                    position.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
                    targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
                }
                position.moveToPosition(targetPosition, speedInPixel);
                position.down(Math.round(50 * Math.sin(Math.toRadians(position.getX()))));
            } else {
                position.down(2);
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("fireball.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }
}
