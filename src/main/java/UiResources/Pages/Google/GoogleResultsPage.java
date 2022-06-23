package UiResources.Pages.Google;

import UiResources.Pages.PerformanceLab.PerfLabMainPage;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс с методами первой страницы результатов поиска поисковой системы Google
 */

public class GoogleResultsPage {

    // метод, возвращающий страницу сайта из первого результата поиска.
    // определяет страницу как главную страницу сайта Performance Lab
    public PerfLabMainPage goToPerformanceLabSite() {
        $x("(//div[@class='g']//div[@class='yuRUbf']/a)[1]").click();
        return page(PerfLabMainPage.class);
    }

}
