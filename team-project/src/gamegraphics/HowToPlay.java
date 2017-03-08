package gamegraphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import audio.Sound;
import gamelogic.GameLogic;
import main.Application;

public class HowToPlay extends BasicGameState 
{
	static final int titleWidth        = (int) (Application.WIDTH*0.58);
	static final int titleHeight       = (int) (Application.HEIGHT*0.15);
	static final int buttonWidth       = (int) (Application.WIDTH*0.09);
	static final int buttonHeight      = (int) (Application.HEIGHT*0.09);
	static final int mouseWidth        = (int) (Application.WIDTH*0.08);
	static final int mouseHeight       = (int) (Application.HEIGHT*0.2);
	static final int titleX            = (int) (Application.WIDTH*0.02);
	static final int titleY            = (int) (Application.HEIGHT*0.357);
	static final int textX             = (int) (Application.WIDTH*0.04);
	static final int textY             = (int) (Application.HEIGHT*0.7);
	static final int buttonWX          = (int) (Application.WIDTH*0.75);
	static final int buttonWY          = (int) (Application.HEIGHT*0.1);
	static final int buttonAX          = buttonWX - (int) (buttonWidth*1.2);
	static final int buttonAY          = buttonWY + (int) (buttonHeight*1.2);
	static final int buttonSX          = buttonAX + (int) (buttonWidth*1.2);
	static final int buttonSY          = buttonAY;
	static final int buttonDX          = buttonSX + (int) (buttonWidth*1.2);
	static final int buttonDY          = buttonAY;
	static final int mouseX            = buttonWX + (int) (buttonWidth*0.09);
	static final int mouseY            = buttonDY + (int) (buttonHeight*1.7);
	
	Image buttonW, buttonA, buttonS, buttonD, mouseI, title, mouseC;
	int state;
	String text1, text2, text3, text4, text5, text6, text7, text8, currentText, miniExplain, titleText;
	private TextField textF;
	
	private UnicodeFont font;
	
