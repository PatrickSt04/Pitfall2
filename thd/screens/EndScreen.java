package thd.screens;

import thd.game.utilities.GameView;

/**
 * Represents the End screen.
 */
public class EndScreen {
    private final GameView gameView;

    /**
     * Creates the end screen.
     *
     * @param gameView gameView
     */
    public EndScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Shows end screen.
     *
     * @param score current score
     */
    public void showEndScreen(int score) {
        gameView.showEndScreen("Congratulations!" + "\n" + "You scored " + score + " points!" + "\nTry again!");
    }
}
