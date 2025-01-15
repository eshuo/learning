package com.wyci.utils.uuid;

import com.wyci.utils.date.DateUtil;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description ID简单生成器
 * @Author wangshuo
 * @Date 2022-10-17 16:04
 * @Version V1.0
 */
public class IdGenerator implements Serializable {

    private static final Map<String, IDEntity> CLASS_NAME_MAP = new ConcurrentHashMap<>();


    /**
     * 根据ID类名生成
     *
     * @return
     */
    public static String nextId() {
        return nextId(IdGenerator.class);
    }

    /**
     * 根据类名生成
     *
     * @param c
     * @return
     */
    public static synchronized String nextId(Class c) {
        return nextId(c, null, null);
    }

    /**
     * 指定前缀
     *
     * @param prefix
     * @return
     */
    public static synchronized String nextId(String prefix) {
        return nextId(IdGenerator.class, prefix, null);
    }

    /**
     * 指定前缀与减去时间
     *
     * @param prefix  前缀
     * @param subDate 时间 yyyy yyyyMM yyyyMMdd yyyyMMddHH yyyyMMddHHmm  yyyyMMddHHmmss
     * @return
     */
    public static synchronized String nextId(String prefix, String subDate) {
        return nextId(IdGenerator.class, prefix, subDate);
    }

    /**
     * 当前项目 时间（yyyyMMddHHmmss）(14)
     *
     * @param c 传入的对象
     * @return 当前流水id
     */
    public static synchronized String nextId(Class c, String prefix, String subDate) {
        String simpleName = prefix;
        if (StringUtils.isBlank(prefix)) {
            simpleName = c.getSimpleName().toUpperCase();
            if (simpleName.length() > 14) {
                simpleName = simpleName.replaceAll("\\.", "").substring(0, 14);
            }
        }

//        Thread.currentThread().getId()
        String dateStr = getDateStr(subDate);
        int andIncrement = 1;
        if (CLASS_NAME_MAP.containsKey(simpleName)) {
            final IDEntity idEntity = CLASS_NAME_MAP.get(simpleName);
            synchronized (simpleName.intern()) {
                boolean isEq = !idEntity.getNowDate().equals(dateStr);
                if (isEq) {
                    idEntity.getAtomicInteger().set(1);
                    idEntity.setNowDate(dateStr);
                } else {
                    andIncrement = idEntity.getAtomicInteger().incrementAndGet();
                }
            }
        } else {
            synchronized (simpleName.intern()) {
                CLASS_NAME_MAP.put(simpleName, new IDEntity(dateStr, new AtomicInteger(andIncrement)));
            }
        }
        simpleName = simpleName.concat(dateStr).concat(String.format("%04d", andIncrement));
        return simpleName;
    }

    private static String getDateStr(String subDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA);
        String dateStr = formatter.format(LocalDateTime.now());
        if (StringUtils.isNotBlank(subDate) && StringUtils.isNumericSpace(subDate)) {
            if (subDate.length() < 14) {
                subDate = String.format("%-14d", Long.parseLong(subDate)).replace(" ", "0");
            }
            dateStr = String.format("%014d", Long.parseLong(dateStr) - Long.parseLong(subDate));
        }
        dateStr += Math.abs(Arrays.hashCode(Thread.currentThread().getStackTrace()) % 9);
        return dateStr;
    }


    /**
     * 身份
     *
     * @author wangshuo
     * @date 2022/10/17
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class IDEntity {

        private String nowDate;

        private AtomicInteger atomicInteger;

    }


    public static void main(String[] args) {

        //减去时间
        System.out.println(IdGenerator.nextId("E", "2025"));
        //指定前缀
        System.out.println(IdGenerator.nextId("E"));

        System.out.println(IdGenerator.nextId(IdGenerator.class));

        System.out.println(IdGenerator.nextId());
        long startTime = System.currentTimeMillis();
        //thread
//        testNextIdMethod();

        System.out.println("zhangss".endsWith("ss"));


//        final int curTime = DateUtil.curTime();
//
//        List<String> list = new ArrayList<>();
//
//        for (; ; ) {
//            final String nextId = IdGenerator.nextId();
//            if (list.contains(nextId)) {
//                System.err.println(nextId);
//            } else {
//                list.add(nextId);
//            }
//            if (DateUtil.curTime() - curTime >= 130) {
//                break;
//            }
//        }
//
//        System.out.println("size => " + list.size());

    }


    static void testNextIdMethod() {
//        Thread thread1 = new Thread(new IdTestThread());
//        Thread thread2 = new Thread(new IdTestThread());
//        thread1.start();
//        thread2.start();

        for (int i = 0; i < 1; i++) {
            Thread thread1 = new Thread(new IdTestThread());
            thread1.start();
        }

    }

    static class IdTestThread implements Runnable {

        private static final List<String> strList = new ArrayList<>();

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            int time = DateUtil.curTime();
            for (; ; ) {
                final String nextId = IdGenerator.nextId(("E"));
                synchronized (strList) {
//                    System.out.println(nextId + ":" + strList.contains(nextId));
                    if (strList.contains(nextId)) {
                        System.err.println(nextId + ":" + strList.contains(nextId));
                    } else {
                        strList.add(nextId);
                    }
                }

//                if (DateUtil.curTime() - time >= 1) {
//                    break;
//                }
                if (System.currentTimeMillis() - startTime >= 1000) {
                    break;
                }

            }

//            for (int i = 0; i < 10000; i++) {
//                final String nextId = IdGenerator.nextId(("E"));
//                synchronized (strList) {
////                    System.out.println(nextId + ":" + strList.contains(nextId));
//                    if (strList.contains(nextId)) {
//                        System.err.println(nextId + ":" + strList.contains(nextId));
//                    } else {
//                        strList.add(nextId);
//                    }
//                }
//            }
            //thread
            System.out.println(Thread.currentThread().getName() + "==========Time:" + (System.currentTimeMillis() - startTime));
            System.out.println("size => " + strList.size());
        }
    }


}
