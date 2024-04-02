package VenkateshProject.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VenkateshProject.AbstractComponents.Abstract;

public class cartPage extends Abstract {
	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".items h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".ng-animating")
	WebElement animation;
	
	@FindBy(css = ".totalRow button")
	WebElement checkout;

	By productsBy = By.cssSelector(".items h3");

	public List<WebElement> getCartProductList() {
		waitForElementToAppear(productsBy);
		return cartProducts;
	}

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}

	
	public checkoutPage goToCheckoutPage() {
		checkout.click();
		checkoutPage checkoutpage = new checkoutPage(driver);
		return checkoutpage;
	}
}
