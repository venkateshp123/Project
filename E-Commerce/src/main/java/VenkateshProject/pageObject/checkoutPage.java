package VenkateshProject.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import VenkateshProject.AbstractComponents.Abstract;

public class checkoutPage extends Abstract {
	WebDriver driver;

	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = ".ta-item:nth-child(3)")
	WebElement selectCountry;
	
	@FindBy(css = "a[class*='action__submit']")
	WebElement submit;

	By countriesBy = By.cssSelector(".ta-results");
	By submitBy = By.cssSelector("a[class*='action__submit']");

	public void selectCountry(String Country) {
		country.sendKeys(Country);
		waitForElementToAppear(countriesBy);
		selectCountry.click();	
	}

	public confirmationPage submitOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)", "");
		waitForElementToAppear(submitBy);
		Actions actions = new Actions(driver);
		actions.moveToElement(submit).click().build().perform();
		confirmationPage confirmationpage = new confirmationPage(driver);
		return confirmationpage;
	}
	
	
}
