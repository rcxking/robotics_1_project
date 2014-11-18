/*
 * ArrayToVector.java - Class that converts a Java String Array to
 * a Java Vector object.
 * 
 * Bryant Pong
 * CSCI-4480
 * 11/17/14
 * 
 * Last Updated: 11/17/14 - 9:11 PM
 */

import java.util.Vector;

public class ArrayToVector {

	// Private Member Variables
	private String[][] stringArray_;
	
	// ArrayToVector Constructor
	public ArrayToVector(String[][] stringArray) {
		stringArray_ = stringArray;
	} // End Constructor ArrayToVector()
	
	// This function converts the stringArray_ into a 2D Vector:
	public Vector<Vector<String>> convertArray() {
		
		Vector<Vector<String>> temp = new Vector<Vector<String>>();
		
		for(int i = 0; i < stringArray_.length; i++) {
			
			Vector<String> nextRow = new Vector<String>();
			
			for(int j = 0; j < stringArray_[i].length; j++) {
				nextRow.addElement(stringArray_[i][j]);
			} // End for
			
			temp.addElement(nextRow);
		} // End for
		
		return temp;
		
	} // End function convertArray()
	
	private String[][] getStringArray() {
		return stringArray_;
	} // End function getStringArray()
	
} // End class ArrayToVector
