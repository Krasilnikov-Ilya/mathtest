package ApiResources.HttpUtils;

import com.google.gson.Gson;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


/**
 * Данный класс отвечает за получение из ответа сервера
 * Независимого (не закрывающегося при закрытии ответа) тела ответа,
 * А так же за его обработку: приведение типов и получение списка моделей
 */
public class ResponseWrapper {
    private HttpEntity entity;
    private String entityString;

    public ResponseWrapper(CloseableHttpResponse closeableHttpResponse) {
        getEntity(closeableHttpResponse);
    }

    // метод для получения тела запроса отдельно от основного запроса.
    public void getEntity(CloseableHttpResponse closeableHttpResponse) {
        // заполнение переменной содержимым тела ответа сервера
        entity = closeableHttpResponse.getEntity();
    }

    public void getStringForGson() {

        // приведение тела ответа к String формату
        try {
            // и сохранение полученного результата в классе
            entityString = EntityUtils.toString(entity);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // метод для создания списка с определённой в тесте моделью.
    public <T> List<T> getListOfGenericTypeFromString (Class tClass) {
        // подключение библиотеки Gson
        Gson gson = new Gson();
        // создание списка
        Class<T[]> tArrayClass = (Class<T[]>) Array.newInstance(tClass,0).getClass();
        // передача списка
        return Arrays.stream(gson.fromJson(entityString, tArrayClass)).toList();
    }
}

