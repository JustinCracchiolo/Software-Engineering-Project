/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: HomePage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program controls the home page of the VCRTS system.
*/


package pages;

import javax.swing.*;

import classes.User;
import classes.UserManager;

import java.awt.*;

public class HomePage extends JPanel {

    public HomePage(JPanel cards, User user) {

        OfferVehiclePage offerVehiclePage = new OfferVehiclePage(cards, user);
        SchedulePage schedulePage = new SchedulePage(cards, user);
        SubmitJobPage submitJobPage = new SubmitJobPage(cards, user);
        Settings settingsPage = new Settings(cards, user);

        cards.add(offerVehiclePage, "offerVehicle");
        cards.add(schedulePage, "schedule");
        cards.add(submitJobPage, "submitJob");
        cards.add(settingsPage, "settings");

        setLayout(new BorderLayout());
        JLabel label = new JLabel("Home Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        String username = user.getUsername();

        JLabel name = new JLabel(username, SwingConstants.CENTER);
        name.setFont(new Font("Arial", Font.BOLD, 24));

        setLayout(new BorderLayout());
        add(new NavBar(cards, user), BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
        add(name, BorderLayout.CENTER);
    }
    
}
