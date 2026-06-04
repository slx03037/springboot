package com.component.bean.interfaces.node01.instantiationawarebeanpostprocessor.d1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author shenlx
 * @description
 * @date 2025/2/26 14:39
 */
@Service
@Slf4j
public class ExampleService {
    public ExampleService() {
        log.info("----ExampleService无参构造方法被执行");
    }
    public void test(){
        System.out.println("test");
    }
}
