package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmptyLoginTest extends BaseTests {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get("https://eyouthlearning.com/signin");
    }

    @Test
    public void testLoginInvalid() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
        Utils.waitUtils.waitFor(1);

        waitUtils.waitForClickable(By.xpath("//button[@type='submit']")).click();
        Utils.waitUtils.waitFor(2);

        String pageSource = driver.getPageSource();
        assert pageSource.contains("اسم المستخدم مطلوب") :
                "Error message should appear";
    }
}


