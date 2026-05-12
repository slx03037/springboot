package com.tools.desgin.structure.composite.node01;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author shenlx
 * @description
 * @date 2024/9/2 10:32
 */
public class Employee {
    private final String name;

    private final String dept;

    private final int salary;

    private final List<Employee> subordinates;

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        subordinates=new ArrayList<Employee>();
    }

    public void add(Employee e){
        subordinates.add(e);
    }

//    public void remove(Employee e){
//        subordinates.remove(e);
//    }

    public List<Employee> getSubordinates(){
        return subordinates;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("dept='" + dept + "'")
                .add("salary=" + salary)
                .add("subordinates=" + subordinates)
                .toString();
    }
}
