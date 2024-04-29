package com.wyci.utils.http;


import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-04-28 17:08
 * @Version V1.0
 */
public class HttpLogFormatterUtils {


    /**
     * 生成关于HTTP请求的信息摘要。
     *
     * @param request 代表客户端请求的ServerHttpRequest对象。
     * @return 包含远程地址、HTTP方法、请求路径以及请求头信息的字符串。
     */
    public static String requestInfo(final ServerHttpRequest request) {
        final StringBuilder result = new StringBuilder();
        result.append('\n');
        // 添加远程地址信息
        result.append("Remote: ");
        result.append(request.getRemoteAddress());
        result.append('\n');
        // 添加HTTP方法和请求路径信息
        result.append("HttpMethod: ");
        result.append(request.getMethod());
        result.append(" path: ");
        result.append(request.getURI());
        result.append('\n');
        // 添加请求头信息
        writeHeaders(request.getHeaders(), result);
        return result.toString();
    }

    //
    public static String requestInfo(final HttpServletRequest request) {
        final StringBuilder result = new StringBuilder();
        result.append('\n');
        // 添加远程地址信息
        result.append("Remote: ");
        result.append(request.getRemoteAddr());
        result.append('\n');
        // 添加HTTP方法和请求路径信息
        result.append("HttpMethod: ");
        result.append(request.getMethod());
        result.append(" path: ");
        result.append(request.getPathInfo());
        result.append('\n');
        // 添加请求头信息
        writeHeaders(request.getHeaderNames(), result, request);
        return result.toString();
    }

    private static void writeHeaders(final Map<String, List<String>> headers, final StringBuilder output) {
        if (headers.isEmpty()) {
            return;
        }

        for (final Entry<String, List<String>> entry : headers.entrySet()) {
            output.append(entry.getKey());
            output.append(": ");
            final List<String> headerValues = entry.getValue();
            if (!headerValues.isEmpty()) {
                for (final String value : entry.getValue()) {
                    output.append(value);
                    output.append(", ");
                }
                output.setLength(output.length() - 2);
            }
            output.append('\n');
        }
    }

//            Enumeration<String> enumeration = request.getHeaderNames();
//        if(enumeration!=null){
//            while (enumeration.hasMoreElements()) {
//                String key = enumeration.nextElement();
//                String value = request.getHeader(key);
//                map.put(key, value);
//            }
//        }

    private static void writeHeaders(final Enumeration<String> headers, final StringBuilder output, HttpServletRequest request) {
        while (headers.hasMoreElements()) {
            String key = headers.nextElement();
            String value = request.getHeader(key);
            output.append(key);
            output.append(": ");
            output.append(value);
            output.append(", ");
            output.setLength(output.length() - 2);
            output.append('\n');
        }
    }


}
