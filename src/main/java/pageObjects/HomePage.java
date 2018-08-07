package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	// public static WebDriver driver;
	private WebDriver driver;
	
	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	// simples find element
	public WebElement findMain() throws Exception {
		return driver.findElement(By.id("main-content"));
	}
}
