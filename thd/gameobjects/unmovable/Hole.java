package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The Hole class represents the ladder.
 *
 * @author Patrick Sterneder
 */
public class Hole extends CollidingGameObject implements ShiftableGameObject {
    /**
     * Shows if a ladder is under the hole.
     */
    public boolean inCombinationWithLadder;

    /**
     * Creates hole Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Hole(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.5;
        //hitbox
        width = 20;
        height = 10;
        hitBoxOffsets(20, 5, width, height);
        distanceToBackground = '4';
        inCombinationWithLadder = false;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Ladder) {
            inCombinationWithLadder = true;
        }
    }

    @Override
    public String toString() {
        return "Hole: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("hole.png", position.getX(), position.getY() + 5, size, rotation);
    }

}
