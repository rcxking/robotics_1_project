/*
 * ArrayToVector.java - Class that converts a Java Character Array to
 * a Java Vector object.
 * 
 * Bryant Pong
 * CSCI-4480
 * 11/17/14
 * 
 * Last Updated: 11/18/14 - 1:46 PM
 */

import java.util.Vector;

public class ArrayToVector {

	// Private Member Variables
	private Character[][] stringArray_;
	
	// ArrayToVector Constructor
	public ArrayToVector(Character[][] stringArray) {
		stringArray_ = stringArray;
	} // End Constructor ArrayToVector()
	
	// This function converts the stringArray_ into a 2D Vector:
	public Vector<Vector<Character>> convertArray() {
		
		Vector<Vector<Character>> temp = new Vector<Vector<Character>>();
		
		for(int i = 0; i < stringArray_.length; i++) {
			
			Vector<Character> nextRow = new Vector<Character>();
			
			for(int j = 0; j < stringArray_[i].length; j++) {
				nextRow.addElement(stringArray_[i][j]);
			} // End for
			
			temp.addElement(nextRow);
		} // End for
		
		return temp;
		
	} // End function convertArray()
	
	public Character[][] getStringArray() {
		return stringArray_;
	} // End function getStringArray()
	
} // End class ArrayToVector
