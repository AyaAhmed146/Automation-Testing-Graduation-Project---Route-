package Pages;

import Utils.waitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CourseCardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private waitUtils waitUtils;

    public CourseCardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.waitUtils = new waitUtils(driver);
    }

    // Get first course card
    public WebElement getFirstCourseCard() {
        WebElement card = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[contains(@class, 'course-card')] | //article[contains(@class, 'course')])[1]")
                )
        );
        waitUtils.waitForPageLoad();
        return card;
    }

    // Check if course has image
    public boolean hasCourseImage(WebElement courseCard) {
        try {
            WebElement imageDiv = courseCard.findElement(By.className("MuiCardMedia-root"));

            if (imageDiv.isDisplayed()) {
                String backgroundImage = imageDiv.getAttribute("style");
                return backgroundImage != null && backgroundImage.contains("background-image");
            }
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("⚠️ No image found in course card");
            return false;
        }
    }

    // Get course title
    public String getCourseTitle(WebElement courseCard) {
        try {
            WebElement title = courseCard.findElement(
                    By.xpath(".//strong[@class='MuiTypography-root MuiTypography-p muirtl-1qfy8rw']")
            );
            return title.getText().trim();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Course title not found in card", e);
        }
    }

    // Get instructor name
    public String getInstructorName(WebElement courseCard) {
        try {
            WebElement instructor = courseCard.findElement(
                    By.xpath(".//span[contains(@class, 'MuiTypography-p') and contains(text(), 'مع')]")
            );
            String text = instructor.getText().trim();
            // extract the instructor name after "مع"
            return text.replace("مع", "").trim();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Instructor name not found in card", e);
        }
    }

    // Check if subscribe button exists
    public boolean hasSubscribeButton(WebElement courseCard) {
        try {
            WebElement button = courseCard.findElement(
                    By.xpath(".//button[contains(text(), 'اشترك')]")
            );
            return button.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}