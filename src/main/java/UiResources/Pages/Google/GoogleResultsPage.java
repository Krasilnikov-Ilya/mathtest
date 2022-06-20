package UiResources.Pages.Google;

import UiResources.Pages.PerformanceLab.PerfLabMainPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class GoogleResultsPage {

    private final SelenideElement firstResultLNK = $x("(//div[@class='g']//div[@class='yuRUbf']/a)[1]");

    public PerfLabMainPage goToPerformanceLabSite() {
        firstResultLNK.click();
        return page(PerfLabMainPage.class);
    }

}
