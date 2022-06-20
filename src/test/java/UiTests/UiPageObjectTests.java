package UiTests;

import UiResources.Pages.Google.GoogleMainPage;
import UiResources.Pages.Google.GoogleResultsPage;
import UiResources.Pages.PerformanceLab.PerfLabAutomationTestingPage;
import UiResources.Pages.PerformanceLab.PerfLabMainPage;
import UiResources.Pages.PerformanceLab.PerfLabWebsiteTestingPage;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static UiResources.Pages.Google.GoogleMainPage.GOOGLE_URL;
import static UiResources.Pages.Google.GoogleResultsPage.GOOGLE_SEARCH_RESULT_PERFORMANCE_LAB_URL;
import static UiResources.Pages.PerformanceLab.PerfLabAutomationTestingPage.PERFORMANCE_LAB_AUTOMATION_TESTING_URL;
import static UiResources.Pages.PerformanceLab.PerfLabMainPage.PERFORMANCE_LAB_URL;
import static UiResources.Pages.PerformanceLab.PerfLabWebsiteTestingPage.PERFORMANCE_LAB_WEBSITE_TESTING_PAGE_URL;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class UiPageObjectTests {

    @Test
    void findSiteThroughGoogleTest() {
        //Steps
        GoogleMainPage googleMainPage = open(GOOGLE_URL, GoogleMainPage.class);
        GoogleResultsPage googleResultsPagePerformanceLab = googleMainPage.search("performance lab");
        //Tests
        googleResultsPagePerformanceLab.getFirstResult().shouldBe(Condition.visible)
                .shouldHave(Condition.href("https://www.performance-lab.ru/"));
    }

    @Test
    void enterSiteThroughGoogleResultsPageTest() {
        //Steps
        GoogleResultsPage googleResultsPagePerformanceLab = open(GOOGLE_SEARCH_RESULT_PERFORMANCE_LAB_URL, GoogleResultsPage.class);
        PerfLabMainPage perfLabMainPage = googleResultsPagePerformanceLab.goToPerformanceLabSite();
        //Tests
        perfLabMainPage.getLogo().shouldBe(Condition.visible)
                .shouldHave(attribute("title", "Перфоманс Лаб"));
    }


    @Test
    void goFromPerformanceLabMainPageToWebsiteTestingPageTest() {
        //Steps
        PerfLabMainPage perfLabMainPage = open(PERFORMANCE_LAB_URL, PerfLabMainPage.class);
        perfLabMainPage.getProductsAndServicesLi().hover();
        //Tests
        PerfLabWebsiteTestingPage perfLabWebsiteTestingPage = perfLabMainPage.goToWebsiteTesting();
        perfLabWebsiteTestingPage.getMainWebsiteTestingPageText().shouldBe(Condition.visible)
                .shouldHave(text("Комплексное тестирование веб сайтов"));
    }

    @Test
    void websiteTestingPageFindOutThePriceButtonColorTest() {
        //Steps
        PerfLabWebsiteTestingPage perfLabWebsiteTestingPage = open(PERFORMANCE_LAB_WEBSITE_TESTING_PAGE_URL, PerfLabWebsiteTestingPage.class);
        perfLabWebsiteTestingPage.getFindOutThePriceButtonFLD().scrollTo();
        //Tests
        perfLabWebsiteTestingPage.getFindOutThePriceButtonFLD()
                .shouldHave(Condition.cssValue("background-color", "rgba(79, 173, 255, 1)"));
    }

    @Test
    void goFromPerformanceLabMainPageToAutomationTestingPageTest() {
        //Steps
        PerfLabMainPage perfLabMainPage = open(PERFORMANCE_LAB_URL, PerfLabMainPage.class);
        perfLabMainPage.getProductsAndServicesLi().hover();
        //Tests
        PerfLabAutomationTestingPage perfLabAutomationTestingPage = perfLabMainPage.goToAutomationTesting();
        perfLabAutomationTestingPage.getMainAutomationTestingText().shouldBe(Condition.visible)
                .shouldHave(text("Автоматизация тестирования"));
    }

    @Test
    void canOpenContactFormOnAutomationTestingPageTest() {
        //Steps
        PerfLabAutomationTestingPage perfLabAutomationTestingPage = open(PERFORMANCE_LAB_AUTOMATION_TESTING_URL, PerfLabAutomationTestingPage.class);
        perfLabAutomationTestingPage.getExamplesOfCompletedProjectsTXT().scrollTo();
        //Tests
        perfLabAutomationTestingPage.getExamplePdfIMG().click();
        perfLabAutomationTestingPage.goToFormIFrame();
        //Здесь можно очень долго клепать проверки.
        //Оставляю самое важное: элемент формы и его первое поле ввода.
        perfLabAutomationTestingPage.getContactFormFRM().shouldBe(Condition.visible);
        perfLabAutomationTestingPage.getContactFormFirstNameFLD().shouldBe(Condition.visible);
    }
}
