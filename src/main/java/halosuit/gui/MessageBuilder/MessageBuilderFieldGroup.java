package main.java.halosuit.gui.MessageBuilder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MessageBuilderFieldGroup implements MessageBuilderItem {

	private JLabel key = null;
	
	private List<MessageBuilderField> fields = new ArrayList<MessageBuilderField>();
	
	public  MessageBuilderFieldGroup(String key) {
		this.key = new JLabel(key);
		this.key.setForeground(Color.RED);
	}
	
	public void addItem(MessageBuilderField field) {
		fields.add(field);
	}
	
	@Override
	public String getKey() {
		
		return key.getText();
	}

	@Override
	public JsonElement getValue() {
		JsonObject object = new JsonObject();
		
		for(MessageBuilderField field : fields) {
			String key = field.getKey();
			JsonElement value = field.getValue();
			
			if(value != null) {
				object.add(key, value);				
			}
		}
		
		// no fields are filled out so return null 
		// instead of empty object Ex. "{}"
		if(object.entrySet().size() == 0) {
			return null;
		}
		
		return object;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub
		
	}

	public int getNumberOfItems() {
		return fields.size();
	}
	
	public MessageBuilderField getItem(int index) {
		return fields.get(index);
	}
	
	public JLabel getKeyLabel() {
		return key;
	}	
}
