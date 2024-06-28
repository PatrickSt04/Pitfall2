package thd.game.level;

/**
 * The NoMoreLevelsAvailableException is thrown if no more levels are available.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {
    /**
     * Creates the Exception.
     *
     * @param message String
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}
