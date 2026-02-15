/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: Settings.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program controls the settings page of the VCRTS system.
*/

package pages;

import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {
    
    public Settings(JPanel cards) {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Settings Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        add(label, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(new NavBar(cards), BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
    }
}