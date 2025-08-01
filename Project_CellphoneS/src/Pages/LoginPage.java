package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void navigateToLoginPage() {
		driver.navigate().to("https://cellphones.com.vn/");
	}

	public void login(String username, String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("// *[@id=\"cpsHeader\"]/div[3]/nav/div[4]")))
				.click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"modalLogin\"]/div[2]/div[3]/div[2]\r\n" + ""))).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"login-form\"]/div[3]/form/div/div[1]/div/input")))
				.sendKeys("0898803983");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"login-form\"]/div[3]/form/div/div[2]/div/input")))
				.sendKeys("sonkenst123");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login-form\"]/div[3]/form/button")))
				.click();

	}
}
