/**
 * @Description
 * @Author wangshuo
 * @Date 2024-08-28 15:27
 * @Version V1.0
 */
public class StrDemo {


    public static void main(String[] args) {

        String s = "1234<NAME>56789</NAME>654321";

        String target = "NAME";
        if (s.contains("<" + target + ">")) {

            //<NAME>56789</NAME>
            String substring = s.substring(s.indexOf("<" + target + ">"), s.indexOf("</" + target + ">") + ("</" + target + ">").length());

            //<NAME>56789</NAME>
            System.out.println(substring);
            //1234654321
            System.out.println(s.replace(substring,""));

        }


    }


}
