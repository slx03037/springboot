package com.tools.cache.caffeine;

import com.github.benmanes.caffeine.cache.*;
import com.tools.cache.caffeine.service.CaffeineN2Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author shenlx
 * @description
 * @date 2025/1/17 15:10
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {

    /**
     * 缓存大小淘汰
     * @throws InterruptedException
     */
    @Test
    public void maximumSizeTest() throws InterruptedException {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //超过10个后会使用W-TinyFlu算法进行淘汰
                .maximumSize(10)
                .evictionListener((key, val, removalCause) -> {
                    log.info("缓存淘汰:key:{},val：{}", key, val);
                })
                .build();
        for(int i=1;i<=20;i++){
            cache.put(i,i);
        }

        //缓存淘汰是异步的
        Thread.sleep(500);

        //打印还没有淘汰的缓存
        System.out.println(cache.asMap());
    }

    /**
     * 权重淘汰
     * @throws InterruptedException
     */
    @Test
    public void maximumWeightTest() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                //限制总权重，若所有缓存的权重加起来 > 总权重就会淘汰权重小的缓存 （权重的排序从小到大）
                .maximumWeight(100)
                .weigher((Weigher<Integer, Integer>) (key, val) -> key)
                .evictionListener((key, val, removalCause) -> {
                    log.info("缓存淘汰: key:{} val:{}", key, val);
                })
                .build();

        //总权重其实是=所有缓存的权重加起来
        int maximumWeight=0;

        for (int i=1;i<=20 ;i++){
            cache.put(i,i);
            maximumWeight+=i;
        }

        System.out.println("总权重="+maximumWeight);

        //缓存淘汰是异步的
        Thread.sleep(1000);

        //打印还没有被淘汰的缓存
        System.out.println(cache.asMap());
    }

    /**
    * 访问后到期（每次访问都会重置时间，也就是说如果一直被访问就不会被淘汰）
     */
    @Test
    public void expireAfterAccessTest() throws InterruptedException {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //可以指定调度程度来及时删除过期缓存页，而不是等待Caffeine触发定期维护
                //若不设置scheduler,则缓存会在下一次调用get的时候才会被动删除
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .scheduler(Scheduler.systemScheduler())
                .evictionListener((key, val, removalCause) -> {
                    log.info("缓存淘汰: key:{} val:{}", key, val);
                }).build();
        cache.put(1,2);
        System.out.println(cache.getIfPresent(1));

        Thread.sleep(2000);
        System.out.println(cache.getIfPresent(1));
    }


    /**
     * 写入后到期
     * @throws InterruptedException
     */
    @Test
    public void expireAfterWriteTest() throws InterruptedException {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //可以指定调度程序来及时删除过期缓存项，而不是等待Caffeine触发定期维护
                //若不设置scheduler，则缓存会在下一次调用get的时候才会被动删除
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .scheduler(Scheduler.systemScheduler())
                .evictionListener((key, val, removalCause) -> {
                    log.info("淘汰缓存，key:{} val:{}", key, val);
                }).build();
        cache.put(1,2);
        Thread.sleep(3000);
        System.out.println(cache.getIfPresent(1));
    }


    /**
     * 刷新机制
     */
    private static int NUM=0;

    @Test
    public void refreshAfterWriteTest() throws InterruptedException {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                //模拟获取数据，每次获取就自增1
                .build(Integer -> ++NUM);

        //获取ID=1的值，由于缓存里还没有，所以会自动放入缓存
        System.out.println(cache.get(1));

        //延迟2秒后，理论上自动刷新缓存后取到的值是2
        //但其实不是，值还是1，因为refreshAfterWrite并不是设置n秒后重新获取就会自动刷新
        //而是x秒后&&第二次调用getIfPresent的时候才会被动刷新
        Thread.sleep(2000);
        System.out.println(cache.getIfPresent(1));

        //此时才会被动刷新缓存，而第一次拿到的还是旧值
        System.out.println(cache.getIfPresent(1));
    }

    /**
     * 统计
     */
    @Test
    public void  count(){
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //开启记录缓存命中率等信息
                .recordStats()
                //根据key查询数据库里面的值
                .build(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });
        cache.put("1","shen");
        System.out.println(cache.get("1"));
        System.out.println(cache.stats());
        /**
         * hitCount:命中的次数
         * missCount:未命中次数
         * requestCount:请求次数
         * hitRate:命中率
         * missRate:丢失率
         * loadSuccessCount:成功加载新值的次数
         * loadExceptionCount:失败加载新值的次数
         * totalLoadCount:总条数
         * loadExceptionRate:失败加载新值的比率
         * totalLoadTime:全部加载时间
         * evictionCount:丢失的条数
         */
    }

    /**
     * 总结
     * 1.设置maxSize、refreshAfterWrite,不设置expireAfterWrite/expireAfterAccess。设置expireAfterWrite当缓存过期时间会同步加锁获取缓存
     * ，所以设置expireAfterWrite时性能较好，但是某些时候会取旧数据，适合允许取到旧值数据的场景
     *
     * 2.设置maxSize、expireAfterWrite/expireAfterAccess，不设置refreshAfterWrite数据一致性比较好，不会获取旧数据，但是性能没有那么好，适合获取数据不耗时的场景
     */

    @Autowired
    CaffeineN2Service caffeineN2;

    @Test
    public void test(){
        caffeineN2.addUserEntity();
        caffeineN2.getUserByUserId(1);
        caffeineN2.getUserByUserId(1);
    }


}
