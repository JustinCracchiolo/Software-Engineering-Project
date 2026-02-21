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
import classes.Admin;


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

       JPanel titlePanel = new JPanel();
       JLabel title = new JLabel("All Users", SwingConstants.CENTER); 
       
       add(Box.createVerticalStrut(10));
       title.setFont(new Font("Arial", Font.BOLD, 26)); 
       add(title); 
       
       // Panel that will hold all user entries 
       listPanel = new JPanel(); 
       listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS)); 
       listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       
       // Make it scrollable 
       JScrollPane scroll = new JScrollPane(listPanel); 
       scroll.setBorder(BorderFactory.createEmptyBorder());

       add(scroll, BorderLayout.CENTER); 
       
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
        userCard.setLayout(new BoxLayout(userCard, BoxLayout.Y_AXIS));
        //userCard.setLayout(new GridLayout(0, 1)); 
        userCard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        userCard.add(new JLabel("Name: " + u.getUsername())); 
        userCard.add(Box.createVerticalStrut(5));
        userCard.add(new JLabel("Email: " + u.getEmail())); 
        userCard.add(Box.createVerticalStrut(5));
        userCard.add(new JLabel("User Type: " + u.getUserType())); 
        userCard.add(Box.createVerticalStrut(5));
        userCard.add(new JLabel("User ID: " + u.getUserId())); 
        userCard.add(Box.createVerticalStrut(5));
        userCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); 

        if(u.getUserType().equals("Owner")) {
            userCard.add(new JLabel("Owner Id:" + ((Owner)u).getOwnerId()));
            userCard.add(new JLabel("Vehicles:"));
            for(Vehicle v: ((Owner)u).getVehicles()) {
                userCard.add(new JLabel(" Make: " + v.getMake() + " Model " + v.getModel() + " VIN " + v.getNumber() 
                + " License Plate " + v.getLicensePlate() + " Year: " + v.getYear() + " Approximate parked time: " + v.approxTime()));
            }
        }
        else if (u.getUserType().equals("Client")) {
            userCard.add(new JLabel("Client Id:" + ((Client)u).getClientId()));
            userCard.add(new JLabel("Jobs"));
            for(Job j: ((Client)u).getClientJobs()) {
                userCard.add(new JLabel("Job Id: " + j.getJobId() + " Job duration: " + j.getApproximateJobDuration() + 
                " Deadline: " + j.getJobDeadline() + " Description: " + j.getJobDescription()));
            }
        }
        else {
            userCard.add(new JLabel("Admin Id:" + ((Admin)u).getAdminId()));
        }

        userCard.setBackground(new Color(80, 80, 120));
        userCard.setOpaque(true);

        return userCard; 
    }
    
}
