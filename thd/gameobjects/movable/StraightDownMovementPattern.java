package thd.gameobjects.movable;

import thd.gameobjects.base.MovementPattern;
import thd.gameobjects.base.Position;


class StraightDownMovementPattern extends MovementPattern {

    private final Position[] pattern;

    StraightDownMovementPattern(double x) {
        super();
        pattern = new Position[]{new Position(x, 0), new Position(x - 200, 720)};
    }

    @Override
    protected Position nextTargetPosition(Position... referencePositions) {
        return pattern[1];
    }

    @Override
    protected Position startPosition(Position... referencePositions) {
        return pattern[0];
    }
}