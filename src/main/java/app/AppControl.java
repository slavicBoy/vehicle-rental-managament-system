package app;

import exception.ExportDataException;
import exception.ImportDataException;
import exception.NoVehicleException;
import io.DataReader;
import io.InfoPrinter;
import io.file.BuildFile;
import io.file.CVSManager;
import io.file.SerializableManager;
import model.*;

import java.util.*;

public class AppControl {
    private static final int EXIT = 0;
    private static final int ADD_CAR = 1;
    private static final int ADD_MOTORBIKE = 2;
    private static final int PRINT_INFO_ABOUT_CARS = 3;
    private static final int PRINT_INFO_ABOUT_MOTORBIKES = 4;
    private static final int EXPORT_TO_CSV = 5;
    private static final int REMOVE_CAR = 6;
    private static final int REMOVE_MOTORBIKE = 7;
    private static final int BORROW_CAR = 8;
    private static final int BORROW_MOTORBIKE = 9;
    private static final int RECEIVE_CAR = 11;
    private static final int RECEIVE_MOTORBIKE = 22;
    private static final int REPEAT_OPTIONS = 100;
    private InfoPrinter infoPrinter = new InfoPrinter();
    private DataReader dataReader = new DataReader(infoPrinter);
    private Garage garage = new Garage();
    private SerializableManager serializableManager;
    private PersonBase personBase = new PersonBase();

    public AppControl() {
        serializableManager = new BuildFile(infoPrinter, dataReader).build();
        try {
            garage = serializableManager.importDataVehicle();
            personBase = serializableManager.importDataPerson();
        } catch (ImportDataException e) {
            infoPrinter.printInfo(e.getMessage());

        }
    }

    public void options() {

        int choice = 4;
        do {
            printOptions();
            try {
                choice = dataReader.getOption();
            } catch (InputMismatchException e) {
                System.err.println("Bład, brak wartosci liczbowej");
            }
            switch (choice) {
                case EXIT -> {
                    exit();
                }
                case ADD_CAR -> {
                    createCar();
                }
                case ADD_MOTORBIKE -> {
                    createMotorbike();
                }
                case PRINT_INFO_ABOUT_CARS -> printCarInfo();
                case PRINT_INFO_ABOUT_MOTORBIKES -> printMotorbikeInfo();
                case EXPORT_TO_CSV -> exportToCSV();
                case REPEAT_OPTIONS -> options();
                case REMOVE_CAR -> removeCar();
                case REMOVE_MOTORBIKE -> removeMotorbike();
                case BORROW_CAR -> borrowCar();
                case BORROW_MOTORBIKE -> borrowMotorbike();
                case RECEIVE_CAR -> receiveCar();
                case RECEIVE_MOTORBIKE -> receiveMotorbike();
                default -> infoPrinter.printInfo("Nie ma takiej opcji, sprobuj ponownie");
            }
        } while (choice != EXIT);
    }

    private void receiveCar() {
        try {
            infoPrinter.printInfo("Wprowadz dane zwroconego samochodu");
            Vehicle car = dataReader.createCar();
            boolean isProperCarExist = garage.removeVehicle(car);
            if (isProperCarExist) {
                car.setBorrowed(false);
                garage.addVehicle(car);
                personBase.remove(car);
                infoPrinter.printInfo("Auto pomyslnie oddane");
            } else {
                infoPrinter.printInfo("Nie ma takiego auta, nie moglo zostac oddane");
            }

        } catch (InputMismatchException e) {
            System.err.println("Zamiast wartosci liczbowej zostala dodana litera, operacja przerwana");
        }

    }

    private void receiveMotorbike() {
        try {
            infoPrinter.printInfo("Wprowadz dane zwroconego motoru");
            Vehicle motorbike = dataReader.createMotorbike();
            boolean isProperCarExist = garage.removeVehicle(motorbike);
            if (isProperCarExist) {
                motorbike.setBorrowed(false);
                garage.addVehicle(motorbike);
                personBase.remove(motorbike);
                infoPrinter.printInfo("Motor pomyslnie oddany");
            } else {
                infoPrinter.printInfo("Nie ma takiego motoru, nie mogl zostac oddany");
            }

        } catch (InputMismatchException e) {
            System.err.println("Zamiast wartości liczbowej została dodana litera, operacja przerwana");
        }

    }

