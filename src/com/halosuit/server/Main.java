package com.halosuit.server;
import java.io.IOException;

public class Main {

	
	public static void main(String args[]) {
		
		int port = 8080;
		
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		try {
			Server server = new Server(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
