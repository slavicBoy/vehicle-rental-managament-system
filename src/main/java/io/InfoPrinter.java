package io;

import model.Car;
import model.Motorbike;
import model.Person;
import model.Vehicle;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InfoPrinter {

    public void carsInfo(Vehicle[] vehicle) {
        int carsCounter = 0;
        for (int i = 0; i < vehicle.length; i++) {
            if (vehicle[i] instanceof Car) {
                String info = vehicle[i].toString();
                printInfo(info);
                carsCounter++;
            }

        }
        if (carsCounter == 0) {
            System.out.println("Brak dostępnych aut");
        }
    }

    public void borrowedCarsInfo(Map<Vehicle, Person> borrowedVehicle) {
        int borrowedCarsCounter = 0;
        Set<Vehicle> vehicles = borrowedVehicle.keySet();
        for (Vehicle vehicle : vehicles) {
            if ( vehicle instanceof Car){
                printInfo("WYPOŻYCZONY SAMOCHÓD:");
                System.out.println(vehicle);
                Person person = borrowedVehicle.get(vehicle);
                printInfo("WYPOŻYCZAJĄCY");
                printInfo(person.toString());
                borrowedCarsCounter++;
            }
        }
        if (borrowedCarsCounter == 0) {
            System.out.println("Brak wypozyczonych samochodów");
        }
    }

    public void motorbikesInfo(Vehicle[] vehicle) {
        int motorbikesCounter = 0;
        for (int i = 0; i < vehicle.length; i++) {
            if (vehicle[i] instanceof Motorbike) {
                String info = vehicle[i].toString();
                printInfo(info);
                motorbikesCounter++;
            }

        }
        if (motorbikesCounter == 0) {
            System.out.println("Brak dostępnych motorów");
        }
    }
    public void borrowedMotorbikesInfo(Map<Vehicle, Person> borrowedVehicle) {
        int borrowedMotorbikesCounter = 0;
        Set<Vehicle> vehicles = borrowedVehicle.keySet();
        for (Vehicle vehicle : vehicles) {
            if ( vehicle instanceof Motorbike){
                printInfo("WYPOŻYCZONY MOTOCYKL:");
                System.out.println(vehicle);
                Person person = borrowedVehicle.get(vehicle);
                printInfo("WYPOŻYCZAJĄCY");
                printInfo(person.toString());
                borrowedMotorbikesCounter++;
            }
        }
        if (borrowedMotorbikesCounter == 0) {
            System.out.println("Brak wypozyczonych motocykli");
        }
    }
    public void printInfo(String info) {
        System.out.println(info);
    }

    public void carsToBorrow(List<Vehicle> vehicle) {
        int carsCounter = 0;
        for (int i = 0; i < vehicle.size(); i++) {
            if (vehicle.get(i) instanceof Car) {
                String info = vehicle.get(i).toString();
                printInfo(info);
                carsCounter++;
            }

        }
        if (carsCounter == 0) {
            System.out.println("Brak dostępnych aut");
        }
    }

    public void motorbikesToBorrow(List<Vehicle> vehicle) {
        int motorbikesCounter = 0;
        for (int i = 0; i < vehicle.size(); i++) {
            if (vehicle.get(i) instanceof Motorbike) {
                String info = vehicle.get(i).toString();
                printInfo(info);
                motorbikesCounter++;
            }

        }
        if (motorbikesCounter == 0) {
            System.out.println("Brak dostępnych motocykli");
        }
    }


}
