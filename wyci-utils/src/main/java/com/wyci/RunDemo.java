package com.wyci;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Description @Author wangshuo @Date 2023-02-28 15:50 @Version V1.0
 */
public class RunDemo {



    private static final String NAME_REGEX = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$";



//
//    /**
//     * 获取调用的类名
//     *
//     * @return String
//     */
//    public static String getClassName() {
//        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//        StackTraceElement e = stacktrace[2];
//        String className = e.getClassName();
//        return className;
//    }
//
//    /**
//     * 获取调用的方法名
//     *
//     * @return String
//     */
//    public static String getMethodName() {
//        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//        StackTraceElement e = stacktrace[2];
//        String methodName = e.getMethodName();
//        return methodName;
//    }
//
//    public static String getFileName() {
//        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//        StackTraceElement e = stacktrace[2];
//        String methodName = e.getFileName();
//        return methodName;
//    }
//
//    public static int getLineNumber() {
//        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//        StackTraceElement e = stacktrace[2];
//        int line = e.getLineNumber();
//        return line;
//    }
//
//    public static void main(String[] args) {
//        System.out.println("当前运行的类："+getClassName());
//        System.out.println("当前执行的方法："+getMethodName());
//        System.out.println("当前文件名："+getFileName());
//        System.out.println("当前执行的行数："+getLineNumber());
//    }

