package UiResources.Configurations;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

//мой основной конфиг для сайта PerformanceLab
public class PerformanceGlitchConfig {
    public static void setUp() {
        //Определяем какой браузер будем использовать.
        Configuration.browser = "chrome";
        //Размер окна браузера, будем считать его самым популярным.
        Configuration.browserSize = "1920x1080";
        //Самое главное отличие - таймауты
        Configuration.timeout = 30000;
        //Создаём объект класса DesiredCapabilities, используется как настройка конфигурации с помощью пары ключ-значение
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //Включить поддержку отображения экрана браузера во время выполнения теста
        capabilities.setCapability("enableVNC", true);
        //Включение записи видео в процессе выполнения тестов
        capabilities.setCapability("enableVideo", false);
        //Переопределяем Browser capabilities
        Configuration.browserCapabilities = capabilities;
    }
}
