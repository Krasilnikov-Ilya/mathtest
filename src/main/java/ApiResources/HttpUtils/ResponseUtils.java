package ApiResources.HttpUtils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;

import java.io.IOException;

/**
 * Класс, отвечающий за создание и закрытие тела ответов сервера
 */

public class ResponseUtils {

    // метод создания тела ответа сервера
    public static CloseableHttpResponse executeRequest(CloseableHttpClient client, ClassicHttpRequest request) {
        CloseableHttpResponse response;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    // метод закрытия тела ответа сервера
    public static void closeResponse(CloseableHttpResponse response) {
        try {
            response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
