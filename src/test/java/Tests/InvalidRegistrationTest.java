package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvalidRegistrationTest extends BaseTests {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.navigate().to("https://eyouthlearning.com/signup");
        Utils.waitUtils.waitFor(3);
    }

    @Test
    public void testCase4_EmptyUsernameField() {
        fillTextField("name", "آية أحمد");
        selectFromDropdown("country", "مصر");
        selectFromDropdown("gender", "أنثى");
        selectFromDropdown("government", "أسيوط");
        fillTextField("email", "test@example.com");
        fillTextField("phone_number", "201001234567");
        fillTextField("password", "TestPassword123");
        fillTextField("confirmPassword", "TestPassword123");

        clickCheckbox();
        clickRegisterButton();

        verifyErrorMessage();
    }

    private void fillTextField(String name, String value) {
        WebElement field = waitUtils.waitForClickable(By.name(name));
        field.clear();
        field.sendKeys(value);
    }

    private void selectFromDropdown(String dropdownId, String optionText) {
        WebElement dropdown = waitUtils.waitForClickable(By.id(dropdownId + "-select"));
        dropdown.click();
        Utils.waitUtils.waitFor(1);

        WebElement option = waitUtils.waitForClickable(
                By.xpath("//li[contains(text(), '" + optionText + "')]")
        );
        option.click();
    }

    private void clickCheckbox() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 600);");
        Utils.waitUtils.waitFor(2);

        WebElement checkbox = findCheckbox();

        if (checkbox == null) {
            throw new RuntimeException("Checkbox not found");
        }

        try {
            checkbox.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }

        Utils.waitUtils.waitFor(1);
    }

    private WebElement findCheckbox() {
        By[] locators = {
                By.id("accept"),
                By.name("accept"),
                By.xpath("//input[@id='accept']"),
                By.xpath("//input[@type='checkbox']"),
                By.xpath("//input[@name='terms']"),
                By.xpath("//input[@class*='accept']"),
                By.cssSelector("input[type='checkbox']")
        };

        for (By locator : locators) {
            try {
                return driver.findElement(locator);
            } catch (Exception e) {
                // Continue to next locator
            }
        }

        return null;
    }

    private void clickRegisterButton() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");
        Utils.waitUtils.waitFor(1);

        WebElement button = waitUtils.waitForClickable(
                By.xpath("//button[contains(text(), 'انشاء')]")
        );
        button.click();
    }

    private void verifyErrorMessage() {
        String errorMsg = waitUtils.waitForVisibility(
                By.xpath("//*[contains(text(), '" + "اسم المستخدم مطلوب" + "')]")
        ).getText();

        Assert.assertTrue(errorMsg.contains("اسم المستخدم مطلوب"),
                "خطأ: رسالة التحقق غير صحيحة");
    }
}