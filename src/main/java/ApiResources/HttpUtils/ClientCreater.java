package ApiResources.HttpUtils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

/**
 * Класс, отвечающий за создание и закрытие HTTP клиентов
 */

public class ClientCreater {

    // метод создания клиента
    public CloseableHttpClient createClient() {
        return HttpClients.createDefault();
    }

    // метод закрытия клиента
    public void closeClient(CloseableHttpClient client) {
        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
