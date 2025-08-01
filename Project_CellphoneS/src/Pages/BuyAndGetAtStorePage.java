package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BuyAndGetAtStorePage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public BuyAndGetAtStorePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Locators
	private By searchBox = By.xpath("//*[@id='inp$earch']");
	private By firstProductResult = By.xpath("//*[@id='search_autocomplete']/div[2]/a[1]");
	private By addToCartButton = By
			.xpath("//*[@id='productDetailV2']/section/div[2]/div[2]/div[6]/div/div[1]/button[1]");
	private By proceedToCheckoutButton = By.xpath("//*[@id='stickyBottomBar']/button");
	private By emailInput = By.xpath("//*[@id='__layout']/div/div[2]/div[2]/div/div[3]/div/div/div[2]/div/input");
	private By districtDropdown = By
			.xpath("//*[@id='__layout']/div/div[2]/div[2]/div/div[4]/div[1]/div[2]/div[1]/div[1]/div[2]/input");
	private By districtOption = By.xpath("//span[contains(text(), 'Quận 1')]");
	private By storeDropdown = By
			.xpath("//*[@id='__layout']/div/div[2]/div[2]/div/div[4]/div[1]/div[2]/div[1]/div[2]/div/input");
	private By storeOption = By.xpath("//span[contains(text(), 'Quận 1')]");
	private By continueToPaymentButton = By.xpath("//*[@id='__layout']/div/div[2]/div[4]/div[1]/div[2]/button");

	// Actions
	public void searchAndSelectProduct(String productName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(productName);
		WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(firstProductResult));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
		wait.until(ExpectedConditions.elementToBeClickable(product)).click();
	}

	public void addToCartAndProceed() {
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
	}

	public void enterEmail(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
	}

	public void enterSelectDistrict() {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(districtDropdown));
		dropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(districtOption)).click();
	}

	public void enterSelectStore() {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(storeDropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
		dropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(storeOption)).click();
	}

	public void continueToPayment() {
		wait.until(ExpectedConditions.elementToBeClickable(continueToPaymentButton)).click();
	}

	public boolean isDistrictErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Quý khách vui lòng không bỏ trống Tỉnh / thành phố')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Quý khách vui lòng không bỏ trống Tỉnh / thành phố")) {
				return true;
			}
		}
		return false;
	}

	public boolean isAddressErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Quý khách vui lòng không bỏ trống Số nhà, tên đường')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Quý khách vui lòng không bỏ trống Số nhà, tên đường")) {
				return true;
			}
		}
		return false;
	}
}
