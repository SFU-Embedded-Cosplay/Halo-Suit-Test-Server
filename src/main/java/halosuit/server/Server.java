package main.java.halosuit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.function.Consumer;

import main.java.halosuit.utils.Logger;


public class Server extends Observable {
	private int port;

	private ServerSocket serverSocket;
	private PrintWriter output;
	private BufferedReader input;

	private Socket client;

	private List<Consumer<Character>> inputListeners = new LinkedList<Consumer<Character>>();

	public static final int MESSAGE_BYTE_LENGTH = 1024;

	private boolean isConnecting = false;

	private Logger log = new Logger() {

		@Override
		public void println(String message) {
			// TODO Auto-generated method stub
			System.out.println(message);
		}

		@Override
		public void print(String message) {
			System.out.print(message);
		}};

	public Server(int port) throws IOException{
		this.port = port;

		serverSocket = new ServerSocket(port);


		log.println("reading data:");
	}


	// TODO: confirm my 1024 character assumption
	/*
	 * @param message - must not be longer than 1024 characters (I think)
	 * */
	public void sendMessage(String message) throws IOException {
		log.println("sending message: " + message);

		byte[] messageBytes = message.getBytes();

		byte[] buffer = new byte[MESSAGE_BYTE_LENGTH];

		for(int i = 0; i < messageBytes.length; i++) {
			buffer[i] = messageBytes[i];
		}

		assert(buffer.length <= MESSAGE_BYTE_LENGTH);

		client.getOutputStream().write(buffer);

	}

	public char readCharacter() throws IOException {
		char character = (char) input.read();
		return character;
	}

	public void listenForConnection() throws IOException {
		if(isConnected()) {
			log.println("aborting additional attempt to listen for android device since since server is already connected");
			setIsConnecting(false);
			return;
		}

		if(isConnecting) {
			log.println("aborting additional attempt to listen for android device since server is already waiting for connection");
			return;
		}

		setIsConnecting(true);

		log.println("waiting for user to connect on port: " + port);

		client = serverSocket.accept();

		log.println("client has connected");

		setIsConnecting(false);

		output = new PrintWriter(client.getOutputStream());
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));

		new Thread(()->listenForMessage()).start();
	}

	public void addInputListener(Consumer<Character> listener) {
		inputListeners.add(listener);
	}

	public boolean isConnected() {
		return client != null && !client.isClosed();
	}

	public boolean isConnecting() {
		return isConnecting;
	}

	private void listenForMessage() {
		char inputCharacter;
		while(isConnected()) {
			try {
				inputCharacter = (char) input.read();
				log.print(Character.toString(inputCharacter));

				for(Consumer<Character> listener : inputListeners) {
					if(inputCharacter == '{') {
						listener.accept('\n');
					}

					listener.accept(inputCharacter);

					if(inputCharacter == '}') {
						listener.accept('\n');
					}
				}
			} catch (SocketException e) { // pipe is broken and client disconnected
				disconnectClient();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void disconnectClient() {
		try {
			client.close();

			client = null;
			input = null;
			output = null;

			setIsConnecting(false);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}


	public void setLog(Logger log) {
		this.log  = log;
	}

	private void setIsConnecting(boolean isConnecting) {
		this.isConnecting = isConnecting;

		setChanged();
		notifyObservers();
	}

}
