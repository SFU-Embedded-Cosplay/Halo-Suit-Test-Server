package main.java.halosuit.gui.MessageBuilder;

import com.google.gson.JsonElement;

public interface MessageBuilderItem {
	
	public String getKey();
	public JsonElement getValue();
	
	public void setValue(String value);
}
