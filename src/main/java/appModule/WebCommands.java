package appModule;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.DocumentException;

import frameworkProperties.Config;
import utility.Constants;
import utility.ExcelUtils;
import utility.Log;
import utility.PDFCreator;

public class WebCommands extends Config{
	
	public static WebDriver driver = null;
/************************************ START WEB TEST ******************************************/
	@BeforeClass
	// read config file
	public static void start() throws Exception
	{
		switch(readConfig("Environment"))
		{
			case "CHROME":
				// Set wich browser the framework should use
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				WebCommands.driver = new ChromeDriver();
				WebCommands.driver.manage().window().maximize();
			break;
			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				WebCommands.driver = new FirefoxDriver();
				WebCommands.driver.manage().window().maximize();
			break;
			default:
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
				WebCommands.driver = new InternetExplorerDriver();
				WebCommands.driver.manage().window().maximize();
			break;
		}
		WebCommands.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Open Excel File
		ExcelUtils.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, "Plan1");
		Log("Abrindo arquivo Excel");
	}
	
/*********************************** WEB COMMANDS **********************************************/
/********************************* ACTION COMMANDS ********************************************/
	// Clica em button
	public void Click(WebElement element)
	{
		element.click();
	}
	
	// Click by link text
	public void ClickByLinkText(String Link)
	{
		driver.findElement(By.linkText(Link)).click();
	}
	
	// Insert Data into a text field
	public static void InsertDataIntoField(WebElement field, String Text)
	{
		field.sendKeys(Text);
	}
	
	// Mark check box or radio buttons
	public static void Marklement(WebElement field)
	{
		field.click();
	}
	
	// Select element by visible text
	public static void ComboSelectByVisibleText(WebElement field, String Value)
	{
		// Construct select element
		Select combo = new Select(field);
		
		// Select by visible text
		combo.selectByVisibleText(Value); 
	}
	
	// Select element by the html value property
	public static void ComboSelectByHtmlValue(WebElement field, String Value)
	{
		// Construct select element
		Select combo = new Select(field);

		// Select by html combo value
		combo.selectByValue(Value); 
	}
	
	// Select element by the html select order
	public static void ComboSelectByIndex(WebElement field, Integer Value)
	{
		// Construct select element
		Select combo = new Select(field);
		
		// Select by index value of combo box
		combo.selectByIndex(Value); 
	}
/********************************* EVIDENCE COMMANDS ***************************************/
	// Create evidence with the default Header
	public static void createEvidence(String TestCaseName, String Objective, String ExpectedResult) throws MalformedURLException, DocumentException, IOException
	{
		// Log4j settings
		Log.startTestCase(TestCaseName);
		Log("Creating test evidence...");
		PDFCreator.createPDF(
			readConfig("Project"),
			TestCaseName,
			Objective,
			readConfig("Environment"),
			readConfig("Sprint"),
			Constants.PROJECT_ED, 
			ExpectedResult
		);
	}
	
	public static void ExceptionThrown(String ErrorToLog) throws DocumentException
	{
		PDFCreator.logFatal(ErrorToLog);
	}
	
	// Add Step into Evidence
	public static void addStep(String Step) throws DocumentException
	{
		PDFCreator.addStep(Step);
	}
	
	// Take Screenshot
	public static void TakeScreenshot() throws IOException, DocumentException
	{
		BufferedImage scrFile = null;
		try {
			scrFile = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		PDFCreator.addScreenshot(scrFile);	
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
/********************************* GET COMMANDS ***********************************************/
	// Get Field Content
	public static String GetFieldValue(WebElement field)
	{
		return field.getAttribute("value");
	}
	
	// Get element text
	public String GetText(WebElement Field)
	{
		return Field.getText();
	}
	
	// check if radio or checkbox is checked
	public static Boolean IsCheck(WebElement field)
	{
		return field.isSelected();
	}
	
	// Get Value selected at the combo box
	public String getSelectvalue(WebElement Field)
	{
		// Construct select element
		Select combo = new Select(Field);
		// return text
		return combo.getFirstSelectedOption().getText();
	}
	
	// Get amount of selected items
	public Integer getAmountofSelections(WebElement Field)
	{
		// Construct select element
		Select combo = new Select(Field);
		// Verify how many items has been selected
		List<WebElement> allSellectedOptions = combo.getAllSelectedOptions();
		return allSellectedOptions.size();
	}
	
/************************************ JS COMMANDS *********************************************/
	// Execute JS command
	public static Object ExecuteJS(String cmd, Object... param)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}
	
	// Accept JS window
	public static void AcceptJSWindow()
	{
		// Change focus to alert JS window
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	// Dismiss JS window
	public static void DismissJSWindow()
	{
		// Change focus to alert JS window
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	// Send keys into JS Alert Field
	public static void SendKeysJSWindow(String content)
	{
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(content);
	}
	
	// Get JS Window Message
	public static String GetJSWindowMessage()
	{
		return driver.switchTo().alert().getText();
	}
	
/************************************ WAIT COMMANDS *******************************************/
	// Simple wait command
	public static void SimpleWait(Integer miliseconds) throws InterruptedException
	{
		Thread.sleep(miliseconds);
	}
	
	// Wait until element shows up using locator for waiting element
	public static WebElement WaitUntilElementShowsUp(By field)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.presenceOfElementLocated(field));
	}
	
/************************************ END WEB TEST *******************************************/
	@AfterClass
	public static void close() throws IOException
	{
		ExcelUtils.closeExcel(Constants.FILE_PATH + Constants.FILE_NAME);
		driver.quit();
	}
}
