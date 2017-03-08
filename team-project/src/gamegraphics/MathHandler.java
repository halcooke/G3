package gamegraphics;

import java.awt.geom.Point2D;

public class MathHandler 
{
	
	/**
	 * gets the angle between two points assuming the points are representing vectors
	 * @param p1 point1
	 * @param p2 point2
	 * @return the angle in radians
	 */
	public static double getAngle(Point2D.Double p1, Point2D.Double p2) {
		if (p1.x != p2.x && p1.y != p2.y) {
			double xdif = (p2.getX() - p1.getX());
			double ydif = (p2.getY() - p1.getY());
			double angle = 0; // in radians
			angle = -Math.atan(ydif / xdif);
			if (xdif < 0) {
				if (ydif < 0) {
					angle += Math.PI;
				} else {
					angle -= Math.PI;
				}
			}
			return -angle;
		} else if (p1.x > p2.x) {
			return Math.PI;
		} else if (p1.x < p2.x) {
			return 0.0;
		} else if (p1.y > p2.y) {
			return -Math.PI / 2.0;
		} else if (p1.y < p2.y) {
			return Math.PI / 2.0;
		}
		return 0.0;
	}
	
	   /**
	    * Gets the distance between the two points.
	    * @param startX The x position of the first point.
	    * @param startY The y position of the first point.
	    * @param endX The x position of the second point.
	    * @param endY The y position of the second point.
	    * @return The distance between two points as a float.
	    */
	   public float getDistanceBetween(float startX, float startY, float endX, float endY) {
		   float dx = endX - startX;
		   float dy = endY - startY;
		   return (float)Math.sqrt(dx*dx + dy*dy);
		}

	   /**
	    * Gets the target angle for the object to rotate by.
	    * @param startX The x position of the object.
	    * @param startY The y position of the object.
	    * @param targetX The x position of the mouse cursor.
	    * @param targetY The y position of the mouse cursor.
	    * @return The angle between two objects as a float.
	    */
	   public float getTargetAngle(float startX, float startY, float targetX, float targetY) {
		   float dx = targetX - startX;
		   float dy = targetY - startY;
		   return (float)Math.toDegrees(Math.atan2(dy, dx));
		}

}
