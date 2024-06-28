package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.*;
import thd.gameobjects.unmovable.Hole;
import thd.gameobjects.unmovable.Ladder;
import thd.gameobjects.unmovable.LevelEnd;

import java.awt.*;
import java.util.ArrayList;

/**
 * The PitfallHarry class represents the main character entity.
 *
 * @author Patrick Sterneder
 * @see GameView
 * @see Position
 * @see #addToCanvas()
 * @see #updatePosition()
 * @see #PitfallHarry(GameView, GamePlayManager)
 */
public class PitfallHarry extends CollidingGameObject implements MainCharacter {
    private final Position startPosition;
    private final double borderForYCoordinateOnCave;
    private final ArrayList<CollidingGameObject> collidingGameObjectsForPathDecision;

    private PitfallHarryStates.State currentState;
    private String png;
    private Liana currentLiana;

    private PitfallHarryStates.RunningRightState runningRightState;
    private final StateSwitch<PitfallHarryStates.RunningRightState> runningRightStateStateSwitch;
    private PitfallHarryStates.RunningLeftState runningLeftState;
    private final StateSwitch<PitfallHarryStates.RunningLeftState> runningLeftStateStateSwitch;
    private PitfallHarryStates.JumpingRightState jumpingRightState;
    private final StateSwitch<PitfallHarryStates.JumpingRightState> jumpingRightStateStateSwitch;
    private PitfallHarryStates.JumpingLeftState jumpingLeftState;
    private final StateSwitch<PitfallHarryStates.JumpingLeftState> jumpingLeftStateStateSwitch;
    private PitfallHarryStates.ClimbingState climbingState;
    private final StateSwitch<PitfallHarryStates.ClimbingState> climbingStateStateSwitch;
    private PitfallHarryStates.SwingingState swingingState;
    private final StateSwitch<PitfallHarryStates.SwingingState> swingingStateStateSwitch;
    private PitfallHarryStates.IdleState idleState;
    private PitfallHarryStates.DamagedState damagedState;
    private final StateSwitch<PitfallHarryStates.DamagedState> damagedStateStateSwitch;
    private PitfallHarryStates.SwingJumpState swingJumpState;
    private final StateSwitch<PitfallHarryStates.SwingJumpState> swingJumpStateSwitch;
    private PitfallHarryStates.FallingState fallingState;
    /**
     * Shows if animation of death is in progress.
     */
    public boolean isAnimatingDeath;
    /**
     * Shows if animation of jump is in progress.
     */
    public boolean isAnimatingJump;
    /**
     * Shows if animation of swing is in progress.
     */
    public boolean isAnimatingSwing;
    /**
     * Shows if Harry is in falling process.
     */
    public boolean isFalling;


    /**
     * Creates PitfallHarry.
     * Automatically sets scale.
     *
     * @param gameView        Import GameView
     * @param gamePlayManager Import GamePlayManager
     */
    public PitfallHarry(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        runningRightState = PitfallHarryStates.RunningRightState.RUNNING_1;
        runningLeftState = PitfallHarryStates.RunningLeftState.RUNNING_1;
        jumpingRightState = PitfallHarryStates.JumpingRightState.JUMPING_1;
        jumpingLeftState = PitfallHarryStates.JumpingLeftState.JUMPING_1;
        climbingState = PitfallHarryStates.ClimbingState.CLIMBING_1;
        swingingState = PitfallHarryStates.SwingingState.SWINGING_1;
        idleState = PitfallHarryStates.IdleState.IDLE;
        damagedState = PitfallHarryStates.DamagedState.DAMAGED_1;
        currentState = PitfallHarryStates.State.IDLE;
        swingJumpState = PitfallHarryStates.SwingJumpState.JUMPING_1;
        fallingState = PitfallHarryStates.FallingState.FALLING_1;
        runningRightStateStateSwitch = new StateSwitch<>(PitfallHarryStates.RunningRightState.RUNNING_1, PitfallHarryStates.RunningRightState.values());
        runningLeftStateStateSwitch = new StateSwitch<>(PitfallHarryStates.RunningLeftState.RUNNING_1, PitfallHarryStates.RunningLeftState.values());
        jumpingRightStateStateSwitch = new StateSwitch<>(PitfallHarryStates.JumpingRightState.JUMPING_1, PitfallHarryStates.JumpingRightState.values());
        jumpingLeftStateStateSwitch = new StateSwitch<>(PitfallHarryStates.JumpingLeftState.JUMPING_1, PitfallHarryStates.JumpingLeftState.values());
        climbingStateStateSwitch = new StateSwitch<>(PitfallHarryStates.ClimbingState.CLIMBING_1, PitfallHarryStates.ClimbingState.values());
        swingingStateStateSwitch = new StateSwitch<>(PitfallHarryStates.SwingingState.SWINGING_1, PitfallHarryStates.SwingingState.values());
        damagedStateStateSwitch = new StateSwitch<>(PitfallHarryStates.DamagedState.DAMAGED_1, PitfallHarryStates.DamagedState.values());
        swingJumpStateSwitch = new StateSwitch<>(PitfallHarryStates.SwingJumpState.JUMPING_1, PitfallHarryStates.SwingJumpState.values());


        collidingGameObjectsForPathDecision = new ArrayList<>(10);
        startPosition = new Position(20, 370);
        position.updateCoordinates(startPosition);
        borderForYCoordinateOnCave = startPosition.getY() + 104;
        size = 4.0;
        speedInPixel = 6;
        width = 15.0;
        height = 40.0;
        hitBoxOffsets(10, 7, width, height);
        distanceToBackground = '6';
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (!isAnimatingDeath) {
            if (other instanceof Collectable) {
                gameView.playSound("collect.wav", false);
                gamePlayManager.addPoints(1000);
            } else if (other instanceof Enemy) {
                currentState = PitfallHarryStates.State.DAMAGED;
                isAnimatingDeath = true;
                gameView.playSound("die.wav", false);
            } else if (other instanceof LevelEnd) {
                gamePlayManager.isEndOfLevel = true;
            } else if (other instanceof Liana liana) {
                resetStates();
                processSwinging(liana);
            } else if (other instanceof Hole hole) {
                if (!hole.inCombinationWithLadder) {
                    currentState = PitfallHarryStates.State.FALLING;
                    isFalling = true;
                }
            } else if (other instanceof Crocodile crocodile && crocodile.isMouthOpen()) {
                currentState = PitfallHarryStates.State.DAMAGED;
                isAnimatingDeath = true;
                gameView.playSound("die.wav", false);
            }
        }
    }

