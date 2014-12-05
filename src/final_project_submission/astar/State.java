/*
 * State.java - A representation of a State for the A* Algorithm.
 * 
 *  Bryant Pong
 *  CSCI-4480
 *  11/10/14
 *  
 *  Last Updated: 11/11/14 - 7:45 PM
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
		//current_ = current;
		numMoves_ = numMoves;
		//previous_ = previous;
		//stepsToState_ = stepsToState;
		
		current_ = new Vector<Vector<String>>();
		previous_ = new Vector<Vector<String>>();
		stepsToState_ = new Vector<Vector<Vector<String>>>();
		
		// Copy the Current Vector:
		for(int i = 0; i < current.size(); i++) {
			Vector<String> temp = new Vector<String>();
			
			for(int j = 0; j < current.get(i).size(); j++) {
				temp.add(current.get(i).get(j));
			} // End for
			
			System.out.println("temp is: " + temp);
			
			current_.addElement(temp);
		} // End for
		
		// Copy the Previous Vector:
		for(int i = 0; i < previous.size(); i++) {
			Vector<String> temp = new Vector<String>();
			
			for(int j = 0; j < previous.get(i).size(); j++) {
				temp.add(previous.get(i).get(j));
			} // End for
			previous_.addElement(temp);
		} // End for
		
		// Copy the stepsToState:
		for(int i = 0; i < stepsToState.size(); i++) {
			Vector<Vector<String>> temp = new Vector<Vector<String>>();
			
			for(int j = 0; j < stepsToState.get(i).size(); j++) {
				
				Vector<String> nextRow = new Vector<String>();
				
				for(int k = 0; k < stepsToState.get(i).get(j).size(); k++) {
					nextRow.addElement(stepsToState.get(i).get(j).get(k));
				} // End for
				
				temp.addElement(nextRow);
			} // End for
				
			
			stepsToState_.addElement(temp);
		} // End for
		
		System.out.println("End of State Constructor.  Variables initialized to: ");
		System.out.println("current_:" + getCurrentState());
		
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