package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The Cave class represents the cave.
 *
 * @author Patrick Sterneder
 */
public class Cave extends GameObject implements ShiftableGameObject {
    private boolean brownBricks;
    private boolean withWater;
    private boolean withIce;

    /**
     * Creates cave Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Cave(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.0;
        distanceToBackground = '2';

    }

    @Override
    public String toString() {
        return "Cave: " + position;
    }

    @Override
    public void addToCanvas() {
        if (brownBricks && !withWater) {
            gameView.addImageToCanvas("cave_brown_bricks.png", position.getX(), position.getY(), size, rotation);
        } else if (brownBricks && withWater) {
            gameView.addImageToCanvas("cave_brown_bricks_water.png", position.getX(), position.getY(), size, rotation);
        } else if (withIce) {
            gameView.addImageToCanvas("cave_with_ice.png", position.getX(), position.getY(), size, rotation);
        } else {
            gameView.addImageToCanvas("cave.png", position.getX(), position.getY(), size, rotation);
        }
    }

    /**
     * Decides whether the cave object is shown with brown or gray bricks.
     *
     * @param brownBricks boolean
     */
    public void setBrownBricks(boolean brownBricks) {
        this.brownBricks = brownBricks;
    }

    /**
     * Decides whether the cave object is shown with water.
     *
     * @param withWater boolean
     */
    public void setWithWater(boolean withWater) {
        this.withWater = withWater;
    }

    /**
     * Decides whether the cave object is shown with ice.
     *
     * @param withIce boolean
     */
    public void setWithIce(boolean withIce) {
        this.withIce = withIce;
    }
}
