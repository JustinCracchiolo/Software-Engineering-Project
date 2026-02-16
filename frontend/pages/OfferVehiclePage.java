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
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import classes.User;

public class OfferVehiclePage extends JPanel {

    //Saving/Loading information from transactions.txt.
    private static final String OUTPUT_FILE = "frontend/transactions.txt";
    private static final DateTimeFormatter TS_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //Look up vehicles by VIN.
    private final Map<String, Vehicle> VEHICLES_BY_VIN = new LinkedHashMap<>();

    private final JTextArea STATUS_AREA = new JTextArea(6, 50);

    public OfferVehiclePage(JPanel cards, User user) {
        setLayout(new BorderLayout());

        //NavBar
        add(new NavBar(cards, user), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel("Offer Vehicle Menu");
        header.setFont(new Font("Arial", Font.BOLD, 26));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Buttons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton addBtn = new JButton("Add Vehicle");
        JButton removeBtn = new JButton("Remove Vehicle");
        JButton editBtn = new JButton("Edit Vehicle Info");
        JButton viewBtn = new JButton("View Vehicles");

        addBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        editBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        viewBtn.setFont(new Font("Arial", Font.PLAIN, 16));

        //buttons and actions
        buttonRow.add(addBtn);
        addBtn.addActionListener(e -> onAddVehicle());
        buttonRow.add(removeBtn);
        removeBtn.addActionListener(e -> onRemoveVehicle());
        buttonRow.add(editBtn);
        editBtn.addActionListener(e -> onEditVehicle());
        buttonRow.add(viewBtn);
        viewBtn.addActionListener(e -> onViewVehicles());
        

        //Status
        STATUS_AREA.setEditable(false);
        STATUS_AREA.setLineWrap(true);
        STATUS_AREA.setWrapStyleWord(true);
        JScrollPane statusScroll = new JScrollPane(STATUS_AREA);
        statusScroll.setBorder(BorderFactory.createTitledBorder("Status"));

  

        //Distances
        center.add(header);
        center.add(Box.createVerticalStrut(10));
        center.add(buttonRow);
        center.add(Box.createVerticalStrut(10));
        center.add(statusScroll);

        add(center, BorderLayout.CENTER);
    }

    //Button Actions

    private void onAddVehicle() {
        VehicleForm form = new VehicleForm(null);

        int result = JOptionPane.showConfirmDialog(
                this,
                form.panel,
                "Add Vehicle",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        //cancel message
        if (result != JOptionPane.OK_OPTION) {
            logStatus("Add Vehicle cancelled.");
            return;
        }

        Vehicle v = form.toVehicle();
        if (v == null) return; // validation already handled with message

        if (VEHICLES_BY_VIN.containsKey(v.vin)) {
            JOptionPane.showMessageDialog(this,
                    "A vehicle with this VIN already exists.\nUse Edit Vehicle Info instead.",
                    "Duplicate VIN",
                    JOptionPane.WARNING_MESSAGE);
            logStatus("Add failed: duplicate VIN " + v.vin);
            return;
        }

        VEHICLES_BY_VIN.put(v.vin, v);
        logStatus("Vehicle added: " + v.make + " " + v.model + " (" + v.year + "), VIN " + v.vin);

        appendToFile(buildRecord("ADD_VEHICLE", v));
    }

    private void onRemoveVehicle() {
        //no vehicle on table
        if (VEHICLES_BY_VIN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No vehicles to remove.", "Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String vin = JOptionPane.showInputDialog(this, "Enter VIN to remove:");
        //cancel message
        if (vin == null) {
            logStatus("Remove Vehicle cancelled.");
            return;
        }

        vin = vin.trim();
        //incomplete info
        if (vin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "VIN is required.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehicle removed = VEHICLES_BY_VIN.remove(vin);
        //incorrect info
        if (removed == null) {
            JOptionPane.showMessageDialog(this, "No vehicle found for VIN: " + vin, "Not Found", JOptionPane.WARNING_MESSAGE);
            logStatus("Remove failed: VIN not found " + vin);
            return;
        }

        logStatus("Vehicle removed: VIN " + vin);
        appendToFile(buildRecord("REMOVE_VEHICLE", removed));
    }

    private void onEditVehicle() {
        //no data in table
        if (VEHICLES_BY_VIN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No vehicles to edit.", "Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String vin = JOptionPane.showInputDialog(this, "Enter VIN to edit:");
        //cancel message
        if (vin == null) {
            logStatus("Edit Vehicle cancelled.");
            return;
        }

        vin = vin.trim();
        //incomplete request
        if (vin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "VIN is required.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Vehicle existing = VEHICLES_BY_VIN.get(vin);
        //incorrect request
        if (existing == null) {
            JOptionPane.showMessageDialog(this, "No vehicle found for VIN: " + vin, "Not Found", JOptionPane.WARNING_MESSAGE);
            logStatus("Edit failed: VIN not found " + vin);
            return;
        }

        //prefill form with existing vehicle
        VehicleForm form = new VehicleForm(existing);

        //Opens the panel for edit
        int result = JOptionPane.showConfirmDialog(
                this,
                form.panel,
                "Edit Vehicle Info (VIN locked)",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        //cancel message
        if (result != JOptionPane.OK_OPTION) {
            logStatus("Edit cancelled for VIN " + vin);
            return;
        }

        Vehicle updated = form.toVehicle();
        if (updated == null) return;

        //Updating while maintaining the VIN
        updated.vin = vin;
        VEHICLES_BY_VIN.put(vin, updated);

        logStatus("Vehicle updated: VIN " + vin);
        appendToFile(buildRecord("EDIT_VEHICLE", updated));
    }

    private void onViewVehicles() {
        //no data on the table
        if (VEHICLES_BY_VIN.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No vehicles available.", "Empty", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //table column names
        String[] cols = {"Owner ID", "Make", "Model", "Year", "Color", "VIN", "License Plate", "Residency Time"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (Vehicle v : VEHICLES_BY_VIN.values()) {
            model.addRow(new Object[]{
                    v.ownerId,
                    v.make,
                    v.model,
                    v.year,
                    v.color,
                    v.vin,
                    v.licensePlate,
                    v.residencyTime
            });
        }

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(900, 300));

        JOptionPane.showMessageDialog(this, pane, "Current Vehicles", JOptionPane.INFORMATION_MESSAGE);

        //log message
        logStatus("Viewed vehicles (" + VEHICLES_BY_VIN.size() + " total).");
        appendToFile(buildViewRecord());
    }

    //log + space
    private void logStatus(String msg) {
        STATUS_AREA.append(msg + "\n");
    }

    //write
    private static boolean appendToFile(String record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
            bw.write(record);
            bw.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static String nowTs() {
        return LocalDateTime.now().format(TS_FORMAT);
    }

    private static String buildRecord(String action, Vehicle v) {
        return "--------------------------------\n" +
                "[" + nowTs() + "]\n" +
                "Action: " + action + "\n" +
                "User Type: OWNER\n" +
                "Owner ID: " + v.ownerId + "\n" +
                "Make: " + v.make + "\n" +
                "Model: " + v.model + "\n" +
                "Year: " + v.year + "\n" +
                "Color: " + v.color + "\n" +
                "VIN: " + v.vin + "\n" +
                "License Plate: " + v.licensePlate + "\n" +
                "Approx Residency Time: " + v.residencyTime + "\n";
    }

    private String buildViewRecord() {
        return "--------------------------------\n" +
                "[" + nowTs() + "]\n" +
                "Action: VIEW_VEHICLES\n" +
                "User Type: OWNER\n" +
                "Vehicle Count: " + VEHICLES_BY_VIN.size() + "\n";
    }

    //constructors
    private static class Vehicle {
        String ownerId;
        String make;
        String model;
        String year;
        String color;
        String vin; // unique key
        String licensePlate;
        String residencyTime;

        Vehicle(String ownerId, String make, String model, String year, String color,
                String vin, String licensePlate, String residencyTime) {
            this.ownerId = ownerId;
            this.make = make;
            this.model = model;
            this.year = year;
            this.color = color;
            this.vin = vin;
            this.licensePlate = licensePlate;
            this.residencyTime = residencyTime;
        }
    }


    private class VehicleForm {
        JPanel panel = new JPanel(new GridBagLayout());

        JTextField ownerId = new JTextField(20);
        JTextField make = new JTextField(20);
        JTextField model = new JTextField(20);
        JTextField year = new JTextField(20);
        JTextField color = new JTextField(20);
        JTextField vin = new JTextField(20);
        JTextField plate = new JTextField(20);
        JTextField residency = new JTextField(20);

        VehicleForm(Vehicle existing) {
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(6, 6, 6, 6);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            addRow(panel, gbc, 0, "Owner ID (required):", ownerId);
            addRow(panel, gbc, 1, "Make (required):", make);
            addRow(panel, gbc, 2, "Model (required):", model);
            addRow(panel, gbc, 3, "Year (required):", year);
            addRow(panel, gbc, 4, "Color (optional):", color);
            addRow(panel, gbc, 5, "VIN (required):", vin);
            addRow(panel, gbc, 6, "License Plate (optional):", plate); //optional since VIN is the key
            addRow(panel, gbc, 7, "Approx Residency Time (required):", residency);

            //checking to see duplicates
            if (existing != null) {
                ownerId.setText(existing.ownerId);
                make.setText(existing.make);
                model.setText(existing.model);
                year.setText(existing.year);
                color.setText(existing.color);
                vin.setText(existing.vin);
                plate.setText(existing.licensePlate);
                residency.setText(existing.residencyTime);

                //Vin editing disable
                vin.setEnabled(false);
                vin.setToolTipText("VIN cannot be edited.");
            }
        }

        Vehicle toVehicle() {
            String ownerIdVal = ownerId.getText().trim();
            String makeVal = make.getText().trim();
            String modelVal = model.getText().trim();
            String yearVal = year.getText().trim();
            String colorVal = color.getText().trim();
            String vinVal = vin.getText().trim();
            String plateVal = plate.getText().trim();
            String residencyVal = residency.getText().trim();

            //required info check
            if (ownerIdVal.isEmpty() || makeVal.isEmpty() || modelVal.isEmpty()
                    || yearVal.isEmpty() || vinVal.isEmpty() || residencyVal.isEmpty()) {
                JOptionPane.showMessageDialog(OfferVehiclePage.this,
                        "Please fill all required fields.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE);
                return null;
            }

            //correct year check
            if (!yearVal.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(OfferVehiclePage.this,
                        "Year must be a 4-digit number (e.g., 2020).",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE);
                return null;
            }

            return new Vehicle(ownerIdVal, makeVal, modelVal, yearVal, colorVal, vinVal, plateVal, residencyVal);
        }
    }

    private static void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }
}
