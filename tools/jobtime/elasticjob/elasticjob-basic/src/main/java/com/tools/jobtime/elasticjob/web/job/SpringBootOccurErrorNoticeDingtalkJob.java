package com.tools.jobtime.elasticjob.web.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2026/6/22 11:55
 */
@Component
public class SpringBootOccurErrorNoticeDingtalkJob implements SimpleJob {

    @Override
    public void execute(final ShardingContext shardingContext) {
        throw new RuntimeException(String.format("An exception has occurred in Job, The parameter is %s", shardingContext.getShardingParameter()));
    }
}
