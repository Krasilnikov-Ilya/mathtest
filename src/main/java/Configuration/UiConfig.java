package Configuration;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Данный класс отвечает за установку настроек selenide
 * Метод setUp() использует значения, получаемые от UiConfProperties
 */

public class UiConfig {
    public static void UiSetUp() {
        // определение используемого браузера
        Configuration.browser = ConfProperties.getProperty("browser");
        // определение режима работы браузера
        Configuration.headless = Boolean.parseBoolean(ConfProperties.getProperty("headless"));
        // определение размера окна браузера
        Configuration.browserSize = ConfProperties.getProperty("browserSize");
        // определение лимита времени явного ожидания
        Configuration.timeout = Long.parseLong(ConfProperties.getProperty("timeout"));
        // определение времени ожидания загрузки страницы
        Configuration.pageLoadTimeout = Long.parseLong(ConfProperties.getProperty("pageLoadTimeout"));
        // определение действия браузера по завершению теста
        Configuration.holdBrowserOpen = Boolean.parseBoolean(ConfProperties.getProperty("holdBrowserOpen"));
        // определение стратегии загрузки страницы
        Configuration.pageLoadStrategy = ConfProperties.getProperty("pageLoadStrategy");
        // определение папки для сохранения репортов
        Configuration.reportsFolder = ConfProperties.getProperty("reportsFolder");

        // получение настроек конфигурации для пар ключ-значение
        Boolean VNC = Boolean.valueOf(ConfProperties.getProperty("VNC"));
        Boolean Video = Boolean.valueOf(ConfProperties.getProperty("Video"));

        // создание объекта класса DesiredCapabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // определение поддержки отображения экрана браузера во время выполнения теста
        capabilities.setCapability("enableVNC", VNC);
        // определение записи видео в процессе выполнения тестов
        capabilities.setCapability("enableVideo", Video);

        // Переопределение Browser capabilities
        Configuration.browserCapabilities = capabilities;
    }
}
