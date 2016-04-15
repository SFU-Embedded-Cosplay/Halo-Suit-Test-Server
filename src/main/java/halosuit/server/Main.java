package main.java.halosuit.server;
import java.io.IOException;

import main.java.halosuit.gui.LogDisplay;
import main.java.halosuit.gui.TestServerFrame;

public class Main {
	
	
	
	public static void main(String args[]) {
		
		int port = 8080;
		Server server;
		TestServerFrame gui;
		
		LogDisplay log = new LogDisplay();
		
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		
		try {
			server = new Server(port);	
						
			gui = new TestServerFrame(server);
			
			// add GUI based log to server and GUI
			server.setLog(log);
			gui.addLogDisplay(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}