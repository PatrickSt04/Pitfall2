package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.Rock;

/**
 * The Scorpion class represents the scorpion entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #updatePosition()
 */
public class Scorpion extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private final StraightMovementPattern straightMovementPattern;

    private enum WalkLeftState {
        WALK_1("scorpion_1.png"), WALK_2("scorpion_2.png");
        private final String display;

        WalkLeftState(String display) {
            this.display = display;
        }
    }

    private enum State {WALK_LEFT}

    private State currentState;
    private String png;
    private final StateSwitch<WalkLeftState> walkLeftStateSwitch;
    private WalkLeftState walkLeftState;

    /**
     * Creates Scorpion.
     * Automatically sets scale.
     *
     * @param gameView         Import GameView
     * @param gamePlayManager  Import GamePlayManager
     * @param startCoordinateY Import startCoordinate at Y
     */
    public Scorpion(GameView gameView, GamePlayManager gamePlayManager, double startCoordinateY) {
        super(gameView, gamePlayManager);
        walkLeftState = WalkLeftState.WALK_1;
        currentState = State.WALK_LEFT;
        walkLeftStateSwitch = new StateSwitch<>(WalkLeftState.WALK_1, WalkLeftState.values());
        straightMovementPattern = new StraightMovementPattern(startCoordinateY);
        //straightMovementPattern = new StraightMovementPattern(startCoordinateY);
        position.updateCoordinates(straightMovementPattern.startPosition());
        targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
        size = 1.5;
        speedInPixel = 3;
        width = 20.0;
        height = 15.0;
        hitBoxOffsets(20, 10, width, height);
        distanceToBackground = '5';
    }

    @Override
    public void updateStatus() {
        switch (currentState) {
            case WALK_LEFT -> {
                png = walkLeftState.display;
                if (animationIsComlete()) {
                    if (gameView.timer(80, this)) {
                        resetStates();
                    }
                }
                if (gameView.timer(80, this)) {
                    walkLeftState = walkLeftStateSwitch.switchState();
                }
            }
        }
    }

    private void resetStates() {
        walkLeftState = WalkLeftState.WALK_1;
    }

    private boolean animationIsComlete() {
        return walkLeftState == WalkLeftState.values()[WalkLeftState.values().length - 1];
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Rock) {
            targetPosition.updateCoordinates(straightMovementPattern.nextTargetPosition());
        }
    }

    @Override
    public String toString() {
        return "Scorpion: " + position;
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            position.moveToPosition(targetPosition, speedInPixel);
            if (position.similarTo(targetPosition)) {
                position.updateCoordinates(targetPosition);
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
