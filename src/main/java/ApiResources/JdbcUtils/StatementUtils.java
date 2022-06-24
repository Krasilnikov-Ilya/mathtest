package ApiResources.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс, отвечающий за создание и закрытие выражений подключений к SQL базам данных
 */

public class StatementUtils {

    // метод для создания выражения подключения
    public static Statement createStatement(Connection connection) {
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    // метод для закрытия выражения подключения
    public static void closeStatement(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
