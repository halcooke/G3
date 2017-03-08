package network;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import gamegraphics.Board;
import gamegraphics.Game;
import gamegraphics.MainMenu;
import main.Application;
// implements MyObservable
public class GameNetwork extends Game{

	int state, gameType, port;
	String ip;
	String update;
	boolean isConnected = false;
	boolean isRunning   = false;
	boolean clientConnected = false;
	int type = 1;
	String[] args;
	Server server;
	GameMessage mes = new GameMessage("Start");
	//private Board map;
	//private Player player;
	//private List<Observer> observers;
	//private boolean changed;
	//private String message;
	//private final Object Mutex = new Object();
	
	//Made changes to the constructor - Isa. (Added gameType)
	public GameNetwork(int state, int type, String[] args, int gameType) 
	{
		super(state, gameType, false);
		
		this.state    = state;
		this.gameType = gameType;
		this.type     = type;
		this.args     = args;
		
		checkType(type,args);
		
	}

	private void initAsServer() 
	{
		server = new Server();
		server.main(null, this);
		
		try 
		{
			displayInfo();
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
	}

	private void initAsClient(String[] args) 
	{
		JTextField port = new JTextField();
		JTextField ip = new JTextField();
		Object[] message = {
		    "Port No.:", port,
		    "IP Address:", ip
		};
		
		clientConnect(port, ip, message);
	}
	
	public void displayInfo() throws InterruptedException
	{
		
		Thread.sleep(2000);
		
		Object[] message = 
			{
			    "Port No.:", Integer.toString(port),
			    "IP Address:", ip
			};

		while(isRunning)
		{
			if(!isConnected)
			{
				int option = JOptionPane.showConfirmDialog(null, message, "Waiting for Connection", JOptionPane.OK_CANCEL_OPTION);
				
				if (option == JOptionPane.OK_OPTION) 
				{
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "No one has connected. Please wait.", "Waiting for Connection", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Someone has not connected! Please wait!");
				} 
				else if(option == JOptionPane.CANCEL_OPTION)
				{
					isRunning   = false;
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Will return back to the Main Menu.", "Match Cancelled", JOptionPane.INFORMATION_MESSAGE);
				    System.out.println("Match cancelled");
				}
				else if(option == JOptionPane.OK_OPTION && isConnected)
				{
					System.out.println("Can proceed");
				}
			}
			else
			{
				System.out.println("Someone has connected! All Good!");
				isRunning = false;
			}
		}
		
		isRunning = true;
		
	}
	
	public void clientConnect(JTextField port, JTextField ip, Object[] message)
	{
		
		int option = JOptionPane.showConfirmDialog(null, message, "Connect", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION && !clientConnected) 
		{
			String[] conS = {ip.getText(),port.getText()};
			Client client = new Client(conS);
			client.main(conS, this);
			
			try 
			{
				Thread.sleep(3000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		} 
		else if(clientConnected && option == JOptionPane.OK_OPTION) 
		{
		    System.out.println("It's all good!");
		}
		else if(option == JOptionPane.CANCEL_OPTION)
		{
			clientConnected = false;
		}
	
	}
	
	public void checkType(int type, String[] args)
	{
		if(type==1)
		{
			initAsServer();
		}
		if(type==2)
		{
			initAsClient(args);
		}
		
	}
	
	public int getType() 
	{
		return type;
	}

	public void setType(int type) 
	{
		this.type = type;
	}
	
	/**
	 * Initialises the board and passes in a game container and the state based game.
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		mouseC     = new Image("images/Cursor/ic_cursor_off.png");
		
		container.setMouseCursor(mouseC.getScaledCopy(0.3f), 0, 0);
			
		if(gameType == 1)
		{
			this.map = new Board(container,type);
			update = map.getEnemyMoves();
			mes.setChanges(map.getEnemyMoves());
		}
		
		if(gameType == 2)
		{
			this.map = new Board(container,player, ai, mapBG, type);
			update = map.getEnemyMoves();
			mes.setChanges(map.getEnemyMoves());
		}
	}
	
	/**
	 * Renders the map with the characters and obstacles on it. 
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
//		System.out.println("Is connected: " + isConnected);
//		System.out.println("Client connected: " + clientConnected);
		
		if(isConnected || clientConnected)
		{
			map.draw(g);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		
			if(!isConnected && !clientConnected || !clientConnected && !isRunning)
			{
				game.addState(new MainMenu(Application.MAINMENU));
				game.getState(Application.MAINMENU).init(container, game);
				game.enterState(Application.MAINMENU, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
			}
			
			Input input = container.getInput();
			
			if(input.isKeyPressed(Input.KEY_BACK))
			{
				game.enterState(Application.MAINMENU, new FadeOutTransition(Color.black, Application.TRANSITION), new FadeInTransition(Color.black, Application.TRANSITION));
			}
			
			map.update(delta, update);
			String update2 = map.getYourMoves();
			mes.setChanges(update2);		
	}
	
	/**
	 * Gets the state id.
	 */
	public int getID() 
	{
		return this.state;
	}
	
	public void updateOpp(String msg) 
	{
		map.updateOpp(msg);
	}
	
	
	public void setIP(String ip)
	{
		this.ip = ip;
	}
	
	public void setPort(int port)
	{
		this.port = port;
	}

	/*@Override
	public void register(Observer obj) {
		if(obj==null) throw new NullPointerException ("NullPointerException");
			synchronized(Mutex){
			if(!observers.contains(obj)) observers.add(obj);
		}
	}

	@Override
	public void unregister(Observer obj) {
		synchronized(Mutex){
			observers.remove(obj);
		}
		
	}

	@Override
	public void notifyObservers() {
		List<Observer> observerLoc = null;
		synchronized(Mutex){
			if(!changed)
				return;
			observerLoc = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for(Observer obj : observerLoc){
			obj.update();
		}
		
	}

	@Override
	public Object getUpdate(Observer obj) {
		return this.message;
	}
	
		public void postMes (String msg){
			this.message = msg;
			this.changed=true;
			notifyObservers();
			
			
			
		}*/
	
}