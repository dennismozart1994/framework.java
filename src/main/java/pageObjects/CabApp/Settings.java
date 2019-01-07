package pageObjects.CabApp;

import appCore.MobileCommands;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

public class Settings extends MobileCommands{
	/***************************** General Controllers *********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="settingsGeneralCell")
	private static MobileElement generalButton;
	
	@iOSXCUITFindBy(accessibility="unitsControlv")
	private static MobileElement unitsControlToggle;
	
	@iOSXCUITFindBy(accessibility="cloudControl")
	private static MobileElement cloudSyncToggle;
	
	@iOSXCUITFindBy(accessibility="sendReceiveControl")
	private static MobileElement sendReceiveToggle;
	
	@iOSXCUITFindBy(accessibility="visiblePaneControl")
	private static MobileElement controlPanelToggle;
	
	@iOSXCUITFindBy(accessibility="operatorModeToggle")
	private static MobileElement operatorModeToggle;
	
	// Start Month have localized ids, could it be set as an unique id in english instead the
	// month itself? Something like startMonthButton ?
	@iOSXCUITFindBy(accessibility="SETEMBRO")
	private static MobileElement startMonthButton;
	
	// The whole table after clicking on startMonthButton have no id on the cells
	// the only ids available are localized inside the cells.
	// Could the table have a unique id and the localized ids not be localized?
	
	
	/***************************** Data Controllers ***********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="settingsDataCell")
	private static MobileElement dataButton;
		// All the ids are localized after clicking on this option
		// The data table with the years have no id on the cells
		// The cells of the fields from the year selected have no id as well, 
		// in this case could the cells have the accessibility id set with the field name?
		// The button generate Map also have localized ids
		// the back button Have localized id, name and label
	
	/***************************** Device Controllers ********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="settingsDevicesCell")
	private static MobileElement devicesButton;
		// The devices table have no id, it would be easier to select if the table have an id
		// because using the expression //XCUIElementTypeTable[2] it returns no value
		// while using the expression //XCUIElementTypeTable[1] it returns both
		// The button table and the desired table
		// The cells already contains the correct setup containing the device name as the id
	
		// The pair new drive button have no identification such as id, name or even label
		
		// After clicking on the drive we want to pair we have
		// no identifiers for the drive name text field 
		// The back button have localized identifiers
		@iOSXCUITFindBy(accessibility="nextButton")
		private static MobileElement nextButton;
		
		// The table of equipments to select have no identifier
		// The cells should have the equipment name as an id
		
		// The button Add new equipment have localized identifiers
		
		// Is this your FV drive? The entire pop up alert have localized identifiers
		
		// Make the field active alert have localized identifiers
		// Same problem with the table and the cells don't have the ids into the cells with
		// the field names
		// Also the buttons from this alert have localized identifiers
		
	/***************************** Hybrid Controllers ********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="settingsHybridsCell")
	private static MobileElement hybridsButton;
		// Tabs Hybrid and Seed Treatment have localized identifiers
		// The table of hybrid dont't have a unique identifier
		// The cells of the table don't have the id with the hybrid name
		// This would allowed to access the remove button easier
		
		
		@iOSXCUITFindBy(accessibility="addNewHybridButton")
		private static MobileElement addNewHybridButton;
			// I'm unable to map the table to add new custom hybrid,
			// due to the extension of the xml the appium crashes the app after trying 
			// to take the xml
			// Table don't contains identifies 
			// Cells should contain the id with the hybrid name
			@iOSXCUITFindBy(accessibility="addHybrid")
			private static MobileElement addHybrid;
				// Culture table don't contain identifier
				// Cells don't contain identifier and shouldn't be localized.
				@iOSXCUITFindBy(accessibility="backArrowButton")
				private static MobileElement backArrowButton;
		
		@iOSXCUITFindBy(accessibility="removeAllButton")
		private static MobileElement removeAllButton;
		
	/*************************** Applications Controllers *********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="settingsApplicationsCell")
	private static MobileElement applicationsButton;
		// Table and cells without identifier
		// Ids localized for the buttons eit application
		// Cells should contain the id with the name of the application
	
		@iOSXCUITFindBy(accessibility="addNewApplicationButton")
		private static MobileElement addNewApplicationButton;
			@iOSXCUITFindBy(accessibility="nameTextField")
			private static MobileElement nameTextField;
			@iOSXCUITFindBy(accessibility="previousButton")
			private static MobileElement previousButton;
			@iOSXCUITFindBy(accessibility="nextButton")
			private static MobileElement nextAddApplicationButton;
				@iOSXCUITFindBy(accessibility="closeButton")
				private static MobileElement closeButton;
				@iOSXCUITFindBy(accessibility="editNameButton")
				private static MobileElement editNameButton;
				@iOSXCUITFindBy(accessibility="baseRateNumpadButton")
				private static MobileElement applicationBaseRateNumpadButton;
				@iOSXCUITFindBy(accessibility="typeToggle")
				private static MobileElement applicationRatetypeToggle;
				@iOSXCUITFindBy(accessibility="helpIcon")
				private static MobileElement helpIcon;
					// Application Table List don't have any id and
					// Weight amount text field don't have identifier
						@iOSXCUITFindBy(accessibility="1")
						private static MobileElement number1Button;
						@iOSXCUITFindBy(accessibility="2")
						private static MobileElement number2Button;
						@iOSXCUITFindBy(accessibility="3")
						private static MobileElement number3Button;
						@iOSXCUITFindBy(accessibility="4")
						private static MobileElement number4Button;
						@iOSXCUITFindBy(accessibility="5")
						private static MobileElement number5Button;
						@iOSXCUITFindBy(accessibility="6")
						private static MobileElement number6Button;
						@iOSXCUITFindBy(accessibility="7")
						private static MobileElement number7Button;
						@iOSXCUITFindBy(accessibility="8")
						private static MobileElement number8Button;
						@iOSXCUITFindBy(accessibility="9")
						private static MobileElement number9Button;
						@iOSXCUITFindBy(accessibility="0")
						private static MobileElement number0Button;
						@iOSXCUITFindBy(accessibility=".")
						private static MobileElement dotButton;
						@iOSXCUITFindBy(accessibility="del")
						private static MobileElement delButton;
						@iOSXCUITFindBy(accessibility="done")
						private static MobileElement saveButton;
					// Unit and Remove Button cannot be mapped on Page Factory due they ids
					// contain the name of the application on the table
						// Units have localized id (Kg and Litros in Portuguese for e.g)
					@iOSXCUITFindBy(accessibility="addProductButton")
					private static MobileElement addProductButton;
						// List is too big to load, same issue as hybrid list when clicked
						// Add application the app crashes
						@iOSXCUITFindBy(accessibility="hybridSearchField")
						private static MobileElement hybridSearchField;
						// Table don't contain identifier
						// Same with the cells, it should contains the product as id
						@iOSXCUITFindBy(accessibility="addProductName")
						private static MobileElement addProductNameButton;
						
				@iOSXCUITFindBy(accessibility="removeButton")
				private static MobileElement removeButton;
				@iOSXCUITFindBy(accessibility="doneButton")
				private static MobileElement doneButton;	
			
		@iOSXCUITFindBy(accessibility="removeAll")
		private static MobileElement removeAllApplicationsButton;
		
	/******************************* Prescription Controllers *********************************/
	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(accessibility="settingsPrescriptionsCell")
	private static MobileElement prescriptionsButton;
	
		// Prescription Table and cells don't contain identifiers
		// Cells should contains the prescription name as identifier
		// Remove Button have a localized identifier
		// Add New prescription don't contain an identifier
			@iOSXCUITFindBy(accessibility="close")
			private static MobileElement closePrescriptionButton;
			// Select file button have a localized identifier
			
	/*************************** Shape Files Controllers **************************************/
	@iOSXCUITFindBy(accessibility="settingsImportedMapsCell")
	private static MobileElement importedMapsButton;
	
	/*************************** Notifications Controllers **************************************/
	@iOSXCUITFindBy(accessibility="settingsNotificationsCell")
	private static MobileElement notificationsButton;
	
	// There's no identifiers on this screen

	/************************************** Functions  *****************************************/
	public Settings(AppiumDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
