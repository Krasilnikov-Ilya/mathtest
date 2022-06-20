package UiTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static UiResources.Pages.Google.GoogleMainPage.GOOGLE_URL;
import static UiResources.Pages.PerformanceLab.PerfLabMainPage.PERFORMANCE_LAB_URL;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class UiSimpleTests {

    @Test
    void findAndEnterSiteThroughGoogleTest() {
        open(GOOGLE_URL);
        // у performance-lab.ru сейчас не лучшая производительность
        // вообще, 60 может быть лишнего, потому что раз 30 приходит сообщение
        // Timed out receiving message from renderer: 30.000
        Configuration.timeout=60000;
        $("[class='gLFyf gsfi']")
                .shouldBe(Condition.visible)
                .sendKeys("Performance Lab" + Keys.ENTER);

        $("[class='tF2Cxc']>div>a")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.href("https://www.performance-lab.ru/"))
                .click();

        $("[class='navbar-brand logo']")
                .shouldBe(Condition.visible)
                .shouldHave(attribute("title", "Перфоманс Лаб"));
    }

    @Test
    void websiteTestingPriceColorTest() {
        open(PERFORMANCE_LAB_URL);
        Configuration.timeout=60000;
        $x("//li[@id='menu-item-317']")
                .shouldBe(Condition.visible)
                .hover();
        $x("//li[@id='menu-item-141']/a[text()='Автоматизация тестирования']")
                .shouldBe(Condition.visible)
                .click();

        // решил не работать с двумя вкладками.
        switchTo().window(0);
        closeWindow();
        switchTo().window(0);

        $("[data-id='5d071489'] [class*='elementor-button-link']")
                .scrollTo()
                .shouldHave(Condition.cssValue("background-color", "rgba(79, 173, 255, 1)"));
    }

    @Test
    void automationExamplesFormTest() {
        open(PERFORMANCE_LAB_URL);
        Configuration.timeout=60000;
        $x("//li[@id='menu-item-317']")
                .shouldBe(Condition.visible)
                .hover();
        $x("//div[@class='container']/*/*/*/a[text()='Автоматизация тестирования']")
                .click();
        //Вообще, есть у сайта странности. С одной ссылки в новую вкладку кинет, с другой - откроет в текущей.
        $("[class='col-12']>h3[style='text-align: center;']")
                .scrollTo(); // Скроллю именно до текста. Слово в слово.
        $("[class='pdf-block openBrochur']>p>img")
                .shouldBe(Condition.visible)
                .click();
        // переключаюсь к нужному фрейму, последовательно.
        switchTo().frame("hubspot-Modal-Iframe");
        switchTo().frame("hs-form-iframe-0");
        $("[class='hbspt-form']")
                .shouldBe(Condition.visible); // проверка наличия формы
        $("[id^='firstname-ae5']")
                .shouldBe(Condition.visible); // и первого поля
    }

}
