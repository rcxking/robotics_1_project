/*
 * Point.java - Representation of a 2-Dimensional Point on our map.
 * 
 * Bryant Pong
 * CSCI-4480 
 * 11/10/14
 * 
 * Last Updated: 11/10/14 - 8:55 PM
 */
public class Point {
	
	// Private member variables
	// The X/Y Location of this point:
	private int xLoc;
	private int yLoc;
	
	// Constructor for Point:
	public Point(int x, int y) {
		xLoc = x;
		yLoc = y;
	} // End Constructor Point()
	
	// Accessor functions for a Point:
	public int getX() {
		return xLoc;
	} // End function getX()
	
	public int getY() {
		return yLoc;
	} // End function getY()
	
	// Modifier functions for a Point:
	public void setX(int newX) {
		xLoc = newX;
	} // End function setX()
	
	public void setY(int newY) {
		yLoc = newY;
	} // End function setY()
} // End class Point.java
