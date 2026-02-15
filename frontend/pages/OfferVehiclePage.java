/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: OfferVehiclePage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program controls the offer vehicle page of the VCRTS system. This page allows
* a user to: offer, remove, edit, and view their vehicles.
*/

package pages;

import javax.swing.*;
import java.awt.*;

public class OfferVehiclePage extends JPanel {

    public OfferVehiclePage(JPanel cards) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Offer Vehicle Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        add(label, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(new NavBar(cards), BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
    }
    
}
