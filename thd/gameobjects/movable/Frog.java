package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * The Frog class represents the scorpion entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #updatePosition()
 */
public class Frog extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry>, Enemy {
    private enum JumpingState {
        JUMPING1("frog_right_1.png", 0), JUMPING2("frog_right_2.png", 30), JUMPING3("frog_right_2.png", 50), JUMPING4("frog_right_2.png", 0), JUMPING5("frog_right_2.png", 0), JUMPING6("frog_right_2.png", 0), JUMPING7("frog_right_2.png", -50), JUMPING8("frog_right_2.png", -30), JUMPING9("frog_right_2.png", 0), JUMPING10("frog_left_1.png", 0), JUMPING11("frog_left_2.png", 30), JUMPING12("frog_left_2.png", 50), JUMPING13("frog_left_2.png", 0), JUMPING14("frog_left_2.png", 0), JUMPING15("frog_left_2.png", -50), JUMPING16("frog_left_2.png", -30), JUMPING17("frog_left_2.png", 0);
        private final String display;
        private final double up;

        JumpingState(String display, double up) {
            this.display = display;
            this.up = up;
        }
    }

    private String png;
    private JumpingState jumpingState;
    private final StateSwitch<JumpingState> jumpingStateSwitch;

    /**
     * Creates Frog.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Frog(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        jumpingState = JumpingState.JUMPING1;
        jumpingStateSwitch = new StateSwitch<>(JumpingState.JUMPING1, JumpingState.values());

        size = 1.5;
        speedInPixel = 3;
        width = 7.0;
        height = 10.0;
        hitBoxOffsets(20, 10, width, height);
        distanceToBackground = '5';
    }

    @Override
    public void updateStatus() {
        if (allowedToUpdatePosition()) {
            png = jumpingState.display;
            if (gameView.timer(100, this)) {
                if (isResting()) {
                    if (gameView.timer(200, this)) {
                        upwardsMovement();
                        jumpingState = jumpingStateSwitch.switchState();
                    }
                } else {
                    upwardsMovement();
                    jumpingState = jumpingStateSwitch.switchState();
                }

            }
        }
    }

    private void upwardsMovement() {
        position.up(jumpingState.up);
        if (jumpingState.ordinal() < 8) {
            position.right(20);
        } else if (jumpingState.ordinal() > 8) {
            position.left(20);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Scorpion: " + position;
    }


    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(png, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }

    private boolean isResting() {
        return jumpingState == JumpingState.JUMPING1 || jumpingState == JumpingState.JUMPING10;
    }
}
