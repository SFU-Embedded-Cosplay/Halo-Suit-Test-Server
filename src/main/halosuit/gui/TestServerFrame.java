package main.halosuit.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.halosuit.server.Server;

public class TestServerFrame extends JFrame implements KeyListener{

	private Server server;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public static final String TITLE = "Test GUI";
	
	private JButton sendButton = new JButton("Send");
	private JTextField serverMessageBox = new JTextField();
	private JButton listenForConnectionButton = new JButton("Wait for connection");
	
	private JButton clearButton =  new JButton("Clear");
	
	private JTextArea androidAppInputTextArea = new JTextArea();
	
	private JPanel sendMessagePanel = new JPanel();
	private JSONMessageBuilderPanel messageBuilderPanel =  new JSONMessageBuilderPanel();
	
	private JTabbedPane tabbedPane = new JTabbedPane();
 
	
	private LogDisplay log;
	
	
	private static final int SEND_BUTTON_HEIGHT = 20;
	private static final int SEND_BUTTON_WIDTH = 80;
	
	private static final int SEND_MESSAGE_BOX_PADDING =  30;
	
	public TestServerFrame(Server server) {
		this.server = server;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		//setBorderLayout()
		
		server.addInputListener((inputCharacter)->addInputCharacter(inputCharacter));
		
		
		sendButton.setPreferredSize(new Dimension(SEND_BUTTON_WIDTH, SEND_BUTTON_HEIGHT));
		serverMessageBox.setPreferredSize(new Dimension(WIDTH  - SEND_BUTTON_WIDTH - SEND_MESSAGE_BOX_PADDING, SEND_BUTTON_HEIGHT));
		
		sendMessagePanel.add(serverMessageBox);
		sendMessagePanel.add(sendButton);
		
		
		sendButton.addActionListener((e) -> {
			sendMessageToPhone(serverMessageBox.getText());
			serverMessageBox.setText("");
		});
		
		listenForConnectionButton.addActionListener((e)-> {
			new Thread(()->connectToServer()).start();
		});
		
		clearButton.addActionListener((e)->{
			androidAppInputTextArea.setText(""); // clears text area
		});
		
		add(sendMessagePanel, BorderLayout.NORTH);
				
		tabbedPane.addTab("Server Status", listenForConnectionButton);
		tabbedPane.addTab("Client Messages", androidAppInputTextArea);
		tabbedPane.addTab("Message Builder", messageBuilderPanel);
		
		add(tabbedPane, BorderLayout.CENTER);
				
		add(clearButton, BorderLayout.SOUTH);
		
				
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
			if(server.isConnected()){
				server.sendMessage(message);
			} else {
				JOptionPane.showMessageDialog(this,
					    "Error sending message to client. Ensure you are connected to the client",
					    "Send Message Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (IOException e) {
			//should not happen at the moment, find a way to deal with this.
			e.printStackTrace();
		} 
	}
	
	public void addLogDisplay(LogDisplay log) {
		// remove old log. does nothing if old log was not added / null.
		tabbedPane.remove(this.log); 
		
		this.log = log;
		
		tabbedPane.addTab("Log", log);
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
