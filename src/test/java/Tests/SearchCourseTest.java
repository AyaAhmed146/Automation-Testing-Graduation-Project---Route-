package Tests;

import Pages.SearchCoursePage;
import Tests.Base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchCourseTest extends BaseTests {

    @Test
    public void testSearch() {
        SearchCoursePage home = new SearchCoursePage(driver);

        // Step 1: Search
        home.search("كيف تنضم إلى البنك");

        // Step 2: Click on the course with exact keyword
        home.clickResultByKeyword("كيف تنضم إلى البنك");

        // Step 3: Assert word appears
        String pageSource = driver.getPageSource();
        Assert.assertTrue(
                pageSource.contains("البنك") || pageSource.contains("bank"),
                "Word should appear on page"
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
