package thd.screens;

import thd.game.level.Difficulty;
import thd.game.utilities.GameView;

/**
 * Represents the start screen.
 */
public class StartScreen {
    private final GameView gameView;
    private Difficulty selectedDifficulty;

    /**
     * Creates the start screen.
     *
     * @param gameView gameview
     */
    public StartScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Shows the startscreen with the preselected difficulty.
     *
     * @param preselectedDifficulty difficulty
     */
    public void showStartScreenWithPreselectedDifficulty(Difficulty preselectedDifficulty) {
        String title = """
                 ________  ___  _________  ________ ________  ___       ___                _______    \s
                |\\   __  \\|\\  \\|\\___   ___\\\\  _____\\\\   __  \\|\\  \\     |\\  \\              /  ___  \\   \s
                \\ \\  \\|\\  \\ \\  \\|___ \\  \\_\\ \\  \\__/\\ \\  \\|\\  \\ \\  \\    \\ \\  \\            /__/|_/  /|  \s
                 \\ \\   ____\\ \\  \\   \\ \\  \\ \\ \\   __\\\\ \\   __  \\ \\  \\    \\ \\  \\           |__|//  / /  \s
                  \\ \\  \\___|\\ \\  \\   \\ \\  \\ \\ \\  \\_| \\ \\  \\ \\  \\ \\  \\____\\ \\  \\____          /  /_/__ \s
                   \\ \\__\\    \\ \\__\\   \\ \\__\\ \\ \\__\\   \\ \\__\\ \\__\\ \\_______\\ \\_______\\       |\\________\\
                    \\|__|     \\|__|    \\|__|  \\|__|    \\|__|\\|__|\\|_______|\\|_______|        \\|_______|
                """;
        String description = """
                In "Pitfall 2: Lost Caverns," embark on an exciting adventure to explore the mysterious lost caverns.
                Your mission is to recover hidden treasures scattered throughout the labyrinthine depths,
                all while dodging perilous dangers and cunning traps.
                Navigate the treacherous terrain with these controls:
                W: Climb upwards to reach higher ledges and platforms.
                S: Descend carefully to explore lower levels and hidden passages.
                A: Move swiftly to the left, evading obstacles and enemies.
                D: Dash to the right, uncovering secrets and treasures.
                SPACE: Leap over gaps and hazards with agility and precision.
                Prepare yourself for a thrilling journey filled with challenges and surprises at every turn.
                Will you emerge victorious from the lost caverns?
                """;
        boolean difficultyIsSetToEasy = gameView.showSimpleStartScreen(title, description, preselectedDifficulty == Difficulty.EASY);
        if (difficultyIsSetToEasy) {
            selectedDifficulty = Difficulty.EASY;
        } else {
            selectedDifficulty = Difficulty.STANDARD;
        }
    }

    /**
     * Returns the selected difficulty.
     *
     * @return selected difficulty
     */
    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
