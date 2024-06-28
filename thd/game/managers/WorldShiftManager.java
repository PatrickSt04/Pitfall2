package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Manages the shifting of the gameWorld.
 */
public class WorldShiftManager extends UserControlledGameObjectPool {
    private final List<GameObject> shiftableGameObjects;

    protected WorldShiftManager(GameView gameView) {
        super(gameView);
        shiftableGameObjects = new LinkedList<>();
    }

    protected void addToShiftableGameObjectsIfShiftable(GameObject gameObject) {
        if (gameObject instanceof ShiftableGameObject) {
            if (!shiftableGameObjects.contains(gameObject)) {
                shiftableGameObjects.add(gameObject);
            }
        }
    }

    protected void spawnGameObject(GameObject gameObject) {
        addToShiftableGameObjectsIfShiftable(gameObject);
    }

    protected void destroyGameObject(GameObject gameObject) {
        if (gameObject instanceof ShiftableGameObject) {
            shiftableGameObjects.remove(gameObject);
        }
    }

    protected void destroyAllGameObjects() {
        shiftableGameObjects.clear();
    }

    /**
     * Moves the game world to the left.
     *
     * @param pixels Number of pixels to move the world.
     */
    public void moveWorldToLeft(double pixels) {
        shiftGameObjects(-pixels, 0);
    }

    /**
     * Moves the game world to the right.
     *
     * @param pixels Number of pixels to move the world.
     */
    public void moveWorldToRight(double pixels) {
        shiftGameObjects(pixels, 0);
    }

    /**
     * Moves the game world up.
     *
     * @param pixels Number of pixels to move the world.
     */
    public void moveWorldUp(double pixels) {
        shiftGameObjects(0, -pixels);
    }

    /**
     * Moves the game world down.
     *
     * @param pixels Number of pixels to move the world.
     */
    public void moveWorldDown(double pixels) {
        shiftGameObjects(0, pixels);
    }

    private void shiftGameObjects(double shiftX, double shiftY) {
        for (GameObject gameObject : shiftableGameObjects) {
            gameObject.getPosition().right(shiftX);
            gameObject.getPosition().down(shiftY);
            gameObject.getTargetPosition().down(shiftY);
        }
    }

    /**
     * Moves world to the starting position of current level.
     * StartSign always has the same Position in every level.
     */
    public void moveWorldToStart() {
        if (startSign.getPosition().getY() < startSign.startPosition.getY()) {
            moveWorldUp(startSign.getPosition().getY() - startSign.startPosition.getY());
        } else if (startSign.getPosition().getY() > startSign.startPosition.getY()) {
            moveWorldDown(startSign.startPosition.getY() - startSign.getPosition().getY());
        }
        if (startSign.getPosition().getX() < startSign.startPosition.getX()) {
            moveWorldToRight(startSign.startPosition.getX() - startSign.getPosition().getX());
        } else if (startSign.getPosition().getX() > startSign.startPosition.getX()) {
            moveWorldToLeft(startSign.getPosition().getX() - startSign.startPosition.getX());
        }
    }
}
