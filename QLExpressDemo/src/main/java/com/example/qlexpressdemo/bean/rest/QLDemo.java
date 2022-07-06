package com.example.qlexpressdemo.bean.rest;

import lombok.Data;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-28 14:51
 * @Version V1.0
 */
public interface QLDemo {


    /**
     * 验证
     */
    @Data
    public static class verify {

        private String ruleId;

        private String userId;

    }






}
