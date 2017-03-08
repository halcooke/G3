package gamegraphics;
import java.util.ArrayList;
import java.util.StringJoiner;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ai.AIPlayer;
import gamelogic.GameLogic;
import main.Application;
/**
 * Keeps track of the objects on the board.
 * @author ibs483, jxs989
 *
 */
public class Board 
{
	//attributes
	private GameLogic gameL;
	public Collision collision;
	private Player player;
	public  ArrayList<Player> players = new ArrayList<Player>();
	public  ArrayList<AIPlayer> comPlayers = new ArrayList<AIPlayer>();
	//private Player enemy;
	//private Character enemy;
	private Player enemy2;
	private AIPlayer ai;
	//private Player player2;
	private Image mapBG;
	public static final int DEFAULTHEIGHT = Application.HEIGHT;
	public static final int DEFAULTWIDTH  = Application.WIDTH;
	public static final int BOARDWIDTH    = (int) (Board.DEFAULTWIDTH * 0.9);
	public static final int BOARDHEIGHT   = (int) (Board.DEFAULTHEIGHT * 0.9);
	public static final int BOARDSTARTX   = (int) (Board.DEFAULTWIDTH * 0.05);
	public static final int BOARDSTARTY   = (int) (Board.DEFAULTHEIGHT * 0.05);
	static int wizardw                    = (int) (BOARDWIDTH * 0.1);
	static int wizardh                    = (int) (BOARDHEIGHT * 0.11);
	static int knightw                    = (int) (BOARDWIDTH * 0.07);
	static int knighth                    = (int) (BOARDHEIGHT * 0.1);
	static int charPX                     = (int) (BOARDWIDTH * 0.1);
	static int charPY                     = (int) (BOARDHEIGHT * 0.1);
	static int oppPX                      = charPX + (int) (BOARDWIDTH * 0.89);
	static int oppPY                      = charPY + (int) (BOARDHEIGHT * 0.89);
	static int oppA					      = charPX + (int) (BOARDWIDTH * 0.95);
	static int oppB					      = charPY + (int) (BOARDHEIGHT * 0.9);
	static int charHealthX				  =	charPX + (int) (wizardw);
	static int charHealthY				  = charPY - (int) (wizardh*0.84);
	static int oppHealthX				  = oppA - (int) (BOARDHEIGHT * 0.7);
	static int oppHealthY				  = charHealthY; 
	static int charMPX				      = charHealthX; 
	static int charMPY				      = charHealthY + (int) (charHealthY*1.5);
	static int oppMPX				      = oppHealthX;
	static int oppMPY				      = charMPY;
		
	public static final int MAZECOLUMNS = 6;
	public static final int MAZEROWS = 4;
	public static final int MAZEBLOCKWIDTH = (int) (Board.BOARDWIDTH / MAZECOLUMNS);
	public static final int MAZEBLOCKHEIGHT = (int) (Board.BOARDHEIGHT / MAZEROWS);
	public static final int WALLWIDTH = 16;
	Obstacle[] maze;
	
	
	public GameContainer container;
	int delta;
	public int type;
	//private GameObject pointer;
	/*
	private boolean isDead = false;
	private boolean hasWon = false;
	private boolean oppWon = false;
	*/
	
	/**
	 * Initialises all the characters, objects and the entire map background
	 * Template Map constructor.
	 * @param container The game container for the game state.
	 */
	public Board(GameContainer container, int type)
	{ 
		//Made changes here - Isa.
		this.container = container;
		this.type = type;
		templateBoard(type);
		gameL = new GameLogic(this);
	}
	
	/**
	 * Creates a board based on the character select and Map background select.
	 * @param container
	 * @param player
	 * @param type
	 */
	public Board(GameContainer container, Player player, AIPlayer ai, Image mapBG, int type)
	{
		//Made Changes here - Isa.
		this.container = container;
		initBoard(player, ai, mapBG, type);
		gameL = new GameLogic(this);
	}
	
