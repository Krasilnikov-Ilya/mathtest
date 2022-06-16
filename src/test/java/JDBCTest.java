import java.sql.*;
import java.util.logging.*;

public class JDBCTest {

    public static void main(String[] args) {
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
            System.out.println("Showing statement");
            while (result1.next()) {
                System.out.println("id = " + result1.getInt("id")
                        + " first_name = " + result1.getString("first_name") + " second_name = " +  result1.getString("second_name")
                        + " money = " + result1.getDouble("money") + " age = " + result1.getInt("age") + " sex = " + (result1.getBoolean("sex")? "Male" : "Female"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(JDBCTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}