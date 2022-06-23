package UiResources.Pages.Google;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс с методами главной страницы поисковой системы Google
 */

public class GoogleMainPage {

    // метод, возвращающий страницу результатов поиска используя переданный текст запроса.
    public GoogleResultsPage search(String query) {
        $x("//input[@title='Поиск']").sendKeys(query + Keys.ENTER);
        return page(GoogleResultsPage.class);
    }


}
