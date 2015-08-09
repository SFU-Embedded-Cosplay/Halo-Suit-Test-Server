package com.halosuit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.halosuit.json.JSONGenerator;


public class Server {
	private int port;
	
	private ServerSocket serverSocket;
	private PrintWriter output;
	private BufferedReader input;
	
	private Socket androidDevice;
	
	private JSONGenerator jsonGenerator = new JSONGenerator();
	
	public static final int MESSAGE_BYTE_LENGTH = 1024;
	
	public Server(int port) throws IOException{
		this.port = port;
		
		serverSocket = new ServerSocket(port);
		System.out.println("waiting for user to connect on port: " + port);
		
		androidDevice = serverSocket.accept();
		
		System.out.println("androidDevice has connected");
		
		output = new PrintWriter(androidDevice.getOutputStream());
		input = new BufferedReader(new InputStreamReader(androidDevice.getInputStream()));
		
		System.out.println("reading data:");
		
		while(true) {
			char character = (char) input.read();
			System.out.print(character);
			
			if(character == '}') {
				System.out.println();
				String message = jsonGenerator.makeJSON("flow rate", "50");
				
				System.out.println("sending message: " + message);
				
				byte[] messageBytes = message.getBytes();
				
				byte[] buffer = new byte[1024];
				
				for(int i = 0; i < messageBytes.length; i++) {
					buffer[i] = messageBytes[i];
				}
				
				sendMessage(buffer);
				
			}
		}
		
	}
	
	
	/*
	 * @param buffer - array must be the same length as MESSAGE_BYTE_LENGTH
	 * */
	public void sendMessage(byte[] buffer) throws IOException {
		assert(buffer.length == MESSAGE_BYTE_LENGTH);
		
		androidDevice.getOutputStream().write(buffer);

	}
	
}
