/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Vehicle.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
 * Date: February 2026
 * The Vehicle class represents a registered vehicle in the VCRTS system. It stores vehicle identification information including VIN number,
 * make, model, and license plate. The class validates that all required vehicle information is provided during object creation.
 */
package classes;
//Focus on the backend and make necessary classes to store information for the user and their vehicles (Sebastian)

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

// ---------------------------------------------------------------
// vehicle class
//The system shall receive and verify the vehicle's number, license plate, make, and model.
public class Vehicle {
    private final String VIN_NUMBER;
    private String make;
    private String model;
    private String licensePlate;
    
    private String year;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Duration duration;
    private String apporxResidencyTime; // in hrs

    // constructor
    // ---------------------------------------------------------------
    // add user as parameter in constructor

    public Vehicle(String VIN_NUMBER, String make, String model, String licensePlate, String year, String arrive, String depart) {
        this.VIN_NUMBER = VIN_NUMBER;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;

        LocalDateTime arrival = LocalDateTime.parse(arrive, formatter);
        LocalDateTime departure = LocalDateTime.parse(depart, formatter);
        duration = Duration.between(arrival, departure);
        int approx_hrs = (int) duration.toHours();
        this.apporxResidencyTime = Integer.toString(approx_hrs);


        // make sure vehicle has all the necessary information
        if (VIN_NUMBER.equals("") || make == null || model == null || licensePlate == null 
        || year == null || apporxResidencyTime == null) {
            throw new IllegalArgumentException("Vehicle information is incomplete");
        }
    }

    //overloading from reading from vehicle file
    public Vehicle(String VIN_NUMBER, String make, String model, String licensePlate, String year, String approxTime) {
        this.VIN_NUMBER = VIN_NUMBER;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;
        this.apporxResidencyTime = approxTime;

    }

    // implementing the getters in order to access the private variables

    // ---------------------------------------------------------------
    // returns the VIN number of the vehicle
    public String getNumber() {
        return VIN_NUMBER;
    }

    // ---------------------------------------------------------------
    // returns the make of the vehicle
    public String getMake() {
        return make;
    }

    // ---------------------------------------------------------------
    // returns the model of the vehicle
    public String getModel() {
        return model;
    }

    // ---------------------------------------------------------------
    // returns the license plate of the vehicle
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getYear() {
        return year;
    }

    public String approxTime() {
        return apporxResidencyTime;
    }

}
