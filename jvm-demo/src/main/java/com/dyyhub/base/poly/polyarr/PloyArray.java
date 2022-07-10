package com.dyyhub.base.poly.polyarr;

/**
 * @author dyyhub
 * @date 2022年06月14日 16:27
 * 多态的应用，多态数组
 */
public class PloyArray {
    public static void main(String[] args) {
        Person[] people = new Person[5];
        people[0] = new Person("jack",18);
        people[1] = new Student("tom",123,67);
        people[2] = new Student("smith",42,54);
        people[3] = new Teacher("pinkMan",32,9000);
        people[4] = new Teacher("king",24,3000);

        for (Person person : people) {
            System.out.println(person.say());
            if(person instanceof Student){
                System.out.println(((Student) person).getScore());
            }else if(person instanceof  Teacher){
                //向下转型
                System.out.println(((Teacher) person).getSalary());
            }else{
                System.out.println("类型有误");
            }
        }
    }
}
