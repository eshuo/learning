package com.example.tokenuserdemo.demos.service;

import com.example.tokenuserdemo.demos.domain.ApiResult;
import java.io.Serializable;
import com.example.tokenuserdemo.demos.domain.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Description @Author wangshuo @Date 2023-03-28 19:20 @Version V1.0
 */
@Service
public class UserService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("${iam.token.path}")
    private String iamTokenPath;


    /**
     * 换取用户信息
     * @param token
     * @return
     */
    public UserInfo token2UserInfo(String token) {

        if (null == token || token.length() <= 0) {
            throw new RuntimeException("无效token");
        }
        // 设置请求头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=utf8");
        headers.add("AuthToken-Authorization", token);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>( new LinkedMultiValueMap<>(3), headers);

        ResponseEntity<ApiResult> responseEntity = restTemplate.postForEntity(iamTokenPath+"/auth/api/v1/function/getUserInfo", requestEntity,ApiResult.class);

        if (responseEntity.hasBody() && !HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new RuntimeException("请求IAM失败");
        }
        return  responseEntity.getBody().getData();
    }
}
