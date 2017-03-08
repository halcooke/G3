package gamegraphics;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.lwjgl.input.Mouse;
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
import main.Application;
import network.Client;
import network.GameNetwork;
import network.Server;

public class MainMenu extends BasicGameState
{

	//attributes
	static final int titleWidth       = (int) (Application.WIDTH*0.5);
	static final int titleHeight      = (int) (Application.HEIGHT*0.2);
	static int titlePosX              = (int) (Application.WIDTH*0.26);
	static int titlePosY              = (int) (Application.HEIGHT*0.2);
	static final int playWidth        = (int) (Application.WIDTH*0.4);
	static final int playHeight       = (int) (Application.HEIGHT*0.1);
	static final int howToPlayWidth   = (int) (playWidth*0.71);
	static final int howToPlayHeight  = (int) (playHeight);
	static final int optionsWidth     = (int) (playWidth*0.4);
	static final int optionsHeight    = (int) (playHeight);
	static final int quitWidth        = (int) (playWidth*0.55);
	static final int quitHeight       = (int) (playHeight);
	static int playOffPosX            = (int) (Application.WIDTH*0.31);
	static int playOffPosY            = (int) (Application.HEIGHT*0.43);
	static int playOnPosX             = playOffPosX;
	static int playOnPosY             = playOffPosY + (int) (playHeight*0.95);
	static int howToPlayPosX          = playOnPosX;
	static int howToPlayPosY          = playOnPosY + (int) (playHeight*0.95);
	static int optionsPosX            = playOnPosX;
	static int optionsPosY            = howToPlayPosY + (int) (playHeight*0.95);
	static int quitPosX               = optionsPosX;
	static int quitPosY               = optionsPosY + (int) (playHeight*0.95);

	int state;
	boolean up = false, down = false, left = false, right = false, one = false;
	private Image playOff;
	private Image playOn;
	private Image howToPlay;
	private Image options;
	private Image quitGame;
	private Image title;
	private Image playOffHighlighted;
	private Image playOnHighlighted;
	private Image howToPlayHighlighted;
	private Image optionsHighlighted;
	private Image quitGameHighlighted;
	private Image playOffTemp;
	private Image playOnTemp;
	private Image howToPlayTemp;
	private Image optionsTemp;
	private Image quitGameTemp;

	private Image mouseC;

	JLabel label = new JLabel("Click the \"Show it!\" button"
			+ " to bring up the selected dialog.",
			JLabel.CENTER);

	public MainMenu(int state) 
	{
		super();
		this.state = state;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		mouseC        = new Image("images/Cursor/best_sword.png");
		container.setMouseCursor(mouseC.getScaledCopy(0.3f).getFlippedCopy(false, true), 0, 0);
		
		title      = new Image("images/text/gameTitle.png");
		playOff    = new Image("images/text/playOffMatch.png");
		playOn     = new Image("images/text/playOnMatch.png");
		howToPlay  = new Image("images/text/howToPlay.png");
		options    = new Image("images/text/optionsT.png");
		quitGame   = new Image("images/text/quitGame.png");
		playOffHighlighted   = new Image("images/text/playOffMatchSd.png");
		playOnHighlighted    = new Image("images/text/playOnMatchSd.png");
		howToPlayHighlighted = new Image("images/text/howToPlaySd.png");
		optionsHighlighted   = new Image("images/text/optionsTSd.png");
		quitGameHighlighted  = new Image("images/text/quitGameSd.png");
		playOffTemp   = playOff;
		playOnTemp    = playOn;
		howToPlayTemp = howToPlay;
		optionsTemp   = options;
		quitGameTemp  = quitGame;

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.drawImage(title.getScaledCopy(titleWidth, titleHeight), titlePosX, titlePosY);
		g.drawImage(playOffTemp.getScaledCopy(playWidth, playHeight), playOffPosX, playOffPosY);
		g.drawImage(playOnTemp.getScaledCopy(playWidth, playHeight), playOnPosX, playOnPosY);
		g.drawImage(howToPlayTemp.getScaledCopy(howToPlayWidth, howToPlayHeight), howToPlayPosX, howToPlayPosY);
		g.drawImage(optionsTemp.getScaledCopy(optionsWidth, optionsHeight), optionsPosX, optionsPosY);
		g.drawImage(quitGameTemp.getScaledCopy(quitWidth, quitHeight), quitPosX, quitPosY);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();

		int mPosX = input.getMouseX();
		int mPosY = input.getMouseY();

		up = input.isKeyDown(Input.KEY_W);
		down = input.isKeyDown(Input.KEY_S);
		left = input.isKeyDown(Input.KEY_A);
		right = input.isKeyDown(Input.KEY_D);
		one = input.isKeyDown(Input.KEY_1);

		if(up)
		{

		}

		if(down)
		{

		}


		//Mick's idea!

		//Test the position of the mouse.
		//		System.out.println("px: " + playOffPosX + " py: " + playOffPosY + " pw: " + playWidth + " ph: " + playHeight);
		//		System.out.println("mx: " + mPosX + " my: " + mPosY);


		//Quick exit
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}

