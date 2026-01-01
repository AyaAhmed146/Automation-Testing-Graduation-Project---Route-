package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class InstagramLinkTest extends BaseTests {

    @Test
    public void testInstagramLink() throws Exception {
        // Navigate to the footer
        for (int i = 0; i < 10; i++) {
            ((org.openqa.selenium.JavascriptExecutor)driver)
                    .executeScript("window.scrollBy(0, window.innerHeight)");
            Thread.sleep(1000);
        }

        // Click Instagram icon
        driver.findElement(By.xpath("//a[@href='https://instagram.com/eyouthlearning?igshid=YmMyMTA2M2Y=']")).click();
        Thread.sleep(1000);

        // Assert URL contains instagram.com
        assert driver.getCurrentUrl().contains("instagram.com") : "URL should contain instagram.com";
    }
}