package com.wyci.sso.demo.demos.web;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description oauth
 * @Author wangshuo
 * @Date 2025-02-22 17:47
 * @Version V1.0
 */
@RestController
public class OauthDemoController {

    @Value("${demo.oauth.redirect}")
    private String redirect;

    @Value("${demo.oauth.accessToken}")
    private String accessToken;

    @Value("${demo.oauth.userInfo}")
    private String userInfo;


    @Value("${demo.oauth.appCode}")
    private String appCode;

    @Value("${demo.oauth.secret}")
    private String secret;
    /**
     * accessTokenX
     */
    @JsonProperty("access_token")
    private String accessTokenX;
    /**
     * expiresIn
     */
    @JsonProperty("expires_in")
    private String expiresIn;
    /**
     * refreshToken
     */
    @JsonProperty("refresh_token")
    private String refreshToken;
    /**
     * scope
     */
    @JsonProperty("scope")
    private String scope;
    /**
     * tokenType
     */
    @JsonProperty("token_type")
    private String tokenType;


    @GetMapping(path = {"/redirect"})
    public ResponseEntity<Void> redirect() {
        return ResponseEntity.status(HttpStatus.FOUND)
            .header("Location", redirect)
            .build();
    }


    //iam配置对应应用编码 单点地址为:http://ip:8080/sso

    //浏览器访问 http://ip:8080/sso
    @GetMapping(path = {"/accessToken", "/token", "/sso"})
    public Object accessToken(@RequestParam(value = "code", required = false) String code, HttpServletResponse response) throws IOException {

        //如果code为空  就重定向到申请code地址
        if (ObjectUtils.isEmpty(code)) {
            response.sendRedirect(redirect);
            return "redirect:" + redirect;
        }

        System.err.println("获取code==============" + code);
        try {
            RestTemplate restTemplate = new RestTemplate();

            //申请令牌
            String getTokenURL =
                accessToken + "?grant_type=authorization_code" + "&client_id=" + appCode + "&client_secret=" + secret + "&code=" + code + "&redirect_uri=xxx";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/x-www-form-urlencoded");
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(null, httpHeaders);

            ResponseEntity<String> getTokenResult = restTemplate.postForEntity(getTokenURL, httpEntity, String.class);
            String body = getTokenResult.getBody();
            System.err.println("===============================" + JSON.parseObject(body));

            //获取用户信息

            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("appCode", appCode);
            dataMap.put("credential", JSON.parseObject(body).getString("access_token"));
            Map<String, Map<String, String>> data = new HashMap<>();
            data.put("data", dataMap);
            HttpHeaders httpHeaders1 = new HttpHeaders();
            httpHeaders1.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
            HttpEntity<Map<String, Map<String, String>>> httpEntity1 = new HttpEntity<>(data, httpHeaders1);
            RestTemplate restTemplate1 = new RestTemplate();
            ResponseEntity<Object> getTokenResult1 = restTemplate1.postForEntity(userInfo, httpEntity1, Object.class);
            return getTokenResult1.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
