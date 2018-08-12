package appModule;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class MobileCommands {
	public static AndroidDriver<MobileElement> driver = null;
	
	/************************ LAUNCH APPS METHODS **********************************/
	// Android Real Devices Launch
	public static AndroidDriver<MobileElement> LaunchLocalApp(String App) throws MalformedURLException
	{
		File f = new File("apps");
		File fs = new File(f, App);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static AndroidDriver<MobileElement> LaunchLocalApp(String PackageName, String Activity) throws MalformedURLException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PackageName);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Activity);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");	
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	// Android Emulator Launch
	public static AndroidDriver<MobileElement> LaunchLocalApp(CharSequence Emulator, String App) throws MalformedURLException
	{
		File f = new File("apps");
		File fs = new File(f, App);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, Emulator);
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static AndroidDriver<MobileElement> LaunchLocalApp(CharSequence Emulator, String PackageName, String Activity) throws MalformedURLException
	{
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, Emulator);	
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PackageName);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Activity);
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	/************************ FIND ELEMENTS METHODS 
	 * @throws InterruptedException **********************************/
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
	public static void AndroidScrollUntil(String attribute, String Value)
	{
		((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" + attribute + "(\"" + Value + "\"))");
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
}
