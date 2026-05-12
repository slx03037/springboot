package com.tools.desgin.behavior.meditaor.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 11:55
 */
public class Employee {

    public String name;

    private final Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public void getMsg(String username,String msg){
        System.out.println("【"+this.name+"】收到【"+username+"】的协调任务：【"+msg+"】");
    }

    public void sendMsg(String name,String msg,Employee employee){
        System.out.println("【"+name+"】发起协调任务"+msg);
        department.coordinate(name,msg,employee);
    }
}
