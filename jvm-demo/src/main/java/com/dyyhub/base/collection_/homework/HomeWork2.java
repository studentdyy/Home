package com.dyyhub.base.collection_.homework;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author dyyhub
 * @date 2022年06月20日 10:41
 * HashMap练习题 员工所有薪资+100,遍历等相关操作
 */
public class HomeWork2 {
    public static void main(String[] args) {
        Employee employee1 = new Employee("jack",650);
        Employee employee2 = new Employee("tom",1200);
        Employee employee3 = new Employee("smith",2900);
        HashMap hashMap = new HashMap();
        hashMap.put(employee1.getName(),employee1.getSalary());
        hashMap.put(employee2.getName(),employee2.getSalary());
        hashMap.put(employee3.getName(),employee3.getSalary());
        System.out.println(hashMap);

        hashMap.put("jack",2600);
        System.out.println(hashMap);
        //为所有员工加薪100 方式一
        Set set = hashMap.entrySet();
        for (Object o : set) {
            Map.Entry m = (Map.Entry)o;
            int salary = (int)m.getValue();
            salary += 100;
            m.setValue(salary);
        }
        System.out.println(hashMap);
        //为所有员工加薪100 方式二
        Set set1 = hashMap.keySet();
        for (Object key : set1) {
            hashMap.put(key,(int)hashMap.get(key) + 100);
        }
        System.out.println(hashMap);

        //遍历所有员工
        for (Object o : set) {
            Map.Entry m = (Map.Entry)o;
            System.out.println("员工：" + m.getKey());
        }
        //遍历所有工资 方式一
        for (Object o : set) {
            Map.Entry m = (Map.Entry)o;
            System.out.println("工资：" + m.getValue());
        }
        //遍历所有工资 方式二
        Collection values = hashMap.values();
        for (Object value : values) {
            System.out.println("工资=" + value);
        }
    }
}
class Employee{
    private String name;
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}