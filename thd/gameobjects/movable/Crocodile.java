package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * The Crocodile class represents the scorpion entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #updatePosition()
 */
public class Crocodile extends CollidingGameObject implements ShiftableGameObject, ActivatableGameObject<PitfallHarry> {
    private enum OpenMouth {
        OPEN("crocodile_2.png");
        private final String display;

        OpenMouth(String display) {
            this.display = display;
        }
    }

    private enum Idle {
        IDLE("crocodile_1.png");
        private final String display;

        Idle(String display) {
            this.display = display;
        }
    }

    private enum State {OPEN, IDLE}

    private String png;
    private State currentState;
    private OpenMouth openMouth;
    private Idle idle;
    private boolean mouthOpen;

    /**
     * Creates Crocodile.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param mouthOpen       Import mouthOpen
     */
    public Crocodile(GameView gameView, GamePlayManager gamePlayManager, boolean mouthOpen) {
        super(gameView, gamePlayManager);
        this.mouthOpen = mouthOpen;
        if (mouthOpen) {
            currentState = State.OPEN;
        } else {
            currentState = State.IDLE;
        }
        openMouth = OpenMouth.OPEN;
        idle = Idle.IDLE;

        size = 1.5;
        speedInPixel = 3;
        width = 20;
        height = 15;
        hitBoxOffsets(20, 10, width, height);
        distanceToBackground = '6';
    }

    @Override
    public void updateStatus() {
        switch (currentState) {
            case OPEN -> {
                if (gameView.timer(500, this)) {
                    mouthOpen = true;
                    png = openMouth.display;
                }
            }
            case IDLE -> {
                png = idle.display;
            }
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (!mouthOpen) {
            if (other instanceof PitfallHarry) {
                currentState = State.OPEN;
            }
        } else {
            if (other instanceof PitfallHarry) {
                // no more hittable for PitfallHarry so he can animate death
                hitBoxOffsets(0, 50, 1, 1);
            }
        }
    }

    @Override
    public String toString() {
        return "Crocodile: " + position;
    }


    @Override
    public void addToCanvas() {
        if (mouthOpen) {
            gameView.addImageToCanvas("crocodile_2.png", position.getX(), position.getY(), size, rotation);
        } else {
            gameView.addImageToCanvas(png, position.getX(), position.getY(), size, rotation);
        }
    }

    @Override
    public boolean tryToActivate(PitfallHarry info) {
        return (position.getX() < 1280 && position.getX() > 0 && position.getY() < 720 && position.getY() > 0) && position.distance(info.getPosition()) < 1280;
    }

    boolean isMouthOpen() {
        return mouthOpen;
    }
}
