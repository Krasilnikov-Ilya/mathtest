package ApiResources.HttpUtils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;

import java.io.IOException;

/**
 * Класс, отвечающий за создание и закрытие ответов сервера
 */

public class ResponseUtils {

    // метод создания ответа сервера
    public static CloseableHttpResponse executeRequest(CloseableHttpClient client, ClassicHttpRequest request) {
        CloseableHttpResponse response;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    // метод закрытия ответа сервера
    public static void closeResponse(CloseableHttpResponse response) {
        try {
            response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
