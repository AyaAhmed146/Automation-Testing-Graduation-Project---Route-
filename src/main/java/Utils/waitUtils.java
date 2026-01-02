package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class waitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public waitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(ConfigReader.getTimeout())
        );
    }

    /* ================= Wait for Elements ================= */

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /* ================= Page & URL ================= */

    public void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    public void waitForTitleContains(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    /* ================= Alerts ================= */

    public Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /* ================= Page Load ================= */

    public void waitForPageLoad() {
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

    /* ================= JavaScript Helpers ================= */

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}