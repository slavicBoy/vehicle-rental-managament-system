package model;

import java.util.Objects;

public class Car extends Vehicle{
    private int numberOfDoors;
    private boolean isWheelOnLeftSide;

    public Car(String brand, String model, double mileage, int year, String kindOfFuel, String color, int numberOfDoors, boolean isWheelOnLeftSide) {
        super(brand, model, mileage, year, kindOfFuel, color);
        this.numberOfDoors = numberOfDoors;
        this.isWheelOnLeftSide = isWheelOnLeftSide;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isWheelOnLeftSide() {
        return isWheelOnLeftSide;
    }

    public void setWheelOnLeftSide(boolean wheelOnLeftSide) {
        isWheelOnLeftSide = wheelOnLeftSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return numberOfDoors == car.numberOfDoors &&
                isWheelOnLeftSide == car.isWheelOnLeftSide;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfDoors, isWheelOnLeftSide);
    }

    @Override
    public String toString() {
        return super.toString() +
                " ilość drzwi: " + numberOfDoors +
                " kierownica po lewej stronie: " + isWheelOnLeftSide + "\n" + "_________________";
    }
    public String toCsv(){
        return super.toCsv() + "liczba drzwi " + numberOfDoors + ";" + "kierownica po lewej? " + isWheelOnLeftSide;
    }
}