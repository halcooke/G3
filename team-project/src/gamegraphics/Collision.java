package gamegraphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import main.Application;

public class Collision {
	
	public static final int DEFAULTHEIGHT = Application.HEIGHT;
	public static final int DEFAULTWIDTH  = Application.WIDTH;
	public static final int BOARDWIDTH    = (int) (Board.DEFAULTWIDTH * 0.9);
	public static final int BOARDHEIGHT   = (int) (Board.DEFAULTHEIGHT * 0.9);
	public static final int BOARDSTARTX   = (int) (Board.DEFAULTWIDTH * 0.05);
	public static final int BOARDSTARTY   = (int) (Board.DEFAULTHEIGHT * 0.05);
	public static final int MAZECOLUMNS = 6;
	public static final int MAZEROWS = 4;
	public static final int MAZEBLOCKWIDTH = (int) (Board.BOARDWIDTH / MAZECOLUMNS);
	public static final int MAZEBLOCKHEIGHT = (int) (Board.BOARDHEIGHT / MAZEROWS);
	public static final int WALLWIDTH = 16;
	
	
	public Ammo[] shots 				  = new Ammo[10];
	private float[] currentXMouse 		  = new float[10];
	private float[] currentYMouse 		  = new float[10];
	private float[] currentXPos 		  = new float[10];
	private float[] currentYPos 		  = new float[10];
	private int mouseCount, shotNum 	  = 0;
	public int shotMax 					  = 10;
	private int slashLife				  = 0;
	public Ammo slash;
	
	Player player;
	Player enemy2;
	GameContainer container;
	int delta;
	ai.AIPlayer ai;
	Obstacle[] maze;
	private final int knightDamage = 200;
	private final int wizardDamage = 150;
	private final int mpDrop	   = 200;
	private final int enDrop	   = 500;
	private final int mpCap 	   = 200;
	private final int enCap        = 500;
	
	
	public Collision(Player player, Player enemy2, GameContainer container, ai.AIPlayer ai){
		this.player = player;
		this.enemy2 = enemy2;
		this.container = container;
		this.ai = ai;
	}
	
	public Collision()
	{
		
	}
	
	public int getDelta() 
	{
		return delta;
	}


	public void setDelta(int delta)
	{
		this.delta = delta;
	}


	public void collideBoard(Player player, Player enemy2, GameContainer container, ai.AIPlayer ai)
	{		
		if(player.type == 2 && player.getPlayerID() == 1){
			manaRegen(player, 2);
			shoot(player, container);
			for(int i = 0; i < shotMax; i++){
				//System.out.println("please appear");
				if((shots[i] != null) &&(ai!= null && shots[i].collidesWith(ai) || enemy2 != null && shots[i].collidesWith(enemy2)))
				{
					System.out.println("Wizard hit!");
					if(ai != null && shots[i].collidesWith(ai)){
						ai.sethP(ai.gethP() - wizardDamage);
						System.out.println("ai hit");
						System.out.println(ai.gethP());
					}
					if(enemy2 != null && shots[i].collidesWith(enemy2)){
						enemy2.sethP(enemy2.gethP() - wizardDamage);
						System.out.println("enemy2 hit");
					}
					shots[i] = null;
				}
			}
		}
		
		if(player.type == 1 && player.getPlayerID() == 1)
		{
			manaRegen(player, 8);
			slash(player, container);
			slashLife++;
			if(slash != null && (ai != null && slash.collidesWith(ai) || enemy2 != null && slash.collidesWith(enemy2)))
			{
				System.out.println("Knight hit!");
				if(ai != null && slash.collidesWith(ai)){
					ai.sethP(ai.gethP() - knightDamage);
					System.out.println("ai hit");
					System.out.println(ai.gethP());
				}
				if(enemy2 != null && slash.collidesWith(enemy2)){
					enemy2.sethP(enemy2.gethP() - knightDamage);
					System.out.println("enemy2 hit");
				}
				slash = null;
			}
		}
	}
	
	/**
	 * Regenerates the mana of the player
	 */
	public void manaRegen(Player player, int i)
	{
		if(player.getmP() < Player.MPDEFAULT){
			player.setmP(player.getmP() + i);
		}
	}
	
