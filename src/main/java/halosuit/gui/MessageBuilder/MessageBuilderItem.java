package main.java.halosuit.gui.MessageBuilder;

import com.google.gson.JsonElement;

public interface MessageBuilderItem {
	
	int KEY_LEFT_PADDING = 5;
	int ELEMENT_PADDING = 5;
	
	public String getKey();
	public JsonElement getValue();
	
	public void setValue(String value);
}
