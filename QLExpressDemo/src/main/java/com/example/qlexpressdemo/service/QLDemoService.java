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
    boolean verify(QLDemo.Verify verify);


    boolean check(QLDemo.Verify verify) throws Exception;



    String conditions(QLDemo.ConditionsInfo conditionsInfo) throws Exception;


    /**
     * 评分卡
     */
    default Object score(QLDemo.Verify verify) throws Exception {
        return null;
    }


}
