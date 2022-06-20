package UiTests;

import UiResources.Configurations.PerformanceGlitchConfig;
import UiResources.Pages.Google.GoogleMainPage;
import UiResources.Pages.Google.GoogleResultsPage;
import UiResources.Pages.PerformanceLab.PerfLabAutomationTestingPage;
import UiResources.Pages.PerformanceLab.PerfLabMainPage;
import UiResources.Pages.PerformanceLab.PerfLabWebsiteTestingPage;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static UiResources.Pages.Google.GoogleMainPage.GOOGLE_URL;
import static UiResources.Pages.PerformanceLab.PerfLabMainPage.PERFORMANCE_LAB_URL;
import static com.codeborne.selenide.Selenide.open;

public class UiTests {
    @BeforeAll
    public static void setUp() {
        PerformanceGlitchConfig.setUp();
    }

    @Test
    void googleToMainToProductsBlueBTNTest() {
        GoogleMainPage googleMainPage = open(GOOGLE_URL, GoogleMainPage.class);

        GoogleResultsPage googleResultsPagePerformanceLab = googleMainPage.search("performance lab");

        PerfLabMainPage perfLabMainPage = googleResultsPagePerformanceLab.goToPerformanceLabSite();

        perfLabMainPage.removeBanner();

        perfLabMainPage.getProductsAndServicesLi().hover();

        PerfLabWebsiteTestingPage perfLabWebsiteTestingPage = perfLabMainPage.goToWebsiteTesting();

        perfLabWebsiteTestingPage.getFindOutThePriceButtonFLD().scrollTo();

        perfLabWebsiteTestingPage.getFindOutThePriceButtonFLD()
                .shouldHave(Condition.cssValue("background-color", "rgba(79, 173, 255, 1)"));
    }

    @Test
    void mainToAutomationFormTest() {
        PerfLabMainPage perfLabMainPage = open(PERFORMANCE_LAB_URL, PerfLabMainPage.class);

        perfLabMainPage.removeBanner();

        perfLabMainPage.getProductsAndServicesLi().hover();

        PerfLabAutomationTestingPage perfLabAutomationTestingPage = perfLabMainPage.goToAutomationTesting();

        perfLabAutomationTestingPage.getExamplesOfCompletedProjectsTXT().scrollTo();

        perfLabAutomationTestingPage.getExamplePdfIMG().click();

        perfLabAutomationTestingPage.goToFormIFrame();

        perfLabAutomationTestingPage.getContactFormFRM().shouldBe(Condition.visible);

        perfLabAutomationTestingPage.getContactFormFirstNameFLD().shouldBe(Condition.visible);
    }
}
