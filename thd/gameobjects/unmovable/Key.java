package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Collectable;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.PitfallHarry;

/**
 * The Key class represents the key.
 *
 * @author Patrick Sterneder
 */
public class Key extends CollidingGameObject implements ShiftableGameObject, Collectable {


    /**
     * Creates key Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Key(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1;
        //hitbox
        width = 5.0;
        height = 5.0;
        hitBoxOffsets(9, 6, width, height);
        distanceToBackground = '4';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof PitfallHarry) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public String toString() {
        return "Key: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("key.png", position.getX(), position.getY(), size, rotation);
    }

}
