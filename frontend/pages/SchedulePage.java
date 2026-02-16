/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: SchedulePage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program builds and controls the main Swing GUI for VCRTS, including login, registration, and home navigation using CardLayout. 
* It connects to UserManager to support user registration and login.
*/

package pages;

import javax.swing.*;
import java.awt.*;
import classes.User;

public class SchedulePage extends JPanel {
    
    public SchedulePage(JPanel cards, User user) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Schedule Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        add(label, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(new NavBar(cards, user), BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
    }
}
