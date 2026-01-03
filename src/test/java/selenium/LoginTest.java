package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\abhij\\Desktop\\Automation files\\chromedriver-win64\\chromedriver.exe");

        // Chrome options for headless mode (no GUI)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");          // run Chrome without GUI
        options.addArguments("--disable-gpu");       // required for some Windows machines
        options.addArguments("--window-size=1920,1080"); // set window size to avoid layout issues
        options.addArguments("--no-sandbox");        // optional, useful in CI/CD
        options.addArguments("--disable-dev-shm-usage"); // optional, prevents shared memory issues

        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // still maximized in virtual viewport
    }

    @Test
    public void loginTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("loginButton")).click();

        // Basic validation to ensure login succeeded
        String successMessage = driver.findElement(By.tagName("h1")).getText();
        if(successMessage.contains("Logged In Successfully")) {
            System.out.println("Login test executed successfully!");
        } else {
            System.out.println("Login test failed!");
        }
    }

    @AfterClass
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
