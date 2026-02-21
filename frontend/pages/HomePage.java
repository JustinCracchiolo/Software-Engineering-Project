/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: HomePage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program controls the home page of the VCRTS system.
*/

package pages;

import classes.Owner;
import classes.User;
import classes.UserManager;
import classes.Vehicle;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

// ---------------------------------------------------------------
public class HomePage extends JPanel implements Refreshable {

    private JLabel name; // change variable name
    private User user;

    // ---------------------------------------------------------------
    // constructor that sets the user and the user manager
    public HomePage(JPanel cards, User user, UserManager users, Map<String, Refreshable> registry) {
        // user = person logged in
        // users = every person in the system
        this.user = user;

        setLayout(new BorderLayout());
        add(new NavBar(cards, user, registry), BorderLayout.NORTH);

        JLabel label = new JLabel("Home Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        name = new JLabel("", SwingConstants.CENTER);
        name.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panel = new JPanel();
        panel.add(label, BorderLayout.CENTER);
        panel.add(name, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);

        refresh();
    }

    // ---------------------------------------------------------------
    // refreshes home page => updating the # of vehicles a user has
    @Override
    public void refresh() {
        String username = user.getUsername();
        if(user.getUserType().equals("Owner")) {
            ArrayList<Vehicle> userVehicles = ((Owner) user).getVehicles();
            int vehicles = 0;
            for (Vehicle v : userVehicles) {
                vehicles++;
            }
            name.setText(username + " vehicles: " + vehicles);
        }
        else {
            name.setText(username);
        }

        revalidate();
        repaint();
    }

}
