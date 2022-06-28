package ApiResources.HttpUtils;

import Configuration.ConfProperties;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;

import java.net.URI;
import java.util.List;

public class PerformanceLabApi {
    public static <T> List<T> getUsers(String path, Class tClass) {
        ClientCreater clientCreater = new ClientCreater();
        RequestCreater requestCreater = new RequestCreater();
        ResponseCreater responseCreater = new ResponseCreater();

        // установка хоста, порта и инициализация билдера URI
        UriCreater uriCreater = new UriCreater(
                ConfProperties.getProperty("API_HOST"),
                ConfProperties.getProperty("API_PORT"));

        // создание полного адреса
        URI uri = uriCreater.createUri(path);

        // создание клиента и получение ответа сервера
        CloseableHttpClient client = clientCreater.createClient();
        ClassicHttpRequest request = requestCreater.createHttpGet(uri);
        CloseableHttpResponse response = responseCreater.createResponse(client,request);

        // сохранение ответа в обёртке
        ResponseWrapper responseWrapper = new ResponseWrapper(response);

        // обработка ответа в обёртке: получение тела ответа
        responseWrapper.getEntity(response);
        // обработка ответа в обёртке: приведение тела ответа к String
        responseWrapper.getStringForGson();

        // закрытие ответа сервера и клиента
        responseCreater.closeResponse(response);
        clientCreater.closeClient(client);

        // возврат списка определённых в тесте моделей
        return responseWrapper.getListOfGenericTypeFromString(tClass);

    }
}
