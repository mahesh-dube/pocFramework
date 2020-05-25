package pocFramework.pocFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Report.ExtentTestManager;
import Report.TestListener;
import Utility.ExcelUtility;

@Test
public class Test_Cases_2 extends TestBase {
	public static String OBJ_Register, OBJ_gender, OBJ_firstName, OBJ_lastName, OBJ_DOBday, OBJ_DOBmonth, OBJ_DOByear,
			OBJ_email, OBJ_companyName, OBJ_PWD, OBJ_ConfirmPWD, OBJ_RegisterBTN, login, loginEmail, loginPassword,
			submit, OBJ_logout, productComp, productDesktop, productFisrt, addTOCart, closeBar, shoppigCart,
			VerfyProductFirst, ContinueShopping, productNotebook, ProductMacbook, addTOCartFisrt, addTOCartSecond,
			verifyProdcutSecond, OBJ_Register_sucess_msg, OBJ_Register_failure_msg;

	// public static String Data_FirstName, Data_LastName, Data_company,
	// Data_URL, Data_emaildID, Data_username,
	// Data_password, Data_Day, Data_Month, Data_year, Data_Register_sucess_msg,
	// Data_Register_failure_msg,
	// Data_emaildID_Duplicate;

	@DataProvider
	public Object[][] testData() throws Exception {

		Object[][] testObjArray = ExcelUtility.getTableArray(
				System.getProperty("user.dir")+"/src/test/java/Utility/TestData.xlsx", "TestData");

		return (testObjArray);

	}

	@Test(dataProvider = "testData")
	public void NewUserReg(String Data_URL, String Data_username, String Data_password, String Data_emaildID,
			String Data_FirstName, String Data_LastName, String Data_company, String Data_Day, String Data_Month,
			String Data_year, String Data_Register_sucess_msg, String Data_Register_failure_msg) throws IOException {

		// Get Object Repo
		File fileOR = new File("./resource/ObjectRepository.properties");
		FileInputStream fisOR = new FileInputStream(fileOR);

		// Get TestData
		// File fileTD = new File(".\\resource\\TestData.properties");
		// FileInputStream fisTD = new FileInputStream(fileTD);

		Properties propOR = new Properties();
		// Properties propTD = new Properties();

		propOR.load(fisOR);
		// propTD.load(fisTD);

		OBJ_Register = propOR.getProperty("OBJ_Register");
		OBJ_gender = propOR.getProperty("OBJ_gender");
		OBJ_firstName = propOR.getProperty("OBJ_firstName");
		OBJ_lastName = propOR.getProperty("OBJ_lastName");
		OBJ_DOBday = propOR.getProperty("OBJ_DOBday");
		OBJ_DOBmonth = propOR.getProperty("OBJ_DOBmonth");
		OBJ_DOByear = propOR.getProperty("OBJ_DOByear");
		OBJ_email = propOR.getProperty("OBJ_email");
		OBJ_companyName = propOR.getProperty("OBJ_companyName");
		OBJ_PWD = propOR.getProperty("OBJ_PWD");
		OBJ_ConfirmPWD = propOR.getProperty("OBJ_ConfirmPWD");
		OBJ_RegisterBTN = propOR.getProperty("OBJ_RegisterBTN");
		OBJ_Register_sucess_msg = propOR.getProperty("OBJ_Register_sucess_msg");
		OBJ_Register_failure_msg = propOR.getProperty("OBJ_Register_failure_msg");

		login = propOR.getProperty("OBJ_login");
		loginEmail = propOR.getProperty("OBJ_username");
		loginPassword = propOR.getProperty("OBJ_password");
		submit = propOR.getProperty("OBJ_submit");
		OBJ_logout = propOR.getProperty("OBJ_logout");

		productComp = propOR.getProperty("OBJ_product_computer");
		productDesktop = propOR.getProperty("OBJ_product_desktop");
		productFisrt = propOR.getProperty("OBJ_product");
		addTOCart = propOR.getProperty("OBJ_addToCart");

		closeBar = propOR.getProperty("OBJ_closeBar");

		shoppigCart = propOR.getProperty("OBJ_gotoCart");
		VerfyProductFirst = propOR.getProperty("OBJ_productCheck");
		ContinueShopping = propOR.getProperty("OBJ_continueShop");
		productNotebook = propOR.getProperty("OBJ_ProductNotebook");
		ProductMacbook = propOR.getProperty("OBJ_productMacbook");
		addTOCartFisrt = propOR.getProperty("OBJ_addTOCartFirst");
		addTOCartSecond = propOR.getProperty("OBJ_addTOCartSecond");
		verifyProdcutSecond = propOR.getProperty("OBJ_verifyNotebook");

		driver.get(Data_URL);

		driver.manage().window().maximize();

		driver.findElement(By.xpath(OBJ_Register)).click();

		boolean genderSelected = driver.findElement(By.xpath(OBJ_gender)).isSelected();
		if (genderSelected == false) {
			driver.findElement(By.xpath(OBJ_gender)).click();

		}
		driver.findElement(By.xpath(OBJ_firstName)).sendKeys(Data_FirstName);
		driver.findElement(By.xpath(OBJ_lastName)).sendKeys(Data_LastName);
		Select OBJ_DOBday_dropdown = new Select(driver.findElement(By.xpath(OBJ_DOBday)));
		OBJ_DOBday_dropdown.selectByValue(Data_Day);
		Select OBJ_DOBmonth_dropdown = new Select(driver.findElement(By.xpath(OBJ_DOBmonth)));
		OBJ_DOBmonth_dropdown.selectByValue(Data_Month);
		Select OBJ_DOByear_dropdown = new Select(driver.findElement(By.xpath(OBJ_DOByear)));
		OBJ_DOByear_dropdown.selectByValue(Data_year);
		driver.findElement(By.xpath(OBJ_email)).sendKeys(Data_emaildID);
		driver.findElement(By.xpath(OBJ_companyName)).sendKeys(Data_company);
		driver.findElement(By.xpath(OBJ_PWD)).sendKeys(Data_password);
		driver.findElement(By.xpath(OBJ_ConfirmPWD)).sendKeys(Data_password);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(OBJ_RegisterBTN)).click();

		ExtentTestManager.getTest().log(Status.INFO, "clicked on Register button");

		try {
			String assert_Name = driver.findElement(By.xpath(OBJ_Register_failure_msg)).getText();
			if (assert_Name.equalsIgnoreCase(Data_Register_failure_msg)) {
				ExtentTestManager.getTest().log(Status.FAIL, Data_Register_failure_msg);

			} else {
				ExtentTestManager.getTest().log(Status.FAIL, "something went wrong");
				// driver.quit();

			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtentTestManager.getTest().log(Status.FAIL, "Registration Failed! " + e.getMessage());

		}

	}

}