package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * The Bat class represents the wooden bat entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #toString()
 * @see #updatePosition()
 */
public class Bat extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private final StraightMovementPattern straightMovementPattern;

    private enum State {FLY}

    private enum FlyLeftState {
        FLY_LEFT_1("bat_fly_left_1.png"), FLY_LEFT_2("bat_fly_left_2.png");
        private final String display;

        FlyLeftState(String display) {
            this.display = display;
        }
    }

    private State currentState;
    private FlyLeftState flyLeftState;
    private final StateSwitch<FlyLeftState> flyLeftStateSwitch;
    private String png;

    /**
     * Creates Bat.
     * automatically sets scale.
     *
     * @param gameView         Import GameView
     * @param gamePlayManager  Import GamePlayManager
     * @param startCoordinateY Import start coordinate at y
     */
    public Bat(GameView gameView, GamePlayManager gamePlayManager, double startCoordinateY) {
        super(gameView, gamePlayManager);
        flyLeftState = FlyLeftState.FLY_LEFT_1;
        currentState = State.FLY;
        flyLeftStateSwitch = new StateSwitch<>(FlyLeftState.FLY_LEFT_1, FlyLeftState.values());

        straightMovementPattern = new StraightMovementPattern(startCoordinateY);
        position.updateCoordinates(straightMovementPattern.startPosition());
        targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
        speedInPixel = 2;
        size = 1.3;
        width = 20;
        height = 10;
        hitBoxOffsets(15, 15, width, height);
        distanceToBackground = '4';
    }

    @Override
    public void updateStatus() {
        switch (currentState) {
            case FLY -> {
                png = flyLeftState.display;
                if (gameView.timer(120, this)) {
                    flyLeftState = flyLeftStateSwitch.switchState();
                }
            }
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Bat: " + position;
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            position.up(Math.round(10 * Math.sin(Math.toRadians(position.getX()))));
            if (position.similarTo(targetPosition)) {
                position.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
                targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition().getX(), position.getY());
            }
            position.moveToPosition(targetPosition, speedInPixel);
            position.down(Math.round(10 * Math.sin(Math.toRadians(position.getX()))));
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
