package UiTests;

import Configuration.ConfProperties;
import Configuration.UiConfig;
import UiResources.Pages.Google.GoogleMainPage;
import UiResources.Pages.Google.GoogleResultsPage;
import UiResources.Pages.PerformanceLab.PerfLabAutomationTestingPage;
import UiResources.Pages.PerformanceLab.PerfLabMainPage;
import UiResources.Pages.PerformanceLab.PerfLabWebsiteTestingPage;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

/**
 * Реализованные на selenide тесты могут исполняться с разными настройками
 * Для установки настроек используется метод UiSetUp() класса Config
 * Настройки хранятся в файле src/test/resources/conf.properties
 * Адреса доменов тестируемых сайтов так же хранятся в файле настроек
 */

public class UiTests {
    @BeforeAll
    public static void setUp() {
        UiConfig.UiSetUp();
    }

    @Test
    void googleToMainToProductsBlueBTNTest() {

        // открытие браузера, получение главной страницы поисковой системы Google
        GoogleMainPage googleMainPage = open(ConfProperties.getProperty("GOOGLE_URL"), GoogleMainPage.class);

        // осуществление поискового запроса и переход на первую страницу результатов поиска
        GoogleResultsPage googleResultsPagePerformanceLab = googleMainPage.search("performance lab");

        // переход на главную страницу сайта Performance Lab по ссылке из первого результата поиска
        PerfLabMainPage perfLabMainPage = googleResultsPagePerformanceLab.goToPerformanceLabSite();

        // ожидание появления баннера и его удаление
        step("Удаление баннера", perfLabMainPage::removeBanner);

        // открытие подменю "Услуги и продукты"
        perfLabMainPage.getProductsAndServicesLi().hover();

        // переход на страницу "Тестирование сайта"
        PerfLabWebsiteTestingPage perfLabWebsiteTestingPage = perfLabMainPage.goToWebsiteTesting();

        step("Проверка цвета кнопки 'Узнать цену' в колонке корпоративного тарифного плана", () -> {
            // прокрутка страницы до кнопки "Узнать цену" в колонке корпоративного тарифного плана
            perfLabWebsiteTestingPage.getFindOutThePriceButtonFLD().scrollTo();

            // проверка фонового цвета кнопки "Узнать цену" в колонке корпоративного тарифного плана
            perfLabWebsiteTestingPage.getFindOutThePriceButtonFLD()
                    .shouldBe(Condition.or("blue", cssValue("background-color", "rgba(79, 173, 255, 1)"), cssValue("background-color", "rgb(79, 173, 255)")));
        });
    }

    @Test
    void mainToAutomationFormTest() {

        // открытие браузера, получение главной страницы сайта "Performance Lab"
        PerfLabMainPage perfLabMainPage = open(ConfProperties.getProperty("PERFORMANCE_LAB_URL"), PerfLabMainPage.class);

        // ожидание появления баннера и его удаление
        step("Удаление баннера", perfLabMainPage::removeBanner);

        // открытие подменю "Услуги и продукты"
        perfLabMainPage.getProductsAndServicesLi().hover();

        // переход на страницу "Автоматизация тестирования"
        PerfLabAutomationTestingPage perfLabAutomationTestingPage = perfLabMainPage.goToAutomationTesting();

        step("Проверка вызова заполняемой формы", () -> {
            // прокрутка страницы до заголовка "Примеры выполненных проектов" над изображением, вызывающим заполняемую форму
            perfLabAutomationTestingPage.getExamplesOfCompletedProjectsTXT().scrollTo();

            // открытие заполняемой формы через клик по изображению
            perfLabAutomationTestingPage.getExamplePdfIMG().click();

            // переключение на заполняемую форму
            perfLabAutomationTestingPage.goToFormIFrame();

            // проверка видимости контейнера заполняемой формы
            perfLabAutomationTestingPage.getContactFormFRM().shouldBe(Condition.visible);

            // проверка видимости поля "Имя" заполняемой формы
            perfLabAutomationTestingPage.getContactFormFirstNameFLD().shouldBe(Condition.visible);
        });
    }

}
