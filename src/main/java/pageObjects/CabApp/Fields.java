package pageObjects.CabApp;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Fields {

	@iOSFindBy(accessibility="homeButton")
	private static MobileElement homeButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeTextField")
	private static MobileElement searchField;
	
	@iOSFindBy(accessibility="addFieldButton")
	private static MobileElement addFieldButton;
	
	/************************* Add Field Form Selectors *******************************/
	
	@iOSFindBy(xpath="//XCUIElementTypeTextField[@name='fieldName']")
	private static MobileElement fieldNameField;
	
	@iOSFindBy(accessibility="clientName")
	private static MobileElement clientNameField;
	
	@iOSFindBy(accessibility="farmName")
	private static MobileElement farmNameField;
	
	@iOSFindBy(accessibility="cancelButton")
	private static MobileElement cancelButton;
	
	@iOSFindBy(accessibility="saveButton")
	private static MobileElement saveButton;
	
	/************************** Field Details Selectors *****************************/
	@iOSFindBy(accessibility="fieldName")
	private static MobileElement fieldNameText;
	
	@iOSFindBy(accessibility="editButton")
	private static MobileElement editButton;
	
	@iOSFindBy(accessibility="deleteButton")
	private static MobileElement deleteButton;
	
	@iOSFindBy(accessibility="addHybrid")
	private static MobileElement addHybridButton;
		@iOSFindBy(accessibility="addNewHybridButton")
		private static MobileElement addNewHybridButton;
	
	@iOSFindBy(accessibility="addTreatment")
	private static MobileElement addTreatmentButton;
	@iOSFindBy(accessibility="addNewTreatmentButton")
	private static MobileElement addNewTreatmentButton;
	
	@iOSFindBy(accessibility="addApplication")
	private static MobileElement addApplicationButton;
		@iOSFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable/XCUIElementTypeButton")
		private static MobileElement addNewApplicationButton;
		
	@iOSFindBy(accessibility="close")
	private static MobileElement closeButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton")
	private static MobileElement addPrescriptionButton;
	
	public Fields(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
}