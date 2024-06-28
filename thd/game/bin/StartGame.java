package thd.game.bin;

import thd.game.managers.GameViewManager;

/**
 * The StartGame class represents the starting class of the game Pitfall 2.
 *
 * @see GameViewManager
 * @see #main(String[])
 */
public class StartGame {
    /**
     * Main method for starting the game.
     *
     * @param args (String)
     */
    public static void main(String[] args) {
        GameViewManager gameViewManager = new GameViewManager();
        gameViewManager.startGame();
    }
}
