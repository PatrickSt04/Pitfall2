package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;

import java.util.Objects;

/**
 * Represents an object in the game.
 */
public abstract class GameObject {
    protected final GamePlayManager gamePlayManager;
    protected final GameView gameView;
    protected final Position position;
    protected final Position targetPosition;
    protected double speedInPixel;
    protected double rotation;
    protected double size;
    protected double width;
    protected double height;
    protected char distanceToBackground;
    protected MovementPattern movementPattern;
    /**
     * Crates a new GameObject.
     *
     * @param gameView        GameView to show the game object on.
     * @param gamePlayManager GamePlayManager
     */
    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        this.gamePlayManager = gamePlayManager;

        position = new Position();
        targetPosition = new Position();
    }

    /**
     * Updates the position of the game object.
     */
    public void updatePosition() {
    }

    /**
     * Draws the game object to the canvas.
     */
    public abstract void addToCanvas();

    /**
     * Returns the current position of the game object.
     *
     * @return position of the game object.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns current target position of the game object.
     *
     * @return target position.
     */
    public Position getTargetPosition() {
        return targetPosition;
    }

    /**
     * Returns width of game object.
     *
     * @return Width of game object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns height of game object.
     *
     * @return Height of game object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Updates a GameObjects status.
     */
    public void updateStatus() {
    }

    /**
     * Get distance to background of game object.
     *
     * @return distance in char
     */
    public char getDistanceToBackground() {
        return distanceToBackground;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameObject other = (GameObject) obj;
        return position.equals(other.position) && targetPosition.equals(other.targetPosition) && size == other.size && width == other.width && height == other.height && rotation == other.rotation && speedInPixel == other.speedInPixel && distanceToBackground == other.distanceToBackground;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, targetPosition, size, width, height, rotation, speedInPixel, distanceToBackground);
    }
}