package com.wyci.utils.log;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-09 09:52
 * @Version V1.0
 */
public class RequestLogUtils {

    private final static Logger logger = LoggerFactory.getLogger(RequestLogUtils.class);

    /**
     * 日志请求信息
     *
     * @param request 要求
     */
    private void logRequestInfo(HttpServletRequest request) {
        StringBuilder requestInfo = new StringBuilder("\n");
        requestInfo.append("请求URI:").append(request.getRequestURI()).append("\n");
        requestInfo.append("请求URL:").append(request.getRequestURL().toString()).append("\n");
        requestInfo.append("请求方式:").append(request.getMethod()).append("\n");
        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = enums.nextElement();
            requestInfo.append(name).append(":").append(request.getHeader(name)).append("\n");
        }

        if (request.getMethod().equals(HttpMethod.GET.name())) {
            requestInfo.append(JSONObject.toJSONString(request.getParameterMap())).append("\n");
        } /*else if (request.getMethod().equals(HttpMethod.POST.name()) || request.getMethod().equals(HttpMethod.PUT.name())) {
        requestInfo.append(request.getBody()).append("\n");
    }*/
        logger.info("----------------请求体信息----------------Start:{}----------------请求体信息----------------End", requestInfo);
    }
}
