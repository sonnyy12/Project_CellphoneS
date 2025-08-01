package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BuyAndGetShipPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	// Constructor
	public BuyAndGetShipPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.js = (JavascriptExecutor) driver;
	}

	// Locators
	private By searchBox = By.xpath("//*[@id='inp$earch']");
	private By firstResult = By.xpath("//*[@id='search_autocomplete']/div[2]/a[1]");
	private By addToCartButton = By
			.xpath("//*[@id=\"productDetailV2\"]/section/div[2]/div[2]/div[6]/div/div[1]/button[1]");
	private By viewCartButton = By.xpath("//*[@id=\"stickyBottomBar\"]/button");
	private By shippingOption = By.id("shipping");
	private By districtDropdown = By
			.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]");
	private By districtOption = By.xpath("//span[contains(text(), 'Quận 1')]");
	private By wardDropdown = By
			.xpath("//*[@id=\"__layout\"]/div/div[2]/div[2]/div/div[4]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]");
	private By wardOption = By.xpath("//span[contains(text(), 'Phường Cô Giang')]");
	private By addressInput = By.xpath(
			"//*[@id=\"__layout\"]/div/div[2]/div[2]/div/div[4]/div[1]/div[2]/div[1]/div[2]/div[2]/div[2]/input");
	private By continueButton = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[4]/div[1]/div[2]/button");
	private By backButton = By.xpath("//*[@id=\"__layout\"]/div/div[2]/div[1]/div/a");
	private By deleteCart = By.xpath("//*[@id=\"listItemSuperCart\"]/div[2]/div[1]/div[2]/div[1]/button");

	// Methods
	public void searchAndBuyProduct(String productName) {
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
		search.sendKeys(productName);
		wait.until(ExpectedConditions.elementToBeClickable(firstResult)).click();
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
	}

	public void enterShippingOption() {
		WebElement shippingRadio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("shipping")));

		js.executeScript("arguments[0].scrollIntoView(true);", shippingRadio);

		try {

			wait.until(ExpectedConditions.elementToBeClickable(shippingRadio)).click();
		} catch (Exception e) {

			js.executeScript("arguments[0].click();", shippingRadio);
		}
	}

	public void enterSelectDistrict() {
		wait.until(ExpectedConditions.elementToBeClickable(districtDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(districtOption)).click();
	}

	public void enterSelectWard() {
		wait.until(ExpectedConditions.elementToBeClickable(wardDropdown)).click();
		wait.until(ExpectedConditions.elementToBeClickable(wardOption)).click();
	}

	public void enterAddress(String address) {
		WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput));

		// Cuộn xuống phần tử
		js.executeScript("arguments[0].scrollIntoView(true);", addressField);

		// Loại bỏ readonly nếu có
		js.executeScript("arguments[0].removeAttribute('readonly');", addressField);

		// Nhập dữ liệu bằng JavaScript
		js.executeScript("arguments[0].value = arguments[1];", addressField, address);

		// Kích hoạt sự kiện input & change để cập nhật giao diện
		js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", addressField);
		js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", addressField);
	}

	public void continueButton() {
		wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}

	public void backButton() {
		wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
	}

	public void deleteCart() {
		wait.until(ExpectedConditions.elementToBeClickable(deleteCart)).click();
	}

	public boolean isDistrictErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Quý khách vui lòng không bỏ trống Quận / huyện')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Quý khách vui lòng không bỏ trống Quận / huyện")) {
				return true;
			}
		}
		return false;
	}

	public boolean isWardErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Quý khách vui lòng không bỏ trống Phường / Xã')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Quý khách vui lòng không bỏ trống Phường / Xã")) {
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
