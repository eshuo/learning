package com.wyci.feign;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        String str = "这是一个字符串,我的手机号:14766685062,需要脱敏,另外一个手机号:17208353109也要脱敏";
        String regex = "(\\d{3})\\d{4}(\\d{4})";
        str = str.replaceAll(regex, "$1****$2");
        System.out.println(str);

        String phoneNumbers = "这是一个字符串,我的手机号:14766685062,需要脱敏,另外一个手机号:17208353109也要脱敏";
        String phoneRegex = "(?<!\\d)(?:(?:1[3456789]\\d{9})|(?:861[3456789]\\d{9}))(?!\\d)";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumbers);
        while (matcher.find()) {
            String phoneNumber = matcher.group();
            phoneNumbers= phoneNumbers.replaceAll(phoneNumber,phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        }
        System.out.println("Found phone phoneNumbers: " + phoneNumbers);
    }


}
