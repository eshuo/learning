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
        System.err.println(System.getProperty("java.class.path"));

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

//        System.out.println(Clock.systemDefaultZone().millis());
//
//        String base64 =
//            "dGVzdDAwNDtsaXVncTt0ZXN0MDA2O2h1YW5neHE7eWFuZDt6aGFuZ3g7aHVqeTt4dXNnO3h1Y2c7bGlkO2xpYW5naHo7bGlhbmdzcDt6aGFveDE7d3V5ejt5YW9qZjt0YW5nY2g7cWlud2I7Z2FvanQ7dGVzdDAwMzt6aHVyO3hpYXlkO3R1c2o7cmVua2I7bGljYjtsaXV5O3dhbmdsZTEyO3lhbnlwO2xpajtkdWFuaG47Y2hlbnJ1aTg7emhvbmd3eDtjaGVubG07emhhbmhqO3NoaXlhbmc2O3NpamY7amlueXE7aHV4eTt0ZXN0MDA3O3Rlc3QwMDE7dGVzdDAwMjt0ZXN0NTU7aGFubDtsZWl6dzttdXNoZW47bGl4eTtnYW96cDtsaXRxO2x1dGluZ2ppZTt3dXRhaXNoaTtoYWlsaWFuY2hlbmc7bml1bGluO3hpYW93ZWlxaWFuZzt0YW5nZ2w7emhhbmd4bTtqaWFzaDtsaXVsaW5mZWk7d2FuZ20xO3BhbnpqO2ppYW5nYnE7bGlueWw7ZHV5dW5mZW5nO3dhbmdsdXlhbzExO3RpYW5jO3htaWFvO3hxaTtoZ3VvO3lseTt6eHk7Z3hzO3lhbmd5O2ppYXpoO3NoZW5nbjt6b3V3ZW5zaGVuZztiaWFueGlud2VpO3BlaXRpYW55dW47bGl5aWRpMTtsdW95aXRpYW4xO3poYW5nbGUxMTt3YW5naGFpeGlvbmcxO3h1dGlhbjM7emhhbmd0aWFubWluZzI7eXV4aWFvdG9uZzt6aGFpdGFvO2RhaXppeWFvMjt4dWNoZW55dTt3YW5neGluZ2NodWFuO2xpY2hlbnlhbmcyO3poYW9saW4xMjtndW9ndWlmdTtzaGFuY2h1YW55aW47dHV5dW5xaW5nO2NoZW55dWUyMjtzaGVuaG9uZzEyO3NoYW5zb25nbGluO3poYW5neGkxNjtsaWppZTkzO25peWl5aW5nO3d1ZnVxdWFuO3poZW5neWFuZ3Fpbmc7d2FuZ3lhd2VpMjtsdnhpYTtyZW5odWl5aW5nO3l1ZXhpYW87ZHV4dWppZTt6aGVuZ3NpbWluMjt6aGFvaG9uZzEwO3dhbmdzaWtlO3lpbmxpNjtob3VsYW9ndW87bGlucWlmZW5nMTtsaW16O3poZW5naGVnZW47eGlleXUxMjtxaWFubG9uZzE7emhhbmd6aXlhbmcxO3poYW5naGFpcGluZzY7Y2hlbmJvNDY7Z2FveGluamlhbjtzb25nbGl3ZWkxO3lhb3NodWJvO3lhb2NoZW4xO3dhbmdqaWFuOTQ7Y2hlbmppYWZlbmcxO2NoZW5kb25nZW47Y2FpbGluZ2hvbmc7emh1YW5neGlhb3FpYW5nMTt6aGFuZ2hhbmcxNztsaWFuZ3poYW90b25nO2ppYW5naHVhMTA7emh1enVzaGVuZztsaXVqaWFuYW47c3Vuc2hpY2hhbzI7bGFueWluZ2Nvbmc7eXVhbnhpYW87eWFuZ3JvbmdmZW5nMTt6aG91eWluZzM3O2xpbmd1YW5nY2hhbmc7bWVuZ2g7d2FuZ2NodW5nZTtjYWltYW5saTt3YW5neW47bHVvd2VpMTY7eXVhbmJhb3l1O3dhbmdiaW5neXU7eWFubWVuZ2ppZTtsaWFvcTtjYWl4aWFuZ21pbmc7d2FuZ2NodW5sZWkyO2NoZW5nYW5nNTg7bGl1eWFuMjg7bWFveTt4dXJ1aTIwO1BhdHJpY2lhO2hhbmxwO2xpbnlqO2NoZW5qbDtjaGVuYjt6aGFuZ2R5O3hpYW95b25nemhpO3poYW5nenkxO2xpeTtmZW5nc2h1bzI7emhhb2Jvd2VpO2xpeGlhb3lpbmc0O3poYW5naGFvMjg7c2hpY2U7emhlbmd4O2Vtb2hhbjt4dWNsO3N1bnprO2xpdWo7d2FuZ3FxO3N1bnlvbmd5b25nO2xpa2UxNDt6aGFuZ2hvbmdqdW4xO3dhbmd4dWVsaTt5YW5qaWFvO3dhbmdqaWFuZ2hlO3dhbmdxaTg5O2d1b3R5O3h1Ync7eGllc3k7eWFuZ2h5O2xpbHVzaGE7bGFuZ3k7cWl1emg7Y2FpZDtjaGVueDtzaGVueWg7eXVmO2xpbDtsaXVsbDt3YW5namo7bGl6dztjaGVubDtoYW5tZDtnb25na3I7d2FuZ3g7bGlwO3podWFuZ3o7eWFuZ2o7eWFuZ3M7eHVqO3poYW5naDtsaXFxO2xpeW07d2FuZ2o7bGlmeDs=";
//        System.err.println(new String(Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8))));

//        final byte[] bytes = base64.getBytes();
//        int seekLen = 0;
//        byte[] headerLengthBytes = new byte[4];
//        System.arraycopy(bytes, seekLen, headerLengthBytes, 0, headerLengthBytes.length);
//
//        System.err.println(bytes2IntLittle(headerLengthBytes));
    }

    public static int bytes2IntLittle(byte[] bytes) {
        int int1 = bytes[0] & 255;
        int int2 = (bytes[1] & 255) << 8;
        int result = int1 | int2;
        if (bytes.length > 2) {
            int int3 = (bytes[2] & 255) << 16;
            int int4 = (bytes[3] & 255) << 24;
            result = result | int3 | int4;
        }

        return result;
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
