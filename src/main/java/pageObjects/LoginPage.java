package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import appCore.WebCommands;
import utility.Constants;
import utility.ExcelUtils;

public class LoginPage extends WebCommands{
	// public static WebDriver driver;
	
	// WebElements
	@FindBy(how = How.NAME, using = "login")
	private WebElement login;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement senha;
	
	@FindBy(how = How.TAG_NAME, using = "button")
	private WebElement login_btn;	
	
	// Access URL Function
	public static LoginPage accessURL() throws Exception {
		driver.get(Constants.URL);		
		Log("Abrindo URL...");
		// add evidence
		try {
			addStep("Step 1 - Acessar URL");
			TakeScreenshot();
		} catch (Exception e1) {
			ExceptionThrown(e1.toString(), Constants.WEB_START_CONTENT_LINE);
		}
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	private void sendLogin() throws Exception {
		try {
			Log("Typing User");
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
			// add evidence
			TakeScreenshot();
		}
		catch(Exception e)
		{
			ExceptionThrown(e.toString(), Constants.WEB_START_CONTENT_LINE);
		}
	}
	

	private void typePassword() throws Exception {
		try {
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
			// add evidence
			TakeScreenshot();
		}
		catch(Exception e)
		{
			ExceptionThrown(e.toString(), Constants.WEB_START_CONTENT_LINE);
		}
	}
	
	// Log In Function	
	public HomePage LogIn() throws Exception
	{
		addStep("Step 2 - Digitar usuário e senha");
		sendLogin();
		typePassword();
		try {
			Click(login_btn);
			Log("Sign In");
			addStep("Step 3 - Realizar Login");
			TakeScreenshot();
		} catch (Exception e) {
			ExceptionThrown(e.toString(), Constants.WEB_START_CONTENT_LINE);
		}
		return PageFactory.initElements(driver, HomePage.class);
		
	}	
}
