package apitest;

import org.apache.hc.core5.http.*;
import utils.JDBCRecorder;
import utils.ApacheHttp5;
import utils.JsonRecorder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import models.User;


import java.io.*;
import java.util.List;

/**
 * Очень надеюсь, что тебя устроит как я тут прибрался.
 */

class ApiTest {
    @Test
    public void usersTest() throws IOException, ProtocolException {
        CloseableHttpClient client = ApacheHttp5.createClient(); // создание клиента
        ClassicHttpRequest getUsersJson = ApacheHttp5.createGetWithHeader(); // создание запроса
        CloseableHttpResponse response = client.execute(getUsersJson); // объявление и инициализация ответа
        Assertions.assertEquals(200, response.getCode()); // проверка на код ответа
        Assertions.assertEquals("Content-Type: application/json",
                response.getHeader(HttpHeaders.CONTENT_TYPE).toString());// проверка хедера ответа
        HttpEntity entity = response.getEntity(); // объявление и инициализация содержимого
        Assertions.assertNotNull(entity); // проверка существования содержимого
        client.close(); // закрытие клиента
    }

    @Test
    public void apiAndSqlTest() throws IOException, ParseException {
        List<User> usersPageRootAPI = JsonRecorder.saveJson();
        List<User> usersPageRootSQL = JDBCRecorder.recordListFromJDBC();
        // идея ругалась на возможное возникновение ошибки NullPointerException, пришлось добавить
        Assertions.assertTrue((usersPageRootAPI != null) && (usersPageRootSQL != null));
        Assertions.assertEquals(usersPageRootAPI.size(), usersPageRootSQL.size());
        Assertions.assertTrue(usersPageRootAPI.containsAll(usersPageRootSQL));
        Assertions.assertTrue(usersPageRootSQL.containsAll(usersPageRootAPI));
    }
}
