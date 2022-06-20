package UiResources.Pages.PerformanceLab;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static UiResources.Pages.PerformanceLab.PerfLabMainPage.PERFORMANCE_LAB_URL;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class PerfLabAutomationTestingPage {
    public static final String HUBSPOT_MODAL_IFRAME = "hubspot-Modal-Iframe";
    public static final String FORM_0_IFRAME = "hs-form-iframe-0";
    private final SelenideElement mainAutomationTestingText = $x("//div[@class='single-page']//h1");
    private final SelenideElement examplesOfCompletedProjectsTXT = $x("//h3[text()='Примеры выполненных проектов']");
    private final SelenideElement examplePdfIMG = $x("//img[@alt='автоматизация тестирования']");
    private final SelenideElement contactFormFRM = $x("//div[@class='hbspt-form']");
    private final SelenideElement contactFormFirstNameFLD = $x("//input[starts-with(@id, 'firstname-ae5')]");

    public SelenideElement getExamplesOfCompletedProjectsTXT() {
        return examplesOfCompletedProjectsTXT;
    }

    public SelenideElement getExamplePdfIMG() {
        return examplePdfIMG;
    }

    public SelenideElement getContactFormFRM() {
        return contactFormFRM;
    }

    public SelenideElement getContactFormFirstNameFLD() {
        return contactFormFirstNameFLD;
    }

    public void goToFormIFrame() {
        switchTo().frame(HUBSPOT_MODAL_IFRAME);
        switchTo().frame(FORM_0_IFRAME);
    }

}
