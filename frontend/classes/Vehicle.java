/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Vehicle.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
 * Date: February 2026
 * The Vehicle class represents a registered vehicle in the VCRTS system. It stores vehicle identification information including VIN number,
 * make, model, and license plate. The class validates that all required vehicle information is provided during object creation.
 */
package classes;
//Focus on the backend and make necessary classes to store information for the user and their vehicles (Sebastian)

// vehicle class
//The system shall receive and verify the vehicle's number, license plate, make, and model.

public class Vehicle { 
    private final String VIN_NUMBER;
    private String make;
    private String model;
    private String licensePlate;


    //constructor 
    public Vehicle(String VIN_NUMBER, String make, String model , String licensePlate) {
        this.VIN_NUMBER = VIN_NUMBER;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;

    
    //make sure vehicle has all the necessary information
    if(VIN_NUMBER.equals("") || make == null || model == null || licensePlate == null){
        throw new IllegalArgumentException("Vehicle information is incomplete");
    }
    }

    //implementing the getters in order to access the private variables

    public String getNumber() {
        return VIN_NUMBER;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    

}
