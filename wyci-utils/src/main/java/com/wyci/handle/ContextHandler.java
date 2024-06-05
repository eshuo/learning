package com.wyci.handle;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-11 16:58
 * @Version V1.0
 */
public class ContextHandler {

    public static final String USER_ID = "USER_ID";

    public static final String USER_INFO = "USER_INFO";


    public static final String SUBJECT = "SUBJECT";

    public static final String USER_NAME = "USER_NAME";

    public static final String USER_LOGIN_NAME = "USER_LOGIN_NAME";


    //结合日志来
    public static final String THREAD_ID = "LOG-THREAD-ID";


    public static final String FEIGN = "FEIGN";

    /**
     * 通讯异常
     */
    public static final String FEIGN_ERROR = "FEIGN_ERROR";


    /**
     * JWT票据在header中的标识
     **/
    public final static String AUTHORIZATION = "Authorization";

    /**
     * 免验证
     */
    public final static String NO_AUTHENTICATION = "noAuthentication";

    /**
     * feign 传输当前对象???
     */
    public final static String ENCODE_BASE64_USER_HANDLER = "ENCODE_BASE64_USER_HANDLER";


    public final static String TOKEN = "TOKEN";

    public static final String APP_ID = "APP_ID";


    public static final String DEPT_ID = "DEPT_ID";

    /**
     * 请求时间
     */
    public static final String REQUEST_START_TIME = "REQUEST_START_TIME";


    /**
     * 请求地址
     */
    public static final String REQUEST_PATH = "REQUEST_PATH";


    /**
     * 无需转换为ApiResult
     */
    public static final String RESPONSE_NO_CONVERT = "RESPONSE_NO_CONVERT";


    private ContextHandler() {
    }

    private static final ThreadLocal<Map<String, Serializable>> THREAD_LOCAL = new TransmittableThreadLocal<>();


    public static final ObjectMapper MAPPER = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static synchronized <T extends Serializable> void set(String key, T value) {
        Map<String, Serializable> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            THREAD_LOCAL.set(map);
        }
        map.put(key, value);
    }

    public static synchronized <T extends Serializable> T get(String key) {
        Map<String, Serializable> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            THREAD_LOCAL.set(map);
        }
        return (T) map.get(key);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
        MDC.remove(THREAD_ID);
    }


    public static void setThreadId(String id) {
        if (StringUtils.isNotBlank(id)) {
            set(THREAD_ID, id);
            MDC.put(THREAD_ID, id);
        }
    }

    public static String initThreadId() {
        String threadId = getThreadId();
        if (StringUtils.isBlank(threadId)) {
            threadId = UUID();
            setThreadId(threadId);
        }
        return threadId;
    }


    /**
     * 刷新现场ID到MDC 子线程使用
     */
    public static void refreshThreadId() {
        setThreadId(getThreadId());
    }

    /**
     * uuid
     *
     * @return {@code String}
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static String getThreadId() {
        return get(THREAD_ID);
    }

    public static String removeThreadId() {
        String uuid = get(THREAD_ID);
        remove();
        return uuid;
    }

    public static void setUserId(String userId) {
        set(USER_ID, userId);
    }

    public static void setDeptId(String deptId) {
        set(DEPT_ID, deptId);
    }

    public static String getUserId() {
        return get(USER_ID);
    }


    public static String getDeptId() {
        return get(DEPT_ID);
    }

    public static void setUserName(String userName) {
        set(USER_NAME, userName);
    }


    public static String getUserName() {
        return get(USER_NAME);
    }


    public static void setUserLoginName(String userLoginName) {
        set(USER_LOGIN_NAME, userLoginName);
    }

    public static String getUserLoginName() {
        return get(USER_LOGIN_NAME);
    }


    public static void setToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            set(TOKEN, token);
        }
    }

    public static String getToken() {
        return get(TOKEN);
    }

    public static void setAppId(String appId) {
        if (StringUtils.isNotBlank(appId)) {
            set(APP_ID, appId);
        }
    }

    public static String getAppId() {
        return get(APP_ID);
    }


    public static void setStartTime() {
        set(REQUEST_START_TIME, System.currentTimeMillis());
    }

    public static Long getStartTime() {
        return get(REQUEST_START_TIME);
    }


    public static void setRequestPath(String path) {
        set(REQUEST_PATH, path);
    }

    public static String getRequestPath() {
        return get(REQUEST_PATH);
    }


    /**
     * 设置返回类型无需转换
     */
    public static void setBodyNoConVert() {
        set(RESPONSE_NO_CONVERT, true);
    }

    /**
     * 判断返回类型是否无需转换为ApiResult
     *
     * @return
     */
    public static boolean bodyNoConVert() {
        Boolean convert = get(RESPONSE_NO_CONVERT);
        return Boolean.TRUE.equals(convert);
    }


    public static Map<String, Serializable> getEnvironment() {
        return Collections.unmodifiableMap(THREAD_LOCAL.get().entrySet().stream().filter(entry -> entry.getValue() != null
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }


}
