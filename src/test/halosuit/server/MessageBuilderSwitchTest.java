package test.halosuit.server;

import static org.junit.Assert.*;
import org.junit.Test;

import main.halosuit.gui.MessageBuilder.MessageBuilderSwitch;

public class MessageBuilderSwitchTest {

	@Test
	public void testGetKey() {
		String key = "Hello World";
		String[] options = {"one", "two", "three"};
		
		MessageBuilderSwitch messageBuilderSwitch = new MessageBuilderSwitch(key, options);
		
		assertEquals(key, messageBuilderSwitch.getKey());
	}

	@Test
	public void testGetSelectedValue() {
		String key = "Hello World";
		String[] options = {"one", "two", "three"};
		
		MessageBuilderSwitch messageBuilderSwitch = new MessageBuilderSwitch(key, options);
		
		assertEquals(null, messageBuilderSwitch.getSelectedValue());
	}

}
