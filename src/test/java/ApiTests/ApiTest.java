package ApiTests;

import ApiResources.HttpUtils.*;
import ApiResources.JdbcUtils.*;
import Configuration.ConfProperties;
import org.apache.hc.core5.http.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ApiResources.Models.User;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static io.qameta.allure.Allure.step;

class ApiTest {

    @Test
    public void usersApiTest() {

        // создание клиента
        CloseableHttpClient client = ClientUtils.createClient();
        // создание запроса
        ClassicHttpRequest request = RequestUtils.createHTTPGet(ConfProperties.getProperty("API_HOST")+ConfProperties.getProperty("API_PORT"),"/users");
        // добавление хедера Content-Type: Json к запросу
        RequestUtils.addHeaderContentTypeJson(request);
        // объявление и инициализация ответа сервера
        CloseableHttpResponse response = ResponseUtils.executeRequest(client, request);

        step("проверка ответа сервера", () -> {
            // проверка кода ответа сервера
            Assertions.assertEquals(200, response.getCode());

            // проверка хэдера типа данных в ответе сервера
            Assertions.assertEquals("Content-Type: application/json", response.getHeader(HttpHeaders.CONTENT_TYPE).toString());
        });

        // получение тела ответа сервера
        HttpEntity entity = EntityUtils.createHttpEntity(response);

        // создание списка пользователей из тела ответа
        List<User> usersPageRootAPI = UsersListGsonCreater.createUsersListWithGson(entity);

        step("проверка наличия пользователей в списке пользователей API", () -> {
            // проверка наличия пользователей в списке
            Assertions.assertNotEquals(usersPageRootAPI.size(), 0);
        });

        // закрытие тела ответа сервера
        ResponseUtils.closeResponse(response);
        // закрытие ответа сервера
        EntityUtils.closeEntity(entity);
        // закрытие клиента
        ClientUtils.closeClient(client);

    }

    @Test
    public void usersSqlTest() {

        // создание подключения
        Connection connection = ConnectionUtils.createJdbcConnection(
                ConfProperties.getProperty("JDBC_HOST") + ConfProperties.getProperty("JDBC_PORT") + "/pflb_trainingcenter",
                ConfProperties.getProperty("JDBC_NAME"),
                ConfProperties.getProperty("JDBC_PASSWORD"));

        // создание выражения
        Statement statement = StatementUtils.createStatement(connection);
        // создание результата запроса
        ResultSet resultSet = ResultUtils.getResultSet(statement,
                "SELECT * FROM person");

        // создание списка пользователей из результата запроса
        List<User> usersPageRootSQL = UsersListJdbcCreater.createUsersList(resultSet);

        step("проверка наличия пользователей в списке пользователей SQL", () -> {
            // проверка наличия пользователей в списке
            Assertions.assertNotEquals(usersPageRootSQL.size(), 0);
        });

        // закрытие результата запроса
        ResultUtils.closeResultSet(resultSet);
        // закрытие выражения
        StatementUtils.closeStatement(statement);
        // закрытие подключения
        ConnectionUtils.closeJdbcConnection(connection);
    }



    @Test
    public void apiAndSqlTest() {

        // создание подключения
        Connection connection = ConnectionUtils.createJdbcConnection(
                ConfProperties.getProperty("JDBC_HOST") + ConfProperties.getProperty("JDBC_PORT") + "/pflb_trainingcenter",
                ConfProperties.getProperty("JDBC_NAME"),
                ConfProperties.getProperty("JDBC_PASSWORD"));
        // создание выражения
        Statement statement = StatementUtils.createStatement(connection);
        // создание результата запроса
        ResultSet resultSet = ResultUtils.getResultSet(statement,
                "SELECT * FROM person");
        // создание списка пользователей из результата запроса
        List<User> usersPageRootSQL = UsersListJdbcCreater.createUsersList(resultSet);
        // закрытие результата запроса
        ResultUtils.closeResultSet(resultSet);
        // закрытие выражения
        StatementUtils.closeStatement(statement);
        // закрытие подключения
        ConnectionUtils.closeJdbcConnection(connection);

        // создание клиента
        CloseableHttpClient client = ClientUtils.createClient();
        // создание запроса
        ClassicHttpRequest request = RequestUtils.createHTTPGet(ConfProperties.getProperty("API_HOST")+ConfProperties.getProperty("API_PORT"),"/users");
        // добавление хедера Content-Type: Json к запросу
        RequestUtils.addHeaderContentTypeJson(request);
        // объявление и инициализация ответа сервера
        CloseableHttpResponse response = ResponseUtils.executeRequest(client, request);
        // получение тела ответа сервера
        HttpEntity entity = EntityUtils.createHttpEntity(response);
        // создание списка пользователей из тела ответа
        List<User> usersPageRootAPI = UsersListGsonCreater.createUsersListWithGson(entity);
        // закрытие тела ответа сервера
        ResponseUtils.closeResponse(response);
        // закрытие ответа сервера
        EntityUtils.closeEntity(entity);
        // закрытие клиента
        ClientUtils.closeClient(client);


        step("Проверка идентичности списков пользователей, полученных из API и SQL",() -> {
            // проверка соответствия размеров списков пользователей, полученных из API и SQL
            Assertions.assertEquals(usersPageRootAPI.size(), usersPageRootSQL.size());

            // проверка идентичности списков пользователей, полученных из API и SQL
            Assertions.assertTrue(usersPageRootAPI.containsAll(usersPageRootSQL));
            Assertions.assertTrue(usersPageRootSQL.containsAll(usersPageRootAPI));
        });

    }

}
