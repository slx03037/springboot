package com.component.bean.interfaces.node01.applicationcontextawareprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author shenlx
 * @description
 * @date 2025/3/5 15:39
 */
@Slf4j
@Configuration
public class ExtendResourceLoaderAware implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        log.info("ApplicationContextAware--Extend--run {}",resourceLoader);
    }


    public void showResourceData() throws IOException
    {
        //This line will be changed for all versions of other examples
        Resource banner = resourceLoader.getResource("file:d:/token.json");

        InputStream in = banner.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
        reader.close();
    }
}

