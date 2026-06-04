package com.component.bean.interfaces.applicationcontext;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author shenlx
 * @description
 * @date 2025/3/11 11:16
 */
public class TestApplicationContext {

    /**
     * 在FileSystemXmlApplicationContext的设计中，我们看到ApplicationContext应用上下文的主要功能已经在FileSystemXmlApplicationContext的基类AbstractXmlApplicationContext中实现了，在FileSystemXmlApplicationContext中，作为一个具体的应用上下文，只需要实现和它自身设计相关的两个功能。
     * 一个功能是，如果应用直接使用FileSystemXmlApplicationContext，对于实例化这个应用上下文的支持，同时启动IoC容器的refresh()过程
     */
//    public  FileSystemXmlApplicationContext(
//            String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
//            throws BeansException {
//
//        super(parent);
//        setConfigLocations(configLocations);
//    这个refresh()过程会牵涉I0C容器启动的一系列复杂操作，同时，对于不同的容器实现，这些操作都是类似的，因此在基类中将它们封装好。所以，我们在FileSystemXml的设计中看到的只是一个简单的调用
//        if (refresh) {
//            refresh();
//        }
//    }

//    这部分与怎样从文件系统中加载XML的Bean定义资源有关。通过这个过程，可以为在文件系统中读取以XML形式存在的BeanDefinition做准备，因为不同的应用上下文实现对应着不同的读取BeanDefinition的方式
    protected Resource getResourceByPath(String path) {
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        return new FileSystemResource(path);
    }
}
