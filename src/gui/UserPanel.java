package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class UserPanel extends JPanel {

	private Frame frame;
	private JTextArea userBox;
	
	public UserPanel(Frame frame) {
		this.frame = frame;
		this.setBackground(Color.decode("#383838"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		userBox = new JTextArea();
		userBox.setForeground(Color.decode("#FFFFFF"));
		userBox.setBorder(new LineBorder(Color.decode("#181818"), 4, true));
		userBox.setBackground(Color.decode("#181818"));
		userBox.setEditable(false);
		
		this.add(getUserBox());
	}

	public JTextArea getUserBox() {
		return userBox;
	}

	public void setUserBox(JTextArea userBox) {
		this.userBox = userBox;
	}
	
	public void appendUsername(String username) {
		userBox.append(username);
	}
}