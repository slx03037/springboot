package com.xinwen.zookeeper.interfaces;

/**
 * @author shenlx
 * @description 我们对节点和值进行了缓存，避免频繁的访问zookeeper。
 * 在对zookeeper操作时，对连接丢失、连接新建、重连等事件进行了监听，使用到了类ZkStateListener
 * @date 2025/1/14 11:07
 */
public interface ZkStateListener {
    void reconnected();
}
