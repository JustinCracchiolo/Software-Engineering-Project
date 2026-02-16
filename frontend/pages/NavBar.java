/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: NavBar.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program controls the navigation bar at the top of the screen.
*/
package pages;

import javax.swing.*;
import java.awt.*;
import classes.User;

public class NavBar extends JPanel{
    public NavBar(JPanel cards, User user) {
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(1000, 60));

        JLabel title = new JLabel("VCRTS");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JButton homeBtn = new JButton("Home");
        JButton scheduleBtn = new JButton("Schedule");
        JButton offerVehicleBtn = new JButton("Offer Vehicle");
        JButton submitJobBtn = new JButton("Submit Job");
        JButton logoutBtn = new JButton("Log Out");
        JButton settingsBtn = new JButton("Settings");

        homeBtn.addActionListener(e ->
            ((CardLayout) cards.getLayout()).show(cards, "home")
        );

        scheduleBtn.addActionListener(e ->
            ((CardLayout) cards.getLayout()).show(cards, "schedule")
        );

        offerVehicleBtn.addActionListener(e ->
            ((CardLayout) cards.getLayout()).show(cards, "offerVehicle")
        );

        submitJobBtn.addActionListener(e ->
            ((CardLayout) cards.getLayout()).show(cards, "submitJob")
        );

        logoutBtn.addActionListener(e ->
            ((CardLayout) cards.getLayout()).show(cards, "login")
        );

        settingsBtn.addActionListener(e ->
            ((CardLayout) cards.getLayout()).show(cards, "settings")
        );

        add(Box.createHorizontalStrut(20));
        add(title);
        add(Box.createHorizontalGlue());
        add(homeBtn);
        add(Box.createHorizontalStrut(10));
        add(scheduleBtn);
        add(Box.createHorizontalStrut(10));
        add(offerVehicleBtn);
        add(Box.createHorizontalStrut(10));
        add(submitJobBtn);
        add(Box.createHorizontalStrut(10));
        add(settingsBtn);
        add(Box.createHorizontalStrut(10));
        add(logoutBtn);
        add(Box.createHorizontalStrut(30));
    }
    
}

/*
        // Home screen
        // JPanel navbar = new JPanel(); 
        
        //navbar.setBackground(new Color(30, 30, 30)); 
        //navbar.setLayout(new BoxLayout(navbar, BoxLayout.X_AXIS)); 
        //navbar.setPreferredSize(new Dimension(1000, 60));
        
        JLabel title = new JLabel("VCRTS"); 
        title.setForeground(Color.WHITE); 
        title.setFont(new Font("Arial", Font.BOLD, 28)); 
        
        JButton homeBtn = new JButton("Home"); 
        JButton settingsBtn = new JButton("Settings"); 
        JButton logOutBtn = new JButton("Log Out"); //logout button

        logOutBtn.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "login");
        });
        
        JButton scheduleBtn = new JButton("Schedule");
        JButton offerVehicleBtn = new JButton("Offer Vehicle");
        JButton submitJobBtn = new JButton("Submit Job");
    
        navbar.add(Box.createHorizontalStrut(20)); 
        navbar.add(title); 
        navbar.add(Box.createHorizontalGlue()); 
        navbar.add(homeBtn); 
        navbar.add(Box.createHorizontalStrut(10)); 
        navbar.add(scheduleBtn); 
        navbar.add(Box.createHorizontalStrut(10)); 
        navbar.add(offerVehicleBtn); 
        navbar.add(Box.createHorizontalStrut(10));
        navbar.add(submitJobBtn); 
        navbar.add(Box.createHorizontalStrut(10));
        navbar.add(settingsBtn); 
        navbar.add(Box.createHorizontalStrut(20));
        navbar.add(logOutBtn); 
        navbar.add(Box.createHorizontalStrut(30));

        // homePage.add(navbar, BorderLayout.NORTH);
        // placeholder to test functionality of each page
        // homePage.add(new JLabel("Home Page TEST", SwingConstants.CENTER), BorderLayout.CENTER);
        //offerVehiclePage.add(new JLabel("Offer Vehicle Form (Owner)", SwingConstants.CENTER), BorderLayout.CENTER);
        //submitJobPage.add(new JLabel("Submit Job Form (Client)", SwingConstants.CENTER), BorderLayout.CENTER);
        //schedulePage.add(new JLabel("Schedule Form (Client)", SwingConstants.CENTER), BorderLayout.CENTER);
        
 */
