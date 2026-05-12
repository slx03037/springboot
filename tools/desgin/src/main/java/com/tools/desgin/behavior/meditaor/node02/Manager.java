package com.tools.desgin.behavior.meditaor.node02;


/**
 * @author shenlx
 * @description
 * @date 2024/10/10 11:54
 */
public class Manager implements Department{
    @Override
    public void coordinate(String username, String msg, Employee employee) {
        System.out.println("经理接收【"+username+"】的协调任务："+msg);
        System.out.println("经理接收【"+username+"】的协调任务,@【"+employee.name+"】");
        employee.getMsg(username,msg);
    }
}
