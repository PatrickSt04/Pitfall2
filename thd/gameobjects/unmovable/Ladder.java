package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The Ladder class represents the ladder.
 *
 * @author Patrick Sterneder
 */
public class Ladder extends CollidingGameObject implements ShiftableGameObject {

    /**
     * Creates ladder Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Ladder(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 3.0;
        //hitbox
        width = 5.0;
        height = 100.0;
        hitBoxOffsets(30, -70, width, height);
        distanceToBackground = '3';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

    @Override
    public String toString() {
        return "Ladder: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("ladder.png", position.getX(), position.getY(), size, rotation);
    }

}
