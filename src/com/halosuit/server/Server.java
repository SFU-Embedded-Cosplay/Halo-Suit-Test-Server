package com.halosuit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import com.halosuit.json.JSONGenerator;


public class Server {
	private int port;
	
	private ServerSocket serverSocket;
	private PrintWriter output;
	private BufferedReader input;
	
	private Socket androidDevice;
	
	private JSONGenerator jsonGenerator = new JSONGenerator();
	
	private List<InputListener> inputListeners = new LinkedList<InputListener>();
	
	public static final int MESSAGE_BYTE_LENGTH = 1024;
	
	private boolean isConnecting = false;
	
	public Server(int port) throws IOException{
		this.port = port;
		
		serverSocket = new ServerSocket(port);

		
		System.out.println("reading data:");
		
//		while(true) {
//			char character = (char) input.read();
//			System.out.print(character);
//			
//			if(character == '}') {
//				System.out.println();
//				String message = jsonGenerator.makeJSON("flow rate", "50");
//				
//				System.out.println("sending message: " + message);
//				
//				byte[] messageBytes = message.getBytes();
//				
//				byte[] buffer = new byte[1024];
//				
//				for(int i = 0; i < messageBytes.length; i++) {
//					buffer[i] = messageBytes[i];
//				}
//				
//				sendMessage(buffer);
//				
//			}
//		}
		
	}
	
	
	// TODO: confirm my 1024 character assumption
	/*
	 * @param message - must not be longer than 1024 characters (I think)
	 * */
	public void sendMessage(String message) throws IOException {		
		System.out.println("sending message: " + message);
		
		byte[] messageBytes = message.getBytes();
		
		byte[] buffer = new byte[MESSAGE_BYTE_LENGTH];
		
		for(int i = 0; i < messageBytes.length; i++) {
			buffer[i] = messageBytes[i];
		}
		
		assert(buffer.length <= MESSAGE_BYTE_LENGTH);
		
		androidDevice.getOutputStream().write(buffer);

	}
	
	public char readCharacter() throws IOException {
		char character = (char) input.read();
		return character;
	}
	
	public void listenForConnection() throws IOException {
		if(androidDevice != null && androidDevice.isConnected()) {
			System.out.println("aborting additional attempt to listen for android device since since server is already connected");
			isConnecting = false;
			return;
		}
		
		if(isConnecting) {
			System.out.println("aborting additional attempt to listen for android device since server is already waiting for connection");
			return;
		}
		
		isConnecting = true;
		
		System.out.println("waiting for user to connect on port: " + port);
		
		androidDevice = serverSocket.accept();
		
		System.out.println("androidDevice has connected");
		
		output = new PrintWriter(androidDevice.getOutputStream());
		input = new BufferedReader(new InputStreamReader(androidDevice.getInputStream()));
		
		new Thread(()->listenForMessage()).start();
	}
	
	public void addInputListener(InputListener listener) {
		inputListeners.add(listener);
	} 
	
	private void listenForMessage() {
		
		char inputCharacter;
		while(androidDevice.isConnected()) {
			try {
				inputCharacter = (char) input.read();
				
				for(InputListener listener : inputListeners) {
					listener.update(inputCharacter);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
