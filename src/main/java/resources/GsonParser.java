package resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import resources.models.User;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Итак, в ответе есть массив, в массиве есть пользователи.
 * Надо конвертировать.
 *
 * Пришлось переделать: раньше я корневую часть массива, теперь - список.
 */


public class GsonParser {
    // говорю, что буду получать
    // в данном случае - первый по вложенности участок массива
    public static List<User> parse() {
        Gson gson = new Gson(); // подтягиваю расширение

        try(FileReader reader = new FileReader("src/main/resources/perfuserspage.json")) { // в try заключаю ридер файла
            // читаю первый уровень массива
            Type listType = new TypeToken<List<User>>(){}.getType();
            List<User> usersPageRootAPI = gson.fromJson(reader, listType);
            //System.out.println(usersPageRootAPI.toString()); //вывод для дебага
            // возвращаю первый уровень массива
            return usersPageRootAPI;
        }
        catch (Exception e) { // обрабатываю исключения
            System.out.println("Parsing error" + e.toString());
        }
        return null;
    }
}
