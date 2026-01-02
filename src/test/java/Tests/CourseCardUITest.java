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
        System.out.println("🧪 Test Case #11: Verify course cards UI");

        try {
            // Step 1: Get first course card
            System.out.println("📍 Step 1: Getting first course card...");
            WebElement courseCard = courseCardPage.getFirstCourseCard();
            Assert.assertNotNull(courseCard, "❌ Course card not found");
            System.out.println("✅ Course card found");

            // Step 2: Verify course image
            System.out.println("📍 Step 2: Verifying course image...");
            boolean hasImage = courseCardPage.hasCourseImage(courseCard);
            Assert.assertTrue(hasImage, "❌ Course image not found");
            System.out.println("✅ Course image exists");

            // Step 3: Verify course title
            System.out.println("📍 Step 3: Verifying course title...");
            String courseTitle = courseCardPage.getCourseTitle(courseCard);
            Assert.assertNotNull(courseTitle, "❌ Course title not found");
            Assert.assertFalse(courseTitle.isEmpty(), "❌ Course title is empty");
            System.out.println("✅ Course title: " + courseTitle);

            // Step 4: Verify instructor name
            System.out.println("📍 Step 4: Verifying instructor name...");
            String instructorName = courseCardPage.getInstructorName(courseCard);
            Assert.assertNotNull(instructorName, "❌ Instructor name not found");
            Assert.assertFalse(instructorName.isEmpty(), "❌ Instructor name is empty");
            System.out.println("✅ Instructor name: " + instructorName);

            // Step 5: Verify subscribe button
            System.out.println("📍 Step 5: Verifying subscribe button...");
            boolean hasSubscribeButton = courseCardPage.hasSubscribeButton(courseCard);
            Assert.assertTrue(hasSubscribeButton, "❌ Subscribe button not found");
            System.out.println("✅ Subscribe button exists");

            System.out.println("\n🎉 Test Case #11 PASSED - Course card UI is correct!");

        } catch (Exception e) {
            System.out.println("❌ Test Case #11 FAILED: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}