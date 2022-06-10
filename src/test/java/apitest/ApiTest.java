package apitest;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.*;

/**
 * Короче я абсолютно не понимаю, что именно ты хотел от меня получить.
 * Я получил URL https://git.performance-lab.ru/ на который нужно заходить под корпоративным аккаунтом.
 * Было решено заниматься клиентом, а не ждать решения вопроса с адресом.
 * По этой причине здесь будет использован дрянной сайт, который не умеет хранить результаты запросов.
 * Для метода GET это абсолютно не важно, но POST, PUT и т.д. пострадают.
 * Ведь ответ на запрос хранит изменения только до следующего запроса.
 * В общем: здесь не будет E2E сценариев.
 *
 * Ещё я решился таки использовать Gson для работы с реальным файлом, раз уж я его получаю.
 * После запуска ApiTest сохраняется файл userspage2.json, GsonJson его выводит в консоль.
 */

public class ApiTest {
    /*
    код собран из фрагментов официальных документов, удочка определённо хорошая
     */
    public static void main(final String[] args) throws Exception { // главный метод, перехват ошибки
        try (final CloseableHttpClient httpclient = HttpClients.createDefault()) { // объявляю клиент
            final HttpGet httpGet = new HttpGet("https://reqres.in/api/users?page=2"); // метод GET и URL
            try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) { // объявляю ответ, вышесозданное
                System.out.println(response1.getCode() + " " + response1.getReasonPhrase()); // вывод кода ответа сервера
                final HttpEntity entity1 = response1.getEntity(); // объявляю содержимое ответа
                System.out.println(entity1.toString()); // вывожу информацию о содержимом
                System.out.println(entity1.getContentType()); // другим способом смотрю, что внутри Json
                String fileName = "src/main/resources/userspage2.json"; // придумываю ему название и место
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