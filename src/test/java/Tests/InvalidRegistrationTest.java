package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class InvalidRegistrationTest extends BaseTests {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.navigate().to("https://eyouthlearning.com/signup");
        waitUtils.waitFor(3); // Give page more time to load
    }

    @Test
    public void testCase4_EmptyUsernameField() {
        System.out.println("=== Starting Registration Test ===");

        try {
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
            System.out.println("✅ Test PASSED");
        } catch (Exception e) {
            System.out.println("❌ Test FAILED: " + e.getMessage());
            e.printStackTrace();
            debugPageElements();
            throw e;
        }
    }

    private void fillTextField(String name, String value) {
        try {
            System.out.println("Filling field: " + name);
            WebElement field = waitUtils.waitForClickable(By.name(name));
            field.clear();
            field.sendKeys(value);
            System.out.println("✓ Filled: " + name);
        } catch (Exception e) {
            System.out.println("❌ Failed to fill: " + name);
            throw e;
        }
    }

    private void selectFromDropdown(String dropdownId, String optionText) {
        try {
            System.out.println("Selecting from dropdown: " + dropdownId + " -> " + optionText);
            WebElement dropdown = waitUtils.waitForClickable(By.id(dropdownId + "-select"));
            dropdown.click();

            waitUtils.waitFor(1);

            WebElement option = waitUtils.waitForClickable(
                    By.xpath("//li[contains(text(), '" + optionText + "')]")
            );
            option.click();
            System.out.println("✓ Selected: " + optionText);
        } catch (Exception e) {
            System.out.println("❌ Failed to select from dropdown: " + dropdownId);
            throw e;
        }
    }

    private void clickCheckbox() {
        try {
            System.out.println("Attempting to click checkbox...");

            // Scroll down to see checkbox
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 600);");
            waitUtils.waitFor(2);

            WebElement checkbox = findCheckbox();

            if (checkbox == null) {
                throw new RuntimeException("Checkbox not found with any locator");
            }

            // Try regular click first
            try {
                checkbox.click();
                System.out.println("✓ Checkbox clicked successfully");
            } catch (Exception e) {
                System.out.println("Regular click failed, trying JavaScript click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
                System.out.println("✓ Checkbox clicked via JavaScript");
            }

            waitUtils.waitFor(1);
        } catch (Exception e) {
            System.out.println("❌ Failed to click checkbox: " + e.getMessage());
            debugPageElements();
            throw e;
        }
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
                System.out.println("Trying locator: " + locator);
                WebElement element = driver.findElement(locator);
                System.out.println("✓ Found checkbox with: " + locator);
                return element;
            } catch (Exception e) {
                System.out.println("✗ Failed with: " + locator);
                continue;
            }
        }

        System.out.println("⚠ Checkbox not found, printing page source...");
        System.out.println(driver.getPageSource());
        return null;
    }

    private void clickRegisterButton() {
        try {
            System.out.println("Attempting to click register button...");

            // Scroll down a bit more
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");
            waitUtils.waitFor(1);

            WebElement button = waitUtils.waitForClickable(
                    By.xpath("//button[contains(text(), 'انشاء')]")
            );
            button.click();
            System.out.println("✓ Register button clicked");
        } catch (Exception e) {
            System.out.println("❌ Failed to click register button: " + e.getMessage());
            throw e;
        }
    }

    private void verifyErrorMessage(String expectedMessage) {
        try {
            System.out.println("Verifying error message: " + expectedMessage);
            String errorMsg = waitUtils.waitForVisibility(
                    By.xpath("//*[contains(text(), '" + expectedMessage + "')]")
            ).getText();

            Assert.assertTrue(errorMsg.contains(expectedMessage),
                    "خطأ: رسالة التحقق غير صحيحة");
            System.out.println("✓ Error message verified: " + errorMsg);
        } catch (Exception e) {
            System.out.println("❌ Failed to verify error message: " + e.getMessage());
            throw e;
        }
    }

    private void debugPageElements() {
        System.out.println("\n=== DEBUGGING PAGE ELEMENTS ===");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());

        try {
            // Debug all input fields
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            System.out.println("\nFound " + inputs.size() + " input elements:");
            for (int i = 0; i < inputs.size(); i++) {
                WebElement input = inputs.get(i);
                System.out.println("Input " + i + ":");
                System.out.println("  - type: " + input.getAttribute("type"));
                System.out.println("  - id: " + input.getAttribute("id"));
                System.out.println("  - name: " + input.getAttribute("name"));
                System.out.println("  - class: " + input.getAttribute("class"));
                System.out.println("  - placeholder: " + input.getAttribute("placeholder"));
            }
        } catch (Exception e) {
            System.out.println("Error finding inputs: " + e.getMessage());
        }

        try {
            // Debug all buttons
            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            System.out.println("\nFound " + buttons.size() + " button elements:");
            for (int i = 0; i < buttons.size(); i++) {
                WebElement button = buttons.get(i);
                System.out.println("Button " + i + ": " + button.getText());
            }
        } catch (Exception e) {
            System.out.println("Error finding buttons: " + e.getMessage());
        }

        try {
            // Debug all checkboxes
            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
            System.out.println("\nFound " + checkboxes.size() + " checkbox elements:");
            for (int i = 0; i < checkboxes.size(); i++) {
                WebElement checkbox = checkboxes.get(i);
                System.out.println("Checkbox " + i + ":");
                System.out.println("  - id: " + checkbox.getAttribute("id"));
                System.out.println("  - name: " + checkbox.getAttribute("name"));
                System.out.println("  - class: " + checkbox.getAttribute("class"));
            }
        } catch (Exception e) {
            System.out.println("Error finding checkboxes: " + e.getMessage());
        }

        System.out.println("\n=== END DEBUGGING ===\n");
    }
}