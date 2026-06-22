package com.tools.jobtime.elasticjob.web.job;

import com.tools.jobtime.elasticjob.web.model.Foo;
import com.tools.jobtime.elasticjob.web.repository.FooRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/22 11:53
 */
@Component
@Slf4j
public class SpringBootSimpleJob implements SimpleJob {


    @Autowired
    private FooRepository fooRepository;

    @Override
    public void execute(final ShardingContext shardingContext) {
        log.info("Item: {} | Time: {} | Thread: {} | {}",
                shardingContext.getShardingItem(), LocalDateTime.now(), Thread.currentThread().getId(), "SIMPLE");
        List<Foo> data = fooRepository.findTodoData(shardingContext.getShardingParameter(), 10);
        for (Foo each : data) {
            fooRepository.setCompleted(each.getId());
        }
    }
}
