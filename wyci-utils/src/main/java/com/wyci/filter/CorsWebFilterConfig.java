package com.wyci.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName corsWebFilterConfig
 * @Description 配置全局CORS跨域策略
 */
@Configuration
@WebFilter(filterName = "corsWebFilterConfig")
public class CorsWebFilterConfig implements Filter {

    private static final String METHOD_OPTIONS = "OPTIONS";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        //漏扫调整
        String origin = request.getHeader("Origin");
        if (StringUtils.isBlank(origin)) {
            origin = request.getHeader("origin");
        }
        if (StringUtils.isNotBlank(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "*");
        }

        // 对 OPTIONS 请求 直接结束请求
        String method = request.getMethod();
        if (METHOD_OPTIONS.equals(method)) {
            return;
        }
        chain.doFilter(req, res);
    }

}