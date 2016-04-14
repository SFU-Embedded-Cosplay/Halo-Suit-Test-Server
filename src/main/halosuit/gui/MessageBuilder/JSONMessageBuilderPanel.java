package main.halosuit.gui.MessageBuilder;

import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class JSONMessageBuilderPanel extends JPanel {
	
	JsonObject json = new JsonObject();
	
	JButton addButton = new JButton("Add");
	
	private List<MessageBuilderItem> messageBuilderItems = new ArrayList<MessageBuilderItem>();
	
	public JSONMessageBuilderPanel() {	
		setLayout(new GridLayout(10, 3));
		json = getJsonObjectFromFile("res//PossibleJson.json");
		
		json.entrySet().forEach( entry -> {
		
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
			if(entry.getValue().isJsonArray()) {
				// extract string array from json array
				String[] options = new Gson().fromJson(entry.getValue(), String[].class); 
				
				MessageBuilderSwitch item = new MessageBuilderSwitch(entry.getKey(), options);
				
				add(item);
				messageBuilderItems.add(item);
			}
		});
		
		
		addButton.addActionListener(e -> {
			JsonObject object = new JsonObject();			
			
			for(MessageBuilderItem item : messageBuilderItems) {
				String key = item.getKey();
				JsonElement value = item.getSelectedValue();
				
				if(value != null) {
					object.addProperty(key, value.getAsString());	
				}				
			}
			
			
			System.out.println(object);
		});
		
		
		add(addButton);
	}
	
	private JsonObject getJsonObjectFromFile(String fileName) {
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = null;
		
		try {
			jsonElement = parser.parse(new FileReader("res//PossibleJson.json"));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace(); // TODO: handle this properly
		}
		
		return jsonElement.getAsJsonObject();
	}
}
