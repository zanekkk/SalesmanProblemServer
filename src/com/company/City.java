package com.company;

import java.io.Serializable;

public class City implements Serializable {
    int x;
    int y;
    String name;

    // Constructs a randomly placed city
    public City() {
        this.x = (int) (Math.random() * 200);
        this.y = (int) (Math.random() * 200);
    }


    public City(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    // Gets city's x coordinate
    public int getX() {
        return this.x;
    }

    // Gets city's y coordinate
    public int getY() {
        return this.y;
    }

    // obliczanie odleglosci pomiedzy miastami
    public double distanceTo(City city) {
        //abs zwraca wartosc bezwzgledna
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        //sqrt to jest pierwiastek kwadratowy
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}