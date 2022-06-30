package ApiTests;

import ApiResources.HttpUtils.PerformanceLabApi;
import ApiResources.JdbcUtils.PerformanceLabJdbc;
import ApiResources.Models.Car;
import ApiResources.Models.User;
import Configuration.ConfProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ApiTests {

    @Test
    public void usersApiTest() {
        List<User> userListAPI = PerformanceLabApi.getResult("/users", User.class);

        List<User> userListSQL = PerformanceLabJdbc.getResult(ConfProperties.getProperty("SQL_QUERY_USER_BOOLEAN_CONVERTION"), User.class);

        Assertions.assertEquals(userListAPI.size(), userListSQL.size());

        Assertions.assertTrue(userListAPI.containsAll(userListSQL));
        Assertions.assertTrue(userListSQL.containsAll(userListAPI));

    }

    @Test
    public void usersCarTest() {
        List<Car> carListAPI = PerformanceLabApi.getResult("/cars", Car.class);

        List<Car> carListSQL = PerformanceLabJdbc.getResult(ConfProperties.getProperty("SQL_QUERY_CAR_WITH_ENGINE_TYPE"), Car.class);

        Assertions.assertEquals(carListAPI.size(), carListSQL.size());

        Assertions.assertTrue(carListAPI.containsAll(carListSQL));
        Assertions.assertTrue(carListSQL.containsAll(carListAPI));
    }
}

