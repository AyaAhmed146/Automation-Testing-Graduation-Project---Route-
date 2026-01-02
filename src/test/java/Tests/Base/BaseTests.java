package Tests.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import Utils.waitUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseTests {
    protected WebDriver driver;
    protected waitUtils waitUtils;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        waitUtils = new waitUtils(driver);

        driver.navigate().to("https://eyouthlearning.com/");
        waitUtils.waitFor(2);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

