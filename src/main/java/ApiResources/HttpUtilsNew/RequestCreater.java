package ApiResources.HttpUtilsNew;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.message.BasicHeader;

import java.net.URI;
import java.util.stream.Stream;

/**
 * Данный класс отвечает за создание HTTP запросов
 */

public class RequestCreater {

    // создаёт конечный запрос
    protected ClassicHttpRequest createHttpGet(URI uri) {
        ClassicHttpRequest request = new HttpGet(uri);
        request.setHeaders(getHeaders());
        return new HttpGet(uri);
    }

    // добавляет к запросу массив хедеров
    protected Header[] getHeaders() {
        Stream<Header> headerStream = Stream.of(
                new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        );
        return headerStream.toArray(Header[]::new);
    }

}
