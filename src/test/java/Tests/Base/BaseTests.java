package Tests.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Utils.waitUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseTests {
    protected WebDriver driver;
    protected waitUtils waitUtils;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

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