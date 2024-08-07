//package com.wyci;
//
//import com.alibaba.fastjson.JSONObject;
//import com.wyci.resp.ResRiskRequest;
//import com.wyci.resp.ResRiskResponse;
//import com.wyci.resp.ResRiskResponse.DictResponse;
//import java.net.Inet4Address;
//import java.net.InetAddress;
//import java.net.InterfaceAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @Description @Author wangshuo @Date 2023-02-28 15:50 @Version V1.0
// */
//public class RunDemo {
//
//
//    private static List<String> whites = new ArrayList<>();
//    private static final String NAME_REGEX = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$";
//
//
//    static {
//        whites.add("/v2/api-docs");
//        whites.add("/doc");
//        whites.add("/webjars");
//        whites.add("/swagger-resources");
//
//
//        whites.add("/actuator");
//
//        whites.add("/auth/api/login");
//        whites.add("/auth/api/v1.0/login");
//        whites.add("/auth/api/genLoginPage");
//        whites.add("/eim/api/v1.0/user/api/account/*");
//        whites.add("/eim/api/v1.0/user/api/*");
//        whites.add("/eim/api/v1.0/department/checkDeptDisabled/*");
//        whites.add("/ubsp/index.html");
//        whites.add("/tyml/v1.0/sync/recon/.*");
//        whites.add("/eim/dept/api/v1.0/department/*");
//        whites.add("/eim/user/api/v1.0/user/*");
//        whites.add("/tyml/v1.0/sync/incre/*");
//        whites.add("/tyml/v1.0/sync/init/*");
//        whites.add("/register/api/v1.0/app/api/queryApp/eim");
//        whites.add("/register/api/v1.0/app/.*");
//        whites.add("/tyml/v1.0/sync/init/.*");
//        whites.add("/data/api/v1.0/.*");
//        whites.add("sync/api/v1.0/insertSyncConfig");
//        whites.add("/sync/cert/config/v1.0/.*");
//        whites.add("/sync/api/v1.0/sysLevel/*");
//        whites.add("/sync/api/v1.0/.*");
//        whites.add("eim/api/v1.0/.*");
//        whites.add("/iam/sync/data/recon/.*");
//        whites.add("/address/v1/login");
//        whites.add("/address/v1/SSOLogin");
//        whites.add("/iam/attrConfig/manage/.*");
//        whites.add("/sso/.*");
//        whites.add("/src/sync/data/iam/eim");
//        whites.add("/src/sync/data/iam/.*");
//    }
//
////
////    /.*
////     * 获取调用的类名
////     *
////     * @return String
////     */
////    public static String getClassName() {
////        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
////        StackTraceElement e = stacktrace[2];
////        String className = e.getClassName();
////        return className;
////    }
////
////    /.*
////     * 获取调用的方法名
////     *
////     * @return String
////     */
////    public static String getMethodName() {
////        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
////        StackTraceElement e = stacktrace[2];
////        String methodName = e.getMethodName();
////        return methodName;
////    }
////
////    public static String getFileName() {
////        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
////        StackTraceElement e = stacktrace[2];
////        String methodName = e.getFileName();
////        return methodName;
////    }
////
////    public static int getLineNumber() {
////        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
////        StackTraceElement e = stacktrace[2];
////        int line = e.getLineNumber();
////        return line;
////    }
////
////    public static void main(String[] args) {
////        System.out.println("当前运行的类："+getClassName());
////        System.out.println("当前执行的方法："+getMethodName());
////        System.out.println("当前文件名："+getFileName());
////        System.out.println("当前执行的行数："+getLineNumber());
////    }
//
//    public static void main(String[] args) throws Exception {
//
//        //秒转分钟
//
//        long minute = 288 / 60;
//        long second = 288 % 60;
//
//        System.out.println(minute);
//        System.out.println(second);
////        String url ="/auth/api/v1/function/login";
////
////
////        System.out.println(whites.stream().noneMatch(url::matches));
////
////
////        String s =new String("wangs".getBytes(), Charset.forName("GBK"));
////        System.out.println(s);
//
////      final DictResponse dictResponse = queryDict();
//
////      SecureRandom sr = new SecureRandom();
////
////      for (int i = 0; i < 100; i++) {
////          System.out.println(new SecureRandom().nextDouble());
////          System.err.println(Math.random());
////      }
////      String str
////          = "zLG5rKdrEBX%2BiCefQrUgEI4yEtvtGTKL";
////
////      final String decode = URLDecoder.decode("zLG5rKdrEBX+iCefQrUgEI4yEtvtGTKL", "UTF-8");
////
////      final String encodeToString = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
////      System.err.println(encodeToString);
////      System.err.println(new String(Base64.getDecoder().decode(encodeToString.getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8));
////
////
////
////      System.err.println(str.contains("%"));
////      System.err.println(decode);
////      System.err.println(decode.equals("zLG5rKdrEBX%2BiCefQrUgEI4yEtvtGTKL"));
////      System.err.println(File.separator);
////      System.out.println(getLocalIpByNetcard());
////      System.err.println(InetAddress.getLocalHost().getHostAddress());
////
////
////      ServletRequestAttributes servletRequestAttributes =
////          (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
////
////      if(null != servletRequestAttributes){
////          HttpServletRequest request = servletRequestAttributes.getRequest();
////          System.err.println(request.getRemoteAddr());
////          System.err.println( InetAddress.getByName(request.getRemoteAddr()));
////          System.err.println( InetAddress.getByName(request.getRemoteAddr()).getHostName());
////      }
//
////      System.err.println("/auth1/pong".endsWith("/pong"));
////      System.err.println("/auth1/token_pong".endsWith("/token_pong"));
//
////      final List<String> roleLevelCodes =new ArrayList<>();
////      roleLevelCodes.add("张三");
////      roleLevelCodes.add("张三.1");
////      roleLevelCodes.add("张三.1.2");
////      roleLevelCodes.add("张三.2");
////
////
////      final  List<String> deptLeveCodeList = new ArrayList<>();
////      deptLeveCodeList.add("张三.1.2.3");
////      deptLeveCodeList.add("张三.2.3");
////
////
////
////          if (roleLevelCodes.stream().anyMatch(leve -> deptLeveCodeList.stream().anyMatch(code -> code.startsWith(leve)))) {
////              System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
////          }
//
////      String inputString = "张三";
////      if (inputString.matches(NAME_REGEX)) {
////          System.out.println("匹配");
////      } else {
////          System.out.println("不匹配");
////      }
////
////
////      String ssss= "山东省";
////
////
////      System.err.println(ssss.indexOf("省"));
////      System.err.println(ssss.length());
////      System.err.println(ssss.indexOf("省")==(ssss.length()-1));
////      String str = "山东省";
////      String toRemove = "省";
////      int lastIndex = str.lastIndexOf(toRemove);
////      if (lastIndex != -1) {
////          str = str.substring(0, lastIndex) + str.substring(lastIndex + toRemove.length());
////      }
////      System.out.println(str);
//
////    String s = "123456|654321|222222";
////
////    System.out.println(s.contains("2222222"));
////
////    String hash =
////        "eyJhbGciOiJIUzUxMiJ9"
////            + "
////            .eyJqdGkiOiI4MjU4MTQwMDg4MjgyNjQ0NDgiLCJzdWIiOiJ7XCJhdXRoZWRTZXRcIjowLFwiYXV0aGVudGljYXRlZFwiOnRydWUsXCJnbG9iYWxQb2xpY3lWZXJpZmllZFwiOmZhbHNlLFwiaW5zdGFuY2VDb2RlXCI6XCJDZXJ0QXV0aFwiLFwibGFzdEF1dGhFcnJcIjpcIlwiLFwibG9naW5TdGF0ZVwiOjEsXCJwcmluY2lwYWxcIjp7XCJjZXJ0U25cIjpcIjAzMjBGNkU3RDBCN0FBQzk3NTJGQTEyMDk4MjdCN0MyXCIsXCJlbmFibGVDYXB0Y2hhXCI6XCIxXCIsXCJyZXN1bHRDb2RlXCI6XCIxXCIsXCJyZXN1bHRNZXNzYWdlXCI6XCLorqTor4HpgJrov4dcIixcInRlbXBvcmFyeUF1dGhcIjpmYWxzZSxcInVzZXJJZFwiOlwiNzE5NTE5NjA4NjM1MTM1OTk4NFwiLFwidXNlckluZm9cIjp7XCJhdXRob3JpemVcIjpmYWxzZSxcImNyeXB0ZWRQYXNzd29yZFwiOlwiXCIsXCJkYXRhVHlwZVwiOjEsXCJlbWFpbFwiOlwiXCIsXCJnZW5kZXJcIjpcIlowM1wiLFwiaWRcIjpcIjcxOTUxOTYwODYzNTEzNTk5ODRcIixcImlkQ2FyZE51bWJlclwiOlwiXCIsXCJpc0RlbGV0ZVwiOjAsXCJsb2dpbk5hbWVcIjpcImxlaWNcIixcIm1vYmlsZVwiOlwiXCIsXCJwYXNzd29yZFwiOlwidE1GVEVIOWNUWW89XCIsXCJzZWNMZXZlbFwiOlwiXCIsXCJzaG93TnVtYmVyXCI6NzUzLFwic3RhdHVzXCI6MSxcInVzZXJOYW1lXCI6XCLpm7fmiJBcIn19LFwidXNlclwiOntcIiRyZWZcIjpcIiQucHJpbmNpcGFsLnVzZXJJbmZvXCJ9fSIsImlzcyI6InVzZXIiLCJpYXQiOjE2NzcwNTU4NjgsImV4cCI6MTY3NzA1NzY2OH0.JqgQv8YiPkigW1Vt4gkiRLZ_C6ykwDeJV9-xDQ65AEr9jbFn1QJStFSgA2d6A8reyAhxevtW9J6_moiwXnhDqQ";
////
////    System.out.println(hash.hashCode());
////    System.out.println(hash.hashCode());
////    System.out.println(hash.hashCode());
////    System.out.println(hash.hashCode());
////
////    TreeMap<Integer, String> map = new TreeMap();
////
////    map.put(1, "一");
////    map.put(6, "六");
////    map.put(2, "二");
////    map.put(3, "三");
////    map.put(4, "四");
////    map.put(1, "重复一");
////
////
////    map.forEach((k,v)->{
////        System.out.println("key = "+k+",value="+v);
////    });
////
////    final List<String> arrayList = new ArrayList<>(map.values());
////
////    arrayList.forEach(aa -> System.out.println("====>" + aa));
//    }
//
//    public static ResRiskResponse.DictResponse queryDict() {
//
//        final ResRiskRequest.QueryDict queryDict = new ResRiskRequest.QueryDict();
//        queryDict.setSize(500);
//        queryDict.setType("client_process");
//        HttpEntity<ResRiskRequest.QueryDict> requestEntity = new HttpEntity<>(queryDict, null);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://10.3.43.160:19100/manage/api/v1.0/dict/query", requestEntity, String.class);
//        if (responseEntity.hasBody() && HttpStatus.OK.equals(responseEntity.getStatusCode())) {
//            JSONObject result = JSONObject.parseObject(responseEntity.getBody());
//            JSONObject policyData = result.getJSONObject("data");
//            return policyData.toJavaObject(DictResponse.class);
//        }
//        return null;
//    }
//
//    /**
//     * 直接根据第一个网卡地址作为其内网ipv4地址，避免返回 127.0.0.1
//     *
//     * @return
//     */
//    public static String getLocalIpByNetcard() {
//        try {
//            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
//                NetworkInterface item = e.nextElement();
//                for (InterfaceAddress address : item.getInterfaceAddresses()) {
//                    if (item.isLoopback() || !item.isUp()) {
//                        continue;
//                    }
//                    if (address.getAddress() instanceof Inet4Address) {
//                        Inet4Address inet4Address = (Inet4Address) address.getAddress();
//                        return inet4Address.getHostAddress();
//                    }
//                }
//            }
//            return InetAddress.getLocalHost().getHostAddress();
//        } catch (SocketException | UnknownHostException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
