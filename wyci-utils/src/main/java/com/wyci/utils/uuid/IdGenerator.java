package com.wyci.utils.uuid;

import com.wyci.utils.date.DateStyle;
import com.wyci.utils.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description ID简单生成器
 * @Author wangshuo
 * @Date 2022-10-17 16:04
 * @Version V1.0
 */
public class IdGenerator implements Serializable {

    private static final Map<String, IDEntity> CLASS_NAME_MAP = new ConcurrentHashMap<>();


    public static String nextId() {
        return nextId(IdGenerator.class);
    }

    /**
     * 当前项目 时间（yyyyMMddHHmm）(12)
     *
     * @param c 传入的对象
     * @return 当前流水id
     */
    public static synchronized String nextId(Class c) {

        String simpleName = c.getSimpleName().toUpperCase();
        if (simpleName.length() > 14) {
            simpleName = simpleName.replaceAll("\\.", "").substring(0, 14);
        }

        final String dateStr = DateUtil.dateToString(new Date(), DateStyle.YYYYMMDDHHMMSS);

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
        simpleName = simpleName.concat(dateStr).concat(String.format("%06d", andIncrement));
        return simpleName;
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


        System.out.println(IdGenerator.nextId());

        final int curTime = DateUtil.curTime();

        List<String> list = new ArrayList<>();

        for (; ; ) {
            final String nextId = IdGenerator.nextId();
            if (list.contains(nextId)) {
                System.err.println(nextId);
            } else {
                list.add(nextId);
            }
            if (DateUtil.curTime() - curTime >= 130) {
                break;
            }
        }

        System.out.println("size => " + list.size());


    }

}
