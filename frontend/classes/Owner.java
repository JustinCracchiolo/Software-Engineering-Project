package classes;
//this class represents a user of the system(specifically owner)

import java.util.ArrayList;

//The Owner class extends User to add additional fields for car owners, such as a list of their vehicles and their approximate residency time. 
// It also includes methods to add vehicles and retrieve the owner's information.
public class Owner extends User {
     private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private int approximateResidencyTime;

    //constructor for the owner class; utilizes the constructor of the User class to set the username and password.
    public Owner(String username, String password, int approximateResidencyTime) {
        super(username, password);
        this.approximateResidencyTime = approximateResidencyTime;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public int getApproximateResidencyTime() {
        return approximateResidencyTime;
    }
    


}

