package utils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;

public class JsonRecorder {
    public static void saveJson() throws IOException {
        CloseableHttpClient client = ApacheHttp5.createClient(); // создание клиента
        ClassicHttpRequest getUsersJson = ApacheHttp5.createGetWithHeader(); // создание запроса
        CloseableHttpResponse response = client.execute(getUsersJson); // объявление и инициализация ответа
        HttpEntity entity = response.getEntity(); // содержимое
        String fileName = "src/main/resources/perfuserspage.json"; // путь к файлу и тип
        FileOutputStream out = new FileOutputStream(fileName); // поток записи
        entity.writeTo(out); // запись
        EntityUtils.consume(entity);
        client.close(); // закрытие клиента
    }
}
