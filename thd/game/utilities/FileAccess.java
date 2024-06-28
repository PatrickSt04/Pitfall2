package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The FileAccess class represents access to files.
 */
public class FileAccess {

    private static final Path WICHTEL_GAME_FILE = Paths.get(System.getProperty("user.home")).resolve("wichtelgame.txt");
    private static final Path WICHTEL_GAME_FILE2 = Paths.get(System.getProperty("user.home")).resolve("wichtelgame2.txt");

    /**
     * Writes the given difficulty to the disc.
     *
     * @param difficulty Difficulty to write to the disc.
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            Files.writeString(WICHTEL_GAME_FILE, difficulty.name());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reads the difficulty from the disc.
     *
     * @return the difficulty read from the disc, or Difficulty.STANDARD if an error occurs.
     */
    public static Difficulty readDifficultyFromDisc() {
        try {
            String difficultyString = Files.readString(WICHTEL_GAME_FILE);
            return Difficulty.valueOf(difficultyString);
        } catch (IOException | IllegalArgumentException e) {
            return Difficulty.STANDARD;
        }
    }

    /**
     * Reads the high score from the disc.
     *
     * @return the high score read from the disc, or 0 if an error occurs.
     */
    public static int readHighScoreFromDisc() {
        try {
            String highScoreString = Files.readString(WICHTEL_GAME_FILE2);
            return Integer.parseInt(highScoreString);
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Writes the given high score to the disc.
     *
     * @param highscore High score to write to the disc.
     */
    public static void writeHighScoreToDisc(int highscore) {
        try {
            Files.writeString(WICHTEL_GAME_FILE2, String.valueOf(highscore));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
