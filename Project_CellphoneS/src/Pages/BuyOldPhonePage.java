package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BuyOldPhonePage {
	private WebDriver driver;
	private WebDriverWait wait;

	public BuyOldPhonePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// locators
	private By selectOldProduct = By.xpath("//*[@id=\"menu-main\"]/div/div[1]/div[10]/a");
	private By selectProduct = By.xpath("//*[@id=\"pinCate-379\"]/div[3]/div/div/div[1]/div[2]/div/div[1]/a");
	private By buyButton = By.xpath("//*[@id=\"productDetailV2\"]/section/div[2]/div[2]/div[6]/div/div[1]/button");

	private By dropdownCity = By.xpath("//*[@id=\"vs1__combobox\"]/div[1]/input");
	private By optionCity = By.xpath("//span[contains(text(), 'Hồ Chí Minh')]");
	private By dropdownDistrict = By.xpath("//*[@id=\"vs2__combobox\"]/div[1]/input");
	private By optionDistrict = By.xpath("//span[contains(text(), 'Quận 1')]");

	private By coutinueButton = By
			.xpath("//*[@id=\"productDetailV2\"]/div[6]/div[2]/div/div[2]/div[2]/div/div[2]/div[2]/div[3]/div[2]");

	// actions
	public void processBuyOldPhone() throws InterruptedException {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(selectOldProduct));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		wait.until(ExpectedConditions.elementToBeClickable(selectProduct)).click();
		wait.until(ExpectedConditions.elementToBeClickable(buyButton)).click();
	}

	public void enterCity() throws InterruptedException {
		// Kiểm tra xem có modal overlay không
		try {
			WebElement overlay = driver.findElement(By.className("modal-overlay"));
			if (overlay.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", overlay);
				Thread.sleep(500); // Đợi modal biến mất
			}
		} catch (NoSuchElementException e) {
			// Nếu không tìm thấy overlay thì tiếp tục
		}

		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownCity));
		dropdown.click();
		Thread.sleep(500);

		WebElement cityOption = wait.until(ExpectedConditions.visibilityOfElementLocated(optionCity));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityOption);
		Thread.sleep(300);

		// Sử dụng JavaScript click để tránh lỗi click bị chặn
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cityOption);
	}

	public void enterDistrict() throws InterruptedException {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownDistrict));

		// Click mở dropdown
		dropdown.click();
		Thread.sleep(500); // Chờ dropdown mở ra

		// Cuộn xuống để hiển thị danh sách nếu cần
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdown);
		Thread.sleep(500);

		// Lấy danh sách các quận
		List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(optionDistrict));

		boolean found = false;
		for (WebElement option : options) {
			if (option.getText().trim().equals("Quận 1")) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
				Thread.sleep(300);
				option.click();
				found = true;
				break;
			}
		}

		if (!found) {
			throw new NoSuchElementException("Không tìm thấy tùy chọn Quận 1");
		}
	}

	public void coutinue() {
		wait.until(ExpectedConditions.elementToBeClickable(coutinueButton)).click();
	}

}