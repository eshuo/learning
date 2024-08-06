package com.wyci.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setSerializationInclusion(JsonInclude.Include.NON_NULL);


    private static final ObjectMapper underlineObjectMapper = new ObjectMapper()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL).setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    public static JsonNode jsonToNode(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (IOException e) {
            logger.error("解析json错误 {}", json, e);
            return null;
        }
    }

    public static JsonNode objectToNode(Object data) {
        try {
            return MAPPER.valueToTree(data);
        } catch (IllegalArgumentException e) {
            logger.error("解析json错误 {}", data, e);
            return null;
        }
    }

    public static <T> T nodeToPojo(JsonNode node, Class<T> clazz) {
        try {
            return MAPPER.convertValue(node, clazz);
        } catch (IllegalArgumentException e) {
            logger.error("解析json错误 {}", node, e);
            return null;
        }
    }

    /**
     * 将对象转换成json字符串。
     * Description:
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        return objectToJson(data, false);
    }

    /**
     * 将对象转换成json字符串。
     *
     * @param data
     * @param underline 属性名称是否转换成下划线形式
     * @return
     */
    public static String objectToJson(Object data, boolean underline) {
        try {
            String string = null;
            if (underline) {
                string = underlineObjectMapper.writeValueAsString(data);
            } else {
                string = MAPPER.writeValueAsString(data);
            }
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * <p>
     * 例如 is_success == isSuccess
     *
     * @param jsonData  json数据
     * @param beanType  对象中的object类型
     * @param underline 是否使用小写下划线的格式解析JSON字符串到对象
     * @param <T>       转换的类型
     * @return 转换之后的结果
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType, boolean underline) {
        try {
            ObjectMapper mapper = MAPPER;
            if (underline) {
                mapper = underlineObjectMapper;
            }
            return mapper.readValue(jsonData, beanType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData      json数据
     * @param typeReference 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, TypeReference<T> typeReference) {
        return jsonToPojo(jsonData, typeReference, false);
    }

    /**
     * 小写下划线的格式解析JSON字符串到对象
     * 将json结果集转化为对象
     *
     * @param jsonData      json数据
     * @param typeReference 对象中的object类型
     * @param underline     是否使用小写下划线的格式解析JSON字符串到对象
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, TypeReference<T> typeReference, boolean underline) {
        try {
            if (underline) {
                return underlineObjectMapper.readValue(jsonData, typeReference);
            } else {
                return MAPPER.readValue(jsonData, typeReference);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 对象转Map
     *
     * @param pojo
     * @param underline 是否使用小写下划线的格式解析JSON字符串到对象
     * @return
     */
    public static Map<?, ?> pojoToMap(Object pojo, boolean underline) {
        try {
            ObjectMapper mapper = MAPPER;
            if (underline) {
                mapper = underlineObjectMapper;
            }
            return mapper.convertValue(pojo, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转Map
     *
     * @param pojo
     * @return
     */
    public static Map<?, ?> pojoToMap(Object pojo) {
        return pojoToMap(pojo, false);
    }


    /**
     * 将json结果集转化为对象
     *
     * @param map       数据
     * @param beanType  对象中的object类型
     * @param underLine 驼峰字段是否要转成下划线
     * @return
     */
    public static <T> T mapToPojo(Map map, Class<T> beanType, boolean underLine) {
        try {
            ObjectMapper mapper = MAPPER;
            if (underLine) {
                mapper = underlineObjectMapper;
            }
            String jsonStr = mapper.writeValueAsString(map);
            T var6 = mapper.readValue(jsonStr, beanType);
            return var6;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        return jsonToList(jsonData, beanType, false);
    }

    /**
     * 将json数据转换成pojo对象list
     *
     * @param underline 是否使用小写下划线的格式解析JSON字符串到对象
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType, boolean underline) {
        ObjectMapper mapper = MAPPER;
        if (underline) {
            mapper = underlineObjectMapper;
        }
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = mapper.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
