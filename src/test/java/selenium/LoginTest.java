package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\abhij\\Desktop\\Automation files\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-infobars", "--disable-extensions");

        driver = new ChromeDriver(options);
    }

    @Test
    public void loginTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
        driver.findElement(By.id("submit")).click();

        // Validation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        String successMessage = driver.findElement(By.tagName("h1")).getText();
        if(successMessage.contains("Logged In Successfully")) {
            System.out.println("Login test executed successfully!");
        } else {
            System.out.println("Login test failed!");
        }
    }

    @AfterClass
    public void teardown() {
        if(driver != null) driver.quit();
    }
}
