/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: SubmitJobPage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program builds and controls the main Swing GUI for VCRTS, including login, registration, and home navigation using CardLayout. 
* It connects to UserManager to support user registration and login.
*/

package pages;

import javax.swing.*;
import java.awt.*;

public class SubmitJobPage extends JPanel {
    public SubmitJobPage(JPanel cards) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Submit Job Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        add(label, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(new NavBar(cards), BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
    }
    
}
