package pages;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

import classes.Owner;
import classes.Client;
import classes.Vehicle;
import classes.Job;


import classes.User;
import classes.UserManager;

public class AdminHome extends JPanel implements Refreshable {

    private User user;
    private UserManager users;
    private JPanel listPanel;

    public AdminHome(JPanel cards, User user, UserManager users, Map<String, Refreshable> registry) {
        // user = person logged in
        // users = every person in the system
        this.user = user;
        this.users = users;

        setLayout(new BorderLayout());
        add(new NavBar(cards, user, registry), BorderLayout.NORTH);

       JLabel title = new JLabel("All Users", SwingConstants.CENTER); 
       
       add(Box.createVerticalStrut(10));
       title.setFont(new Font("Arial", Font.BOLD, 26)); 
       add(title); 
       
       // Panel that will hold all user entries 
       listPanel = new JPanel(); 
       listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); 
       
       // Make it scrollable 
       JScrollPane scroll = new JScrollPane(listPanel); add(scroll, BorderLayout.CENTER); 
       
       refresh();
    }

    @Override 
    public void refresh() { 
        listPanel.removeAll(); 
        // clear old content 
        
        for (User u : users.getAllUsers().values()) { 
            listPanel.add(createUserCard(u)); 
        } 

        listPanel.revalidate(); 
        listPanel.repaint(); 
    }

    private JPanel createUserCard (User u) { 
        JPanel userCard = new JPanel(); 
        userCard.setLayout(new GridLayout(0, 1)); 
        userCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        userCard.add(new JLabel("Name: " + u.getUsername())); 
        userCard.add(new JLabel("Email: " + u.getEmail())); 
        userCard.add(new JLabel("User Tyoe: " + u.getUserType())); 
        userCard.add(new JLabel("ID: " + u.getUserId())); 
        userCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 

        if(u.getUserType().equals("Owner")) {
            userCard.add(new JLabel("Vehicle:"));
            for(Vehicle v: ((Owner)u).getVehicles()) {
                userCard.add(new JLabel(" • " + v.getMake() + " " + v.getModel() + " • " + v.getNumber() + " • " + v.getLicensePlate()));
            }
        }
        else if (u.getUserType().equals("Client")) {
            userCard.add(new JLabel("Jobs"));
            for(Job j: ((Client)u).getClientJobs()) {
                userCard.add(new JLabel(" • " + j.getJobId() + " • " + j.getApproximateJobDuration() + " • " + j.getJobDeadline()));
            }
        }

        return userCard; 
    }
    
}
