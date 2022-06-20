package UiResources.Pages.PerformanceLab;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PerfLabWebsiteTestingPage {

    private final SelenideElement findOutThePriceButtonFLD = $x("//div[@data-id='5d071489']//a[contains(@class, 'elementor-button-link')]");

    public SelenideElement getFindOutThePriceButtonFLD() {
        return findOutThePriceButtonFLD;
    }

}

