package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class EndToEndCourseSubscriptionTest extends BaseTests {

    private static final String USERNAME = "ayaahmed1462001@gmail.com";
    private static final String PASSWORD = "12345678";

    @Test
    public void testEndToEndCourseSubscription() throws Exception {
        // Login
        driver.navigate().to("https://eyouthlearning.com/signin");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("eyouthlearning.com"));
        Thread.sleep(2000);

        // Navigate to All Courses
        driver.findElement(By.xpath("//a[@href='/all-courses']")).click();
        wait.until(ExpectedConditions.urlContains("/all-courses"));
        Thread.sleep(2000);

        // Get first course name
        WebElement courseCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("course-card")));
        WebElement courseNameElement = courseCard.findElement(By.xpath(".//strong"));
        String courseName = courseNameElement.getText().trim();
        System.out.println("Found course: " + courseName);

        // Subscribe
        WebElement subscribeBtn = courseCard.findElement(By.xpath(".//button[contains(text(), 'اشترك')]"));
        subscribeBtn.click();
        Thread.sleep(3000);

        // Wait for payment page to load
        wait.until(ExpectedConditions.urlContains("/payment"));
        Thread.sleep(2000);

        // Check if course appears in payment page
        String paymentPageSource = driver.getPageSource();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Looking for course: " + courseName);
        System.out.println("Course found in payment page: " + paymentPageSource.contains(courseName));

        assert paymentPageSource.contains(courseName) :
                String.format("Course '%s' should appear in payment page", courseName);
    }
}