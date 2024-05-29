package net;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import gui.Frame;

public class ClientHandler implements Runnable {
	
	public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String clientUsername;
	
	public ClientHandler(Socket socket) {
		
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.clientUsername = bufferedReader.readLine();
			clientHandlers.add(this);
			
			broadcastMessage("SERVER: " + clientUsername + " has joined");
			
		} catch (IOException e) {
			close(socket, bufferedReader, bufferedWriter);
		}
		
		
	}

	@Override
	public void run() {
		
		String messageFromClient;
		
		while(socket.isConnected()) {
			
			try {
				messageFromClient = bufferedReader.readLine();
				broadcastMessage(messageFromClient);
			} catch (IOException e) {
				close(socket, bufferedReader, bufferedWriter);
				break;
			}
		}
	}
	
	public void broadcastMessage(String messageToSend) {
		for (ClientHandler clientHandler : clientHandlers) {
			try {
				clientHandler.bufferedWriter.write(messageToSend);
				clientHandler.bufferedWriter.newLine();
				clientHandler.bufferedWriter.flush();
				
			} catch (IOException e) {
				close(socket, bufferedReader, bufferedWriter);
			}
		}
	}
	
	public void removeClientHandler() {
		clientHandlers.remove(this);
		broadcastMessage("SERVER: " + clientUsername + " has left");
	}
	
	private void close(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		try {
			if (socket != null) {
				socket.close();
			}
			
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			
			removeClientHandler();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
