package apitest;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.*;

/**
 * Я этот код просто выстрадал, потому что разница между HTTPClient5 и HTTPClient4 весьма существенна.
 * В частности - билдер теперь не один, а HttpUriRequest больше не используется.
 * Как следствие - приходится работать без гайдов "для тупых", а у меня с этим плохо.
 */

public class ApiTest {
    public static void main(final String[] args) throws Exception { // главный метод, перехват ошибки
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) { // объявляю клиент
            // Создаю объект "билдер запросов" и сразу обозначаю метод
            ClassicRequestBuilder reqbuilder = ClassicRequestBuilder.get(); // На четвёртом клиенте всё проще
            // Заполняю URL
            ClassicRequestBuilder reqbuilder1 = reqbuilder.setUri("http://77.50.236.203:4880/users");
            // Добавляю хедер с типом контента. Я так понимаю, это для опыта, ведь Json приходит и без него.
            ClassicRequestBuilder reqbuilder2 = reqbuilder1.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            // Созданию запрос используя билдер
            ClassicHttpRequest httpGet = reqbuilder2.build();
            try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) { // объявляю ответ
                System.out.println(response1.getCode() + " " + response1.getReasonPhrase()); // вывод кода ответа сервера
                final HttpEntity entity1 = response1.getEntity(); // объявляю содержимое ответа
                System.out.println(entity1.toString()); // вывожу информацию о содержимом
                System.out.println(entity1.getContentType()); // другим способом смотрю, что внутри Json
                String fileName = "src/main/resources/perfuserspage.json"; // придумываю ему название и место
                FileOutputStream out = new FileOutputStream(fileName); // объявляю поток для записи
                entity1.writeTo(out); // записываю Json
                EntityUtils.consume(entity1); // вообще не понял что это, надеюсь, объяснишь
            }

            /*
            final HttpPost httpPost = new HttpPost("http://httpbin.org/post"); // пусть лежит на будущее
            final List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));

            try (final CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
                System.out.println(response2.getCode() + " " + response2.getReasonPhrase());
                final HttpEntity entity2 = response2.getEntity();
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            }
             */
        }
    }

}