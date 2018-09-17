package pageObjects.CabApp;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Settings {
	/***************************** General Controllers *********************************/
	@iOSFindBy(accessibility="settingsGeneralCell")
	private static MobileElement generalButton;
	
	@iOSFindBy(accessibility="unitsControlv")
	private static MobileElement unitsControlToggle;
	
	@iOSFindBy(accessibility="cloudControl")
	private static MobileElement cloudSyncToggle;
	
	@iOSFindBy(accessibility="sendReceiveControl")
	private static MobileElement sendReceiveToggle;
	
	@iOSFindBy(accessibility="visiblePaneControl")
	private static MobileElement controlPanelToggle;
	
	@iOSFindBy(accessibility="operatorModeToggle")
	private static MobileElement operatorModeToggle;
	
	
	/***************************** Data Controllers ***********************************/
	@iOSFindBy(accessibility="settingsDataCell")
	private static MobileElement dataButton;
	
	/***************************** Device Controllers ********************************/
	@iOSFindBy(accessibility="settingsDevicesCell")
	private static MobileElement devicesButton;
	
	@iOSFindBy(accessibility="settingsHybridsCell")
	private static MobileElement hybridsButton;
	
	@iOSFindBy(accessibility="settingsApplicationsCell")
	private static MobileElement applicationsButton;
	
	@iOSFindBy(accessibility="settingsPrescriptionsCell")
	private static MobileElement prescriptionsButton;
	
	@iOSFindBy(accessibility="settingsImportedMapsCell")
	private static MobileElement importedMapsButton;
	
	@iOSFindBy(accessibility="settingsNotificationsCell")
	private static MobileElement notificationsButton;
}
