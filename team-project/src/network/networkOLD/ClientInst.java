package network.networkOLD;

import java.net.InetAddress;

public class ClientInst {
	public final InetAddress ip;
	public final int port;
	public ClientInst (InetAddress ip, int port){
		this.ip = ip;
		this.port = port;
	}
	public String toString(){
		return ip.toString() +":"+ port;
	}
}
