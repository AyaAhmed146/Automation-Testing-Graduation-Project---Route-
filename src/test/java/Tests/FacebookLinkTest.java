package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class FacebookLinkTest extends BaseTests {

    @Test
    public void testFacebookLink() throws Exception {
        // Scroll to footer
        for (int i = 0; i < 10; i++) {
            ((org.openqa.selenium.JavascriptExecutor)driver)
                    .executeScript("window.scrollBy(0, window.innerHeight)");
            Thread.sleep(1000);
        }

        // Click Facebook icon
        driver.findElement(By.xpath("//a[@href='https://www.facebook.com/EYouthLearning/']")).click();
        Thread.sleep(1000);

        // Assert URL contains facebook.com
        assert driver.getCurrentUrl().contains("facebook.com") : "URL should contain facebook.com";
    }
}