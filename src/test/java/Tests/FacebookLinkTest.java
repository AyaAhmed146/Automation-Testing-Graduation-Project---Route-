package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FacebookLinkTest extends BaseTests {

    @Test
    public void testFacebookLink() {
        scrollToFooter();

        waitUtils.waitForClickable(
                By.xpath("//a[@href='https://www.facebook.com/EYouthLearning/']")
        ).click();

        waitUtils.waitFor(1);
        waitUtils.waitForUrlContains("facebook.com");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("facebook.com")
        );
    }

    private void scrollToFooter() {
        for (int i = 0; i < 10; i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, window.innerHeight)");
            waitUtils.waitFor(1);
        }
    }
}

