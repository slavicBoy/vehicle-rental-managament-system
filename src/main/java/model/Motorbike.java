package model;

import java.util.Objects;

public class Motorbike extends Vehicle {
    private boolean audioSystem;
    private double seatHeight;
    public Motorbike(String brand, String model, double mileage, int year, String kindOfFuel, String color, boolean audioSystem, double seatHeight) {
        super(brand, model, mileage, year, kindOfFuel, color);
        this.audioSystem = audioSystem;
        this.seatHeight = seatHeight;
    }

    public boolean isAudioSystem() {
        return audioSystem;
    }

    public void setAudioSystem(boolean audioSystem) {
        this.audioSystem = audioSystem;
    }

    public double getSeatHeight() {
        return seatHeight;
    }

    public void setSeatHeight(double seatHeight) {
        this.seatHeight = seatHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motorbike motorbike = (Motorbike) o;
        return audioSystem == motorbike.audioSystem &&
                Double.compare(motorbike.seatHeight, seatHeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), audioSystem, seatHeight);
    }

    @Override
    public String toString() {
        return super.toString()   +
                " audioSystem: " + audioSystem +
                " wysokość siodełka: " + seatHeight + " cm" + "\n" + "_________________";
    }

    @Override
    public String toCsv() {
        return super.toCsv() + ";" + "wysokość siedzenia " + seatHeight + ";"
                + "system audio " + audioSystem;
    }
}
