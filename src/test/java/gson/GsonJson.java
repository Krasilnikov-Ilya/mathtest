package gson;

import resources.GsonParser;
import resources.models.UserAPI;

import java.util.List;

/**
 * Пришлось переделать модели и методы, а так же возвращать объект List<User>
 * Но в целом - с одномерным массивом всё равно проще
 */

public class GsonJson { // пять строк кода, пять часов страданий.
    public static void main(String[] args) {
        GsonParser parser = new GsonParser(); // объявляю парсер
        List<UserAPI> usersPageRoot = parser.parse(); // использую парсер на Json
        System.out.println(usersPageRoot.toString()); // вывожу в консоль содержимое




    }
}
