package pandoraTest;

import java.util.Properties;
import java.io.InputStream;


public class PropertiesLoader {
	private static final String CONFIG_FILE = "/resources/config.properties";
	private static Properties prop = new Properties();
	
	private static void openFile(String file){
		InputStream in_stream = Properties.class.getResourceAsStream(CONFIG_FILE);
		
		try{
			prop.load(in_stream);
		} catch (Exception e){
			System.out.println("Error opening file " + CONFIG_FILE);
			throw new RuntimeException(e);
		}
	}
	
	public static String getValue(String key){
		openFile(CONFIG_FILE);
		String value = null;
		
		value = prop.getProperty(key);
		System.out.println(">>> PropertiesLoader.getValue('" + key + "') " + "- " + value);
		return value;
	}
	
	public static int getIntValue(String key){
		openFile(CONFIG_FILE);
		String value = null;
		int result = 0;
		
		value = prop.getProperty(key);
		System.out.println(">>> PropertiesLoader.getValue('" + key + "') " + "- " + value);
		
		try {
		    result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			String msg = "Enable to rarse to int value of " + key;
			System.out.println(msg);
			throw new RuntimeException(msg);
		}
		
		return result;
	}
	
	
	

}
