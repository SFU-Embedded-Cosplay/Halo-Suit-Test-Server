package main.java.halosuit.gui.MessageBuilder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


public class MessageBuilderPanel extends JPanel {
	
	private JsonObject json = new JsonObject();
	private JButton addButton = new JButton("Add");
	
	public static final String DEFAULT_JSON_TEMPLATE_FILE_LOCATION = "src//main//resources//PossibleJson.json";
	
	private JPanel messageBuilderPanel = new JPanel();
	private List<MessageBuilderItem> messageBuilderItems = new ArrayList<MessageBuilderItem>();
	private List<MessageBuilderItem> messageBuilderItemGroups = new ArrayList<MessageBuilderItem>();
	
	Consumer<String> addBuildMessageCallback = null;
	
	public MessageBuilderPanel(String jsonTemplateFileLocation, Consumer<String> addBuildMessageCallback) {	
		this.addBuildMessageCallback = addBuildMessageCallback;
		
			
		setLayout(new BorderLayout());
	
		GroupLayout layout = new GroupLayout(messageBuilderPanel);
		messageBuilderPanel.setLayout(layout);
		
		
		add(addButton, BorderLayout.NORTH);
		add(messageBuilderPanel, BorderLayout.CENTER);
		
		json = getJsonObjectFromFile(jsonTemplateFileLocation);
		
		json.entrySet().forEach( entry -> {
			
			if(entry.getValue().isJsonArray()) {
				// extract string array from json array
				String[] options = new Gson().fromJson(entry.getValue(), String[].class); 
				
				MessageBuilderSwitch item = new MessageBuilderSwitch(entry.getKey(), options);
				
				messageBuilderItems.add(item);
			} else if(entry.getValue().isJsonPrimitive()) {
				MessageBuilderField field = new MessageBuilderField(entry.getKey());
				
				messageBuilderItems.add(field);
			} else if(entry.getValue().isJsonObject()) {	
				MessageBuilderFieldGroup fieldGroup = new MessageBuilderFieldGroup(entry.getKey());
				
				entry.getValue().getAsJsonObject().entrySet().forEach( item -> {
					MessageBuilderField field = new MessageBuilderField(item.getKey());
					fieldGroup.addItem(field);
					
					
//					messageBuilderItems.add(field);
				});
				
				messageBuilderItemGroups.add(fieldGroup);
			}
		});

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		SequentialGroup horizontalGroup = layout.createSequentialGroup();
		SequentialGroup verticalGroup = layout.createSequentialGroup();
				
		ParallelGroup leftHorizontalGroup = layout.createParallelGroup();
		ParallelGroup rightHorizontalGroup = layout.createParallelGroup();
		ParallelGroup verticalLayerGrouping = null;

		
		for(int i = 0; i < messageBuilderItems.size(); i++) {
			if(i % 2 == 0) { // left item  (value is even)
				
				Component item = (Component) messageBuilderItems.get(i);
				
				verticalLayerGrouping = layout.createParallelGroup();
				verticalLayerGrouping.addComponent(item);
				
				
				leftHorizontalGroup.addComponent(item);

			} else { // right item (value is odd)
				Component item = (Component) messageBuilderItems.get(i);
				
				verticalLayerGrouping.addComponent(item);
				verticalGroup.addGroup(verticalLayerGrouping);
				
				rightHorizontalGroup.addComponent(item);
			}
		}
		
		// ensures last virticalGroup is added to layout properly
		if(messageBuilderItems.size() % 2 == 1) {
			verticalGroup.addGroup(verticalLayerGrouping);
		}
		
		for(int i = 0; i < messageBuilderItemGroups.size(); i++) {
			MessageBuilderFieldGroup itemGroup = (MessageBuilderFieldGroup) messageBuilderItemGroups.get(i);
			
			verticalLayerGrouping = layout.createParallelGroup();
			verticalLayerGrouping.addComponent(itemGroup.getKeyLabel());
			verticalGroup.addGroup(verticalLayerGrouping);
			
			
			leftHorizontalGroup.addComponent(itemGroup.getKeyLabel());
			
			for(int j = 0; j < itemGroup.getNumberOfItems(); j++) {
				if(j % 2 == 0) { // left item  (value is even)
					
					Component item = (Component) itemGroup.getItem(j);
					
					verticalLayerGrouping = layout.createParallelGroup();
					verticalLayerGrouping.addComponent(item);
					
					
					leftHorizontalGroup.addComponent(item);
	
				} else { // right item (value is odd)
					Component item = (Component) itemGroup.getItem(j);
					
					verticalLayerGrouping.addComponent(item);
					verticalGroup.addGroup(verticalLayerGrouping);
					
					rightHorizontalGroup.addComponent(item);
				}
			}
			
			if(itemGroup.getNumberOfItems() % 2 == 1) {
				verticalGroup.addGroup(verticalLayerGrouping);
			}
		}
				
		horizontalGroup.addGroup(leftHorizontalGroup);
		horizontalGroup.addGroup(rightHorizontalGroup);
		
		layout.setHorizontalGroup(horizontalGroup);
		layout.setVerticalGroup(verticalGroup);
		
		
		addButton.addActionListener(e -> {
			addBuildMessageCallback.accept(getMessage());
		});
	}
	
	public String getMessage() {
		JsonObject object = new JsonObject();			
		
		for(MessageBuilderItem item : messageBuilderItems) {
			String key = item.getKey();
			JsonElement value = item.getValue();
			
			if(value != null) {
				object.add(key, value);
			}				
		}
		
		for(MessageBuilderItem item : messageBuilderItemGroups) {
			String key = item.getKey();
			JsonElement value = item.getValue();
			
			if(value != null) {
				object.add(key, value);
			}	
		}
		
		return object.toString();
	}
	
	public void setValue(String key, String value) {
		
		for(MessageBuilderItem item : messageBuilderItems) {
			if(key.equals(item.getKey())) {
				
				item.setValue(value);
				return;
				
			}
		}
	}
	
	private JsonObject getJsonObjectFromFile(String fileName) {
		JsonParser parser = new JsonParser();
		JsonElement jsonElement = null;
		
		try {
			jsonElement = parser.parse(new FileReader(fileName));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace(); // TODO: handle this properly
		}
		
		return jsonElement.getAsJsonObject();
	}
}