  public static void main(String[] args) throws Exception {


//      SecureRandom sr = new SecureRandom();
//
//      for (int i = 0; i < 100; i++) {
//          System.out.println(new SecureRandom().nextDouble());
//          System.err.println(Math.random());
//      }
      String str
          = "zLG5rKdrEBX%2BiCefQrUgEI4yEtvtGTKL";

      final String decode = URLDecoder.decode("zLG5rKdrEBX+iCefQrUgEI4yEtvtGTKL", "UTF-8");

      final String encodeToString = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
      System.err.println(encodeToString);
      System.err.println(new String(Base64.getDecoder().decode(encodeToString.getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8));



      System.err.println(str.contains("%"));
      System.err.println(decode);
      System.err.println(decode.equals("zLG5rKdrEBX%2BiCefQrUgEI4yEtvtGTKL"));
      System.err.println(File.separator);
      System.out.println(getLocalIpByNetcard());
      System.err.println(InetAddress.getLocalHost().getHostAddress());


      ServletRequestAttributes servletRequestAttributes =
          (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

      if(null != servletRequestAttributes){
          HttpServletRequest request = servletRequestAttributes.getRequest();
          System.err.println(request.getRemoteAddr());
          System.err.println( InetAddress.getByName(request.getRemoteAddr()));
          System.err.println( InetAddress.getByName(request.getRemoteAddr()).getHostName());
      }



//      System.err.println("/auth1/pong".endsWith("/pong"));
//      System.err.println("/auth1/token_pong".endsWith("/token_pong"));

//      final List<String> roleLevelCodes =new ArrayList<>();
//      roleLevelCodes.add("张三");
//      roleLevelCodes.add("张三.1");
//      roleLevelCodes.add("张三.1.2");
//      roleLevelCodes.add("张三.2");
//
//
//      final  List<String> deptLeveCodeList = new ArrayList<>();
//      deptLeveCodeList.add("张三.1.2.3");
//      deptLeveCodeList.add("张三.2.3");
//
//
//
//          if (roleLevelCodes.stream().anyMatch(leve -> deptLeveCodeList.stream().anyMatch(code -> code.startsWith(leve)))) {
//              System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//          }



//      String inputString = "张三";
//      if (inputString.matches(NAME_REGEX)) {
//          System.out.println("匹配");
//      } else {
//          System.out.println("不匹配");
//      }
//
//
//      String ssss= "山东省";
//
//
//      System.err.println(ssss.indexOf("省"));
//      System.err.println(ssss.length());
//      System.err.println(ssss.indexOf("省")==(ssss.length()-1));
//      String str = "山东省";
//      String toRemove = "省";
//      int lastIndex = str.lastIndexOf(toRemove);
//      if (lastIndex != -1) {
//          str = str.substring(0, lastIndex) + str.substring(lastIndex + toRemove.length());
//      }
//      System.out.println(str);

//    String s = "123456|654321|222222";
//
//    System.out.println(s.contains("2222222"));
//
//    String hash =
//        "eyJhbGciOiJIUzUxMiJ9"
//            + ".eyJqdGkiOiI4MjU4MTQwMDg4MjgyNjQ0NDgiLCJzdWIiOiJ7XCJhdXRoZWRTZXRcIjowLFwiYXV0aGVudGljYXRlZFwiOnRydWUsXCJnbG9iYWxQb2xpY3lWZXJpZmllZFwiOmZhbHNlLFwiaW5zdGFuY2VDb2RlXCI6XCJDZXJ0QXV0aFwiLFwibGFzdEF1dGhFcnJcIjpcIlwiLFwibG9naW5TdGF0ZVwiOjEsXCJwcmluY2lwYWxcIjp7XCJjZXJ0U25cIjpcIjAzMjBGNkU3RDBCN0FBQzk3NTJGQTEyMDk4MjdCN0MyXCIsXCJlbmFibGVDYXB0Y2hhXCI6XCIxXCIsXCJyZXN1bHRDb2RlXCI6XCIxXCIsXCJyZXN1bHRNZXNzYWdlXCI6XCLorqTor4HpgJrov4dcIixcInRlbXBvcmFyeUF1dGhcIjpmYWxzZSxcInVzZXJJZFwiOlwiNzE5NTE5NjA4NjM1MTM1OTk4NFwiLFwidXNlckluZm9cIjp7XCJhdXRob3JpemVcIjpmYWxzZSxcImNyeXB0ZWRQYXNzd29yZFwiOlwiXCIsXCJkYXRhVHlwZVwiOjEsXCJlbWFpbFwiOlwiXCIsXCJnZW5kZXJcIjpcIlowM1wiLFwiaWRcIjpcIjcxOTUxOTYwODYzNTEzNTk5ODRcIixcImlkQ2FyZE51bWJlclwiOlwiXCIsXCJpc0RlbGV0ZVwiOjAsXCJsb2dpbk5hbWVcIjpcImxlaWNcIixcIm1vYmlsZVwiOlwiXCIsXCJwYXNzd29yZFwiOlwidE1GVEVIOWNUWW89XCIsXCJzZWNMZXZlbFwiOlwiXCIsXCJzaG93TnVtYmVyXCI6NzUzLFwic3RhdHVzXCI6MSxcInVzZXJOYW1lXCI6XCLpm7fmiJBcIn19LFwidXNlclwiOntcIiRyZWZcIjpcIiQucHJpbmNpcGFsLnVzZXJJbmZvXCJ9fSIsImlzcyI6InVzZXIiLCJpYXQiOjE2NzcwNTU4NjgsImV4cCI6MTY3NzA1NzY2OH0.JqgQv8YiPkigW1Vt4gkiRLZ_C6ykwDeJV9-xDQ65AEr9jbFn1QJStFSgA2d6A8reyAhxevtW9J6_moiwXnhDqQ";
//
//    System.out.println(hash.hashCode());
//    System.out.println(hash.hashCode());
//    System.out.println(hash.hashCode());
//    System.out.println(hash.hashCode());
//
//    TreeMap<Integer, String> map = new TreeMap();
//
//    map.put(1, "一");
//    map.put(6, "六");
//    map.put(2, "二");
//    map.put(3, "三");
//    map.put(4, "四");
//    map.put(1, "重复一");
//
//
//    map.forEach((k,v)->{
//        System.out.println("key = "+k+",value="+v);
//    });
//
//    final List<String> arrayList = new ArrayList<>(map.values());
//
//    arrayList.forEach(aa -> System.out.println("====>" + aa));
  }

    /**
     * 直接根据第一个网卡地址作为其内网ipv4地址，避免返回 127.0.0.1
     *
     * @return
     */
    public static String getLocalIpByNetcard() {
        try {
            for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements(); ) {
                NetworkInterface item = e.nextElement();
                for (InterfaceAddress address : item.getInterfaceAddresses()) {
                    if (item.isLoopback() || !item.isUp()) {
                        continue;
                    }
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address) address.getAddress();
                        return inet4Address.getHostAddress();
                    }
                }
            }
            return InetAddress.getLocalHost().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
