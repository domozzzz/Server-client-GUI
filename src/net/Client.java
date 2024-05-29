package net;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gui.Frame;

public class Client {
	
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	private Frame frame;
	
	public static ArrayList<String> usernames = new ArrayList<>();
	
	public Client(Socket socket, String username) {
		try {
			this.socket = socket;
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.username = username;
			this.frame = new Frame(this);
			
			usernames.add(username);
			
			for (String user : usernames) {
				frame.getUserPanel().appendUsername(user);
			}
			
			bufferedWriter.write(username);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
		} catch (IOException e) {
			close(socket, bufferedReader, bufferedWriter);
		}
	}
	
	public void sendMessage(String message) {
		try {
			Date sentTime = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			bufferedWriter.write(sdf.format(sentTime) + " | " + username + ": " + message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
		} catch(IOException e) {
			close(socket, bufferedReader, bufferedWriter);
		}
	}
	
	public void listenForMessage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				while (socket.isConnected()) {
					try {
						String message = bufferedReader.readLine();
						frame.getMessagePanel().getMessageBox().append(message + System.lineSeparator());
						frame.getMessagePanel().getMessageBox().revalidate();
						frame.getMessagePanel().getScrollPane().revalidate();
					} catch (IOException e) {
						close(socket, bufferedReader, bufferedWriter);
					}
				}
			}
		}).start();
	}
	public void close(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		String username = "bob";
		Client client = null;
		try {
			client = new Client(new Socket("localhost", 1234), username);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.listenForMessage();
	}
}