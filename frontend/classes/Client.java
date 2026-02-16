/* Project: Vehicular Cloud Real Time System (VCRTS)
 * Class: Client.java
 * Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, and Sebastian Villavicencio)
 * Date: February 2026
 * The Client class represents a consumer in the VCRTS system who submits computational jobs. It stores the client's ID, approximate job duration,
 * and job deadline, and extends the User class to inherit authentication properties such as username and password.
 */

package classes;

public class Client extends User { // utilizes the User class to set client username and password

    private String clientId;

    //constructor for the client class; utilizes the constructor of the User class to set the username and password, and also sets the clientId.
    public Client(String username, String password, String clientId) {
        super(username, password, "");//email is not needed for clients, so we can set it to an empty string
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

}
