package com.halosuit.server;
import java.io.IOException;

import com.halosuit.gui.GUITestApplication;

public class Main {

	
	public static void main(String args[]) {
		
		int port = 8080;
		Server server;
		
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		
		try {
			server = new Server(port);			
			
			new GUITestApplication(server);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
