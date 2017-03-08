package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Application;

public class Client {
	//private static final Exception IllegalArgumentException = null;
	//Socket socket;
	//DataOutputStream out;
	//DataInputStream in;
	/*
	public static void main(String[] args){
		
			if(args.length != 1){
				System.exit(1);
			}
		//String hostname = args[0];
		//PrintStream 
		DataInputStream in;
		DataOutputStream out;
		Socket server;
		int port = 56500;
		boolean connection = true;
		try{
			String hostname = "127.0.0.1";
			server = new Socket(hostname , port);
			out = new DataOutputStream(server.getOutputStream());
			in = new DataInputStream(server.getInputStream());
			Application game = new Application ("Try");
			game.main(null);
			while(connection){
			System.out.print("Game would be here");
			}
		}catch(UnknownHostException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	*/
	
	private Socket server;
	private PrintStream out;
	private BufferedReader in;
	private String[] args;
	static GameNetwork game;
	private Exception IllegalArgumentException;
	
	
	Client(String [] args)
	{
		assert(args.length == 2);
		this.args = args;
		
	}
	
	public static void main(String[] args, GameNetwork gameNet)
	{
		Client client = new Client (args);
		game = gameNet;
		client.startClient();
		
	}

	  public void startClient() 
	  {

	        Runnable clientTask = new Runnable() {
	            
	        	@Override
	            public void run() 
	        	{
	            	runC();
	        	}
	        };
	        Thread clientThread = new Thread(clientTask);
	        clientThread.start();
	  }

	private void runC() 
	{
		makeConnection();
		
		loopforagame();
		
		closeConnection();
		
	}

	private void makeConnection() {
		try{
			server = getServerSocket();
			System.out.println("Made a connection to the server");
			game.clientConnected = true;
		}
		catch(IllegalArgumentException e){
			game.clientConnected = false;
			e.printStackTrace();
			//System.exit(0);
			
		}catch(UnknownHostException e){
			//System.exit(0);
			game.clientConnected = false;
			e.printStackTrace();
			
		} catch (IOException e) {
			
			game.clientConnected = false;
			e.printStackTrace();
			//System.exit(0);
		}
		
		
	}
	
	private Socket getServerSocket () throws UnknownHostException, IOException{
		
		if(args.length != 2){
				throw new IllegalArgumentException
				("Cleintx hostname portnumber");
		}
		String hostname = args[0];
		int port = 0; 
		
		try{
			port = Integer.parseInt(args[1]);
		}catch (NumberFormatException e){
			throw new IllegalArgumentException
			("Port number must be an integer");
		}
		
		return new Socket(hostname, port);
	}

	private void loopforagame() {
		try{
			out = new PrintStream (server.getOutputStream());
			in = new BufferedReader (new InputStreamReader(server.getInputStream()));
		}catch(IOException e){
			//System.exit(0);
			e.printStackTrace();
		}
		Sender clientSender = new Sender(out, game);
		Receiver clientReceiver = new Receiver (in, 2,game);
		
		clientSender.start();
		clientReceiver.start();
		boolean connection = true;

		while(connection)
		{
			//game.clientConnected = true;
			if(clientSender.quitgame()||clientReceiver.quitgame())
			{
				connection=false;
			}
		}
	}

	public void closeConnection() {
		try{
			out.close();
			in.close();
			server.close();
			System.out.println("Closed connection");
		}
		catch(IOException e ){
			//System.exit(0);
		}
	}

	
}