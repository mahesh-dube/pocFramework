package pocFramework.pocFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

	public static WebDriver driver;

	@BeforeClass
	public void initDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Driver/chromedriver");
		driver = new ChromeDriver();
	}

	@AfterClass
	public void TearDown() {
		driver.quit();
	}
}
