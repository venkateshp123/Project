package VenkateshProject;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import VenkateshProject.pageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class StandaloneTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		String ProductName = "ZARA COAT 3";
		String Country="India";
		String CVV="143";
		String Name="Venkatesh Sanganwar";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingpage =new LandingPage(driver);
		
		
		driver.findElement(By.id("userEmail")).sendKeys("abc8149@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abc@123456");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-child")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".items h3"));
		Boolean match = cartProduct.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(CVV);
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(Name);
		//driver.findElement(By.name("coupon")).sendKeys("rahulshettyacademy");
		//driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class*=ng-star-inserted]")));
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(Country);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
		

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		driver.findElement(By.cssSelector(".details__user a[class*='action__submit']")).click();
		
		String actualText=driver.findElement(By.cssSelector(".hero-primary")).getText();
		String expectedText="THANKYOU FOR THE ORDER.";
		
		Assert.assertEquals(expectedText, actualText);
	

	}
}
