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
	
	// Log In Function	
	public void LogIn() throws Exception
	{
		accessURL();
		// map elements
		login = driver.findElement(By.name("login"));
		senha = driver.findElement(By.name("password"));
		login_btn = driver.findElement(By.tagName("button"));
		
		
		
		try {
			// step 2
			Log("Typing User");
			InsertDataIntoField(
				login,
				ExcelUtils.getCellData
				(	// Read test data from Excel File to use
					Constants.FILE_PATH + Constants.FILE_NAME,
					"Web",
					Constants.USER_R,
					Constants.USER_C
				)
			);
			Log("Typing password");
			InsertDataIntoField
			(
				senha,
				ExcelUtils.getCellData
				(	// Read test data from Excel File to use
					Constants.FILE_PATH + Constants.FILE_NAME,
					"Web",
					Constants.PASS_R,
					Constants.PASS_C
				)
			);
			
			// add evidence
			addStep("Step 2 - Digitar usuário e senha");
			TakeScreenshot();
			
			// step 3
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
