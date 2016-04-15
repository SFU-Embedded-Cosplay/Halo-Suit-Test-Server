package test.java.halosuit;

import static org.junit.Assert.assertEquals;
import main.java.halosuit.gui.MessageBuilder.MessageBuilderSwitch;

import org.junit.Test;

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
