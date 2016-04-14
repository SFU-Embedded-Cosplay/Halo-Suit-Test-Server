package main.halosuit.gui;

import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;import com.google.gson.reflect.TypeToken;


public class JSONMessageBuilderPanel extends JPanel {
	
	JsonObject json = new JsonObject();
	
	JButton addButton = new JButton("Add");
	
	public JSONMessageBuilderPanel() {	
		setLayout(new GridLayout(10, 3, 10, 10));
		json = getJsonObjectFromFile("res//PossibleJson.json");
		
		json.entrySet().forEach( entry -> {
		
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
			if(entry.getValue().isJsonArray()) {
				// extract string array from json array
				String[] options = new Gson().fromJson(entry.getValue(), String[].class); 
				
				add(new MessageBuilderSwitch(entry.getKey(), options));
			}
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
