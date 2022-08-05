package com.example.mongo.utils;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.data.annotation.Id;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-27 15:49
 * @Version V1.0
 */
public class ReflectUtil {


    public static Object getTarget(Object dest, Map<String, Object> addProperties) {
        // get property map
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        Map<String, Class> propertyMap = new HashMap<>();
        for (PropertyDescriptor d : descriptors) {
            if (!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        // add extra properties
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        // new dynamic bean
        DynamicBean dynamicBean = new DynamicBean(dest.getClass(), propertyMap);
        // add old value
        propertyMap.forEach((k, v) -> {
            try {
                // filter extra properties
                if (!addProperties.containsKey(k)) {
                    dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(dest, k));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        // add extra value
        addProperties.forEach((k, v) -> {
            try {
                dynamicBean.setValue(k, v);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }


    public static HashMap<String, Object> getPropertiesMap(Object dest, Map<String, Object> addProperties) {
        HashMap<String, Object> map = new HashMap<>();


//        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
//        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
//        for (PropertyDescriptor d : descriptors) {
//            if (!"class".equalsIgnoreCase(d.getName())) {
//                try {
//                    map.put(d.getName(), propertyUtilsBean.getNestedProperty(dest, d.getName()));
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
        for (Field declaredField : dest.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
                final Object o = declaredField.get(dest);
                if (null != o) {
                    if (isNullOrEmpty(o)) {
                        continue;
                    }
                    final Id annotation = declaredField.getAnnotation(Id.class);
                    if (null != annotation) {
                        map.put("_id", o);
                        continue;
                    }
                    final org.springframework.data.mongodb.core.mapping.Field fieldAnnotation = declaredField.getAnnotation(org.springframework.data.mongodb.core.mapping.Field.class);
                    if (null != fieldAnnotation) {
                        map.put(fieldAnnotation.value(), o);
                        continue;
                    }
                    map.put(declaredField.getName(), o);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

        if (!CollectionUtils.isEmpty(addProperties)) {
            map.putAll(addProperties);
        }
        return map;
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

}
