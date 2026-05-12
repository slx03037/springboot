package com.tools.desgin.structure.composite.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/2 10:46
 */
public class CompositePatternDemo {
    public static void main(String[]args){
        Employee ceo=new Employee("john","CEO",300000);

        Employee headSales=new Employee("Robert","Head Sales",200000);

        Employee headMarketing=new Employee("Michel","Head Marketing",200000);

        Employee clerk1=new Employee("Laura","Marketing",10000);

        Employee clerk2=new Employee("Bob","Marketing",10000);

        Employee salesExecutive1=new Employee("Richard","Sales",10000);

        Employee salesExecutive2=new Employee("Rbo","Sales",10000);

        ceo.add(headSales);
        ceo.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        System.out.println(ceo);


    }
}
