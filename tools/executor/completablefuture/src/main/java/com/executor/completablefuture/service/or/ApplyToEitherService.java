package com.executor.completablefuture.service.or;

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
import java.util.concurrent.ExecutionException;

/**
 * @author shenlx
 * @description
 * @date 2026/3/6 16:56
 */
@Service
@Slf4j
public class ApplyToEitherService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private MenuRepository menuRepository;

    @Resource
    private DeptRepository deptRepository;

    //会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值
    public void defaultApplyToEither() throws ExecutionException, InterruptedException {
        long lordStartTime = System.currentTimeMillis();
        CompletableFuture<User> userCompletableFuture = CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部新增用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增用户耗时:" + (endTime - lordStartTime) / 1000 + "秒");
            return save;
        });

        CompletableFuture<Menu> uCompletableFuture = userCompletableFuture.applyToEither(CompletableFuture.supplyAsync(() -> {
            //log.info("用户信息:{}");
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部部门用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增部门耗时:" + (endTime - lordStartTime) / 1000 + "秒");
            User user = new User("张三2", 10, 1);
            return user;
        }), (user) -> {
            log.info("用户信息:{}",user);
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部菜单用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增菜单耗时:" + (endTime - lordStartTime) / 1000 + "秒");
            return save;
        });
        log.info("请求调用结果:{}",uCompletableFuture.get());
        log.info("总耗时:" + (System.currentTimeMillis() - lordStartTime) / 1000 + "秒");
    }

    public void defaultAcceptEither(){
        long lordStartTime = System.currentTimeMillis();
        CompletableFuture<User> userCompletableFuture = CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部新增用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增用户耗时:" + (endTime - lordStartTime) / 1000 + "秒");
            return save;
        });

        CompletableFuture<Void> uCompletableFuture = userCompletableFuture.acceptEither(CompletableFuture.supplyAsync(() -> {
            //log.info("用户信息:{}");
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部部门用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增部门耗时:" + (endTime - lordStartTime) / 1000 + "秒");
            User user = new User("张三2", 10, 1);
            return user;
        }), (user) -> {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部菜单用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增菜单耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        });
        log.info("总耗时:" + (System.currentTimeMillis() - lordStartTime) / 1000 + "秒");
    }

    public void defaultRunAfterEither(){
        long lordStartTime = System.currentTimeMillis();
        CompletableFuture<User> userCompletableFuture = CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = new User("张三1", 10, 1);
            User save = userRepository.save(user);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部新增用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增用户耗时:" + (endTime - lordStartTime) / 1000 + "秒");
            return save;
        });

        CompletableFuture<Void> uCompletableFuture = userCompletableFuture.runAfterEither(CompletableFuture.supplyAsync(() -> {
            //log.info("用户信息:{}");
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Dept dept = new Dept("boss", "ceo", 0L);
            Dept save = deptRepository.save(dept);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部部门用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增部门耗时:" + (endTime - lordStartTime) / 1000 + "秒");
            User user = new User("张三2", 10, 1);
            return user;
        }), () -> {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Menu menu = new Menu("dept", "部门");
            Menu save = menuRepository.save(menu);
            long endTime = System.currentTimeMillis();
            log.info("线程：{}，---{}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("内部菜单用户耗时:" + (endTime - startTime) / 1000 + "秒");
            log.info("新增菜单耗时:" + (endTime - lordStartTime) / 1000 + "秒");
        });
        log.info("总耗时:" + (System.currentTimeMillis() - lordStartTime) / 1000 + "秒");
    }

    public void applyToEither1() {
        log.info("魏凯下班准备回家。。。");
        log.info("魏凯等待2号，4号地铁。。。");
        CompletableFuture<String> busCF = CompletableFuture.supplyAsync(() -> {
            log.info("2号在路上。。。");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            log.info("4号地铁在路上。。。");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "4";
        }), second -> second + "号");
        log.info("魏凯坐上" + busCF.join() + "地铁");
    }

    public void applyToEither3() throws ExecutionException, InterruptedException {
        log.info("魏凯下班准备回家。。。");
        log.info("魏凯等待2号，4号地铁。。。");
        CompletableFuture<String> busCF = CompletableFuture.supplyAsync(() -> {
            log.info("2号在路上。。。");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "2";
        });
        CompletableFuture<String> stringCompletableFuture = busCF.applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "4";
        }), code -> code);
        log.info("获取结果:{}",stringCompletableFuture.get());
    }

    public void applyToEither() {
        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 7;
        });
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 7;
        }).applyToEither(first, num -> num);
        log.info("最后结果为：" + second.join());
    }
}
