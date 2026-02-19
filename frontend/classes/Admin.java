package classes;

public class Admin extends User {
    private String AdminId;

    public Admin(String username, String password) {
        this(username, password, "");
    }

    public Admin(String username, String password, String email) {
        super(username, password, email, "Admin");

    }
}
