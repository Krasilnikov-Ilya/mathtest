package UiResources.Pages.Google;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class GoogleMainPage {
    public static final String GOOGLE_URL = "https://google.com";
    private final SelenideElement searchFLD = $x("//input[@title='Поиск']");

    public GoogleResultsPage search(String query) {
        searchFLD.sendKeys(query + Keys.ENTER);
        return page(GoogleResultsPage.class);
    }


}
