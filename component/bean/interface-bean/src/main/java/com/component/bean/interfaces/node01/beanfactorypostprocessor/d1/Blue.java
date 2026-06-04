package com.component.bean.interfaces.node01.beanfactorypostprocessor.d1;

import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/2/24 14:40
 */
@Component
public class Blue extends Tint{
    @Override
    public String toString() {
        return "Blue{" + "label='" + label + '\'' + "}";
    }
}
