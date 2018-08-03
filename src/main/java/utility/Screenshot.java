package utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

public class Screenshot {
	public static BufferedImage TakeScreenshot(WebDriver driver, String Name) throws IOException
	{
		// Take Screenshot
		BufferedImage scrFile = null;
		try {
			scrFile = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return scrFile;		
	}
}
