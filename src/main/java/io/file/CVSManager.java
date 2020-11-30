package io.file;

import exception.InvalidDataException;
import io.DataReader;
import io.InfoPrinter;
import model.Car;
import model.Garage;
import model.Motorbike;
import model.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class CVSManager {
    private InfoPrinter printerInfo;
    private DataReader dataReader;
    private Garage garage;
    private final static String CARS = "cars.txt";
    private final static String MOTORBIKES = "motorbikes.txt";

    public CVSManager(InfoPrinter printerInfo, DataReader dataReader, Garage garage) {
        this.printerInfo = printerInfo;
        this.dataReader = dataReader;
        this.garage = garage;
    }

    public void exportData() {
        int option = option();
        if(option == 1){
            int carsCounter = 0;
            Vehicle[] vehicles = garage.getVehicles();
            for (int i = 0; i < vehicles.length ; i++) {
                 if(vehicles[i] instanceof Car)
                     carsCounter++;
            }
            Car[] cars = new Car[carsCounter];
            int carsTab = 0;
            for (int i = 0; i < vehicles.length; i++) {
                if(vehicles[i] instanceof Car){
                    Car car = (Car) vehicles[i];
                    cars[carsTab] = car;
                    carsTab++;
                }
            }
            try (var fileWriter = new FileWriter(CARS);
                 var bufferedWriter = new BufferedWriter(fileWriter)) {
                for (int i = 0; i <cars.length ; i++) {
                    bufferedWriter.write(cars[i].toCsv());
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                throw new InvalidDataException("Błąd zapisu danych do pliku " + CARS);
            }
        }
        if(option == 0){
            int motorbikesCounter = 0;
            Vehicle[] vehicles = garage.getVehicles();
            for (int i = 0; i < vehicles.length ; i++) {
                if(vehicles[i] instanceof Motorbike)
                    motorbikesCounter++;
            }
            Motorbike[] motorbikes = new Motorbike[motorbikesCounter];
            int motorbikesTab = 0;
            for (int i = 0; i < vehicles.length; i++) {
                if(vehicles[i] instanceof Motorbike){
                    Motorbike motorbike = (Motorbike) vehicles[i];
                    motorbikes[motorbikesTab] = motorbike;
                    motorbikesTab++;
                }
            }
            try (var fileWriter = new FileWriter(MOTORBIKES);
                 var bufferedWriter = new BufferedWriter(fileWriter)) {
                for (int i = 0; i <motorbikes.length ; i++) {
                    bufferedWriter.write(motorbikes[i].toCsv());
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                throw new InvalidDataException("Błąd zapisu danych do pliku " + CARS);
            }
        }
    }

    private int option() {
        int choice = 0;
        do {
            printerInfo.printInfo("Co chcesz zapisać do pliku? 0 - MOTOCYKLE, 1 - AUTA");
            try {
                choice = dataReader.getInt();
            } catch (InputMismatchException error) {
                System.err.println("Błąd, brak wartości liczbowej");
            }
              if(choice != 0 && choice != 1){
                  printerInfo.printInfo("Brak takiej wartości, spróbuj ponownie");
              }
        } while (choice != 0 && choice !=1);
          return choice;
    }
}

