package network.networkOLD;

import java.io.PrintWriter;

public interface ServerListener {
	public void clientConnected (ClientInst client, PrintWriter out);
	public void clientDisconnected (ClientInst client);
	public void receivedInput (ClientInst client, String msg);
	public void serverClosed ();

}
