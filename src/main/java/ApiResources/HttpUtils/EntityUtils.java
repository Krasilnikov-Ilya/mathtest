package ApiResources.HttpUtils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;

import java.io.IOException;

/**
 * Класс, отвечающий за создание и закрытие ответов сервера
 */

public class EntityUtils {

    // метод создания ответа сервера
    public static HttpEntity createHttpEntity(CloseableHttpResponse response) {
        return response.getEntity();
    }

    // метод закрытия ответа сервера
    public static void closeEntity(HttpEntity entity) {
        try {
            entity.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}