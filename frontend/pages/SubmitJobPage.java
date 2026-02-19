/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: SubmitJobPage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program builds and controls the main Swing GUI for VCRTS, including login, registration, and home navigation using CardLayout. 
* It connects to UserManager to support user registration and login.
*/

package pages;

import classes.Job;
import classes.PlaceHolderTextField;
import classes.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import classes.UserManager;
import classes.Admin;


// ---------------------------------------------------------------
// Submit Job Page
public class SubmitJobPage extends JPanel implements Refreshable {

    // Saving/Loading information from transactions.txt.
    private static final String OUTPUT_FILE = "frontend/transactions.txt";
    private static final DateTimeFormatter DEADLINE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Look up vehicles by JobID.
    private final Map<String, Job> JOB_BY_JOBID = new LinkedHashMap<>();
    private final JTextArea STATUS_AREA = new JTextArea(6, 50);

    private final User user;

    private JLabel clientIdLabel;
    private JTextField durationField;
    private JTextField deadlineField;
    private JTextField jobDescField;

    // ---------------------------------------------------------------
       // ---------------------------------------------------------------
    // constructor: sets user + user manager + registry
    public SubmitJobPage(JPanel cards, User user, Map<String, Refreshable> registry, UserManager users) {
        setLayout(new BorderLayout());
        this.user = user;

        // NavBar
        add(new NavBar(cards, user, registry), BorderLayout.NORTH);

        JPanel splitPanel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(65, 105, 255));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel leftTitle = new JLabel("Submit Job");
        leftTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftTitle.setForeground(Color.WHITE);
        leftTitle.setFont(new Font("Arial", Font.PLAIN, 50));

        leftPanel.add(Box.createVerticalGlue());
        leftPanel.add(leftTitle);
        leftPanel.add(Box.createVerticalGlue());

        JPanel jobForm = new JPanel();
        jobForm.setBackground(Color.WHITE);
        jobForm.setLayout(new BoxLayout(jobForm, BoxLayout.Y_AXIS)); // center everything vertically

        JLabel jobTitle = new JLabel("Enter Job information");
        jobTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        jobTitle.setForeground(new Color(65, 105, 255));
        jobTitle.setFont(new Font("Arial", Font.PLAIN, 36));

        JTextField jobID = new PlaceHolderTextField("Job ID", 16); // adds more graphics to regular textfield
        jobID.setMaximumSize(jobID.getPreferredSize());
        jobID.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField jobDuration = new PlaceHolderTextField("Approximate Job Duration", 16); // adds more graphics to regular textfield
        jobDuration.setMaximumSize(jobDuration.getPreferredSize());
        jobDuration.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField jobDeadline = new PlaceHolderTextField("Deadline", 20); // adds more graphics to regular textfield
        jobDeadline.setMaximumSize(jobDeadline.getPreferredSize());
        jobDeadline.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        jobForm.add(Box.createVerticalGlue());
        jobForm.add(jobTitle);
        jobForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        jobForm.add(jobID);
        jobForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        jobForm.add(jobDuration);
        jobForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        jobForm.add(jobDeadline);
        jobForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        jobForm.add(submitBtn);
        jobForm.add(Box.createVerticalGlue());

        splitPanel.add(leftPanel);
        splitPanel.add(jobForm);
        add(splitPanel, BorderLayout.CENTER);

        submitBtn.addActionListener(e -> {
            String idText = jobID.getText();
            String durationText = jobDuration.getText();
            String deadlineText = jobDeadline.getText();

            //gets an admin account
            User admin = null;
            Map<String, User> all_users = users.getAllUsers();
            for(User u: all_users.values()) {
                if(u.getUserType().equals("Admin")) {
                    admin = u;
                    break;
                }
            }
            if (admin == null) {
                JOptionPane.showMessageDialog(this, "No admin account found.");
                return;
            }

            //make new vehicle from form information
            Job j = new Job(idText, Integer.parseInt(durationText), LocalDateTime.parse(deadlineText, DEADLINE_FORMAT));
            
            //((Owner) user).addJob(v);
            ((Admin)admin).addPendingJob(user, j);
        });
    }

    // ---------------------------------------------------------------
    // refreshes submit job page
    @Override
    public void refresh() {
    }

}


/*
// constructor that sets the cards, user, and registry
    public SubmitJobPage(JPanel cards, User user, Map<String, Refreshable> registry) {
        this.user = user;

        setLayout(new BorderLayout());
        add(new NavBar(cards, user, registry), BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel header = new JLabel("Submit Job Page", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        content.add(header, BorderLayout.NORTH);
        content.add(buildForm(), BorderLayout.CENTER);
        
         // ---------------------------------------------------------------
         status.setEditable(false);
         status.setLineWrap(true);
         status.setWrapStyleWord(true);
         JScrollPane scrollPane = new JScrollPane(status);
         scrollPane.setBorder(BorderFactory.createTitledBorder("Status"));
         content.add(scrollPane, BorderLayout.SOUTH);

         add(content, BorderLayout.CENTER);
         refresh();
    }
    // ---------------------------------------------------------------
    // builds the panel where the form is located
    private JPanel buildForm() {
        JPanel form = new JPanel(new GridLayout(4, 2));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        clientIdLabel = new JLabel("Client ID:");
        form.add(clientIdLabel);
        durationField = new PlaceHolderTextField("Duration", 10);
        form.add(durationField);
        deadlineField = new PlaceHolderTextField("Deadline", 10);
        form.add(deadlineField);
        jobDescField = new PlaceHolderTextField("Job Description", 20);
        form.add(jobDescField);

        JButton submitBtn = new JButton("Submit");
        form.add(submitBtn);

        return form;
    }
 */


   
    

 
