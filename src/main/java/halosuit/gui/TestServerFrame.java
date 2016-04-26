package main.java.halosuit.gui;

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

import main.java.halosuit.gui.MessageBuilder.MessageBuilderPanel;
import main.java.halosuit.server.Server;

public class TestServerFrame extends JFrame implements KeyListener{

	private Server server;
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	public static final String TITLE = "Halo-Suit Testing Server";
	
	private JButton sendButton = new JButton("Send");
	private JTextField sendMessageField = new JTextField();
	private JPanel sendMessagePanel = new JPanel();
	
	private JButton clearButton = new JButton("Clear");
	private JButton clearLogButton = new JButton("Clear");
	
	private JTextArea receiveMessageBox = new JTextArea();
	
	private  ServerStatusPanel serverStatus = null;
	
	private MessageBuilderPanel messageBuilderPanel =  new MessageBuilderPanel(
			MessageBuilderPanel.DEFAULT_JSON_TEMPLATE_FILE_LOCATION, 
			(message) -> { 
				sendMessageField.setText(message); 
			});
	
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
		setResizable(true);
		
		server.addInputListener((inputCharacter)->addInputCharacter(inputCharacter));

		
		serverStatus = new ServerStatusPanel(server);
		
		sendButton.setPreferredSize(new Dimension(SEND_BUTTON_WIDTH, SEND_BUTTON_HEIGHT));
		sendMessageField.setPreferredSize(new Dimension(WIDTH  - SEND_BUTTON_WIDTH - SEND_MESSAGE_BOX_PADDING, SEND_BUTTON_HEIGHT));
		
		sendMessagePanel.add(sendMessageField);
		sendMessagePanel.add(sendButton);
		
		
		sendButton.addActionListener((e) -> {
			sendMessageToPhone(sendMessageField.getText());
			sendMessageField.setText("");
		});
		
		clearButton.addActionListener((e)->{
			receiveMessageBox.setText(""); // clears text area
		});
		
		clearLogButton.addActionListener((e)->{
			log.setText(""); // clears text area
		});
		
		add(sendMessagePanel, BorderLayout.NORTH);
				
		ScrollableTextArea scrollableReceiveTextBox = new ScrollableTextArea(receiveMessageBox);
		
		tabbedPane.addTab("Server Status", serverStatus);
		tabbedPane.addTab("Client Messages", scrollableReceiveTextBox);
		tabbedPane.addTab("Message Builder", messageBuilderPanel);
		
		tabbedPane.addChangeListener(changeEvent -> {
			
			if(tabbedPane.getSelectedIndex() == tabbedPane.indexOfComponent(scrollableReceiveTextBox)) {
				add(clearButton, BorderLayout.SOUTH);
			} else {
				remove(clearButton);
			}
		});
		
		add(tabbedPane, BorderLayout.CENTER);		
				
		sendMessageField.addKeyListener(this);
		
	}
	
	private void addInputCharacter(char inputCharacter) {
		receiveMessageBox.append("" + inputCharacter);
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
		
		ScrollableTextArea logTextArea = new ScrollableTextArea(log);
		
		tabbedPane.addTab("Log", logTextArea);
		
		tabbedPane.addChangeListener(changeEvent -> {
			
			if(tabbedPane.getSelectedIndex() == tabbedPane.indexOfComponent(logTextArea)) {
				add(clearLogButton, BorderLayout.SOUTH);
			} else {
				remove(clearLogButton);
			}
		});
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
