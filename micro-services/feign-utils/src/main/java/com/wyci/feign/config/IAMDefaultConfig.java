package com.wyci.feign.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.io.ByteStreams;
import com.wyci.feign.api.ApiResult;
import com.wyci.feign.api.R;
import feign.FeignException;
import feign.Logger;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.codec.StringDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-12-19 14:53
 * @Version V1.0
 */
public class IAMDefaultConfig {


    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(IAMDefaultConfig.class);

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setSerializationInclusion(JsonInclude.Include.NON_NULL);


//    @Bean
//    @Scope("prototype")
//    public RequestInterceptor iamHeaderInterceptor() {
//        return template -> {
//            final String threadId = ContextHandler.getThreadId();
//            if (StringUtils.isNotBlank(threadId)) {
//                template.header(ContextHandler.IAM_THREAD_ID, threadId);
//            }
//        };
//    }


    @Bean
    public Decoder feignDecoder() {
        return new IAMDecoder(MAPPER, new StringDecoder());
    }


    @Bean
    @Scope("prototype")
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }

    public class UserErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            try {
                final String errorJson = new String(ByteStreams.toByteArray(response.body().asInputStream()), UTF_8);
                logger.error("ErrorDecoder:{}", errorJson);
                final ApiResult<?> apiResult = JSONObject.parseObject(errorJson, ApiResult.class);
                if (null != apiResult && HttpStatus.OK.value() != apiResult.getStatus()) {
//                    return new IAMException(apiResult.getCode(), apiResult.getMessage());
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
//            return new IAMException(BaseMessage.ERROR, "远程服务调用失败，解析错误信息失败");
            return new Exception("远程服务调用失败，解析错误信息失败");
        }
    }


    @Bean
    @Scope("prototype")
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @RequiredArgsConstructor
    public class IAMDecoder implements Decoder {

        private final ObjectMapper objectMapper;


        private final StringDecoder stringDecoder;

        /**
         * Decodes an http response into an object corresponding to its
         * {@link Method#getGenericReturnType() generic return type}. If you need to
         * wrap exceptions, please do so via {@link DecodeException}.
         *
         * @param response the response to decode
         * @param type     {@link Method#getGenericReturnType() generic return type} of the
         *                 method corresponding to this {@code response}.
         *
         * @return instance of {@code type}
         *
         * @throws IOException     will be propagated safely to the caller.
         * @throws DecodeException when decoding failed due to a checked exception besides IOException.
         * @throws FeignException  when decoding succeeds, but conveys the operation failed.
         */
        @SneakyThrows
        @Override
        public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
//        if (response.body() == null) {
//            throw new IAMException("远程调用无响应");
//        }
            Method method = response.request().requestTemplate().methodMetadata().method();
            Reader reader = response.body().asReader(Charset.defaultCharset());
            if (R.class.equals(method.getReturnType()) || ApiResult.class.equals(method.getReturnType())) {
                JavaType javaType = TypeFactory.defaultInstance().constructType(type);
                return objectMapper.readValue(reader, javaType);
            } else {
                final ApiResult<?> apiResult = objectMapper.readValue(reader, ApiResult.class);
                if (null != apiResult) {
                    Object data = apiResult.getData();
                    if (null == data) {
                        logger.error("data is null ,message:{}", apiResult.getMessage());
                    }
                    JavaType javaType = TypeFactory.defaultInstance().constructType(type);
                    return objectMapper.convertValue(data, javaType);
                }
            }
            return stringDecoder.decode(response, type);
        }
    }
}
