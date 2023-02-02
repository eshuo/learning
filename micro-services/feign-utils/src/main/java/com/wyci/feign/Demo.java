package com.wyci.feign;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-01-31 10:35
 * @Version V1.0
 */
public class Demo {


    public static void main(String[] args) {

        //正则  /auth/v1/function/login   /auth*/v1/function/login


        String regex = ".*/auth(/api)?(/v1/function)?/login";
        System.out.println("/auth/api/login".matches(regex));
    }

}
