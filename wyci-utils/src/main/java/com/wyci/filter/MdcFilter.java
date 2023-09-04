package com.wyci.filter;

import com.wyci.utils.uuid.IdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description @Author wangshuo @Date 2023-09-01 17:47 @Version V1.0
 */
@Component
public class MdcFilter implements Filter {

    public static final String LOG_ID = "LOG-THREAD-ID";

    private static final Logger logger = LoggerFactory.getLogger(MdcFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String xRequestId = request.getHeader(LOG_ID);
        if (StringUtils.isNotEmpty(xRequestId)) {
            logger.trace("请求头中存在LOG-THREAD-ID，设置为traceId");
            MDC.put(LOG_ID, xRequestId);
        } else {
            logger.trace("请求头中不存在LOG-THREAD-ID，生成一个traceId");
            MDC.put(LOG_ID, IdGenerator.nextId(MdcFilter.class));
        }
        filterChain.doFilter(request, servletResponse);
    }
}
