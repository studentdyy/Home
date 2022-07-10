package com.dyyhub.base.homework;

/**
 * @author dyyhub
 * @date 2022年06月16日 17:22
 */
public class HomeWork08 {
    public static void main(String[] args) {
        Car car = new Car(45);
        car.getAir().flow();
        Car car2 = new Car(32);
        car2.getAir().flow();
    }

}
class Car{
    private double temperature;

    public Car(double temperature) {
        this.temperature = temperature;
    }

    class Air{
        public void flow(){
            if(temperature >= 40){
                System.out.println("吹冷风");
            }else {
                System.out.println("吹暖风");
            }
        }
    }

    public Air getAir() {
        return new Air();
    }
}