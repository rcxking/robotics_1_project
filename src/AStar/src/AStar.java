/*
 * The AStar object executes the A* algorithm on a given map.
 * 
 * It returns a path from the given starting location to the given ending location.
 * 
 * Bryant Pong
 * CSCI-4480
 * 11/10/14
 * 
 * Last Updated: 11/17/14 - 6:31 PM
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
		System.out.println("startBoard is: ");
		printBoard(startBoard);
		System.out.println("endState is: ");
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
			} // End for
			System.out.println();
		} // End for
		System.out.println();
	} // End function printBoard()
	
	// This function compares whether two boards are the same:
	public boolean areBoardsSame(Vector<Vector<String>> board1, Vector<Vector<String>> board2) {
		
		for(int i = 0; i < board1.size(); i++) {
			for(int j = 0; j < board1.get(0).size(); j++) {
				if(!(board1.get(i).get(j).equalsIgnoreCase(board2.get(i).get(j)))) {
					return false;
				} // End if
			} // End for
		} // End for
		return true;
	} // End function areBoardsSame()
	
	// This function checks whether a board has been seen in a list of boards:
	public boolean hasBoardBeenExplored(Vector<Vector<String>> boardToFind, 
										Vector<Vector<Vector<String>>> listOfBoards) {
		
		for(int i = 0; i < listOfBoards.size(); i++) {
			if(areBoardsSame(boardToFind, listOfBoards.get(i))) {
				 return true;
			} // End if
		} // End for
		return false;
	} // End function hasBoardBeenExplored()
	
	/* This function returns a move.  A move can be in any of the 8 adjacent squares:
	 * 
	 * The location is specified by a number between 0 and 7.  This number corresponds
	 * to the following moves:
	 * 
	 * 0 1 2
	 * 3 C 4
	 * 5 6 7
	 */
	public Vector<Vector<String>> makeMove(Vector<Vector<String>> currentBoard, 
			                               int row, int col, 
			                               int loc) {
		
		// Make a copy of the current board:
		Vector<Vector<String>> nextMove = new Vector<Vector<String>>();
		
		for(int i = 0; i < currentBoard.size(); i++) {
			
			Vector<String> temp = new Vector<String>();
			
			for(int j = 0; j < currentBoard.get(i).size(); j++) {
				temp.addElement(currentBoard.get(i).get(j));
			} // End for
			
			nextMove.addElement(temp);
		} // End for
			
		// Make the appropriate move:
		switch(loc) {
		
			// Case 0: Upper-left corner:
			case 0:
				nextMove.get(row).set(col, "0");
				nextMove.get(row-1).set(col - 1, "C");
				break;
			// Case 1: Upper-Middle:
			case 1:
				nextMove.get(row).set(col, "0");
				nextMove.get(row-1).set(col, "C");
				break;
			// Case 2: Upper-right corner
			case 2:
				nextMove.get(row).set(col, "0");
				nextMove.get(row-1).set(col + 1, "C");
				break;
			// Case 3: Left Middle
			case 3:
				nextMove.get(row).set(col, "0");
				nextMove.get(row).set(col - 1, "C");
				break;
			// Case 4: Right Middle
			case 4:
				nextMove.get(row).set(col, "0");
				nextMove.get(row).set(col + 1, "C");
				break;
			// Case 5: Lower-left corner
			case 5:
				nextMove.get(row).set(col, "0");
				nextMove.get(row + 1).set(col - 1, "C");
				break;
			// Case 6: Lower Middle
			case 6:
				nextMove.get(row).set(col, "0");
				nextMove.get(row + 1).set(col, "C");
				break;
			// Case 7: Lower-right corner
			case 7:
				nextMove.get(row).set(col, "0");
				nextMove.get(row + 1).set(col + 1, "C");
				break;

			// We should never reach this state:
			default:
				System.err.println("ERROR: INVALID MOVE");
				System.exit(1);
				break;
			
		} // End switch
		
		//System.out.println("nextMove is: ");
		//printBoard(nextMove);
		
		return nextMove;
	} // End function makeMove()
	
	// This function returns a list of all the possible moves in a given state:
	public Vector<Vector<Vector<String>>> possibleMoves(State currentState) {
		
		// Vector of possible moves:
		Vector<Vector<Vector<String>>> moves = new Vector<Vector<Vector<String>>>();
		
		// Copy of the current map:
		Vector<Vector<String>> copyMap = new Vector<Vector<String>>(currentState.getCurrentState());
		
		// Find the current X and Y location:
		int row, col;
		row = col = 0;
		
		for(int i = 0; i < currentState.getCurrentState().size(); i++) {
			for(int j = 0; j < currentState.getCurrentState().get(i).size(); j++) {
				
				if(currentState.getCurrentState().get(i).get(j).equalsIgnoreCase("C")) {
					// We found the current location!
					
					col = j;
					row = i;
					
					break;
				} // End if
			} // End for
		} // End for
		
	
		// DEBUG ONLY - Print out the value of the current position:
		System.out.println("The currentLocation is at: (" + col + "," + row + ")");
		
		/*
		// DEBUG ONLY - Print out the copy of the map:
		copyMap.get(0).set(0, "69");
		System.out.println("copyMap is: " + copyMap);
		System.out.println("currentState.getCurrentState is: " + currentState.getCurrentState());
		*/
		
		/*
		 * In a given state, there can be up to 7 possible moves.  These are:
		 * 
		 * 0 1 2
		 * 3 C 4
		 * 5 6 7
		 * 
		 * We will need to check these 7 possible moves.
		 */
		
		// Move 0 is clear!
		if((row - 1) >= 0 && (col - 1) >= 0 && !copyMap.get(row-1).get(col - 1).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 0);
			
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 0 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
		
		//System.out.println("After ")
		
		// Move 1 is clear!
		if((row - 1) >= 0 && !copyMap.get(row-1).get(col).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 1);
		
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 1 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
		
		// Move 2 is clear!
		if((row - 1) >= 0 && (col + 1) < copyMap.get(0).size() && !copyMap.get(row-1).get(col + 1).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 2);
			
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 2 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
		
		// Move 3 is clear!
		if((col - 1) >= 0 && !copyMap.get(row).get(col - 1).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 3);
				
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 3 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
		
		// Move 4 is clear!
		if((col + 1) < copyMap.get(0).size() && !copyMap.get(row).get(col + 1).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 4);
						
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 4 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
				
		// Move 5 is clear!
		if((row + 1) < copyMap.size() && (col - 1) >= 0 && !copyMap.get(row+1).get(col - 1).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 5);
						
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 5 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
				
		// Move 6 is clear!
		if((row + 1) < copyMap.size() && !copyMap.get(row+1).get(col).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 6);
					
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 6 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
				
		// Move 7 is clear!
		if((row + 1) < copyMap.size() && (col + 1) < copyMap.get(0).size()
				&& !copyMap.get(row+1).get(col+1).equalsIgnoreCase("X")) {
			Vector<Vector<String>> temp = makeMove(copyMap, row, col, 7);
					
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				System.out.println("Move 7 is unique!");
				moves.addElement(temp);
			} // End if
		} // End if
			
		return moves;
	} // End for
	
	public void AStarAlgorithm() {
		
		stepsToHere.addElement(startBoard);
		
		Vector<Vector<String>> emptyBoard = new Vector<Vector<String>>();
		for(int i = 0; i < startBoard.size(); i++) {
			Vector<String> nextRow = new Vector<String>();
			
			for(int j = 0; j < startBoard.get(i).size(); j++) {
				nextRow.addElement("0");
			} // End for
			emptyBoard.addElement(nextRow);
		} // End for
		
		// Create the initial state:
		State initialState = new State(startBoard, 0, emptyBoard, stepsToHere);
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
				
				System.out.println("Size of getStepsToState: " + nextState.getStepsToState().size());
				
				System.out.println("Number of steps from start to finish: " + nextState.getNumMoves());
				break;
			}
			
			// Find all possible moves that we can make:
			Vector<Vector<Vector<String>>> possiblemoves = possibleMoves(nextState);
			
			
			// For each possible move, create and add a new state to the priority queue:
			for(int i = 0; i < possiblemoves.size(); i++) {
				
				Vector<Vector<Vector<String>>> stepsToHere = nextState.getStepsToState();
				stepsToHere.addElement(possiblemoves.get(i));
				
				State newState = new State(possiblemoves.get(i), nextState.getNumMoves() + 1, 
										   nextState.getCurrentState(), stepsToHere);
				
				if(!hasBoardBeenExplored(possiblemoves.get(i), exploredBoards)) {
					pq.add(newState);
				}
			} // End for
			
			
			if(pq.isEmpty()) {
				System.err.println("No Solution Found!");
				break;
			}
			
		}
	} // End function AStarAlgorithm()
	
} // End class AStar()
