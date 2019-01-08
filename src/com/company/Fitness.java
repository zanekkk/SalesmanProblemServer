package com.company;

public class Fitness extends Thread {
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

    public static int getTourDistance(Tour tour) {
        int distance = 0;

        if (tour.getDistance() == 0) {
            int tourDistance = 0;
            for (int cityIndex = 0; cityIndex < tour.tourSize(); cityIndex++) {
                City fromCity = tour.getCity(cityIndex);

                City destinationCity;

                if (cityIndex + 1 < tour.tourSize()) {
                    destinationCity = tour.getCity(cityIndex + 1);
                } else {
                    destinationCity = tour.getCity(0);
                }
                tourDistance += distanceTo(fromCity, destinationCity);
            }
            distance = tourDistance;
        }
        tour.setDistance(distance);
        return distance;
    }

    // obliczanie odleglosci pomiedzy miastami
    public static double distanceTo(City fromCity, City toCity) {
        //abs zwraca wartosc bezwzgledna
        int xDistance = Math.abs(fromCity.getX() - toCity.getX());
        int yDistance = Math.abs(fromCity.getY() - toCity.getY());
        //sqrt to jest pierwiastek kwadratowy
        double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));

        return distance;
    }

    public Tour getTour() {
        return tour;
    }
}