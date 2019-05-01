import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class Vivlo {

    /**
     * Create the choice.
     */
    public Vivlo() {
        createChoiceFrame();
    }

    /**
     * Initialize the contents of the create choice frame.
     */
    private static void createChoiceFrame() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame choice = new JFrame();
                choice.setTitle("Vivlo - Choice");
                choice.setBounds(100, 100, 400, 200);
                choice.setBackground(new Color(247, 247, 247));
                choice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                choice.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

                JButton btnBooks = new JButton("Books");
                choice.getContentPane().add(btnBooks);

                JButton btnManagement = new JButton("Management");
                btnManagement.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createManagementFrame();
                        choice.dispose();
                    }
                });
                choice.getContentPane().add(btnManagement);
                choice.setVisible(true);
            }
        });
    }

    /**
     * Initialize the contents of the create management frame.
     */
    private static void createManagementFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Something");
                JFrame management = new JFrame();
                management.setTitle("Vivlo - Management");
                management.setBounds(100, 100, 400, 400);
                management.setBackground(new Color(247, 247, 247));
                management.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                management.getContentPane().setLayout(new GridLayout(6, 2, 10, 10));
                JButton btnBack = new JButton("Back");
                management.add((btnBack));
                management.add(new JLabel());
                management.add((new JLabel("Total Free Computers: ")));
                management.add((new JLabel("30")));                             // Change this value with database
                management.add((new JLabel("Quiet Floors: ")));
                management.add((new JLabel("29")));                             // Change this value with database
                management.add((new JLabel("Number of Book Holds: ")));
                management.add((new JLabel("28")));                             // Change this value with database
                management.add((new JLabel("Number of Open Study Rooms: ")));
                management.add((new JLabel("27")));                             // Change this value with database
                management.add((new JLabel("Number of Student Workers: ")));
                management.add((new JLabel("26")));                             // Change this value with database
                btnBack.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createChoiceFrame();
                        management.dispose();
                    }
                });
                management.setVisible(true);
            }
        });
    }
}