    @Override
    public String toString() {
        return "PitfallHarry: " + position;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(png, position.getX(), position.getY(), size, rotation);
    }

    @Override
    public void updateStatus() {
        switch (currentState) {
            case RUNNING_LEFT -> {
                png = runningLeftState.display;
                if (gameView.timer(80, this)) {
                    runningLeftState = runningLeftStateStateSwitch.switchState();
                }
            }
            case RUNNING_RIGHT -> {
                png = runningRightState.display;
                if (gameView.timer(80, this)) {
                    runningRightState = runningRightStateStateSwitch.switchState();
                }
            }
            case JUMPING_RIGHT -> {
                png = jumpingRightState.display;
                if (gameView.timer(80, this)) {
                    position.up(jumpingRightState.up);
                    moveRightWhenAllowed(25);
                    jumpingRightState = jumpingRightStateStateSwitch.switchState();
                    if (isJumpRightAnimationComplete()) {
                        resetJumpingLogic();
                    }
                }
            }
            case JUMPING_LEFT -> {
                png = jumpingLeftState.display;
                if (gameView.timer(80, this)) {
                    position.up(jumpingLeftState.up);
                    moveLeftWhenAllowed(25);
                    jumpingLeftState = jumpingLeftStateStateSwitch.switchState();
                    if (isJumpLeftAnimationComplete()) {
                        resetJumpingLogic();
                    }
                }
            }
            case JUMPING_IDLE -> {
                png = jumpingRightState.display;
                if (gameView.timer(80, this)) {
                    position.up(jumpingRightState.up);
                    jumpingRightState = jumpingRightStateStateSwitch.switchState();
                    if (isJumpRightAnimationComplete()) {
                        resetJumpingLogic();
                    }
                }
            }
            case SWING_JUMP -> {
                png = swingJumpState.display;
                if (gameView.timer(80, this)) {
                    position.up(swingJumpState.up);
                    moveRightWhenAllowed(35);
                    swingJumpState = swingJumpStateSwitch.switchState();
                    if (isSwingJumpAnimationComplete()) {
                        resetJumpingLogic();
                    }
                }
            }
            case CLIMBING -> {
                png = climbingState.display;
                if (gameView.timer(80, this)) {
                    climbingState = climbingStateStateSwitch.switchState();
                }
                //Movement after climbing up/down is smoother
                if ((position.getY() == startPosition.getY()) || (position.getY() == borderForYCoordinateOnCave)) {
                    currentState = PitfallHarryStates.State.IDLE;
                }
            }
            case SWINGING -> {
                png = swingingState.display;
                if (currentLiana.swingOpposite) {
                    currentLiana.updatePositionForSwingingOpposite(this);
                } else {
                    currentLiana.updatePositionForSwinging(this);
                }
                if (gameView.timer(1000, this)) {
                    swingingState = swingingStateStateSwitch.switchState();
                }
            }
            case IDLE -> {
                png = idleState.display;
                switchToNextState();
                resetStates();
            }
            case DAMAGED -> {
                png = damagedState.display;
                if (gameView.timer(280, this)) {
                    damagedState = damagedStateStateSwitch.switchState();
                    if (isDamagedAnimationComplete()) {
                        isAnimatingDeath = false;
                        isAnimatingJump = false;
                        resetStates();
                        gamePlayManager.lifeLost();
                        currentState = PitfallHarryStates.State.IDLE;
                    }
                }
            }
            case FALLING -> {
                png = fallingState.display;
                position.updateCoordinates(position.getX(), borderForYCoordinateOnCave);
                gamePlayManager.moveWorldUp(104);
                if (isFallingComplete()) {
                    resetStates();
                    resetJumpingLogic();
                    isFalling = false;
                }
            }
        }
    }

