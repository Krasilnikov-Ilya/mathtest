package ApiResources.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Данный метод отвечает за создание набора данных по результатам запроса
 */

public class ResultSetCreater {

    // метод для получения результирующего набора данных
    public ResultSet getResultSet(Statement statement, String query) {
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
    public void closeResultSet(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

