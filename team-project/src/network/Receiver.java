package network;

import java.io.BufferedReader;
import java.io.IOException;

import gamegraphics.Game;
import main.Application;
//Receives updates from the game of the opponent and updates your game
public class Receiver extends Thread{

	private BufferedReader in;
	private boolean running;
	private int type;
	static GameNetwork game;


	public Receiver(BufferedReader in, int type, GameNetwork gameNet) 
	{
		this.in = in;
		game = gameNet;
		this.type = type;
	}


	public boolean isReady() throws IOException{
		return in.ready();
	}
	public String getInput() throws IOException{
		return in.readLine();
	}

	public boolean quitgame() {
		// TODO Auto-generated method stub
		return false;
	}
	private void startGame(int type){
		//GameNetwork game = new GameNetwork(Application.GAME,type);
		//Game game = new Game (Application.GAME, 0);
		running = true;
	}

	public void run(){
		try{
			while(true)
			{
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
					if(in.readLine() != null)
					{
						String msg = in.readLine();
						//System.out.println("Receiver: " + msg);
						game.update = msg;
						
					}
					else 
					{
						System.out.println("We are screwed!!");
					}
						
				}
			
		}
		catch(IOException e){
			e.getMessage();
		}
		
		
	}
	
	
}
