package apachehttpclient5;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class ApacheHttp5 {
    public static CloseableHttpClient createClient() { // создание клиента
        return HttpClients.createDefault();
    }

    public static ClassicHttpRequest createGetWithHeader() { // создание запроса
        ClassicRequestBuilder reqbuilder = ClassicRequestBuilder.get()
                .setUri("http://77.50.236.203:4880/users")
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return reqbuilder.build();
    }

    public static void saveJson() throws IOException {
        // это для сохранения Json отдельные действия, что бы не мусорить в тесте.
        CloseableHttpClient client = ApacheHttp5.createClient(); // создание клиента
        ClassicHttpRequest getUsersJson = ApacheHttp5.createGetWithHeader(); // создание запроса
        CloseableHttpResponse response = client.execute(getUsersJson); // объявление и инициализация ответа
        HttpEntity entity = response.getEntity(); // объявление и инициализация содержимого
        client.close(); // закрытие клиента
        String fileName = "src/main/resources/perfuserspage.json"; // что и куда пишем
        FileOutputStream out = new FileOutputStream(fileName); // поток для записи
        entity.writeTo(out); // запись Json
    }

}
