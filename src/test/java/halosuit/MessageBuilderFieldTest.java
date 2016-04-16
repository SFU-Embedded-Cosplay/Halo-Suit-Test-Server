package test.java.halosuit;

import static org.junit.Assert.*;
import main.java.halosuit.gui.MessageBuilder.MessageBuilderField;

import org.junit.Test;

public class MessageBuilderFieldTest {

	@Test
	public void testGetKey() {
		MessageBuilderField field = new MessageBuilderField("myKey");
		
		assertEquals("myKey", field.getKey());
	}
	
	@Test
	public void testGetSelectedValue() {
		MessageBuilderField field = new MessageBuilderField("key");
		
		// test int value
		field.setFieldValue("-5");
		assertEquals(-5, field.getValue().getAsInt());
		
		// test float value
		field.setFieldValue("10.3");
		assertEquals(10.3, field.getValue().getAsFloat(), 0.01);
		
		// test string value
		field.setFieldValue("hello world 5");
		assertEquals("hello world 5", field.getValue().getAsString());

		
	}

}
