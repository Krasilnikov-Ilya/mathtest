package ApiResources.JdbcUtilsNew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс, отвечающий за создание и закрытие подключений к SQL базам данных
 */

public class ConnectionCreater{

    // метод для подключения к базе данных
    public Connection createJdbcConnection(String url, String name, String password) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    // метод для закрытия подключения к базе данных
    public void closeJdbcConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
