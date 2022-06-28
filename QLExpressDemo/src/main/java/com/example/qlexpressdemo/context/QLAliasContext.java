package com.example.qlexpressdemo.context;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.annotation.QLAlias;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-28 15:37
 * @Version V1.0
 */
public class QLAliasContext extends DefaultContext {
    public void putAutoParams(Object... values) {
        for (Object value : values) {
            if (value.getClass().isAnnotationPresent(QLAlias.class)) {
                QLAlias[] annotations = value.getClass().getAnnotationsByType(QLAlias.class);
                for (int i = 0; i < annotations.length; i++) {
                    String[] name = annotations[i].value();
                    for (int j = 0; j < annotations.length; j++) {
                        super.put(name[j], value);
                    }
                }
            }
        }
    }
}
