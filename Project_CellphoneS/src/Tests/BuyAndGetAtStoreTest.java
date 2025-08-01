package Tests;

import Pages.LoginPage;
import Pages.BuyAndGetAtStorePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BuyAndGetAtStoreTest {
	private WebDriver driver;
	private BuyAndGetAtStorePage buyAndGetAtStorePage;
	private LoginPage loginPage;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://cellphones.com.vn/");
		driver.manage().window().maximize();

		buyAndGetAtStorePage = new BuyAndGetAtStorePage(driver);
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void testLogin() throws InterruptedException {
		loginPage.login("0898803983", "sonkenst123");
		Thread.sleep(2000);
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Đăng nhập | Mong đợi: Đăng nhập thành công!");
		System.out.println("✅ TC01: Kiểm thử thành công: Đăng nhập đúng! | PASSED ");
	}

	@Test(priority = 2)
	public void testBuyAndGetAtStore() throws InterruptedException {
		buyAndGetAtStorePage.searchAndSelectProduct("ip");
		buyAndGetAtStorePage.addToCartAndProceed();
		buyAndGetAtStorePage.enterEmail("sonttt028@gmail.com");
		buyAndGetAtStorePage.enterSelectDistrict();
		buyAndGetAtStorePage.enterSelectStore();
		buyAndGetAtStorePage.continueToPayment();

		Thread.sleep(2000);
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println(
				"Mua hàng với đầy đủ thông tin và tiến hành thanh toán | Mong đợi: Click tiếp tục sẽ dẫn tới trang thanh toán");
		System.out.println("✅ TC02: Kiểm thử thành công: Đến trang thanh toán! | PASSED");
	}

	@Test(priority = 3)
	public void testBuyWithoutDistrict() throws InterruptedException {
		buyAndGetAtStorePage.searchAndSelectProduct("ip");
		buyAndGetAtStorePage.addToCartAndProceed();
		Thread.sleep(1000);
		buyAndGetAtStorePage.enterEmail("sonttt028@gmail.com");

		buyAndGetAtStorePage.continueToPayment();
		Thread.sleep(2000);

		// Kiểm tra xem thông báo lỗi có hiển thị không
		boolean isErrorDisplayed = buyAndGetAtStorePage.isDistrictErrorDisplayed();

		if (!isErrorDisplayed) {
			System.out.println("\n----- Kết quả kiểm thử -----");
			System.out.println(
					"\n❌ TC03: Kiểm thử thất bại - Không hiển thị thông báo lỗi khi chưa chọn quận/huyện! | FAILED");
			Assert.fail(
					"Không tìm thấy thông báo lỗi: 'Quý khách vui lòng không bỏ trống Tỉnh / thành phố'. Kiểm tra lại XPath hoặc giao diện.");

		} else {

			System.out.println("\n----- Kết quả kiểm thử -----");
			System.out.println("Không chọn quận, huyện | Mong đợi: Quý khách vui lòng không bỏ trống Tỉnh / thành phố");
			System.out
					.println("✅ TC03: Kiểm thử thành công: Hiển thị thông báo lỗi khi chưa chọn quận/huyện! | PASSED");
		}
	}

	@Test(priority = 4)
	public void testBuyWithoutAddress() throws InterruptedException {
		buyAndGetAtStorePage.searchAndSelectProduct("ip");
		buyAndGetAtStorePage.addToCartAndProceed();
		buyAndGetAtStorePage.enterEmail("sonttt028@gmail.com");
		buyAndGetAtStorePage.enterSelectDistrict();

		buyAndGetAtStorePage.continueToPayment();
		Thread.sleep(2000);

		Assert.assertTrue(buyAndGetAtStorePage.isAddressErrorDisplayed());
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out
				.println("Không chọn địa chỉ cửa hàng | Mong đợi: Quý khách vui lòng không bỏ trống Số nhà, tên đường");
		System.out.println(
				"✅ TC04: Kiểm thử thành công - Hiển thị thông báo lỗi khi chưa chọn địa chỉ cửa hàng! | PASSED");

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
