package ApiResources.HttpUtils;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Класс, отвечающий за создание полного идентификатора ресурса.
 * Использует хост и порт из properties, а так же передаваемый адрес каталога.
 */

public class UriCreater {

    String host;
    String port;

    public UriCreater(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public URI createUri(String address) {
        try {
            URI uri = new URI(host + port + address);
            //System.out.println("Your uri: " + uri);
            return uri;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
