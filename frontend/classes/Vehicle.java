
package classes;
//Focus on the backend and make necessary classes to store information for the user and their vehicles (Sebastian)

// vehicle class
//The system shall receive and verify the vehicle's number, license plate, make, and model.

public class Vehicle { 
    private int number;
    private String make;
    private String model;
    private String licensePlate;


    public Vehicle(int number, String make, String model , String licensePlate) {
        this.number = number;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;

        //make sure vehicle has all the necessary information
    if(number == 0 || make == null || model == null || licensePlate == null){
        throw new IllegalArgumentException("Vehicle information is incomplete");
    }
    }

    //implementing the getters in order to access the private variables

    public int getNumber() {
        return number;
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