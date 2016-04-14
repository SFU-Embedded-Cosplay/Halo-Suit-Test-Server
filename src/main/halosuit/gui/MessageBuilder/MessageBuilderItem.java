package main.halosuit.gui.MessageBuilder;

import com.google.gson.JsonElement;

public interface MessageBuilderItem {
	
	public String getKey();
	public JsonElement getSelectedValue();
	
}
