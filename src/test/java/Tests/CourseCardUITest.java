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
        waitUtils.waitFor(3);
        courseCardPage = new CourseCardPage(driver);
    }

    @Test
    public void testVerifyCourseCardUI() {

        try {
            // Step 1: Get first course card
            WebElement courseCard = courseCardPage.getFirstCourseCard();
            Assert.assertNotNull(courseCard);

            // Step 2: Verify course image
            boolean hasImage = courseCardPage.hasCourseImage(courseCard);
            Assert.assertTrue(hasImage);

            // Step 3: Verify course title
            String courseTitle = courseCardPage.getCourseTitle(courseCard);
            Assert.assertNotNull(courseTitle);

            // Step 4: Verify instructor name
            String instructorName = courseCardPage.getInstructorName(courseCard);
            Assert.assertNotNull(instructorName);

            // Step 5: Verify subscribe button
            boolean hasSubscribeButton = courseCardPage.hasSubscribeButton(courseCard);
            Assert.assertTrue(hasSubscribeButton);

        } catch (Exception e) {
            System.out.println("Run time Error:  " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}