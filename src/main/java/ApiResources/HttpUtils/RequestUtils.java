package ApiResources.HttpUtils;


import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpHeaders;

/**
 * Класс, отвечающий за создание и изменение HTTP запросов
 */

public class RequestUtils {

    // метод, создающий запрос
    public static ClassicHttpRequest createHTTPGet(String host,String address) {
        return new HttpGet(host + address);
    }

    // метод, добавляющий хедер Content-Type: Json к запросу
    public static void addHeaderContentTypeJson(ClassicHttpRequest request) {
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    }

}
