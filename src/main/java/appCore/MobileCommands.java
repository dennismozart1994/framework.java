package appCore;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.DocumentException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import properties.Config;
import utility.Constants;
import utility.Log;
import utility.PDFCreator;

public class MobileCommands extends Config{
	public static AppiumDriver<MobileElement> driver = null;
	
	/************************ LAUNCH APPS METHODS **********************************/
	// Browser App Launch
		public static AppiumDriver<MobileElement> LaunchBrowserApp(String DeviceName, CharSequence Browser) throws DocumentException, IOException
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, Browser);
			
			if(DeviceName.startsWith("Android"))
			{
				cap.setCapability(MobileCapabilityType.UDID, getDeviceSerialNumber(DeviceName));
				cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
				cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
				driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			}
			else
			{
				cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
				cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
				cap.setCapability(IOSMobileCapabilityType.START_IWDP, true);
				cap.setCapability(MobileCapabilityType.UDID, readConfig("UDID"));
				cap.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "dennis.silva@climate.com");
				cap.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer");
				driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			addStep("Open App: ");
			TakeScreenshot();
			
			return driver;
		}
	
	// Install and Launch app
	public static AppiumDriver<MobileElement> LaunchAndInstallApp(String DeviceName, String App) throws MalformedURLException, DocumentException, IOException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		
		if(DeviceName.startsWith("Android"))
		{
			File f = new File("apps");
			File fs = new File(f, App);
			
			cap.setCapability(MobileCapabilityType.UDID, getDeviceSerialNumber(DeviceName));
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
			
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		}
		else
		{
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			cap.setCapability(IOSMobileCapabilityType.START_IWDP, true);
			cap.setCapability(MobileCapabilityType.UDID, readConfig("UDID"));
			cap.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "dennis.silva@climate.com");
			cap.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer");
			cap.setCapability(MobileCapabilityType.APP, App);
			driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		addStep("Open App: ");
		TakeScreenshot();
		
		return driver;
	}
	
	
	// Launch local Android app
	public static AppiumDriver<MobileElement> LaunchAndroidLocalApp(String DeviceName, String PackageName, String Activity) throws MalformedURLException, DocumentException, IOException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
		cap.setCapability(MobileCapabilityType.UDID, getDeviceSerialNumber(DeviceName));
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PackageName);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Activity);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		addStep("Open App: ");
		TakeScreenshot();
		
		return driver;
	}
	
	// Launch local Android app
		public static AppiumDriver<MobileElement> LaunchIOSLocalApp(String DeviceName, String BundleID) throws MalformedURLException, DocumentException, IOException
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			cap.setCapability(IOSMobileCapabilityType.START_IWDP, true);
			cap.setCapability(MobileCapabilityType.UDID, readConfig("UDID"));
			cap.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "dennis.silva@climate.com");
			cap.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer");
			cap.setCapability(IOSMobileCapabilityType.BUNDLE_ID, BundleID);
			driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			addStep("Open App: ");
			TakeScreenshot();
			
			return driver;
		}
	
	/************************ FIND ELEMENTS METHODS **********************************/
	@SuppressWarnings("rawtypes")
	public static MobileElement findByUIAutomator(String Attribute, String Value) throws InterruptedException
	{
		Thread.sleep(500);
		return (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(Attribute + "(\"" + Value + "\")");
	}
	
	public static MobileElement findById(String Id)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Id)));
	}
	
	public static MobileElement findByAcessibilityID(String ID)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return (MobileElement) wait.until(ExpectedConditions.visibilityOf(driver.findElementByAccessibilityId(ID)));
	}
	
	public static MobileElement findByName(String Name)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Name)));
	}
	
	public static MobileElement findByXpath(String Xpath)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
	}
	
	public static MobileElement findByClassName(String ClassName)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ClassName)));
	}
	
	
	/************************ MOBILE GESTURES **********************************/	
	// Tap back Android Button
	public static void TapBack()
	{
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK).
				withFlag(KeyEventFlag.SOFT_KEYBOARD).
				withFlag(KeyEventFlag.EDITOR_ACTION));
	}
	// Tap home Android Button
	public static void TapHome()
	{
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.HOME).
				withFlag(KeyEventFlag.SOFT_KEYBOARD).
				withFlag(KeyEventFlag.EDITOR_ACTION));
	}
	// Tap the square that opens all the apps opened into the phone
	public static void TapSwitch()
	{
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH).
				withFlag(KeyEventFlag.SOFT_KEYBOARD).
				withFlag(KeyEventFlag.EDITOR_ACTION));
	}
	// Navigate back
	public static void NavigateBack()
	{
		driver.navigate().back();
	}
	// simple tap, click on element
	public static void Tap(MobileElement element)
	{
		new TouchAction<>(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
	}
	
	// simple tap, click on coordinates
	public static void Tap(Integer x, Integer y)
	{
		new PointOption<>();
		new TouchAction<>(driver).tap(PointOption.point(x, y)).perform();
	}
	
	// simple tap, click on coordinates relative to element
	public static void Tap(MobileElement element, Integer x, Integer y)
	{
		new TouchAction<>(driver).tap(TapOptions.tapOptions().withElement(ElementOption.element(element, x, y))).perform();
	}
	
	public static void DoubleTap(MobileElement element)
	{
		new TouchActions(driver).doubleTap(element).perform();
	}
	
	// simple click and hold
	public static void LongPress(MobileElement element)
	{
		new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element))).release().perform();
	}
	
	//click and hold for a couple of seconds
	public static void LongPress(MobileElement element, Integer Seconds)
	{
		new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)).withDuration(Duration.ofSeconds(Seconds))).release().perform();
	}
	
	// Swipe from one element to the other
	public static void Swipe(MobileElement from, MobileElement to)
	{
		// Calculate middle coordinates of element 
		// X coordinate
		int leftX = to.getLocation().getX();
		int rightX = leftX + to.getSize().getWidth();
		int middleX = (rightX + leftX) / 2;
		
		// Y coordinate
		int upperY = to.getLocation().getY();
		int lowerY = upperY + to.getSize().getHeight();
		int middleY = (upperY + lowerY) / 2;
		
		// Swipe from element to element
		new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(from)).withDuration(Duration.ofSeconds(2))).moveTo(PointOption.point(middleX, middleY)).release().perform();
	}
	
	// Swipe from one element to coordinates
	public static void Swipe(MobileElement from, Integer X, Integer Y)
	{
		new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(from)).withDuration(Duration.ofSeconds(2))).moveTo(PointOption.point(X, Y)).release().perform();
	}
	
	// Swipe from one Coordinate to the other
	public static void Swipe(Integer fromX, Integer fromY, Integer toX, Integer toY)
	{
		new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withPosition(PointOption.point(fromX, fromY)).withDuration(Duration.ofSeconds(2))).moveTo(PointOption.point(toX, toY)).release().perform();
	}
	
	// Simple Drag and Drop from element, to element
	public static void DragAndDrop(MobileElement Origin, MobileElement Destination)
	{
		// Calculate middle coordinates of element 
		// X coordinate
		int leftX = Destination.getLocation().getX();
		int rightX = leftX + Destination.getSize().getWidth();
		int xOffset = (rightX + leftX) / 2;
		
		// Y coordinate
		int upperY = Destination.getLocation().getY();
		int lowerY = upperY + Destination.getSize().getHeight();
		int yOffset = (upperY + lowerY) / 2;
		
		new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(Origin))).moveTo(PointOption.point(xOffset, yOffset)).release().perform();
	}
	
	// Drag and Drop element into coordinate
	public static void DragAndDrop(MobileElement Origin, Integer X, Integer Y)
	{		
		new TouchAction<>(driver).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(Origin))).moveTo(PointOption.point(X, Y)).release().perform();
	}
	
	// Set the value into whell dropdown components
	public static void setWhellValue(MobileElement element, String value)
	{
		element.sendKeys(value);
	}
	
	// Android Scroll until element shows up
	@SuppressWarnings("rawtypes")
	public static void ScrollUntil(String attribute, String Value)
	{
		((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + attribute + "(\"" + Value + "\"))");
	}
	
	// Default Scroll
	@SuppressWarnings("unchecked")
	public static void ScrollUntil(boolean down, MobileElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		@SuppressWarnings("rawtypes")
		HashMap scrollObject = new HashMap<>();
		scrollObject.put("scrollObject", ((RemoteWebElement) element).getId());
		if(down)
		{
			scrollObject.put("direction", "down");
		}
		else
		{
			scrollObject.put("direction", "up");
		}
		
		js.executeScript("mobile: scroll", scrollObject);
	}
	
	/************************ HYBRID APPS COMMANDS **********************************/
	// return all contexts if there more than one, for example
	// 1 native   1 Web
	public static Set<String> GetCurrentContexts()
	{
		return driver.getContextHandles();
	}
	
	// Switch to different context into hybrid apps, the Context Handle needs
	// to be the exactly string returned into one of the strings from the function GetContextHandles
	public static void SwitchToContext(String ContextHandle)
	{
		driver.context(ContextHandle);
	}
	/************************ GET COMMANDS **********************************/
	// get text of an element
	public static String GetContent(MobileElement element)
	{
		return element.getText();
	}
	
	
	// return if it's a Native_APP only, Hybrid APP or Web view App
	public static String GetContext()
	{
		return driver.getContext();
	}
	
	// Return the orientation of the screen
	public static String GetOrientation()
	{
		return driver.getOrientation().toString();
	}
	
	private static String getDeviceSerialNumber(String device) {
		Scanner scanner = null;
		try {
			device = device.trim().toLowerCase();
			String os = System.getProperty("os.name");
			
			if (os.startsWith("Windows"))
				scanner = new Scanner(Runtime.getRuntime().exec("cmd /c adb get-serialno").getInputStream());
			else if (os.startsWith("Mac") && device.equalsIgnoreCase("android"))
				scanner = new Scanner(Runtime.getRuntime().exec(new String[] {"/bin/bash", "-l", "-c", "adb get-serialno"}).getInputStream());
			else 
				scanner = new Scanner(Runtime.getRuntime().exec(new String[] {"/bin/bash", "-l", "-c", "idevice_id -l"}).getInputStream());
			
		} catch (IOException e) {
			System.err.println("Erro ao obter o serial number do dispositivo");
		}
		String deviceSerialNumver = (scanner != null && scanner.hasNext()) ? scanner.next() : "";
		return deviceSerialNumver;
	}
	
	/************************ EVIDENCE METHODS **********************************/
	/********************************* EVIDENCE COMMANDS ***************************************/
	// Create evidence with the default Header
	public static void createEvidence(String TestCaseName, String Objective, String ExpectedResult, String Environment) throws MalformedURLException, DocumentException, IOException
	{
		// Log4j settings
		Log.startTestCase(TestCaseName);
		Log("Creating test evidence...");
		PDFCreator.createPDF(
			readConfig("Project"),
			TestCaseName,
			Objective,
			Environment,
			readConfig("Sprint"),
			Constants.PROJECT_ED, 
			ExpectedResult
		);
	}
	
	public static void ExceptionThrown(String ErrorToLog) throws Exception
	{
		PDFCreator.logFatal(ErrorToLog);		
		throw new Exception(ErrorToLog);
	}
	
	// Add Step into Evidence
	public static void addStep(String Step) throws DocumentException
	{
		PDFCreator.addStep(Step);
	}
	
	// Takes Screenshot and Add into PDF Evidence
	public static void TakeScreenshot() throws IOException, DocumentException
	{
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		BufferedImage screenshot = ImageIO.read(scr);
		PDFCreator.addMobileScreenshot(screenshot);
	}
	
	// Close PDF Evidence
	public static void FinishEvidence(String TestName)
	{
		PDFCreator.quitPDF();
		Log.endTestCase(TestName);
	}
	
	/********************************* LOG COMMANDS ***************************************/
	public static void Log(String Message)
	{
		Constants.logger.error(Message);
	}
}
