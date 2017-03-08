package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import gamegraphics.Game;
import main.Application;

//Simple Server class based on tic Tac toe game from first year
public class Server {
/*
	//private int port;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//int port = 4465;
		ServerSocket server = null;
		boolean connection = false;
		//int port ;
		try {
			server = new ServerSocket(0);
			int port = server.getLocalPort();
			System.out.println("Listetning to port" + port);
		}catch(IOException e){
			System.out.println("Couldn't listen to the " );
		}
		
		try{
			Socket socket = server.accept();
			DataOutputStream out;
			DataInputStream in;
			
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				connection = true;
				String msg ;
				while(connection){
					//if(msg = in.)
					System.out.println("here im going to send messages to the client");
				//Game here?
					////Application game = new Application ("Game");
					//game.main(null);
					//System.out.println("Game is supposed to be here");
				}
				out.close();
				in.close();
				socket.close();
			}catch(IOException e){
				System.exit(1);
				
			}
		}catch(Exception e){
			try{
				server.close();
			}catch(IOException io){
				io.printStackTrace();
			}
			
		}
		

	}
*/	static ServerSocket server = null;

	static int portNum;
	static InetAddress ipAddr;
	static GameNetwork game;
	static boolean connection;
	
	public static void main (String[] args, GameNetwork gameNet)
	{
		Server server = new Server();
		game = gameNet;
		server.startServer();
	}
	
	 public void startServer() {

	        Runnable serverTask = new Runnable() {
	            
	        	@Override
	            public void run() 
	        	{
	            	runS();
	        	}
	        };
	        Thread serverThread = new Thread(serverTask);
	        serverThread.start();
	  }
	
	public void runS() 
	{
		openSocket();
		
		acceptClientandloop();
		
		closeConnection();
		
	}

	private static void openSocket() {
		try{
			server = new ServerSocket(0);
			int port = server.getLocalPort();
			game.port = port;
			String addr1     = getIpAddress();
			game.ip = addr1;
			
			System.out.println("Listetning to port" + port);
			System.out.println("Your IP number is : " + addr1);
			
			game.isRunning = true;
		}catch(IOException e)
		{
			System.out.println("Couldn't listen to the port.");
		}
	}
			
public static void closeConnection() {
	try{
		game.isRunning   = false;
		game.isConnected = false;
		server.close();
		System.out.println("Closed connection");
	}
		catch(IOException e){
			e.printStackTrace();
		}
}


public static String getIpAddress(){

	String ip;
	try{	
	Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
    for (NetworkInterface netint : Collections.list(nets))
        if (netint.getName().equals("wlan0") || netint.getName().equals("en1") || netint.getName().equals("en0")) {
        	
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            while(inetAddresses.hasMoreElements())
            {
	            InetAddress addr = inetAddresses.nextElement();
	            
	            if(addr instanceof Inet4Address){
		            ip = addr.getHostAddress();
		            return ip;

	            }
            }
        
        }
		} catch (SocketException e) 
		{
				throw new RuntimeException(e);
		}
	return null;
}

private static void acceptClientandloop() 
{
	try{
		Socket socket = server.accept();
		PrintStream out;
		BufferedReader in;
		
		try {
			out = new PrintStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
			
			
			String msg ;
			
			Sender serverSender = new Sender(out, game);
			Receiver serverReceiver = new Receiver(in,1, game);
			
			serverSender.start();
			serverReceiver.start();
			
			connection = true;
			
			while(connection)
			{
				game.isConnected = true;
				
				if(serverSender.quitgame() || serverReceiver.quitgame())
				{
					connection = false;
					game.isRunning = false;
					game.isConnected = false;
				}
			}
			out.close();
			in.close();
			socket.close();
		}catch(IOException e)
		{
			System.exit(1);
		}
		
	}catch(Exception e){
		System.exit(1);
	}

   }
}
