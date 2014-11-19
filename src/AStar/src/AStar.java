/*
 * The AStar object executes the A* algorithm on a given map.
 * 
 * It returns a path from the given starting location to the given ending location.
 * 
 * Bryant Pong
 * CSCI-4480
 * 11/10/14
 * 
 * Last Updated: 11/19/14 - 3:00 PM
 */

// Data Structures:
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.Comparator;

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
	private Vector<Vector<Vector<Character>>> exploredBoards;
	private Vector<Vector<Vector<Character>>> stepsToHere;
	private PriorityQueue<State> pq;
	private Vector<Vector<Character>> endState;
	private Vector<Vector<Character>> startBoard;
	
	// The destination coordinate:
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
	public AStar(Vector<Vector<Character>> startingBoard){
		
		
		exploredBoards = new Vector<Vector<Vector<Character>>>(100);
		stepsToHere = new Vector<Vector<Vector<Character>>>(10);
		pq = new PriorityQueue<State>(15, stateComparator);
		startBoard = new Vector<Vector<Character>>();
		endState = new Vector<Vector<Character>>();
		
		for(int i = 0; i < startingBoard.size(); i++) {
			
			Vector<Character> startRow = new Vector<Character>(startingBoard.get(0).size());
			Vector<Character> endRow = new Vector<Character>(startingBoard.get(0).size());
			
			for(int j = 0; j < startingBoard.get(0).size(); j++) {
							
				if(startingBoard.get(i).get(j) == 'S') {
					startRow.add('C');
					endRow.add('0');
				} else if(startingBoard.get(i).get(j) == 'E') {
					startRow.add('0');
					endRow.add('C');
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
	public int calculateManhattanDistance(Vector<Vector<Character>> currentBoard) {
		
		int manhattanDistance = 0;
		
		// Find the current location on the currentBoard:
		for(int i = 0; i < currentBoard.size(); i++) {
			for(int j = 0; j < currentBoard.get(i).size(); j++) {
				
				if(currentBoard.get(i).get(j) == 'C') {
					
					manhattanDistance = (Math.abs(i - desY) + Math.abs(j - desX));
					break;
				} // End if
			} // End for
		} // End for
		
		return manhattanDistance;
	} // End function calculateManhattanDistance()
	
	// This function prints out a board:
	public void printBoard(Vector<Vector<Character>> state) {
		
		for(int i = 0; i < state.size(); i++) {
			for(int j = 0; j < state.get(i).size(); j++) {
				System.out.print(state.get(i).get(j));
			} // End for
			System.out.println();
		} // End for
		System.out.println();
	} // End function printBoard()
	
	// This function compares whether two boards are the same:
	public boolean areBoardsSame(Vector<Vector<Character>> board1, Vector<Vector<Character>> board2) {
		
		for(int i = 0; i < board1.size(); i++) {
			for(int j = 0; j < board1.get(0).size(); j++) {
				if(!(board1.get(i).get(j) == board2.get(i).get(j))) {
					return false;
				} // End if
			} // End for
		} // End for
		return true;
	} // End function areBoardsSame()
	
	// This function checks whether a board has been seen in a list of boards:
	public boolean hasBoardBeenExplored(Vector<Vector<Character>> boardToFind, 
										Vector<Vector<Vector<Character>>> listOfBoards) {
		
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
	public Vector<Vector<Character>> makeMove(Vector<Vector<Character>> currentBoard, 
			                               int row, int col, 
			                               int loc) {
		
		// Make a copy of the current board:
		Vector<Vector<Character>> nextMove = new Vector<Vector<Character>>();
		
		for(int i = 0; i < currentBoard.size(); i++) {
			
			Vector<Character> temp = new Vector<Character>();
			
			for(int j = 0; j < currentBoard.get(i).size(); j++) {
				temp.addElement(currentBoard.get(i).get(j));
			} // End for
			
			nextMove.addElement(temp);
		} // End for
			
		// Make the appropriate move:
		switch(loc) {
		
			// Case 0: Upper-left corner:
			case 0:
				nextMove.get(row).set(col, '0');
				nextMove.get(row-1).set(col - 1, 'C');
				break;
			// Case 1: Upper-Middle:
			case 1:
				nextMove.get(row).set(col, '0');
				nextMove.get(row-1).set(col, 'C');
				break;
			// Case 2: Upper-right corner
			case 2:
				nextMove.get(row).set(col, '0');
				nextMove.get(row-1).set(col + 1, 'C');
				break;
			// Case 3: Left Middle
			case 3:
				nextMove.get(row).set(col, '0');
				nextMove.get(row).set(col - 1, 'C');
				break;
			// Case 4: Right Middle
			case 4:
				nextMove.get(row).set(col, '0');
				nextMove.get(row).set(col + 1, 'C');
				break;
			// Case 5: Lower-left corner
			case 5:
				nextMove.get(row).set(col, '0');
				nextMove.get(row + 1).set(col - 1, 'C');
				break;
			// Case 6: Lower Middle
			case 6:
				nextMove.get(row).set(col, '0');
				nextMove.get(row + 1).set(col, 'C');
				break;
			// Case 7: Lower-right corner
			case 7:
				nextMove.get(row).set(col, '0');
				nextMove.get(row + 1).set(col + 1, 'C');
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
	public Vector<Vector<Vector<Character>>> possibleMoves(State currentState) {
		
		// Vector of possible moves:
		Vector<Vector<Vector<Character>>> moves = new Vector<Vector<Vector<Character>>>();
		
		// Copy of the current map:
		Vector<Vector<Character>> copyMap = new Vector<Vector<Character>>(currentState.getCurrentState());
		
		// Find the current X and Y location:
		int row, col;
		row = col = 0;
		
		for(int i = 0; i < currentState.getCurrentState().size(); i++) {
			for(int j = 0; j < currentState.getCurrentState().get(i).size(); j++) {
				
				if(currentState.getCurrentState().get(i).get(j) =='C') {
					// We found the current location!
					
					col = j;
					row = i;
					
					break;
				} // End if
			} // End for
		} // End for
		
	
		// DEBUG ONLY - Print out the value of the current position:
		//System.out.println("The currentLocation is at: (" + col + "," + row + ")");
		
		/*
		 * In a given state, there can be up to 7 possible moves.  These are:
		 * 
		 * 0 1 2
		 * 3 C 4
		 * 5 6 7
		 * 
		 * We will need to check these 7 possible moves.
		 */
		
		Vector<Vector<Character>> temp;
		
		// Move 0 is clear!
		if((row - 1) >= 0 && (col - 1) >= 0 && copyMap.get(row-1).get(col - 1) != 'X') {
			temp = makeMove(copyMap, row, col, 0);
			
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
		
		//System.out.println("After ")
		
		// Move 1 is clear!
		if((row - 1) >= 0 && copyMap.get(row-1).get(col) != 'X') {
			temp = makeMove(copyMap, row, col, 1);
		
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
		
		// Move 2 is clear!
		if((row - 1) >= 0 && (col + 1) < copyMap.get(0).size() && copyMap.get(row-1).get(col + 1) != 'X') {
			temp = makeMove(copyMap, row, col, 2);
			
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
		
		// Move 3 is clear!
		if((col - 1) >= 0 && copyMap.get(row).get(col - 1) != 'X') {
			temp = makeMove(copyMap, row, col, 3);
				
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
		
		// Move 4 is clear!
		if((col + 1) < copyMap.get(0).size() && copyMap.get(row).get(col + 1) != 'X') {
			temp = makeMove(copyMap, row, col, 4);
						
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
				
		// Move 5 is clear!
		if((row + 1) < copyMap.size() && (col - 1) >= 0 && copyMap.get(row+1).get(col - 1) != 'X') {
			temp = makeMove(copyMap, row, col, 5);
						
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
				
		// Move 6 is clear!
		if((row + 1) < copyMap.size() && copyMap.get(row+1).get(col) != 'X') {
			temp = makeMove(copyMap, row, col, 6);
					
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
				
		// Move 7 is clear!
		if((row + 1) < copyMap.size() && (col + 1) < copyMap.get(0).size()
				&& copyMap.get(row+1).get(col+1) != 'X') {
			temp = makeMove(copyMap, row, col, 7);
					
			if(!areBoardsSame(temp, currentState.getPrevious())) {
				// These boards are different!
				moves.addElement(temp);
			} // End if
		} // End if
			
		return moves;
	} // End for
	
	public int[][] AStarAlgorithm() {
		
		// This Array will hold the points to navigate to in (row, col):
		int[][] solution;
		
		stepsToHere.addElement(startBoard);
		
		Vector<Vector<Character>> emptyBoard = new Vector<Vector<Character>>();
		Vector<Character> rowOfZeros = new Vector<Character>(startBoard.size());
	
		for(int i = 0; i < startBoard.get(0).size(); i++) {
			rowOfZeros.addElement('0');
		} // End for
		System.out.println("rowOfZeros is: " + rowOfZeros);
		
		for(int i = 0; i < startBoard.size(); i++) {
			emptyBoard.addElement(rowOfZeros);
		}
		
		System.out.println("emptyBoard is:");
		printBoard(emptyBoard);
		
		// Create the initial state:
		State initialState = new State(startBoard, 0, emptyBoard, stepsToHere);
		pq.add(initialState);
		
		// Begin the A* Algorithm:  
		while(!pq.isEmpty()) {
			
			// Get the next state to process and remove it from the priority queue:
			State nextState = pq.poll();
			
			exploredBoards.addElement(nextState.getCurrentState());
			
			if(areBoardsSame(nextState.getCurrentState(), endState)) {
				// We reached the goal!
				
				System.out.println("We reached the goal state!");
				
				for(int i = 0; i < nextState.getStepsToState().size(); i++) {
					printBoard(nextState.getStepsToState().get(i));
				}
				
				System.out.println("Size of getStepsToState: " + nextState.getStepsToState().size());
				
				System.out.println("Number of steps from start to finish: " + nextState.getNumMoves());
				
				solution = new int[nextState.getStepsToState().size()][2];
				
				// Give the coordinates to move to in (row, col) form:
				for(int i = 0; i < nextState.getStepsToState().size(); i++) {
					
					Vector<Vector<Character>> nextStateBoard = nextState.getStepsToState().get(i);
					
					int[] coordinate = new int[2];
					
					for(int j = 0; j < nextStateBoard.size(); j++) {
						for(int k = 0; k < nextStateBoard.get(j).size(); k++) {
							
							if(nextStateBoard.get(j).get(k) == 'C') {
								
								coordinate[0] = j;
								coordinate[1] = k;
								
								break;
							}
						} // End for
					} // End for
					
					solution[i] = coordinate;
					
				} // End for
				
				return solution;
			}
			
			// Find all possible moves that we can make:
			Vector<Vector<Vector<Character>>> possiblemoves = possibleMoves(nextState);
			
		
			// For each possible move, create and add a new state to the priority queue:
			for(int i = 0; i < possiblemoves.size(); i++) {
				
				Vector<Vector<Vector<Character>>> stepsToHere =  new Vector<Vector<Vector<Character>>>(); //nextState.getStepsToState();
				
				for(int j = 0; j < nextState.getStepsToState().size(); j++) {
					stepsToHere.addElement(nextState.getStepsToState().get(j));
				} // End for
				
				stepsToHere.addElement(possiblemoves.get(i));
				
				State newState = new State(possiblemoves.get(i), nextState.getNumMoves() + 1, 
										   nextState.getCurrentState(), stepsToHere);
				
				if(!hasBoardBeenExplored(possiblemoves.get(i), exploredBoards)) {
					pq.add(newState);
				}
			} // End for
			
			
			
			if(pq.isEmpty()) {
				System.err.println("No Solution Found!");
				return null;
			}
			
		}
		
		return null;
	} // End function AStarAlgorithm()
	
} // End class AStar()
