package com.wyci.utils.str;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-09-04 15:55
 * @Version V1.0
 */
public class StrUtils {


    /**
     * 判断是否是base64编码后的字符串
     * @param str
     * @return
     */
    public static boolean isBase64(String str) {
        String base64Rule = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(base64Rule, str);
    }


    public static void main(String[] args) {


        System.out.println(isBase64("aHR0cHM6Ly9vZm0uY3JtLjE4OS5jbjoyMzEyMi9vcmRlckhhbmRsZS8jL2VtT3JkZXJIYW5kbGUvZWVPcmRlcklURGVtYW5kSGFuZGxlP29yZGVySWQ9ODQzMTgwJnNoYXJkaW5nSWQ9NCZ3b3JrT3JkZXJJZD0xNzYyNjY5JnByb2Nlc3NJbnN0SWQ9MTU0NDE4NSZhY3Rpdml0eUluc3RJZD0zMzg5NzE1JmFjdGl2aXR5UmVwb0lkPXJlcG9fMjAyMTExMTAxNTQ3MzU5OTcmaXNIaXN0b3J5PTAmdXNlck5hbWU9ay9UdzBLTTlia2pVK2p2MEZydU9BQT09JmFjdGl2aXR5TmFtZT3pm4blm6LkuJrliqHkuLvnrqHlrqHmoLgmaXNIYW5kbGU9MCZvcmRlclRpdGxlPea1i-ivleWNlSZ1bmlDb2RlPTM3MDQxMzItMTMzNzE5ODYwMzA="));

        System.err.println(new String( java.util.Base64.getDecoder().decode("aHR0cHM6Ly9vZm0uY3JtLjE4OS5jbjoyMzEyMi9vcmRlckhhbmRsZS8jL2VtT3JkZXJIYW5kbGUvZWVPcmRlcklURGVtYW5kSGFuZGxlP29yZGVySWQ9ODQzMTgwJnNoYXJkaW5nSWQ9NCZ3b3JrT3JkZXJJZD0xNzYyNjY5JnByb2Nlc3NJbnN0SWQ9MTU0NDE4NSZhY3Rpdml0eUluc3RJZD0zMzg5NzE1JmFjdGl2aXR5UmVwb0lkPXJlcG9fMjAyMTExMTAxNTQ3MzU5OTcmaXNIaXN0b3J5PTAmdXNlck5hbWU9ay9UdzBLTTlia2pVK2p2MEZydU9BQT09JmFjdGl2aXR5TmFtZT3pm4blm6LkuJrliqHkuLvnrqHlrqHmoLgmaXNIYW5kbGU9MCZvcmRlclRpdGxlPea1i-ivleWNlSZ1bmlDb2RlPTM3MDQxMzItMTMzNzE5ODYwMzA=".getBytes(StandardCharsets.UTF_8))));

    }

}
