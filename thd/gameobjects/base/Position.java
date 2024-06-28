package thd.gameobjects.base;


import thd.game.utilities.GameView;

import java.util.Objects;

/**
 * The Position class represents the position of a game-object.
 * For rendering game objects or managing the game view see {@link GameView}.
 *
 * @author Patrick Sterneder
 * @see GameView
 */
public class Position implements Comparable<Position> {

    private double x;
    private double y;

    @Override
    public int compareTo(Position other) {
        return Double.compare(distance(new Position()), other.distance(new Position()));
    }

    /**
     * Creates a position on (0, 0).
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Creates a position with the coordinates of the given position.
     *
     * @param otherPosition Another position.
     */
    public Position(Position otherPosition) {
        this(otherPosition.x, otherPosition.y);
    }

    /**
     * Creates a position on (x, y).
     *
     * @param x X-coordinate on the window.
     * @param y Y-coordinate on the window.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x coordinate.
     *
     * @return x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y coordinate.
     *
     * @return y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Updates this position to the coordinates of the given position.
     *
     * @param otherPosition Another position.
     */
    public void updateCoordinates(Position otherPosition) {
        x = otherPosition.x;
        y = otherPosition.y;
    }

    /**
     * Updates this position to the coordinates of the new position.
     *
     * @param x X-coordinate on the window.
     * @param y Y-coordinate on the window.
     */
    public void updateCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * One pixel to the right.
     */
    public void right() {
        x++;
    }

    /**
     * To the right by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void right(double pixel) {
        x += pixel;
    }

    /**
     * One pixel to the left.
     */
    public void left() {
        x--;
    }

    /**
     * To the left by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void left(double pixel) {
        x -= pixel;
    }

    /**
     * One pixel upwards.
     */
    public void up() {
        y--;
    }

    /**
     * Upwards by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void up(double pixel) {
        y -= pixel;
    }

    /**
     * One pixel downwards.
     */
    public void down() {
        y++;
    }

    /**
     * Downwards by the given number of pixels.
     *
     * @param pixel Number of pixels.
     */
    public void down(double pixel) {
        y += pixel;
    }

    /**
     * Moves towards the given position with the given speed.
     *
     * @param otherPosition Another position
     * @param speedInPixel  Speed of movement in a single frame
     */
    public void moveToPosition(Position otherPosition, double speedInPixel) {
        double distance = distance(otherPosition);
        if (distance <= speedInPixel) {
            updateCoordinates(otherPosition);
        } else {
            right((otherPosition.x - x) / distance * speedInPixel);
            down((otherPosition.y - y) / distance * speedInPixel);
        }
    }

    /**
     * Calculates distance between this and another positon.
     *
     * @param otherPosition other position
     * @return double distance
     */
    public double distance(Position otherPosition) {
        double differenceX = (x > otherPosition.getX()) ? x - otherPosition.getX() : otherPosition.getX() - x;
        double differenceY = (y > otherPosition.getY()) ? y - otherPosition.getY() : otherPosition.getY() - y;
        return Math.sqrt(Math.pow(differenceX, 2) + Math.pow(differenceY, 2));
    }

    /**
     * Compares position to otherPosition.
     *
     * @param otherPosition other Position
     * @return true or false
     */
    public boolean similarTo(Position otherPosition) {
        return Math.round(x) == Math.round(otherPosition.x)
                && Math.round(y) == Math.round(otherPosition.y);
    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
