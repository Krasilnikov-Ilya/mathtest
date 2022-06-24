package ApiResources.HttpUtils;

import ApiResources.Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Класс, отвечающий за создание списка пользователей из тела ответа сервера с форматом Json
 * Использует для парсинга массива подключаемую библиотеку Gson
 */

public class UsersListGsonCreater {
    public static List<User> createUsersListWithGson(HttpEntity entity) {

        // подключение библиотеки Gson
        Gson gson = new Gson();

        // конвертация тела ответа сервера в String формат
        String entityString;
        try {
            entityString = EntityUtils.toString(entity);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        // создание массива Json
        JsonArray jArray = new Gson().fromJson(entityString, JsonArray.class);

        // определение типа данных в списке
        Type listType = new TypeToken<List<User>>() {
        }.getType();

        // создание списка пользователей
        return gson.fromJson(jArray, listType);
    }
}
