package pandoraTest;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PandoraTest {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup(){
		String browser = PropertiesLoader.getValue("browser");
		String err_msg = null;

		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		default:
			err_msg = "Unknow browser requested " + browser;
			System.err.println(err_msg);
			break;
		}
		
		if (driver == null){
			throw new RuntimeException(err_msg);
		}
		
		System.out.println(">>> Driver has been succesfully started: '" + browser +"'");
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
		System.out.println(">>> Driver has been succesfully closed!");
	}

	@Test
	public void RegisterTest() {
		
		String base_url 	= PropertiesLoader.getValue("base_url");
		int exp_wait 		= PropertiesLoader.getIntValue("explicit_wait_time");
		WebDriverWait wait 	= new WebDriverWait(driver, exp_wait);
		String userEmail	= PropertiesLoader.getValue("user_email");

		driver.get(base_url);
		WebElement registerLink = driver.findElement(By.cssSelector(DataProvider.getLocator("register_link")));
		registerLink.click();
		
		WebElement onBordForm = driver.findElement(By.cssSelector(DataProvider.getLocator("on_bord_form")));
		wait.until(ExpectedConditions.visibilityOf(onBordForm));
		
		WebElement emailInput = driver.findElement(By.cssSelector(DataProvider.getLocator("email_input")));
		emailInput.click();
		emailInput.sendKeys(userEmail);
		
		WebElement passwordInput = driver.findElement(By.cssSelector(DataProvider.getLocator("password_input")));
		passwordInput.click();
		passwordInput.sendKeys("xsw123");
		
		WebElement birthYearInput = driver.findElement(By.cssSelector(DataProvider.getLocator("birth_year_input")));
		wait.until(ExpectedConditions.elementToBeClickable(birthYearInput));
		birthYearInput.sendKeys("1989");
		
		WebElement zipInput = driver.findElement(By.cssSelector(DataProvider.getLocator("zip_input")));
		wait.until(ExpectedConditions.elementToBeClickable(zipInput));;
		zipInput.sendKeys("60642");
		
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor)driver).executeScript("document.querySelectorAll('#register_female.onboarding__form__radio')[0].style.display = 'block'");
			((JavascriptExecutor)driver).executeScript("document.querySelectorAll('#register_female.onboarding__form__radio')[0].click();");
			((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('registerButton')[0].click();");
		} else {
		    throw new IllegalStateException("This driver does not support JavaScript!");
		}
		
		WebElement weUserName = driver.findElement(By.cssSelector(DataProvider.getLocator("user_email_span")));
		wait.until(ExpectedConditions.visibilityOf(weUserName));
		String actualUserEmail = weUserName.getText();
		System.out.println("User has registered as " + actualUserEmail);
		
		AssertJUnit.assertEquals("User has not been logged in as " + userEmail, userEmail, actualUserEmail);
		
	}

}
