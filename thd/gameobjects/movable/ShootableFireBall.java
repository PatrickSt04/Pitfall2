package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Enemy;
import thd.gameobjects.base.Position;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.unmovable.FloorUpperPart;

/**
 * Represents a fireball shooten by the statue.
 */
class ShootableFireBall extends CollidingGameObject implements Enemy, ShiftableGameObject {

    ShootableFireBall(GameView gameView, GamePlayManager gamePlayManager, Position startPosition, Position targetPosition) {
        super(gameView, gamePlayManager);
        width = 15;
        height = 15;
        speedInPixel = 4;
        size = 1;
        distanceToBackground = '6';
        hitBoxOffsets(0, 0, width, height);
        position.updateCoordinates(startPosition);
        this.targetPosition.updateCoordinates(targetPosition);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof FloorUpperPart) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("shootable_fireball.png", position.getX(), position.getY(), size, 5);
    }

    @Override
    public void updatePosition() {
        if (allowedToUpdatePosition()) {
            position.moveToPosition(targetPosition, speedInPixel);
            position.down(1);
            if (gameView.timer(3000, this)) {
                gamePlayManager.destroyGameObject(this);
            }
        }
    }
}
