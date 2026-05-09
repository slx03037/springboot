package com.executor.completablefuture.service.async;

import com.executor.completablefuture.dao.DeptRepository;
import com.executor.completablefuture.dao.MenuRepository;
import com.executor.completablefuture.dao.UserRepository;
import com.executor.completablefuture.entity.Dept;
import com.executor.completablefuture.entity.Menu;
import com.executor.completablefuture.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:54
 */
@Service
@Slf4j
public class AsyncRunService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private DeptRepository deptRepository;


    /**
     *这里的get()与join()都是获取任务线程的返回值。join()方法抛出的是uncheck异常（即RuntimeException)，
     * 不会强制开发者抛出， 会将异常包装成CompletionException异常 /CancellationException异常，
     * 但是本质原因还是代码内存在的真正的异常
     *
     * get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）
     */

    // runAsync执行CompletableFuture任务，没有返回值
    public void defaultRunAsync() {

        long lordStartTime = System.currentTimeMillis();

        CompletableFuture<Void> userCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("新增用户耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        });

        CompletableFuture<Void> deptCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("新增部门耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        });
        CompletableFuture<Void> menuCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("新增菜单耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        });
        log.info("新增用户为：" + userCompletableFuture.join());
        log.info("新增部门结果为：" + deptCompletableFuture.join());
        log.info("新增菜单:{}" ,menuCompletableFuture.join());

        log.info("总耗时:" + (System.currentTimeMillis() - lordStartTime) / 1000 + "秒");
    }

    // runAsync执行CompletableFuture任务，没有返回值
    public void customRunAsync() {

        long lordStartTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<Void> userCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("新增用户耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        }, executor);

        CompletableFuture<Void> deptCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("新增部门耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        }, executor);

        CompletableFuture<Void> menuCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("新增菜单耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        }, executor);

        log.info("新增用户为：" + userCompletableFuture.join());
        log.info("新增部门结果为：" + deptCompletableFuture.join());
        log.info("新增菜单:{}" ,menuCompletableFuture.join());

        // 关闭线程池
        executor.shutdown();
        log.info("总耗时:" + (System.currentTimeMillis() - lordStartTime) / 1000 + "秒");
    }
}
