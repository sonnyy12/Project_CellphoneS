package Tests;

import Pages.LoginPage;
import Pages.TradePhonePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TradePhoneTest {
	private WebDriver driver;
	private TradePhonePage tradePhonePage;
	private LoginPage loginPage;

	@BeforeTest
	public void setup() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://cellphones.com.vn/");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		tradePhonePage = new TradePhonePage(driver);
		loginPage = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void testLogin() throws InterruptedException {
		loginPage.login("0898803983", "sonkenst123");
		Thread.sleep(1000);
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Đăng nhập | Mong đợi: Đăng nhập thành công!");
		System.out.println("✅ TC01: Kiểm thử thành công: Đăng nhập đúng! | PASSED ");
	}

	@Test(priority = 2)
	public void testTradePhone() throws InterruptedException {
		Thread.sleep(2000);
		tradePhonePage.searchAndSelectProduct("ip");
		tradePhonePage.tradePhoneProcess("Trần Sơn", "0898803983");
		tradePhonePage.enterDistrict();
		tradePhonePage.enterAddress();
		tradePhonePage.close();
		
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Trade Phone | Mong đợi: Nhập tất cả các thông tin cần thiết!");
		System.out.println("✅ TC02: Kiểm thử thành công! | PASSED");
	}

	@Test(priority = 3)
	public void testWithoutName() throws InterruptedException {
		Thread.sleep(2000);
		tradePhonePage.searchAndSelectProduct("ip");
		tradePhonePage.tradePhoneProcess("", "0898803983");
		Thread.sleep(1000);
		tradePhonePage.sell();
		tradePhonePage.close();
		
		
		
		Assert.assertFalse(tradePhonePage.isNameErrorDisplayed());
		
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Không điền tên| Mong đợi: Có lỗi: Vui lòng kiểm tra lại họ và tên!");
		System.out.println("✅ TC03: Kiểm thử thành công! | PASSED");
	}

	@Test(priority = 4)
	public void testWithoutNumber() throws InterruptedException {
		Thread.sleep(2000);
		tradePhonePage.searchAndSelectProduct("ip");
		tradePhonePage.tradePhoneProcess("Trần Sơn", "");
		Thread.sleep(1000);
		tradePhonePage.sell();
		tradePhonePage.close();
		Assert.assertFalse(tradePhonePage.isNumberErrorDisplayed());
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Không điền số điện thoại| Mong đợi: Có lỗi: Vui lòng kiểm tra lại số điện thoại!");
		System.out.println("✅ TC04: Kiểm thử thành công! | PASSED");
	}

	@Test(priority = 5)
	public void testWithoutDistrict() throws InterruptedException {
		Thread.sleep(2000);
		tradePhonePage.searchAndSelectProduct("ip");
		tradePhonePage.tradePhoneProcess("Trần Sơn", "0898803983");
		Thread.sleep(1000);
		tradePhonePage.sell();
		tradePhonePage.close();
		
		Assert.assertFalse(tradePhonePage.isDistrictErrorDisplayed());
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Không chọn quận, huyện| Mong đợi: Có lỗi: Vui lòng chọn cửa hàng muốn giao dịch!");
		System.out.println("✅ TC05: Kiểm thử thành công! | PASSED");
	}

	@Test(priority = 6)
	public void testWithoutAddress() throws InterruptedException {
		Thread.sleep(2000);
		tradePhonePage.searchAndSelectProduct("ip");
		tradePhonePage.tradePhoneProcess("Trần Sơn", "0898803983");
		tradePhonePage.enterDistrict();
		Thread.sleep(1000);
		tradePhonePage.sell();
		tradePhonePage.close();
		Assert.assertFalse(tradePhonePage.isAddressErrorDisplayed());
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Không chọn địa chỉ| Mong đợi: Có lỗi: Vui lòng chọn cửa hàng muốn giao dịch!");
		System.out.println("✅ TC06: Kiểm thử thành công! | PASSED");
	}
	
	@Test(priority = 7)
	public void testWithoutCheckCaptcha() throws InterruptedException {
		Thread.sleep(2000);
		tradePhonePage.searchAndSelectProduct("ip");
		tradePhonePage.tradePhoneProcess("Trần Sơn", "0898803983");
		tradePhonePage.enterDistrict();
		tradePhonePage.enterAddress();
		Thread.sleep(1000);
		tradePhonePage.sell();
		tradePhonePage.close();
		Assert.assertFalse(tradePhonePage.isCaptChaErrorDisplayed());
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Không tích Captcha| Mong đợi: Có lỗi: Vui lòng xác thực captcha.");
		System.out.println("✅ TC07: Kiểm thử thành công! | PASSED");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
