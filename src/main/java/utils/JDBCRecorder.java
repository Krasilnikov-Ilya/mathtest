package utils;

import models.User;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class JDBCRecorder {

    public static List<User> recordListFromJDBC() {
        // заранее инициализирую соединение
        Connection connection = null;
        // параметры для подключения
        String url = "jdbc:postgresql://77.50.236.203:4832/pflb_trainingcenter";
        String name = "pflb-at-read";
        String password = "PflbQaTraining2354";
        try {
            //Загружаю драйвер
            Class.forName("org.postgresql.Driver");
            //Создаю соединение
            connection = DriverManager.getConnection(url, name, password);
            //инициализирую результат
            Statement statement = null;
            //заполняю результат
            statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM person");
            //result это указатель на первую строку с выборки
            //метод next() переходит к следующему элементу в выборке
            //System.out.println("Recording statement");
            List<User> usersPageRootSQL = new ArrayList<>();
            while (result1.next()) {
                User user = new User(result1.getInt("id"), result1.getString("first_name"),result1.getString("second_name"),
                        result1.getInt("age"),(result1.getBoolean("sex") ? "MALE" : "FEMALE"),result1.getDouble("money"));
                usersPageRootSQL.add(user);
                //System.out.println(user.toString());
            }
            //System.out.println(usersPageRootSQL.toString());
            return usersPageRootSQL;



        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(JDBCRecorder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCRecorder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}

