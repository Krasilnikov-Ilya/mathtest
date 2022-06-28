package ApiTests;

import ApiResources.HttpUtils.PerformanceLabApi;
import ApiResources.JdbcUtils.PerformanceLabJdbc;
import ApiResources.Models.User;
import Configuration.ConfProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ApiTests {

    @Test
    public void usersApiTest() {
        List<User> userListAPI = PerformanceLabApi.getUsers("/users", User.class);

        List<User> userListSQL = PerformanceLabJdbc.getUsers(ConfProperties.getProperty("SQL_QUERY_USER_BOOLEAN_CONVERTION"), User.class);

        Assertions.assertEquals(userListAPI.size(), userListSQL.size());

        Assertions.assertTrue(userListAPI.containsAll(userListSQL));
        Assertions.assertTrue(userListSQL.containsAll(userListAPI));

    }

}

