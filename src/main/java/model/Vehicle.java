package model;

import java.io.Serializable;
import java.util.Objects;

abstract public class Vehicle implements Serializable, Comparable<Vehicle> {
    private String brand;
    private String model;
    private double mileage;
    private int year;
    private String kindOfFuel;
    private String color;
    private boolean borrowed;

    public Vehicle(String brand, String model, double mileage, int year, String kindOfFuel, String color) {
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.year = year;
        this.kindOfFuel = kindOfFuel;
        this.color = color;
    }

    public boolean getBorrowed() {
        return borrowed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getKindOfFuel() {
        return kindOfFuel;
    }

    public void setKindOfFuel(String kindOfFuel) {
        this.kindOfFuel = kindOfFuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(vehicle.mileage, mileage) == 0 &&
                year == vehicle.year &&
                Objects.equals(brand, vehicle.brand) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(kindOfFuel, vehicle.kindOfFuel) &&
                Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, mileage, year, kindOfFuel, color);
    }

    @Override
    public String toString() {
        return
                "Marka: " + brand +
                " Model pojazdu: " + model +
                " Przebieg pojazdu: " + mileage + "/ 100 KM " +
                " Rok produkcji: " + year +
                " Rodzaj paliwa: " + kindOfFuel  +
                " Kolor: " + color  +
                " wypożyczony?: "  + borrowed;

    }

    public String toCsv() {
        return "Marka " + brand + ";" + "model " + model + ";" + "przebieg " + mileage + ";" +
                "rok produkcji " + year + ";" + "paliwo " + kindOfFuel + ";" + "kolor " + color + ";" + "wypożyczony " + borrowed + ";";
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.brand.toLowerCase().compareTo(o.brand.toLowerCase());
    }


}
