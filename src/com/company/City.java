package com.company;

import java.io.Serializable;

public class City implements Serializable {
    int x;
    int y;
    String name;

    public City() {
        this.x = (int) (Math.random() * 200);
        this.y = (int) (Math.random() * 200);
    }


    public City(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}