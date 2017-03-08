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

import audio.Sound;
import main.Application;

public class SplashScreen extends BasicGameState 
{

	static final int titleWidth  = (int) (Application.WIDTH*0.5);
	static final int titleHeight = (int) (Application.HEIGHT*0.15);
	static final int pressWidth  = titleWidth/3;
	static final int pressHeight = titleHeight/3;
	static int titlePosX         = (int) (Application.WIDTH*0.255);
	static int titlePosY         = (int) (Application.HEIGHT*0.43);
	static int pressPosX         = titlePosX + (int) (titleWidth*0.34);
	static int pressPosY         = titlePosY + (int) (titleHeight*0.95);
	
	int state;
	private Image title;
	private Image pressEnter;
	private Image mouseC;
	
	public SplashScreen(int state) 
	{
		super();
		this.state = state;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		mouseC     = new Image("images/Cursor/best_sword.png");
		container.setMouseCursor(mouseC.getScaledCopy(0.3f).getFlippedCopy(false, true), 0, 0);
		title      = new Image("images/text/gameTitle.png");
		pressEnter = new Image("images/text/pressEnter.png");
		
		Sound.EnterScreen.loop();
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		
			g.drawImage(title.getScaledCopy(titleWidth, titleHeight), titlePosX, titlePosY);
			g.drawImage(pressEnter.getScaledCopy(pressWidth, pressHeight), pressPosX, pressPosY);
	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_ENTER))
		{
			game.addState(new MainMenu(Application.MAINMENU));
			game.getState(Application.MAINMENU).init(container, game);
			game.enterState(Application.MAINMENU, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
			
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}
		
		//Made changes - Isa
		
		/**
		 * Cheats to get a template board from the splashScreen.
		 * Start as a Knight
		 */
		if(input.isKeyPressed(Input.KEY_1))
		{
			
			game.addState(new Game(Application.GAME, 1, true));
			game.addState(new MainMenu(Application.MAINMENU));
			game.getState(Application.MAINMENU).init(container, game);
			game.getState( Application.GAME ).init(container, game);
			game.enterState(Application.GAME, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
		}
		
		/**
		 * Cheats to get a template board from the splashScreen.
		 * Start as a wizard.
		 */
		if(input.isKeyPressed(Input.KEY_2))
		{
			
			game.addState(new Game(Application.GAME, 1, false));
			game.addState(new MainMenu(Application.MAINMENU));
			game.getState(Application.MAINMENU).init(container, game);
			game.getState( Application.GAME ).init(container, game);
			game.enterState(Application.GAME, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
		}
		
		//Sound.EnterScreen.loop();
	}

	@Override
	public int getID() 
	{
		return this.state;
	}

}
