package main.java.halosuit.gui;

import java.awt.BorderLayout;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.java.halosuit.server.Server;

public class ServerStatusPanel extends JPanel {

	public static final String CONNECTION_MESSAGE_NOT_CONNECTED = "Currently not connected to client";
	public static final String CONNECTION_MESSAGE_WAITING_FOR_CONNECTION = "Currently waiting for connection from client";
	public static final String CONNECTION_MESSAGE_CONNECTED = "Currently connected to client";
	
	private Server server = null;
	
	private JButton listenForConnectionButton = new JButton("Wait for connection");
	private JLabel serverStatus = new JLabel(CONNECTION_MESSAGE_NOT_CONNECTED, SwingConstants.CENTER); //TODO: figure out how to nicely add color to this
	
	public ServerStatusPanel(Server server) {
		this.server = server;		
		setLayout(new BorderLayout());
	
		add(serverStatus, BorderLayout.NORTH);
		add(listenForConnectionButton,  BorderLayout.CENTER);
		
		
		listenForConnectionButton.addActionListener((e)-> {
			new Thread(()->connectToServer()).start();
		});
		
		server.addObserver((observedServer, args) -> {			
			if(server.isConnected()) {
				serverStatus.setText(CONNECTION_MESSAGE_CONNECTED);
			} else if(server.isConnecting()) {
				serverStatus.setText(CONNECTION_MESSAGE_WAITING_FOR_CONNECTION);
			} else {
				serverStatus.setText(CONNECTION_MESSAGE_NOT_CONNECTED);
			}
		});
	}
	
	private void connectToServer() {
		try {
			server.listenForConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}
	
}
