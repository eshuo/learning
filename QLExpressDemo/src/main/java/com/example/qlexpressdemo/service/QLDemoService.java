package com.example.qlexpressdemo.service;

import com.example.qlexpressdemo.bean.rest.QLDemo;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-28 14:49
 * @Version V1.0
 */

public interface QLDemoService {


//    com.example.qlexpressdemo.bean.rest.QLDemo.verify

    /**
     * 验证
     *
     * @return
     */
    boolean verify(QLDemo.verify verify);


    boolean check(QLDemo.verify verify) throws Exception;


    /**
     * 评分卡
     */
    default Object score(QLDemo.verify verify) throws Exception {
        return null;
    }


}
