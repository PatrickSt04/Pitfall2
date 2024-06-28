package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;

/**
 * The Statue class represents the statue.
 *
 * @author Patrick Sterneder
 */
public class Statue extends CollidingGameObject implements ShiftableGameObject {
    private final GameObject trackingObject;


    /**
     * Creates Statue Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     * @param trackingObject  Import TrackingObject
     */
    public Statue(GameView gameView, GamePlayManager gamePlayManager, GameObject trackingObject) {
        super(gameView, gamePlayManager);
        this.trackingObject = trackingObject;
        size = 1.0;
        //hitbox
        width = 12.0;
        height = 100.0;
        hitBoxOffsets(10, 5, width, height);
        distanceToBackground = '4';
    }


    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public void updateStatus() {
        if (allowedToUpdatePosition()) {
            if (gameView.timer(1500, this)) {
                shootFireBall();
            }
        }
    }

    @Override
    public String toString() {
        return "Statue: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("statue.png", position.getX(), position.getY(), size, rotation);
    }

    private void shootFireBall() {
        ShootableFireBall shootableFireBall = new ShootableFireBall(gameView, gamePlayManager, new Position(position.getX(), position.getY()), trackingObject.getPosition());
        gamePlayManager.spawnGameObject(shootableFireBall);
    }
}
