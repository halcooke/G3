package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import gamegraphics.SplashScreen;
import network.GameNetwork;

/** 
 * Use this to run the game.
 * @author ibs483
 *
 */
public class Application extends StateBasedGame 
{

    // Game state identifiers
    public static final int SPLASHSCREEN    = 0;
    public static final int MAINMENU        = 1;
    public static final int GAME            = 2;
    public static final int SETTINGS        = 3;
    public static final int CHARACTERSELECT = 4;
    public static final int MAPSELECT       = 5;
    public static final int NETWORKPLAY     = 6;
    public static final int GAMEOVER        = 7;
    public static final int HOWTOPLAY 		= 8;

    // Application Properties
    public static final int FPS        = 60;
    public static final double VERSION = 1.0;
    public static final int TRANSITION = 500;
    
    //final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 700;
	static int frames = 0;

	
	private BasicGameState SplashScreen;
	
    // Class Constructor
    public Application(String appName)
    {
        super(appName);
          SplashScreen = new SplashScreen(SPLASHSCREEN);
 
    }

    // Initialize the game states (calls init method of each gamestate, and set's the state ID)
    public void initStatesList(GameContainer gc) throws SlickException 
    {
        // The first state added will be the one that is loaded first, when the application is launched
        this.addState(SplashScreen);

    }
    

	// Main Method
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Application("Armiger Wars V.0 " + VERSION));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.setShowFPS(true);
            app.start();
        } catch(SlickException e) 
        {
            e.printStackTrace();
        }
    }

}
