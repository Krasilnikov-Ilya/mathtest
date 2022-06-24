package ApiResources.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс, отвечающий за создание и закрытие подключений к SQL базам данных
 */

public class ConnectionUtils {

    // метод для подключения к базе данных
    public static Connection createJdbcConnection(String url,String name,String password) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    // метод для закрытия подключения к базе данных
    public static void closeJdbcConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
