
package classes;
//Focus on the backend and make necessary classes to store information for the user and their vehicles (Sebastian)

// vehicle class
//The system shall receive and verify the vehicle's number, license plate, make, and model.

public class Vehicle { 
    private final String vinNumber;
    private String make;
    private String model;
    private String licensePlate;


    //constructor 
    public Vehicle(String vinNumber, String make, String model , String licensePlate) {
        this.vinNumber = vinNumber;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;

    
    //make sure vehicle has all the necessary information
    if(vinNumber.equals("") || make == null || model == null || licensePlate == null){
        throw new IllegalArgumentException("Vehicle information is incomplete");
    }
    }

    //implementing the getters in order to access the private variables

    public String getNumber() {
        return vinNumber;
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
