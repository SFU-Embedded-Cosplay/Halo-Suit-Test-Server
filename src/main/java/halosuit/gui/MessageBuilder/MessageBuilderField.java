package main.java.halosuit.gui.MessageBuilder;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MessageBuilderField extends JPanel implements MessageBuilderItem {
	private JLabel key = null;
	private JTextField textField = new JTextField();
	
	public MessageBuilderField(String key) {
		this.key = new JLabel(key);
		
		setLayout(new BorderLayout());
		
		add(this.key, BorderLayout.WEST);
		
		add(textField, BorderLayout.CENTER);			
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
		textField.setText("");
		
		
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

}
