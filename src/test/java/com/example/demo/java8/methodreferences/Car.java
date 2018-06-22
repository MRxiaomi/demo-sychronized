package com.example.demo.java8.methodreferences;

import java.util.function.Supplier;

/**
 * Created by liuyumeng on 2018/6/11.
 *
 */
public class Car {
    /**
     * 类似于工厂方法，每次返回不同对象
     * @param supplier
     * @return
     */
    public static Car create( final Supplier< Car > supplier ) {
        return supplier.get();
    }

    public static void collide( final Car car ) {
        System.out.println( "Collided " + car.toString() );
    }

    public void follow( final Car another ) {
        System.out.println( "Following the " + another.toString() );
    }

    public void repair() {
        System.out.println( "Repaired " + this.toString() );
    }

    public static void main(String[] args) {
        Car car = Car.create(Car::new);
        System.out.println(car);
        car = Car.create(Car::new);
        System.out.println(car);
        Supplier<Car> supplier = ()->new Car();
        supplier = Car::new;


    }
}
