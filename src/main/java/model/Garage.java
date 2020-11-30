package model;

import exception.NoVehicleException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Garage implements Serializable {
    private static final int STARTING_NUMBER = 1;
    private int vehiclesNumber = 0;
    private Vehicle[] vehicles = new Vehicle[STARTING_NUMBER];

    public Vehicle[] getVehicles() {
        Vehicle[] vehicles = new Vehicle[vehiclesNumber];
        for (int i = 0; i < vehiclesNumber; i++) {
            if (this.vehicles[i] != null) {
                vehicles[i] = this.vehicles[i];
            }
        }
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehiclesNumber == vehiclesNumber) {
            vehicles = Arrays.copyOf(vehicles, vehicles.length * 2);
        }
        vehicles[vehiclesNumber] = vehicle;
        vehiclesNumber++;
    }

    public boolean removeVehicle(Vehicle vehicle) {
        int removedVehicles = 0;
        List<Vehicle> vehiclesList = new ArrayList<>(Arrays.asList(this.vehicles));
        for (int i = 0; i < vehiclesNumber; i++) {
            if (vehicle.equals(vehiclesList.get(i))) {
                vehiclesList.remove(i);
                removedVehicles++;
                vehiclesNumber--;
            }
        }
        this.vehicles = vehiclesList.toArray(Vehicle[]::new);

        return removedVehicles != 0;
    }

    public List<Vehicle> vehicleToBorrow(Vehicle[] vehicles) {
        List<Vehicle> vehiclesToBorrow = new ArrayList<>();
        int vehicleToBorrowCounter = 0;
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i] != null) {
                if (!vehicles[i].getBorrowed()) {
                    vehiclesToBorrow.add(vehicles[i]);
                    vehicleToBorrowCounter++;
                }
            }
        }
        if (vehicleToBorrowCounter == 0) {
            throw new NoVehicleException("No vehicles to borrow");
        }
        return vehiclesToBorrow;
    }
}


