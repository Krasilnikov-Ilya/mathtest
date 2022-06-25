package ApiResources.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Данный класс отвечает за реализацию получения настроек
 * Из файла conf.properties
 * И передачу их значений другим классам.
 *
 * Несмотря на идентичность классу UiConfProperties,
 * Данный класс необходим для возможности реализации
 * Обращения к конкретному файлу настроек бэкэнда
 * В случае, если реализовано отдельное хранение конфигураций.
 */

public class ApiConfProperties {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    static {
        try {
            //указание пути до файла с настройками
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //обработка возможного исключения
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    //метод для возврата строки со значением из файла с настройками
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
