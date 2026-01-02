package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CourseDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CourseDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // Check if course details page is loaded
    public boolean isCourseDetailsPageLoaded() {
        try {
            // تحقق من وجود عنوان الكورس أو أي عنصر من عناصر صفحة التفاصيل
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1 | //h2[contains(@class, 'course-title')]")
                    )
            );
            waitForPageLoad();
            return true;
        } catch (TimeoutException e) {
            System.out.println("⚠️ Course details page did not load");
            return false;
        }
    }

    // Check if "حول الدورة التدريبية" section is displayed
    public boolean isAboutSectionDisplayed() {
        try {
            // ابحث عن القسم "حول الدورة التدريبية"
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
            System.out.println("⚠️ 'حول الدورة التدريبية' section not found");
            return false;
        }
    }

    // Get course title from details page
    public String getCourseTitle() {
        try {
            WebElement title = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1 | //h2[contains(@class, 'course-title')]")
                    )
            );
            return title.getText().trim();
        } catch (TimeoutException e) {
            throw new RuntimeException("Course title not found on details page", e);
        }
    }

    // Get course description
    public String getCourseDescription() {
        try {
            WebElement description = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//p[contains(@class, 'description')] | //div[contains(@class, 'about')]")
                    )
            );
            return description.getText().trim();
        } catch (TimeoutException e) {
            throw new RuntimeException("Course description not found", e);
        }
    }

    // Get about section content
    public String getAboutSectionContent() {
        try {
            WebElement aboutSection = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//*[contains(text(), 'حول الدورة التدريبية')]/following-sibling::* | " +
                                            "//div[contains(@class, 'about-section')]"
                            )
                    )
            );
            return aboutSection.getText().trim();
        } catch (TimeoutException e) {
            throw new RuntimeException("About section content not found", e);
        }
    }

    // Check if instructor information is displayed
    public boolean isInstructorInfoDisplayed() {
        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(text(), 'مع')] | //div[contains(@class, 'instructor')]")
                    )
            );
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for page to load
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