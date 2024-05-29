package net;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private ServerSocket serverSocket;
	
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	private void startServer() {
		
		try {
			while(!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(socket);
				Thread thread = new Thread(clientHandler);
				thread.start(); 
			}
				
		} catch (IOException e) {
			closeServerSocket();
		}
	}
	
	private void closeServerSocket() {
	
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		Server server = new Server(new ServerSocket(1234));
		server.startServer();
	}
}