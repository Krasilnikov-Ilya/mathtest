package UiResources.Pages.PerformanceLab;

import UiResources.Pages.BasePage.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

    /**
     * Я очень долго мучался с этим баннером.
     * Из-за закрытой теневой ветки не помогают теневые селекторы ByShadowCss
     * А следовательно, не поможет и обычный метод получения доступа через JS,
     * Ведь селекторы теневого древа его по сути и используют.
     * Остаются только решения "по-плохому":
     * Или забить localstore отметками о прохождении и̶в̶е̶н̶т̶о̶в̶ ̶п̶р̶о̶с̶м̶о̶т̶р̶а̶ ̶б̶а̶н̶н̶е̶р̶а̶ шести кругов ада, что ненадёжно,
     * ведь сайт через N-ное время может решить, что сеансы устарели и надо сновать дать баннер.
     * Или снести к чёртовой матери родителя баннера вместе с баннером,
     * что не совсем "по-тестировщицки", потому что ломать один функционал
     * ради проверки другого - не совсем правильный подход.
     * Мой выбор пал на второй вариант, потому что НЕНАВИЖУ.
     */

public class PerfLabMainPage extends BasePage {
    public static final String PERFORMANCE_LAB_URL = "https://www.performance-lab.ru/";

    private static SelenideElement logo = $x("//a[@class='navbar-brand logo']");
    private static SelenideElement productsAndServicesLi = $x("//li[@id='menu-item-317']");
    private static SelenideElement websiteTesting = $x("//div[@id='nav_top']//a[text() = 'Тестирование сайта']");
    private static SelenideElement automationTesting = $x("//*[@id='nav_top']//a[text()='Автоматизация тестирования']");
    private static SelenideElement bannerHost = $x("//div[@data-gr='popup-container']");

    public void removeFuckingBanner() {
        executeJavaScript("""
            var element = arguments[0];
            element.parentNode.removeChild(element);
            """, bannerHost); // надеюсь, больше не увидимся.
    }

    public SelenideElement getLogo() {
        return logo;
    }

    public SelenideElement getProductsAndServicesLi() {
        return productsAndServicesLi;
    }

    public PerfLabWebsiteTestingPage goToWebsiteTesting() {
        websiteTesting.click();
        // данные действия необходимы в связи с открытием новой вкладки
        switchTo().window(0);
        closeWindow();
        switchTo().window(0);
        return page(PerfLabWebsiteTestingPage.class);
    }

    public PerfLabAutomationTestingPage goToAutomationTesting() {
        automationTesting.click();
        // здесь редирект происходит для текущей вкладки
        return page(PerfLabAutomationTestingPage.class);
    }


}
