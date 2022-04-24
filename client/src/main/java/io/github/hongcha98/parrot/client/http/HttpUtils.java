package io.github.hongcha98.parrot.client.http;

import com.alibaba.fastjson.JSON;
import io.github.hongcha98.parrot.common.error.ParrotException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    private static final String CONTENT_TYPE_VALUE = "application/json;charset=utf-8";

    public static <T> T reqApi(HttpMethod httpMethod, String url, Map<String, String> param, Object body, Class<T> clazz) {
        return JSON.parseObject(reqApi(httpMethod, url, param, body), clazz);
    }

    public static <T> List<T> reqApiListResp(HttpMethod httpMethod, String url, Map<String, String> param, Object body, Class<T> clazz) {
        return JSON.parseArray(reqApi(httpMethod, url, param, body), clazz);
    }


    public static String reqApi(HttpMethod httpMethod, String url, Map<String, String> param, Object body) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            if (param != null && !param.isEmpty()) {
                url += "?";
                for (String key : param.keySet()) {
                    url += (key + "=" + param.get(key) + "&");
                }
                url = url.substring(0, url.length() - 1);
            }
            HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = new HttpEntityEnclosingRequestBase() {
                @Override
                public String getMethod() {
                    return httpMethod.name();
                }
            };
            httpEntityEnclosingRequestBase.setURI(URI.create(url));
            if (httpMethod.isHaveBody()) {
                httpEntityEnclosingRequestBase.setEntity(new StringEntity(JSON.toJSONString(body)));
            }
            httpEntityEnclosingRequestBase.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_VALUE);
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpEntityEnclosingRequestBase); InputStream is = httpResponse.getEntity().getContent()) {
                String response = toStr(is);
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    return response;
                } else {
                    throw new RuntimeException(response);
                }
            }
        } catch (Exception e) {
            throw new ParrotException(e);
        }
    }


    private static String toStr(InputStream is) throws IOException {
        byte[] bytes = new byte[65536];
        int offset = 0;
        while (true) {
            int readCount = is.read(bytes, offset, bytes.length - offset);
            if (readCount == -1) {
                return new String(bytes, 0, offset);
            }
            offset += readCount;
            if (offset == bytes.length) {
                byte[] newBytes = new byte[bytes.length * 3 / 2];
                System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
                bytes = newBytes;
            }
        }
    }
}
