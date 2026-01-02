package Tests;

import Pages.CourseCardPage;
import Pages.CourseDetailsPage;
import Tests.Base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenCourseDetailsTest extends BaseTests {

    private CourseCardPage courseCardPage;
    private CourseDetailsPage courseDetailsPage;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        courseCardPage = new CourseCardPage(driver);
        courseDetailsPage = new CourseDetailsPage(driver);
    }

    @Test
    public void testOpenCourseDetails() {
        driver.navigate().to("https://eyouthlearning.com/all-courses");
        Utils.waitUtils.waitFor(3);

        WebElement courseCard = courseCardPage.getFirstCourseCard();
        Assert.assertNotNull(courseCard);

        courseCard.click();
        Utils.waitUtils.waitFor(3);

        boolean isAboutSectionDisplayed = courseDetailsPage.isAboutSectionDisplayed();
        Assert.assertTrue(isAboutSectionDisplayed);
    }
}