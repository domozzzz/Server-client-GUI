package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.Client;

public class Frame extends JFrame {
	
	private final int SCREEN_WIDTH = 800;
	private final int SCREEN_HEIGHT = 600;
	private Client client;
	private static ArrayList<String> users = new ArrayList<>();
	
	private MessagePanel messagePanel;
	private UserPanel userPanel;
	
	public Frame(Client client) {
	
		this.client = client;
		this.setTitle("gimmy");
		this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.getContentPane().setForeground(Color.decode("#FF00FF"));
		this.getContentPane().setBackground(Color.decode("#FFFF00"));
		this.setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		messagePanel = new MessagePanel(this);
		userPanel = new UserPanel(this);
		this.add(messagePanel);
		this.add(userPanel);
		this.setJMenuBar(new MenuBar());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public MessagePanel getMessagePanel() {
		return messagePanel;
	}
	
	public UserPanel getUserPanel() {
		return userPanel;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
