package appCore;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.DocumentException;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.KeyEventFlag;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
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
	public static AndroidDriver<MobileElement> driver = null;
	
	/************************ LAUNCH APPS METHODS **********************************/
	// Android Browser App Launch
		public static AndroidDriver<MobileElement> LaunchLocalApp(String DeviceName, CharSequence Browser) throws DocumentException, IOException
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
			cap.setCapability(MobileCapabilityType.UDID, getDeviceSerialNumber(DeviceName));
			cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
			cap.setCapability(MobileCapabilityType.BROWSER_NAME, Browser);
			
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			addStep("Open App: ");
			TakeScreenshot();
			
			return driver;
		}
	
	// Android Local App Launch
	public static AndroidDriver<MobileElement> LaunchLocalApp(String DeviceName, String App) throws MalformedURLException, DocumentException, IOException
	{
		File f = new File("apps");
		File fs = new File(f, App);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		cap.setCapability(MobileCapabilityType.UDID, getDeviceSerialNumber(DeviceName));
		cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		addStep("Open App: ");
		TakeScreenshot();
		
		return driver;
	}
	// Android App already installed Launch
	public static AndroidDriver<MobileElement> LaunchLocalApp(String DeviceName, String PackageName, String Activity) throws MalformedURLException, DocumentException, IOException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
		cap.setCapability(MobileCapabilityType.UDID, getDeviceSerialNumber(DeviceName));
		cap.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PackageName);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Activity);
				
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
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
		driver.pressKey(new KeyEvent(AndroidKey.BACK).
				withFlag(KeyEventFlag.SOFT_KEYBOARD).
				withFlag(KeyEventFlag.EDITOR_ACTION));
	}
	// Tap home Android Button
	public static void TapHome()
	{
		driver.pressKey(new KeyEvent(AndroidKey.HOME).
				withFlag(KeyEventFlag.SOFT_KEYBOARD).
				withFlag(KeyEventFlag.EDITOR_ACTION));
	}
	// Tap the square that opens all the apps opened into the phone
	public static void TapSwitch()
	{
		driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH).
				withFlag(KeyEventFlag.SOFT_KEYBOARD).
				withFlag(KeyEventFlag.EDITOR_ACTION));
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
	
	// Android Scroll until element shows up
	@SuppressWarnings("rawtypes")
	public static void ScrollUntil(String attribute, String Value)
	{
		((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + attribute + "(\"" + Value + "\"))");
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
	
	public static void ExceptionThrown(String ErrorToLog, Integer ExcelFileLine) throws Exception
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
