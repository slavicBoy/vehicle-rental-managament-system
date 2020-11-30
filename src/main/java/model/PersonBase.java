package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PersonBase implements Serializable {
    private Map<Vehicle, Person> people = new HashMap<>();

    public void addPerson(Vehicle vehicle, Person person) {
        people.put(vehicle, person);
    }

    public Map<Vehicle, Person> getBorrowedVehicles(){
        return people;
    }

    public void remove(Vehicle vehicle){
        people.remove(vehicle);
    }


}
