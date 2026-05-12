package com.tools.desgin.behavior.meditaor.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 14:37
 */
public class C01_Inscene {
    public static void main(String[]args){
        Manager manager = new Manager();
        EmployeeA a = new EmployeeA("张三", manager);
        EmployeeB b = new EmployeeB("李四", manager);
        a.sendMsg(a.name,"需要产品文档",b);
    }
}
