package ApiResouces.utils;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class ApacheHttp5 {
    public static CloseableHttpClient createClient() { // создание клиента
        return HttpClients.createDefault();
    }

    public static ClassicHttpRequest createGetWithHeader() { // создание запроса без билдера
        HttpGet request = new HttpGet("http://77.50.236.203:4880/users");
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        return request;
    }

}
