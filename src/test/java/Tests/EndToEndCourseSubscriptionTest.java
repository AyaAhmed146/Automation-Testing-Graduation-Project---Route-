package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndCourseSubscriptionTest extends BaseTests {

    private static final String USERNAME = "ayaahmed1462001@gmail.com";
    private static final String PASSWORD = "12345678";

    @Test
    public void testEndToEndCourseSubscription() {
        login();
        navigateToCourses();
        String courseName = getFirstCourseName();
        subscribeToCourse();
        verifyPaymentPage(courseName);
    }

    private void login() {
        driver.navigate().to("https://eyouthlearning.com/signin");
        waitUtils.waitForVisibility(By.id("username")).sendKeys(USERNAME);
        waitUtils.waitForVisibility(By.id("password")).sendKeys(PASSWORD);
        waitUtils.waitForClickable(By.xpath("//button[@type='submit']")).click();
        waitUtils.waitForUrlContains("eyouthlearning.com");
        Utils.waitUtils.waitFor(2);
    }

    private void navigateToCourses() {
        waitUtils.waitForClickable(By.xpath("//a[@href='/all-courses']")).click();
        waitUtils.waitForUrlContains("/all-courses");
        Utils.waitUtils.waitFor(2);
    }

    private String getFirstCourseName() {
        WebElement courseCard = waitUtils.waitForVisibility(By.className("course-card"));
        WebElement courseNameElement = courseCard.findElement(By.xpath(".//strong"));
        String courseName = courseNameElement.getText().trim();
        System.out.println("Found course: " + courseName);
        return courseName;
    }

    private void subscribeToCourse() {
        WebElement courseCard = waitUtils.waitForVisibility(By.className("course-card"));
        WebElement subscribeBtn = courseCard.findElement(By.xpath(".//button[contains(text(), 'اشترك')]"));
        subscribeBtn.click();
        Utils.waitUtils.waitFor(2);
    }

    private void verifyPaymentPage(String courseName) {
        waitUtils.waitForUrlContains("/payment");
        Utils.waitUtils.waitFor(2);

        String paymentPageSource = driver.getPageSource();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Looking for course: " + courseName);

        Assert.assertTrue(paymentPageSource.contains(courseName),
                String.format("Course '%s' should appear in payment page", courseName));
    }
}


