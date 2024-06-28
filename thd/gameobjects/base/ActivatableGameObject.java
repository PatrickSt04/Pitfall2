package thd.gameobjects.base;

/**
 * The ActivatableGameObject class represents an activatable gameobject.
 *
 * @param <T> Type of Object which decide about activation
 */
public interface ActivatableGameObject<T> {
    /**
     * Given object can decide whether an object will be activated or not.
     *
     * @param info Object
     * @return true or false
     */
    boolean tryToActivate(T info);
}
