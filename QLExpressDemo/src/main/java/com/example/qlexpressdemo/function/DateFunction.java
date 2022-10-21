package com.example.qlexpressdemo.function;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * @Description 时间Function
 * @Author wangshuo
 * @Date 2022-09-22 17:58
 * @Version V1.0
 */
public class DateFunction {


    /**
     * 是周末
     *
     * @return boolean
     */
    public static boolean isWeekend() {
        DayOfWeek day = getDayOfWeek();
        switch (day) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

    private static DayOfWeek getDayOfWeek() {
        DayOfWeek day = DayOfWeek.of(LocalDate.now().get(ChronoField.DAY_OF_WEEK));
        return day;
    }

    /**
     * 一周 哪一天
     *
     * @param d 1-7 周几
     * @return boolean
     */
    public static boolean whichDay(int d) {
        DayOfWeek day = getDayOfWeek();

        if (d == day.getValue()) {
            return true;
        }
        return false;
    }


}
