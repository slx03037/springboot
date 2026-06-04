package com.component.bean.interfaces;


import com.component.bean.interfaces.node01.applicationcontextawareprocessor.ConfigUtil;
import com.component.bean.interfaces.node01.beanpostprocessor.node01.Dog;
import com.component.bean.interfaces.node01.instantiationawarebeanpostprocessor.d1.ExampleController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

/**
 * @author shenlx
 * @description
 * @date 2025/2/26 14:40
 */
@Slf4j
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private ConfigUtil configUtil;

    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.xinwen.start");

        ExampleController bean = context.getBean(ExampleController.class);
        //AbstractApplicationContext
        Assert.isTrue("fanfu".equals(bean.getCreator()), "属性替换失败");
        log.info("----" + bean.getCreator());
    }

    @Test
    public void test2(){
        /**
         * 验证了Spring的扩展点BeanPostProcessor的执行时机，即postProcessBeforeInitialization方法的执行时机
         * 是在Spring管理的Bean实例化、属性注入完成后，InitializingBean#afterPropertiesSet方法以及自定义的初
         * 始化方法之前；postProcessAfterInitialization方法的执行时机是在InitializingBean#afterPropertiesSet
         * 方法以及自定义的初始化方法之前之后；
         */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.xinwen.start");
        //Dog dog = context.getBean(Dog.class);

        Dog dog = (Dog) context.getBean("dog");
        log.info(dog.getName());
    }

    @Test
    public void test3(){
        String s = configUtil.getPropertiesValue("spring.application.name");
        log.info(s);
    }
}
