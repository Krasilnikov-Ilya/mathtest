package gson;

import resources.GsonParser;
import resources.models.UsersPageRoot;

/**
 * Я не смог удержаться от исследования Gson
 * Незакрытый гештальт в виде дополнительного задания не давал мне покоя.
 * Вижу Json - начинаю парсить, ничего с собой поделать не могу.
 * Давай будем считать, что это мой академический долг:
 * выполнить дополнительное задание, на которое я положил ранее из-за сроков
 */

public class GsonJson { // пять строк кода, пять часов страданий.
    public static void main(String[] args) {
        GsonParser parser = new GsonParser(); // объявляю парсер
        UsersPageRoot usersPageRoot = parser.parse(); // использую парсер на Json
        System.out.println(usersPageRoot.toString()); // вывожу в консоль содержимое

        /*
        Так как сайт содержит несколько страниц пользователей,
        можно получить например первую, создать второй файл и так же его вывести.
        Я работал со второй, мне она как родная после работы с Postman.
         */


    }
}
