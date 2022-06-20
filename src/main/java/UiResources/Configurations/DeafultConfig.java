package UiResources.Configurations;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DeafultConfig {
    public static void setUp() {
        //Определяем какой браузер будем использовать.
        Configuration.browser = "chrome";
        //Папка для репортов отправляется в target, что бы не копить мусор.
        Configuration.reportsFolder = "target/test-result/reports";
        //И больше ничего не меняем, на то он и Default
    }
}

