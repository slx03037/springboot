package com.component.bean.interfaces.node01.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @author shenlx
 * @description
 * @date 2025/3/10 17:21
 */

public class BeanDefinitionAndDefaultListableBeanFactory {

    public void test(){
        // 1)创建loC配置文件的抽象资源，这个抽象资源包含了BeanDefinition的定义信息。
        ClassPathResource res=new ClassPathResource("beans.xml");
        //2)创建一个BeanFactory，这里使用DefaultListableBeanFactory。
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        //  3)创建一个载入BeanDefinition的读取器，这里使用XmlBeanDefinitionReader来载入XML文件形式的BeanDefinition，通过一个回调配置给BeanFactory。
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        //4)从定义好的资源位置读入配置信息，具体的解析过程由XmlBeanDefinitionReader来完成。完成整个载入和注册Bean定义之后，需要的IoC容器就建立起来了。这个时候就可以直接使用IoC容器了。
        reader.loadBeanDefinitions(res);
    }
}
