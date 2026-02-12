/* 
Front end for the project
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/* 
JFrame references

JButton button = new JButton("Button"); 
JLabel label = new JLabel("Label"); 
JTextField textField = new JTextField(12); 
JTextArea textArea = new JTextArea(4, 12);
JCheckBox checkBox = new JCheckBox("Check"); JRadioButton radioButton = new JRadioButton("Option"); 

*/

public class UI {
    public static void main(String[] args) {
        // create UI when invoked 
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
    //------------------------------------

    private static void createAndShowGUI() {
        
        // Simple in-memory user store for demo login/register.
        Map<String, User> users = new HashMap<>();
        
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

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color. darkGray);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS)); // center everything vertically
        sidePanel.setAlignmentX(Component.CENTER_ALIGNMENT); //center horizontally
        sidePanel.setPreferredSize(new Dimension(500, 800));
        
        JPanel loginPanel = new JPanel(); 
        loginPanel.setBackground(new Color(65, 105, 255 ));
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.setPreferredSize(new Dimension(600, 800));


        //Enter a username
        JLabel username_label = new JLabel("Username"); 
        username_label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        username_label.setForeground(Color.white);  
        username_label.setFont(new Font("Arial", Font.PLAIN, 40));


        JTextField usernameTextField = new JTextField(20);
        usernameTextField.setMaximumSize(usernameTextField.getPreferredSize()); 
        usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT); 
    

        //Enter a password
        JLabel password_label = new JLabel("Password");
        password_label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        password_label.setForeground(Color.white);  
        password_label.setFont(new Font("Arial", Font.PLAIN, 40));

        JTextField passwordTextField = new JTextField(20); 
        passwordTextField.setMaximumSize(passwordTextField.getPreferredSize()); 
        passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT); 
                
        //Login button
        JButton loginButton = new JButton("Login"); 
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 30));
        
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
            User user = users.get(username);
            if (user != null && user.password.equals(password)) {
                CardLayout cl = (CardLayout) cards.getLayout(); 
                cl.show(cards, "home");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });

        // Go to register screen.
        registerButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "register");
        });
        
        //Title on sidebar
        JLabel title_label = new JLabel("VCRTS");
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        title_label.setForeground(Color.white);  
        title_label.setFont(new Font("Arial", Font.PLAIN, 50));


        //Add all of the elements

        loginPanel.add(Box.createVerticalGlue()); 
        loginPanel.add(username_label); 
        loginPanel.add(Box.createVerticalStrut(10)); //creates padding between elements
        loginPanel.add(usernameTextField); 
        loginPanel.add(Box.createVerticalStrut(20)); 
        loginPanel.add(password_label); 
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

        sidePanel.add(Box.createVerticalStrut(400)); 
        sidePanel.add(title_label);

        loginPage.add(loginPanel, BorderLayout.CENTER); 
        loginPage.add(sidePanel, BorderLayout.WEST);

        loginPanel.setOpaque(true); //allows changing color
        sidePanel.setOpaque(true);

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

            if (users.containsKey(username)) {
                JOptionPane.showMessageDialog(frame, "Username already exists.");
                return;
            }

            users.put(username, new User(password));

            regUsernameField.setText("");
            regPasswordField.setText("");


            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "login");
        });

        // Return without changes.
        backToLoginButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "login");
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
        registerPage.add(registerSidePanel, BorderLayout.WEST);
        registerPanel.setOpaque(true);
        registerSidePanel.setOpaque(true);

        frame.add(cards);
        frame.setVisible(true);

    }

    // Simple user record for demo purposes.
    private static class User {
        private final String password;

        private User(String password) {
            this.password = password;

        }
    }
}
