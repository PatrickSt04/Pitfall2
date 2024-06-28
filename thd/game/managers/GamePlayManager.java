package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * The GamePlayManager represents the unit spawning and destroying GameObjects.
 *
 * @author Patrick Sterneder
 */
public class GamePlayManager extends WorldShiftManager {
    private final GameObjectManager gameObjectManager;
    protected int lives;
    protected int points;
    /**
     * Boolean end of level.
     */
    public boolean isEndOfLevel;

    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
    }

    /**
     * Spawns a given game object.
     *
     * @param gameObject Import object
     */
    @Override
    public void spawnGameObject(GameObject gameObject) {
        super.spawnGameObject(gameObject);
        gameObjectManager.add(gameObject);
    }

    /**
     * Destroys a given game object.
     *
     * @param gameObject Import object
     */
    @Override
    public void destroyGameObject(GameObject gameObject) {
        super.destroyGameObject(gameObject);
        gameObjectManager.remove(gameObject);
    }

    @Override
    protected void destroyAllGameObjects() {
        super.destroyAllGameObjects();
        gameObjectManager.removeAll();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }

    private void gamePlayManagement() {
    }

    /**
     * Adds points.
     *
     * @param points amount of points that will be added.
     */
    public void addPoints(int points) {
        this.points += points;
        if (this.points > highscore.score) {
            highscore.score = this.points;
        }
        currentScore.updatePoints(this.points);
    }

    /**
     * At Collision with opponent the screen is set to the origin of the current level.
     * Harry's Position is reset;
     */
    public void lifeLost() {
        //Set main character to its start position at current screen
        lives--;
        moveWorldToStart();
        pitfallHarry.resetPosition();
        isEndOfLevel = true;
    }

}

