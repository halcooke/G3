package gamegraphics;

import java.awt.geom.Point2D;

/**
 * Not sure what this class is going handle yet.
 * @author ibs483
 *
 */
public class GridPoint extends Point2D
{
	
	//attributes
	double x,y;
	boolean isChar,isOb;
	Point2D location;
	
	public GridPoint(double x, double y, boolean isChar, boolean isOb)
	{
		super();
		this.x = x;
		this.y = y;
		this.isChar = isChar;
		this.isOb = isOb;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isChar() {
		return isChar;
	}

	public void setChar(boolean isChar) {
		this.isChar = isChar;
	}

	public boolean isOb() {
		return isOb;
	}

	public void setOb(boolean isOb) {
		this.isOb = isOb;
	}

	public void setLocation(double x, double y) 
	{
		this.location.setLocation(x, y);
	}
	
	public Point2D locate()
	{
		return this.location;
	}
	
}
