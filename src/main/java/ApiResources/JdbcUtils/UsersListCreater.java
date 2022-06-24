package ApiResources.JdbcUtils;

import ApiResources.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, отвечающий за создание списка пользователей из результирующего набора данных
 */

public class UsersListCreater {

    // метод для создания списка пользователей из результирующего набора данных
    public static List<User> createUsersList(ResultSet resultSet) {
        // создание списка пользователей
        List<User> userArrayList = new ArrayList<>();
        try {
            // циклическое заполнение списка пользователей
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("second_name"),
                        resultSet.getInt("age"), (resultSet.getBoolean("sex") ? "MALE" : "FEMALE"), resultSet.getDouble("money"));
                userArrayList.add(user);
            }
            // возврат заполненного списка пользователей
            return userArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
