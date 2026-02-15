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
import javax.swing.*;

public class OfferVehiclePage extends JPanel {

    private static final String OUTPUT_FILE = "transactions.txt";
    private static final DateTimeFormatter TS_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public OfferVehiclePage(JPanel cards) {
        setLayout(new BorderLayout());

        // Top NavBar
        add(new NavBar(cards), BorderLayout.NORTH);

        // Main Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Offer Vehicle Information"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField ownerIdField = new JTextField(20);
        JTextField makeField = new JTextField(20);
        JTextField modelField = new JTextField(20);
        JTextField yearField = new JTextField(20);
        JTextField colorField = new JTextField(20);
        JTextField vinField = new JTextField(20);
        JTextField plateField = new JTextField(20);
        JTextField residencyField = new JTextField(20);

        addRow(formPanel, gbc, 0, "Owner ID:", ownerIdField);
        addRow(formPanel, gbc, 1, "Make:", makeField);
        addRow(formPanel, gbc, 2, "Model:", modelField);
        addRow(formPanel, gbc, 3, "Year:", yearField);
        addRow(formPanel, gbc, 4, "Color:", colorField);
        addRow(formPanel, gbc, 5, "VIN:", vinField);
        addRow(formPanel, gbc, 6, "License Plate:", plateField);
        addRow(formPanel, gbc, 7, "Approx Residency Time:", residencyField);

        // Status box
        JTextArea statusArea = new JTextArea(5, 40);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Status"));

        // Buttons
        JButton submitBtn = new JButton("Submit");
        JButton clearBtn = new JButton("Clear");

        submitBtn.addActionListener(e -> {
            if (isBlank(ownerIdField.getText()) ||
                isBlank(makeField.getText()) ||
                isBlank(modelField.getText()) ||
                isBlank(yearField.getText()) ||
                isBlank(residencyField.getText())) {

                statusArea.append("Please fill all required fields.\n");
                return;
            }

            String timestamp = LocalDateTime.now().format(TS_FORMAT);

            String record =
                    "--------------------------------\n" +
                    "[" + timestamp + "]\n" +
                    "User Type: OWNER\n" +
                    "Owner ID: " + ownerIdField.getText().trim() + "\n" +
                    "Make: " + makeField.getText().trim() + "\n" +
                    "Model: " + modelField.getText().trim() + "\n" +
                    "Year: " + yearField.getText().trim() + "\n" +
                    "Color: " + colorField.getText().trim() + "\n" +
                    "VIN: " + vinField.getText().trim() + "\n" +
                    "License Plate: " + plateField.getText().trim() + "\n" +
                    "Residency Time: " + residencyField.getText().trim() + "\n";

            if (appendToFile(record)) {
                statusArea.append("Vehicle successfully submitted.\n");
                clearFields(ownerIdField, makeField, modelField, yearField,
                            colorField, vinField, plateField, residencyField);
            } else {
                statusArea.append("Error writing to file.\n");
            }
        });

        clearBtn.addActionListener(e ->
            clearFields(ownerIdField, makeField, modelField, yearField,
                        colorField, vinField, plateField, residencyField)
        );

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearBtn);
        buttonPanel.add(submitBtn);

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.add(formPanel, BorderLayout.CENTER);
        centerWrapper.add(buttonPanel, BorderLayout.SOUTH);

        add(centerWrapper, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private static void addRow(JPanel panel, GridBagConstraints gbc, int row,
                               String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    private static boolean appendToFile(String record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
            bw.write(record);
            bw.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
