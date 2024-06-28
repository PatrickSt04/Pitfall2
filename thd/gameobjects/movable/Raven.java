package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * The Raven class represents the wooden log entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #toString()
 * @see #updatePosition()
 */
public class Raven extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private final StraightMovementPattern straightMovementPattern;

    private enum FlyLeftState {
        FLY_1("raven_fly_left_1.png"), FLY_2("raven_fly_left_2.png"), FLY_3("raven_fly_left_3.png"), FLY_4("raven_fly_left_4.png"), FLY_5("raven_fly_left_5.png"), FLY_6("raven_fly_left_6.png");
        private final String display;

        FlyLeftState(String display) {
            this.display = display;
        }
    }

    private FlyLeftState flyLeftState;
    private final StateSwitch<FlyLeftState> flyLeftStateSwitch;
    private String png;

    /**
     * Creates Raven.
     * automatically sets scale.
     *
     * @param gameView         Import GameView
     * @param gamePlayManager  Import GamePlayManager
     * @param startCoordinateY Import start coordinate at y
     */
    public Raven(GameView gameView, GamePlayManager gamePlayManager, double startCoordinateY) {
        super(gameView, gamePlayManager);
        flyLeftState = FlyLeftState.FLY_1;
        flyLeftStateSwitch = new StateSwitch<>(FlyLeftState.FLY_1, FlyLeftState.values());

        straightMovementPattern = new StraightMovementPattern(startCoordinateY);
        //Startpositon with straight movementpattern from right to left at middle of gameview
        position.updateCoordinates(straightMovementPattern.startPosition());
        //Targetposition
        targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
        speedInPixel = 3;
        size = 2.0;
        width = 20;
        height = 10;
        hitBoxOffsets(15, 24, width, height);
        distanceToBackground = '4';
    }

    @Override
    public void updateStatus() {
        png = flyLeftState.display;
        if (animationIsComlete()) {
            if (gameView.timer(1000, this)) {
                resetStates();
            }
        }
        if (gameView.timer(1000, this)) {
            flyLeftState = flyLeftStateSwitch.switchState();
        }
    }

    private boolean animationIsComlete() {
        return flyLeftState == FlyLeftState.values()[FlyLeftState.values().length - 1];
    }

    private void resetStates() {
        flyLeftState = FlyLeftState.FLY_1;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Raven: " + position;
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            position.down(2 * Math.round(Math.sin(Math.toRadians(position.getX() * 4))));
            if (position.similarTo(targetPosition)) {
                targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
            }
            position.moveToPosition(targetPosition, speedInPixel);
            position.up(2 * Math.round(Math.sin(Math.toRadians(position.getX() * 4))));
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("raven_fly_left_2.png", position.getX(), position.getY(), size, rotation);
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }
}
