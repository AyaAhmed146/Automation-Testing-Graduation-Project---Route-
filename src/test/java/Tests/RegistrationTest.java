package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class RegistrationTest extends BaseTests {

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        waitUtils.waitFor(2);
    }

    @Test
    public void testOpenRegistrationPage() {
        // خذ جميع الأزرار واتحقق من اللي فيه "انضم لنا"
        List<WebElement> allButtons = driver.findElements(
                By.xpath("//button[@aria-label='button to be clicked']")
        );

        WebElement targetButton = null;
        for (WebElement btn : allButtons) {
            if (btn.getText().trim().contains("انضم لنا")) {
                targetButton = btn;
                break;
            }
        }

        // اضغط على الزر
        targetButton.click();
        System.out.println("✅ تم الضغط على زر انضم لنا");

        // انتظر Dialog تظهر
        waitUtils.waitFor(1);

        // اضغط على "انضم لنا كطالب"
        WebElement signupLink = waitUtils.waitForClickable(
                By.xpath("//a[@href='/signup']")
        );
        signupLink.click();
        System.out.println("✅ تم الضغط على انضم لنا كطالب");

        // انتظر الـ URL تتغير
        waitUtils.waitForUrlContains("/signup");

        // تحقق من الـ URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("/signup"),
                "URL يجب أن تحتوي على /signup"
        );

        System.out.println("✅ اختبار نجح!");
        waitUtils.waitFor(1);
    }
}