    private void adjustCoordinateAfterJump() {
        if (yCoordinateHasToBeReset()) {
            position.updateCoordinates(position.getX(), startPosition.getY());
        }
    }

    private void resetJumpingLogic() {
        isAnimatingJump = false;
        resetStates();
        adjustCoordinateAfterJump();
    }

    private void switchToNextState() {
        int nextState = (currentState.ordinal() + 1) % PitfallHarryStates.State.values().length;
        currentState = PitfallHarryStates.State.values()[nextState];
    }

    private boolean isFallingComplete() {
        return position.getY() == borderForYCoordinateOnCave;
    }

    private boolean isDamagedAnimationComplete() {
        return damagedState.display.equals("die_4.png");
    }

    private boolean isJumpRightAnimationComplete() {
        return jumpingRightState == PitfallHarryStates.JumpingRightState.JUMPING_8;
    }

    private boolean isJumpLeftAnimationComplete() {
        return jumpingLeftState == PitfallHarryStates.JumpingLeftState.JUMPING_8;
    }

    private boolean isSwingJumpAnimationComplete() {
        return swingJumpState == PitfallHarryStates.SwingJumpState.JUMPING_8;
    }

    private void resetStates() {
        resetStateSwitches();
        currentState = PitfallHarryStates.State.IDLE;
        runningRightState = PitfallHarryStates.RunningRightState.RUNNING_1;
        runningLeftState = PitfallHarryStates.RunningLeftState.RUNNING_1;
        idleState = PitfallHarryStates.IdleState.IDLE;
        jumpingRightState = PitfallHarryStates.JumpingRightState.JUMPING_1;
        jumpingLeftState = PitfallHarryStates.JumpingLeftState.JUMPING_1;
        swingingState = PitfallHarryStates.SwingingState.SWINGING_1;
        damagedState = PitfallHarryStates.DamagedState.DAMAGED_1;
        swingJumpState = PitfallHarryStates.SwingJumpState.JUMPING_1;
        climbingState = PitfallHarryStates.ClimbingState.CLIMBING_1;
    }

    private void resetStateSwitches() {
        runningRightStateStateSwitch.resetStates();
        runningLeftStateStateSwitch.resetStates();
        jumpingRightStateStateSwitch.resetStates();
        jumpingLeftStateStateSwitch.resetStates();
        swingingStateStateSwitch.resetStates();
        damagedStateStateSwitch.resetStates();
        swingJumpStateSwitch.resetStates();
        climbingStateStateSwitch.resetStates();
    }

    private void processSwinging(Liana liana) {
        currentLiana = liana;
        currentState = PitfallHarryStates.State.SWINGING;
        isAnimatingSwing = true;
        isAnimatingJump = false;
        jumpingLeftState = PitfallHarryStates.JumpingLeftState.JUMPING_1;
        jumpingRightState = PitfallHarryStates.JumpingRightState.JUMPING_1;
        swingJumpState = PitfallHarryStates.SwingJumpState.JUMPING_1;
    }

    /**
     * Move left.
     */
    public void left() {
        if (!isEndOfLevel() && !isClimbing()) {
            currentState = PitfallHarryStates.State.RUNNING_LEFT;
            moveLeftWhenAllowed(speedInPixel);
            if (gameView.timer(1500, this)) {
                gamePlayManager.addPoints(10);
            }
        }
    }

