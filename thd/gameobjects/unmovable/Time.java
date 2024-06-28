package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * The Time class represents the CurrentScore.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects.base.Position
 * @see java.awt
 * @see #addToCanvas()
 * @see #Time(GameView, GamePlayManager)
 * @see #toString()
 */
public class Time extends GameObject {
    private long currentTimeInMilliseconds;

    /**
     * Creates Time Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public Time(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 25;
        width = 150;
        height = 33;
        position.updateCoordinates(10, 40);
        distanceToBackground = '2';
    }

    @Override
    public String toString() {
        return "Time: " + position;
    }

    @Override
    public void updateStatus() {
        currentTimeInMilliseconds = gameView.gameTimeInMilliseconds();
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("Time", position.getX(), position.getY() - 20, size, true, Color.DARK_GRAY, rotation);
        gameView.addTextToCanvas(minutesAndSecondsAsString(), position.getX(), position.getY(), size - 5, true, Color.DARK_GRAY, rotation);
    }

    private String minutesAndSecondsAsString() {
        long totalSeconds = currentTimeInMilliseconds / 1000;
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Resets the timer in case of a game start.
     */
    public void resetTime() {
        currentTimeInMilliseconds = 0;
    }
}
