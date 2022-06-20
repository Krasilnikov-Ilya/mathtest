package UiResources.Pages.PerformanceLab;

import com.codeborne.selenide.SelenideElement;

import static UiResources.Pages.PerformanceLab.PerfLabMainPage.PERFORMANCE_LAB_URL;
import static com.codeborne.selenide.Selenide.$x;

public class PerfLabWebsiteTestingPage {

    private final SelenideElement mainWebsiteTestingPageText = $x("//div[@class='elementor-section-wrap']//h1");
    private final SelenideElement findOutThePriceButtonFLD = $x("//div[@data-id='5d071489']//a[contains(@class, 'elementor-button-link')]");

    public SelenideElement getFindOutThePriceButtonFLD() {
        return findOutThePriceButtonFLD;
    }

}

