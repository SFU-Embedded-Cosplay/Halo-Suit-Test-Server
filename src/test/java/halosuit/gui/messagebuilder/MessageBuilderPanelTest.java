package test.java.halosuit.gui.messagebuilder;

import static org.junit.Assert.*;
import main.java.halosuit.gui.MessageBuilder.MessageBuilderPanel;

import org.junit.Test;

public class MessageBuilderPanelTest {
	

	@Test
	public void testGetMessage() {
		String jsonFilePath = "src//test//resource//testJson1.json";
		
		MessageBuilderPanel panel = new MessageBuilderPanel(jsonFilePath, (message) -> {});
		
		assertEquals("{}", panel.getMessage());
			
		panel.setValue("lights", "off");
		assertEquals("{\"lights\":\"off\"}", panel.getMessage());

		panel.setValue("heart rate", "50700");
		assertEquals("{\"lights\":\"off\",\"heart rate\":50700}", panel.getMessage());
		
		panel.setValue("water temperature", "5.7265");
		assertEquals("{\"lights\":\"off\",\"water temperature\":5.7265,\"heart rate\":50700}", panel.getMessage());
		
		panel.setValue("voltage2", "hello world 531");
		assertEquals("{\"lights\":\"off\",\"water temperature\":5.7265,\"heart rate\":50700,\"voltage2\":\"hello world 531\"}", panel.getMessage());
	}

}
