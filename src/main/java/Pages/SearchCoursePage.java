package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SearchCoursePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchCoursePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void search(String keyword) {
        // Find search input
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[type='search'], input[placeholder*='بحث']")
                )
        );

        // Type keyword and press ENTER
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);

        waitForPageLoad();
    }

    public void clickResultByKeyword(String courseName) {
        // Wait for results to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'course-card')] | //article[contains(@class, 'course')]")
        ));

        List<WebElement> results = driver.findElements(
                By.xpath("//div[contains(@class, 'course-card')] | //article[contains(@class, 'course')]")
        );

        for (WebElement result : results) {
            if (result.getText().contains(courseName)) {
                result.click();
                waitForPageLoad();
                return;
            }
        }

        throw new RuntimeException("Course with name '" + courseName + "' not found!");
    }

    private void waitForPageLoad() {
        wait.until(driver -> {
            try {
                Object readyState = ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState");
                return readyState != null && readyState.equals("complete");
            } catch (Exception e) {
                return false;
            }
        });
    }
}
