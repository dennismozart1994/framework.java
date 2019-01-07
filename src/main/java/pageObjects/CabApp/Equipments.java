package pageObjects.CabApp;

import java.io.IOException;
import java.util.List;

import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Equipments extends MobileCommands{

	/*********************** Header Elements ************************/
    @HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="remoteViewSearchButton")
	private static MobileElement remoteViewSearchButton;

    @HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="homeButton")
	private static MobileElement homeButton;
	
	/*********************** Controllers ****************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="addNewEquipmentButton")
	private static MobileElement addNewEquipmentButton;
		@iOSXCUITFindBy(accessibility="addEquipmentBack")
		private static MobileElement cancelButton;
		@iOSXCUITFindBy(accessibility="addEquipmentNext")
		private static MobileElement nextButton;
		// Equipment Type Table don't have identifier
		// Equipments have localized identifier
		// Search Model fields also have localized identifier
		@iOSXCUITFindBy(accessibility="nameTextField")
		private static MobileElement nameTextField;
		@iOSXCUITFindBy(accessibility="removeButton")
		private static MobileElement removeEquipmentButton;
		@iOSXCUITFindBy(accessibility="doneButton")
		private static MobileElement finishAddEquipmentButton;
		@iOSXCUITFindBy(accessibility="editName")
		private static MobileElement editEquipmentNameButton;
	
	// Equipments table don't have identifier, same with the cells
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath="//XCUIElementTypeCell")
	private static List<MobileElement> tableList;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeCell/XCUIElementTypeStaticText[2]")
		private static List<MobileElement> equipmentList;
	
	/********************* Functions **************************/
		
	public Equipments(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	// scroll to select field / crop / map
	private static MobileElement scrollDefaultList(String field) throws IOException, DocumentException
	{
		MobileElement element = null;
		int indexOfSearch = equipmentList.indexOf(driver.findElementByXPath("//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable//XCUIElementTypeStaticText[@name='"+field+"']"));
		if( indexOfSearch != -1) {
			 element = tableList.get(indexOfSearch);
			 Log("finded");
		}
		scrollUntilElementShowUp(element);
		return equipmentList.get(indexOfSearch);
	}
		
		
}
