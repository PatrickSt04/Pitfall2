package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The BackgroundTrees class represents the key.
 *
 * @author Patrick Sterneder
 */
public class BackgroundTrees extends GameObject implements ShiftableGameObject {
    private final boolean smilingTrees;

    /**
     * Creates Background Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param smilingTrees    Import smilingTrees
     */
    public BackgroundTrees(GameView gameView, GamePlayManager gamePlayManager, boolean smilingTrees) {
        super(gameView, gamePlayManager);
        this.smilingTrees = smilingTrees;
        size = 1.0;
        position.updateCoordinates(0, 0);
        distanceToBackground = '0';
    }

    @Override
    public String toString() {
        return "BackgroundTrees: " + position;
    }

    @Override
    public void addToCanvas() {
        if (smilingTrees) {
            gameView.addImageToCanvas("background_trees_smiling.png", position.getX(), position.getY(), size, rotation);
        } else {
            gameView.addImageToCanvas("background_trees.png", position.getX(), position.getY(), size, rotation);
        }
    }

}
