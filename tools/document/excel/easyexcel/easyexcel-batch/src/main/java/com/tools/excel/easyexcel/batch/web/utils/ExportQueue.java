package com.tools.excel.easyexcel.batch.web.utils;

import com.tools.excel.easyexcel.batch.web.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 14:39
 */
@Slf4j
@Component
public class ExportQueue {

    private final int MAX_CAPACITY = 10;     // 队列最大容量
    private LinkedList<User> queue;     // 用户队列

    public ExportQueue(LinkedList<User> queue) {
        this.queue = new LinkedList <>();
    }

    /**
     * 排队队列添加
     *
     * @param sysUser
     */
    public synchronized LinkedList<User> add(User sysUser) {
        while (queue.size() >= MAX_CAPACITY) {
            try {
                log.info("当前排队人已满，请等待");
                wait();
            } catch (InterruptedException e) {
                e.getMessage();
            }
        }
        queue.add(sysUser);
        log.info("目前导出队列排队人数：" + queue.size());
        notifyAll();
        return queue;
    }

    /**
     * 获取排队队列下一个人
     *
     * @return
     */
    public synchronized User getNextSysUser() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        User sysUser = queue.remove();
        notifyAll();     //唤醒
        return sysUser;
    }
}
