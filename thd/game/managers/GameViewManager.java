package thd.game.managers;

import thd.game.utilities.GameView;

/**
 * The GameViewManager inheritates from GameView and represents its extension.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see #initialize()
 * @see #gameLoop()
 */
public class GameViewManager extends GameView {
    private GameManager gameManager;

    /**
     * Initializes game window.
     */
    @Override
    public void initialize() {
        gameManager = new GameManager(this);
        setWindowTitle("Pitfall II");
        setStatusText("Patrick Sterneder - Java Programmierung SS 2024");
        setWindowIcon("pitfall_icon.png");
        gameManager.startNewGame();
        //showStatistic(true);
    }

    /**
     * Calls gameManager.gameLoopUpdate.
     * Updates objects positions.
     */
    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}
