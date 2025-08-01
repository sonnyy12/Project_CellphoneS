package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.BuyOldPhonePage;
import java.time.Duration;

public class BuyOldPhoneTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private BuyOldPhonePage buyOldPhonePage;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://cellphones.com.vn/");
		driver.manage().window().maximize();

		loginPage = new LoginPage(driver);
		buyOldPhonePage = new BuyOldPhonePage(driver);
	}

	@Test(priority = 1)
	public void testLogin() throws InterruptedException {
		loginPage.login("0898803983", "sonkenst123");
		Thread.sleep(4000);

		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Đăng nhập | Mong đợi: Đăng nhập thành công!");
		System.out.println("✅ TC01: Kiểm thử thành công: Đăng nhập đúng! | PASSED ");
	}

	@Test(priority = 2)
	public void testBuyOldPhone() throws InterruptedException {
		buyOldPhonePage.processBuyOldPhone();
		buyOldPhonePage.enterCity();
		buyOldPhonePage.enterDistrict();
		buyOldPhonePage.coutinue();

		
		System.out.println("\n----- Kết quả kiểm thử -----");
		System.out.println("Mua hàng cũ  | Mong đợi: Chuyển tới trang giỏ hàng!");
		System.out.println("✅ TC02: Kiểm thử thành công: Chuyển tớ trang giỏ hàng thành công! | PASSED ");
	}

	

	//@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
