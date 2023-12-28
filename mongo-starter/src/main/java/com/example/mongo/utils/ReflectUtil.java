package com.example.mongo.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;
import sun.misc.Unsafe;

import java.beans.FeatureDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-27 15:49
 * @Version V1.0
 */
public class ReflectUtil {


    private static Unsafe unsafe;

    /**
     * 类型初始化工具
     *
     * @return
     */
    private static Unsafe getUnsafe() {
        if (null == unsafe) {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            } catch (Exception e) {
                unsafe = Unsafe.getUnsafe();
            }
        }
        return unsafe;
    }

    /**
     * @param codeList   转换的字段
     * @param contentMap 填充集合
     * @param t          转换类型
     * @param <T>
     */
    private <T> void convertContentMap(List<String> codeList, Map<String, Object> contentMap, T t) {
        final Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            final String name = declaredField.getName();

            if (CollectionUtils.isEmpty(codeList) || codeList.contains(name)) {
                try {
                    final Object value = declaredField.get(t);
                    if (!ReflectUtil.isNullOrEmpty(value)) {
                        contentMap.put(name, value);
                    } else {
                        final Class<?> type = declaredField.getType();
                        //数据类型赋值初始化
                        if (Collection.class.isAssignableFrom(type)) {
                            contentMap.put(name, getUnsafe().allocateInstance(ArrayList.class));
                        } else {
                            contentMap.put(name, getUnsafe().allocateInstance(type));
                        }
                    }
                } catch (IllegalAccessException | InstantiationException e) {
//                    throw new RuntimeException(e);
                }
            }


        }
    }


    /**
     * 给定数据为空初始化
     *
     * @param t
     * @param <T>
     */
    private <T> void initData(T t) {

        final Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            try {
                final Object value = declaredField.get(t);
                if (ReflectUtil.isNullOrEmpty(value)) {
                    final Class<?> type = declaredField.getType();

                    //数据类型赋值初始化
                    if (Collection.class.isAssignableFrom(type)) {
                        declaredField.set(t, getUnsafe().allocateInstance(ArrayList.class));
                    } else {
                        declaredField.set(t, getUnsafe().allocateInstance(type));
                    }


                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }


    /**
     * 判断对象空或者null
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object o : object) {
                if (!isNullOrEmpty(o)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }


    /**
     * 获取null字段
     *
     * @param source 传入的对象
     * @return null字段
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> null == wrappedSource.getPropertyValue(propertyName))
                .toArray(String[]::new);
    }

}
