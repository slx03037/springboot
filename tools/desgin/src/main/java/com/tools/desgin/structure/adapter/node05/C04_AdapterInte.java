package com.tools.desgin.structure.adapter.node05;

/**
 * @author shenlx
 * @description
 * @date 2024/8/26 15:52
 */
public class C04_AdapterInte {
    public static void main(String[]args){
        ServiceAdapter adapter = new ServiceAdapter() {
            @Override
            public int serviceOperation2() {
                return 22;
            }
        };
        System.out.println(adapter.serviceOperation2());
    }
}
