/*
 * The AStar object executes the A* algorithm on a given map.
 * 
 * It returns a path from the given starting location to the given ending location.
 * 
 * Bryant Pong
 * CSCI-4480
 * 11/10/14
 * 
 * Last Updated: 11/11/14 - 7:44 PM
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
	private Vector<Vector<String>> endState;
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
		startBoard = new Vector<Vector<String>>();
		endState = new Vector<Vector<String>>();
		
		for(int i = 0; i < startingBoard.size(); i++) {
			
			Vector<String> startRow = new Vector<String>(startingBoard.get(0).size());
			Vector<String> endRow = new Vector<String>(startingBoard.get(0).size());
			
			for(int j = 0; j < startingBoard.get(0).size(); j++) {
							
				if(startingBoard.get(i).get(j).equalsIgnoreCase("S")) {
					startRow.add("C");
					endRow.add("0");
				} else if(startingBoard.get(i).get(j).equalsIgnoreCase("E")) {
					startRow.add("0");
					endRow.add("C");
				} else {
					startRow.add(startingBoard.get(i).get(j));
					endRow.add(startingBoard.get(i).get(j));
				}
			} // End for
			
			startBoard.add(startRow);
			endState.add(endRow);
		} // End for
		
		System.out.println("The Starting Board is:");
		printBoard(startingBoard);
		printBoard(startBoard);
		printBoard(endState);
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
	
	// This function compares whether two boards are the same:
	public boolean areBoardsSame(Vector<Vector<String>> board1, Vector<Vector<String>> board2) {
		
		for(int i = 0; i < board1.size(); i++) {
			for(int j = 0; j < board1.get(0).size(); j++) {
				if(!(board1.get(i).get(j).equalsIgnoreCase(board2.get(i).get(j)))) {
					return false;
				}
			}
		}
		return true;
		
	} // End function areBoardsSame()
	
	// This function returns a list of all the possible moves in a given state:
	public Vector<Vector<Vector<String>>> possibleMoves(State currentState) {
		
		// Vector of possible moves:
		Vector<Vector<Vector<String>>> moves = new Vector<Vector<Vector<String>>>();
		
		// Find the current X and Y location:
		int currentXLoc, currentYLoc;
		currentXLoc = currentYLoc = 0;
		
		for(int i = 0; i < currentState.getCurrentState().size(); i++) {
			for(int j = 0; j < currentState.getCurrentState().get(i).size(); j++) {
				
				if(currentState.getCurrentState().get(i).get(j).equalsIgnoreCase("C")) {
					// We found the current location!
					
					currentXLoc = j;
					currentYLoc = i;
					
					break;
				} // End if
			} // End for
		} // End for
		
		// DEBUG ONLY - Print out the value of the current position:
		
		
		return moves;
	} // End for
	
	public void AStarAlgorithm() {
		
		stepsToHere.addElement(startBoard);
		// Create the initial state:
		State initialState = new State(startBoard, 0, null, stepsToHere);
		pq.add(initialState);
		
		// Begin the A* Algorithm:  
		while(!pq.isEmpty()) {
			
			// Get the next state to process and remove it from the priority queue:
			State nextState = pq.poll();
			
			System.out.println("The nextState is: ");
			printBoard(nextState.getCurrentState());
			exploredBoards.addElement(nextState.getCurrentState());
			
			if(areBoardsSame(nextState.getCurrentState(), endState)) {
				// We reached the goal!
				
				System.out.println("We reached the goal state!");
				
				for(int i = 0; i < nextState.getStepsToState().size(); i++) {
					printBoard(nextState.getStepsToState().get(i));
				}
				
				System.out.println("Number of steps from start to finish: " + nextState.getNumMoves());
				break;
			}
			
			if(pq.isEmpty()) {
				System.err.println("No Solution Found!");
				break;
			}
			
		}
	} // End function AStarAlgorithm()
	
} // End class AStar()
