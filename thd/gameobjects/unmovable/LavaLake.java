package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.base.Enemy;

/**
 * The FloorLowerPart class represents the FloorUpperPart.
 *
 * @author Patrick Sterneder
 */
public class LavaLake extends CollidingGameObject implements ShiftableGameObject, Enemy {
    private final boolean bigLake;

    /**
     * Creates FloorUpperPart Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param bigLake         Import bigLake
     */
    public LavaLake(GameView gameView, GamePlayManager gamePlayManager, boolean bigLake) {
        super(gameView, gamePlayManager);
        this.bigLake = bigLake;
        size = 1.5;
        width = 150;
        height = 5;
        if (bigLake) {
            hitBoxOffsets(40, 0, width * 2.5, height);
        } else {
            hitBoxOffsets(20, 0, width, height);
        }
        distanceToBackground = '5';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "FloorLowerPart: " + position;
    }

    @Override
    public void addToCanvas() {
        if (bigLake) {
            gameView.addImageToCanvas("lava_lake_large.png", position.getX(), position.getY(), size, rotation);
        } else {
            gameView.addImageToCanvas("lava_lake_small.png", position.getX(), position.getY(), size, rotation);
        }
    }
}
