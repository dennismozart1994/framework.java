package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.itextpdf.text.DocumentException;

import appModule.WebCommands;
import utility.Constants;
import utility.ExcelUtils;

public class LoginPage extends WebCommands{
	// public static WebDriver driver;
	private WebDriver driver;
	
	// WebElements
	private static WebElement login;
	private static WebElement senha;
	private static WebElement login_btn;
	
	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	// Access URL Function
	private void accessURL() throws DocumentException {
		driver.get(Constants.URL);		
		Log("Abrindo URL...");
		// add evidence
		try {
			addStep("Step 1 - Acessar URL");
			TakeScreenshot();
		} catch (Exception e1) {
			ExceptionThrown(e1.toString());
		}
	}
	
	private void sendLogin() throws Exception {
		try {
			Log("Typing User");
			login = driver.findElement(By.name("login"));
			InsertDataIntoField
			(
				login,
				ExcelUtils.getCellData
				(	// Read test data from Excel File to use
					Constants.FILE_PATH + Constants.FILE_NAME,
					"Web",
					Constants.WEB_START_CONTENT_LINE,
					Constants.USER_C
				)
			);
		}
		catch(Exception e)
		{
			ExceptionThrown(e.toString());
		}
	}
	

	private void typePassword() throws Exception {
		try {
			senha = driver.findElement(By.name("password"));
			Log("Typing password");
			InsertDataIntoField
			(
				senha,
				ExcelUtils.getCellData
				(	// Read test data from Excel File to use
					Constants.FILE_PATH + Constants.FILE_NAME,
					"Web",
					Constants.WEB_START_CONTENT_LINE,
					Constants.PASS_C
				)
			);
		}
		catch(Exception e)
		{
			ExceptionThrown(e.toString());
		}
	}
	
	// Log In Function	
	public void LogIn() throws Exception
	{
		accessURL();
		sendLogin();
		typePassword();
		// add evidence
		addStep("Step 2 - Digitar usuário e senha");
		TakeScreenshot();
		
		try {
			// step 3
			login_btn = driver.findElement(By.tagName("button"));
			Click(login_btn);
			Log("Sign In");
			
			// add evidence
			addStep("Step 3 - Realizar Login");
			TakeScreenshot();
		} catch (Exception e) {
			ExceptionThrown(e.toString());
		}
		
	}	
}
