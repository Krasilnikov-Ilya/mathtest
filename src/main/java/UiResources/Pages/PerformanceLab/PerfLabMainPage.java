package UiResources.Pages.PerformanceLab;

import UiResources.Pages.BasePage.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class PerfLabMainPage extends BasePage {
    public static final String PERFORMANCE_LAB_URL = "https://www.performance-lab.ru/";

    private static SelenideElement logo = $x("//a[@class='navbar-brand logo']");
    private static SelenideElement productsAndServicesLi = $x("//li[@id='menu-item-317']");
    private static SelenideElement websiteTesting = $x("//div[@id='nav_top']//a[text() = 'Тестирование сайта']");
    private static SelenideElement automationTesting = $x("//*[@id='nav_top']//a[text()='Автоматизация тестирования']");

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
