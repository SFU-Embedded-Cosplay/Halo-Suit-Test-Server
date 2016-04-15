package main.halosuit.gui.MessageBuilder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class JSONMessageBuilderPanel extends JPanel {
	
	private JsonObject json = new JsonObject();
	private JButton addButton = new JButton("Add");
	
	
	private JPanel messageBuilderPanel = new JPanel();
	private List<MessageBuilderItem> messageBuilderItems = new ArrayList<MessageBuilderItem>();
	
	Consumer<String> addBuildMessageCallback = null;
	
	public JSONMessageBuilderPanel(Consumer<String> addBuildMessageCallback) {	
		this.addBuildMessageCallback = addBuildMessageCallback;
		
		
		setLayout(new BorderLayout());
		messageBuilderPanel.setLayout(new GridLayout(10, 3, 10, 5));
		
		add(addButton, BorderLayout.NORTH);
		add(messageBuilderPanel, BorderLayout.CENTER);

		
		json = getJsonObjectFromFile("res//PossibleJson.json");
		
		json.entrySet().forEach( entry -> {
		
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			
			if(entry.getValue().isJsonArray()) {
				// extract string array from json array
				String[] options = new Gson().fromJson(entry.getValue(), String[].class); 
				
				MessageBuilderSwitch item = new MessageBuilderSwitch(entry.getKey(), options);
				
				messageBuilderPanel.add(item);
				messageBuilderItems.add(item);
			} else if(entry.getValue().isJsonPrimitive()) {
				MessageBuilderField field = new MessageBuilderField(entry.getKey());
				
				
				
				messageBuilderPanel.add(field);
				messageBuilderItems.add(field);
			}
		});
		
		
		addButton.addActionListener(e -> {
			JsonObject object = new JsonObject();			
			
			for(MessageBuilderItem item : messageBuilderItems) {
				String key = item.getKey();
				JsonElement value = item.getSelectedValue();
				
				if(value != null) {
					object.add(key, value);

				}				
			}
			
			
			addBuildMessageCallback.accept(object.toString());
		});
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