	public HowToPlay(int state) 
	{
		this.state = state;
		text1 = "Hello! I see that you have come to learn the Ways of the Warrior.\n"
				+"This will teach you the combat mechanics. Let us begin.\n"
				+"W              -- moves the character north in an absolute direction.\n"
				+"S              -- moves the character south in an absolute direction.\n"
				+"A              -- moves the character west in an absolute direction.\n"
				+"D              -- moves the character east in an absolute direction.\n"
				+"Mouse/Touchpad -- rotates the character with reference to the mouse cursor.\n"
				+"Mouse Button1  -- attack button for the character. Shoot, Slash, Blast.\n"
				+"Enter          -- pauses the game.\n"
				+"Backspace      -- takes a user to the previous state. E.g. Main Menu to Title Screen.";
		
		text2 = "W  -- \n"
				+"Allows a user to move the character upwards\n"
				+ "in an absolute direction.\n"
				+"Does not move towards the mouse cursor\n"
				+ "on the screen.";
		text3 = "S  -- \n"
				+"Allows a user to move the character towards\n"
				+ "the left in an absolute direction.\n"
				+"Does not move left with reference to \n"
				+ "the mouse cursor on the screen.";
		text4 = "A  -- \n"
				+"Allows a user to move the character towards\n"
				+ "the right in an absolute direction.\n"
				+"Does not move right with reference to\n"
				+ "the mouse cursor on the screen.";
		text5 = "D  -- \n"
				+"Allows a user to move the character downwards\n"
				+ "in an absolute direction.\n"
				+"Does not move away from the mouse cursor\n"
				+ "on the screen.";
		text6 = "Mouse  -- \n"
				+"Rotates the character with reference to \n"
				+ "the mouse cursor on the screen.\n"
				+"Pressing the mouse button allows\n"
				+ "the user to attack the opponent.\n"
				+"Attacks cause damage to the opponent\n"
				+ "and drain the user's energy/mana bar.";
		text7 = "Search Through Archive (Not implemented).";
		text8 = "Hover over an icon to see its description.";
		titleText = " 2D Top-Down View Fighting Game\n"
				+ "featuring distinct warriors and\n"
				+ "3 character types to choose from.\n"
				+ "Utilise strategy and skill to\n"
				+ "overwhelm your opponents as the\n"
				+ "Battles rage on!!";
		currentText = text1;
		miniExplain = "";
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		
		buttonW = new Image("images/howToPlay/computer_key_W_T.png");
		buttonA = new Image("images/howToPlay/computer_key_A_T.png");
		buttonS = new Image("images/howToPlay/computer_key_S_T.png");
		buttonD = new Image("images/howToPlay/computer_key_D_T.png");
		mouseI  = new Image("images/howToPlay/computer_mouse_T.png");
		title   = new Image("images/text/gameTitle.png");
		
		font = GameLogic.getNewFontC("Arial" , 16, new ColorEffect(java.awt.Color.black));
		font.loadGlyphs();
        textF = new TextField(container, font, (int) (Application.WIDTH*0.1),(int) (Application.HEIGHT*0.1),420,25);
        textF.setFocus(true);
        textF.setBorderColor(Color.cyan);
    	textF.setBackgroundColor(Color.white);
    	textF.setText(text7);
    	
    	mouseC        = new Image("images/Cursor/hero_sword.png");
		container.setMouseCursor(mouseC.getScaledCopy(0.3f).getFlippedCopy(false, true), 0, 0);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		textF.render(container, g);
		g.setColor(Color.cyan);
		g.drawImage(title.getScaledCopy(1.1f), titleX, titleY);
		g.drawImage(buttonW.getScaledCopy(buttonWidth,buttonHeight), buttonWX, buttonWY);
		g.drawImage(buttonA.getScaledCopy(buttonWidth,buttonHeight), buttonAX, buttonAY);
		g.drawImage(buttonS.getScaledCopy(buttonWidth,buttonHeight), buttonSX, buttonSY);
		g.drawImage(buttonD.getScaledCopy(buttonWidth,buttonHeight), buttonDX, buttonDY);
		g.drawImage(mouseI.getScaledCopy(mouseWidth, mouseHeight), mouseX, mouseY);
		g.drawString("Up", buttonWX + (int) (buttonWidth*0.42), buttonWY - (int) (buttonHeight*0.3));
		g.drawString("Left", buttonAX - (int) (buttonWidth*0.5), buttonAY + (int) (buttonHeight*0.3));
		g.drawString("Right", buttonDX + (int) (buttonWidth), buttonDY + (int) (buttonHeight*0.3));
		g.drawString("Down", buttonSX + (int) (buttonWidth*0.3), buttonSY + (int) (buttonHeight));
		g.drawString(currentText, textX, textY);
		g.drawString(text8, (int) (Application.WIDTH*0.63), (int) (Application.HEIGHT*0.01));
		g.drawString(miniExplain, buttonAX, textY);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
				Input input = container.getInput();
				
				int mPosX = input.getMouseX();
				int mPosY = input.getMouseY();
				
				if(input.isKeyPressed(Input.KEY_BACK))
				{
					//Sound.EnterScreen.loop();
					game.getState(Application.MAINMENU).init(container, game);
					game.enterState(Application.MAINMENU, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
					
				}
				
				//For the search textfield function.
				if(input.isKeyPressed(Input.KEY_ENTER))
				{
					
					
				}
		
				//W-button 
				if((mPosX > buttonWX && mPosX < (buttonWX + buttonWidth)) && (mPosY > buttonWY  && mPosY < (buttonWY + buttonHeight)))
				{
					miniExplain = text2;
				}
				else
				{
					miniExplain = "";
				}
				
				//A-button 
				if((mPosX > buttonAX && mPosX < (buttonAX + buttonWidth)) && (mPosY > buttonAY  && mPosY < (buttonAY + buttonHeight)))
				{
					miniExplain = text4;
				}
				
		
				//D-button 
				if((mPosX > buttonDX && mPosX < (buttonDX + buttonWidth)) && (mPosY > buttonDY  && mPosY < (buttonDY + buttonHeight)))
				{
					miniExplain = text5;
				}
				
				
				//S-button 
				if((mPosX > buttonSX && mPosX < (buttonSX + buttonWidth)) && (mPosY > buttonSY  && mPosY < (buttonSY + buttonHeight)))
				{
					miniExplain = text3;
				}
		
				
				//Mouse 
				if((mPosX > mouseX && mPosX < (mouseX + mouseWidth)) && (mPosY > mouseY  && mPosY < (mouseY + mouseHeight)))
				{
					miniExplain = text6;
				}

				
				//Title  
				if((mPosX > titleX && mPosX < (titleX + titleWidth)) && (mPosY > titleY  && mPosY < (titleY + titleHeight)))
				{
					miniExplain = titleText;
				}
		
		
	}

	@Override
	public int getID()
	{
		return state;
	}

}
