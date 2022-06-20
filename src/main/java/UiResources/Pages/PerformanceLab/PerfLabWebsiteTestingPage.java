package UiResources.Pages.PerformanceLab;

import UiResources.Pages.BasePage.BasePage;
import com.codeborne.selenide.SelenideElement;

import static UiResources.Pages.PerformanceLab.PerfLabMainPage.PERFORMANCE_LAB_URL;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class PerfLabWebsiteTestingPage extends BasePage {
    public static final String PERFORMANCE_LAB_WEBSITE_TESTING_PAGE_URL = PERFORMANCE_LAB_URL + "/website-testing";

    private final SelenideElement mainWebsiteTestingPageText = $x("//div[@class='elementor-section-wrap']//h1");
    private final SelenideElement findOutThePriceButtonFLD = $x("//div[@data-id='5d071489']//a[contains(@class, 'elementor-button-link')]");

    public SelenideElement getMainWebsiteTestingPageText() {
        return mainWebsiteTestingPageText;
    }

    public SelenideElement getFindOutThePriceButtonFLD() {
        return findOutThePriceButtonFLD;
    }



}

