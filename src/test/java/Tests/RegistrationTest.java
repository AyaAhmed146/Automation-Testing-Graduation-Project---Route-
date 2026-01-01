package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTests {

    @Test
    public void testOpenRegistrationPage() {
        try {
            // Wait for page to load
            Thread.sleep(1000);

            // Find button
            WebElement button = driver.findElement(By.xpath("//button[contains(text(), 'انضم')]"));

            // Click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            System.out.println("✅ Clicked button");

            // Wait for navigation
            Thread.sleep(2000);

            // Assert URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("/signup"),
                    "URL should contain /signup. Current: " + currentUrl
            );

            System.out.println("✅ Test PASSED");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}