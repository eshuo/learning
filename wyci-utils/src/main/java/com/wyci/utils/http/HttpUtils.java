package com.wyci.utils.http;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-21 10:45
 * @Version V1.0
 */
@Log4j2
public class HttpUtils {


    private static final int MAX_TOTAL = 20;

    private static CloseableHttpClient client;

    static {
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
        connMgr.setMaxTotal(MAX_TOTAL);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        client = HttpClients.custom()
                .setConnectionManager(connMgr)
                .setConnectionManagerShared(true)
                .build();
    }


    public final static String SPLIT_SYMBOL = "&";

    public final static String EQUAL_SYMBOL = "=";

    public final static String QUESTION_MARK = "?";


    public static <T> T sendGetRequest(String uri, Class<T> tClass) {
        return sendGetRequest(uri, null, tClass);
    }

    public static <T> T sendGetRequestByJson(String uri, Map<String, String> map, Class<T> tClass) {
        final String convertUrl = paramsConvertUrl(map);
        if (StringUtils.isNotBlank(convertUrl)) {
            uri += convertUrl;
        }
        log.info("sendGetRequest Uri:{}", uri);
        HttpGet method = new HttpGet(uri);
        try {
            final CloseableHttpResponse response = client.execute(method);
            final HttpEntity entity = response.getEntity();
            final T t = JSONObject.parseObject(EntityUtils.toString(entity), tClass);
            log.debug("sendGetRequest ToString:{}", t.toString());
            return t;

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return null;
    }


    public static <T> T sendGetRequest(String uri, Map<String, String> map, Class<T> tClass) {
        final String convertUrl = paramsConvertUrl(map);
        if (StringUtils.isNotBlank(convertUrl)) {
            uri += convertUrl;
        }
        log.info("sendGetRequest Uri:{}", uri);
        HttpGet method = new HttpGet(uri);
        try {
            final CloseableHttpResponse response = client.execute(method);
            final HttpEntity entity = response.getEntity();
            final T t = JSONObject.parseObject(EntityUtils.toString(entity), tClass);
            log.debug("sendGetRequest ToString:{}", t.toString());
            return t;

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return null;
    }


    public static <T> T sendPostRequest(String uri, Map<String, String> map, Class<T> tClass) {
        log.info("sendPostRequest Uri:{}", uri);
        HttpPost postMethod = new HttpPost(uri);
        try {
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            postMethod.setEntity(new UrlEncodedFormEntity(params));
            final CloseableHttpResponse response = client.execute(postMethod);
            final T t = JSONObject.parseObject(EntityUtils.toString(response.getEntity()), tClass);
            log.info("sendPostRequest ToString:{}", t.toString());
            return t;

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private static String paramsConvertUrl(Map<String, String> params) {
        if (null != params && !params.isEmpty()) {
            StringBuilder urlParams = new StringBuilder(QUESTION_MARK);
            params.forEach((k, v) -> urlParams.append(k).append(EQUAL_SYMBOL).append(v).append(SPLIT_SYMBOL));
            return urlParams.substring(0, urlParams.length() - 1);
        }
        return StringUtils.EMPTY;
    }
}
