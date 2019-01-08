package com.company;

import java.util.ArrayList;

public class TourManager {

    private static ArrayList destinationCities = new ArrayList<City>();

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static City getCity(int index) {
        return (City) destinationCities.get(index);
    }

    public static int numberOfCities() {
        return destinationCities.size();
    }

    public static ArrayList getDestinationCities() {
        return destinationCities;
    }

    public static void setDestinationCities(ArrayList destinationCities) {
        TourManager.destinationCities = destinationCities;
    }
}

