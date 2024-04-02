package VenkateshProject;

import java.io.IOException;
import org.testng.annotations.Test;
import VenkateshProject.TestComponents.BaseTest;
import VenkateshProject.pageObject.ProductPage;
import VenkateshProject.pageObject.cartPage;
import VenkateshProject.pageObject.checkoutPage;
import VenkateshProject.pageObject.confirmationPage;
import junit.framework.Assert;

public class SubmitOrderTest extends BaseTest{

	@Test
	public void submitOrder() throws IOException {

		String ProductName = "ZARA COAT 3";
		String Country = "India";
		String email = "abc8149@gmail.com";
		String password = "Abc@123456";
		String expectedText = "THANKYOU FOR THE ORDER.";
		
		ProductPage productPage = landingpage.LoginApplication(email, password);

		productPage.addProductToCart(ProductName);
		cartPage cartpage = productPage.goToCartPage();

		Boolean match = cartpage.verifyProductDisplay(ProductName);
		Assert.assertTrue(match);
		checkoutPage checkoutpage = cartpage.goToCheckoutPage();

		checkoutpage.selectCountry(Country);
		confirmationPage confirmationpage = checkoutpage.submitOrder();
		String actualText = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(actualText.equalsIgnoreCase(expectedText));

	}
}
