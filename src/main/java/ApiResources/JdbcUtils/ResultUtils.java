package ApiResources.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс, отвечающий за создание и закрытие результирующих наборов данных
 */

public class ResultUtils {

    // метод для получения результирующего набора данных
    public static ResultSet getResultSet(Statement statement, String query) {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(
                    query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    // метод для закрытия результирующего набора данных
    public static void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
