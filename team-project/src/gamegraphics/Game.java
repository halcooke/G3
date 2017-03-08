package gamegraphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ai.AIPlayer;
import audio.Sound;
import main.Application;


/**
 * Recreated the swing classes. It looks a lot better. 
 * Perhaps we could use this for the game. 
 * I'll keep figuring out things but this is just a demo
 * @author ibs483
 *
 */
public class Game extends BasicGameState
{
	//attributes
	int state, gameType;
	/*
	 * Checks if the template board should load you as a knight.
	 */
	static boolean isKnight = false;
	static boolean isPaused = false;
	static boolean hasPaused = false;
	protected Player player;
	protected AIPlayer ai;
	protected Image mapBG;
	protected Board map;
	protected Image mouseC;
	
	
	/**
	 * Constructor.
	 */
	public Game(int state, int gameType, boolean knight) 
	{
		super();
		this.state = state;
		this.gameType = gameType;
		isKnight = knight;
	}
	
	public Game(int state, Player playerU, AIPlayer aiU, Image map, int gameType)
	{
		super();
		this.state      = state;
		this.player     = playerU;
		this.ai         = aiU;
		this.mapBG      = map;
		this.gameType   = gameType;
		
	}

	/**
	 * Initialises the board and passes in a game container and the state based game.
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		mouseC     = new Image("images/Cursor/ic_cursor_off.png");
		container.setMouseCursor(mouseC.getScaledCopy(0.3f), 0, 0);
		
		if(gameType == 1)
		{
			
			this.map = new Board(container,0);
			
		}
		
		if(gameType == 2)
		{
			this.map = new Board(container, player, ai, mapBG, 0);
		}
	}

	/**
	 * Renders the map with the characters and obstacles on it. 
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{

		if(!isPaused)
		{
			map.draw(g);
		}
		else if(isPaused && !map.getGameL().isDead() && !map.getGameL().isHasWon() && !map.getGameL().isOppWon())
		{
			g.setColor(Color.blue);
			g.drawString("Paused", Board.BOARDSTARTX + (int) (Board.BOARDWIDTH*0.48), Board.BOARDSTARTY + (int) (Board.BOARDHEIGHT*0.5));
		}
		else
		{
			map.draw(g);
		}
	}


	/**
	 * Updates the map with user input.
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		//System.out.println(Board.BOARDHEIGHT + " " + Board.BOARDWIDTH);
		
		Input input = container.getInput();
		
		if(input.isKeyPressed(Input.KEY_BACK))
		{
			Sound.Battle.stop();
			Sound.EnterScreen.loop();
			game.getState(Application.MAINMENU).init(container, game);
			game.enterState(Application.MAINMENU, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
		}
		
		/**
		 * Pause function.
		 */
		if(!isPaused && input.isKeyPressed(Input.KEY_ENTER))
		{
			isPaused = true;
		}
		
		if(isPaused && input.isKeyPressed(Input.KEY_ENTER))
		{
			isPaused  = false;
		}
	
		if(!isPaused)
		{
			map.update(delta, "");
		}
	}

	/**
	 * Gets the state id.
	 */
	public int getID() 
	{
		return this.state;
	}	

}
