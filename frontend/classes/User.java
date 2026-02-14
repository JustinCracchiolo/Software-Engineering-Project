package classes;

//this class represents a user of the system, with a username and password. It is used by UserManager to store user data in memory and persist it to a file. The Owner class extends User to add additional fields for car owners.

public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
}
