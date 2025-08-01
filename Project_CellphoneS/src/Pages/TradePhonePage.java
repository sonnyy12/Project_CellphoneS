package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TradePhonePage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Constructor
	public TradePhonePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Locators
	private By searchBox = By.xpath("//*[@id='inp$earch']");
	private By firstProductResult = By.xpath("//*[@id='search_autocomplete']/div[2]/a[1]");
	private By tradeButton = By.xpath("//*[@id='productDetailV2']/section/div[2]/div[2]/div[6]/div/div[3]/a");
	private By confirmTradeButton = By.xpath("//*[@id='boxProductTrade']/div[4]/div[3]/div[1]");
	private By nextStepButton = By.xpath("//*[@id='condition-of-old-product-modal']/div[2]/div[3]/button");
	private By nameInput = By.xpath("//*[@id='inptName2']");
	private By phoneInput = By.xpath(
			"/html/body/div[1]/div/div/div[3]/div[2]/div[1]/div[6]/div[4]/div[2]/div[2]/div[2]/form/div[1]/div[2]/input");
	private By districtDropdown = By.xpath("//*[@id='optQuan']");
	private By districtOption = By.xpath("//*[@id='optQuan']/option[2]");
	private By streetDropdown = By.xpath("//*[@id='optDuong']");
	private By streetOption = By.xpath("//*[@id='optDuong']/option[2]");
	private By sellButton = By.xpath("//*[@id=\"modal-payment\"]/div[2]/div[4]/button");
	private By close = By.xpath("//*[@id=\"modal-payment\"]/div[2]/div[1]/button");

	// Actions
	public void searchAndSelectProduct(String productName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(productName);
		WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(firstProductResult));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
		wait.until(ExpectedConditions.elementToBeClickable(product)).click();
	}

	public void tradePhoneProcess(String name, String phone) {
		wait.until(ExpectedConditions.elementToBeClickable(tradeButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(confirmTradeButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(nextStepButton)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
		wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput)).sendKeys(phone);
	}

	public void enterDistrict() {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(districtDropdown));
		dropdown.click();
		wait.until(ExpectedConditions.elementToBeClickable(districtOption)).click();
	}

	public void enterAddress() {
		WebElement dropdown1 = wait.until(ExpectedConditions.elementToBeClickable(streetDropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown1);
		dropdown1.click();
		wait.until(ExpectedConditions.elementToBeClickable(streetOption)).click();
	}

	public void sell() {
		wait.until(ExpectedConditions.elementToBeClickable(sellButton)).click();
	}
	public void close() {
		wait.until(ExpectedConditions.elementToBeClickable(close)).click();
	}

	public boolean isNameErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[@id=\"txtErr\"]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Có lỗi: Vui lòng kiểm tra lại họ và tên")) {
				return true;
			}
		}
		return false;
	}

	public boolean isNumberErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Có lỗi: Vui lòng kiểm tra lại số điện thoại')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Có lỗi: Vui lòng kiểm tra lại số điện thoại")) {
				return true;
			}
		}
		return false;
	}

	public boolean isDistrictErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Có lỗi: Vui lòng chọn cửa hàng muốn giao dịch')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Có lỗi: Vui lòng chọn cửa hàng muốn giao dịch")) {
				return true;
			}
		}
		return false;
	}

	public boolean isAddressErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Có lỗi: Vui lòng chọn cửa hàng muốn giao dịch')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Có lỗi: Vui lòng chọn cửa hàng muốn giao dịch")) {
				return true;
			}
		}
		return false;
	}

	public boolean isCaptChaErrorDisplayed() {
		List<WebElement> errors = driver
				.findElements(By.xpath("//*[contains(text(),'Có lỗi: Vui lòng xác thực captcha.')]"));
		for (WebElement error : errors) {
			if (error.getText().trim().equals("Có lỗi: Vui lòng xác thực captcha.")) {
				return true;
			}
		}
		return false;
	}
}
