package com.halosuit.server;
import java.io.IOException;

import com.halosuit.gui.GUITestApplication;
import com.halosuit.gui.LogDisplay;
import com.halosuit.utils.Logger;

public class Main {
	
	
	
	public static void main(String args[]) {
		
		int port = 8080;
		Server server;
		GUITestApplication gui;
		
		LogDisplay log = new LogDisplay();
		
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		
		try {
			server = new Server(port);	
						
			gui = new GUITestApplication(server);
			
			// add GUI based log to server and GUI
			server.setLog(log);
			gui.addLogDisplay(log);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
