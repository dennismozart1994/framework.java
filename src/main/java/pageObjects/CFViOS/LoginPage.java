package pageObjects.CFViOS;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class LoginPage extends MobileCommands{

	@iOSXCUITFindBy(accessibility="logInButton")
	private static MobileElement homeLogInButton;
		@iOSXCUITFindBy(accessibility="emailField")
		private static MobileElement userField;
		@iOSXCUITFindBy(accessibility="passwordField")
		private static MobileElement userPasswordField;
		@iOSXCUITFindBy(accessibility="loginButton")
		private static MobileElement loginButton;
		@iOSXCUITFindBy(accessibility="forgotPassword")
		private static MobileElement forgotPasswordLink;
			// Forgot Password Text field have no identifier
			@iOSXCUITFindBy(xpath="(//XCUIElementTypeTextField)[2]")
			private static MobileElement forgotPasswordPopUpField;
			// Cancel and send Pop-up button have localized identifiers
			@iOSXCUITFindBy(xpath="(//XCUIElementTypeButton)[4]")
			private static MobileElement cancelPopUpButton;
			@iOSXCUITFindBy(xpath="(//XCUIElementTypeButton)[5]")
			private static MobileElement sendPopUpButton;
				// Success modal have localized identifier
				@iOSXCUITFindBy(xpath="//XCUIElementTypeAlert//XCUIElementTypeStaticText[1]")
				private static MobileElement responseMsgText;
				@iOSXCUITFindBy(accessibility="OK")
				private static MobileElement okPopUpButton;		
		@iOSXCUITFindBy(accessibility="cancelButton")
		private static MobileElement cancelLink;
	
	@iOSXCUITFindBy(accessibility="signUpButton")
	private static MobileElement signUpButton;
		// All fields have no identifiers on the add user form.
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeTextField)[1]")
		private static MobileElement nameField;
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeTextField)[2]")
		private static MobileElement surnameField;
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeTextField)[3]")
		private static MobileElement emailField;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeSecureTextField")
		private static MobileElement passwordField;
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeTextField)[4]")
		private static MobileElement phoneNumberField;
		// Country name have localized identifier
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeStaticText)[3]")
		private static MobileElement countryNameText;
		// Change country button have localized identifier
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeButton)[2]")
		private static MobileElement changeCountryButton;
			@iOSXCUITFindBy(xpath="//XCUIElementTypeSearchField")
			private static MobileElement searchCountryField;
			// -- Reminder -- XCUIElementTypeStaticText contains accessibility country name
		// create account and already have an account button have localized identifiers
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeButton)[3]")
		private static MobileElement addUserButton;
		@iOSXCUITFindBy(xpath="(//XCUIElementTypeButton)[4]")
		private static MobileElement alreadyHaveUserButton;
		
		// constructor 
		public LoginPage(AppiumDriver<MobileElement> driver)
		{
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
		
		public void Login(String username, String password)
		{
			Tap(homeLogInButton);
			userField.sendKeys(username);
			userPasswordField.sendKeys(password);
			Tap(loginButton);
		}
		
}
