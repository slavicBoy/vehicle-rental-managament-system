package io.file;

import exception.ExportDataException;
import exception.ImportDataException;
import model.Garage;
import model.PersonBase;

import java.io.*;

public class SerializableManager {
    private static final String GARAGE_FILE = "Garage.txt";
    private static final String PERSON_FILE = "PersonDataBase.txt";
    public Garage importDataVehicle() {
        try (var fis = new FileInputStream(GARAGE_FILE);
             var ois = new ObjectInputStream(fis)) {
            return (Garage) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new ImportDataException("Błąd importu, brak pliku!");
        } catch (IOException e) {
            throw new ImportDataException("Błąd zapisu danych do pliku");
        } catch (ClassNotFoundException e) {
            throw new ImportDataException("Błąd zapisu danych do pliku");
        }
    }
    public PersonBase importDataPerson() {
        try (var fis = new FileInputStream(PERSON_FILE);
             var ois = new ObjectInputStream(fis)) {
            return (PersonBase) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new ImportDataException("Błąd importu, brak pliku!");
        } catch (IOException e) {
            throw new ImportDataException("Błąd zapisu danych do pliku");
        } catch (ClassNotFoundException e) {
            throw new ImportDataException("Błąd zapisu danych do pliku");
        }
    }
    public void exportVehicleData(Garage garage) {
        try (var fos = new FileOutputStream(GARAGE_FILE);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(garage);
        } catch (IOException e) {
            throw new ExportDataException("Błąd eksportu danych.");
        }
    }
    public void exportPersonData(PersonBase personBase) {
        try (var fos = new FileOutputStream(PERSON_FILE);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(personBase);
        } catch (IOException e) {
            throw new ExportDataException("Błąd eksportu danych.");
        }

    }
}
