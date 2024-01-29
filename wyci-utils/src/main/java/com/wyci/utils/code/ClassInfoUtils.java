package com.wyci.utils.code;

/**
 * @Description class info
 * @Author wangshuo
 * @Date 2024-01-29 09:27
 * @Version V1.0
 */
public class ClassInfoUtils {


    /**
     * 获取调用的类名
     *
     * @return String
     */
    public static String getClassName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        return e.getClassName();
    }

    /**
     * 获取调用的方法名
     *
     * @return String
     */
    public static String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        return e.getMethodName();
    }

    public static String getFileName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        return e.getFileName();
    }

    public static int getLineNumber() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        return e.getLineNumber();
    }

    public static void main(String[] args) {
        System.out.println("当前运行的类：" + getClassName());
        System.out.println("当前执行的方法：" + getMethodName());
        System.out.println("当前文件名：" + getFileName());
        System.out.println("当前执行的行数：" + getLineNumber());
    }
}