    private void borrowCar() {
        try {
            infoPrinter.printInfo("Auta dostepne do wypozyczenia:");
            Vehicle[] vehicles = garage.getVehicles();
            List<Vehicle> vehiclesToBorrow = garage.vehicleToBorrow(vehicles);
            infoPrinter.carsToBorrow(vehiclesToBorrow);
            infoPrinter.printInfo("Ktore auto chcesz wypozyczyć?");
            Vehicle car = dataReader.createCar();
            boolean isProperCarExist = garage.removeVehicle(car);
            if (isProperCarExist) {
                car.setBorrowed(true);
                garage.addVehicle(car);
                infoPrinter.printInfo("Podaj dane wypozyczającego");
                personBase.addPerson(car, dataReader.createPerson());
                infoPrinter.printInfo("Auto pomyslnie wypozyczone");
            } else {
                infoPrinter.printInfo("Brak takiego auta do wypozyczenia");
            }

        } catch (NoVehicleException e) {
            System.err.println("Brak samochodow do wypozyczenia");
        } catch (InputMismatchException e) {
            System.err.println("Zamiast wartości liczbowej została dodana litera, operacja przerwana");
        }

    }

    private void borrowMotorbike() {
        try {
            infoPrinter.printInfo("Motocykle dostępne do wypozyczenia:");
            Vehicle[] vehicles = garage.getVehicles();
            List<Vehicle> vehiclesToBorrow = garage.vehicleToBorrow(vehicles);
            infoPrinter.motorbikesToBorrow(vehiclesToBorrow);
            infoPrinter.printInfo("Ktory motocykl chcesz wypozyczyć?");
            Vehicle motorbike = dataReader.createMotorbike();
            boolean isProperMotorbikeExist = garage.removeVehicle(motorbike);
            if (isProperMotorbikeExist) {
                motorbike.setBorrowed(true);
                garage.addVehicle(motorbike);
                personBase.addPerson(motorbike, dataReader.createPerson());
                infoPrinter.printInfo("Motor pomyslnie wypozyczony");
            } else {
                infoPrinter.printInfo("Brak takiego motocykla do wypozyczenia");
            }
        } catch (NoVehicleException e) {
            System.err.println("Brak motorow do wypozyczenia");
        } catch (InputMismatchException e) {
            System.err.println("Zamiast wartości liczbowej została dodana litera, operacja przerwana");
        }

    }

    private void removeCar() {
        try {
            Car car = dataReader.createCar();
            boolean removedVehicles = garage.removeVehicle(car);
            if (!removedVehicles) {
                infoPrinter.printInfo("Brak takiego pojazdu w bazie");
            } else {
                personBase.remove(car);
                infoPrinter.printInfo("Pojazd został usunięty");
            }
        } catch (InputMismatchException e) {
            System.err.println("Zamiast wartości liczbowej została dodana litera, operacja przerwana.");
        }
    }

    private void removeMotorbike() {
        try {
            Motorbike motorbike = dataReader.createMotorbike();
            boolean removedVehicles = garage.removeVehicle(motorbike);
            if (!removedVehicles) {
                infoPrinter.printInfo("Brak takiego pojazdu w bazie");
            } else {
                infoPrinter.printInfo("Pojazd został usunięty");
                personBase.remove(motorbike);
            }
        } catch (InputMismatchException e) {
            System.err.println("Zamiast wartości liczbowej została dodana litera, operacja przerwana.");
        }
    }

    private void exportToCSV() {
        CVSManager cvs = new CVSManager(infoPrinter, dataReader, garage);
        cvs.exportData();

    }

    private void createMotorbike() {
        try {
            Motorbike motorbike = dataReader.createMotorbike();
            garage.addVehicle(motorbike);
        } catch (InputMismatchException e) {
            System.err.println("Błąd, zamiast cyfry została dodana litera, pojazd nie został dodany do garażu");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Błąd, brak miejsca w garażu, pojazd nie został dodany");
        }
    }

    private void createCar() {
        try {
            Car car = dataReader.createCar();
            garage.addVehicle(car);
        } catch (InputMismatchException e) {
            System.err.println("Błąd, zamiast cyfry została dodana litera, pojazd nie został dodany do garażu");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Błąd, brak miejsca w garażu, pojazd nie został dodany");
        }

    }

