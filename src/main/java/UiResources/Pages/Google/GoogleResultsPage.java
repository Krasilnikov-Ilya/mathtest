package UiResources.Pages.Google;

import UiResources.Pages.PerformanceLab.PerfLabMainPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class GoogleResultsPage extends GoogleMainPage {

    public static String GOOGLE_SEARCH_RESULT_PERFORMANCE_LAB_URL = GOOGLE_URL + "/search?q=" + searchQuery.replaceAll(" +", "+");
    private final SelenideElement firstResultLNK = $x("(//div[@class='g']//div[@class='yuRUbf']/a)[1]");


    public SelenideElement getFirstResult() {
        return firstResultLNK;
    }

    public PerfLabMainPage goToPerformanceLabSite() {
        firstResultLNK.click();
        return page(PerfLabMainPage.class);
    }

}
