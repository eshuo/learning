package com.example.qlexpressdemo.utils;

import com.example.qlexpressdemo.function.DateFunction;
import com.example.qlexpressdemo.operator.IntersectionOperator;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-04 10:36
 * @Version V1.0
 */
public class QlRunnerUtils {

    private final static Logger logger = LoggerFactory.getLogger(QlRunnerUtils.class);


    private static ExpressRunner runner;

    private static Unsafe unsafe;


    public static ExpressRunner init() {
        if (null == runner) {
            runner = new ExpressRunner();
            try {
                BasicKeyword(runner);
            } catch (Exception e) {
                logger.error("QlRunnerUtils error ", e);
            }
        }
        return runner;
    }


    public static Unsafe getUnsafe() {
        if (null == unsafe) {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                unsafe = (Unsafe) f.get(null);
            } catch (Exception e) {
                logger.error("getUnsafe error", e);
                unsafe = Unsafe.getUnsafe();
            }
        }
        return unsafe;
    }


    /**
     * 配置实例
     *
     * @param type 类型
     * @return {@code Object}
     */
    public static Object allocateInstance(Class<?> type) {
        try {
            if (Collection.class.isAssignableFrom(type)) {
                return QlRunnerUtils.getUnsafe().allocateInstance(ArrayList.class);
            } else {
                return QlRunnerUtils.getUnsafe().allocateInstance(type);
            }
        } catch (Exception e) {
            logger.error("allocateInstance error ", e);
        }
        return null;
    }


    public static <T> Map<String, Object> declaredFieldsToMap(T t) {
        Map<String, Object> map = new HashMap<>(3);
        if (null == t) {
            return map;
        }
        final Field[] declaredFields = t.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                if (ReflectUtil.isNullOrEmpty(declaredField.get(t))) {
                    final Class<?> type = declaredField.getType();
                    if (Collection.class.isAssignableFrom(type)) {
                        map.put(declaredField.getName(), QlRunnerUtils.allocateInstance(ArrayList.class));
                    } else if (CharSequence.class.isAssignableFrom(type)) {
                        map.put(declaredField.getName(), "");
                    } else {
                        map.put(declaredField.getName(), QlRunnerUtils.allocateInstance(type));
                    }
                } else {
                    map.put(declaredField.getName(), declaredField.get(t));
                }
            } catch (IllegalAccessException e) {
                logger.error("declaredFieldsToMap error class:{} ,name:{}", t.getClass().getName(), declaredField.getName(), e);
//                    throw new RuntimeException(e);
            }
        }
        return map;
    }


    /**
     * init宣布字段
     *
     * @param t t
     */
    public static <T> void initDeclaredFields(T t) {
        if (null == t) {
            return;
        }

        final Field[] declaredFields = t.getClass().getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                if (ReflectUtil.isNullOrEmpty(declaredField.get(t))) {
                    final Class<?> type = declaredField.getType();
                    if (Collection.class.isAssignableFrom(type)) {
                        declaredField.set(t, QlRunnerUtils.allocateInstance(ArrayList.class));
                    } else {
                        declaredField.set(t, QlRunnerUtils.allocateInstance(type));
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error("initDeclaredFields error class:{} ,name:{}", t.getClass().getName(), declaredField.getName(), e);
//                    throw new RuntimeException(e);
            }
        }


    }


    /**
     * 添加基础关键字
     *
     * @param runner
     */
    public static void BasicKeyword(ExpressRunner runner) throws Exception {
        runner.addOperatorWithAlias("如果", "if", null);
        runner.addOperatorWithAlias("则", "then", null);
        runner.addOperatorWithAlias("否则", "else", null);
        runner.addOperatorWithAlias("并且", "&&", null);
        runner.addOperatorWithAlias("或者", "||", null);
        runner.addOperatorWithAlias("大于", ">", null);
        runner.addOperatorWithAlias("大于等于", ">=", null);
        runner.addOperatorWithAlias("小于", "<", null);
        runner.addOperatorWithAlias("小于等于", "<=", null);
        runner.addOperatorWithAlias("等于", "==", null);
        runner.addOperatorWithAlias("不等于", "!=", null);
        runner.addOperatorWithAlias("返回", "return", null);
        runner.addOperator("intersection", new IntersectionOperator());
//        runner.addOperator("isWeekend", new WeekendOperator());

        runner.addFunctionOfClassMethod("isWeekend", DateFunction.class, "isWeekend", new Class[]{}, null);

        runner.addFunctionOfClassMethod("whichDay", DateFunction.class.getName(), "whichDay", new String[]{"int"}, null);
    }

    /**
     * 缓存执行表达式
     *
     * @param expressString
     * @param context
     * @return
     */
    public static Object cacheExecute(String expressString, Map<String, Object> context) {
        if (null != context) {
            DefaultContext<String, Object> defaultContext = new DefaultContext<>();
            defaultContext.putAll(context);
            return execute(true, expressString, defaultContext);
        } else {
            return execute(true, expressString, null);
        }
    }

    /**
     * 缓存执行表达式
     *
     * @param expressString 表达式
     * @param context       上下文
     * @return
     */
    public static Object cacheExecute(String expressString, IExpressContext<String, Object> context) {
        return execute(true, expressString, context);
    }


    /**
     * 执行表达式
     *
     * @param isCache       是否缓存
     * @param expressString 表达式
     * @param context       上下文
     * @return
     */
    public static Object execute(boolean isCache, String expressString, IExpressContext<String, Object> context) {
        try {
            return init().execute(expressString, context, null, isCache, true);
        } catch (Exception e) {
            logger.error("execute error:", e);
            throw new RuntimeException(expressString);
        }
    }


    /**
     * 多个表达式转换统一结果
     *
     * @param express 表达式
     * @return List<?>   字符串
     */
    public static String ExpressToFunction(String... express) {
        if (null == express) {
            return null;
        }
        StringBuilder str = new StringBuilder("dataList = new ArrayList();");
        for (int i = 0; i < express.length; i++) {
            String functionName = "exp_to_fun_" + i + "()";
            str.append("function ").append(functionName).append("{").append(express[i]).append("}");
            str.append(" dataList.add(").append(functionName).append(");");
        }
        str.append("return dataList;");
        return str.toString();
    }

}
