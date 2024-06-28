package thd.gameobjects.base;

import java.util.Random;

/**
 * Represents a movement pattern in the game.
 */
public class MovementPattern {
    protected int currentIndex;
    protected final Random random;

    protected MovementPattern() {
        currentIndex = -1;
        random = new Random();
    }

    protected Position nextTargetPosition(Position... referencePositions) {
        return new Position();
    }

    protected Position startPosition(Position... referencePositions) {
        currentIndex = 0;
        return new Position();
    }
}
