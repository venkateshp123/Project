package VenkateshProject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import VenkateshProject.AbstractComponents.Abstract;

public class confirmationPage extends Abstract {
	WebDriver driver;

	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary]")
	WebElement actualText;

	By countriesBy = By.cssSelector(".ta-results");
	
	public String getConfirmationMessage() {
		return actualText.getText();
	}
	
	
}
