package main.java.halosuit.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScrollableTextArea extends JPanel {
	
	JTextArea textArea = null;
	
	public ScrollableTextArea(JTextArea textArea) {
		setLayout(new BorderLayout());
		this.textArea = textArea;
		
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		add(scroll);

	}
	
	public JTextArea getTextArea() {
		return textArea;
		
	}
}
