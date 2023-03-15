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


//        String regex = ".*/auth(/api)?(/v1/function)?/login";
//        System.out.println("/auth/api/login".matches(regex));

       String code = "951678|368090|416581|165334|536120|419870";
        boolean success = false;
        if (code.contains("|")) {
            final String[] split = code.split("\\|");
            for (String s : split) {
                if ("416581".equals(s)) {
                    success = true;
                    break;
                }
            }
        } else {
            success = "123456".equals(code);
        }
        System.out.println(success);


    }

}
