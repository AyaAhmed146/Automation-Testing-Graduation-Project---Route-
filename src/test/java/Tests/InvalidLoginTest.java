package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTests {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.get("https://eyouthlearning.com/signin");
    }

    @Test
    public void testLoginInvalid() throws Exception {
        driver.findElement(By.id("username")).sendKeys("x");
        driver.findElement(By.id("password")).sendKeys("x");
        ((org.openqa.selenium.JavascriptExecutor)driver).executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        assert driver.getPageSource().contains("اسم المستخدم أو كلمة المرور غير صحيحة");
    }
}