package thd.game.managers;

import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.screens.EndScreen;
import thd.screens.StartScreen;

/**
 * The GameManager class represents the managing unit of the game
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects
 * @see #GameManager(GameView)
 * @see #gameLoopUpdate()
 */
class GameManager extends LevelManager {

    GameManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
        overlay.updateStatus();
    }

    private void gameManagement() {
        if (endOfGame()) {
            gameView.stopAllSounds();
            //overlay.showMessage("Game Over");
            //overlay.addToCanvas();
            if (gameView.timer(2000, this)) {
                FileAccess.writeHighScoreToDisc(highscore.score);
                EndScreen endScreen = new EndScreen(gameView);
                endScreen.showEndScreen(points);
                startNewGame();
                isEndOfLevel = false;
            }
        } else if (endOfLevel()) {
            gameView.stopAllSounds();
            if (!(lives == 0)) {
                overlay.showMessage(" ", 1);
                overlay.addToCanvas();
                if (gameView.timer(1000, this)) {
                    overlay.stopShowing();
                }
            }
            if (!overlay.isMessageShown()) {
                switchToNextLevel();
                initializeLevel();
                isEndOfLevel = false;
            }
        }
    }

    @Override
    protected void initializeLevel() {
        overlay.showMessage(level.name, 2);
        super.initializeLevel();
        gameView.stopAllSounds();
        gameView.playSound(level.getSound(), true);
    }

    private boolean endOfLevel() {
        return isEndOfLevel;
    }

    private boolean endOfGame() {
        return (!hasNextLevel() && endOfLevel() && !(lives == 0));
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        initializeLevel();
    }

    void startNewGame() {
        highscore.score = FileAccess.readHighScoreFromDisc();
        Difficulty difficulty = FileAccess.readDifficultyFromDisc(); // Lesen der gespeicherten Auswahl.
        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreenWithPreselectedDifficulty(difficulty);
        difficulty = startScreen.getSelectedDifficulty();
        FileAccess.writeDifficultyToDisc(difficulty); // Abspeichern der neuen Auswahl.
        Level.difficulty = difficulty;
        initializeGame();
    }
}