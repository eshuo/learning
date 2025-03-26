package com.eshuo.service;

import com.alibaba.fastjson2.JSON;
import com.eshuo.dto.request.DifyRequestBody;
import com.eshuo.dto.response.BlockResponse;
import com.eshuo.dto.response.StreamResponse;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-02-26 20:39
 * @Version V1.0
 */

@Service
@RequiredArgsConstructor
public class DifyService {


    @Resource
    private RestTemplate restTemplate;

    @Resource
    private WebClient.Builder webClient;

    /**
     * 流式调用dify.
     *
     * @param query  查询文本
     * @param userId 用户id
     * @param apiKey apiKey 通过 apiKey 获取权限并区分不同的 dify 应用
     * @return Flux 响应流
     */
    public Flux<StreamResponse> streamingMessage(String query, String userId, String apiKey,String url,String conversationId) {
        //1.设置请求体
        DifyRequestBody body = new DifyRequestBody();
        body.setInputs(new HashMap<>());
        body.setQuery(query);
        body.setResponseMode("streaming");
        body.setConversationId(conversationId);
        body.setUser(userId);
        //2.使用webclient发送post请求
        return webClient.build().post()
            .uri(url)
            .headers(httpHeaders -> {
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                httpHeaders.setBearerAuth(apiKey);
            })
            .bodyValue(JSON.toJSONString(body))
            .retrieve()
            .bodyToFlux(StreamResponse.class);
    }


    /**
     * 阻塞式调用dify.
     *
     * @param query  查询文本
     * @param userId 用户id
     * @param apiKey apiKey 通过 apiKey 获取权限并区分不同的 dify 应用
     * @return BlockResponse
     */
    public BlockResponse blockingMessage(String query, String userId, String apiKey,String url,String conversationId) {
        //1.设置请求体
        DifyRequestBody body = new DifyRequestBody();
        body.setInputs(new HashMap<>());
        body.setQuery(query);
        body.setResponseMode("blocking");
        body.setConversationId(conversationId);
        body.setUser(userId);
        //2.设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(apiKey);
        //3.封装请求体和请求头
        String jsonString = JSON.toJSONString(body);
        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);
        //4.发送post请求，阻塞式
        ResponseEntity<BlockResponse> stringResponseEntity =
            restTemplate.postForEntity(url, entity, BlockResponse.class);
        //5.返回响应体
        return stringResponseEntity.getBody();
    }
}

