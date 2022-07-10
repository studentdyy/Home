package com.dyyhub.base.collection_.mapInterface_.hashmap_;

import java.util.*;

/**
 * @author dyyhub
 * @date 2022年06月19日 17:27
 * HashMap的各种遍历方法
 * 打印员工薪资大于18000的员工
 */
public class HomeWork01 {
    public static void main(String[] args) {
        Employee employee1 = new Employee(1,"Tom",18000);
        Employee employee2 = new Employee(2,"jack",16000);
        Employee employee3 = new Employee(3,"Laly",19000);
        HashMap hashMap = new HashMap();
        hashMap.put(employee1.getEmployeeId(),employee1);
        hashMap.put(employee2.getEmployeeId(),employee2);
        hashMap.put(employee3.getEmployeeId(),employee3);
        //第一种遍历方式
        Set set = hashMap.keySet();
        for (Object key : set) {
//            System.out.println(key + "-" + hashMap.get(key));
            Employee employee = (Employee) hashMap.get(key);
            if(employee.getSalary() > 18000){
                System.out.println(employee);
            }
        }
        System.out.println("===========================");
        //第二种
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object key =  iterator.next();
            System.out.println(key + "-" + hashMap.get(key));
        }
        System.out.println("===========================");
        //第三种
        Collection values = hashMap.values();
        for (Object value : values) {
            System.out.println(value);
        }
        System.out.println("===========================");
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            Object value = iterator1.next();
            System.out.println(value);
        }
        System.out.println("===========================");
        Set set1 = hashMap.entrySet();
        System.out.println("===========================");
        System.out.println(set1.toString());
        System.out.println("===========================");
        for (Object entry : set1) {
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }
    }
}
class Employee{
    int  employeeId;
    String name;
    double salary;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}