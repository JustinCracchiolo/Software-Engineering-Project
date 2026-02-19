/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: OfferVehiclePage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program controls the offer vehicle page of the VCRTS system. This page allows
* a user to: offer, remove, edit, and view their vehicles.
*/

package pages;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import classes.Admin;
import classes.UserManager;

import classes.Owner;
import classes.PlaceHolderTextField;
import classes.User;
import classes.Vehicle;

// ---------------------------------------------------------------
// class that controls the offer vehicle page
public class OfferVehiclePage extends JPanel implements Refreshable {

    // Saving/Loading information from transactions.txt.
    private static final String OUTPUT_FILE = "frontend/transactions.txt";
    private static final DateTimeFormatter TS_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Look up vehicles by VIN.
    private final Map<String, Vehicle> VEHICLES_BY_VIN = new LinkedHashMap<>();

    private final JTextArea STATUS_AREA = new JTextArea(6, 50);

    private JLabel vehicleLabel;
    private JTextField vehicleVin;
    private JTextField vehicleMake;
    private JTextField vehicleModel;
    private JTextField vehiclePlate;


    // ---------------------------------------------------------------
    // constructor: sets user + user manager + registry
    public OfferVehiclePage(JPanel cards, User user, Map<String, Refreshable> registry, UserManager users) {
        setLayout(new BorderLayout());

        // NavBar
        add(new NavBar(cards, user, registry), BorderLayout.NORTH);


        JPanel splitPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(65, 105, 255));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel leftTitle = new JLabel("Offer Vehicle");
        leftTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftTitle.setForeground(Color.WHITE);
        leftTitle.setFont(new Font("Arial", Font.PLAIN, 50));

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(leftTitle);
        leftPanel.add(Box.createVerticalGlue());

        JPanel vehicleForm = new JPanel();
        vehicleForm.setBackground(Color.WHITE);
        vehicleForm.setLayout(new BoxLayout(vehicleForm, BoxLayout.Y_AXIS)); // center everything vertically

        vehicleLabel = new JLabel("Enter vehicle information");
        vehicleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        vehicleLabel.setForeground(new Color(65, 105, 255));
        vehicleLabel.setFont(new Font("Arial", Font.PLAIN, 36));

        vehicleVin = new PlaceHolderTextField("Vin", 16); // adds more graphics to regular textfield
        vehicleVin.setMaximumSize(vehicleVin.getPreferredSize());
        vehicleVin.setAlignmentX(Component.CENTER_ALIGNMENT);

        vehicleMake = new PlaceHolderTextField("Make", 16); // adds more graphics to regular textfield
        vehicleMake.setMaximumSize(vehicleMake.getPreferredSize());
        vehicleMake.setAlignmentX(Component.CENTER_ALIGNMENT);

        vehicleModel = new PlaceHolderTextField("Model", 16); // adds more graphics to regular textfield
        vehicleModel.setMaximumSize(vehicleModel.getPreferredSize());
        vehicleModel.setAlignmentX(Component.CENTER_ALIGNMENT);

        vehiclePlate = new PlaceHolderTextField("License Plate", 16);
        vehiclePlate.setMaximumSize(vehiclePlate.getPreferredSize());
        vehiclePlate.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        vehicleForm.add(Box.createVerticalGlue());
        vehicleForm.add(vehicleLabel);
        vehicleForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        vehicleForm.add(vehicleVin);
        vehicleForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        vehicleForm.add(vehicleMake);
        vehicleForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        vehicleForm.add(vehicleModel);
        vehicleForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        vehicleForm.add(vehiclePlate);
        vehicleForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        vehicleForm.add(submitBtn);
        vehicleForm.add(Box.createVerticalGlue());

        splitPanel.add(leftPanel);
        splitPanel.add(vehicleForm);
        add(splitPanel, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> {
            String VIN_NUMBER = vehicleVin.getText().trim();
            String make = vehicleMake.getText().trim();
            String model = vehicleModel.getText().trim();
            String licensePlate = vehiclePlate.getText();

            if(VIN_NUMBER.isEmpty() || make.isEmpty() || model.isEmpty() || licensePlate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
                return;
            }

            //gets an admin account
            User admin = null;
            Map<String, User> all_users = users.getAllUsers();
            for(User u: all_users.values()) {
                if(u.getUserType().equals("Admin")) {
                    admin = u;
                    break;
                }
            }

            if (admin == null) { JOptionPane.showMessageDialog(this, "No admin account found."); return; }

            //make new vehicle from form information
            Vehicle v = new Vehicle(VIN_NUMBER, make, model, licensePlate);
            
            //((Owner) user).addVehicle(v);
            ((Admin)admin).addPendingVehicle(user, v);

            JOptionPane.showMessageDialog(this, "Succesfully sent a vehicle request.");
            refresh();
        });

    }

    // ---------------------------------------------------------------
    // refreshes offer vehicle page
    @Override
    public void refresh() {
        vehicleLabel.setText("");
        vehicleVin.setText("");
        vehicleMake.setText("");
        vehicleModel.setText("");
        vehiclePlate.setText("");
    }
}
