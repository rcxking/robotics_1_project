/*
 * State.java - A representation of a State for the A* Algorithm.
 * 
 *  Bryant Pong
 *  CSCI-4480
 *  11/10/14
 *  
 *  Last Updated: 11/10/14 - 9:00 PM
 */
import java.util.*;

public class State {
	
	/*
	 * Private Member Variables:
	 * current: The current state of the map.
	 * numMoves: The number of moves to reach this state.
	 * previous: The previous state before this one.
	 * stepsToState: A list containing the previous states to this one
	 */
	private Vector<Vector<String>> current_;
	private int numMoves_;
	private Vector<Vector<String>> previous_;
	private Vector<Vector<Vector<String>>> stepsToState_;
	
	// Constructor for a State:
	public State(Vector<Vector<String>> current, int numMoves, 
				 Vector<Vector<String>> previous,
				 Vector<Vector<Vector<String>>> stepsToState) {
		current_ = current;
		numMoves_ = numMoves;
		previous_ = previous;
		stepsToState_ = stepsToState;
	} // End Constructor State()
	
	public int getNumMoves() {
		return numMoves_;
	}
	
	public Vector<Vector<String>> getCurrentState() {
		return current_;
	}
	
	public Vector<Vector<String>> getPrevious() {
		return previous_;
	}
	
	public Vector<Vector<Vector<String>>> getStepsToState() {
		return stepsToState_;
	}
} // End class State.java
