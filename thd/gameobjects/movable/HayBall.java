package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * The HayBall class represents the hayball entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #updatePosition()
 * @see #HayBall(GameView, GamePlayManager)
 */
public class HayBall extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private final StraightMovementPattern straightMovementPattern;

    /**
     * Creates Hayball.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public HayBall(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        straightMovementPattern = new StraightMovementPattern(360);
        position.updateCoordinates(straightMovementPattern.startPosition());
        targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
        size = 0.5;
        speedInPixel = 2;
        rotation = 2;
        //hitbox
        width = 5.0;
        height = 5.0;
        hitBoxOffsets(15, 5, width, height);
        distanceToBackground = '5';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Hayball: " + position;
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            position.up(Math.round(50 * Math.sin(Math.toRadians(position.getX()))));
            if (position.similarTo(targetPosition)) {
                //targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
                position.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
                targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
            }
            position.moveToPosition(targetPosition, speedInPixel);
            position.down(Math.round(50 * Math.sin(Math.toRadians(position.getX()))));
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("hayball.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }
}
