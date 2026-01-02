package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTests {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get("https://eyouthlearning.com/signin");
        waitUtils.waitFor(1);
    }

    @Test
    public void testLoginInvalid() {
        waitUtils.waitForVisibility(By.id("username")).sendKeys("x");
        waitUtils.waitForVisibility(By.id("password")).sendKeys("x");

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
        waitUtils.waitFor(1);

        waitUtils.waitForClickable(By.xpath("//button[@type='submit']")).click();
        waitUtils.waitFor(2);

        String pageSource = driver.getPageSource();
        assert pageSource.contains("اسم المستخدم أو كلمة المرور غير صحيحة") :
                "Error message should appear";
    }
}
