/* Project: VCRTS
Class: UI.java
Author: Justin, Athony, Sebastian, Lauren, Ivan, Tristan, David
Date: Spring 2026 Semester
This class controls the UI for the project.
*/

import classes.User;
import classes.UserManager;
import classes.PlaceHolderTextField;
import classes.PlaceHolderTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;


public class UI {
    public static User currentUser; //once a person logs in, this holds all information for that user
    
    public static void main(String[] args) {
        // create UI when invoked 
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    //------------------------------------

    private static void createAndShowGUI() {

        //Possible place for loadUsersFromFile

        //management tool for keeping track of who creates accounts. 
        UserManager userManager = new UserManager();

        JFrame frame = new JFrame("VCRTS App"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(1000, 800); 

        // CardLayout drives screen switching between Login/Home/Register.
        JPanel cards = new JPanel(new CardLayout());
        
        // Creation of different screens
        JPanel loginPage = new JPanel(new BorderLayout());
        JPanel homePage = new JPanel(new BorderLayout());
        JPanel registerPage = new JPanel(new BorderLayout());

        cards.add(loginPage, "login"); 
        cards.add(homePage, "home");
        cards.add(registerPage, "register");

        //--------------------------------------------

        // Login page

        JPanel loginSidePanel = new JPanel();
        loginSidePanel.setBackground(new Color(65, 105, 255 ));
        loginSidePanel.setLayout(new BoxLayout(loginSidePanel, BoxLayout.Y_AXIS)); // center everything vertically
        loginSidePanel.setAlignmentX(Component.CENTER_ALIGNMENT); //center horizontally
        loginSidePanel.setPreferredSize(new Dimension(500, 800));
        
        JPanel loginPanel = new JPanel(); 
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.setPreferredSize(new Dimension(600, 800));

        JLabel loginLabel = new JLabel("Sign In");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        loginLabel.setForeground(new Color(65, 105, 255 ));  
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 40));

        JTextField usernameTextField = new PlaceHolderTextField("Username", 20); //adds more graphics to regular textfield
        usernameTextField.setMaximumSize(usernameTextField.getPreferredSize()); 
        usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT); 
    

