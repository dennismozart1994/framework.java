package pageObjects.CabApp;

import java.io.IOException;
import java.util.List;

import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Maps extends MobileCommands{
	/******************* Field, Crop and Report Selectors **********************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="fieldNameButton")
	private static MobileElement fieldNameButton;
		// All order by filters buttons have localized ids, name and label
		// This way is only possible to localize the element via absolute xpath
		@iOSXCUITFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton")
		private static List<MobileElement> orderBy;
		@iOSXCUITFindBy(className="XCUIElementTypeSearchField")
		private static MobileElement fieldFilter;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeSearchField/XCUIElementTypeButton")
		private static MobileElement clearFieldFilter;
		
		@iOSXCUITFindBy(accessibility="fieldFilterButton")
		private static MobileElement fieldFilterButton;
			// Options inside filter table have the same issue of localized ids for the whole table
			// Please add the ids on the cells to allow the scroll and tap
			// Because the cell is interactive and not the text 
			@iOSXCUITFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]//XCUIElementTypeCell/XCUIElementTypeStaticText")
			private static List<MobileElement> filterList;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="cropSeasonButton")
	private static MobileElement cropSeasonButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="mapTypeButton")
	private static MobileElement mapTypeButton;
		// All the map types ids, names and lables are localized, so
		// the only way to map universally is via absolute xpath, 
		// could it be setup with English ids?
		@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[1]")
		private static MobileElement mapSelectPlanting;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[2]")
		private static MobileElement mapSelectInSeason;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[3]")
		private static MobileElement mapSelectHarvest;
			@iOSXCUITFindBy(accessibility="FHMapIcon")
			private static MobileElement mapSelectFHA;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeButton[4]")
		private static MobileElement mapSelectBaseLayer;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="mapSelectorCloseButton")
	private static MobileElement mapCloseListButton;
	
	// All tables have the same issue of localized ids or simple does not have ids at all
	// In case of fieldname the id could be the fieldname itself on the cell
	// In the other cases, all the ids could be put on the cell based on their english values
	// because it won't change and it would be easier to create an uniform automation
	// also the id should be add on the cell because the table is scrollable and the cell is
	// interactive via appium, not the text, you can see that by the property 
	// "Visible" set as false on the field name, when the field list is expanded
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable/XCUIElementTypeCell")
	private static List<MobileElement> tableList;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[2]//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
		private static List<MobileElement> fieldList;
	
	/**************************** Notes Section Selectors *********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="metricMaximize")
	private static MobileElement maximizeLeftLegend;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="search")
	private static MobileElement searchLeftLegend;
	
	// The Pin ad Region Notes Table have no id, name or identifier to localize via automation
	// The cells inside with the pins details also face the same issue
	// It needs to improve, otherwise the only way to locate is via absolute xpath
	
	// Button "MAP OPTIONS" have a localized id, name and label
	// only possible to localize via absolute xpath
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath="//XCUIElementTypeWindow/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeButton[1]")
	private static MobileElement mapOptionsButton;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="metricMinimize")
	private static MobileElement minimizeLeftLegend;
	
	/************************* Widgets Section Selectors *********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="metricsEdit")
	private static MobileElement metricsEdit;
	
	// All widgets have localized ids, name and labels
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath="(//XCUIElementTypeImage[@name=\"metricButtonGreen\"])")
	private static List<MobileElement> activeWidgets;
	
	// The Available widgets panel is generated during runtime and all widgets ids are also
	// localized, in this way is impossible to use xpath to map elements because they're
	// all dynamic generated
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="metricCollectionView")
	private static MobileElement metricCollectionView;

	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="greenCloseButton")
	private static MobileElement closeMetricPanelButton;
	
	/************************* My Location Controller ***************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="MAPTOOLSdefaultzoom_button")
	private static MobileElement myLocationButton;
	
	/************************* Re-center Controllers ******************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="MAPTOOLSfieldzoom_button")
	private static MobileElement recenterButton;
	
	/************************* Split View Controllers ******************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="MAPTOOLSsplitview_button")
	private static MobileElement splitViewButton;
		@iOSXCUITFindBy(accessibility="slaveMapTypeButton")
		private static MobileElement slaveMapTypeButton;
		@iOSXCUITFindBy(xpath="//XCUIElementTypeTable[@name=\"mapTypeTable\"]/XCUIElementTypeCell")
		private static List<MobileElement> MapTables;
	/***************************** Home Controllers *****************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="MAPTOOLShome_button")
	private static MobileElement homeButton;
	
	/***************************** Report Controllers *****************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="MAPTOOLSfieldreport_button")
	private static MobileElement fieldReportButton;
		@iOSXCUITFindBy(accessibility="reportTypeControl")
		private static MobileElement reportTypeControlToggle;
		// Table OrderBy contains localized identifiers
		@iOSXCUITFindBy(xpath="//XCUIElementTypeTable[@name=\"fieldReportTable\"]/XCUIElementTypeCell")
		private static List<MobileElement> reportTableCells;
		// Is not possible to reach the values from the table
		@iOSXCUITFindBy(accessibility="footerIndex1")
		private static MobileElement TotalCell1;
		@iOSXCUITFindBy(accessibility="footerIndex2")
		private static MobileElement TotalCell2;
		@iOSXCUITFindBy(accessibility="footerIndex3")
		private static MobileElement TotalCell3;
		@iOSXCUITFindBy(accessibility="FIELDREPORTSclose_button")
		private static MobileElement closeButton;
		
	/***************************** Share Controllers *****************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="MAPTOOLSpopovermenu_button")
	private static MobileElement reportMenuButton;
		/***************** Field Summary Report Controllers **********************/
		@iOSXCUITFindBy(accessibility="MAPTOOLSPOPOVERfieldsummary_button")
		private static MobileElement fieldSummaryButton;
			@iOSXCUITFindBy(accessibility="createMapPDFButton")
			private static MobileElement createMapPDFButton;
			@iOSXCUITFindBy(accessibility="notesControl")
			private static MobileElement notesControlField;
			/********************** Harvest Report *****************************/
			@iOSXCUITFindBy(accessibility="acreValue")
			private static MobileElement acreValue;
			@iOSXCUITFindBy(accessibility="wetMassValue")
			private static MobileElement wetMassValue;
			// Moisture value don't have unique identifier
			@iOSXCUITFindBy(accessibility="dryBushelsValue")
			private static MobileElement dryBushelsValue;
			@iOSXCUITFindBy(accessibility="averageYieldValue")
			private static MobileElement averageYieldValue;

		@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
		@iOSXCUITFindBy(accessibility="MAPTOOLSPOPOVERfiles_button")
		private static MobileElement sendFieldFilesButton;
		@iOSXCUITFindBy(accessibility="MAPTOOLSPOPOVERshare_button")
		private static MobileElement shareButton;
		@iOSXCUITFindBy(accessibility="MAPTOOLSPOPOVERmarker_button")
		private static MobileElement addPinButton;
		@iOSXCUITFindBy(accessibility="MAPTOOLSPOPOVERfieldregion_button")
		private static MobileElement frrButton;
	
	/************************* Legend Controllers *************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="LEGENDedit_button")
	private static MobileElement editLegendButton;
	
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="LEGENDmin_button")
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
