/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Owner.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, and Sebastian Villavicencio)
 * Date: February 2026
 * The Owner class represents a vehicle provider in the VCRTS system. It extends the User class and stores information specific to vehicle
 * owners, including their registered vehicles and approximate residency time. Owners can add vehicles to the system and manage their availability.
 */

package classes;

// ---------------------------------------------------------------
//this class represents a user of the system(specifically owner)
import java.util.ArrayList;

// ---------------------------------------------------------------
//The Owner class extends User to add additional fields for car owners, such as a list of their vehicles and their approximate residency time. 
// It also includes methods to add vehicles and retrieve the owner's information.

public class Owner extends User {
    private ArrayList<Vehicle> user_vehicles = new ArrayList<>();

    // possibly change to recieve arrival and departure time from user application
    private int approximateResidencyTime;

    // ---------------------------------------------------------------
    // constructor for the owner class; utilizes the constructor of the User class
    // to set the username and password.
    public Owner(String username, String password, int approximateResidencyTime) {
        super(username, password, "", "Owner");
        this.approximateResidencyTime = approximateResidencyTime;
    }

    // ---------------------------------------------------------------
    // adds a vehicle to the owner's list of vehicles
    public void addVehicle(Vehicle vehicle) {
        //before add, call function for admin
        user_vehicles.add(vehicle);
    }

    // ---------------------------------------------------------------
    // returns => owner's list of vehicles.
    public ArrayList<Vehicle> getVehicles() {
        return user_vehicles;
    }

    // ---------------------------------------------------------------
    // would calc difference in time object
    public int getApproximateResidencyTime() {
        return approximateResidencyTime;
    }

}
