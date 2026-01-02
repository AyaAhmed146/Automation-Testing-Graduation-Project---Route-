package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedInLinkTest extends BaseTests {

    @Test
    public void testLinkedInLink() {
        scrollToFooter();

        waitUtils.waitForClickable(
                By.xpath("//a[@href='https://www.linkedin.com/company/eyouth/mycompany/verification/']")
        ).click();

        waitUtils.waitFor(1);
        waitUtils.waitForUrlContains("linkedin.com");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("linkedin.com")
        );
    }

    private void scrollToFooter() {
        for (int i = 0; i < 10; i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, window.innerHeight)");
            waitUtils.waitFor(1);
        }
    }
}
