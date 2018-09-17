package pageObjects.CabApp;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Equipments extends MobileCommands{

	/*********************** Header Elements ************************/
	@iOSFindBy(accessibility="remoteViewSearchButton")
	private static MobileElement remoteViewSearchButton;
	
	@iOSFindBy(accessibility="homeButton")
	private static MobileElement homeButton;
	
	/*********************** Controllers ****************************/
	@iOSFindBy(accessibility="addNewEquipmentButton")
	private static MobileElement addNewEquipmentButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeCell")
	private static List<MobileElement> tableList;
		@iOSFindBy(xpath="//XCUIElementTypeCell/XCUIElementTypeStaticText[2]")
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
