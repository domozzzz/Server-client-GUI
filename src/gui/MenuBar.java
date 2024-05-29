package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener {
	
	JMenuItem exitItem;
	
	public MenuBar() {
		
		this.setBackground(Color.decode("#383838"));
		
		addMenus();
	}
	
	private void addMenus() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.setForeground(Color.decode("#FFFFFF"));
		
		this.add(fileMenu);
		
		exitItem = new JMenuItem("exit");
		exitItem.setBackground(Color.decode("#383838"));
		exitItem.setForeground(Color.decode("#FFFFFF"));
		exitItem.addActionListener(this);
		
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitItem) {
			System.exit(0);
		}
	}
}

