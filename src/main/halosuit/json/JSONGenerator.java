package main.halosuit.json;

public class JSONGenerator {

	public String makeJSON(String key, String value) {
		String json = "{\"" + key + "\":\"" + value + "\"}";
		
		return json;
		
	}
	
}
