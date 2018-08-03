package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constants;
import utility.ExcelUtils;
import utility.PDFCreator;
import utility.Screenshot;

public class LoginPage {
	// public static WebDriver driver;
	private WebDriver driver;
	
	// WebElements
	private static WebElement login;
	private static WebElement senha;
	private static WebElement login_btn;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	public void LogIn() throws Exception
	{
		driver.get(Constants.URL);		
		Constants.logger.error("Abrindo URL...");
		// add evidence
		try {
			PDFCreator.addStep("Step 1 - Acessar URL");
			PDFCreator.addScreenshot(Screenshot.TakeScreenshot(driver, "test"));
			
			// map elements
			login = driver.findElement(By.name("login"));
			senha = driver.findElement(By.name("password"));
			login_btn = driver.findElement(By.tagName("button"));
		} catch (Exception e1) {
			PDFCreator.logFatal(e1.toString());
		}

		// Read test Result into Excel File after open it
		try {
			// step 2
			login.sendKeys(ExcelUtils.getCellData(Constants.USER_R, Constants.USER_C));
			Constants.logger.error("Digitando usuário");
			senha.sendKeys(ExcelUtils.getCellData(Constants.PASS_R, Constants.PASS_C));
			Constants.logger.error("Digitando senha");
			// add evidence
			PDFCreator.addStep("Step 2 - Digitar usuário e senha");
			PDFCreator.addScreenshot(Screenshot.TakeScreenshot(driver, "test"));
			
			// step 3
			login_btn.click();
			Constants.logger.error("Efetuando Login");
			// add evidence
			PDFCreator.addStep("Step 3 - Realizar Login");
			PDFCreator.addScreenshot(Screenshot.TakeScreenshot(driver, "test"));
		} catch (Exception e) {
			PDFCreator.logFatal(e.toString());
		}
		
	}
}
