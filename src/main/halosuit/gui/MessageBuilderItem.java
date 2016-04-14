package main.halosuit.gui;

import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MessageBuilderItem extends JPanel{
			
	public MessageBuilderItem(String key, String[] options) {
		add(new JLabel(key));
		
		ButtonGroup optionGroup = new ButtonGroup();
		for(String option : options) {
			JRadioButton optionRadioButton = new JRadioButton(option);
			
			optionGroup.add(optionRadioButton);
			
			add(optionRadioButton);
		}				
	}
}
