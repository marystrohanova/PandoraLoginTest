package pandoraTest;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class DataProviderTest {

	@Test
	public void getValue() {

		String locatorName = "birth_year_input1";
		String expected = "input#register_birthYear";

		String actual = DataProvider.getLocator(locatorName);
		System.out.println(locatorName + ": " + actual);
		AssertJUnit.assertEquals("Locator doesn't  exist!", actual, expected);
	}
	

}
