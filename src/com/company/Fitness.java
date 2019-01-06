package com.company;


import java.util.ArrayList;

public class Fitness extends Thread {
    // Gets the tours fitness
    private Lock lock;
    private Tour tour;

    public Fitness(Tour tour, Lock lock){
        this.lock = lock;
        this.tour = tour;
    }



    @Override
    public void run() {
        try {
            lock.addRunningThread();

            tour.setFitness(startSettingFitness());

            lock.removeRunningThread();

            synchronized (lock) {
                lock.notifyAll();
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }



    public double startSettingFitness() {
        double fitness = tour.getFitness();

        if (fitness == 0.0) {
            fitness = 1 / (double) getTourDistance(this.tour);
        }

        return fitness;
    }

    // Gets the total distance of the tour
    public static int getTourDistance(Tour tour) {
        int distance = 0;

        if (tour.getDistance() == 0) {
            int tourDistance = 0;
            // Loop through our tour's cities
            for (int cityIndex = 0; cityIndex < tour.tourSize(); cityIndex++) {
                // Get city we're travelling from
                City fromCity = tour.getCity(cityIndex);
                // City we're travelling to
                City destinationCity;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if (cityIndex + 1 < tour.tourSize()) {
                    destinationCity = tour.getCity(cityIndex + 1);
                } else {
                    destinationCity = tour.getCity(0);
                }
                // Get the distance between the two cities
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        tour.setDistance(distance);
        return distance;
    }

    private static Tour checkChild(Tour child) {
        ArrayList<City> cities = new ArrayList<>();

        for (int i = 0; i < child.tourSize(); i++) {
            City cityFromChild = child.getCity(i);
            System.out.print(child.getCity(i));
            if (citiesContains(cities,cityFromChild)) {
                child.setCity(i, findCityNotContains(child));
                System.out.print("JESTEM W");
            } else {
                cities.add(cityFromChild);
            }

        }
        return child;
    }

    private static boolean citiesContains(ArrayList<City> tour, City city){
        Integer x = city.x;
        Integer y = city.y;
        for (int i = 0; i < tour.size(); i++) {
            if (!x.equals(null) && y.equals(null)) {
                if (city.x == tour.get(i).x && city.y == tour.get(i).y) {
                    return true;
                }
            }
        }
        return false;
    }

    private static City findCityNotContains(Tour child) {
        TourManager.setDestinationCities(child.getBaseCities());

        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            if (!child.containsCity(TourManager.getCity(i))) {
                return TourManager.getCity(i);
            }
        }
        return null;
    }


    public Tour getTour() {
        return tour;
    }
}