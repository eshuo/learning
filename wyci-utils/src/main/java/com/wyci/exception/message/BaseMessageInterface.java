package com.wyci.exception.message;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-18 17:13
 * @Version V1.0
 */
public interface BaseMessageInterface {

    /**
     * 基础错误码
     * <p>

     * </p>
     */
    int BASE_CODE = 0;

    /**
     * 状态码
     *
     * @return
     */
    int getCode();

    /**
     * 响应信息
     *
     * @return
     */
    String getMessage();


}