    private void moveLeftWhenAllowed(double speedInPixel) {
        if (!gamePlayManager.isEndOfLevel) {
            if (position.getX() > 10) {
                position.left(speedInPixel);
                for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
                    if (notAllowedToMoveLeft(collidingGameObject)) {
                        position.right(speedInPixel);
                        break;
                    }
                }
            } else {
                shiftToPreviousSequence();
            }
        }
    }

    /**
     * Move right.
     */
    public void right() {
        if (!isEndOfLevel() && !isClimbing()) {
            currentState = PitfallHarryStates.State.RUNNING_RIGHT;
            moveRightWhenAllowed(speedInPixel);
            if (gameView.timer(1500, this)) {
                gamePlayManager.addPoints(10);
            }
        }
    }

    private void moveRightWhenAllowed(double speedInPixel) {
        if (!gamePlayManager.isEndOfLevel) {
            if (position.getX() < GameView.WIDTH) {
                position.right(speedInPixel);
            } else {
                shiftToNextSequence();
            }
            for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
                if (notAllowedToMoveRight(collidingGameObject)) {
                    position.left(speedInPixel);
                    break;
                }
            }
        }
    }

    /**
     * Move up.
     */
    public void up() {
        if (!gamePlayManager.isEndOfLevel) {
            for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
                if (collidesWithLadder(collidingGameObject) && position.getY() > startPosition.getY()) {
                    position.up();
                    gamePlayManager.moveWorldDown(1);
                    currentState = PitfallHarryStates.State.CLIMBING;
                }
            }
        }
    }

    /**
     * Move down.
     */
    public void down() {
        if (!gamePlayManager.isEndOfLevel) {
            for (CollidingGameObject collidingGameObject : collidingGameObjectsForPathDecision) {
                if (collidesWithLadder(collidingGameObject) && position.getY() < borderForYCoordinateOnCave) {
                    position.down();
                    gamePlayManager.moveWorldUp(1);
                    currentState = PitfallHarryStates.State.CLIMBING;
                }
            }
        }
    }

    @Override
    public void jump() {
        if (!gamePlayManager.isEndOfLevel) {
            switch (currentState) {
                case RUNNING_RIGHT -> updateCurrentState(PitfallHarryStates.State.JUMPING_RIGHT);
                case RUNNING_LEFT -> updateCurrentState(PitfallHarryStates.State.JUMPING_LEFT);
                case SWINGING -> updateCurrentState(PitfallHarryStates.State.SWING_JUMP);
                default -> updateCurrentState(PitfallHarryStates.State.JUMPING_IDLE);
            }
            isAnimatingJump = true;
            isAnimatingSwing = false;
            gameView.playSound("jump.wav", false);
        }
    }

    private void updateCurrentState(PitfallHarryStates.State state) {
        currentState = state;
    }

    /**
     * Resets Objects position to start position in case of its "death".
     */
    public void resetPosition() {
        position.updateCoordinates(startPosition);
    }

    /**
     * Adds an object to the game objects for path decision.
     *
     * @param gameObject Import gameObject
     */
    public void addGameObjectForPathDecision(CollidingGameObject gameObject) {
        collidingGameObjectsForPathDecision.add(gameObject);
    }

    /**
     * Clears the list of colliding game objects for path decision.
     */
    public void clearGameObjectsForPathDecision() {
        var iterator = collidingGameObjectsForPathDecision.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    private boolean collidesWithLadder(CollidingGameObject collidingGameObject) {
        return collidesWith(collidingGameObject) && collidingGameObject instanceof Ladder;
    }

    private boolean collidesWithObjectExceptLadder(CollidingGameObject collidingGameObject) {
        return collidesWith(collidingGameObject) && !(collidingGameObject instanceof Ladder);
    }

    private boolean notAllowedToMoveRight(CollidingGameObject collidingGameObject) {
        return (collidesWithLadder(collidingGameObject) && !(position.getY() == startPosition.getY() || position.getY() == borderForYCoordinateOnCave)) || collidesWithObjectExceptLadder(collidingGameObject);
    }

    private boolean notAllowedToMoveLeft(CollidingGameObject collidingGameObject) {
        return (collidesWithLadder(collidingGameObject) && !(position.getY() == startPosition.getY() || position.getY() == borderForYCoordinateOnCave)) || collidesWithObjectExceptLadder(collidingGameObject);
    }

    private boolean yCoordinateHasToBeReset() {
        return position.getY() != startPosition.getY() && !(position.getY() == borderForYCoordinateOnCave);
    }

    private void shiftToNextSequence() {
        gamePlayManager.moveWorldToLeft(1280);
        position.updateCoordinates(30, position.getY());
    }

    private void shiftToPreviousSequence() {
        gamePlayManager.moveWorldToRight(1280);
        position.updateCoordinates(1260, position.getY());
    }

    /**
     * Sets the currentState idle in case no keys are pressed.
     */
    public void changeIdleState() {
        currentState = PitfallHarryStates.State.IDLE;
    }

    private boolean isEndOfLevel() {
        return gamePlayManager.isEndOfLevel;
    }

    /**
     * Shows if harry is currently climbing.
     *
     * @return boolean
     */
    public boolean isClimbing() {
        return currentState == PitfallHarryStates.State.CLIMBING;
    }
}
