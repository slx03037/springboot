官网:https://github.com/ben-manes/caffeine

Caffeine是基于Java 1.8的高性能本地缓存库，由Guava改进而来，而且在Spring5开始的默认缓存实现就将Caffeine代替原来的Google Guava，官方说明指出，其缓存命中率已经接近最优值。实际上Caffeine这样的本地缓存和ConcurrentMap很像，即支持并发，并且支持O(1)时间复杂度的数据存取。二者的主要区
别在于：
ConcurrentMap将存储所有存入的数据，直到你显式将其移除；
Caffeine将通过给定的配置，自动移除“不常用”的数据，以保持内存的合理占用。

1、缓存加载策略
    1.1 Cache手动创建
最普通的一种缓存，无需指定加载方式，需要手动调用put()进行加载。需要注意的是put()方法对于已存在的key将进行覆盖，这点和Map的表现是一致的。在获取缓存值时，如果想要在缓存值不存在时，原子地将值写入缓存，则可以调用get(key, k -> value)方法，该方法将避免写入竞争。调用invalidate()方法，将手动移除缓存。
在多线程情况下，当使用get(key, k -> value)时，如果有另一个线程同时调用本方法进行 竞争，则后一线程会被阻塞，直到前一线程更新缓存完成；而若另一线程调用getIfPresent()方法，则会立即 返回null，不会被阻塞。
    1.2 Loading Cache自动创建
LoadingCache是一种自动加载的缓存。其和普通缓存不同的地方在于，当缓存不存在/缓存已过期时，若调用get()方法，则会自动调用CacheLoader.load()方法加载最新值。调用getAll()方法将遍历所有的key调用get()，除非实现了CacheLoader.loadAll()方法。使用LoadingCache时，需要指定CacheLoader，并实现其中的load()方法供缓存缺失时自动加载。在多线程情况下，当两个线程同时调用get()，则后一线程将被阻塞，直至前一线程更新缓存完成。
    1.3 Async Cache异步获取
AsyncCache是Cache的一个变体，其响应结果均为CompletableFuture，通过这种方式，AsyncCache对异步编程模式进行了适配。默认情况下，缓存计算使用ForkJoinPool.commonPool()作为线程池，如果想要指定线程池，则可以覆盖并实现Caffeine.executor(Executor)方法。synchronous()提供了阻塞直到异步缓存生成完毕的能力，它将以Cache进行返回。在多线程情况下，当两个线程同时调用get(key, k -> value)，则会返回同一个CompletableFuture对象。由于返回结果本身不进行阻塞，可以根据业务设计自行选择阻塞等待或者非阻塞。

2、驱逐策略
驱逐策略在创建缓存的时候进行指定。常用的有基于容量的驱逐和基于时间的驱逐。
基于容量的驱逐需要指定缓存容量的最大值，当缓存容量达到最大时，Caffeine将使用LRU策略对缓存进行淘汰；基于时间的驱逐策略如字面意思，可以设置在最后访问/写入一个缓存经过指定时间后，自动进行淘汰。
驱逐策略可以组合使用，任意驱逐策略生效后，该缓存条目即被驱逐。
    1.LRU 最近最少使用，淘汰最长时间没有被使用的页面。
    2.LFU 最不经常使用，淘汰一段时间内使用次数最少的页面
    3.FIFO 先进先出
Caffeine有4种缓存淘汰设置
    1.大小 （LFU算法进行淘汰）
    2.权重 （大小与权重 只能二选一）
    3.时间
    4.引用 

3、刷新机制
refreshAfterWrite()表示x秒后自动刷新缓存得策略可以配合淘汰策略使用，注意的是刷新机制只支持LoadingCache和AsyncLoadingCache


SpringBoot整合Caffeine
1.@Cacheable相关注解
    1.1 相关依赖
    如果要使用@Cacheable注解，需要引入相关依赖，并在任一配置类文件上添加@EnableCaching注解
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
    1.2 常用注解
        @Cacheable：表示该方法支持缓存。当调用被注解的方法时，如果对应的键已经存在缓存，则不再执行方法体，而从缓存中直接返回。当方法返回null时，将不进行缓存操作。
        @CachePut：表示执行该方法后，其值将作为最新结果更新到缓存中，每次都会执行该方法。
        @CacheEvict：表示执行该方法后，将触发缓存清除操作。
        @Caching：用于组合前三个注解，例如：
            @Caching(cacheable =@Cacheable("CacheConstants.GET_USER"), evict = {@CacheEvict("CacheConstants.GET_DYNAMIC",allEntries =true)}
            public User find(Integer id){
                return null;
            }
    1.3 常用注解属性
        cacheNames/value：缓存组件的名字，即cacheManager中缓存的名称。
        key：缓存数据时使用的key。默认使用方法参数值，也可以使用SpEL表达式进行编写。
        keyGenerator：和key二选一使用。
        cacheManager：指定使用的缓存管理器。
        condition：在方法执行开始前检查，在符合condition的情况下，进行缓存
        unless：在方法执行完成后检查，在符合unless的情况下，不进行缓存
        sync：是否使用同步模式。若使用同步模式，在多个线程同时对一个key进行load时，其他线程将被阻塞。
    1.4 缓存同步模式
        sync开启或关闭，在Cache和LoadingCache中的表现是不一致的：
        Cache中，sync表示是否需要所有线程同步等待
        LoadingCache中，sync表示在读取不存在/已驱逐的key时，是否执行被注解方法