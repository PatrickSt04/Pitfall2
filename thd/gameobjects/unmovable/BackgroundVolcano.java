package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * The BackgroundVolcano class represents the key.
 *
 * @author Patrick Sterneder
 */
public class BackgroundVolcano extends GameObject implements ShiftableGameObject {
    private boolean purple;
    private boolean green;
    private boolean grey;
    private boolean pink;

    /**
     * Creates Background Object.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public BackgroundVolcano(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 1.0;
        position.updateCoordinates(0, 0);
        distanceToBackground = '0';
    }

    @Override
    public String toString() {
        return "BackgroundVolcano: " + position;
    }

    @Override
    public void addToCanvas() {
        if (purple) {
            gameView.addImageToCanvas("background_vulcano_purple.png", position.getX(), position.getY(), size, rotation);
        } else if (grey) {
            gameView.addImageToCanvas("background_vulcano_grey.png", position.getX(), position.getY(), size, rotation);
        } else if (green) {
            gameView.addImageToCanvas("background_vulcano_green.png", position.getX(), position.getY(), size, rotation);
        } else if (pink) {
            gameView.addImageToCanvas("background_vulcano_pink.png", position.getX(), position.getY(), size, rotation);
        }
    }

    /**
     * Sets Background grey.
     *
     * @param grey boolean
     */
    public void setGrey(boolean grey) {
        this.grey = grey;
    }

    /**
     * Sets Background green.
     *
     * @param green boolean
     */
    public void setGreen(boolean green) {
        this.green = green;
    }

}
