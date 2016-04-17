package main.java.halosuit.gui.MessageBuilder;

import java.awt.GridLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MessageBuilderSwitch extends JPanel implements MessageBuilderItem {
			
	private JLabel key = null;
	private ButtonGroup optionGroup = new ButtonGroup();
	
	public MessageBuilderSwitch(String key, String[] options) {
		this.key = new JLabel(key);
		
		setLayout(new GridLayout());
		
		add(this.key);
		
		for(String option : options) {
			JRadioButton optionRadioButton = new JRadioButton(option);
			optionRadioButton.setActionCommand(option);
			
			optionGroup.add(optionRadioButton);
			add(optionRadioButton);
		}				
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
