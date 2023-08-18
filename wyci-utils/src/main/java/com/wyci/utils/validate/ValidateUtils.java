package com.wyci.utils.validate;



import java.util.regex.Pattern;

/**
 * @Description 验证工具类  如需动态验证 需要引入admin-feign服务
 * @Author wangshuo
 * @Date 2023-01-07 10:13
 * @Version V1.0
 */
public class ValidateUtils {


    public static final String PHONE_PATTERN_STR = "^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$";

    public static final String ID_CARD_PATTERN_STR = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

    public static final String EMAIL_PATTERN_STR = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";



    /**
     * 验证手机号
     *
     * @param mobile
     *
     * @return
     */
    public static boolean mobile(String mobile) {
        return Pattern.compile(PHONE_PATTERN_STR).matcher(mobile).matches();
    }


    /**
     * 验证身份证号
     *
     * @param idCard
     *
     * @return
     */
    public static boolean idCard(String idCard) {
        return Pattern.compile(ID_CARD_PATTERN_STR).matcher(idCard).matches();
    }


    /**
     * 验证邮箱
     *
     * @param email
     *
     * @return
     */
    public static boolean email(String email) {
        return Pattern.compile(EMAIL_PATTERN_STR).matcher(email).matches();

    }

}
