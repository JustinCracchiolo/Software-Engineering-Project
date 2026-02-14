package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores users in memory and persists them to a tab-separated file.
 * Usernames are normalized for lookup (trimmed, lowercase).
 */

public class UserManager {

    private Map<String, User> users = new HashMap<>(); //username => User
    private final Path usersFilePath;
    //------------------------------

    // Uses the default users file path under UserInfo/users.txt. 
    // calls other constructor
    public UserManager() {
        this(Paths.get("UserInfo", "users.txt"));
    }
    //------------------------------

    // Loads users from the provided file path. 
    public UserManager(Path usersFilePath) {
        this.usersFilePath = usersFilePath;
        loadUsersFromFile();
    }
    //------------------------------

    /** 
     * @param username: this is the users usersname when they make an account
     * @param password: this is the users password when they make an account
     * @param Username undergoes normalization 
     * @see User is put into the system 
     * @return boolean: force person to register again or put them to login screen
     */

    public boolean register(String username, String password) {
        String normalizedUsername = normalizeUsername(username);
        if(users.containsKey(normalizedUsername)) {
            return false;
        }
        users.put(normalizedUsername, new User(username, password,""));
        addUserToFile(username, password);
        users.put(normalizedUsername, new User(username, password, "")); //added to UserManager hashmap of all users
        addUserToFile(username, password); //added to the txt file
        return true;
    }   

    // Validates credentials against the in-memory store. 
    //------------------------------

    /** 
     * @param username: this is the users username from the login screen
     * @param password: this is the users password from the login screen
     * @param Username undergoes normalization 
     * @see User information is looked up in hashmap from UserManager
     * @return boolean: allows user into application if username and password are valid
     */
    public boolean login(String username, String password) {
        String normalizedUsername = normalizeUsername(username);
        User user = users.get(normalizedUsername); //gets user data from username
        if(user == null) {
            return false;
        }
        return user.getPassword().equals(password); //checks that password is correct
    }
    //------------------------------

    /** 
     * @param username: this is the users username
     * @param Username undergoes normalization 
     * @see User information is looked up in hashmap from UserManager
     * @return boolean: User information from User class
     */
    public User getUser(String username) {
        String normalizedUsername = normalizeUsername(username);
        return users.get(normalizedUsername); //get user object from hashamp
    }
    //------------------------------

    /** 
     * Read all user informaton from users.txt
     * Put all their information in the hashmap. Keeps registrations after application closed
     */
    private void loadUsersFromFile() {
        if (!Files.exists(usersFilePath)) {
            return;
        }

        //try reading it by line and using next()
        try (BufferedReader reader = Files.newBufferedReader(usersFilePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                int sepIndex = trimmed.indexOf('\t');
                if (sepIndex <= 0) {
                    continue;
                }
                String username = trimmed.substring(0, sepIndex);
                String password = trimmed.substring(sepIndex + 1);
                String normalizedUsername = normalizeUsername(username);
                if (!username.isEmpty() && !users.containsKey(normalizedUsername)) {
                    users.put(normalizedUsername, new User(username, password, ""));
                }
            }
        } catch (IOException e) {
            // If the file is unreadable, continue with an empty in-memory list.
        }
        
    }
    //------------------------------

    /** 
     * @param username: from the user when they register
     * @param password: from the user when they register
     * Put their information into the txt file
     */
    private void addUserToFile(String username, String password) {
        try {
            Path parent = usersFilePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            try (BufferedWriter writer = Files.newBufferedWriter(
                    usersFilePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            )) {
                writer.write(username + "\t" + password);
                writer.newLine();
            }
        } catch (IOException e) {
            // If the write fails, keep the user in memory for this session.
        }
    }
    //------------------------------

    //Make Username no longer case sensitive and trim space.
    private String normalizeUsername(String username) {
        if (username == null) {
            return "";
        }
        return username.trim().toLowerCase();
    }
    //------------------------------
    
}
