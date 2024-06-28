package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {
    protected final GameView gameView;
    protected Highscore highscore;
    protected CurrentScore currentScore;
    protected Icon icon;
    protected PitfallHarry pitfallHarry;
    protected StartSign startSign;
    protected StatusBlockImages statusBlockImages;
    protected Time time;
    protected Level level;
    protected Overlay overlay;

    UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        if (!pitfallHarry.isAnimatingDeath && !pitfallHarry.isAnimatingJump && !pitfallHarry.isFalling) {
            if (pressedKeys.length == 0 && !pitfallHarry.isAnimatingSwing && !pitfallHarry.isClimbing()) {
                pitfallHarry.changeIdleState();
            }
            for (int keyCode : pressedKeys) {
                if (pitfallHarry.isAnimatingSwing && (keyCode == KeyEvent.VK_SPACE || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D)) {
                    processKeyCode(keyCode);
                } else if (!pitfallHarry.isAnimatingSwing && !pitfallHarry.isAnimatingJump && !pitfallHarry.isAnimatingDeath) {
                    processKeyCode(keyCode);
                } else {
                    break;
                }
            }
        }
    }

    private void processKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                if (!pitfallHarry.isAnimatingSwing) {
                    pitfallHarry.left();
                }
                break;
            case KeyEvent.VK_D:
                if (!pitfallHarry.isAnimatingSwing) {
                    pitfallHarry.right();
                }
                break;
            case KeyEvent.VK_W:
                pitfallHarry.up();
                break;
            case KeyEvent.VK_S:
                pitfallHarry.down();
                break;
            case KeyEvent.VK_SPACE:
                if (!pitfallHarry.isClimbing()) {
                    pitfallHarry.jump();
                }
                break;
        }
    }
}
