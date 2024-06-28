package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.ActivatableGameObject;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.base.StateSwitch;

import java.awt.*;

/**
 * The HayBall class represents the hayball entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects.base.Position
 * @see #addToCanvas()
 * @see #updatePosition()
 */
public class Liana extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry> {
    private enum SwingState {
        SWING_1("liane_1.png"), SWING_2("liane_2.png"), SWING_3("liane_3.png"), SWING_4("liane_4.png"), SWING_5("liane_5.png"), SWING_6("liane_4.png"), SWING_7("liane_3.png"), SWING_8("liane_2.png");
        private final String display;

        SwingState(String display) {
            this.display = display;
        }
    }

    private enum SwingStateOpposite {
        SWING_1("liane_5.png"), SWING_2("liane_4.png"), SWING_3("liane_3.png"), SWING_4("liane_2.png"), SWING_5("liane_1.png"), SWING_6("liane_2.png"), SWING_7("liane_3.png"), SWING_8("liane_4.png");
        private final String display;

        SwingStateOpposite(String display) {
            this.display = display;
        }
    }

    private String png;
    private SwingState swingState;
    private final StateSwitch<SwingState> stateSwitch;
    private SwingStateOpposite swingStateOpposite;
    private final StateSwitch<SwingStateOpposite> stateSwitchOpposite;
    final boolean swingOpposite;
    private static final double WIDTH = 25.0;
    private static final double HEIGHT = 20.0;

    /**
     * Creates Liana.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param swingOpposite   Import swingOpposite
     */
    public Liana(GameView gameView, GamePlayManager gamePlayManager, boolean swingOpposite) {
        super(gameView, gamePlayManager);
        this.swingOpposite = swingOpposite;
        swingStateOpposite = SwingStateOpposite.SWING_1;
        stateSwitchOpposite = new StateSwitch<>(SwingStateOpposite.SWING_1, SwingStateOpposite.values());
        swingState = SwingState.SWING_1;
        stateSwitch = new StateSwitch<>(SwingState.SWING_1, SwingState.values());

        size = 1.5;
        speedInPixel = 2;
        width = WIDTH;
        height = HEIGHT;
        hitBoxOffsets(-10, 200, width, height);
        distanceToBackground = '3';
    }

    @Override
    public void updateStatus() {
        if (swingOpposite) {
            png = swingStateOpposite.display;
            if (animationIsComlete()) {
                if (gameView.timer(700, this)) {
                    resetStates();
                }
            }
            if (gameView.timer(700, this)) {
                swingStateOpposite = stateSwitchOpposite.switchState();
            }
        } else {
            png = swingState.display;
            if (animationIsComlete()) {
                if (gameView.timer(700, this)) {
                    resetStates();
                }
            }
            if (gameView.timer(700, this)) {
                swingState = stateSwitch.switchState();
            }
        }
        tryToDisableHitbox();
    }

    private void tryToDisableHitbox() {
        if (swingOpposite) {
            if (swingStateOpposite.ordinal() > 5 || swingStateOpposite.ordinal() < 3) {
                disableHitbox();
            } else {
                resetHitboxValues();
            }
        } else {
            if (swingState.ordinal() >= 2) {
                disableHitbox();
            } else {
                resetHitboxValues();
            }
        }
    }

    private void disableHitbox() {
        width = 1;
        height = 1;
    }

    private void resetHitboxValues() {
        width = WIDTH;
        height = HEIGHT;
    }

    private boolean animationIsComlete() {
        return swingState == SwingState.values()[SwingState.values().length - 1];
    }

    private void resetStates() {
        swingState = SwingState.SWING_1;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "Liana: " + position;
    }


    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(png, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }

    /**
     * Updates harrys position according to the current swinging state of the liana.
     *
     * @param harry Character
     */
    void updatePositionForSwinging(PitfallHarry harry) {
        switch (swingState) {
            case SWING_1 -> harry.getPosition().updateCoordinates(position.getX() - 20, position.getY() + 160);
            case SWING_2, SWING_8 -> harry.getPosition().updateCoordinates(position.getX() - 10, position.getY() + 170);
            case SWING_3, SWING_7 -> harry.getPosition().updateCoordinates(position.getX() + 40, position.getY() + 220);
            case SWING_4, SWING_6 ->
                    harry.getPosition().updateCoordinates(position.getX() + 115, position.getY() + 220);
            case SWING_5 -> harry.getPosition().updateCoordinates(position.getX() + 130, position.getY() + 200);
        }
    }

    /**
     * Updates harrys position according to the current swinging state of the liana.
     *
     * @param harry Character
     */
    void updatePositionForSwingingOpposite(PitfallHarry harry) {
        switch (swingStateOpposite) {
            case SWING_1 -> harry.getPosition().updateCoordinates(position.getX() + 130, position.getY() + 200);
            case SWING_2, SWING_8 ->
                    harry.getPosition().updateCoordinates(position.getX() + 115, position.getY() + 220);
            case SWING_3, SWING_7 -> harry.getPosition().updateCoordinates(position.getX() + 40, position.getY() + 220);
            case SWING_4, SWING_6 -> harry.getPosition().updateCoordinates(position.getX() - 10, position.getY() + 170);
            case SWING_5 -> harry.getPosition().updateCoordinates(position.getX() - 20, position.getY() + 160);
        }
    }
}