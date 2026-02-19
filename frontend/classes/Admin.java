/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Admin.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, and Sebastian Villavicencio)
 * Date: February 2026
 * The Admin class represents a user who can moderate and manage the system. 
 */

package classes;
import java.util.ArrayList;

public class Admin extends User {
private ArrayList <Vehicle> pendingVehicles;
private ArrayList <Job> pendingJobs;

public Admin (String username, String password, String email) {
super(username, password, email, "Admin");
        this.pendingVehicles = new ArrayList<>();
        this.pendingJobs = new ArrayList<>();
}
   
}
