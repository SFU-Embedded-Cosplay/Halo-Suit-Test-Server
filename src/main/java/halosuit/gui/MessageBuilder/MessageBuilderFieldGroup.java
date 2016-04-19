package main.java.halosuit.gui.MessageBuilder;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.gson.JsonElement;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement getValue() {
		// TODO Auto-generated method stub
		return null;
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
