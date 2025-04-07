package com.wyci.handle;

import com.alibaba.fastjson.JSON;
import com.wyci.dto.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description 统一响应
 * @Author wangshuo
 * @Date 2022-08-10 13:52
 * @Version V1.0
 */
@RestControllerAdvice
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {

        if (ContextHandler.bodyNoConVert() || body instanceof Resource) {
            return body;
        }

        ApiResponse<?> result;
        if (body instanceof byte[]) {
            // 验证码支持
            return body;
        } else if (body instanceof ApiResponse) {
            // 框架定义返回格式处理
            logger.info("请求出参 : {}", JSON.toJSONString(body));
            return body;
        } else if (body instanceof CharSequence) {
            return JSON.toJSONString(ApiResponse.success(body));
        } else {
            //默认结果处理
            result = ApiResponse.success(body);
        }
        return result;
    }

}
