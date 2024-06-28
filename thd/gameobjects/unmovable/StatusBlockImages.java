package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * The StatusBlockImages represents unmovable and constant objects.
 * They are shown as block images.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see thd.gameobjects.base.Position
 * @see #addToCanvas()
 * @see #toString()
 * @see #StatusBlockImages(GameView, GamePlayManager)
 */
public class StatusBlockImages extends GameObject {
    private static final String ONEUP = """
              RRWWRRWWRRWRRWWWWWRR
              RWWWRRWWRRWRRWWRRW
              RWWWRRWWRRWRRWWRRW
             RRRWWRRWWRRWRRWWWWR
             RRRWWRRWWRRWRRWWRRR
             RRRWWRRWWRRWRRWWRRR
             RRRWWRRWWRRWRRWWRRR
            RRRWWWWRRWWWRRRWWRR
            """;
    private static final String TOP = """
              WWWWWWRRWWWRRWWWWW
              WWWWWWRWWRRWRRWWRRW
             RWRWWRWRWWRRWRRWWRRW
             RRRWWRRRWWRRWRRWWRRW
             RRRWWRRRWWRRWRRWWRRW
             RRRWWRRRWWRRWRRWWWWW
             RRRWWRRRWWRRWRRWWRRR
            RRRRWWRRRRWWWRRRWWRR
            """;

    /**
     * Creates block images for unmovable and constant objects.
     * Automatically sets scales.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public StatusBlockImages(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 3.0;
        position.updateCoordinates(10, 1);
        distanceToBackground = '9';
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(ONEUP, position.getX(), position.getY(), size, rotation);
        gameView.addBlockImageToCanvas(TOP, GameView.WIDTH / 2.0, 1, size, rotation);
    }

}
