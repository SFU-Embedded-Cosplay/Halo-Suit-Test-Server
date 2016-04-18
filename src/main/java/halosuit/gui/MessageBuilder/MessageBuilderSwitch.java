package main.java.halosuit.gui.MessageBuilder;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MessageBuilderSwitch extends JPanel implements MessageBuilderItem {
			
	public static final int KEY_LEFT_PADDING = 5;
	public static final int ELEMENT_PADDING = 5;
	
	private JLabel key = null;
	private ButtonGroup optionGroup = new ButtonGroup();
	
	public MessageBuilderSwitch(String key, String[] options) {
		this.key = new JLabel(key);
		
		SpringLayout layout = new SpringLayout();
		
		layout.putConstraint(SpringLayout.WEST, this.key, KEY_LEFT_PADDING, SpringLayout.WEST, this);
		
		add(this.key);
		
		Component lastComponet = this.key;
		
		for(String option : options) {
			JRadioButton optionRadioButton = new JRadioButton(option);
			optionRadioButton.setActionCommand(option);
			
			layout.putConstraint(SpringLayout.WEST, optionRadioButton, ELEMENT_PADDING, SpringLayout.EAST, lastComponet);
			lastComponet = optionRadioButton;
			
			optionGroup.add(optionRadioButton);
			add(optionRadioButton);
		}				

		setLayout(layout);

	}

	@Override
	public String getKey() {
		return key.getText();
	}

	@Override
	public JsonElement getValue() {	
		
		ButtonModel selectedRadioButton = optionGroup.getSelection();
		
		if(selectedRadioButton != null) {
			String selectedButtonLabel = selectedRadioButton.getActionCommand();
			return new Gson().toJsonTree(selectedButtonLabel);	
		} else {
			return null;
		}
	}
	
	@Override
	public void setValue(String value) {
		
		Enumeration<AbstractButton> radioButtons = optionGroup.getElements();
		
		while(radioButtons.hasMoreElements()) {
			JRadioButton radioButton = (JRadioButton) radioButtons.nextElement();
			
			if(value.equals(radioButton.getText())) {
				radioButton.setSelected(true);
				return;
			}
		}
	}
}
