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

public class Maps extends MobileCommands{
	/******************* Field, Crop and Report Selectors **********************/
	@iOSFindBy(accessibility="fieldNameButton")
	private static MobileElement fieldNameButton;
		@iOSFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton")
		private static List<MobileElement> orderBy;
		@iOSFindBy(className="XCUIElementTypeSearchField")
		private static MobileElement fieldFilter;
		@iOSFindBy(xpath="//XCUIElementTypeSearchField/XCUIElementTypeButton")
		private static MobileElement clearFieldFilter;	
		@iOSFindBy(accessibility="fieldFilterButton")
		private static MobileElement fieldFilterButton;
			@iOSFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]//XCUIElementTypeCell/XCUIElementTypeStaticText")
			private static List<MobileElement> filterList;
		
	@iOSFindBy(accessibility="cropSeasonButton")
	private static MobileElement cropSeasonButton;
	
	@iOSFindBy(accessibility="mapTypeButton")
	private static MobileElement mapTypeButton;
		@iOSFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]")
		private static MobileElement mapSelectPlanting;
		@iOSFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[2]")
		private static MobileElement mapSelectInSeason;
		@iOSFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[3]")
			@iOSFindBy(accessibility="FHMapIcon")
			private static MobileElement mapSelectFHA;
		private static MobileElement mapSelectHarvest;
		@iOSFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[4]")
		private static MobileElement mapSelectBaseLayer;
		
	@iOSFindBy(accessibility="mapSelectorCloseButton")
	private static MobileElement mapCloseListButton;
	
	@iOSFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable/XCUIElementTypeCell")
	private static List<MobileElement> tableList;
		@iOSFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
		private static List<MobileElement> fieldList;
	
	/**************************** Notes Section Selectors *********************************/
	@iOSFindBy(accessibility="metricMaximize")
	private static MobileElement maximizeLeftLegend;
	
	@iOSFindBy(accessibility="search")
	private static MobileElement searchLeftLegend;
	
	// To Do -- Map the table @iOSFindBy(xpath="")
	
	@iOSFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeButton[1]")
	private static MobileElement mapOptionsButtons;
	
	@iOSFindBy(accessibility="metricMinimize")
	private static MobileElement minimizeLeftLegend;
	
	/************************* Main Selector Controllers ***************************/
	@iOSFindBy(accessibility="MAPTOOLSdefaultzoom_button")
	private static MobileElement myLocationButton;
	
	@iOSFindBy(accessibility="MAPTOOLSfieldzoom_button")
	private static MobileElement recenterButton;
	
	@iOSFindBy(accessibility="MAPTOOLSsplitview_button")
	private static MobileElement splitViewButton;
	
	@iOSFindBy(accessibility="MAPTOOLSfieldreport_button")
	private static MobileElement fieldReportButton;
	
	@iOSFindBy(accessibility="MAPTOOLShome_button")
	private static MobileElement homeButton;
	
	// reports
	@iOSFindBy(accessibility="MAPTOOLSpopovermenu_button")
	private static MobileElement reportMenuButton;
		@iOSFindBy(accessibility="MAPTOOLSPOPOVERfieldsummary_button")
		private static MobileElement fieldSummaryButton;
		@iOSFindBy(accessibility="MAPTOOLSPOPOVERfiles_button")
		private static MobileElement sendFieldFilesButton;
		@iOSFindBy(accessibility="MAPTOOLSPOPOVERshare_button")
		private static MobileElement shareButton;
		@iOSFindBy(accessibility="MAPTOOLSPOPOVERmarker_button")
		private static MobileElement addPinButton;
		@iOSFindBy(accessibility="MAPTOOLSPOPOVERfieldregion_button")
		private static MobileElement frrButton;
	
	/************************* Legend Controllers *************************/
	@iOSFindBy(accessibility="LEGENDedit_button")
	private static MobileElement editLegendButton;

	@iOSFindBy(accessibility="LEGENDmin_button")
	private static MobileElement hideLegendButton;
	
	/************************* Functions *********************************/
	public Maps(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	// scroll to select field / crop / map
	private static MobileElement scrollDefaultList(String field) throws IOException, DocumentException
	{
		MobileElement element = null;
		int indexOfSearch = fieldList.indexOf(driver.findElementByXPath("//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable//XCUIElementTypeStaticText[@name='"+field+"']"));
		if( indexOfSearch != -1) {
			 element = tableList.get(indexOfSearch);
			 Log("finded");
		}
		scrollUntilElementShowUp(element);
		return element;
	}
	
	// 0) client button - 1) farm button 3) all button
	public void ChangeFilter(String Mode)
	{	
		if(Mode.toLowerCase().contains("client"))
			filterList.get(0).click();
		else
			filterList.get(1).click();
	}
	
	// order by function
	public void orderBy(String Mode) 
	{
		switch(Mode.toLowerCase()) {
			case "field": orderBy.get(0).click();
			break;
			case "farm": orderBy.get(1).click();
			break;
			case "client": orderBy.get(2).click();
			break;
			default: orderBy.get(3).click();
			break;
		}
	}
	
	public void selectField(String field) throws Exception
	{	
		Tap(fieldNameButton);
		scrollDefaultList(field).click();
		addStepWithScreenshot("Select a field");
	}
	
	public void selectCrop(String crop) throws Exception
	{
		Tap(cropSeasonButton);
		scrollDefaultList(crop).click();
		addStepWithScreenshot("Tap on Crop Layer");
	}
	// 1 - Planting Tab, 2 - Harvest Tab, default - Base Layer Tab
	public void selectMapType(int MapTab, String map) throws IOException, DocumentException, InterruptedException
	{
		Tap(mapTypeButton);
		addStepWithScreenshot("Tap on Map Layer");
		switch(MapTab)
		{
			case 1:Tap(mapSelectPlanting);;
				break;
			case 2:Tap(mapSelectHarvest);
				break;
			default:Tap(mapSelectBaseLayer);
				break;
		}
		scrollDefaultList(map).click();
		Thread.sleep(3000);
		addStepWithScreenshot("Tap on " + map);
	}
}