	/**
	 * Creates a board through input from the user.
	 * @param playerU The character selected by the player.
	 * @param enemyU The enemy character selected by the player.
	 * @param map The map background selected by the player.
	 * @param type The type of the board. 0 for offline, 1 for online Host and 2 for online Client.
	 */
	public void initBoard(Player playerU, AIPlayer enemyU, Image map, int type)
	{
		
		//Initialise everything with the given input.
		if(type == 0)
		{
			try
			{
				maze = createMaze();
				//Template background Offline 
				mapBG = map;
				player = playerU;
				ai = enemyU;
				//enemy2 = new Player(oppPX,oppPY, knightw, knighth, 1,2);
				
				player.setPlayerID(1);
				player.setLocation(charPX + 100, charPY + 100);
				System.out.println("Player coord x: " + (charPX + 100) + " Player coord y: " + (charPY + 100));
				player.isMe = true;
				
				//For now
				ai.setLocation(oppPX - 200, oppPY - 200);
				ai.getObstacleCoordinates(maze);
				ai.setInitialDone(true);
				
				
				if(player.getType() == 1)
				{
					player.setDimension(knightw, knighth);
				}
				
				if(player.getType() == 2)
				{
					player.setDimension(wizardw, wizardh);
				}
				
				if(ai.getType() == 1)
				{
					ai.setDimension(knightw, knighth);
				}
				
				if(ai.getType() == 2)
				{
					ai.setDimension(wizardw, wizardh);
				}
				
			
				
				players.clear();
				comPlayers.clear();
				players.add(player);
				comPlayers.add(ai);
				
				collision = new Collision();
				//collision = new Collision(player, enemy2, container, ai);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
			
		if(type == 1)
		{
			try
			{
				
				maze = createMaze();
				//Background Online Host 
				mapBG = map;
				player = playerU;
				enemy2 = new Player(oppPX,oppPY, knightw, knighth, 1,2);
				
				player.setPlayerID(1);
				player.setLocation(charPX, charPY);
				player.isMe = true;
				
				if(player.getType() == 1)
				{
					player.setDimension(knightw, knighth);
				}
				
				if(player.getType() == 2)
				{
					player.setDimension(wizardw, wizardh);
				}
				
				players.clear();
				players.add(player);
				players.add(enemy2);
				collision = new Collision();
				//collision = new Collision(player, enemy2, container, ai);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}	
		
		if(type == 2)
		{
			try
			{
				maze = createMaze();
				//Background Online Client
				mapBG = map;
				player = playerU;
				enemy2 = new Player(charPX,charPY, knightw, knighth, 1,2);
				
				player.setPlayerID(1);
				player.setLocation(oppPX, oppPY);
				player.isMe = true;
				
				if(player.getType() == 1)
				{
					player.setDimension(knightw, knighth);
				}
				
				if(player.getType() == 2)
				{
					player.setDimension(wizardw, wizardh);
				}
				
				players.clear();
				players.add(player);
				players.add(enemy2);
				collision = new Collision();
				//collision = new Collision(player, enemy2, container, ai);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * Sets up a template board.
	 * @param type The type of the board. 0 for offline, 1 for online Host and 2 for online Client.
	 */
	public void templateBoard(int type)
	{
		
		if(type == 0)
		{
			try
			{
				maze = createMaze();
				//Template background Offline 
				mapBG = new Image("images/Backgrounds/backgrounddetailed1.png");
				
				if(!Game.isKnight)
				{
					player = new Player(charPX, charPY, wizardw, wizardh,2,1);
					player.setDimension(wizardw, wizardh);
				}
				else
				{
					player = new Player(charPX, charPY, knightw, knighth,1,1);
					player.setDimension(knightw, knighth);
				}
				player.isMe = true;
				ai = new AIPlayer(oppPX, oppPY, wizardw, wizardh, 2, player.x, player.y);
				
				players.clear();
				comPlayers.clear();
				players.add(player);
				comPlayers.add(ai);
				collision = new Collision();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
			
		if(type == 1)
		{
			try
			{
				maze = createMaze();
				//Template background Online for Host
				mapBG = new Image("images/Backgrounds/backgrounddetailed1.png");
				player = new Player(charPX, charPY, wizardw, wizardh,2,1);
				player.isMe = true;
				player.setDimension(wizardw, wizardh);
				enemy2  = new Player(oppPX,oppPY, knightw,knighth,1,2);
				enemy2.setDimension(knightw, knighth);
				
				players.clear();
				players.add(player);
				players.add(enemy2);
				collision = new Collision();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}	
		
		if(type == 2)
		{
			try
			{
				maze = createMaze();
				//Template background Online for Client
				mapBG = new Image("images/Backgrounds/backgrounddetailed1.png");
				player = new Player(oppPX, oppPY, knightw, knighth,1,1);
				player.isMe = true;
				player.setDimension(knightw, knighth);
				enemy2      = new Player(oppPX,oppPY, wizardw, wizardh,2,2);
				enemy2.setDimension(wizardw, wizardh);
				
				players.clear();
				players.add(player);
				players.add(enemy2);
				collision = new Collision();
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}			
	}
	/**
	 * Updates the characters position and orientation on the screen.
	 * @param delta The speed of the character.
	 */
	public void update(int delta, String update)
	{
		
		gameL.updateBoard(delta, update);
	}
		
	/**
	 * Draws the characters on the screen.
	 * @param g The Graphics object that draws the images.
	 */
	public void draw(Graphics g)
	{
		
		gameL.drawOnBoard(g);
	}
	
	public void updateOpp(String msg)
	{
		enemy2.updateEnemy(msg);
	}
	
	public Player getPlayer() 
	{
		return player;
	}
	public void setPlayer(Player player) 
	{
		this.player = player;
	}
	public Player getEnemy() 
	{
		return enemy2;
	}
	public void setEnemy(Player enemy2) 
	{
		this.enemy2 = enemy2;
	}
	public Image getMapBG() 
	{
		return mapBG;
	}
	public void setMapBG(Image mapBG) 
	{
		this.mapBG = mapBG;
	}
	
	
	
	public GameLogic getGameL() 
	{
		return gameL;
	}
	public void setGameL(GameLogic gameL) 
	{
		this.gameL = gameL;
	}
	private Obstacle[] createMaze()
	{
		//Hard coded for now, will implement random generation in due course
		Obstacle[] maze = new Obstacle[24];
		//for(int i = 1 ; i <= maze.length; i++){
		//	maze[i-1] = new Obstacle(i, true, true, true, true);
		//	
		//}
		maze[0] = new Obstacle(1, true, true, false, false);
		maze[1] = new Obstacle(2, false, true, true, true);
		maze[2] = new Obstacle(3, true, true, false, true);
		maze[3] = new Obstacle(4, false, true, true, false);
		maze[4] = new Obstacle(5, true, true, false, false);
		maze[5] = new Obstacle(6, false, true, true, false);
		maze[6] = new Obstacle(7, true, false, false, true);
		maze[7] = new Obstacle(8, false, true, true, false);
		maze[8] = new Obstacle(9, true, true, false, false);
		maze[9] = new Obstacle(10, false, false, true, true);
		maze[10] = new Obstacle(11, true, false, true, false);
		maze[11] = new Obstacle(12, true, false, true, true);
		maze[12] = new Obstacle(13, true, true, false, false);
		maze[13] = new Obstacle(14, false, false, true, true);
		maze[14] = new Obstacle(15, true, false, false, false);
		maze[15] = new Obstacle(16, false, true, true, false);
		maze[16] = new Obstacle(17, true, false, false, true);
		maze[17] = new Obstacle(18, false, true, true, false);
		maze[18] = new Obstacle(19, true, false, false, true);
		maze[19] = new Obstacle(20, false, true, false, true);
		maze[20] = new Obstacle(21, false, false, true, true);
		maze[21] = new Obstacle(22, true, false, false, true);
		maze[22] = new Obstacle(23, false, true, false, true);
		maze[23] = new Obstacle(24, false, false, true, true);
		
		return maze;
	}
	
	public Obstacle[] getMaze()
	{
		return maze;
	}
//<<<<<<< Updated upstream
	/*String message about your position 
	 * code, x pos, y pos, angle, is Hit, is attacking
	 *TODO code int
	 *x pos - float
	 *y pos - float 
	 *angle - float
	 *is Hit - boolean 
	 *isAttacking boolean
	 * */
	public String getYourMoves() 
	{
		String xpos = Float.toString(getPlayer().getX());
		String ypos = Float.toString(getPlayer().getY());
		String angle = Float.toString(getPlayer().getTargetAng());
		
		int hit = (getPlayer().isHit()) ? 1:0;
		int attack = (getPlayer().isAttacking()) ? 1:0;
		
		String hitStr = Integer.toString(hit);
		String attackStr = Integer.toString(attack);
		
		String hp = Integer.toString(getPlayer().gethP());
		String mp = Integer.toString(getPlayer().getmP());
		
		StringJoiner joiner = new StringJoiner(";");
		joiner.add(xpos).add(ypos).add(angle).add(hitStr).add(attackStr).add(hp).add(mp);
		String message = joiner.toString();
		
		return message;
		
	}
	
	/*String message about your position 
	 * code, x pos, y pos, angle, is Hit, is attacking
	 *TODO code int
	 *x pos - float
	 *y pos - float 
	 *angle - float
	 *is HIt - boolean 
	 *isAttacking boolean
	 * */
	public String getEnemyMoves() 
	{
		String xpos = Float.toString(getEnemy().getX());
		String ypos = Float.toString(getEnemy().getY());
		String angle = Float.toString(getEnemy().getTargetAng());
		
		int hit = (getEnemy().isHit()) ? 1:0;
		int attack = (getEnemy().isAttacking()) ? 1:0;
		
		String hitStr = Integer.toString(hit);
		String attackStr = Integer.toString(attack);
		
		String hp = Integer.toString(getEnemy().gethP());
		String mp = Integer.toString(getEnemy().getmP());
		
		StringJoiner joiner = new StringJoiner(";");
		joiner.add(xpos).add(ypos).add(angle).add(hitStr).add(attackStr).add(hp).add(mp);
		String message = joiner.toString();
		
		return message;
		
	}
}