        JTextField passwordTextField = new PlaceHolderTextField("Password", 20); 
        passwordTextField.setMaximumSize(passwordTextField.getPreferredSize()); 
        passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT); 
                
        //Login button
        JButton loginButton = new JButton("Login"); 
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
        loginButton.setBackground(new Color(65, 105, 255 )); 
        loginButton.setForeground(Color.white);
        loginButton.setOpaque(true); 
        loginButton.setBorderPainted(false);
        
        //Register button
        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 30));

        //--------------------------------------------
        //Temp button to skip login process
        JButton skipButton = new JButton("Skip Login");
        skipButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        skipButton.setFont(new Font("Arial", Font.PLAIN, 30));

        skipButton.addActionListener(e -> { 
            CardLayout cl = (CardLayout) cards.getLayout(); 
            cl.show(cards, "home");
        });

        //--------------------------------------------
       
        // Validate credentials before switching to Home.
        loginButton.addActionListener(e -> { 
            String username = usernameTextField.getText().trim();
            String password = passwordTextField.getText();
           
            if (userManager.login(username, password)) {
                currentUser = userManager.getUser(username); //this tells the program the person who is logged in    
                CardLayout cl = (CardLayout) cards.getLayout(); 
                cl.show(cards, "home");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });

        // Go to register screen.
        registerButton.addActionListener(e -> {
            meetAndSwitch(cards, loginPage, loginSidePanel, loginPanel, "register");
           
            /*  
             CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "register");
            */
        });
        
        //Title on sidebar
        JLabel title_label = new JLabel("VCRTS");
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        title_label.setForeground(Color.white);  
        title_label.setFont(new Font("Arial", Font.PLAIN, 50));


        //Add all of the elements

        loginPanel.add(Box.createVerticalGlue()); 
        loginPanel.add(loginLabel); 
        loginPanel.add(Box.createVerticalStrut(30)); //creates padding between elements
        loginPanel.add(usernameTextField); 
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(Box.createVerticalStrut(10)); 
        loginPanel.add(passwordTextField); 
        loginPanel.add(Box.createVerticalStrut(30)); 
        loginPanel.add(loginButton); 
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(registerButton);
        loginPanel.add(Box.createVerticalGlue()); 

        //--------------------------
        // to delete
        loginPanel.add(skipButton);

        //--------------------------

        loginSidePanel.add(Box.createVerticalStrut(400)); 
        loginSidePanel.add(title_label);

        loginPage.add(loginPanel, BorderLayout.CENTER); 
        loginPage.add(loginSidePanel, BorderLayout.WEST);

        loginPanel.setOpaque(true); //allows changing color
        loginSidePanel.setOpaque(true);

        //--------------------------------------------

        // Home screen
        JPanel navbar = new JPanel(); 
        
        navbar.setBackground(new Color(30, 30, 30)); 
        navbar.setLayout(new BoxLayout(navbar, BoxLayout.X_AXIS)); 
        navbar.setPreferredSize(new Dimension(1000, 60));
        
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
    
        navbar.add(Box.createHorizontalStrut(20)); 
        navbar.add(title); 
        navbar.add(Box.createHorizontalGlue()); 
        navbar.add(homeBtn); 
        navbar.add(Box.createHorizontalStrut(10)); 
        navbar.add(scheduleBtn); 
        navbar.add(Box.createHorizontalStrut(10)); 
        navbar.add(settingsBtn); 
        navbar.add(Box.createHorizontalStrut(20));
        navbar.add(logOutBtn); 
        navbar.add(Box.createHorizontalStrut(30));


        homePage.add(navbar, BorderLayout.NORTH);
        
        //--------------------------------------------

        // Register screen
        JPanel registerSidePanel = new JPanel();
        registerSidePanel.setBackground(Color.darkGray);
        registerSidePanel.setLayout(new BoxLayout(registerSidePanel, BoxLayout.Y_AXIS));
        registerSidePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerSidePanel.setPreferredSize(new Dimension(500, 800));

        JLabel registerTitleLabel = new JLabel("VCRTS");
        registerTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerTitleLabel.setForeground(Color.white);
        registerTitleLabel.setFont(new Font("Arial", Font.PLAIN, 50));

        registerSidePanel.add(Box.createVerticalStrut(400));
        registerSidePanel.add(registerTitleLabel);

        JPanel registerPanel = new JPanel();
        registerPanel.setBackground(new Color(65, 105, 255));
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerPanel.setPreferredSize(new Dimension(600, 800));

        JLabel registerHeader = new JLabel("Register");
        registerHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerHeader.setForeground(Color.white);
        registerHeader.setFont(new Font("Arial", Font.PLAIN, 40));

        JLabel regUsernameLabel = new JLabel("Username");
        regUsernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        regUsernameLabel.setForeground(Color.white);
        regUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        JTextField regUsernameField = new JTextField(20);
        regUsernameField.setMaximumSize(regUsernameField.getPreferredSize());
        regUsernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel regPasswordLabel = new JLabel("Password");
        regPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        regPasswordLabel.setForeground(Color.white);
        regPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        JTextField regPasswordField = new JTextField(20);
        regPasswordField.setMaximumSize(regPasswordField.getPreferredSize());
        regPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton submitRegisterButton = new JButton("Create Account");
        submitRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitRegisterButton.setFont(new Font("Arial", Font.PLAIN, 30));

        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToLoginButton.setFont(new Font("Arial", Font.PLAIN, 24));

        // Create a new account in memory and return to Login.
        submitRegisterButton.addActionListener(e -> {
            String username = regUsernameField.getText().trim();
            String password = regPasswordField.getText();


            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username and password are required.");
                return;
            }
            
            if(userManager.register(username, password)) { //creates a User with username and passwords and puts them into system
                JOptionPane.showMessageDialog(frame, "User " + username + " created!");
                regUsernameField.setText("");
                regPasswordField.setText("");
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, "login");
                return;
            }
            else { //checks if someone with repeat username
                JOptionPane.showMessageDialog(frame, "Username already exists. Try again");
                regUsernameField.setText("");
                regPasswordField.setText("");
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, "register");
                return;
            }
        });

        // Return without changes.
        backToLoginButton.addActionListener(e -> {
            meetAndSwitch(cards, registerPage, registerPanel, registerSidePanel, "login");
            /* 
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "login");
            */
        });

        registerPanel.add(Box.createVerticalGlue());
        registerPanel.add(registerHeader);
        registerPanel.add(Box.createVerticalStrut(20));
        registerPanel.add(regUsernameLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(regUsernameField);
        registerPanel.add(Box.createVerticalStrut(20));
        registerPanel.add(regPasswordLabel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(regPasswordField);
        registerPanel.add(Box.createVerticalStrut(20));
        registerPanel.add(submitRegisterButton);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(backToLoginButton);
        registerPanel.add(Box.createVerticalGlue());

        registerPage.add(registerPanel, BorderLayout.CENTER);
        registerPage.add(registerSidePanel, BorderLayout.EAST);
        registerPanel.setOpaque(true);
        registerSidePanel.setOpaque(true);

        frame.add(cards);
        frame.setVisible(true);

    }
    
    //animations from login => register and register => login
    public static void meetAndSwitch(JPanel cards, JPanel currentCard, JPanel leftPanel, JPanel rightPanel, String nextCardName) {
        int halfWidth = currentCard.getWidth() / 2;
        
        // Temporarily disable layout on the CURRENT card
        currentCard.setLayout(null);
        
        // Starting positions
        leftPanel.setLocation(0, 0);
        rightPanel.setLocation(halfWidth, 0);
        
        Timer timer = new Timer(5, null);
        timer.addActionListener(e -> {
            // Move left panel right
            leftPanel.setLocation(leftPanel.getX() + 10, 0);
            
            // Move right panel left
            rightPanel.setLocation(rightPanel.getX() - 10, 0);
            
            currentCard.repaint();
            
            // Stop when they meet in the middle
            if (rightPanel.getX() <= halfWidth / 2) {
                timer.stop();

                currentCard.removeAll(); 
                currentCard.setLayout(new BorderLayout()); 
                
                // Put the panels back where they originally belonged 
                // Login page uses WEST + CENTER 
                // Register page uses CENTER + EAST 
                if (nextCardName.equals("register")) { 
                    currentCard.add(leftPanel, BorderLayout.WEST); 
                    currentCard.add(rightPanel, BorderLayout.CENTER); 
                } 
                else {
                    currentCard.add(leftPanel, BorderLayout.CENTER); 
                    currentCard.add(rightPanel, BorderLayout.EAST); 
                } 
                currentCard.revalidate(); 
                currentCard.repaint();
                
                // Switch to the next card
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, nextCardName);
            }
        });
        timer.start();
    }


}
