package com.wyci.opendemo.service;

/**
 * @author kindear
 * chat 相关鉴权接口
 */
public interface ChatAuthService {
    // 获取当前操作用户ID
    default String getUserId(){
        return "0";
    }

    default String getUserName(){
        return "匿名用户";
    }
}
