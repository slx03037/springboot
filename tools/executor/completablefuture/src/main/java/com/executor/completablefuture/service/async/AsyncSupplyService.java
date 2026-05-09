package com.executor.completablefuture.service.async;

import com.executor.completablefuture.dao.DeptRepository;
import com.executor.completablefuture.dao.MenuRepository;
import com.executor.completablefuture.dao.UserRepository;
import com.executor.completablefuture.entity.Dept;
import com.executor.completablefuture.entity.Menu;
import com.executor.completablefuture.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:55
 */
@Service
@Slf4j
public class AsyncSupplyService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private DeptRepository deptRepository;

    @GetMapping("save/supply/async")
    // supplyAsync执行CompletableFuture任务，支持返回值
    public void defaultSupplyAsync() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        // 构建执行任务
        CompletableFuture<User> userCompletableFuture= CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("新增用户耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        });
//        这行代码在这里 则会进行6秒的阻塞 下面代码其他线程无法创建
//        只能等这个线程6秒过后结束才能创建其他线程
//        Map<String, Object> userMap = userCompletableFuture.get();
        CompletableFuture<Dept> deptCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("新增部门耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        });

        CompletableFuture<Menu> menuCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("新增菜单耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        });

        log.info("新增用户为：" + userCompletableFuture.join());

        log.info("新增部门为：" + deptCompletableFuture.join());

        log.info("新增菜单为：" + menuCompletableFuture.join());

        log.info("总耗时:" + (System.currentTimeMillis() - startTime) / 1000 + "秒");
    }

    @GetMapping("save/supply/bock")
    // supplyAsync执行CompletableFuture任务，支持返回值
    public String defaultSupplyBock() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        // 构建执行任务
        CompletableFuture<User>  userCompletableFuture= CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("新增用户耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        });
        log.info("新增用户为：" + userCompletableFuture.join());
//        这行代码在这里 则会进行6秒的阻塞 下面代码其他线程无法创建
//        只能等这个线程6秒过后结束才能创建其他线程
//        Map<String, Object> userMap = userCompletableFuture.get();
        CompletableFuture<Dept> deptCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("新增部门耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        });
//        这行代码在这里 则会进行6秒的阻塞 下面代码其他线程无法创建
//        只能等这个线程6秒过后结束才能创建其他线程
        //       Dept join = deptCompletableFuture.join();
        //       Dept dept = deptCompletableFuture.get();
        log.info("新增部门为：" + deptCompletableFuture.join());

        CompletableFuture<Menu> menuCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("新增菜单耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        });

        log.info("新增菜单为：" + menuCompletableFuture.join());

        log.info("总耗时:" + (System.currentTimeMillis() - startTime) / 1000 + "秒");
        long l = (System.currentTimeMillis() - startTime) / 1000;
        return String.format("总耗时:%s秒",l);
    }

    @GetMapping("save/custom/supply/async")
    // supplyAsync执行CompletableFuture任务，支持返回值
    public void customSupplyAsync() throws ExecutionException, InterruptedException {
        // 自定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        CompletableFuture<User> userCompletableFuture=CompletableFuture.supplyAsync( () -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("新增用户耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        },executorService);
//        这行代码在这里 则会进行6秒的阻塞 下面代码其他线程无法创建
//        只能等这个线程6秒过后结束才能创建其他线程
//        userCompletableFuture.get();和 userCompletableFuture.join();

        CompletableFuture<Dept> deptCompletableFuture=CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("新增部门耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        },executorService);

        CompletableFuture<Menu> menuCompletableFuture=CompletableFuture.supplyAsync( () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("新增菜单耗时:" + (endTime - startTime) / 1000 + "秒");
            return save;
        },executorService);

        log.info("用户查询结果为：" + userCompletableFuture.join());
        log.info("部门查询结果为：" + deptCompletableFuture.join());
        log.info("菜单查询结果为：" + menuCompletableFuture.join());
        // 线程池需要关闭
        executorService.shutdown();
        log.info("总耗时:" + (System.currentTimeMillis() - startTime) / 1000 + "秒");
    }
}
