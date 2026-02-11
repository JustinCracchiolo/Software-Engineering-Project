/* 
Front end for the project
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* 
JFrame references

JButton button = new JButton("Button"); 
JLabel label = new JLabel("Label"); 
JTextField textField = new JTextField(12); 
JTextArea textArea = new JTextArea(4, 12);
JCheckBox checkBox = new JCheckBox("Check"); JRadioButton radioButton = new JRadioButton("Option"); 

*/

public class BasicUI {
    public static void main(String[] args) {
        // create UI when invoked 
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        
        JFrame frame = new JFrame("VCRTS App"); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(1000, 800); 

        JPanel cards = new JPanel(new CardLayout()); //allows us to switch to different screens
        
        JPanel loginPage = new JPanel(new BorderLayout());
        JPanel homePage = new JPanel(new BorderLayout());

        cards.add(loginPage, "login"); 
        cards.add(homePage, "home");
        //---------------------------------------------------------

        //Login page

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

        //
        //

        //Enter a username
        JLabel username_label = new JLabel("Username"); 
        username_label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        username_label.setForeground(Color.white);  // text color
        username_label.setFont(new Font("Arial", Font.PLAIN, 40));


        JTextField usernameTextField = new JTextField(20);
        usernameTextField.setMaximumSize(usernameTextField.getPreferredSize()); 
        usernameTextField.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        //
        // 

        //Enter a password
        JLabel password_label = new JLabel("Password");
        password_label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        password_label.setForeground(Color.white);  // text color
        password_label.setFont(new Font("Arial", Font.PLAIN, 40));

        JTextField passwordTextField = new JTextField(20); 
        passwordTextField.setMaximumSize(passwordTextField.getPreferredSize()); 
        passwordTextField.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        //
        //
        
        //Login button
        JButton loginButton = new JButton("Login"); 
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 30));
       
        //switch to home page
        loginButton.addActionListener(e -> { 
            CardLayout cl = (CardLayout) cards.getLayout(); 
            cl.show(cards, "home"); 
        });

        //
        //
        
        //Title on sidebar
        JLabel title_label = new JLabel("VCRTS");
        title_label.setAlignmentX(Component.CENTER_ALIGNMENT); 
        title_label.setForeground(Color.white);  // text color
        title_label.setFont(new Font("Arial", Font.PLAIN, 50));

        //
        //

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
        loginPanel.add(Box.createVerticalGlue()); 

        sidePanel.add(Box.createVerticalStrut(400)); 
        sidePanel.add(title_label);

        loginPage.add(loginPanel, BorderLayout.CENTER); 
        loginPage.add(sidePanel, BorderLayout.WEST);

        loginPanel.setOpaque(true); //allows changing color
        sidePanel.setOpaque(true);

        //-------------------------------------------

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
       
        navbar.add(Box.createHorizontalStrut(20)); 
        navbar.add(title); 
        navbar.add(Box.createHorizontalGlue()); 
        navbar.add(homeBtn); 
        navbar.add(Box.createHorizontalStrut(10)); 
        navbar.add(settingsBtn); 
        navbar.add(Box.createHorizontalStrut(20));

        homePage.add(navbar, BorderLayout.NORTH);
        //-------------------------------------------
        frame.add(cards);
        frame.setVisible(true);

       

    }
}
