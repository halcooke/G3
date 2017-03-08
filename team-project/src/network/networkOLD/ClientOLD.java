package network.networkOLD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Application;

public class ClientOLD {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ClientListener clientlisten;
	private boolean open = true;
	public ClientOLD(String ip, int port, ClientListener listener){
		clientlisten = listener;
		try{
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			Thread clientTh = new Thread(new Runnable(){
				public void run(){
						while(open){
								try{
									Application game = new Application (null);
									String msg = in.readLine();
									if(msg==null){
										open=false;
										clientlisten.disconnected();
										try{if(socket!=null)socket.close();
										}catch(Exception e){e.printStackTrace();}
										try{if(in !=null) in.close();
										}catch(Exception ex) {ex.printStackTrace();}
										try{ if(out!=null) out.close();
										}catch(Exception exp) {exp.printStackTrace();}
										return;
									}
									clientlisten.recivedInput(msg);
								}catch(IOException exception){
									open=false;
									clientlisten.serverClosed();
									try { socket.close();
									}catch(Exception exon){exon.printStackTrace();}
									try{ out.close();
									}catch(Exception exon){exon.printStackTrace();}
									try{ in.close();
									}catch(Exception exon) {exon.printStackTrace();}
									return;
									}
								}
							}
						});
						clientTh.setName("Client");
						clientTh.setDaemon(true);
						clientTh.start();
						listener.connectedToServer();
			}catch(UnknownHostException exp){
					open=false;
					listener.unknownHost();
					
			}catch(IOException exp){
				open = false;
				listener.couldNotConnect();
			}catch(Exception exp){
				open= false;
				exp.printStackTrace();
			}
	}
	public void dispose(){
		try{
			if(open){
				open=false;
				socket.close();
				in.close();
				out.close();
				clientlisten.disconnected();
			}
			socket=null;
			in=null;
			out=null;
			clientlisten = null;
		} catch(Exception exp){exp.printStackTrace();}
		
	}
	public void send(String msg){
		if(open) out.println(msg);
	}
	public boolean isConnected(){return open;}
}