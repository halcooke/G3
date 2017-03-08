package gamegraphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import audio.Sound;


/**
 * Keeps track of user inputs.
 * @author ibs483
 *
 */
public class Player extends Character
{

	//attributes
	Image character = still;
	boolean up = false, down = false, left = false, right = false, one = false;
	int frames = 0;
	int playerID;
	float targetAng = 0.0f;
	float speedFactor = 0.2f;
	int temp;
	int frams, atkFrames = 0;
	boolean mouse = false;
	public boolean isMe = false;

	final int WIDTHLIMIT  = Board.BOARDWIDTH;
	final int HEIGHTLIMIT = Board.BOARDHEIGHT;
	final int STARTXLIMIT = Board.BOARDSTARTX;
	final int STARTYLIMIT = Board.BOARDSTARTY;
	
	String bump;
	
	/**
	 * Creates a player object.
	 * @param x The x coordinate of the player.
	 * @param y The y coordinate of the player.
	 * @param width The width of the player object.
	 * @param height The height of the player object.
	 * @param type The type of character. 1 for Knight and 2 for Wizard.
	 */
	public Player(float x, float y, int width, int height, int type, int id) 
	{
		super(x, y, height, height, type);
		this.playerID = id;
		bump = "OK";
	}
	
	public Player(int type, int id)
	{
		super(type);
		this.playerID = id;
		bump = "OK";
	}
	
	
	public void attackCycle(){
		
		atkFrames++;
		if(frames >= 1){
			character = attack1;
		}
		if(frames >= 26){
			character = attack;
		}
		if(frames >= 51){
			character = attack2;
		}
		if(frames >= 76){
			character = attack;
		}
		if(frames >= 101){
			frames = 0;
		}
	}
	
	public void walkCycle(){

		if(frames >= 1){
			character = walking1;
		}
		if(frames >= 26){
			character = still;
		}
		if(frames >= 51){
			character = walking2;
		}
		if(frames >= 76){
			character = still;
		}
		if(frames >= 101){
			frames = 0;
		}
	}
	
	public void spriteChoice(){
		if(up || down || left || right || one){
			frames++;
			if(mouse){
				System.out.println("KEK");
				attackCycle();
			}
			else{

				walkCycle();
			}
		}			
		else{
			if(mouse){
				character = attack;
			}
			else{
				character = still;
			}
			frames = 0;
		}
	}
	
	public void mouseListener(){
		
	}

	
	/**
	 * Change the position of the character based on user's input.
	 * @param container The container that holds the game state.
	 * @param delta The speed of the player.
	 */
	public void move(GameContainer container, int delta) 
	{
		
		Input input = container.getInput();
		
		up = input.isKeyDown(Input.KEY_W);
		down = input.isKeyDown(Input.KEY_S);
		left = input.isKeyDown(Input.KEY_A);
		right = input.isKeyDown(Input.KEY_D);
		one = input.isKeyDown(Input.KEY_1);
		
		
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			System.out.println("NON");
			if(atkFrames < 25){
				mouse = true;
				atkFrames++;
			}
			else{
				mouse = false;
				mouse = false;
			}
		}
		else{
			atkFrames = 0;
			mouse = false;
		}
		
		boolean test1 = x > WIDTHLIMIT + (int) (width*0.3);
		boolean test2 = y > HEIGHTLIMIT - (int) (height*0.1);
		boolean test3 = x < STARTXLIMIT + (int) (width*0.5);
		boolean test4 = y < STARTYLIMIT + (int) (height*0.5);
		
		boolean test5;

		if (up)
		{
		    // The lower the delta the slower the sprite will animate.
		    y -= delta * speedFactor;
		    
		    if(test4 || bump == "goDown")
		    {
		    	y += delta * speedFactor;
		    }
		}
		if (down)
		{

		    y += delta * speedFactor;
		    
		    if(test2 || bump == "goUp")
		    {
		    	y -= delta * speedFactor;
		    }
		}
	    if (left)
		{

		    x -= delta * speedFactor;
		    
		    if(test3 || bump == "goRight")
		    {
		    	x += delta * speedFactor;
		    }
		}
		if (right)
		{

		    x += delta * speedFactor;
		    
		    if(test1 || bump == "goLeft")
		    {
		    	x -= delta * speedFactor;
		    }
		}	
		
		if(one)
		{
			setLocation(Board.oppPX, Board.oppPY);
		}
		
	//Tests for the type of character first to avoid an exception. 
	//This will change as soon as the sprite animations are done.
	
		spriteChoice();
		//System.out.println("x = " + getX() + ", y = " + getY());
	}	
	
	// Update for the enemy moves
	public void updateEnemy(String message)
	{
		try
		{
			String[] moves =  parseMessage(message);
			setX(getEnemyXpos(moves));
			setY(getEnemyYpos(moves));
			setTargetAng(getEnemyAngle(moves));
			setHit(getEnemyIsHit(moves));
			setAttacking(getEnemyIsAttacking(moves));
			sethP(getEnemyHP(moves));
			setmP(getEnemyMP(moves));
		}
		catch(NumberFormatException e)
		{
			System.out.println("No String yet!");
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}


	private String[] parseMessage(String message) 
	{
	
		String[] moves = message.split(";");
		return moves;
	}
	
	private float getEnemyXpos(String[] moves){
		float xpos = Float.parseFloat(moves[0]);
		return xpos;
	}


	private float getEnemyYpos(String[] moves) {
		float ypos = Float.parseFloat(moves[1]);
		return ypos;
	}

	private float getEnemyAngle(String [] moves){
		float angle = Float.parseFloat(moves[2]);
		return angle;
	}
	
	private boolean getEnemyIsHit(String [] moves){
		int hitAsInt = Integer.parseInt(moves[3]);
		return hitAsInt == 1 ? true:false;
	}
	private boolean getEnemyIsAttacking(String[] moves){
		int attackAsInt = Integer.parseInt(moves[4]);
		return attackAsInt ==1 ? true:false;
	}
	private int getEnemyHP(String[] moves){
		int enemyHP = Integer.parseInt(moves[5]);
		return enemyHP;
	}
	
	private int getEnemyMP(String[] moves){
		int enemyMP = Integer.parseInt(moves[6]);
		return enemyMP;
	}
	/**
	 * Draws the player's character on the screen.
	 * @param g Graphics object that draws the character onto the screen.
	 * @param container The container that holds the game state.
	 */
	public void draw(Graphics g, GameContainer container) 
	{

		//g.drawImage(bullet.getScaledCopy((int) (width*0.0095), (int) (height*0.0095)), width/2, height/2);
		MathHandler hand = new MathHandler();
		
		if(isMe)
		{
			targetAng =  hand.getTargetAngle(x, y, container.getInput().getMouseX(), container.getInput().getMouseY());
		}
		g.rotate(x , y , targetAng);
		g.drawImage(character.getScaledCopy(width, height), (int) (x - width/2), (int) (y - height/2));
		g.rotate(x , y , -targetAng);
		
	}
	
	public float getTargetAng() 
	{
		return targetAng;
	}


	public void setTargetAng(float targetAng) 
	{
		this.targetAng = targetAng;
	}
	
	public int getPlayerID() 
	{
		return playerID;
	}


	public void setPlayerID(int playerID) 
	{
		this.playerID = playerID;
	}
	
	public void setBump(String bump) {
		this.bump = bump;
		
	}
	
	@Override
	public void collidedWith(GameObject other)
	{
		//this.sethP(gethP() - 2);
		//this.setmP(getmP()-1);
		//System.out.println("It touched MEEEE!!!");
	}
	
	

}
