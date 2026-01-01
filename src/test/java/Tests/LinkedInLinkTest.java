package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LinkedInLinkTest extends BaseTests {

    @Test
    public void testLinkedInLink() throws Exception {
        // Scroll to footer
        for (int i = 0; i < 10; i++) {
            ((org.openqa.selenium.JavascriptExecutor)driver)
                    .executeScript("window.scrollBy(0, window.innerHeight)");
            Thread.sleep(1000);
        }

        // Click LinkedIn icon
        driver.findElement(By.xpath("//a[@href='https://www.linkedin.com/company/eyouth/mycompany/verification/']")).click();
        Thread.sleep(1000);

        // Assert URL contains linkedin.com
        assert driver.getCurrentUrl().contains("linkedin.com") : "URL should contain linkedin.com";
    }
}