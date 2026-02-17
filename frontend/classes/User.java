/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: User.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
 * Date: February 2026
 * The User class represents a general system user in the VCRTS system. It stores authentication-related information including username,
 * password, and email. This class acts as a base class for more specific user types such as Owner and Client.
 */
package classes;

import java.util.ArrayList;

//this class represents a user of the system, with a username and password. It is used by UserManager to store user data in memory and persist it to a file. The Owner class extends User to add additional fields for car owners.

public class User {
    private static int increment = 1000;
    private String userId;
    private String username;
    private String password;
    private String email;

    private String userType;

    private ArrayList<Vehicle> user_vehciles = new ArrayList<>();

    public User(String username, String password, String email, String userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        increment++;
        this.userId = Integer.toString(increment);

        this.userType = userType;

        //read vehicle file for when user is loaded into the system
        // readUserVehicles()
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public void addUserVehicles(Vehicle v) {
        user_vehciles.add(v);
    }

    public ArrayList<Vehicle> getUserVehicles() {
        return user_vehciles;
    }

    public String getUserType() {
        return userType;
    }
}
