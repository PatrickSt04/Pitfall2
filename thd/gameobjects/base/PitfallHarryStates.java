package thd.gameobjects.base;

/**
 * Contains all states of PitfallHarry class in order to prevent Code of main class to be hard to read.
 */
public class PitfallHarryStates {
    /**
     * Main characters states.
     */
    public enum State {IDLE, DAMAGED, RUNNING_LEFT, RUNNING_RIGHT, JUMPING_LEFT, JUMPING_RIGHT, JUMPING_IDLE, CLIMBING, SWINGING, SWING_JUMP, FALLING}

    /**
     * Running right state.
     */
    public enum RunningRightState {
        /**
         * States.
         */
        RUNNING_1("walk_1.png"), RUNNING_2("walk_2.png"), RUNNING_3("walk_3.png"), RUNNING_4("walk_4.png"), RUNNING_5("walk_5.png");
        /**
         * Represents the png.
         */
        public final String display;

        RunningRightState(String display) {
            this.display = display;
        }
    }

    /**
     * Running left state.
     */
    public enum RunningLeftState {
        /**
         * States.
         */
        RUNNING_1("walk_left_1.png"), RUNNING_2("walk_left_2.png"), RUNNING_3("walk_left_3.png"), RUNNING_4("walk_left_4.png"), RUNNING_5("walk_left_5.png");
        /**
         * Represents the png.
         */
        public final String display;

        RunningLeftState(String display) {
            this.display = display;
        }
    }

    /**
     * Idle state.
     */
    public enum IdleState {
        /**
         * States.
         */
        IDLE("walk_1.png");
        /**
         * Represents the png.
         */
        public final String display;

        IdleState(String display) {
            this.display = display;
        }
    }

    /**
     * Jumping right state.
     */
    public enum JumpingRightState {
        /**
         * States.
         */
        JUMPING_1("walk_1.png", 0), JUMPING_2("walk_3.png", 50), JUMPING_3("walk_3.png", 30), JUMPING_4("walk_3.png", 0), JUMPING_5("walk_3.png", 0), JUMPING_6("walk_3.png", -30), JUMPING_7("walk_3.png", -50), JUMPING_8("walk_1.png", 0);
        /**
         * Represents the png.
         */
        public final String display;
        /**
         * Distance for upwards movement.
         */
        public final int up;

        JumpingRightState(String display, int up) {
            this.display = display;
            this.up = up;
        }
    }

    /**
     * Jumping left state.
     */
    public enum JumpingLeftState {
        /**
         * States.
         */
        JUMPING_1("walk_left_1.png", 0), JUMPING_2("walk_left_3.png", 50), JUMPING_3("walk_left_3.png", 30), JUMPING_4("walk_left_3.png", 0), JUMPING_5("walk_left_3.png", 0), JUMPING_6("walk_left_3.png", -30), JUMPING_7("walk_left_3.png", -50), JUMPING_8("walk_left_1.png", 0);
        /**
         * Represents the png.
         */
        public final String display;
        /**
         * Distance for upwards movement.
         */
        public final int up;

        JumpingLeftState(String display, int up) {
            this.display = display;
            this.up = up;
        }
    }

    /**
     * Climbing state.
     */
    public enum ClimbingState {
        /**
         * States.
         */
        CLIMBING_1("climb_1.png"), CLIMBING_2("climb_2.png"), CLIMBING_3("climb_3.png"), CLIMBING_4("climb_4.png"), CLIMBING_5("climb_5.png");
        /**
         * Represents the png.
         */
        public final String display;

        ClimbingState(String display) {
            this.display = display;
        }
    }

    /**
     * Swinging state.
     */
    public enum SwingingState {
        /**
         * States.
         */
        SWINGING_1("swing_1.png"), SWINGING_2("swing_2.png"), SWINGING_3("swing_3.png");
        /**
         * Represents the png.
         */
        public final String display;

        SwingingState(String display) {
            this.display = display;
        }
    }

    /**
     * Swing jump state.
     */
    public enum SwingJumpState {
        /**
         * States.
         */
        JUMPING_1("walk_1.png", 0), JUMPING_2("walk_3.png", 70), JUMPING_3("walk_3.png", 50), JUMPING_4("walk_3.png", 0), JUMPING_5("walk_3.png", 0), JUMPING_6("walk_3.png", -50), JUMPING_7("walk_3.png", -70), JUMPING_8("walk_1.png", 0);
        /**
         * Represents the png.
         */
        public final String display;
        /**
         * Distance for upwards movement.
         */
        public final int up;

        SwingJumpState(String display, int up) {
            this.display = display;
            this.up = up;
        }
    }

    /**
     * Damaged state.
     */
    public enum DamagedState {
        /**
         * States.
         */
        DAMAGED_1("die_1.png"), DAMAGED_2("die_2.png"), DAMAGED_3("die_3.png"), DAMAGED_4("die_4.png");
        /**
         * Represents the png.
         */
        public final String display;

        DamagedState(String display) {
            this.display = display;
        }
    }

    /**
     * Falling state.
     */
    public enum FallingState {
        /**
         * States.
         */
        FALLING_1("walk_1.png");
        /**
         * Represents the png.
         */
        public final String display;

        FallingState(String display) {
            this.display = display;
        }
    }

}
