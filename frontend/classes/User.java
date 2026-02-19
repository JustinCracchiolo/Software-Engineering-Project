/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: User.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
 * Date: February 2026
 * The User class represents a general system user in the VCRTS system. It stores authentication-related information including username,
 * password, and email. This class acts as a base class for more specific user types such as Owner and Client.
 */
package classes;

import java.util.ArrayList;

// ---------------------------------------------------------------
//this class represents a user of the system, with a username and password. It is used by UserManager to store user data in memory and persist it to a file. The Owner class extends User to add additional fields for car owners.
public class User {
    private static int increment = 1000;
    private String userId;
    private String username;
    private String password;
    private String email;

    private String userType;

    // ---------------------------------------------------------------
    // constructor that sets the username, password, email, and user type
    public User(String username, String password, String email, String userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        increment++;
        this.userId = Integer.toString(increment);

        this.userType = userType;

        // read vehicle file for when user is loaded into the system
        // readUserVehicles()
    }

    // ---------------------------------------------------------------
    // returns the username of the user
    public String getUsername() {
        return username;
    }

    // ---------------------------------------------------------------
    // returns the password of the user
    public String getPassword() {
        return password;
    }

    // ---------------------------------------------------------------
    // returns the email of the user
    public String getEmail() {
        return email;
    }

    // ---------------------------------------------------------------
    // returns the user ID of the user
    public String getUserId() {
        return userId;
    }

    // ---------------------------------------------------------------
    // returns the user type of the user
    public String getUserType() {
        return userType;
    }
}
