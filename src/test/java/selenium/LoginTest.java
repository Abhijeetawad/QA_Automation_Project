package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import java.time.Duration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.ExtentReportManager; // Make sure you have this utility class

public class LoginTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Initialize Extent Report
        extent = ExtentReportManager.getExtentReports();
        test = extent.createTest("Login Test");

        // Chrome setup
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\abhij\\Desktop\\Automation files\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        // Headless removed to see browser
        // options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-infobars", "--disable-extensions");

        driver = new ChromeDriver(options);
        test.log(Status.INFO, "Chrome browser launched successfully");
    }

    @Test
    public void loginTest() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        test.log(Status.INFO, "Navigated to login page");

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
        test.log(Status.INFO, "Entered username and password");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
        driver.findElement(By.id("submit")).click();
        test.log(Status.INFO, "Clicked login button");

        // Validation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        String successMessage = driver.findElement(By.tagName("h1")).getText();

        if (successMessage.contains("Logged In Successfully")) {
            test.log(Status.PASS, "Login test executed successfully!");
            System.out.println("Login test executed successfully!");
        } else {
            test.log(Status.FAIL, "Login test failed!");
            System.out.println("Login test failed!");
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
        test.log(Status.INFO, "Browser closed");

        // Flush report
        extent.flush();
    }
}
