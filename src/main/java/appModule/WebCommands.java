package appModule;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import frameworkProperties.Config;
import utility.Constants;
import utility.ExcelUtils;

public class WebCommands extends Config{
	
	public static WebDriver driver = null;
	@BeforeClass
	// read config file
	public static void start() throws Exception
	{
		switch(readConfig("Environment"))
		{
			case "CHROME":
				// Seta qual navegador o sistema deve abrir
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
		ExcelUtils.setExcelFile(Constants.PATH + Constants.FILE, "Plan1");
		Constants.logger.trace("Abrindo arquivo Excel");
	}
	
	
	
	@AfterClass
	public static void close() throws IOException
	{
		ExcelUtils.closeExcel();
		driver.quit();
	}
}
