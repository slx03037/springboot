package com.tools.desgin.spring.test01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author shenlx
 * @description
 * @date 2024/8/16 10:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-abstract-factory.xml"})
public class SpringTest {
    @Resource
    private BeanFactory beanFactory;

    @Test
    public void test01(){
        CarBean carBean = (CarBean)beanFactory.getBean("carBean1");
        System.out.println("获取carBean："+carBean);
    }

    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-abstract-factory.xml");
        CarBean carBean = (CarBean) context.getBean("carBean2");
        System.out.println("获取carBean："+carBean);
    }


}
