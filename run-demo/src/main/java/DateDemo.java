import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-08-07 17:04
 * @Version V1.0
 */
public class DateDemo {


    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String nowFormat = simpleDateFormat.format(now);
        Date date = new Date(1722911510316L);
        String dateFormat = simpleDateFormat.format(date);
        boolean equals = nowFormat.equals(dateFormat);

        System.err.println(equals);
    }

}
