package gamegraphics;

import org.newdawn.slick.Image;

/**
 * Keeps track of the attributes of the character.
 * @author ibs483
 *
 */
public class Character extends GameObject
{
	//attributes
	public final static int HPDEFAULT = 1600;
	public final static int MPDEFAULT = 2000;
	public int type;
	int hP = HPDEFAULT;
	int mP = MPDEFAULT;
	boolean isAttacking = false;
	boolean isHit = false;
	boolean isBlocking = false;
	boolean isMoving = false;
	boolean healthZero = false;
	boolean magicZero = false;
	protected Image still = entityImage;
	protected Image walking1, walking2, attack, attack1, attack2;
	protected String f, f2, f3, f4, f5, f6;
	
	/**
	 * Creates a character.
	 * @param x The x coordinate of the character.
	 * @param y The y coordinate of the character.
	 * @param width The width of the character.
	 * @param height The height of the character.
	 * @param type The type of character. 1 for Knight and 2 for Wizard.
	 */
	public Character(float x, float y, int width, int height, int type)
	{
		super(x,y,width,height);
		
		this.type = type;
		
		//Checks to see the type of character to create.
		if(type == 1)
		{
			initKnight();
		}
		if(type == 2)
		{
			initWizard();
		}
	}
	
	public Character(int type)
	{
		super();
		this.type = type;
		
		//Checks to see the type of character to create.
				if(type == 1)
				{
					initKnight();
				}
				if(type == 2)
				{
					initWizard();
				}
	}
	

	public int getType() 
	{
		return type;
	}

	public void setType(int type) 
	{
		this.type = type;
	}

	/**
	 * Initialises a wizard character.
	 */
	public void initWizard()
	{
		f = "images/Wizard/Still.png";
		f2 ="images/Wizard/Walking1.png";
		f3 = "images/Wizard/Walking2.png";
		f4 = "images/Wizard/Attack.png";
		f5 = "images/Wizard/Attack1.png";
		f6 = "images/Wizard/Attack2.png";
		
		try
		{
			
		still = new Image(f);
		entityImage = still;
		walking1 = new Image(f2);
		walking2 = new Image (f3);
		attack = new Image(f4);
		attack1 = new Image(f5);
		attack2 = new Image(f6);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialises a knight character.
	 */
	public void initKnight()
	{
		f = "images/Knight/Still.png";
		f2 ="images/Knight/Walking1.png";
		f3 = "images/Knight/Walking2.png";
		f4 = "images/Knight/Attack.png";
		f5 = "images/Knight/Attack1.png";
		f6 = "images/Knight/Attack2.png";
		
		try
		{
			
		still = new Image(f);
		entityImage = still;
		walking1 = new Image(f2);
		walking2 = new Image (f3);
		attack = new Image(f4);
		attack1 = new Image(f5);
		attack2 = new Image(f6);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * The current health points the character has.
	 * @return The vitality level of the character..
	 */
	public int gethP() 
	{
		return hP;
	}

	/**
	 * Changes the amount of health points the character has.
	 * @param hP The new vitality level of the character..
	 */
	public void sethP(int hP) 
	{
		this.hP = hP;
	}

	/**
	 * The current magic points the character has.
	 * @return The magic level of the character.
	 */
	public int getmP() 
	{
		return mP;
	}

	/**
	 * Changes the amount of magic points the character has.
	 * @param mP The new magic level of the character.
	 */
	public void setmP(int mP) 
	{
		this.mP = mP;
	}

	/**
	 * Tells whether the character is hit or not.
	 * @return The boolean value of isHit.
	 */
	public boolean isHit() 
	{
		return isHit;
	}

	/**
	 * Changes the isHit boolean state of the character.
	 * @param isHit The new boolean hit state of the character.
	 */
	public void setHit(boolean isHit) 
	{
		this.isHit = isHit;
	}
	
	/**
	 * Tells whether the character is blocking.
	 * @return The boolean value of IsBlocking.
	 */
	public boolean isBlocking() 
	{
		return isBlocking;
	}


	/**
	 * Changes the boolean state of the isBlocking. 
	 * @param isBlocking The new boolean state of isBlocking.
	 */
	public void setBlocking(boolean isBlocking) 
	{
		this.isBlocking = isBlocking;
	}

	/**
	 * A to string method to return all attributes of the character as a string.
	 */
	public String toString() 
	{
		return "Character [x=" + x + ", y=" + y + ", hP=" + hP + ", mP=" + mP + ", isHit=" + isHit + ", isBlocking="
				+ isBlocking + ", isMoving=" + isMoving + ", healthZero=" + healthZero + ", magicZero=" + magicZero
				+ "]";
	}

	/**
	 * Tells whether a character is moving or not.
	 * @return The boolean value of isMoving.
	 */
	public boolean isMoving() 
	{
		return isMoving;
	}

	/**
	 * Changes the boolean state of the isMoving attribute.
	 * @param isMoving The new boolean state of isMoving.
	 */
	public void setMoving(boolean isMoving) 
	{
		this.isMoving = isMoving;
	}

	/**
	 * Tells whether the character's health is zero.
	 * @return Only returns true when health points are zero.
	 */
	public boolean isHealthZero() 
	{
		return healthZero;
	}

	/**
	 * Changes the boolean state of the healthZero attribute.
	 * @param healthZero The new boolean state of healthZero.
	 */
	public void setHealthZero(boolean healthZero) 
	{
		this.healthZero = healthZero;
	}

	/**
	 * Tells whether the character's magic is zero.
	 * @return Only returns true when magic points are zero.
	 */
	public boolean isMagicZero() 
	{
		return magicZero;
	}

	/**
	 * Changes the boolean state of the magicZero attribute.
	 * @param magicZero The new boolean state of magicZero.
	 */
	public void setMagicZero(boolean magicZero) 
	{
		this.magicZero = magicZero;
	}


	public boolean isAttacking() 
	{
		return isAttacking;
	}


	public void setAttacking(boolean isAttacking) 
	{
		this.isAttacking = isAttacking;
	}
}
