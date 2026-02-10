/* 
Front end for the project
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicUI {
    public static void main(String[] args) {
        // create UI when invoked 
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        
        JFrame frame = new JFrame("Login Screen"); //frame for the GUI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Create a label
        JLabel label = new JLabel("This is a login screen!", SwingConstants.CENTER);
        frame.add(label, BorderLayout.CENTER); //add a label to the frame at the center of the frame

        // Create a button
        JButton button = new JButton("Login");

        //when button is pressed do something
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Button clicked!");
            }
        });

        // add button to the GUI
        frame.add(button, BorderLayout.SOUTH);

        // window visibility
        frame.setVisible(true);
    }
}
