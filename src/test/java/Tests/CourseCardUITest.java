package Tests;

import Pages.CourseCardPage;
import Tests.Base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CourseCardUITest extends BaseTests {

    private CourseCardPage courseCardPage;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();
        driver.navigate().to("https://eyouthlearning.com/all-courses");
        Utils.waitUtils.waitFor(3);
        courseCardPage = new CourseCardPage(driver);
    }

    @Test
    public void testVerifyCourseCardUI() {

        try {
            //  Get first course card
            WebElement courseCard = courseCardPage.getFirstCourseCard();
            Assert.assertNotNull(courseCard);

            //  Verify course image
            boolean hasImage = courseCardPage.hasCourseImage(courseCard);
            Assert.assertTrue(hasImage);

            //  Verify course title
            String courseTitle = courseCardPage.getCourseTitle(courseCard);
            Assert.assertNotNull(courseTitle);

            // Verify instructor name
            String instructorName = courseCardPage.getInstructorName(courseCard);
            Assert.assertNotNull(instructorName);

            //  Verify subscribe button
            boolean hasSubscribeButton = courseCardPage.hasSubscribeButton(courseCard);
            Assert.assertTrue(hasSubscribeButton);

        } catch (Exception e) {
            System.out.println("Run time Error:  " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
