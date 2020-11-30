package io;

import model.Car;
import model.Motorbike;
import model.Person;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {
    private final Scanner sc = new Scanner(System.in);
    private final InfoPrinter infoPrinter;

    public DataReader(InfoPrinter infoPrinter){
        this.infoPrinter = infoPrinter;
    }

    public int getInt(){
        try {
            return sc.nextInt();
        } catch (InputMismatchException e){
            throw e;
        }
        finally {
            sc.nextLine();
        }
    }
    public double getDouble(){
        try {
            return sc.nextDouble();
        } catch (InputMismatchException e){
            throw e;
        }
        finally {
            sc.nextLine();
        }
    }

    public Motorbike createMotorbike(){
        infoPrinter.printInfo("Marka:");
        String brand = sc.nextLine();
        infoPrinter.printInfo("Model");
        String model = sc.nextLine();
        infoPrinter.printInfo("Rodzaj paliwa");
        String kindOfFuel = sc.nextLine();
        infoPrinter.printInfo("Kolor");
        String color = sc.nextLine();
        infoPrinter.printInfo("Posiada audio system: TAK - wciśnij 1, NIE - wciśnij 0");
        boolean audioSystem = true;
        int choice;
        do {
            choice = getInt();
            switch (choice) {
                case 0 -> audioSystem = false;
                case 1 -> audioSystem = true;
                default -> infoPrinter.printInfo("Zły wybór! - Wprowadź ponownie: kierownica po lewej stronie wciśnij 1, po prawej wciśnij 0");
            }
        } while (!(choice == 1 || choice == 0));
        infoPrinter.printInfo("Spalanie na 100 kilometrów");
        double mileage = getDouble();
        infoPrinter.printInfo("Rok produkcji");
        int year = getInt();
        infoPrinter.printInfo("Wysokość siodełka w cm");
        double seatHeight = getDouble();
        return new Motorbike(brand, model, mileage, year, kindOfFuel, color, audioSystem, seatHeight);
    }

    public Car createCar() {
        infoPrinter.printInfo("Marka:");
        String brand = sc.nextLine();
        infoPrinter.printInfo("Model");
        String model = sc.nextLine();
        infoPrinter.printInfo("Spalanie na 100 kilometrów");
        double mileage = getDouble();
        infoPrinter.printInfo("Rok produkcji");
        int year = getInt();
        infoPrinter.printInfo("Ilość drzwi");
        int numberOfDoors = getInt();
        infoPrinter.printInfo("Rodzaj paliwa");
        String kindOfFuel = sc.nextLine();
        infoPrinter.printInfo("Kolor");
        String color = sc.nextLine();
        infoPrinter.printInfo("Kierownica po: lewej stronie - wciśnij 1, po prawej wciśnij 0");
        boolean isWheelOnLeftSide = true;
        int choice;
        do {
            choice = getInt();
            switch (choice) {
                case 0 -> isWheelOnLeftSide = false;
                case 1 -> isWheelOnLeftSide = true;
                default -> System.out.println("Zły wybór! - Wprowadź ponownie: kierownica po lewej stronie wciśnij 1, po prawej wciśnij 0");
            }
        } while (!(choice == 1 || choice == 0));
        return new Car(brand, model, mileage, year,  kindOfFuel, color, numberOfDoors, isWheelOnLeftSide);
    }
    public Person createPerson() {
        infoPrinter.printInfo("Imię");
        String firstName = sc.nextLine();
        infoPrinter.printInfo("Nazwisko");
        String lastName = sc.nextLine();
        infoPrinter.printInfo("PESEL");
        String pesel = sc.nextLine();
        return new Person(firstName, lastName, pesel);
    }
    public int getOption() {
        return getInt();
    }
    public void closeScanner() {
        sc.close();
    }
    public void clearStream(){
        sc.nextLine();
    }
}