    private void printCarInfo() {
        printCarSpecificOptions();
        try {
            int option = dataReader.getOption();
            switch (option) {
                case 0 -> {
                    Map<Vehicle, Person> borrowers = personBase.getBorrowedVehicles();
                    infoPrinter.borrowedCarsInfo(borrowers);
                }
                case 1 -> {
                    Vehicle[] vehicles = garage.getVehicles();
                    Arrays.sort(vehicles);
                    infoPrinter.carsInfo(vehicles);
                }
                default -> infoPrinter.printInfo("Zła opcja");
        }

        } catch (InputMismatchException e){
            System.err.println("Brak wartości liczbowej, bład.");
        }

    }

    private void printMotorbikeInfo() {
        printMotorbikeSpecificOptions();
        try {
            int option = dataReader.getOption();
            switch (option) {
                case 0 -> {
                    Map<Vehicle, Person> borrowers = personBase.getBorrowedVehicles();
                    infoPrinter.borrowedMotorbikesInfo(borrowers);
                }
                case 1 -> {
                    Vehicle[] vehicles = garage.getVehicles();
                    Arrays.sort(vehicles);
                    infoPrinter.motorbikesInfo(vehicles);
                }
                default -> infoPrinter.printInfo("Zła opcja");
            }

        } catch (InputMismatchException e){
            System.err.println("Brak wartości liczbowej, bład.");
        }
    }


    private void printOptions() {
        infoPrinter.printInfo("Jaką opcje chcesz wybrać?");
        infoPrinter.printInfo(EXIT + " - WYJŚCIE Z PROGRAMU");
        infoPrinter.printInfo(ADD_CAR + " - DODAJ AUTO DO WYPOZYCZALNI");
        infoPrinter.printInfo(ADD_MOTORBIKE + " - DODAJ MOTOR DO WYPOZYCZALNI");
        infoPrinter.printInfo(PRINT_INFO_ABOUT_CARS + " - WYŚWIETL INFORMACJE O AUTACH");
        infoPrinter.printInfo(PRINT_INFO_ABOUT_MOTORBIKES + " - WYŚWIETL INFORMACJE O MOTORACH");
        infoPrinter.printInfo(EXPORT_TO_CSV + " - EKSPORTUJ DO PLIKU TEKSTOWEGO");
        infoPrinter.printInfo(REMOVE_CAR + " - USUŃ SAMOCHÓD Z BAZY");
        infoPrinter.printInfo(REMOVE_MOTORBIKE + " - USUŃ MOTOCYKL Z BAZY");
        infoPrinter.printInfo(BORROW_CAR + " - WYPOŻYCZ AUTO");
        infoPrinter.printInfo(BORROW_MOTORBIKE + " - WYPOŻYCZ MOTOCYKL");
        infoPrinter.printInfo(RECEIVE_CAR + " - PRZYJMIJ ZWRÓCONY SAMOCHÓD");
        infoPrinter.printInfo(RECEIVE_MOTORBIKE + " - PRZYJMIJ ZWRÓCONY MOTOCYKL");
    }
    private void printMotorbikeSpecificOptions() {
        int allMotorbikes = 1;
        int borrowers = 0;
        infoPrinter.printInfo(borrowers + " - INFORMACJĘ O POZYCZAJĄCYCH");
        infoPrinter.printInfo(allMotorbikes + " - INFORMACJĘ O WSZYSTKICH MOTORACH");
    }

    private void printCarSpecificOptions() {
        int allCars = 1;
        int borrowers = 0;
        infoPrinter.printInfo(borrowers + " - INFORMACJĘ O POZYCZAJĄCYCH");
        infoPrinter.printInfo(allCars + " - INFORMACJĘ O WSZYSTKICH SAMOCHODACH");

    }

    public void exit() {
        try {
            serializableManager.exportVehicleData(garage);
            serializableManager.exportPersonData(personBase);
            infoPrinter.printInfo("Dane exportowane");
        } catch (ExportDataException e) {
            infoPrinter.printInfo(e.getMessage());
        }
        dataReader.closeScanner();
        System.out.println("Koniec programu, dzięki.");
    }

}
