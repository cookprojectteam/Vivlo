
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
    private void createChoiceFrame() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame choice = new JFrame("Vivlo - Choice");
                choice.setTitle("Vivlo - Choice");
                choice.setBounds(100, 100, 600, 200);
                choice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                GridBagConstraints gbc = new GridBagConstraints();
                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                panel.setBackground(tuYellow);

                JButton btnBooks = new JButton("Insert Book");
                btnBooks.setPreferredSize(new Dimension(170, 40));
                btnBooks.setMargin(new Insets(10, 10, 10, 10));
                btnBooks.setBackground(Color.BLACK);
                btnBooks.setForeground(Color.WHITE);
                btnBooks.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createSearchFrame();
                        choice.dispose();
                    }
                });

                JButton btnBookAdd = new JButton("Add Book");
                btnBookAdd.setPreferredSize(new Dimension(170, 40));
                btnBookAdd.setMargin(new Insets(10, 10, 10, 10));
                btnBookAdd.setBackground(Color.BLACK);
                btnBookAdd.setForeground(Color.WHITE);
                btnBookAdd.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createInsertBookFrame();
                        choice.dispose();
                    }
                });

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
                panel.add(btnBookAdd, gbc);
                choice.add(panel);
                choice.setVisible(true);
            }
        });
    }

    /**
     * Initialize the contents of the create management frame.
     */
    private void createManagementFrame() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Font normal = new Font("Tahoma", Font.PLAIN, 18);
                JFrame management = new JFrame();
                management.getContentPane().setBackground(Color.YELLOW);
                management.setTitle("Vivlo - Management");
                management.setBounds(100, 100, 930, 540);
                management.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.setBackground(tuYellow);

                // Sets frame title
                JLabel managementLbl = new JLabel("Management");
                managementLbl.setBounds(360, 0, 250, 50);
                managementLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
                panel.add(managementLbl);

             // Sets number of available computers
                try {
                    ResultSet result = query.getAvailableComps();
                    
                    while (result.next()) {
                    	  JLabel compLbl = new JLabel("Computers Available: " + result.getString(1));
                          compLbl.setBounds(10, 50, 250, 50);
                          compLbl.setFont(normal);
                          panel.add(compLbl);
                    }
                } catch (SQLException error) {
                    error.printStackTrace();
                }
              

                // Sets number of books on hold
               
                try {
                    ResultSet result1 = query.countBooksOnHold();
                    
                    while (result1.next()) {
                    	 JLabel booksLbl = new JLabel("Books on Hold: " + result1.getString(1));
                         booksLbl.setBounds(380, 50, 250, 50);
                         booksLbl.setFont(normal);
                         panel.add(booksLbl);
                    }
                } catch (SQLException error) {
                    error.printStackTrace();
                }

                // Sets number of student workers
                JLabel stuLbl = new JLabel("Student Workers: " + "18");
                stuLbl.setBounds(610, 50, 250, 50);
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
                    	 JLabel count = new JLabel("Floor Num: " + result.getString("F_NUM") + " ");
                    	 count.setFont(new Font("Tahoma", Font.PLAIN, 20));
                    	 quietPanel.add(count);
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

                JLabel overLbl = new JLabel("Overdue Books");
                overLbl.setBounds(460, 120, 250, 50);
                overLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
                panel.add(overLbl);

                JPanel overPanel = new JPanel();
                JScrollPane overScroll = new JScrollPane(overPanel);
                overScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                overScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                overScroll.setBounds(490, 170, 400, 100);
                panel.add(overScroll);

                JLabel deptLbl = new JLabel("Departments");
                deptLbl.setBounds(460, 270, 250, 50);
                deptLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
                panel.add(deptLbl);

                JPanel deptPanel = new JPanel();
                JScrollPane deptScroll = new JScrollPane(deptPanel);
                deptScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                deptScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                deptScroll.setBounds(490, 320, 400, 100);
                panel.add(deptScroll);

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
                    	ResultSet resultFilter = query.loginFilter(idNum);
                    	if(resultFilter.next()) {
                    		Query.current_tuid = idNum;
                        	JOptionPane.showMessageDialog(null, "Successfull Login For Library Staff!",
                                "Login Successful!", JOptionPane.INFORMATION_MESSAGE);
                        	createChoiceFrame();
                        	frame.dispose();
                    	}
                    	else {
                    		Query.current_tuid = idNum;
                    		JOptionPane.showMessageDialog(null, "Successfull Login For Non Library Staff!",
                    				"Login Successful!", JOptionPane.INFORMATION_MESSAGE);
                    		createSearchFrame();
                    		frame.dispose();
                    	}
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
        frame.setBounds(100, 100, 390, 300);

        JLabel JL_fname,JL_lname,JL_title,JL_isbn;
        JTextField JT_fname,JT_lname,JT_title,JT_isbn;
        JButton btn_search;

        JL_isbn = new JLabel("Enter ISBN:");
        JL_isbn.setBounds(20, 20, 220, 20);
        JT_isbn = new JTextField(20);
        JT_isbn.setBounds(170, 20, 190, 20);

        JL_fname = new JLabel("Author First Name: ");
        JL_fname.setBounds(20, 50, 220, 20);
        JT_fname = new JTextField(20);
        JT_fname.setBounds(170, 50, 190, 20);
        JL_lname = new JLabel("Author Last Name: ");
        JL_lname.setBounds(20, 80, 220, 20);
        JT_lname = new JTextField(20);
        JT_lname.setBounds(170, 80, 190, 20);
        
        JL_title = new JLabel("Title: ");
        JL_title.setBounds(20, 110, 220, 20);
        JT_title = new JTextField(20);
        JT_title.setBounds(170, 110, 190, 20);

        btn_search = new JButton("Search");
        btn_search.setBounds(260, 215, 100, 30);
        btn_search.setBackground(Color.BLACK);
        btn_search.setForeground(Color.WHITE);
        btn_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbnNum = JT_isbn.getText();
                String Fname = JT_fname.getText();
                String Lname =  JT_lname.getText();
                String title = JT_title.getText();
                try {
                    ResultSet result = query.findBookByISBN(isbnNum);
                    ResultSet author = query.listByAuthor(Fname, Lname);
                    ResultSet bookTitle = query.findBookByTitle(title);
                    if(result.next()) {
                    	result.getString(1);
                        JOptionPane.showMessageDialog(null, "ISBN found",
                                "ISBN found", JOptionPane.INFORMATION_MESSAGE);
                    }else if(author.next()){ 
                    	author.getString(1);
                        JOptionPane.showMessageDialog(null, "Book Author Name found",
                                "Author Name Found", JOptionPane.INFORMATION_MESSAGE);
                    }else if(bookTitle.next()){
                    	bookTitle.getString(1);
                        JOptionPane.showMessageDialog(null, "Book title found",
                                "Book Title Found", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Not a valid ISBN#, book title or Author name",
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

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(10, 215, 100, 30);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBookResultsFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn);

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
        JFrame frame = new JFrame("Vivlo - Checkout");
        frame.setBounds(100, 100, 400, 260);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblCheckOutPage = new JLabel("Check Out Page");
        lblCheckOutPage.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblCheckOutPage.setBounds(60, 15, 300, 45);
        frame.add(lblCheckOutPage);

        JLabel isbnLbl = new JLabel("ISBN Number");
        isbnLbl.setBounds(30, 80, 150, 30);
        frame.add(isbnLbl);

        JTextField isbnText = new JTextField();
        isbnText.setBounds(30, 110, 150, 30);
        frame.add(isbnText);

        JLabel copyLbl = new JLabel("Copy Number");
        copyLbl.setBounds(200, 80, 150, 30);
        frame.add(copyLbl);

        JTextField copyText = new JTextField();
        copyText.setBounds(200, 110, 150, 30);
        frame.add(copyText);

        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBackground(Color.BLACK);
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setBounds(250, 160, 100, 40);

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.setBounds(30, 160, 100, 40);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBookResultsFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn);

        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        frame.add(checkoutBtn);

        frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
    }

    /**
     * Creates the query book results frame
     */
    public void createBookResultsFrame() {
        JFrame frame = new JFrame("Vivlo - Books");
        frame.setBounds(100, 100, 970, 620);
        frame.getContentPane().setBackground(tuYellow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblBookResults = new JLabel("Book Results");
        lblBookResults.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblBookResults.setBounds(30, 10, 250, 50);
        frame.getContentPane().add(lblBookResults);

        String[] columnNames = {"ISBN#", "CopyNo","Title", "Author First", "Author Last", "Checked Out", "On Hold"};
        String [][] bookData = {columnNames, {"112222333", "1","The Alchemist", "Bart", "Allen", "yes", "No"},
                {"7865435354", "2","The Alchemist", "Maggie", "Zuelsdorf", "yes", "No"},
                {"354354616", "1","CODE", "Joey", "Case", "yes", "No"},
                {"453132354", "1","Boku No Hero Academia", "Emily", "Vogel", "yes", "No"}};


        TableModel model;
        model = new DefaultTableModel(bookData, columnNames);

        JScrollPane sp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.setSize(1490, 640);
        JTable table = new JTable(model);
        resizeColumnWidth(table);
        table.setFont(new Font("Tahoma", Font.PLAIN, 18));
        table.setRowHeight(30);
        frame.getContentPane().add(table);
        table.setBounds(33, 63, 1403, 493);
        frame.getContentPane().add(sp);

        JButton btnNewSearchQuery = new JButton("New Search Query");
        btnNewSearchQuery.setBackground(Color.BLACK);
        btnNewSearchQuery.setForeground(Color.WHITE);
        btnNewSearchQuery.setBounds(820, 15, 195, 40);
        btnNewSearchQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSearchFrame();
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnNewSearchQuery);

        JButton btnCheckin = new JButton("Return a Book");
        btnCheckin.setBackground(Color.BLACK);
        btnCheckin.setForeground(Color.WHITE);
        btnCheckin.setBounds(1030, 15, 195, 40);
        btnCheckin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReturnFrame();
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnCheckin);

        JButton btnCheckout = new JButton("Checkout Book");
        btnCheckout.setBackground(Color.BLACK);
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCheckoutFrame();
                frame.dispose();
            }
        });
        btnCheckout.setBounds(1240, 15, 195, 40);

        frame.getContentPane().add(btnCheckout);
        frame.setVisible(true);
    }

    /**
     * Create the book return frame
     */
    public void createReturnFrame() {
        JFrame frame = new JFrame("Vivlo - Return");
        frame.setBounds(100, 100, 400, 260);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLbl = new JLabel("Return Page");
        titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
        titleLbl.setBounds(90, 15, 300, 45);
        frame.add(titleLbl);

        JLabel isbnLbl = new JLabel("ISBN Number");
        isbnLbl.setBounds(30, 80, 150, 30);
        frame.add(isbnLbl);

        JTextField isbnText = new JTextField();
        isbnText.setBounds(30, 110, 150, 30);
        frame.add(isbnText);

        JLabel copyLbl = new JLabel("Copy Number");
        copyLbl.setBounds(200, 80, 150, 30);
        frame.add(copyLbl);

        JTextField copyText = new JTextField();
        copyText.setBounds(200, 110, 150, 30);
        frame.add(copyText);

        JButton returnBtn = new JButton("Return");
        returnBtn.setBackground(Color.BLACK);
        returnBtn.setForeground(Color.WHITE);
        returnBtn.setBounds(250, 160, 100, 40);

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.setBounds(30, 160, 100, 40);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBookResultsFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn);

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        frame.add(returnBtn);

        frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
    }

    /**
     * Creates the book insert frame
     */
    public void createInsertBookFrame() {
        Font normal = new Font("Tahoma", Font.PLAIN, 20);
        JFrame frame = new JFrame("Vivlo - Insert");
        frame.setBounds(100, 100, 440, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(tuYellow);

        JLabel title = new JLabel("Insert Book");
        title.setBounds(120, 30, 300, 30);
        title.setFont(new Font("Tahoma", Font.PLAIN, 36));
        frame.add(title);

        JLabel isbnLbl = new JLabel("ISBN");
        isbnLbl.setBounds(10, 80, 200, 30);
        isbnLbl.setFont(normal);
        frame.add(isbnLbl);

        JTextField isbnTxt = new JTextField();
        isbnTxt.setBounds(210, 80, 200, 30);
        frame.add(isbnTxt);

        JLabel copyLbl = new JLabel("Copy");
        copyLbl.setBounds(10, 120, 200, 30);
        copyLbl.setFont(normal);
        frame.add(copyLbl);

        JTextField copyTxt = new JTextField();
        copyTxt.setBounds(210, 120, 200, 30);
        frame.add(copyTxt);

        JLabel titleLbl = new JLabel("Title");
        titleLbl.setBounds(10, 160, 200, 30);
        titleLbl.setFont(normal);
        frame.add(titleLbl);

        JTextField titleTxt = new JTextField();
        titleTxt.setBounds(210, 160, 200, 30);
        frame.add(titleTxt);

        JLabel genreLbl = new JLabel("Genre");
        genreLbl.setBounds(10, 200, 200, 30);
        genreLbl.setFont(normal);
        frame.add(genreLbl);

        JTextField genreTxt = new JTextField();
        genreTxt.setBounds(210, 200, 200, 30);
        frame.add(genreTxt);

        JLabel pubDateLbl = new JLabel("Publish Date");
        pubDateLbl.setBounds(10, 240, 200, 30);
        pubDateLbl.setFont(normal);
        frame.add(pubDateLbl);

        JTextField pubDateTxt = new JTextField();
        pubDateTxt.setBounds(210, 240, 200, 30);
        frame.add(pubDateTxt);

        JLabel pubLbl = new JLabel("Publisher");
        pubLbl.setBounds(10, 280, 200, 30);
        pubLbl.setFont(normal);
        frame.add(pubLbl);

        JTextField pubTxt = new JTextField();
        pubTxt.setBounds(210, 280, 200, 30);
        frame.add(pubTxt);

        JLabel sizeLbl = new JLabel("Size");
        sizeLbl.setBounds(10, 320, 200, 30);
        sizeLbl.setFont(normal);
        frame.add(sizeLbl);

        JTextField sizeTxt = new JTextField();
        sizeTxt.setBounds(210, 320, 200, 30);
        frame.add(sizeTxt);

        JLabel authorLbl = new JLabel("Author");
        authorLbl.setBounds(10, 360, 200, 30);
        authorLbl.setFont(normal);
        frame.add(authorLbl);

        JTextField authorTxt = new JTextField();
        authorTxt.setBounds(210, 360, 200, 30);
        frame.add(authorTxt);

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(10, 400, 160, 45);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChoiceFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn);

        JButton insertBtn = new JButton("Insert");
        insertBtn.setBackground(Color.BLACK);
        insertBtn.setForeground(Color.WHITE);
        insertBtn.setBounds(250, 400, 160, 45);
        insertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        frame.add(insertBtn);

        frame.setVisible(true);
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
