package classes;

import java.util.HashMap;
import java.util.Map;

/* 
This class holds a hashmap of usernames, and Users (another class)
If someone registers with a username and password it creates a map with that persons username and an new User object for that person
When someone tries to login it checks if the person has an accout, and checks for the right password 
*/

public class UserManager {
    private Map<String, User> users = new HashMap<>(); //username => User


    public boolean register(String username, String password) {
        if(users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        return true;
    }

    public boolean login(String username, String password) {
        User user = users.get(username); //gets user data from username
        if(user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    public User getUser(String username) {
        return users.get(username);
    }
    
}
