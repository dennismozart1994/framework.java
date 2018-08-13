package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	
	@FindBy(how = How.ID, using = "main-content")
	private WebElement mainContent;
	
	// simples find element
	public WebElement findMain() throws Exception {
		return mainContent;
	}
}
