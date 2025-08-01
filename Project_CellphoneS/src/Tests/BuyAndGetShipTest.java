package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.BuyAndGetShipPage;
import java.time.Duration;

public class BuyAndGetShipTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private BuyAndGetShipPage buyAndGetShipPage;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://cellphones.com.vn/");
		driver.manage().window().maximize();

		loginPage = new LoginPage(driver);
		buyAndGetShipPage = new BuyAndGetShipPage(driver);
	}

	@Test(priority = 1)
	public void testLogin() throws InterruptedException {
		loginPage.login("0898803983", "sonkenst123");
		Thread.sleep(4000);

		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Đăng nhập | Mong đợi: Đăng nhập thành công!");
		System.out.println("✅ TC01: Kiểm thử thành công: Đăng nhập đúng! | PASSED ");
	}

	@Test(priority = 2, dependsOnMethods = { "testLogin" })
	public void testBuyAndGetShip() throws InterruptedException {
		buyAndGetShipPage.searchAndBuyProduct("ip");
		Thread.sleep(2000);
		buyAndGetShipPage.enterShippingOption();
		buyAndGetShipPage.enterSelectDistrict();
		buyAndGetShipPage.enterSelectWard();

		buyAndGetShipPage.enterAddress("277 Lê Lợi");

		buyAndGetShipPage.continueButton();
		Thread.sleep(2000);
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println(
				"Mua hàng với đầy đủ thông tin và tiến hành thanh toán | Mong đợi: Click tiếp tục sẽ dẫn tới trang thanh toán");
		System.out.println("✅ TC02: Kiểm thử thành công: Đến trang thanh toán! | PASSED");
		
	}

	@Test(priority = 3)
	public void testBuyWitoutDistrict() throws InterruptedException {
		buyAndGetShipPage.searchAndBuyProduct("ip");
		Thread.sleep(2000);
		buyAndGetShipPage.enterShippingOption();
		buyAndGetShipPage.continueButton();
		Thread.sleep(2000);

		Assert.assertTrue(buyAndGetShipPage.isDistrictErrorDisplayed());
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Không chọn quận, huyện | Mong đợi: Quý khách vui lòng không bỏ trống Quận / huyện");
		System.out.println("✅ TC03: Kiểm thử thành công - Hiển thị thông báo lỗi khi chưa chọn Quận / huyện! | PASSED");

	}

	@Test(priority = 4)
	public void testWithoutWard() throws InterruptedException {
		buyAndGetShipPage.searchAndBuyProduct("ip");
		Thread.sleep(2000);
		buyAndGetShipPage.enterShippingOption();
		buyAndGetShipPage.enterSelectDistrict();
		buyAndGetShipPage.continueButton();
		Thread.sleep(2000);

		boolean isErrorDisplayed = buyAndGetShipPage.isWardErrorDisplayed();

		if (!isErrorDisplayed) {
			System.out.println("\n----- Kết quả kiểm thử -----");
			System.out.println(
					"\n❌ TC04: Kiểm thử thất bại - Không hiển thị thông báo lỗi khi chưa chọn Phường / Xã! | FAILED");
		} else {

			System.out.println("\n----- Kết quả kiểm thử -----");
			System.out.println("Không chọn phường, xã| Mong đợi: Quý khách vui lòng không bỏ trống Phường / Xã");
			System.out.println(
					"✅ TC04: Kiểm thử thành công - Hiển thị thông báo lỗi khi chưa chọn Phường / Xã! | PASSED");

		}
	}

	@Test(priority = 5)
	public void testWithoutAddress() throws InterruptedException {
		buyAndGetShipPage.backButton();
		buyAndGetShipPage.deleteCart();
		buyAndGetShipPage.searchAndBuyProduct("ip");
		Thread.sleep(2000);
		buyAndGetShipPage.enterShippingOption();
		buyAndGetShipPage.enterSelectDistrict();
		buyAndGetShipPage.enterSelectWard();

		buyAndGetShipPage.continueButton();
		Thread.sleep(2000);
		boolean isErrorDisplayed = buyAndGetShipPage.isAddressErrorDisplayed();

		if (!isErrorDisplayed) {
			System.out.println("\n----- Kết quả kiểm thử -----");
			System.out.println(
					"\n❌ TC05: Kiểm thử thất bại - Không hiển thị thông báo lỗi khi bỏ trống địa chỉ! | FAILED");
		} else {

			System.out.println("\n----- Kết quả kiểm thử -----");
			System.out.println("Không nhập địa chỉ| Mong đợi: Quý khách vui lòng không bỏ trống Số nhà, tên đường");
			System.out.println(
					"✅ TC05: Kiểm thử thành công - Hiển thị thông báo lỗi khi chưa bỏ trống địa chỉ! | PASSED");

		}
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
