package apitest;

import JDBC.JDBCRecorder;
import apachehttpclient5.ApacheHttp5;
import apachehttpclient5.JsonRecorder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;

import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ProtocolException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import resources.GsonParser;
import resources.models.UsersPageRootAPI;
import resources.models.UsersPageRootSQL;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Очень надеюсь, что тебя устроит как я тут прибрался.
 */

public class ApiTest {
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
    public void jsonTest() throws IOException {
        JsonRecorder.saveJson();
        Object usersPageRootAPI = GsonParser.parse();
        Assertions.assertNotNull(usersPageRootAPI);
    }


    @Test
    public void sqlTest() {
        Object usersPageRootSQL = JDBCRecorder.recordListFromJDBC();
        Assertions.assertNotNull(usersPageRootSQL);
    }

    @Test
    public void apiAndSqlTest() throws IOException {
        JsonRecorder.saveJson();
        Object usersPageRootAPI = GsonParser.parse();
        Object usersPageRootSQL = JDBCRecorder.recordListFromJDBC();
        Assertions.assertEquals(usersPageRootAPI.toString(), usersPageRootSQL.toString());
    }
}