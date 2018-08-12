package appModule;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
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
import utility.TestRail;

public class WebCommands extends Config{
	
	public static WebDriver driver = null;
	public static String SheetName;
	
/************************************ START WEB TEST ******************************************/
	// read config file
	public static void start(String SheetName) throws Exception
	{
		// Set wich browser the framework should use
		switch(readConfig("Browser"))
		{
			case "CHROME":
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
		ExcelUtils.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, SheetName);
		Log("Abrindo arquivo Excel");
		WebCommands.SheetName = SheetName;
	}
	
/*********************************** WEB COMMANDS **********************************************/
/********************************* ACTION COMMANDS ********************************************/
	// Clica em button
	public void Click(WebElement element)
	{
		element.click();
	}
	
	// Click by link text
	public void Click(String Link)
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
	public static void ComboSelect(WebElement field, String Text)
	{
		// Construct select element
		Select combo = new Select(field);
		
		// Select by visible text
		combo.selectByVisibleText(Text); 
	}
	
	// Select element by the html select order
	public static void ComboSelect(WebElement field, Integer Index)
	{
		// Construct select element
		Select combo = new Select(field);
		
		// Select by index value of combo box
		combo.selectByIndex(Index); 
	}
	
	// Select element by the html value property
	public static void ComboSelectByHtmlValue(WebElement field, String Value)
	{
		// Construct select element
		Select combo = new Select(field);

		// Select by html combo value
		combo.selectByValue(Value); 
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
			readConfig("Browser"),
			readConfig("Sprint"),
			Constants.PROJECT_ED, 
			ExpectedResult
		);
	}
	
	public static void ExceptionThrown(String ErrorToLog, Integer ExcelFileLine) throws Exception
	{
		PDFCreator.logFatal(ErrorToLog);
		
		TestRail.AddResult
		(
			Constants.FILE_PATH + Constants.FILE_NAME,
			SheetName,
			ExcelFileLine, 
			Constants.TESTRAIL_FAILED,
			ErrorToLog
		);
		
		throw new Exception(ErrorToLog);
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
	public static void closeExcel() throws IOException
	{
		ExcelUtils.closeExcel(Constants.FILE_PATH + Constants.FILE_NAME);
		driver.quit();
	}
/**************************** ASSERTIONS COMMANDS *************************************/
	public static void ValidateString(Integer RowNum, String expected, String current, String comment) throws Exception
	{
		addStep("Validate:");
		String Comment;
		boolean shouldThrown = false;
		
		try 
		{
			Assert.assertEquals(expected, current);
			Comment = comment;
			shouldThrown = false;
		}
		catch(AssertionError e)
		{
			Comment = "Executado via automação com erro: \n" + e.toString();
			shouldThrown = true;
		}
		
		// send result to Testrail
		if(shouldThrown)
		{
			ExceptionThrown(Comment, RowNum);
		}
		else
		{
			TestRail.AddResult
			(
				Constants.FILE_PATH + Constants.FILE_NAME,
				SheetName, 
				RowNum, 
				Constants.TESTRAIL_PASSED,
				Comment
			);
		}
	}
	
	// check if element is displayed
	public static void PresenceValidation(WebElement element, Integer RowNum, String comment) throws Exception
	{
		addStep("Validate:");
		try {
			Assert.assertTrue(element.isDisplayed());
			Log(comment);
			
			// send result to Testrail
			TestRail.AddResult
			(
				Constants.FILE_PATH + Constants.FILE_NAME,
				SheetName, 
				RowNum, 
				Constants.TESTRAIL_PASSED,
				comment
			);
			
		}catch(Exception e) {
			// add throw declaration into evidence
			Log("Failed - " + e.toString());
			ExceptionThrown("Failed - " + e.toString(), RowNum);
		}
	}
/**************************** TESTRAIL COMMANDS *************************************/
	// shouldTest
	public static boolean ShouldTest(String FileName, Integer RowNum) throws Exception
	{
		// get id
		String Testid = ExcelUtils.getCellData(FileName, SheetName, RowNum, Constants.TEST_ID);
		Integer sub = Integer.parseInt(Testid.substring(1));
		
		// get status
		Integer status = TestRail.GetTestResult(sub);
		
		if(status == Constants.TESTRAIL_PASSED)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
