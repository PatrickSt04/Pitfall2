package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;

class StraightMovementPattern extends MovementPattern {

    private final Position[] pattern;

    StraightMovementPattern(double y) {
        super();
        pattern = new Position[]{new Position(10, y), new Position(1270, y)};
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        currentIndex++;
        if (currentIndex >= pattern.length) {
            currentIndex = 0;
        }
        return pattern[currentIndex];
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return pattern[0];
    }
}