	public void shoot(Player player, GameContainer container)
	{
		if(detect(container))
		{
			if(player.getmP() >= mpCap){
				player.setmP(player.getmP() - mpDrop);
				//int thisShot = findShot();
				shots[shotNum] = new Ammo(player.x, player.y, 32, 32);
				currentXMouse[shotNum] = container.getInput().getMouseX();
				currentYMouse[shotNum] = container.getInput().getMouseY();
				currentXPos[shotNum] = player.getX();
				currentYPos[shotNum] = player.getY();
				shotNum++;
				if(shotNum == 10){
					shotNum = 0;
				}
				

			}
		}
		/*if(shotNum == shotMax){
			shots[0] = null;
			shotNum = 0;
		}*/
		for(int i = 0; i < shotMax; i++){
			if(shots[i] != null){
				//currentX[i] = pointer.getX();
				//currentY[i] = pointer.getY();
				shots[i].move(currentXPos[i], currentXMouse[i], currentYPos[i], currentYMouse[i]);
				if(!( (shots[i].getX() > BOARDSTARTX) && (shots[i].getX() < BOARDWIDTH) && (shots[i].getY() > BOARDSTARTY) && (shots[i].getY() < BOARDHEIGHT) )){
					shots[i] = null;
				}
			}
		}
	}
	
	/**
	 * Detects whether the player has just attacked
	 * @return Whether or not a attack has occurred
	 */
	public boolean detect(GameContainer container)
	{
		if(container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		{
			mouseCount++;
		}
		else
		{
			mouseCount = 0;
		}
		return (mouseCount == 1);
	}
	
	/**
	 * Creates an attack for the Knight.
	 */
	public void slash(Player player, GameContainer container)
	{
		
			if(detect(container))
			{
				if(player.getmP() >= enCap)
				{
					player.setmP(player.getmP() - enDrop);
					slash = new Ammo(player.x, player.y, 32, 32);
					slashLife = 0;
				}
			}
			
			if(slashLife > 2)
			{
				slashLife = 0;
				slash = null;
			}
			
			if(slash != null)
			{
				slash.slashPoint(player.getX(), container.getInput().getMouseX(), player.getY(), container.getInput().getMouseY());
			}
	}
	
	public String mazeCollision(Obstacle[] maze, float x, float y){
		int scaleX = (int)((x - BOARDSTARTX)/MAZEBLOCKWIDTH) + 1;
		int scaleY = (int)((y - BOARDSTARTY)/MAZEBLOCKHEIGHT);
		int pos = scaleX + scaleY * MAZECOLUMNS;
		//System.out.println(x - BOARDSTARTX);
		//System.out.println(y - BOARDSTARTY);
		if(maze[pos - 1].getL()){
			if(x - BOARDSTARTX <= (((pos-1) % MAZECOLUMNS) * MAZEBLOCKWIDTH + WALLWIDTH) ){
				//System.out.println("left:" + (x-BOARDSTARTX) + " , " + (y-BOARDSTARTY) + " Pos : " + pos);
				//System.out.println(((pos-1) % 6 - 1) * MAZEBLOCKWIDTH + WALLWIDTH/2);
				return "goRight";
			}
		}
		if(maze[pos - 1].getU()){
			if(y - BOARDSTARTY <= ((pos-1)/MAZECOLUMNS)*MAZEBLOCKHEIGHT + WALLWIDTH ){
				//System.out.println("up:" + (x-BOARDSTARTX) + " , " + (y-BOARDSTARTY) + " Pos : " + pos);
				return "goDown";
			}
		}
		if(maze[pos - 1].getR()){
			if(x - BOARDSTARTX >= ((((pos-1) % MAZECOLUMNS) + 1) * MAZEBLOCKWIDTH - WALLWIDTH) ){
				//System.out.println("right:" + (x-BOARDSTARTX) + " , " + (y-BOARDSTARTY) + " Pos : " + pos);
				return "goLeft";
			}
		}
		if(maze[pos - 1].getD()){
			if(y - BOARDSTARTY >= ((pos - 1)/MAZECOLUMNS + 1)*MAZEBLOCKHEIGHT - WALLWIDTH){
				//System.out.println("down:" + (x-BOARDSTARTX) + " , " + (y-BOARDSTARTY) + " Pos : " + pos);
				return "goUp";
			}
		}
		return "OK";
	}
	
}
