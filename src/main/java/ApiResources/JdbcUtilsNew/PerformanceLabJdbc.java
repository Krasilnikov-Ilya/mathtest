package ApiResources.JdbcUtilsNew;

import ApiResources.Models.User;
import Configuration.ConfProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Основной класс со статичными методами для использования в тестах
 */

public class PerformanceLabJdbc {
    public static <T> List<T> getUsers(String query, Class  tClass) {
        ConnectionCreater connectionCreater = new ConnectionCreater();
        StatementCreater statementCreater = new StatementCreater();
        ResultSetCreater resultSetCreater = new ResultSetCreater();
        ResultSetStringWrapper resultSetJsonWrapper = new ResultSetStringWrapper();

        // создание подключения, использует данные из conf.properties
        Connection connection = connectionCreater.createJdbcConnection(
                ConfProperties.getProperty("JDBC_HOST") + ConfProperties.getProperty("JDBC_PORT") + "/pflb_trainingcenter",
                ConfProperties.getProperty("JDBC_NAME"),
                ConfProperties.getProperty("JDBC_PASSWORD"));

        Statement statement = statementCreater.createStatement(connection);

        // получение результата запроса, использует запрос из теста
        ResultSet resultSet = resultSetCreater.getResultSet(statement, query);

        // Линейная последовательность методов класса resultSetJsonWrapper
        resultSetJsonWrapper.getResultSet(resultSet); // сохранение результата
        resultSetJsonWrapper.getMetaData(); // получение информации о результате
        resultSetJsonWrapper.getColumnCount(); // получение количества колонок
        resultSetJsonWrapper.getColNames(); // получение имён колонок
        resultSetJsonWrapper.convertResultSetToJsonArray(); // создание массива Json
        resultSetJsonWrapper.convertJsonArrayToString(); // конвертация в String

        // создание списка с использованием класса, переданного тестом
        List<T> userListSQL = resultSetJsonWrapper.getListOfGenericTypeFromString(tClass);

        // закрытие ресурсов
        resultSetCreater.closeResultSet(resultSet);
        statementCreater.closeStatement(statement);
        connectionCreater.closeJdbcConnection(connection);

        return userListSQL;
    }
}
