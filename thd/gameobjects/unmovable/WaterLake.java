package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Enemy;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The WaterLake class represents the FloorUpperPart.
 *
 * @author Patrick Sterneder
 */
public class WaterLake extends CollidingGameObject implements ShiftableGameObject, Enemy {

    /**
     * Creates WaterLake Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public WaterLake(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.5;
        distanceToBackground = '5';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public String toString() {
        return "WaterLake: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("water_lake_big.png", position.getX(), position.getY(), size, rotation);
    }
}
