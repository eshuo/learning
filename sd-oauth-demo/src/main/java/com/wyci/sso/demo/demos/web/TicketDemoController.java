package com.wyci.sso.demo.demos.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

/**
 * @Description ticket demo
 * @Author wangshuo
 * @Date 2025-07-04 10:20
 * @Version V1.0
 */
@RestController
public class TicketDemoController {


    @Value("${demo.ticket.iamUrl}")
    private String iamUrl;

    @Value("${demo.ticket.applyUrl}")
    private String applyUrl;

    @Value("${demo.ticket.userInfoUrl}")
    private String userUrl;

    //申请ticket

    @PostMapping("/auth/api/v1/function/sso")
    public String applyTicket(@RequestBody LoginNameReq req, HttpServletRequest request) {

        String iamToken = request.getHeader("AuthToken-Authorization");
        if (StringUtils.isEmpty(iamToken)) {
            throw new RuntimeException("IAM-TOKEN不存在");
        }

        String appCode = req.getAppCode();
        if (StringUtils.isEmpty(appCode)) {
            throw new RuntimeException("应用编码不存在");
        }
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.set("AuthToken-Authorization", iamToken);
        HttpEntity<LoginNameReq> httpEntity = new HttpEntity<>(req, httpHeaders);

        ResponseEntity<String> iamResponse = restTemplate.postForEntity(iamUrl + applyUrl, httpEntity, String.class);
        System.err.println("applyTicket响应 ===============================>>>" + iamResponse.getBody());
        //{
        //    "code": 20000,
        //    "message": "处理成功",
        //    "data": {
        //        "appCode": "VPN",
        //        "token": null,
        //        "ssoTicket": {
        //            "key": "ticket",
        //            "value": "a9c2502195f2474cb0df9f59f1c345b4"
        //        },
        //        "ssoConfig": {
        //            "ssoUrl": "127.0.0.1:8009/123",
        //            "appType": "B/S",
        //            "ssoClientPathCN": null,
        //            "startMode": null,
        //            "ssoClientPath": null,
        //            "registry": null,
        //            "mobileAppType": null,
        //            "mobileAccessUrl": null,
        //            "mobileSsoUrl": null,
        //            "iosScheme": null,
        //            "androidScheme": null,
        //            "formScheme": null,
        //            "isAutoLogin": null
        //        },
        //        "level": null,
        //        "cause": null,
        //        "formScheme": null,
        //        "certSn": null
        //    },
        //    "status": 200,
        //    "timestamp": "2025-07-04 13:38:35",
        //    "errorInfo": null
        //}
        return iamResponse.getBody();
        //请求IAM申请ticket
    }

    //ticket换取用户
    @PostMapping("/sso/api/v1.0/ssoToken/getUserInfo")
    public String userInfo(@RequestBody GetUserInfoReq getUserInfo) {

        String credential = getUserInfo.getCredential();
        if (StringUtils.isEmpty(credential)) {
            throw new RuntimeException("单点凭证不存在");
        }

        String appCode = getUserInfo.getAppCode();
        if (StringUtils.isEmpty(appCode)) {
            throw new RuntimeException("应用编码不存在");
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<GetUserInfoReq> httpEntity = new HttpEntity<>(getUserInfo, httpHeaders);

        ResponseEntity<String> iamResponse = restTemplate.postForEntity(iamUrl + userUrl, httpEntity, String.class);
        System.err.println("获取用户响应 ===============================>>>" + iamResponse.getBody());
        //{
        //    "code": 20000,
        //    "message": "处理成功",
        //    "data": {
        //        "passport": "jtcjgly",
        //        "extendInfo": {
        //            "loginName": "jtcjgly",
        //            "phone": "",
        //            "mail": ""
        //        }
        //    },
        //    "status": 200,
        //    "timestamp": "2025-07-04 13:57:42",
        //    "errorInfo": null
        //}
        return iamResponse.getBody();
    }

    @ApiModel
    @Data
    @ToString
    public static class LoginNameReq {

        @ApiModelProperty("单点目标标识")
        private String appCode;

    }


    @Data
    @ApiModel(value = "单点服务ssoToken")
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class GetUserInfoReq implements Serializable {

        @ApiModelProperty(value = "业务系统编码")
        private String appCode;

        @ApiModelProperty(value = "单点凭证")
        private String credential;

    }


}
