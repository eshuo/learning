package com.eshuo.api.controller;

import com.eshuo.dto.resp.BlockResponse;
import com.eshuo.dto.resp.StreamResponse;
import com.eshuo.service.DifyService;
import javax.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-02-26 20:43
 * @Version V1.0
 */
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    @Value("${dify.key.test}")
    private String testKey;

    @Resource
    private  DifyService difyService;


    @GetMapping("/block")
    public String test1() {
        String query = "我要生成一个报告";
        BlockResponse blockResponse = difyService.blockingMessage(query, 0L, testKey);
        return blockResponse.getAnswer();
    }

    @GetMapping("/stream")
    public Flux<StreamResponse> test2() {
        String query = "我要生成一个报告";
        return difyService.streamingMessage(query, 0L, testKey);
    }
}

