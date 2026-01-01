package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class InvalidRegistrationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://eyouthlearning.com/signup");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testCase4_EmptyUsernameField() {
        fillTextField("name", "أحمد محمد");
        selectFromDropdown("country", "مصر");
        selectFromDropdown("gender", "ذكر");
        selectFromDropdown("government", "أسيوط");
        fillTextField("email", "test@example.com");
        fillTextField("phone_number", "201001234567");
        fillTextField("password", "TestPassword123");
        fillTextField("confirmPassword", "TestPassword123");

        clickCheckbox();
        clickRegisterButton();

        verifyErrorMessage("اسم المستخدم مطلوب");
    }

    private void fillTextField(String name, String value) {
        WebElement field = wait.until(
                ExpectedConditions.elementToBeClickable(By.name(name))
        );
        field.clear();
        field.sendKeys(value);
    }

    private void selectFromDropdown(String dropdownId, String optionText) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(By.id(dropdownId + "-select"))
        );
        dropdown.click();

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//li[contains(text(), '" + optionText + "')]")
                )
        );
        option.click();
    }

    private void clickCheckbox() {
        // اسكرول للأسفل بشكل أكبر عشان نتأكد
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 600);");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // جرب البحث عن الـ input مباشرة
        WebElement checkbox = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("accept"))
        );

        // اضغط بـ JavaScript إذا العادي ما اشتغل
        try {
            checkbox.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void clickRegisterButton() {
        // اسكرول عشان نشوف الزر
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'انشاء')]"))
        );
        button.click();
    }

    private void verifyErrorMessage(String expectedMessage) {
        String errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), '" + expectedMessage + "')]")
                )
        ).getText();

        Assert.assertTrue(errorMsg.contains(expectedMessage),
                "خطأ: رسالة التحقق غير صحيحة");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}