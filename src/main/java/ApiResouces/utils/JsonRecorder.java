package ApiResouces.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import ApiResouces.models.User;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonRecorder {
    public static List<User> saveJson() throws IOException, ParseException {
        Gson gson = new Gson();
        CloseableHttpClient client = ApacheHttp5.createClient(); // создание клиента
        ClassicHttpRequest getUsersJson = ApacheHttp5.createGetWithHeader(); // создание запроса
        CloseableHttpResponse response = client.execute(getUsersJson); // объявление и инициализация ответа
        HttpEntity entity = response.getEntity(); // содержимое
        String entityString = EntityUtils.toString(entity); // перевожу в String
        JsonArray jArray = new Gson().fromJson(entityString, JsonArray.class); // создаю json
        Type listType = new TypeToken<List<User>>(){}.getType(); // не смог избавиться от получения типа
        List<User> usersPageRootSQL = gson.fromJson(jArray, listType);
        client.close(); // закрытие клиента
        return usersPageRootSQL;
    }
}
