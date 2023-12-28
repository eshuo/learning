package com.wyci.feign;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

//    String code = "951678|368090|416581|165334|536120|419870";
//    boolean success = false;
//    if (code.contains("|")) {
//      final String[] split = code.split("\\|");
//      for (String s : split) {
//        if ("416581".equals(s)) {
//          success = true;
//          break;
//        }
//      }
//    } else {
//      success = "123456".equals(code);
//    }
//    System.out.println(success);
//
//    String str = "这是一个字符串,我的手机号:14766685062,需要脱敏,另外一个手机号:17208353109也要脱敏";
//    String regex = "(\\d{3})\\d{4}(\\d{4})";
//    str = str.replaceAll(regex, "$1****$2");
//    System.out.println(str);
//
//    String phoneNumbers = "这是一个字符串,我的手机号:14766685062,需要脱敏,另外一个手机号:17208353109也要脱敏";
//    String phoneRegex = "(?<!\\d)(?:(?:1[3456789]\\d{9})|(?:861[3456789]\\d{9}))(?!\\d)";
//    Pattern pattern = Pattern.compile(phoneRegex);
//    Matcher matcher = pattern.matcher(phoneNumbers);
//    while (matcher.find()) {
//      String phoneNumber = matcher.group();
//      phoneNumbers = phoneNumbers.replaceAll(phoneNumber, phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
//    }
//    System.out.println("Found phone phoneNumbers: " + phoneNumbers);
//
//    final long time = Long.parseLong("1698739196459");
//    final long currentTimeMillis = 1698739156738L;
//    if (time > currentTimeMillis + 10 * 60 * 1000 || currentTimeMillis - time > 10 * 60 * 1000) {
//      System.err.println("666");
//    } else {
//      System.err.println("888");
//    }

//    System.err.println(isSecAdmin("xtadmin1"));
//
//    final List<String> list = Arrays.asList("8121239448257929290",
//        "8121239448257929968",
//        "8121239448257929878",
//        "8121239448257929882",
//        "8121239448257929886",
//        "8121239448257929890",
//        "8121239448257929894",
//        "8121239448257929898",
//        "8121239448257929902",
//        "8121239448257929906",
//        "8121239448257929910",
//        "8121239448257929914",
//        "8121239448257929290",
//        "8121239448257929918",
//        "8121239448257929922",
//        "8121239448257929926");
//
//    final List<String> collect = list.stream().distinct().peek(s->s=s.replace("8121239448257929926","123")).collect(Collectors.toList());
//    System.err.println(list.size());
//    System.err.println(collect.size());
//    System.err.println(collect.toString());

//        List<DemoDo> demoDos = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            DemoDo demoDo = new DemoDo();
//            demoDo.setId(String.valueOf(i));
//            demoDo.setName(String.valueOf(i));
//            demoDos.add(demoDo);
//        }
//
//        System.err.println("DemoDo = "+demoDos);
//
//
//        demoDos= demoDos.stream().peek(d->d.setName(null)).collect(Collectors.toList());
//        System.err.println(demoDos);

//        long keyExpire = 1234L;
//        String msg = "动态口令验证失败次数已达上限，请" + keyExpire / 60 + "分" + keyExpire % 60 + "秒后重试";
//
//        System.err.println(msg);



//        System.err.println(String.valueOf(new Integer(1)).equals("1"));

        //时间demo

//        ZoneId zone = ZoneId.of("Asia/Shanghai");
//        Clock clock = Clock.system(zone);
//
//        System.err.println(System.currentTimeMillis());
//        System.err.println(clock.systemUTC().millis());
//        System.err.println(clock.systemUTC().instant());
//        ZonedDateTime zdt = clock.instant().atZone(clock.getZone());
//        System.out.println("给定区域的日期时间是: " + zdt.toString());


//
//        DemoDo demo = new DemoDo();
//
//        for (Field declaredField : demo.getClass().getDeclaredFields()) {
//            System.out.println(declaredField.getName());
//        }


//        String json = "{\"code\":58400,\"message\":\"请联系管理员\",\"status\":500,\"success\":false,\"timestamp\":17032342655\n"
//            + "64}";
//        final String encodeToString = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
//        System.out.println(encodeToString);
//        System.out.println(Base64.getDecoder().decode(encodeToString));
//        System.out.println(Base64.getDecoder().decode(encodeToString).toString());
//        System.out.println(new String(Base64.getDecoder().decode(encodeToString), StandardCharsets.UTF_8));


        System.out.println(Clock.systemDefaultZone().millis());
    }


    public enum SecurityType {
        AES,
        RSA,
        ;
    }


    public static class DemoDo {

        private String id;

        private String name;


        private String desc3ds;

        private String dddType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getDesc3ds() {
            return desc3ds;
        }

        public void setDesc3ds(String desc3ds) {
            this.desc3ds = desc3ds;
        }


        public String getDddType() {
            return dddType;
        }

        public void setDddType(String dddType) {
            this.dddType = dddType;
        }

        @Override
        public String toString() {
            return "DemoDo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
        }
    }


    private static boolean isSecAdmin(String split) {
        final List<String> list = Arrays.asList("xtadmin", "aqadmin", "sjadmin");
        return list.contains(split);
    }

}
