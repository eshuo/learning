package com.example.qlexpressdemo.context;

import com.ql.util.express.IExpressContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-04 09:22
 * @Version V1.0
 */
@Service
public class QlExpressBeanRunner implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public IExpressContext getBeanContext(Map<String, Object> context) {
        return new QLExpressContext(context, this.applicationContext);
    }

    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }


    public <T> T getBean(java.lang.Class<T> aClass) {
        return applicationContext.getBean(aClass);
    }


}
