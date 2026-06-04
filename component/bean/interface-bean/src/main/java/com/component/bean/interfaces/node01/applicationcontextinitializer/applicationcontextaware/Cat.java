package com.component.bean.interfaces.node01.applicationcontextinitializer.applicationcontextaware;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shenlx
 * @description
 * @date 2025/3/4 15:07
 */
@Getter
@Setter
@Slf4j
public class Cat {
    private String name = "旺财";
    private String color = "黑色";
    public void init2() {
        log.info("---initMethod2被执行");
    }
}
