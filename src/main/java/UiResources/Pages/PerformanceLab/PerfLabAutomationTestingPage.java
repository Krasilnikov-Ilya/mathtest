package UiResources.Pages.PerformanceLab;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Класс с методами страницы "Автоматизация тестирования" сайта Performance Lab
 */

public class PerfLabAutomationTestingPage {

    // метод, переключающий фреймы: переключает на заполняемую форму после её открытия
    public void goToFormIFrame() {
        switchTo().frame("hubspot-Modal-Iframe");
        switchTo().frame("hs-form-iframe-0");
    }

    // метод, возвращающий элемент: контейнер заполняемой формы
    public SelenideElement getContactFormFRM() {
        return $x("//div[@class='hbspt-form']");
    }

    // метод, возвращающий элемент: заполняемое поле формы, "имя"
    public SelenideElement getContactFormFirstNameFLD() {
        return $x("//input[starts-with(@id, 'firstname-ae5')]");
    }

    // метод, возвращающий элемент: заголовок над изображением, вызывающим заполняемую форму
    public SelenideElement getExamplesOfCompletedProjectsTXT() {
        return $x("//h3[text()='Примеры выполненных проектов']");
    }

    //  метод, возвращающий элемент: кликабельное изображение, вызывающее заполняемую форму
    public SelenideElement getExamplePdfIMG() {
        return $x("//img[@alt='автоматизация тестирования']");
    }


}
