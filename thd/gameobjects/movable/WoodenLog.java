package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;


/**
 * The WoodenLog class represents the log entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #updatePosition()
 */
public class WoodenLog extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private final StraightMovementPattern straightMovementPattern;

    private enum RollingState {
        ROLLING_1("wooden_log_1.png"), ROLLING_2("wooden_log_2.png"), ROLLING_3("wooden_log_3.png");
        private final String display;

        RollingState(String display) {
            this.display = display;
        }
    }

    private String png;
    private RollingState rollingState;
    private final StateSwitch<RollingState> rollingStateSwitch;

    /**
     * Creates WoodenLog.
     * Automatically sets scale.
     *
     * @param gameView         Import GameView
     * @param gamePlayManager  Import GamePlayManager
     * @param startCoordinateY Import start coordinate at y
     */
    public WoodenLog(GameView gameView, GamePlayManager gamePlayManager, double startCoordinateY) {
        super(gameView, gamePlayManager);
        rollingState = RollingState.ROLLING_1;
        rollingStateSwitch = new StateSwitch<>(RollingState.ROLLING_1, RollingState.values());

        straightMovementPattern = new StraightMovementPattern(startCoordinateY);
        position.updateCoordinates(straightMovementPattern.startPosition());
        targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
        size = 1.4;
        speedInPixel = 3;
        width = 10.0;
        height = 10.0;
        hitBoxOffsets(6, 8, width, height);
        distanceToBackground = '5';
    }

    @Override
    public void updateStatus() {
        png = rollingState.display;
        if (animationIsComlete()) {
            if (gameView.timer(80, this)) {
                resetStates();
            }
        }
        if (gameView.timer(80, this)) {
            rollingState = rollingStateSwitch.switchState();
        }
    }

    private void resetStates() {
        rollingState = RollingState.ROLLING_1;
    }

    private boolean animationIsComlete() {
        return rollingState == RollingState.values()[RollingState.values().length - 1];
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "WoodenLog: " + position;
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            targetPosition.updateCoordinates(targetPosition.getX(), position.getY());

            position.moveToPosition(targetPosition, speedInPixel);
            if (position.similarTo(targetPosition)) {
                position.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
                targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
            }
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(png, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }
}
