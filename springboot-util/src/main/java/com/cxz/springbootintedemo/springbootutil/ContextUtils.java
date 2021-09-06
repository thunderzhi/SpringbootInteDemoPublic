package com.cxz.springbootintedemo.springbootutil;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/9/1 16:05
 */
@Component
public class ContextUtils implements ApplicationContextAware {


    public static ApplicationContext context;

    private ContextUtils() {
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(ContextUtils.context == null) {
            ContextUtils.context = applicationContext;
        }
    }

//    public static void setApplicationContext(ApplicationContext applicationContext) {
//        context = applicationContext;
//    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }

    /**
     * 获取当前的环境变量名称，没有返回空(test/stage/product)
     *
     * @return
     */
    public static String getEnv() {
        String env = "";
        if (context != null && context.getParent().getEnvironment().getActiveProfiles().length > 0) {
            env = context.getParent().getEnvironment().getActiveProfiles()[0];
        }
        return env;
    }


}
