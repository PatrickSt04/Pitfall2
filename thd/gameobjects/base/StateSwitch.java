package thd.gameobjects.base;

/**
 * Switches the state of a given Enumeration state type.
 *
 * @param <T> Enumeration Type
 */
public class StateSwitch<T extends Enum<T>> {
    private T[] states;
    private T currentState;
    private T initialState;

    /**
     * Constructs the StateSwitch class.
     *
     * @param initialState starting State
     * @param states       values of possible states
     */
    @SafeVarargs
    public StateSwitch(T initialState, T... states) {
        this.states = states;
        this.currentState = initialState;
        this.initialState = initialState;
    }

    /**
     * Performs the switch of a state.
     *
     * @return next state
     */
    public T switchState() {
        int nextState = (currentState.ordinal() + 1) % states.length;
        currentState = states[nextState];
        return currentState;
    }

    /**
     * Resets the currentState to the initial state.
     */
    public void resetStates() {
        currentState = initialState;
    }
}
