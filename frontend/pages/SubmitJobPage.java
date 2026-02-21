/* Project: Vehicular Cloud Real Time System (VCRTS)
* Class: SubmitJobPage.java
* Authors: Group 2 (Justin Cracchiolo, Lauren Rodriguez, David Choi, Tristan Huertas, Ivan Lin, Anthony Vallejo, Sebastian Villavicencio)
* Date: February 2026
* This program builds and controls the main Swing GUI for VCRTS, including login, registration, and home navigation using CardLayout. 
* It connects to UserManager to support user registration and login.
*/

package pages;

import classes.Admin;
import classes.Job;
import classes.PlaceHolderTextField;
import classes.User;
import classes.UserManager;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;


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

    private JTextField jobDescription;
    private JTextField jobDuration;
    private JTextField jobDeadline;

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

        jobDescription = new PlaceHolderTextField("Job Description", 16); // adds more graphics to regular textfield
        jobDescription.setMaximumSize(jobDescription.getPreferredSize());
        jobDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        jobDuration = new PlaceHolderTextField("Approximate Job Duration (in terms of hours)", 25); // adds more graphics to regular textfield
        jobDuration.setMaximumSize(jobDuration.getPreferredSize());
        jobDuration.setAlignmentX(Component.CENTER_ALIGNMENT);

        jobDeadline = new PlaceHolderTextField("Deadline (yyyy-mm-dd hh:mm:ss)", 20); // adds more graphics to regular textfield
        jobDeadline.setMaximumSize(jobDeadline.getPreferredSize());
        jobDeadline.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        jobForm.add(Box.createVerticalGlue());
        jobForm.add(jobTitle);
        jobForm.add(Box.createVerticalStrut(30)); // creates padding between elements
        jobForm.add(jobDescription);
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
            String idText = jobDescription.getText().trim();
            String durationText = jobDuration.getText().trim();
            String deadlineText = jobDeadline.getText().trim();

            if(idText.isEmpty() || durationText.isEmpty() || deadlineText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty.");
                return;
            }

            double duration; 
            try { 
                duration = Double.parseDouble(durationText); 
                if (duration <= 0) { 
                    JOptionPane.showMessageDialog(this, "Duration must be a positive number."); 
                    return; 
                } 
            } 
            catch (NumberFormatException ex) { 
                JOptionPane.showMessageDialog(this, "Duration must be an integer or decimal."); 
                return; 
            }

            LocalDateTime deadline; 
            try { 
                deadline = LocalDateTime.parse(deadlineText, DEADLINE_FORMAT); 
            } 
            catch (Exception ex) { 
                JOptionPane.showMessageDialog(this, "Deadline must be in the format: yyyy-mm-dd hh:mm:ss\nExample: 2024-03-09 17:45:00"); 
                return; 
            }


            /* 
            //gets an admin account
            User admin = null;
            Map<String, User> allUsers = users.getAllUsers();
            for(User u: allUsers.values()) {
                if(u.getUserType().equals("Admin")) {
                    admin = u;
                    break;
                }
            }
            if (admin == null) {
                JOptionPane.showMessageDialog(this, "No admin account found.");
                return;
            }
            */

            //make new vehicle from form information
            Job j = new Job(idText, duration, deadline);
            
            //((Owner) user).addJob(v);
            Admin.addPendingJob(user, j, true);

            JOptionPane.showMessageDialog(this, "Submitted job application.");

            refresh();
        });
    }

    // ---------------------------------------------------------------
    // refreshes submit job page
    @Override
    public void refresh() {
        jobDeadline.setText("");
        jobDuration.setText("");
        jobDescription.setText("");
    }

}



