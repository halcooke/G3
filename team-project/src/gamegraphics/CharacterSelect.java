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
import main.Application;

public class CharacterSelect extends BasicGameState {

	
	static final int titleWidth       = (int) (Application.WIDTH*0.5);
	static final int titleHeight      = (int) (Application.HEIGHT*0.2);
	static int titlePosX              = (int) (Application.WIDTH*0.26);
	static int titlePosY              = (int) (Application.HEIGHT*0.2);
	static final int wizardWidth      = (int) (Application.WIDTH*0.4);
	static final int wizardHeight     = (int) (Application.HEIGHT*0.6);
	static final int knightWidth      = (int) (wizardWidth);
	static final int knightHeight     = (int) (wizardHeight);	
	static int wizardPosX             = (int) (Application.WIDTH*0.15);
	static int wizardPosY             = (int) (Application.HEIGHT*0.2);
	static int knightPosX             = wizardPosX + (int) (wizardWidth*0.95);
	static int knightPosY             = wizardPosY;
	static int wizardTextX            = wizardPosX + (int) (wizardWidth*0.5);
	static int wizardTextY            = wizardPosY + (int) (wizardHeight);
	static int knightTextX            = knightPosX + (int) (wizardWidth*0.5);
	static int knightTextY            = wizardTextY;
	static int nextTextX			  = knightTextX + (int) (wizardWidth*0.18);
	static int nextTextY			  = knightTextY + (int) (wizardWidth*0.2);
	
	
	int state, type;
	boolean hasSelected = false;
	boolean isUser      = true;
	boolean isCPU       = false;
	Image wizard, knight;
	String pathW, pathK, titleText, knightText, wizardText, playerSelect, cpuText, aiSelect, text1, text2, nextText;
	Player playerU;
	AIPlayer aiU;
	
	public CharacterSelect(int state, int type) 
	{
		super();
		//To be used later
		this.type = type;
		this.state = state;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		pathW        = "images/Wizard/Still.png"; 
		pathK        = "images/Knight/Still.png";
		wizard       = new Image(pathW);
		knight       = new Image(pathK);
		titleText    = "Please select a character and COM: "; 
		knightText   = "Knight";
		wizardText   = "Wizard";
		playerSelect = "P1: ";
		cpuText      = "COM: ";
		text1        = wizardText;
		text2        = knightText;
		nextText     = "Press enter when finished.";
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.drawString(titleText, titlePosX, titlePosY);
		g.drawImage(wizard.getScaledCopy(wizardWidth, wizardHeight), wizardPosX, wizardPosY);
		g.drawImage(knight.getScaledCopy(knightWidth, knightHeight), knightPosX, knightPosY);
		g.drawString(text1, wizardTextX, wizardTextY);
		g.drawString(text2, knightTextX, knightTextY);
		g.drawString(nextText, nextTextX, nextTextY);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		
		int mPosX = input.getMouseX();
		int mPosY = input.getMouseY();
		
		//Wizard character
		if((mPosX > wizardPosX && mPosX < (wizardPosX + wizardWidth)) && (mPosY > wizardPosY  && mPosY < (wizardPosY + wizardHeight)))
		{
			//optionsTemp = optionsHighlighted;
			if(isUser && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				hasSelected = false;
				text1 = playerSelect + wizardText;
				text2 = knightText;
				playerU = new Player(2,1);
				isUser = false;
			}
		   
			if(!isUser && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				text1 = text1 + "\n" + cpuText;
				aiU = new AIPlayer(2);
				isUser = true;
				hasSelected = true;
			}
			
		}
		else
		{
			//optionsTemp = options;
		}
		
		//Knight Character
		if((mPosX > knightPosX && mPosX < (knightPosX + knightWidth)) && (mPosY > knightPosY  && mPosY < (knightPosY + knightHeight)))
		{
			//optionsTemp = optionsHighlighted;
			if(isUser && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				hasSelected = false;
				text2 = playerSelect + knightText;
				text1 = wizardText;
				playerU = new Player(1,1);
				isUser = false;
			}
			
			if(!isUser && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				text2 = text2 + "\n" + cpuText;
				aiU = new AIPlayer(1);
				isUser = true;
				hasSelected = true;
			}
			
		}
		else
		{
			//optionsTemp = options;
		}
		
		//Moves to the next state
		if(input.isKeyPressed(Input.KEY_ENTER) && hasSelected)
		{
			game.addState(new MapSelect(Application.MAPSELECT, playerU, aiU, 0));
			game.getState(Application.MAPSELECT).init(container, game);
			game.enterState(Application.MAPSELECT, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));	
		}
		
		//Go back to the main menu
		if(input.isKeyPressed(Input.KEY_BACK))
		{
			System.out.println("Number of States: " + game.getStateCount());
			game.addState(new MainMenu(Application.MAINMENU));
			game.getState(Application.MAINMENU).init(container, game);
			game.enterState(Application.MAINMENU, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
		}
		
	}

	@Override
	public int getID() 
	{
		return this.state;
	}

}
