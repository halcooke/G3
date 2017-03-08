package gamegraphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


/**
 * Represents an object in the game.
 * @author ibs483
 *
 */
public class GameObject 
{
	//attributes
	protected float x, y;
	protected int width,height;
	protected Image entityImage;
	 /** The rectangle used for this entity during collisions resolution
     */
     private Rectangle me = new Rectangle(0,0,0,0);
 //    private Shape me2    = new Circle(0,0,0);

     /** The rectangle used for other entities during collision
     resolution */
     private Rectangle him = new Rectangle(0,0,0,0);
   //  private Shape him2    = new Circle(0,0,0);
	
	/**
	 * The constructor for objects in the game.
	 * @param x The x position on the screen.
	 * @param y The y position on the screen.
	 * @param width The width of the object.
	 * @param height The height of the object.
	 */
	public GameObject(float x, float y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Creates an empty GameObject
	 */
	public GameObject()
	{
		
		
	}
	
	/**
	 * Get the x position of the object.
	 * @return The x position of the object.
	 */
	public float getX() 
	{
		return x;
	}

	/**
	 * Get the y position of the object.
	 * @return The y position of the object.
	 */
	public float getY() 
	{
		return y;
	}
	
	/**
	 * Changes the x position of the character.
	 * @param x The new x position.
	 */
	public void setX(float x) 
	{
		this.x = x;
	}

	/**
	 * Changes the y position of the character.
	 * @param y The new y position.
	 */
	public void setY(float y) 
	{
		this.y = y;
	}
	
	/**
	 * Change the position of the object.
	 * @param x The new x position of the object.
	 * @param y The new y position of the object.
	 */
	public void setLocation(float newX, float newY)
	{
		x = newX;
		y = newY;
	}
	
	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
	
	public void setWidth(int newWidth) 
	{
		width = newWidth;
	}

	public void setHeight(int newHeight) 
	{
		height = newHeight;
	}
	
	public void setDimension(int newWidth, int newHeight)
	{
		width = newWidth;
		height = newHeight;
	}
	
	/**
	 * Changes the image source of the entityImage attribute.
	 * @param source The path to the image.
	 * @throws SlickException 
	 */
	public void setImage(String source) throws SlickException
	{
		entityImage = new Image(source);
	}
	
	/**
	 * Draws the image of the game object. NB: the image is not set when the constructor is called.
	 * @param g Graphics object that draws the image of the entity.
	 */
	public void draw(Graphics g)
	{
		g.drawImage(entityImage.getScaledCopy(width, height), (int) x- width/2, (int) y - height/2);
	}
	

	/**
	 * Checks if two objects in the game have collided.  
	 * @param other
	 * @return
	 */
	public boolean collidesWith(GameObject other) 
	{

          me.setBounds((int) x, (int) y, width,height);

          him.setBounds((int) other.x, (int) other.y, other.getWidth(), other.getHeight());
	
          return me.intersects(him);
  }
	
	   /**
     * Notification that this entity collided with another.
     *
     * @param other The entity with which this entity collided.
     */
    public void collidedWith(GameObject other)
    {

    	System.out.println("I'll go deaf! I'll go DEAFFFF!!");
    	
    }
}
