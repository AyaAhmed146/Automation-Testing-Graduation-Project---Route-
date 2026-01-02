package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CourseCardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CourseCardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // Get first course card
    public WebElement getFirstCourseCard() {
        WebElement card = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[contains(@class, 'course-card')] | //article[contains(@class, 'course')])[1]")
                )
        );
        waitForPageLoad();
        return card;
    }

    // Check if course has image
    public boolean hasCourseImage(WebElement courseCard) {
        try {
            // الصورة في div بـ class "MuiCardMedia-root" مع background-image
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
            // العنوان في strong tag بـ class "MuiTypography-root"
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
            // اسم المدرب في span بـ "مع **instructor name**"
            WebElement instructor = courseCard.findElement(
                    By.xpath(".//span[contains(@class, 'MuiTypography-p') and contains(text(), 'مع')]")
            );
            String text = instructor.getText().trim();
            // استخرج الاسم بعد كلمة "مع"
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

    // Click subscribe button
    public void clickSubscribeButton(WebElement courseCard) {
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        courseCard.findElement(By.xpath(".//button[contains(text(), 'اشترك')]"))
                )
        );
        button.click();
        waitForPageLoad();
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