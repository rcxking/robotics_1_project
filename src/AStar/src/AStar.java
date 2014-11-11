/*
 * The AStar object executes the A* algorithm on a given map.
 * 
 * It returns a path from the given starting location to the given ending location.
 * 
 * Bryant Pong
 * CSCI-4480
 * 11/10/14
 * 
 * Last Updated: 11/11/14 - 2:30 PM
 */

// Data Structures:
import java.util.*; // Needed for Java's Priority Queue and Vector



public class AStar {

	/*
	 *  Private Member Variables:
	 *  
	 *  1) stepsToHere: A vector containing the steps to get to this state.
	 *  2) pq: Priority Queue used to hold the states to explore. 
	 *  3) endState: This is the state where the goal has been reached.
	 *  4)
	 *  5) desX: The X-Location of the ending destination.
	 *  6) desY: The Y-Location of the ending destination.
	 *  7) exploredBoards: A vector containing the already explored steps:
	 */
	private Vector<Vector<Vector<String>>> exploredBoards;
	private Vector<Vector<Vector<String>>> stepsToHere;
	private PriorityQueue<State> pq;
	//private Vector<Vector<Vector<String>>> endState;
	private Vector<Vector<String>> startBoard;
	
	private int desX;
	private int desY;
	
	StateComparator stateComparator = new StateComparator();
	
	class StateComparator implements Comparator<State> {
		@Override
		public int compare(State s1, State s2) {
			return ( (s1.getNumMoves() + calculateManhattanDistance(s1.getCurrentState()) > 
			(s2.getNumMoves() + calculateManhattanDistance(s2.getCurrentState())))) ? 1 : -1;
		}
	} // End Priority Queue Comparator
	
	/*
	 *  Constructor for the AStar Object:
	 *  
	 *  Parameters:
	 *  1) Start Board - The starting map.
	 */
	public AStar(Vector<Vector<String>> startingBoard){
		
		
		exploredBoards = new Vector<Vector<Vector<String>>>();
		stepsToHere = new Vector<Vector<Vector<String>>>();
		pq = new PriorityQueue<State>(10, stateComparator);
		startBoard = startingBoard;
		
		// Find the coordinates of the ending location and replace the "S" with a "C":
		for(int i = 0; i < startBoard.size(); i++) {
			for(int j = 0; j < startBoard.get(0).size(); j++) {
				
				// We found the "S"!
				if(startBoard.get(i).get(j).equalsIgnoreCase("S")) {
					startBoard.get(i).set(j, "C");
				}
				
				// We found the "E"!
				if(startBoard.get(i).get(j).equalsIgnoreCase("E")) {
					// Set the desX and desY member variables:
					desX = j;
					desY = i;
					
					// Remove the "E":
					startBoard.get(i).set(j, "0");
					break;
				}
			}
		}
		
		System.out.println("The Starting Board is:");
		printBoard(startBoard);
		System.out.println("The Ending Location is at: (" + desX + "," + desY + ")");
		System.out.println("The starting Manhattan Distance is: " + calculateManhattanDistance(startBoard));
		System.out.println("AStar Constructor Successfully Completed!");
	}
	
	/*
	 *  This function calculates the Manhattan Distance from a current board
	 *  to the end state:
	 *  
	 *  Parameters:
	 *  
	 */
	public int calculateManhattanDistance(Vector<Vector<String>> currentBoard) {
		
		int manhattanDistance = 0;
		
		// Find the current location on the currentBoard:
		for(int i = 0; i < currentBoard.size(); i++) {
			for(int j = 0; j < currentBoard.get(i).size(); j++) {
				
				if(currentBoard.get(i).get(j).equalsIgnoreCase("C")) {
					
					manhattanDistance = (Math.abs(i - desY) + Math.abs(j - desX));
					break;
				} // End if
			} // End for
		} // End for
		
		return manhattanDistance;
	} // End function calculateManhattanDistance()
	
	// This function prints out a board:
	public void printBoard(Vector<Vector<String>> state) {
		
		for(int i = 0; i < state.size(); i++) {
			for(int j = 0; j < state.get(i).size(); j++) {
				System.out.print(state.get(i).get(j));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void AStarAlgorithm() {
		
		/*
		// DEBUG ONLY - Testing Priority Queue:
		Vector<Vector<String>> map = new Vector<Vector<String>>(5);
		Vector<String> row1 = new Vector<String>(5);
		row1.addElement("0");
		row1.addElement("X");
		row1.addElement("X");
		row1.addElement("0");
		row1.addElement("0");
		
		Vector<String> row2 = new Vector<String>(5);
		row2.addElement("0");
		row2.addElement("0");
		row2.addElement("0");
		row2.addElement("0");
		row2.addElement("0");
		
		Vector<String> row3 = new Vector<String>(5);
		row3.addElement("0");
		row3.addElement("0");
		row3.addElement("0");
		row3.addElement("X");
		row3.addElement("0");
		
		Vector<String> row4 = new Vector<String>(5);
		row4.addElement("0");
		row4.addElement("X");
		row4.addElement("0");
		row4.addElement("X");
		row4.addElement("0");
		
		Vector<String> row5 = new Vector<String>(5);
		row5.addElement("0");
		row5.addElement("0");
		row5.addElement("0");
		row5.addElement("0");
		row5.addElement("C");
		
		map.addElement(row1);
		map.addElement(row2);
		map.addElement(row3);
		map.addElement(row4);
		map.addElement(row5);
		
		//State s1 = new State(startBoard, 0, null, null);
		//State s2 = new State(map, 0, null, null);
		//pq.add(s1);
		//pq.add(s2);
		 * */
		stepsToHere.addElement(startBoard);
		// Create the initial state:
		State initialState = new State(startBoard, 0, null, stepsToHere);
		pq.add(initialState);
		
		// Begin the A* Algorithm:  
		while(!pq.isEmpty()) {
			
			// Get the next state to process:
			State nextState = pq.poll();
			
			System.out.println("The nextState is: ");
			printBoard(nextState.getCurrentState());
			exploredBoards.addElement(nextState.getCurrentState());
			
		
			/*
			if(pq.isEmpty()) {
				System.err.println("No Solution Found!");
				break;
			}
			*/
		}
	} // End function AStarAlgorithm()
	
} // End class AStar()
