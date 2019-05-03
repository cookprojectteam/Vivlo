
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

    public void createSearchFrame() {
        JFrame frame = new JFrame("Vivlo - Search");
        JLabel JL_fname,JL_lname,JL_title,JL_isbn, JL_dept;
        JTextField JT_fname,JT_lname,JT_title,JT_isbn;
        JComboBox JCB_dept;
        JButton btn_search;
        JL_isbn = new JLabel("Enter ISBN:");
        JL_isbn.setBounds(20, 20, 200, 20);
        JT_isbn = new JTextField(20);
        JT_isbn.setBounds(130, 20, 150, 20);
        btn_search = new JButton("Search");
        btn_search.setBounds(300, 20, 80, 20);
        btn_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbnNum = JT_isbn.getText();
                try {
                    ResultSet result = query.findBook(isbnNum);
                    if(result.next()) {
                        JOptionPane.showMessageDialog(null, "ISBN found",
                                "ISBN found", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Not a valid ISBN#",
                                "ISBN error", JOptionPane.ERROR_MESSAGE);
                        JT_isbn.setText(null);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
//				String towsonID = tuID.getText();
            }
        });
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(450,200);

        JL_fname = new JLabel("Author First Name: ");
        JL_fname.setBounds(20, 50, 100, 20);
        JT_fname = new JTextField(20);
        JT_fname.setBounds(130, 50, 150, 20);
        JL_lname = new JLabel("Author Last Name: ");
        JL_lname.setBounds(20, 80, 100, 20);
        JT_lname = new JTextField(20);
        JT_lname.setBounds(130, 80, 150, 20);
        JL_title = new JLabel("Title: ");
        JL_title.setBounds(20, 110, 100, 20);
        JT_title = new JTextField(20);
        JT_title.setBounds(130, 110, 150, 20);
        JL_dept = new JLabel("Department: ");
        JL_dept.setBounds(20,140, 100, 20);
        String dept[] = {"Administration", "Access and Outreach Services", "Content Management", "Research Instruction"};
        JCB_dept = new JComboBox(dept);
        JCB_dept.setBounds(130, 140, 150, 20);

        frame.setLayout(null);
        frame.add(btn_search);
        frame.add(JL_fname);
        frame.add(JT_fname);
        frame.add(JL_lname);
        frame.add(JT_lname);
        frame.add(JL_title);
        frame.add(JT_title);
        frame.add(JL_isbn);
        frame.add(JT_isbn);
        frame.add(JL_dept);
        frame.add(JCB_dept);
        frame.setVisible(true);
    }
}
