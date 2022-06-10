package resources;

import com.google.gson.Gson;
import resources.models.UsersPageRoot;

import java.io.FileReader;

/**
 * Итак, в ответе есть массив, в массиве есть пользователи.
 * Надо конвертировать.
 */


public class GsonParser {
    // говорю, что буду получать
    // в данном случае - первый по вложенности участок массива
    public UsersPageRoot parse() {
        Gson gson = new Gson(); // подтягиваю расширение

        try(FileReader reader = new FileReader("src/main/resources/userspage2.json")) { // в try заключаю ридер файла
            // читаю первый уровень массива
            UsersPageRoot usersPageRoot = gson.fromJson(reader, UsersPageRoot.class);
            // возвращаю первый уровень массива
            return usersPageRoot;
        }
        catch (Exception e) { // обрабатываю исключения
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }
}
