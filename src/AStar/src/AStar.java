/*
 * The AStar object executes the A* algorithm on a given map.
 * 
 * It returns a path from the given starting location to the given ending location.
 * 
 * Bryant Pong
 * CSCI-4480
 * 11/10/14
 * 
 * Last Updated: 11/10/14 - 8:31 PM
 */

// Data Structures:
import java.util.*; // Needed for Java's Priority Queue and Vector

public class AStar {
	
	// 

	/*
	 *  Private Member Variables:
	 *  
	 *  stepsToHere: A vector containing the steps to get to this state.
	 *  pq: Priority Queue used to hold the states to explore. 
	 */
	private Vector<Vector<Vector<String>>> stepsToHere;
	private PriorityQueue<State> pq;
	
	// Constructor for the AStar Object:
	public AStar(){
		stepsToHere = new Vector<Vector<Vector<String>>>();
		pq = new PriorityQueue<State>();
	}
	
	public void AStarAlgorithm() {
		// Begin the A* Algorithm:  
		while(!pq.isEmpty()) {
			
			// Get the next state to process:
			State nextState = pq.poll();
		
		}
		
		 
		
	} // End function AStarAlgorithm()
	
} // End class AStar()
