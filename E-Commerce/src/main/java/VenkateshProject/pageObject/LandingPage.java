package VenkateshProject.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VenkateshProject.AbstractComponents.Abstract;

public class LandingPage extends Abstract{
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginClick;
	
	public ProductPage LoginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginClick.click();
		ProductPage productPage = new ProductPage(driver);
		return productPage;
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
