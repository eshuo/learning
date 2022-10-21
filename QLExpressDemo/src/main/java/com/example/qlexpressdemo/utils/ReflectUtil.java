package com.example.qlexpressdemo.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-21 17:21
 * @Version V1.0
 */
public class ReflectUtil {
    public ReflectUtil() {
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof CharSequence) {
            return ((CharSequence)obj).length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map)obj).isEmpty();
        } else if (!(obj instanceof Object[])) {
            return false;
        } else {
            Object[] object = (Object[])((Object[])obj);
            if (object.length == 0) {
                return true;
            } else {
                boolean empty = true;
                Object[] var3 = object;
                int var4 = object.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    Object o = var3[var5];
                    if (!isNullOrEmpty(o)) {
                        empty = false;
                        break;
                    }
                }

                return empty;
            }
        }
    }
}
