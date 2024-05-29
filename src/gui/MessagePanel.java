package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MessagePanel extends JPanel implements ActionListener {
	
	private Frame frame;
	JButton sendButton;
	private JScrollPane scrollPane;
	private JTextField textBox;
	private JTextArea messageBox;
	
	public MessagePanel(Frame frame) {
		this.frame = frame;
		this.setBackground(Color.decode("#383838"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		messageBox = new JTextArea();
		messageBox.setForeground(Color.decode("#FFFFFF"));
		messageBox.setBorder(new LineBorder(Color.decode("#181818"), 4, true));
		messageBox.setBackground(Color.decode("#181818"));
		messageBox.setPreferredSize(new Dimension(250, 400));
		messageBox.setEditable(false);
		
		scrollPane = new JScrollPane(messageBox);
		scrollPane.setForeground(Color.decode("#FFFFFF"));
		scrollPane.setBorder(new LineBorder(Color.decode("#181818"), 4, true));
		scrollPane.setBackground(Color.decode("#181818"));
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10));
		scrollPane.getVerticalScrollBar().setBorder(new LineBorder(Color.decode("#181818")));
		scrollPane.getVerticalScrollBar().setBackground(Color.decode("#181818"));
		scrollPane.getVerticalScrollBar().setForeground(Color.decode("#FFFFFF"));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		textBox = new JTextField();
		textBox.setForeground(Color.decode("#FFFFFF"));
		textBox.setBorder(new LineBorder(Color.decode("#FFFFFF"), 1, true));
		textBox.setBackground(Color.decode("#181818"));
		textBox.setCaretColor(Color.decode("#FFFFFF"));
		textBox.setSize(new Dimension(10, 10));
		
		sendButton = new JButton("send");
		sendButton.setForeground(Color.decode("#FFFFFF"));
		sendButton.setBorder(new LineBorder(Color.decode("#181818"), 4, true));
		sendButton.setBackground(Color.decode("#181818"));
		sendButton.addActionListener(this);
		
		this.add(scrollPane);
		this.add(textBox);
		this.add(sendButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendButton) {
			this.frame.getClient().sendMessage(textBox.getText());
		}
	}

	public JTextField getTextBox() {
		return textBox;
	}

	public void setTextBox(JTextField textBox) {
		this.textBox = textBox;
	}

	public JTextArea getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(JTextArea messageBox) {
		this.messageBox = messageBox;
	}
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}