		//Back to Title Screen
		if(input.isKeyPressed(Input.KEY_BACK))
		{

			System.out.println("Back!");
			game.enterState(Application.SPLASHSCREEN, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
		}

		//quit game button
		if((mPosX > quitPosX && mPosX < (quitPosX + quitWidth)) && (mPosY > quitPosY  && mPosY < (quitPosY + quitHeight)))
		{
			quitGameTemp = quitGameHighlighted;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				System.out.println("End Game");
				//JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "End Game?", "End", JOptionPane.OK_CANCEL_OPTION);
				System.exit(0);

			}

		}
		else
		{
			quitGameTemp = quitGame;
		}

		//How play game button.
		if((mPosX > howToPlayPosX && mPosX < (howToPlayPosX + howToPlayWidth)) && (mPosY > howToPlayPosY  && mPosY < (howToPlayPosY + howToPlayHeight)))
		{
			howToPlayTemp = howToPlayHighlighted;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				System.out.println("This is how to play: ");
				game.addState(new HowToPlay(Application.HOWTOPLAY));
				game.getState(Application.HOWTOPLAY).init(container, game);
				game.enterState(Application.HOWTOPLAY, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
				//JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "End Game?", "End", JOptionPane.OK_CANCEL_OPTION);

			}	
		}
		else
		{
			howToPlayTemp = howToPlay;
		}


		//options button
		if((mPosX > optionsPosX && mPosX < (optionsPosX + optionsWidth)) && (mPosY > optionsPosY  && mPosY < (optionsPosY + optionsHeight)))
		{
			optionsTemp = optionsHighlighted;
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				System.out.println("Settings!");
				//game.enterState(Application.SETTINGS, new FadeOutTransition(Color.black, 1000), new FadeInTransition(Color.black, 1000));
			}
		}
		else
		{
			optionsTemp = options;
		}

		//play offline button
		if((mPosX > playOffPosX && mPosX < (playOffPosX + playWidth)) && (mPosY > playOffPosY  && mPosY < (playOffPosY + playHeight)))
		{
			playOffTemp = playOffHighlighted;

			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				//SOUND
				System.out.println("Demo Game start!");
				Sound.EnterScreen.stop();
				Sound.Battle.loop();
				System.out.println("Number of States: " + game.getStateCount());
				game.addState(new CharacterSelect(Application.CHARACTERSELECT, 0));
				game.getState(Application.CHARACTERSELECT).init(container, game);
				game.enterState(Application.CHARACTERSELECT, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));

				/**
				//Reset the game state for now
				System.out.println("Number of states: " + game.getStateCount());
				game.addState(new Game(Application.GAME, 1));
				game.getState( Application.GAME ).init(container, game);
				game.enterState(Application.GAME, new FadeOutTransition(Color.black, 1000), new FadeInTransition(Color.black, 1000));
				 **/
			}
		}
		else
		{
			playOffTemp = playOff;
		}

		//play online button
		if((mPosX > playOnPosX && mPosX < (playOnPosX + playWidth)) && (mPosY > playOnPosY  && mPosY < (playOnPosY + playHeight)))
		{
			playOnTemp = playOnHighlighted;

			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				System.out.println("Network play to be implemented");
				Sound.EnterScreen.stop();
				Object[] options = {"Exit",
						"Host",
				"Join"};

				int n = JOptionPane.showOptionDialog(JOptionPane.getRootFrame(),
						"Please select the mode ",
						"Network Play",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[2]);

				//Default args value
				String[] args = {""};

				//As host!
				if(n == 1)
				{
					Sound.Battle.loop();
					game.addState(new GameNetwork(Application.NETWORKPLAY, n, args, 1));
					game.getState(Application.NETWORKPLAY ).init(container, game);
					game.enterState(Application.NETWORKPLAY, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
				}


				//As client
				if(n == 2)
				{
					Sound.Battle.loop();
					game.addState(new GameNetwork(Application.NETWORKPLAY, n, args, 1));
					game.getState(Application.NETWORKPLAY ).init(container, game);
					game.enterState(Application.NETWORKPLAY, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));

				}
			}

		}
		else
		{
			playOnTemp = playOn;
		}
	}

	public UnicodeFont getNewFont(String fontName , int fontSize) throws SlickException
	{
		UnicodeFont ufont = null;
		Font font = new Font(fontName, Font.BOLD, fontSize);
		ufont = new UnicodeFont(font, font.getSize(), font.isBold(), font.isItalic());
		ufont.addAsciiGlyphs();
		//ufont.addGlyphs(400, 600);
		ufont.getEffects().add(new ColorEffect(java.awt.Color.RED));
		ufont.loadGlyphs();
		return ufont;
	}

	@Override
	public int getID() 
	{
		return this.state;
	}

}
