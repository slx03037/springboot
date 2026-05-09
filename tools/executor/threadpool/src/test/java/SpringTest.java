import com.executor.threadpool.custom.ThreadExecutorService;
import com.executor.threadpool.newCachedThreadPool.NewCachedThreadPoolService;
import com.executor.threadpool.newFixedThreadPool.FixedThreadPoolService;
import com.executor.threadpool.newScheduledThreadPool.NewScheduledThreadPoolService;
import com.executor.threadpool.newSingleThreadExecutor.NewSingleThreadExecutorService;
import com.executor.threadpool.newSingleThreadScheduledExecutor.NewSingleThreadScheduledExecutorService;
import com.executor.threadpool.newWorkStealingPool.NewWorkStealingPoolService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:20
 */
@SpringBootTest
public class SpringTest {

    @Test
    public void custom(){
        ThreadExecutorService threadPoolExecutorDemo=new ThreadExecutorService();
        threadPoolExecutorDemo.threadHandler();
    }

    @Test
    public void newCachedThreadPool(){
        NewCachedThreadPoolService.newCachedThreadPool();
    }

    @Test
    public void fixedThreadPool(){
        ExecutorService threadPool = FixedThreadPoolService.newFixedThreadPool(2);
        // 创建任务
        Runnable runnable = () -> System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
        // 线程池执行任务(一次添加 8 个任务)
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.shutdown();

        // 创建 2 个线程的线程池
        ExecutorService threadPools = Executors.newFixedThreadPool(2);
        threadPools.execute(runnable);
    }

    @Test
    public void newScheduledThreadPool(){
        NewScheduledThreadPoolService.first();
        NewScheduledThreadPoolService.second();
        NewScheduledThreadPoolService.third();
    }

    @Test
    public void newSingleThreadExecutor(){
        NewSingleThreadExecutorService.newSingleThreadExecutor();
    }

    @Test
    public void newSingleThreadScheduledExecutor(){
        NewSingleThreadScheduledExecutorService.newSingleThreadScheduledExecutor();
    }

    @Test
    public void newWorkStealingPool(){
        NewWorkStealingPoolService.newWorkStealingPool();
    }
}
