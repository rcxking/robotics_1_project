import java.util.*;

public class AStarTest {
	
	public static void printCurrentState(Vector<Vector<String>> state) {
		
		for(int i = 0; i < state.size(); i++) {
			for(int j = 0; j < state.get(0).size(); j++) {
				System.out.print(state.get(i).get(j));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println("Now running A* Algorithm:");
		
		/*
		 * The test map is the following one:
		 * 
		 * S X X 0 0 X
		 * 0 0 0 0 0 X
		 * 0 0 0 X 0 0
		 * 0 X 0 X 0 E
		 * 
		 * Where:
    	 * 1) "S" denotes the starting point.
    	 * 2) "E" denotes the ending points.
    	 * 3) "X" represents an obstacle.
    	 * 4) "0" represents a free space.
		 *
    	 * The robot can move in any direction (even diagonally).  Thus, the
    	 * shortest path in the above map is:
    	 * S X X 0 0 X
    	 * 0 1 2 3 0 X 
    	 * 0 0 0 X 4 0
    	 * 0 X 0 X 0 5 
		 *
    	 * The starting point "S" is at (0, 0) and the ending point "E" is at (3, 5)
    	 * (0-Indexed).
		 */
		
		// Representation of the map:
		Vector<Vector<String>> map = new Vector<Vector<String>>(4);
		Vector<String> row1 = new Vector<String>(6);
		row1.addElement("S");
		row1.addElement("X");
		row1.addElement("X");
		row1.addElement("0");
		row1.addElement("0");
		row1.addElement("X");
		
		Vector<String> row2 = new Vector<String>(6);
		row2.addElement("0");
		row2.addElement("0");
		row2.addElement("0");
		row2.addElement("0");
		row2.addElement("0");
		row2.addElement("X");
		
		Vector<String> row3 = new Vector<String>(6);
		row3.addElement("0");
		row3.addElement("0");
		row3.addElement("0");
		row3.addElement("X");
		row3.addElement("0");
		row3.addElement("0");
		
		Vector<String> row4 = new Vector<String>(6);
		row4.addElement("0");
		row4.addElement("X");
		row4.addElement("0");
		row4.addElement("X");
		row4.addElement("0");
		row4.addElement("E");
		
		map.addElement(row1);
		map.addElement(row2);
		map.addElement(row3);
		map.addElement(row4);
		
		//System.out.println("The current map is: ");
		//printCurrentState(map);
		
		// Run the A* Algorithm:
		AStar aStar = new AStar(map);
		aStar.AStarAlgorithm();
		
		System.out.println("A* Algorithm Completed!");
	} // End function main()
}
