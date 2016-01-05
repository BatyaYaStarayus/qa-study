package qa.study.automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.study.automation.data.User;
import qa.study.automation.data.UsersCredentials;

public class Test001 {
	WebDriver driver;
	String baseUrl;

	@BeforeMethod
	public void SetUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.work.ua/jobseeker";
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void TearDown() throws Exception {
		driver.quit();
	}

	@DataProvider
	public Object[][] user() {
		return new  Object[][] {{(UsersCredentials.getTestUser())}};
	}
	
	@Test(dataProvider = "user")
	public void loginTest(User user) {		
		driver.get(baseUrl + "/login/");
		
		WebElement emailField = driver.findElement(By.xpath("//input[@id = 'email']"));
		WebElement passField = driver.findElement(By.xpath("//input[@id = 'password']"));
		WebElement loginButton = driver.findElement(By.xpath("//form[@id='lForm']/div[4]/button"));
		
		emailField.clear();
		emailField.sendKeys(user.getLoginName());
		passField.clear();
		passField.sendKeys(user.getPassword());
		loginButton.click();
		
		WebElement alert = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'alert')]")));
		
		Assert.assertTrue(alert.isDisplayed());
	}	
}
