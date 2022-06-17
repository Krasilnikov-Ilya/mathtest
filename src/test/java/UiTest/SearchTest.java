package UiTest;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class SearchTest {

    @Test
    void e2etest() {
        open("https://google.com");
        $("[class='gLFyf gsfi']")
                .shouldBe(Condition.visible)
                .sendKeys("Perfomance Lab" + Keys.ENTER);

        $("[class='tF2Cxc']>div>a")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.href("https://www.performance-lab.ru/"))
                .click();
    }
}
