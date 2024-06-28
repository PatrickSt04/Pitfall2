package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;

/**
 * Represents list of gameObjects sorted by distance to background.
 */
public class SortedGameObjectsList extends LinkedList<GameObject> {

    @Override
    public boolean add(GameObject object) {
        int indexToSortIn = 0;
        for (GameObject gameObject : this) {
            if (gameObject.getDistanceToBackground() >= object.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        this.add(indexToSortIn, object);
        return true;
    }
}
