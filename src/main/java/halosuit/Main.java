package main.java.halosuit;
import java.io.IOException;

import main.java.halosuit.gui.LogDisplay;
import main.java.halosuit.gui.TestServerFrame;
import main.java.halosuit.server.Server;

public class Main {
	
	public static final int DEFAULT_PORT = 8080;
	
	public static void main(String args[]) {
		
		int port = DEFAULT_PORT;
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
