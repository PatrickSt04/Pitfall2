package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;


/**
 * The Rock class represents the rock.
 *
 * @author Patrick Sterneder
 */
public class Rock extends CollidingGameObject implements ShiftableGameObject {
    private final boolean positionOnLeft;

    /**
     * Creates rock Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param positionOnLeft  Import rock position on left or right of the screen
     */
    public Rock(GameView gameView, GamePlayManager gamePlayManager, boolean positionOnLeft) {
        super(gameView, gamePlayManager);
        this.positionOnLeft = positionOnLeft;
        size = 1.0;
        width = 32;
        height = 200;
        hitBoxOffsets(0, 0, 0, 0);
        distanceToBackground = '3';
    }

    @Override
    public String toString() {
        return "Rock: " + position;
    }

    @Override
    public void addToCanvas() {
        if (positionOnLeft) {
            gameView.addImageToCanvas("rock_left.png", position.getX(), position.getY(), size, rotation);
        } else {
            gameView.addImageToCanvas("rock_right.png", position.getX(), position.getY(), size, rotation);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }
}
