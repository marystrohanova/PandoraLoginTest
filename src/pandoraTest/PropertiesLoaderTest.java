package pandoraTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class PropertiesLoaderTest {

	@Test
	public void GetBaseUrl() {
		String key = "base_url";
		String expected_value = "http://www.pandora.com/";
		String value = PropertiesLoader.getValue(key);
		AssertJUnit.assertEquals("Strings don't match", expected_value, value);	
	}
	
	@Test
	public void GetIntValue() {
		String key = "implicit_wait_time";
		int expected_value = 3;
		int value = PropertiesLoader.getIntValue(key);
		AssertJUnit.assertEquals("Numbers don't match", expected_value, value);
	}
}
