
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vivlo {

    static final Color tuYellow = new Color(255, 187, 0);
    private Query query;

    /**
     * Create the choice.
     */
    public Vivlo() {
        query = new Query();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    createLoginFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
                choice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                GridBagConstraints gbc = new GridBagConstraints();
                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                panel.setBackground(tuYellow);

                JButton btnBooks = new JButton("Books");
                btnBooks.setPreferredSize(new Dimension(170, 40));
                btnBooks.setMargin(new Insets(10, 10, 10, 10));
                btnBooks.setBackground(Color.BLACK);
                btnBooks.setForeground(Color.WHITE);

                JButton btnManagement = new JButton("Management");
                btnManagement.setPreferredSize(new Dimension(170, 40));
                btnManagement.setMargin(new Insets(10, 10, 10, 10));
                btnManagement.setBackground(Color.BLACK);
                btnManagement.setForeground(Color.WHITE);
                btnManagement.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createManagementFrame();
                        choice.dispose();
                    }
                });

                panel.add(btnBooks, gbc);
                panel.add(btnManagement, gbc);
                choice.add(panel);
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

    /**
     * Creates the login frame
     */
    private void createLoginFrame() {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(tuYellow);
        frame.getContentPane().setLayout(null);

        JLabel TU_logo = new JLabel("");
        TU_logo.setBounds(0, 0, 545, 492);
        Image img = new ImageIcon(this.getClass().getResource("towsonu-logo.png")).getImage();
        TU_logo.setIcon(new ImageIcon(img));
        frame.getContentPane().add(TU_logo);

        JTextField tuID = new JTextField();
        tuID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tuID.setBounds(599, 265, 252, 38);
        frame.getContentPane().add(tuID);
        tuID.setColumns(10);

        JButton btnSignIn = new JButton("Sign In");
        btnSignIn.setBackground(Color.BLACK);
        btnSignIn.setForeground(Color.WHITE);
        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idNum = tuID.getText();
                try {
                    ResultSet result = query.login(idNum);
                    if(result.next()) {
                        JOptionPane.showMessageDialog(null, "Successfull Login!",
                                "Login Successful!", JOptionPane.INFORMATION_MESSAGE);
                        createChoiceFrame();
                        frame.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Not a valid TU ID#",
                                "Login Error", JOptionPane.ERROR_MESSAGE);
                        tuID.setText(null);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
//				String towsonID = tuID.getText();
            }
        });
        btnSignIn.setBounds(670, 343, 97, 25);
        frame.getContentPane().add(btnSignIn);

        JLabel lblWelcomeToVivlo = new JLabel("Welcome To Vivlo!");
        lblWelcomeToVivlo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblWelcomeToVivlo.setBounds(636, 48, 190, 25);
        frame.getContentPane().add(lblWelcomeToVivlo);

        JLabel lblLoginWithTu = new JLabel("Login with TU ID#");
        lblLoginWithTu.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblLoginWithTu.setBounds(636, 222, 179, 25);
        frame.getContentPane().add(lblLoginWithTu);
        frame.setBounds(100, 100, 915, 534);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
