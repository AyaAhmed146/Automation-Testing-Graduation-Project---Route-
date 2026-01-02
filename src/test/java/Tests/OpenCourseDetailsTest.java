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
        System.out.println("🧪 Test Case #2: Open course details");

        try {
            // Step 1: Navigate to all courses page
            System.out.println("📍 Step 1: Navigating to all courses page...");
            driver.navigate().to("https://eyouthlearning.com/all-courses");
            waitUtils.waitFor(3);
            System.out.println("✅ All courses page loaded");

            // Step 2: Get first course card
            System.out.println("📍 Step 2: Getting first course card...");
            WebElement courseCard = courseCardPage.getFirstCourseCard();
            Assert.assertNotNull(courseCard, "❌ Course card not found");
            System.out.println("✅ Course card found");

            // Step 3: Get course title before clicking
            System.out.println("📍 Step 3: Getting course title...");
            String courseTitle = courseCardPage.getCourseTitle(courseCard);
            System.out.println("📌 Course title: " + courseTitle);

            // Step 4: Click on the course card
            System.out.println("📍 Step 4: Clicking on course card...");
            courseCard.click();
            waitUtils.waitFor(3);
            System.out.println("✅ Clicked on course card");

            // Step 5: Verify course details page opened
            System.out.println("📍 Step 5: Verifying course details page opened...");
            boolean isDetailsPageLoaded = courseDetailsPage.isCourseDetailsPageLoaded();
            Assert.assertTrue(isDetailsPageLoaded, "❌ Course details page did not load");
            System.out.println("✅ Course details page loaded");

            // Step 6: Verify page content changed (not checking URL as it might be SPA)
            System.out.println("📍 Step 6: Verifying page content changed...");
            String currentUrl = driver.getCurrentUrl();
            System.out.println("📌 Current URL: " + currentUrl);

            // تحقق من أن محتوى الصفحة تغير (لا نعتمد على URL لأن قد تكون SPA)
            String pageTitle = courseDetailsPage.getCourseTitle();
            Assert.assertNotNull(pageTitle, "❌ Course title not found on details page");
            System.out.println("✅ Page content changed to course details");

            // Step 7: Verify "حول الدورة التدريبية" section is displayed
            System.out.println("📍 Step 7: Verifying 'حول الدورة التدريبية' section...");
            boolean isAboutSectionDisplayed = courseDetailsPage.isAboutSectionDisplayed();
            Assert.assertTrue(
                    isAboutSectionDisplayed,
                    "❌ 'حول الدورة التدريبية' section not found"
            );
            System.out.println("✅ 'حول الدورة التدريبية' section is displayed");

            System.out.println("\n🎉 Test Case #2 PASSED - Course details page opened successfully!");

        } catch (Exception e) {
            System.out.println("❌ Test Case #2 FAILED: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}