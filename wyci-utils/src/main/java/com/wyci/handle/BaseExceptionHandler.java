package com.wyci.handle;

import com.wyci.dto.response.ApiResponse;
import com.wyci.exception.BaseException;
import com.wyci.exception.message.BaseMessage;
import java.io.FileNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Description 统一异常处理
 * @Author wangshuo
 * @Date 2022-08-10 14:20
 * @Version V1.0
 */
@Order(Integer.MIN_VALUE)
@RestControllerAdvice
public class BaseExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);


    /**
     * 异常处理。
     *
     * @param e the e
     * @return ApiResult
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
        logger.error("BaseExceptionHandler error ", e);
        ApiResponse<String> apiResponse;
        int status = HttpStatus.OK.value();
        if (e instanceof NoHandlerFoundException || e instanceof FileNotFoundException) {
            apiResponse = ApiResponse.error("路径不存在，请检查路径是否正确！", HttpStatus.NOT_FOUND.value());
            status = HttpStatus.NOT_FOUND.value();
        } else if (e instanceof BaseException) {
            final BaseException exception = (BaseException) e;
            apiResponse = ApiResponse.error(exception.getErrorInfo(), exception.getErrorCode());
        } else if (e instanceof HttpMessageNotReadableException) {
            apiResponse = ApiResponse.error("非法的请求参数", HttpStatus.BAD_REQUEST.value());
        } else {
            apiResponse = ApiResponse.error("系统错误", BaseMessage.BASE_CODE);
        }
        // 2023/4/11 WangShuo: 异常处理状态码全部为200(网络通讯正常)  code为自定义
        response.setStatus(status);
        response.addHeader(HttpHeaders.CONTENT_TYPE, "text/plain;charset=UTF-8");
        return apiResponse;
    }

}
