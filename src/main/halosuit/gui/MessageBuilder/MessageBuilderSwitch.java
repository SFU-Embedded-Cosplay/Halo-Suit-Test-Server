package main.halosuit.gui.MessageBuilder;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MessageBuilderSwitch extends JPanel implements MessageBuilderItem {
			
	JLabel key = null;
	ButtonGroup optionGroup = new ButtonGroup();
	
	public MessageBuilderSwitch(String key, String[] options) {
		this.key = new JLabel(key);
		
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
	public JsonElement getSelectedValue() {	
		
		ButtonModel selectedRadioButton = optionGroup.getSelection();
		
		if(selectedRadioButton != null) {
			String selectedButtonLabel = selectedRadioButton.getActionCommand();
			return new Gson().toJsonTree(selectedButtonLabel);	
		} else {
			return null;
		}
	}
}
