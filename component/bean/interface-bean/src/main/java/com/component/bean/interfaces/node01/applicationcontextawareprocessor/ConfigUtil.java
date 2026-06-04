package com.component.bean.interfaces.node01.applicationcontextawareprocessor;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author shenlx
 * @description
 * @date 2025/3/5 15:32
 */
@Component
public class ConfigUtil implements EmbeddedValueResolverAware {
    private StringValueResolver resolver;



    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * 获取属性，直接传入属性名称即可
     * @param key
     * @return
     */
    public String getPropertiesValue(String key) {
        return resolver.resolveStringValue("${" + key + "}");
    }
}
