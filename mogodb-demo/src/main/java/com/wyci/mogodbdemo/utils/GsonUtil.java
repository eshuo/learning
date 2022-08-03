package com.wyci.mogodbdemo.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * GSON工具类
 * </p>
 */
public class GsonUtil {
    private static Gson gson = null;

    static {
        GsonUtil.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }


    public static Gson getGson() {
        return GsonUtil.gson;
    }

    /**
     * Create the standard {@link Gson} configuration
     *
     * @param serializeNulls whether nulls should be serialized
     * @return created gson, never null
     */
    public static Gson newInstance(final boolean serializeNulls) {
        return newInstance(serializeNulls, null);
    }


    public static Gson newInstance(final boolean serializeNulls, FieldNamingPolicy fieldNamingPolicy) {
        final GsonBuilder builder = new GsonBuilder();
        if (null == fieldNamingPolicy) {
            builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        } else {
            builder.setFieldNamingPolicy(fieldNamingPolicy);
        }
        if (serializeNulls)
            builder.serializeNulls();
        return builder.create();
    }

    /**
     * JSON字符串转为Map
     *
     * @param json 字符串
     * @return Map集合
     */
    @SuppressWarnings("all")
    public static Map<String, String> fromJson2Map(String json) {
        return GsonUtil.gson.fromJson(json, new TypeToken<Map<String, String>>() {
            private static final long serialVersionUID = -3006593022069606890L;
        }.getType());
    }


    /**
     * 小写下划线的格式解析JSON字符串到对象
     * <p>
     * 例如 is_success == isSuccess
     *
     * @param json     传入的json串
     * @param classOfT 要转换的类型
     * @param <T>      转换的类型
     * @return 转换之后的结果
     */
    public static <T> T fromJsonUnderScoreStyle(String json, Class<T> classOfT) {
        return GsonUtil.gson.fromJson(json, classOfT);
    }

    /**
     * Map转为JSON字符串
     *
     * @param map Map集合
     * @return 转换之后的字符串
     */
    @SuppressWarnings("all")
    public static String fromMap2Json(Map<String, String> map) {
        return GsonUtil.gson.toJson(map, new TypeToken<Map<String, String>>() {
            private static final long serialVersionUID = -1512805424754755816L;
        }.getType());
    }

    /**
     * 小写下划线的格式将对象转换成JSON字符串
     *
     * @param src 对象
     * @return 下划线转换为对象
     */
    public static String toJson(Object src) {
        return GsonUtil.gson.toJson(src);
    }


    /**
     * 转换List对象
     *
     * @param json     传入的json串
     * @param classOfT 要转换的类型
     * @param <T>      转换的类型
     * @return
     */
    public static <T> List<T> fromJson2List(String json, Class<T> classOfT) {
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(GsonUtil.gson.fromJson(elem, classOfT));
        }
        return list;
    }

    /**
     * 转换List对象
     *
     * @param json 传入的json串
     * @param <T>  转换的类型
     * @return
     */
    public static <T> List<T> fromJson2List(String json) {
        return GsonUtil.gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
    }


    public static <T> T mapToObject(Map<?, ?> map, Class<T> tClass) {
        return GsonUtil.gson.fromJson(GsonUtil.gson.toJson(map), tClass);
    }

}
