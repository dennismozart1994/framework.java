package testScenarios;

import java.net.MalformedURLException;

import org.junit.Test;

import appModule.MobileCommands;

public class MobileTestExample extends MobileCommands{

	@Test
	public void FindElementSimple() throws MalformedURLException, InterruptedException
	{
		LaunchLocalApp("ApiDemos-debug.apk");
		findByXpath("//android.widget.TextView[@text='Preference']").click();
		findByXpath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		findById("android:id/checkbox").click();
		findByXpath("(//android.widget.RelativeLayout)[2]").click();
		findByClassName("android.widget.EditText").sendKeys("hello");
		driver.findElementsByClassName("android.widget.Button").get(1).click();
	}
	
	@Test
	public void FindElementByUIAutomator() throws MalformedURLException, InterruptedException
	{
		LaunchLocalApp("Android8", "ApiDemos-debug.apk");
		findByUIAutomator("text", "Views").click();
	}
	
	@Test
	public void TapGesture() throws MalformedURLException
	{
		LaunchLocalApp("Android8", "ApiDemos-debug.apk");
		findByXpath("//android.widget.TextView[@text='Views']").click();
		Tap(findByXpath("//android.widget.TextView[@text='Expandable Lists']"));
	}
	
	@Test
	public void LongPressGesture() throws MalformedURLException, InterruptedException
	{
		LaunchLocalApp("ApiDemos-debug.apk");
		findByXpath("//android.widget.TextView[@text='Views']").click();
		Tap(findByXpath("//android.widget.TextView[@text='Expandable Lists']"));
		Tap(findByXpath("//android.widget.TextView[@text='1. Custom Adapter']"));
		LongPress(findByXpath("//android.widget.TextView[@text='People Names']"), 3);
	}
	
	@Test
	public void SwipeFromElementToElement() throws MalformedURLException, InterruptedException
	{
		LaunchLocalApp("ApiDemos-debug.apk");
		findByXpath("//android.widget.TextView[@text='Views']").click();
		Tap(findByXpath("//android.widget.TextView[@text='Date Widgets']"));
		Tap(findByUIAutomator("text", "2. Inline"));
		Tap(findByXpath("//*[@content-desc='9']"));
		Swipe(findByXpath("//*[@content-desc='15']"), findByXpath("//*[@content-desc='45']"));
	}
	
	@Test
	public void Scroll() throws MalformedURLException
	{
		LaunchLocalApp("ApiDemos-debug.apk");
		findByXpath("//android.widget.TextView[@text='Views']").click();
		AndroidScrollUntil("text", "WebView");
	}
	
	@Test
	public void DragAndDropTest() throws MalformedURLException
	{
		LaunchLocalApp("ApiDemos-debug.apk");
		findByXpath("//android.widget.TextView[@text='Views']").click();
		findByXpath("//android.widget.TextView[@text='Drag and Drop']").click();
		DragAndDrop(driver.findElementsByClassName("android.view.View").get(0), driver.findElementsByClassName("android.view.View").get(2));
	}
}
