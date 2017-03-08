package network;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

// Gets updates from your game and sends details of updates to the opponent
//implements Observer
public class Sender extends Thread{

	private PrintStream out;
	GameMessage mes;
	static GameNetwork game;

	public Sender(PrintStream out, GameNetwork gameNet) {
		this.out = out;
		game     = gameNet;
	}

	public boolean quitgame() {
		return false;
	}

	
	public synchronized void sendUpdate (String msg)
	{
		out.println(msg);	
	}
	
	// class for reading updates from GameMessager
	public class SendObserver implements Observer{
		public String name;
		private String updateString;
		
		public SendObserver(String name)
		{
			this.name = name;
		}
		
		@Override
		public void update(Observable o, Object arg) 
		{
			updateString = (String) arg;
			//System.out.println("To test what kind of strings are going to be printed out");
		}
		
		public String getString(){
			return updateString;
		}
	}
	
	public void run()
	{
		mes = game.mes;
		//GameMessage changes = mes;
		SendObserver observe = new SendObserver("ObserverforSender");
		game.mes.addObserver(observe);
		while(true)
		{
			//System.out.println("Changes: " + observe.getString());
			sendUpdate(observe.getString());
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	// Setting the message.
	/*private String setMessage() {
		
	}
*/
	




	/*@Override
	public void update() {
		String msg = (String) observable.getUpdate(this);
		if(msg == null){
			
		}
		
		}

	@Override
	public void setObservable(MyObservable obs) {
		this.observable = obs;
	}*/
	
	
}
