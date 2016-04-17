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
	}

}
