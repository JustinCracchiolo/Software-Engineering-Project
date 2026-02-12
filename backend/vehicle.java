// vehicle class
//The system shall receive and verify the vehicle's number, license plate, make, and model.

public class vehicle { 
    private int number;
    private String make;
    private String model;
    private String licensePlate;

    public vehicle(int number, String make, String model , String licensePlate) {
        this.number = number;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
    }

}
