package com.dyyhub.base.homework;

/**
 * @author dyyhub
 * @date 2022年06月16日 16:57
 * 灵活运行接口，工厂
 */
public class HomeWork06 {
    public static void main(String[] args) {
        Man man = new Man("唐生",new Horse());
        man.passRiver();
        man.common();
        man.common();
        man.common();
        man.passRiver();
    }
}

interface Vehicles{
    public void work();
}
class Horse implements Vehicles{
    @Override
    public void work() {
        System.out.println("骑马走");

    }
}
class Boat implements Vehicles{
    @Override
    public void work() {
        System.out.println("坐船走");
    }
}
class TransportationToolFactory{
    private static final Horse horse = new Horse();//饿汉式
    private static final Boat boat = new Boat();//饿汉式
    private TransportationToolFactory(){}
    public static Horse getHorse(){
        return horse;
    }
    public static Boat getBoat(){
        return boat;
    }
}
class Man{
    private String name;
    private Vehicles vehicles;

    public Man(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
        vehicles.work();
    }

    public void passRiver(){
        Boat boat = TransportationToolFactory.getBoat();
        boat.work();
    }
    public void common(){
        vehicles.work();
    }
}