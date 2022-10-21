package com.example.qlexpressdemo.operator;

import com.eetrust.res.manage.utils.QlRunnerUtils;
import com.ql.util.express.Operator;
import org.apache.commons.collections.ListUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 是否有交集操作
 * @Author wangshuo
 * @Date 2022-09-09 17:11
 * @Version V1.0
 */
public class IntersectionOperator extends Operator {
    @Override
    public Object executeInner(Object[] list) throws Exception {
        return executeInner(list[0], list[1]);
    }

    public Object executeInner(Object op1, Object op2) throws Exception {
        //交集操作
        if (!(op1.getClass().isArray() || op1 instanceof List) || !(op2.getClass().isArray() || op2 instanceof List)) {
            return false;
        }
        try {
            List<Object> objects = convertArray(op1);
            List<Object> objects2 = convertArray(op2);

            if (CollectionUtils.isEmpty(objects) || CollectionUtils.isEmpty(objects2)) {
                return false;
            }
            final List intersection = ListUtils.intersection(objects, objects2);
            return intersection.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private List<Object> convertArray(Object o) {
        List<Object> list = new ArrayList<>();
        if (o.getClass().isArray()) {
            final int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                list.add(i, Array.get(o, i));
            }
        } else if (o instanceof List) {
            return (List<Object>) o;
        }
        return list;
    }


    public static void main(String[] args) {
        String str = "obj1 intersection [\"a\",\"b\"]";

        final HashMap<String, Object> objectHashMap = new HashMap<>();

        objectHashMap.put("obj1", Arrays.asList("a", "b"));
//        objectHashMap.put("obj2", Arrays.asList("c", "d", "a"));

//        String[] s = new String[]{"a","b","c"};
//        String[] s1 = new String[]{"d","e","f"};
//        objectHashMap.put("obj1",s);
//        objectHashMap.put("obj2",s1);
        final Object o = QlRunnerUtils.cacheExecute(str, objectHashMap);

        System.out.println(o);
    }


}
