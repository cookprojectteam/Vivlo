
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vivlo {

    static final Color tuYellow = new Color(255, 187, 0);
    private static Query query;

    /**
     * Create the choice.
     */
    public Vivlo() {
        query = new Query();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    createSearchFrame();
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
                JFrame choice = new JFrame("Vivlo - Choice");
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
                Font normal = new Font("Tahoma", Font.PLAIN, 18);
                JFrame management = new JFrame();
                management.setTitle("Vivlo - Management");
                management.setBounds(100, 100, 500, 540);
                management.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.setBackground(tuYellow);

                // Sets frame title
                JLabel managementLbl = new JLabel("Management");
                managementLbl.setBounds(130, 0, 250, 50);
                managementLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
                panel.add(managementLbl);

                // Sets number of available computers
                try {
                    ResultSet result = query.getAvailableComps();
                    while (result.next()) {
                        
                    }
                } catch (SQLException error) {
                    error.printStackTrace();
                }
                JLabel compLbl = new JLabel("Computers Available: " + "18");
                compLbl.setBounds(10, 50, 250, 50);
                compLbl.setFont(normal);
                panel.add(compLbl);

                // Sets number of books on hold
                JLabel booksLbl = new JLabel("Books on Hold: " + "83");
                booksLbl.setBounds(10, 80, 250, 50);
                booksLbl.setFont(normal);
                panel.add(booksLbl);

                // Sets number of student workers
                JLabel stuLbl = new JLabel("Student Workers: " + "18");
                stuLbl.setBounds(260, 50, 250, 50);
                stuLbl.setFont(normal);
                panel.add(stuLbl);

                // Sets number of quiet floors
                JLabel quietLbl = new JLabel("Quiet Floors");
                quietLbl.setBounds(10, 120, 250, 50);
                quietLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
                panel.add(quietLbl);

                JPanel quietPanel = new JPanel();
                try {
                    ResultSet result = query.findQuietFloors();
                    while(result.next()) {

                    }
                } catch (SQLException error) {
                    error.printStackTrace();
                }
                JScrollPane quietScroll = new JScrollPane(quietPanel);
                quietScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                quietScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                quietScroll.setBounds(40, 170, 400, 100);
                panel.add(quietScroll);

                JLabel roomLbl = new JLabel("Study Rooms Available");
                roomLbl.setBounds(10, 270, 250, 50);
                roomLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
                panel.add(roomLbl);

                JPanel roomPanel = new JPanel();
                JScrollPane roomScroll = new JScrollPane(roomPanel);
                roomScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                roomScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                roomScroll.setBounds(40, 320, 400, 100);
                panel.add(roomScroll);

                JButton btnBack = new JButton("Back");
                btnBack.setBounds(10, 440, 70, 40);
                btnBack.setBackground(Color.BLACK);
                btnBack.setForeground(Color.WHITE);
                btnBack.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createChoiceFrame();
                        management.dispose();
                    }
                });
                panel.add(btnBack);

                management.add(panel);
                management.setVisible(true);
            }
        });
    }

    /**
     * Creates the login frame
     */
    private void createLoginFrame() {
        JFrame frame = new JFrame("Vivlo - Login");
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

    /**
     * Creates the search frame
     */
    public void createSearchFrame() {
        JFrame frame = new JFrame("Vivlo - Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(450,300);

        JLabel JL_fname,JL_lname,JL_title,JL_isbn;
        JTextField JT_fname,JT_lname,JT_title,JT_isbn;
        JButton btn_search;

        JL_isbn = new JLabel("Enter ISBN:");
        JL_isbn.setBounds(20, 20, 200, 20);
        JT_isbn = new JTextField(20);
        JT_isbn.setBounds(130, 20, 150, 20);

        btn_search = new JButton("Search");
        btn_search.setBounds(300, 20, 80, 20);
        btn_search.setBackground(Color.BLACK);
        btn_search.setForeground(Color.WHITE);
        btn_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbnNum = JT_isbn.getText();
                try {
                    ResultSet result = query.findBookByISBN(isbnNum);
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

        JCheckBox Reference = new JCheckBox("Reference Book");
        Reference.setBackground(tuYellow);
        Reference.setBounds(30, 130, 120, 40);
        frame.add(Reference);

        JCheckBox Non_reference = new JCheckBox("Non-Reference Book");
        Non_reference.setBackground(tuYellow);
        Non_reference.setBounds(170, 130, 145, 40);
        frame.add(Non_reference);

        JCheckBox Holds = new JCheckBox("Not Held");
        Holds.setBackground(tuYellow);
        Holds.setBounds(30, 170, 100, 40);
        frame.add(Holds);

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

        frame.add(btn_search);
        frame.add(JL_fname);
        frame.add(JT_fname);
        frame.add(JL_lname);
        frame.add(JT_lname);
        frame.add(JL_title);
        frame.add(JT_title);
        frame.add(JL_isbn);
        frame.add(JT_isbn);
        frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
    }

    /**
     * Create the book checkout frame
     */
    public void createCheckoutFrame() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 891, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblCheckOutPage = new JLabel("Check Out Page");
        lblCheckOutPage.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCheckOutPage.setBounds(346, 13, 188, 31);
        frame.getContentPane().add(lblCheckOutPage);
    }

    /**
     * Creates the query book results frame
     */
    public void createBookResultsFrame() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 970, 619);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblBookResults = new JLabel("Book results");
        lblBookResults.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblBookResults.setBounds(415, 13, 131, 43);
        frame.getContentPane().add(lblBookResults);

        String[] columnNames = {"ISBN#", "CopyNo","Title", "Author First", "Author Last", "Checked Out", "On Hold"};
        String [][] bookData = {columnNames, {"112222333", "1","The Alchemist", "Bart", "Allen", "yes", "No"},
                {"7865435354", "2","The Alchemist", "Maggie", "Zuelsdorf", "yes", "No"},
                {"354354616", "1","CODE", "Joey", "Case", "yes", "No"},
                {"453132354", "1","Boku No Hero Academia", "Emily", "Vogel", "yes", "No"}};


        TableModel model;
        model = new DefaultTableModel(bookData, columnNames);

        JScrollPane sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.setSize(1570, 684);
        JTable table = new JTable(model);
        resizeColumnWidth(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 18));
        table.setRowHeight(30);
        frame.getContentPane().add(table);
        table.setBounds(33, 63, 1403, 493);
        frame.getContentPane().add(sp);

        JButton btnNewSearchQuery = new JButton("New Search Query");
        btnNewSearchQuery.setBounds(590, 13, 170, 36);
        frame.getContentPane().add(btnNewSearchQuery);

        JButton btnCheckout = new JButton("Checkout Book");
        btnCheckout.setBounds(804, 13, 195, 36);
        frame.getContentPane().add(btnCheckout);
    }

    /**
     * Resizes the table column
     * @param table JTable to resize
     */
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
