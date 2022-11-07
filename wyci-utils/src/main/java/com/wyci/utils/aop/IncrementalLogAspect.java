package com.wyci.utils.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.UUID;

/**
 * @Description 日志切面 @Author wangshuo @Date 2022-11-04 17:29 @Version V1.0
 */
@Aspect
@Component
public class IncrementalLogAspect implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(IncrementalLogAspect.class);

    @Pointcut("execution(public * com.wyci.*(..))")
    public void controllerPointcutLog() {
    }

    @Before("controllerPointcutLog()")
    public void doBeforeLog(JoinPoint joinPoint) {
        // 增加日志流水号
        MDC.put("LOG_ID", UUID.randomUUID().toString().replace("-", ""));
        LOG.debug("------------- Log Start -------------");
        beforeLogInfo(joinPoint);
    }

    @Around("controllerPointcutLog()")
    public Object doAroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        doAroundLogInfo("返回结果: {}", result);
        LOG.debug(
                "------------- Log End 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

    @After("controllerPointcutLog()")
    public void afterLog() {
        LOG.debug("============Log remove LOG_ID==================");
        MDC.remove("LOG_ID");
    }

    /**
     * 之前日志信息
     *
     * @param joinPoint 连接点
     */
    private void beforeLogInfo(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            LOG.debug("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
            //            LOG.debug("远程地址: {}", request.getRemoteAddr());
        }
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        // 打印请求信息
        LOG.debug("类名方法: {}.{}", signature.getDeclaringTypeName(), name);

        // 打印请求参数
        Object[] args = joinPoint.getArgs();

        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // 排除字段，敏感字段或太长的字段不显示
        doAroundLogInfo("请求参数: {}", arguments);
    }

    /**
     * 在日志信息
     *
     * @param format 格式
     * @param result 结果
     */
    private void doAroundLogInfo(String format, Object result) {
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.debug(format, JSONObject.toJSONString(result, excludefilter));
    }
}
