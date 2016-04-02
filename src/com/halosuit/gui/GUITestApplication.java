package com.halosuit.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.halosuit.server.Server;

public class GUITestApplication extends JFrame implements KeyListener{

	private Server server;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static final String TITLE = "Test GUI";
	
	private JButton sendButton = new JButton("Send");
	private JTextField serverMessageBox = new JTextField();
	private JButton listenForConnectionButton = new JButton("Wait for connection");
	
	private JTextArea androidAppInputTextArea = new JTextArea();
	
	public GUITestApplication(Server server) {
		this.server = server;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		//setBorderLayout()
		
		server.addInputListener((inputCharacter)->addInputCharacter(inputCharacter));
		
		
		sendButton.setPreferredSize(new Dimension(50, 50));
		
		
		sendButton.addActionListener((e) -> {
			sendMessageToPhone(serverMessageBox.getText());
			serverMessageBox.setText("");
		});
		
		listenForConnectionButton.addActionListener((e)-> {
			new Thread(()->connectToServer()).start();
		});
		
		add(serverMessageBox, BorderLayout.NORTH);
		
		add(androidAppInputTextArea, BorderLayout.CENTER);
		
		add(listenForConnectionButton, BorderLayout.EAST);
		
		add(sendButton, BorderLayout.SOUTH);
		
		serverMessageBox.addKeyListener(this);
		
	}
	
	private void addInputCharacter(char inputCharacter) {
		androidAppInputTextArea.append("" + inputCharacter);
	}

	private void connectToServer() {
		try {
			server.listenForConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void sendMessageToPhone(String message) {		
		try {
			server.sendMessage(message);			
		} catch (IOException e) {
			//should not happen at the moment, find a way to deal with this.
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == Event.ENTER) {
			sendButton.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
