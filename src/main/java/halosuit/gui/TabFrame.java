package main.java.halosuit.gui;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TabFrame extends JFrame {
	
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	
	int index;
	
	public TabFrame(String title, JTabbedPane tabbedPane) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(title);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(true);
		
		index = tabbedPane.indexOfTab(title);
		
		// This method removes the tab from the tab panel, along with returning
		// the tabs content.
		Component tabContent = tabbedPane.getComponentAt(index);
		add(tabContent);
				
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	tabbedPane.insertTab(title, null, tabContent, "", index);
            	ExpandableTab.addExpandableTab(title, tabbedPane);
            }
        });
		
	}

	
}
