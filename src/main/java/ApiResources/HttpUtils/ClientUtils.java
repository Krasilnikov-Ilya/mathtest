package ApiResources.HttpUtils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

/**
 * Класс, отвечающий за создание и закрытие HTTP клиентов
 */

public class ClientUtils {

    // метод создания клиента
    public static CloseableHttpClient createClient() {
        return HttpClients.createDefault();
    }

    // метод закрытия клиента
    public static void closeClient(CloseableHttpClient client) {
        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
