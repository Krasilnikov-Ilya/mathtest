package UiResources.Pages.PerformanceLab;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс с методами страницы "Тестирование сайта" сайта Performance Lab
 */

public class PerfLabWebsiteTestingPage {

    // метод, возвращающий элемент: поле кнопки "узнать цену" в колонке корпоративного тарифного плана
    public SelenideElement getFindOutThePriceButtonFLD() {
        return $x("//div[@data-id='5d071489']//a[contains(@class, 'elementor-button-link')]");
    }

}

