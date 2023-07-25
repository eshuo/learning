package com.wyci;

import com.eetrust.etcommon.Common;
import com.eetrust.etcommon.Crypt;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;import java.util.HashMap;
import java.util.List;import java.util.TreeMap;
/**
 * @Description @Author wangshuo @Date 2023-02-28 15:50 @Version V1.0
 */
public class RunDemo {



    private static final String NAME_REGEX = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$";




  public static void main(String[] args) throws Exception {

      final List<String> roleLevelCodes =new ArrayList<>();
      roleLevelCodes.add("张三");
      roleLevelCodes.add("张三.1");
      roleLevelCodes.add("张三.1.2");
      roleLevelCodes.add("张三.2");


      final  List<String> deptLeveCodeList = new ArrayList<>();
      deptLeveCodeList.add("张三.1.2.3");
      deptLeveCodeList.add("张三.2.3");



          if (roleLevelCodes.stream().anyMatch(leve -> deptLeveCodeList.stream().anyMatch(code -> code.startsWith(leve)))) {
              System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          }



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
}
