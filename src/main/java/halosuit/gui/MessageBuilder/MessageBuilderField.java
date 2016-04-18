package main.java.halosuit.gui.MessageBuilder;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MessageBuilderField extends JPanel implements MessageBuilderItem {
	
	private JLabel key = null;
	private JTextField textField = new JTextField();
	
	public MessageBuilderField(String key) {
		this.key = new JLabel(key);
		
		SpringLayout layout = new SpringLayout();
		
		add(this.key, BorderLayout.WEST);
		add(textField, BorderLayout.CENTER);
		
		// add padding to left of key
		layout.putConstraint(SpringLayout.WEST, this.key, KEY_LEFT_PADDING, SpringLayout.WEST, this);
		// add padding between key and textfield
		layout.putConstraint(SpringLayout.WEST, textField, ELEMENT_PADDING, SpringLayout.EAST, this.key);
		// stretch textField to end of panel
		layout.putConstraint(SpringLayout.EAST, textField, ELEMENT_PADDING, SpringLayout.EAST, this);
		
				
		setLayout(layout);
	}
	
	public void setFieldValue(String text) {
		textField.setText(text);
	}

	@Override
	public String getKey() {
		return key.getText();
	}

	@Override
	public JsonElement getValue() {	
		
		String message = textField.getText();		
		
		// check if value is int and return it.
		try{
			int intValue = Integer.parseInt(message);
			return new Gson().toJsonTree(intValue);
		} catch(NumberFormatException e) { } // do nothing, this should happen if the value is not an int 

		
		// check if value is float and return it.
		try{
			float floatValue = Float.parseFloat(message);
			return new Gson().toJsonTree(floatValue);
		} catch(NumberFormatException e) { } // do nothing, this should happen if the value is not a float
		
		
		// if value is a string then return it.
		if(message.equals("")) {
			return null;
		} else {
			return new Gson().toJsonTree(message);
		}
	}

	@Override
	public void setValue(String value) {
		textField.setText(value);
	}

}
