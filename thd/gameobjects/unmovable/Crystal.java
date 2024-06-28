package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.Collectable;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.PitfallHarry;

/**
 * The Crystal class represents the Crystal.
 *
 * @author Patrick Sterneder
 */
class Crystal extends CollidingGameObject implements ShiftableGameObject, Collectable {

    /**
     * Creates Crystal Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    Crystal(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.0;
        //hitbox
        width = 18.0;
        height = 13.0;
        hitBoxOffsets(15, 18, width, height);
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
        return "Crystal: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("crystal.png", position.getX(), position.getY(), size, rotation);
    }

}
