package Tests;

import Tests.Base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        // looping on all buttons to find the correct one
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

        // click on the found button
        targetButton.click();
        Utils.waitUtils.waitFor(1);

        // click on the "انضم لنا كطالب" link
        WebElement signupLink = waitUtils.waitForClickable(
                By.xpath("//a[@href='/signup']")
        );
        signupLink.click();
        System.out.println("✅ تم الضغط على انضم لنا كطالب");

        // verify that the URL contains "/signup"
        waitUtils.waitForUrlContains("/signup");

        Utils.waitUtils.waitFor(1);
    }
}