package thd.game.managers;

/**
 * The TooManyGameObjectsException exception is thrown when a maximum number of GameObjects is exceeded.
 *
 * @author Patrick Sterneder
 */
public class TooManyGameObjectsException extends RuntimeException {
    /**
     * Creates the exception.
     *
     * @param message Imports message
     */
    public TooManyGameObjectsException(String message) {
        super(message);
    }

}
