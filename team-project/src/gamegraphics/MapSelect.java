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

public class MapSelect extends BasicGameState {
	
	
	static final int titleWidth       = (int) (Application.WIDTH*0.5);
	static final int titleHeight      = (int) (Application.HEIGHT*0.2);
	static int titlePosX              = (int) (Application.WIDTH*0.16);
	static int titlePosY              = (int) (Application.HEIGHT*0.1);
	static final int mapWidth         = (int) (Application.WIDTH*0.2);
	static final int mapHeight        = (int) (Application.HEIGHT*0.2);
	static final int currentMapWidth  = (int) (mapWidth*2.35);
	static final int currentMapHeight = (int) (mapHeight*2.35);
	static int nextTextPosX           = (int) (Application.WIDTH*0.8);
	static int nextTextPosY           = (int) (Application.HEIGHT*0.95);
	static int selectedPosX           = (int) (Application.WIDTH*0.6);
	static int selectedPosY           = (int) (Application.HEIGHT*0.1);
	static int currentMapPosX         = (int) (selectedPosX) - (int) (currentMapWidth*0.38);
	static int currentMapPosY         = (int) (selectedPosY) + (int) (mapHeight*0.1);
	static int map1PosX               = (int) (Application.WIDTH*0.05);
	static int map1PosY               = (int) (Application.HEIGHT*0.6);
	static int map2PosX               = map1PosX + (int) (mapWidth*0.95);
	static int map2PosY               = map1PosY;
	static int map3PosX               = map2PosX + (int) (mapWidth);
	static int map3PosY               = map2PosY;
	static int map4PosX               = map3PosX + (int) (mapWidth);
	static int map4PosY               = map3PosY;
	static int map5PosX			      = map4PosX - (int) (mapWidth*1.5);
	static int map5PosY			      = map4PosY + (int) (mapWidth*0.6);
	
	
	String map1 = "images/Backgrounds/backgrounddetailed1.png";
	String map2 = "images/Backgrounds/backgrounddetailed2.png";
	String map3 = "images/Backgrounds/backgrounddetailed4.png";
	String map4 = "images/Backgrounds/backgrounddetailed5.png";
	String map5 = "images/Backgrounds/backgrounddetailed6.png";
	String selected  = "Map Selected: ";
	String mapSelect = "Please select a map background: ";
	String nextText  = "Press enter when finished.";
	
	Image mapOne;
	Image mapTwo;
	Image mapThree;
	Image mapFour;
	Image mapFive;
	Image currentMap;
	Image finalMap;
	
	boolean hasSelected = true;
	boolean mapChosen   = false;
	
	Player playerU;
	AIPlayer aiU;
	int state, type;
	
	public MapSelect(int state, Player player, AIPlayer ai, int type) 
	{
		super();
		this.state   = state;
		this.playerU = player;
		this.aiU     = ai;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		mapOne   = new Image(map1);
		mapTwo   = new Image(map2);
		mapThree = new Image(map3);
		mapFour  = new Image(map4);
		mapFive  = new Image(map5);
		currentMap = mapOne;
		finalMap   = currentMap;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.drawString(mapSelect, titlePosX, titlePosY);
		g.drawString(selected, selectedPosX, selectedPosY);
		g.drawImage(mapOne.getScaledCopy(mapWidth, mapHeight), map1PosX, map1PosY);
		g.drawImage(mapTwo.getScaledCopy(mapWidth, mapHeight), map2PosX, map2PosY);
		g.drawImage(mapThree.getScaledCopy(mapWidth, mapHeight), map3PosX, map3PosY);
		g.drawImage(mapFour.getScaledCopy(mapWidth, mapHeight), map4PosX, map4PosY);
		g.drawImage(mapFour.getScaledCopy(mapWidth, mapHeight), map5PosX, map5PosY);
		g.drawImage(currentMap.getScaledCopy(currentMapWidth, currentMapHeight), currentMapPosX, currentMapPosY);
		g.drawString(nextText, nextTextPosX, nextTextPosY);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		
		int mPosX = input.getMouseX();
		int mPosY = input.getMouseY();
		

		//Moves to the next state
		if(hasSelected && input.isKeyPressed(Input.KEY_ENTER))
		{
		
			game.addState(new Game(Application.GAME, playerU, aiU, finalMap, 2));
			game.getState(Application.GAME).init(container, game);
			game.enterState(Application.GAME, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));	
		}
		
		//Map1 
		if((mPosX > map1PosX && mPosX < (map1PosX + mapWidth)) && (mPosY > map1PosY  && mPosY < (map1PosY + mapHeight)))
		{
			//optionsTemp = optionsHighlighted;
			if(!mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapOne;
				finalMap    = currentMap;
				mapChosen   = true;
			}
		   
			if(mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapOne;
				finalMap    = currentMap;
				mapChosen   = false;
			}
			
		}
		else
		{
			//optionsTemp = options;
		}
		
		//Map2
		if((mPosX > map2PosX && mPosX < (map2PosX + mapWidth)) && (mPosY > map2PosY  && mPosY < (map2PosY + mapHeight)))
		{
			//optionsTemp = optionsHighlighted;
			if(!mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapTwo;
				finalMap    = currentMap;
				mapChosen   = true;
			}
		   
			if(mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapTwo;
				finalMap    = currentMap;
				mapChosen   = false;
			}
			
		}
		else
		{
			
			//optionsTemp = options;
		}
		
		//Map3
		if((mPosX > map3PosX && mPosX < (map3PosX + mapWidth)) && (mPosY > map3PosY  && mPosY < (map3PosY + mapHeight)))
		{
			//optionsTemp = optionsHighlighted;
			if(!mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapThree;
				finalMap    = currentMap;
				mapChosen   = true;
			}
		   
			if(mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapThree;
				finalMap    = currentMap;
				mapChosen   = false;
			}
			
		}
		else
		{
			
			//optionsTemp = options;
		}
		
		//Map4
		if((mPosX > map4PosX && mPosX < (map4PosX + mapWidth)) && (mPosY > map4PosY  && mPosY < (map4PosY + mapHeight)))
		{
			//optionsTemp = optionsHighlighted;
			if(!mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapFour;
				finalMap    = currentMap;
				mapChosen   = true;
			}
		   
			if(mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapFour;
				finalMap    = currentMap;
				mapChosen   = false;
			}
			
		}
		else
		{
			
			//optionsTemp = options;
		}
		
		//Map1 
		if((mPosX > map5PosX && mPosX < (map5PosX + mapWidth)) && (mPosY > map5PosY  && mPosY < (map5PosY + mapHeight)))
		{
			//optionsTemp = optionsHighlighted;
			if(!mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapFive;
				finalMap    = currentMap;
				mapChosen   = true;
			}
		   
			if(mapChosen && input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				currentMap  = mapFive;
				finalMap    = currentMap;
				mapChosen   = false;
			}
			
		}
		else
		{
			
			//optionsTemp = options;
		}
		
		//Go back to character select.
		if(input.isKeyPressed(Input.KEY_BACK))
		{
			System.out.println("Number of States: " + game.getStateCount());
			game.addState(new CharacterSelect(Application.CHARACTERSELECT, 0));
			game.getState(Application.CHARACTERSELECT).init(container, game);
			game.enterState(Application.CHARACTERSELECT, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
		}
		
	}

	@Override
	public int getID() 
	{
		return this.state;
	}

}
