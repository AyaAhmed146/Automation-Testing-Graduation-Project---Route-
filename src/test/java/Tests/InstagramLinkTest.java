package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InstagramLinkTest extends BaseTests {

    @Test
    public void testInstagramLink() {
        scrollToFooter();

        waitUtils.waitForClickable(
                By.xpath("//a[@href='https://instagram.com/eyouthlearning?igshid=YmMyMTA2M2Y=']")
        ).click();

        Utils.waitUtils.waitFor(1);
        waitUtils.waitForUrlContains("instagram.com");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("instagram.com")
        );
    }

    private void scrollToFooter() {
        for (int i = 0; i < 10; i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, window.innerHeight)");
            Utils.waitUtils.waitFor(1);
        }
    }
}

