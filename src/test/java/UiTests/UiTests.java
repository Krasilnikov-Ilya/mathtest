package UiTests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class UiTests {

    /**
     * К "ванильному" селениуму я больше возвращаться по своей воле точно не буду.
     * Selenide просто шикарен. Вообще, я почувствовал себя динозавром, который нормальных фреймворков не видел.
     * Я поначалу хотел поработать с Xpath, но побоялся, что создание @FindBy Xpath "" сделает тест только хуже.
     * CSS принимаются без аннотаций, к тому же используются не больше одного раза.
     */

    private static final String PERFOMANCE_LAB_URL = "https://www.performance-lab.ru/";
    private static final String GOOGLE_URL = "https://google.com";

    @Test
    void findAndEnterSiteThroughGoogleTest() {
        open(GOOGLE_URL);
        $("[class='gLFyf gsfi']")
                .shouldBe(Condition.visible)
                .sendKeys("Perfomance Lab" + Keys.ENTER);

        $("[class='tF2Cxc']>div>a")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.href("https://www.performance-lab.ru/"))
                .click();

        $("[class='navbar-brand logo']")
                .shouldBe(Condition.visible)
                .shouldHave(attribute("title", "Перфоманс Лаб"));
    }

    @Test
    void siteTestingPriceColorTest() {
        open(PERFOMANCE_LAB_URL);
        $("[id='menu-item-317']")
                .shouldBe(Condition.visible)
                .hover(); // вообще кайф
        $("[class='container']>*>*>[class*='menu-item-8248']>a")
                .shouldBe(Condition.visible)
                .click();

        // решил не работать с двумя вкладками.
        switchTo().window(0);
        closeWindow();
        switchTo().window(0);

        $("[data-id='5d071489'] [class*='elementor-button-link']")
                .scrollTo() // и никаких "execute java script", я не могу нарадоваться :)
                // пробовал через HEX #4FADFF, пришлось привести к RGBA. Но я проверил, цвета одинаковые.
                .shouldHave(Condition.cssValue("background-color", "rgba(79, 173, 255, 1)"));
    }

    @Test
    void automationExamplesFormTest() {
        open(PERFOMANCE_LAB_URL);

        $("[id='menu-item-317']")
                .shouldBe(Condition.visible)
                .hover();
        $("[class='container']>*>*>[class*='menu-item-141']>a")
                .click();
        //Вообще, есть у сайта странности. С одной ссылки в новую вкладку кинет, с другой - откроет в текущей.
        $("[class='col-12']>h3[style='text-align: center;']")
                .scrollTo(); // Скроллю именно до текста. Слово в слово.
        $("[class='pdf-block openBrochur']>p>img")
                .shouldBe(Condition.visible)
                .click();
        // Смерть кощея в игле. Игла в яйце.
        switchTo().frame("hubspot-Modal-Iframe");
        // Яйцо в утке.
        switchTo().frame("hs-form-iframe-0");
        // Утка в зайце.
        $("[class='hbspt-form']")
                .shouldBe(Condition.visible);
        // Где заяц?
        $("[id^='firstname-ae5']") // Заяц в шоке.
                .shouldBe(Condition.visible);
    }
}
