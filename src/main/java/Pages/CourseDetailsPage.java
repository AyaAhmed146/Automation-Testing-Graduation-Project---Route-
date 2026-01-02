package Pages;

import Utils.waitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CourseDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private waitUtils waitUtils;

    public CourseDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.waitUtils = new waitUtils(driver);
    }

    public boolean isAboutSectionDisplayed() {
        try {
            WebElement aboutSection = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//*[contains(text(), 'حول الدورة التدريبية')] | " +
                                            "//*[contains(text(), 'حول')] | " +
                                            "//h3[contains(text(), 'حول')] | " +
                                            "//div[contains(text(), 'حول الدورة')]"
                            )
                    )
            );
            return aboutSection.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


}