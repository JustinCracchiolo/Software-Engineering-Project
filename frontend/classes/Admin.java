package classes;

public class Admin extends User {
    private String AdminId;

    public Admin(String username, String password) {
        super(username, password, "", "Admin"); // email is not needed for clients, so we can set it to an empty string

    }
}
