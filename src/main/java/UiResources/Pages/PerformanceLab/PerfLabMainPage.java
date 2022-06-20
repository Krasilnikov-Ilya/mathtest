package UiResources.Pages.PerformanceLab;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;


public class PerfLabMainPage {
    public static final String PERFORMANCE_LAB_URL = "https://www.performance-lab.ru/";

    private final SelenideElement logo = $x("//a[@class='navbar-brand logo']");
    private final SelenideElement productsAndServicesLi = $x("//li[@id='menu-item-317']");
    private final SelenideElement websiteTesting = $x("//div[@id='nav_top']//a[text() = 'Тестирование сайта']");
    private final SelenideElement automationTesting = $x("//*[@id='nav_top']//a[text()='Автоматизация тестирования']");
    private final SelenideElement bannerHost = $x("//div[@data-gr='popup-container']");

    public void removeBanner() {
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
