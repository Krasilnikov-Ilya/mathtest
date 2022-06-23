package UiResources.Pages.PerformanceLab;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс с методами главной страницы сайта Performance Lab
 */

public class PerfLabMainPage {

    // метод, удаляющий элемент: контейнер, содержащий баннер
    public void removeBanner() {
        executeJavaScript("""
            var element = arguments[0];
            element.parentNode.removeChild(element);
            """, $x("//div[@data-gr='popup-container']"));
    }

    // метод, возвращающий элемент: элемент списка "услуги и продукты" в хэдере сайта
    // элемент списка используется для развёртывания подменю методом .hover()
    public SelenideElement getProductsAndServicesLi() {
        return $x("//li[@id='menu-item-317']");
    }

    // метод, возвращающий страницу "Тестирование сайта"
    // в связи с открытием страницы в новой вкладке, закрывает старую вкладку и переключается на новую
    public PerfLabWebsiteTestingPage goToWebsiteTesting() {
        $x("//div[@id='nav_top']//a[text() = 'Тестирование сайта']").click();
        switchTo().window(0);
        closeWindow();
        switchTo().window(0);
        return page(PerfLabWebsiteTestingPage.class);
    }

    // метод, возвращающий страницу "Автоматизация тестирования"
    public PerfLabAutomationTestingPage goToAutomationTesting() {
        $x("//*[@id='nav_top']//a[text()='Автоматизация тестирования']").click();
        // здесь редирект происходит для текущей вкладки
        return page(PerfLabAutomationTestingPage.class);
    }


}
