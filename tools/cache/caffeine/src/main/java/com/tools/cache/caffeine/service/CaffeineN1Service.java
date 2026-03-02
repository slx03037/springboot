package com.tools.cache.caffeine.service;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author shenlx
 * @description
 * @date 2025/1/17 13:50
 */
public class CaffeineN1Service {
    public static void test01Method(){
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //初始数量
                .initialCapacity(10)
                //最大条数
                .maximumSize(10)
                //expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准
                //最后一次写操作后经过指定时间过期
                .expireAfterWrite(1, TimeUnit.SECONDS)
                //最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(1, TimeUnit.SECONDS)
                //监听缓存被移除
                .removalListener((key, val, removalCause) -> {
                })
                //记录命中
                .recordStats()
                .build();
        cache.put("1","张三");

        System.out.println(cache.getIfPresent("1"));

        System.out.println(cache.getIfPresent("2"));

        //存储的是默认值
        System.out.println(cache.get("2",o->"默认值"));

        System.out.println(cache.getIfPresent("2"));
    }

    public  static void main(String[]args){
        test01Method();
    }

    public static void test02Method(){
        LoadingCache<Object, String> cache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔,刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(20)
                //根据key查询数据库里的值，这里是个lamba表达式
                .build(key -> new Date().toString());
    }

    public static void test03Method(){
        AsyncLoadingCache<Object, String> cacheAsync = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存,仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据key查询数据库里的值
                .buildAsync(key -> {
                    Thread.sleep(2000);
                    return new Date().toString();
                });
        //异步缓存返回的值是CompletableFuture
        CompletableFuture<String> future = cacheAsync.get("1");
        future.thenAccept(System.out::println);
    }


}
