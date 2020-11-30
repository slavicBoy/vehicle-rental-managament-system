package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Person implements Serializable {
   private LocalDate localDate;
   private String firstname;
   private String lastName;
   private String PESEL;

    public Person(String firstname, String lastName, String PESEL) {
        this.localDate = LocalDate.now();
        this.firstname = firstname;
        this.lastName = lastName;
        this.PESEL = PESEL;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(localDate, person.localDate) &&
                Objects.equals(firstname, person.firstname) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(PESEL, person.PESEL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate, firstname, lastName, PESEL);
    }

    @Override
    public String toString() {
        return "Osoba: Imię " + firstname + " nazwisko " + lastName
        + " PESEL " + PESEL + " Data wypożyczenia pojazdu " + localDate.getDayOfMonth() + " / " + localDate.getMonth() + " / " + localDate.getYear() + "r";
    }
}
