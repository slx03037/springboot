package com.tools.desgin.spring.test02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shenlx
 * @description
 * @date 2024/8/19 13:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-property.xml"})
public class SpringTest {
    @Test
    public void test02(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-property.xml");
        Sheep sheep01 = (Sheep) context.getBean("sheep01");
        Sheep sheep02 = (Sheep) context.getBean("sheep01");
        System.out.println(sheep01==sheep02);
        Sheep sheep03 = (Sheep) context.getBean("sheep02");
        Sheep sheep04 = (Sheep) context.getBean("sheep02");
        System.out.println(sheep03==sheep04);
    }

}
