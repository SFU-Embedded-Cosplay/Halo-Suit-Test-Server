package main.java.halosuit.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ExpandableTab extends JPanel {
	
	
	private JButton expandButton = new JButton(new ImageIcon("src\\main\\resources\\images\\plus symbol.png"));
	private JLabel title = null;
	
	public ExpandableTab(String title, JTabbedPane tabbedPane) {
		setLayout(new GridBagLayout());
		setOpaque(false);
		
		this.title = new JLabel(title);
				
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		
		add(this.title, constraints);
		
		constraints.gridx++;
		constraints.ipadx = -20;
		constraints.weightx = 0;
		
		add(expandButton, constraints);
		expandButton.setBackground(Color.WHITE);
		
		expandButton.addActionListener((e) -> {
			
		});
	}

}
