package com.androidprojects.esprit.secondapproach;

/**
 * Created by Amal on 13/12/2016.
 */

public class Car {

    private String brand;
    private int ref;
    private int speed;

    public Car(String brand,int ref,int speed){
        this.brand=brand;
        this.ref=ref;
        this.speed=speed;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
