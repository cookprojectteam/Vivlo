
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Vivlo {
	public static final int joey=9;

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

        JButton btnNewSearchQuery = new JButton("Back to Search");
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

        JButton btnCheckin = new JButton("Return Book");
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
    /*
     * Create the query book results frame for management
     */
    public void createBookResultsFrameManagement() {
        JFrame frame = new JFrame("Vivlo - Books");
        frame.setBounds(100, 100, 500, 500);
        frame.getContentPane().setBackground(tuYellow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblBookResults = new JLabel("Book Results");
        lblBookResults.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lblBookResults.setBounds(200, 10, 250, 50);
        frame.getContentPane().add(lblBookResults);
        
       

        JButton btnNewSearchQuery = new JButton("Back to Search");
        btnNewSearchQuery.setBackground(Color.BLACK);
        btnNewSearchQuery.setForeground(Color.WHITE);
        btnNewSearchQuery.setBounds(820, 15, 195, 40);
        btnNewSearchQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSearchFrameManagement();
                frame.dispose();
            }
        });
        frame.getContentPane().add(btnNewSearchQuery);

        JButton btnCheckin = new JButton("Return Book");
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
                createManageHomeFrame();
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
    
    
    /*
     * 
     * Restructuring everything from scratch to make the project uniform in UI.
     * Joey's Log:
     * Update: 4:28 A.M. start
     * Update: 6:24 A.M. completed login, navigation, return, checkout
     *
     */
    /**
     * 
     * 
     * 
     * 
     * 
     * NON MANAGEMENT SIDE OF THE CODE
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Creates the login frame called createLoginFrame. Sends the user to a navigation page when the user is 
     * not library staff. Sends the user to a choice page when the user is library staff. 
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
                        	createManageHomeFrame();
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
     * 
     * 
     * 
     * Creates the navigation frame called createSearchFrame for non library staff users. The features on 
     * the page include test search, show search, return book, list available non reference books, and list reference books 
     * 
     */
    public void createSearchFrame() {
        JFrame frame = new JFrame("Vivlo - Navigation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(100, 100, 915, 534);

        JLabel JL_fname,JL_lname,JL_title,JL_isbn;
        JTextField JT_fname,JT_lname,JT_title,JT_isbn;
        JButton btn_search;

        JL_isbn = new JLabel("Enter ISBN:");
        JL_isbn.setBounds(20, 20, 220, 20);
        JL_isbn.setFont(new Font("Tahoma", Font.BOLD, 18));
        JT_isbn = new JTextField(20);
        JT_isbn.setBounds(200, 22, 190, 20);

        JL_fname = new JLabel("Author First Name: ");
        JL_fname.setBounds(20, 80, 220, 20);
        JL_fname.setFont(new Font("Tahoma",Font.BOLD,18));
        JT_fname = new JTextField(20);
        JT_fname.setBounds(200, 82, 190, 20);
        
        JL_lname = new JLabel("Author Last Name: ");
        JL_lname.setBounds(20, 140, 220, 20);
        JL_lname.setFont(new Font("Tahoma",Font.BOLD,18));
        JT_lname = new JTextField(20);
        JT_lname.setBounds(200, 142, 190, 20);
        
        JL_title = new JLabel("Title: ");
        JL_title.setBounds(20, 200, 220, 20);
        JL_title.setFont(new Font("Tahoma",Font.BOLD,18));
        JT_title = new JTextField(20);
        JT_title.setBounds(200, 202, 190, 20);

        btn_search = new JButton("Test Search");
        btn_search.setBounds(100, 300, 300, 40);
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
        
        /*
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
        */

        JButton backBtn = new JButton("Show Search");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(100, 400, 300, 40);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String isbnNum = JT_isbn.getText();
                String Fname = JT_fname.getText();
                String Lname =  JT_lname.getText();
                String title = JT_title.getText();
                showSearch(isbnNum,Fname,Lname,title);
                frame.dispose();
            }
        });
        
        JButton checkoutBook = new JButton("CheckOut Book");
        checkoutBook.setBackground(Color.BLACK);
        checkoutBook.setForeground(Color.WHITE);
        checkoutBook.setBounds(550, 100, 300, 40);
        checkoutBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCheckoutFrame();
                frame.dispose();
            }
        });
        
        JButton returnBook = new JButton("Return Book");
        returnBook.setBackground(Color.BLACK);
        returnBook.setForeground(Color.WHITE);
        returnBook.setBounds(550, 200, 300, 40);
        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReturnFrame();
                frame.dispose();
            }
        });
        JButton availableNonReference = new JButton("Available Non Reference Books");
        availableNonReference.setBackground(Color.BLACK);
        availableNonReference.setForeground(Color.WHITE);
        availableNonReference.setBounds(550, 300, 300, 40);
        availableNonReference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listNRefFrame();
                frame.dispose();
            }
        });
        JButton reference = new JButton("Reference Books");
        reference.setBackground(Color.BLACK);
        reference.setForeground(Color.WHITE);
        reference.setBounds(550, 400, 300, 40);
        reference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listRefFrame();
                frame.dispose();
            }
        });
        frame.add(checkoutBook);
        frame.add(availableNonReference);
        frame.add(reference);
        frame.add(returnBook);
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
     * 
     * 
     * 
     * Creates the return book frame called createReturnFrame. Allows user to enter in ISBN 
     * and COPY number of book, return the book, and navigate back to the navigation frame
     */
    public void createReturnFrame() {
        JFrame frame = new JFrame("Vivlo - Return Book");
        frame.setBounds(100, 100, 915, 534);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLbl = new JLabel("Return Book");
        titleLbl.setFont(new Font("Tahoma", Font.BOLD, 36));
        titleLbl.setBounds(350, 15, 300, 40);
        frame.add(titleLbl);
        
        JLabel isbnLbl,copyLbl;
        JTextField isbnText,copyText;
        
        isbnLbl = new JLabel("ISBN Number: ");
        isbnLbl.setBounds(20, 140, 220, 20);
        isbnLbl.setFont(new Font("Tahoma",Font.BOLD,18));
        isbnText = new JTextField(20);
        isbnText.setBounds(200, 142, 190, 20);
        
        copyLbl = new JLabel("Copy Number: ");
        copyLbl.setBounds(20, 200, 220, 20);
        copyLbl.setFont(new Font("Tahoma",Font.BOLD,18));
        copyText = new JTextField(20);
        copyText.setBounds(200, 202, 190, 20);        
        frame.add(isbnLbl);      
        frame.add(isbnText);       
        frame.add(copyLbl);        
        frame.add(copyText);
        
        
        
        JButton returnBtn = new JButton("Return Book");
        returnBtn.setBackground(Color.BLACK);
        returnBtn.setForeground(Color.WHITE);
        returnBtn.setBounds(550, 400, 300, 40);

        JButton backBtn = new JButton("Back to Navigation");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.setBounds(100, 400, 300, 40);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ResultSet result = query.isLibStaff();
            	try {
            		if(result.next()) {
            			createSearchFrameManagement();
            		}
            		else {
            			createSearchFrame();
            		}
            	}
            	catch (SQLException er) {
            		er.printStackTrace();
            	}
                frame.dispose();
            }
        });
        frame.add(backBtn);

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String isbn = isbnText.getText();
            	String copy = copyText.getText();
            	try {
            		query.checkin(isbn, copy);
            	}
            	catch (Exception er) {
            		er.printStackTrace();
            	}

            }
        });
        frame.add(returnBtn);

        frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
    }
    /**
     * 
     * 
     * 
     * Create the check out book frame called createCheckoutFrame. Allows user to enter ISBN
     * and Copy number of book, checkout book, and return back to the navigation frame
     */
    public void createCheckoutFrame() {
        JFrame frame = new JFrame("Vivlo - Checkout Book");
        frame.setBounds(100, 100, 915, 534);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblCheckOutPage = new JLabel("CheckOut Book");
        lblCheckOutPage.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblCheckOutPage.setBounds(350, 15, 300, 40);
        frame.add(lblCheckOutPage);

        JLabel isbnLbl,copyLbl;
        JTextField isbnText,copyText;
        
        isbnLbl = new JLabel("ISBN Number: ");
        isbnLbl.setBounds(20, 140, 220, 20);
        isbnLbl.setFont(new Font("Tahoma",Font.BOLD,18));
        isbnText = new JTextField(20);
        isbnText.setBounds(200, 142, 190, 20);
        
        copyLbl = new JLabel("Copy Number: ");
        copyLbl.setBounds(20, 200, 220, 20);
        copyLbl.setFont(new Font("Tahoma",Font.BOLD,18));
        copyText = new JTextField(20);
        copyText.setBounds(200, 202, 190, 20);        
        frame.add(isbnLbl);      
        frame.add(isbnText);       
        frame.add(copyLbl);        
        frame.add(copyText);

        JButton checkoutBtn = new JButton("Checkout Book");
        checkoutBtn.setBackground(Color.BLACK);
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setBounds(550, 400, 300, 40);

        JButton backBtn = new JButton("Back to Navigation");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.setBounds(100, 400, 300, 40);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ResultSet result = query.isLibStaff();
            	try {
            		if(result.next()) {
            			createSearchFrameManagement();
            		}
            		else {
            			createSearchFrame();
            		}
            	}
            	catch (SQLException er) {
            		er.printStackTrace();
            	}
                frame.dispose();
            }
        });
        frame.add(backBtn);

        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String isbn = isbnText.getText();
            	String copy = copyText.getText();
            	try {
            		query.checkout(isbn,copy);           		
            		
            	}
            	catch (Exception er) {
            		er.printStackTrace();
            	}

            }
        });
        frame.add(checkoutBtn);

        frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
    }
    
    /**
     * Create the result table for show search in navigation frame called showSearch. The table will 
     * process input from the navigation frame by returning any tuples that contain the information.
     * 
     * 
     */
    
    public void showSearch(String isbn,String af,String al,String title) {    	
    	JFrame frame = new JFrame("Vivlo - Show Search");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Show Search");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ISBN","COPY","AF","AL","TITLE","SIZE"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.show(isbn,af,al,title);
			while(result.next()){
				String ISBN = result.getString("ISBN");
				int COPY = result.getInt("COPY");
				String AF = result.getString("AF");
				String AL = result.getString("AL");
				String TITLE = result.getString("TITLE");
				int SIZE = result.getInt("SIZE");
			
			
			tableModel.addRow(new Object[] {ISBN,COPY,AF,AL,TITLE,SIZE});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Navigation");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ResultSet result = query.isLibStaff();
            	try {
            		if(result.next()) {
            			createSearchFrameManagement();
            		}
            		else {
            			createSearchFrame();
            		}
            	}
            	catch (SQLException er) {
            		er.printStackTrace();
            	}
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    
    /**
     * Create the result table for a list of reference books that the library owns. Contains a button
     * to return to the navigation frame
     * 
     * 
     */
    
    public void listRefFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Reference Books");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Reference Books");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ISBN","COPY","TITLE","GENRE","PUBLISHER","SIZE"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.listRef();
			while(result.next()){
				String ISBN = result.getString("ISBN");
				int COPY = result.getInt("COPY");
				String TITLE = result.getString("TITLE");
				String GENRE = result.getString("GENRE");
				String PUBLISHER = result.getString("PUBLISHER");
				String SIZE = result.getString("SIZE");
			
			
			tableModel.addRow(new Object[] {ISBN,COPY,TITLE,GENRE,PUBLISHER,SIZE});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Navigation");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ResultSet result = query.isLibStaff();
            	try {
            		if(result.next()) {
            			createSearchFrameManagement();
            		}
            		else {
            			createSearchFrame();
            		}
            	}
            	catch (SQLException er) {
            		er.printStackTrace();
            	}
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    /**
     * Create the result table for a list of available non reference books. Contains a button that
     * sends the user to the navigation frame
     * 
     * 
     */
    
    public void listNRefFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Available Non Reference Books");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Available Non Reference Books");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ISBN","COPY","TITLE","GENRE","PUBLISHER","SIZE"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.listNref();
			while(result.next()){
				String ISBN = result.getString("ISBN");
				int COPY = result.getInt("COPY");
				String TITLE = result.getString("TITLE");
				String GENRE = result.getString("GENRE");
				String PUBLISHER = result.getString("PUBLISHER");
				String SIZE = result.getString("SIZE");
			
			
			tableModel.addRow(new Object[] {ISBN,COPY,TITLE,GENRE,PUBLISHER,SIZE});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Navigation");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ResultSet result = query.isLibStaff();
            	try {
            		if(result.next()) {
            			createSearchFrameManagement();
            		}
            		else {
            			createSearchFrame();
            		}
            	}
            	catch (SQLException er) {
            		er.printStackTrace();
            	}
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    /**
     * MANAGEMENT SIDE OF THE CODE
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
    /**
     * Initialize the contents of the create choice frame.
     */
    private void createManageHomeFrame() {
    	JFrame frame = new JFrame("Vivlo - Management Home");
        frame.setBounds(100, 100, 915, 534);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblChoice = new JLabel("Management Home");
        lblChoice.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblChoice.setBounds(280, 15, 500, 40);
        frame.add(lblChoice);

        
        JButton BookSearch = new JButton("Book Search");
        BookSearch.setBackground(Color.BLACK);
        BookSearch.setForeground(Color.WHITE);
        BookSearch.setBounds(300, 150, 300, 40);
        
        JButton ManageInfo = new JButton("Management Information");
        ManageInfo.setBackground(Color.BLACK);
        ManageInfo.setForeground(Color.WHITE);
        ManageInfo.setBounds(300, 250, 300, 40);

        JButton BookInsert = new JButton("Book Insert");
        BookInsert.setForeground(Color.WHITE);
        BookInsert.setBackground(Color.BLACK);
        BookInsert.setBounds(300, 350, 300, 40);
        
        BookSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createSearchFrameManagement();
                frame.dispose();
            }
        });       
        
        
        ManageInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManagementInfoFrame();
                frame.dispose();
            }
        });       

        BookInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();          		
            		

            }
        });        
        frame.add(BookInsert);
        frame.add(BookSearch);
        frame.add(ManageInfo);
        frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
    }
    /**
     * create the search functionality for library management. Almost entirely the same code 
     * . The only part that is different is the extra button. Library management 
     * staff can check management information and insert new books into the database while 
     * non library management users can not
     * 
     */
    
    public void createSearchFrameManagement() {
        JFrame frame = new JFrame("Vivlo - Navigation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(100, 100, 915, 534);

        JLabel JL_fname,JL_lname,JL_title,JL_isbn;
        JTextField JT_fname,JT_lname,JT_title,JT_isbn;
        JButton btn_search;

        JL_isbn = new JLabel("Enter ISBN:");
        JL_isbn.setBounds(20, 20, 220, 20);
        JL_isbn.setFont(new Font("Tahoma", Font.BOLD, 18));
        JT_isbn = new JTextField(20);
        JT_isbn.setBounds(200, 22, 190, 20);

        JL_fname = new JLabel("Author First Name: ");
        JL_fname.setBounds(20, 80, 220, 20);
        JL_fname.setFont(new Font("Tahoma",Font.BOLD,18));
        JT_fname = new JTextField(20);
        JT_fname.setBounds(200, 82, 190, 20);
        
        JL_lname = new JLabel("Author Last Name: ");
        JL_lname.setBounds(20, 140, 220, 20);
        JL_lname.setFont(new Font("Tahoma",Font.BOLD,18));
        JT_lname = new JTextField(20);
        JT_lname.setBounds(200, 142, 190, 20);
        
        JL_title = new JLabel("Title: ");
        JL_title.setBounds(20, 200, 220, 20);
        JL_title.setFont(new Font("Tahoma",Font.BOLD,18));
        JT_title = new JTextField(20);
        JT_title.setBounds(200, 202, 190, 20);

        btn_search = new JButton("Test Search");
        btn_search.setBounds(100, 300, 300, 40);
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

        JButton backBtn = new JButton("Show Search");
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(100, 400, 300, 40);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String isbnNum = JT_isbn.getText();
                String Fname = JT_fname.getText();
                String Lname =  JT_lname.getText();
                String title = JT_title.getText();
                showSearch(isbnNum,Fname,Lname,title);
                frame.dispose();
            }
        });
        
        JButton checkoutBook = new JButton("CheckOut Book");
        checkoutBook.setBackground(Color.BLACK);
        checkoutBook.setForeground(Color.WHITE);
        checkoutBook.setBounds(550, 100, 300, 40);
        checkoutBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCheckoutFrame();
                frame.dispose();
            }
        });
        
        JButton returnBook = new JButton("Return Book");
        returnBook.setBackground(Color.BLACK);
        returnBook.setForeground(Color.WHITE);
        returnBook.setBounds(550, 200, 300, 40);
        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createReturnFrame();
                frame.dispose();
            }
        });
        JButton availableNonReference = new JButton("Available Non Reference Books");
        availableNonReference.setBackground(Color.BLACK);
        availableNonReference.setForeground(Color.WHITE);
        availableNonReference.setBounds(550, 300, 300, 40);
        availableNonReference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listNRefFrame();
                frame.dispose();
            }
        });
        JButton reference = new JButton("Reference Books");
        reference.setBackground(Color.BLACK);
        reference.setForeground(Color.WHITE);
        reference.setBounds(550, 400, 300, 40);
        reference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listRefFrame();
                frame.dispose();
            }
        });
        JButton BackButton = new JButton("Back to Management Home");
        BackButton.setBackground(Color.BLACK);
        BackButton.setForeground(Color.WHITE);
        BackButton.setBounds(550, 10, 300, 40);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createManageHomeFrame();
                frame.dispose();
            }
        });
        
        frame.add(BackButton);
        frame.add(checkoutBook);
        frame.add(availableNonReference);
        frame.add(reference);
        frame.add(returnBook);
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
     * Management information frame. Contains buttons that allow management execute queries that non 
     * management users can not execute
     */
    private void createManagementInfoFrame() {
    	JFrame frame = new JFrame("Vivlo - Management Information");
        frame.setBounds(100, 100, 915, 534);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblChoice = new JLabel("Management Information");
        lblChoice.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblChoice.setBounds(200, 15, 500, 40);
        frame.add(lblChoice);
        
        JButton Back = new JButton("Back to Management Home");
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        Back.setBounds(270, 85, 300, 40);
        frame.add(Back);

        
        JButton ODBook = new JButton("List of Overdue Books");
        ODBook.setBackground(Color.BLACK);
        ODBook.setForeground(Color.WHITE);
        ODBook.setBounds(100, 150, 300, 40);
        JButton HBook = new JButton("List of Bookds on Hold");
        HBook.setBackground(Color.BLACK);
        HBook.setForeground(Color.WHITE);
        HBook.setBounds(100, 200, 300, 40);
        JButton COBook = new JButton("List of Checked Out Books");
        COBook.setBackground(Color.BLACK);
        COBook.setForeground(Color.WHITE);
        COBook.setBounds(100, 250, 300, 40);
        frame.add(ODBook);
        frame.add(HBook);
        frame.add(COBook);
        
        
        
        JButton Department = new JButton("Department Information");
        Department.setBackground(Color.BLACK);
        Department.setForeground(Color.WHITE);
        Department.setBounds(450, 150, 300, 40);
        JButton Floor = new JButton("Floor Information");
        Floor.setBackground(Color.BLACK);
        Floor.setForeground(Color.WHITE);
        Floor.setBounds(450, 200, 300, 40);
        JButton Staff = new JButton("Staff Information");
        Staff.setBackground(Color.BLACK);
        Staff.setForeground(Color.WHITE);
        Staff.setBounds(450, 250, 300, 40);
        frame.add(Department);
        frame.add(Floor);
        frame.add(Staff);
        
        
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManageHomeFrame();
                frame.dispose();
            }
        });       
        
        
        Department.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DepartmentFrame();
                frame.dispose();
            }
        });       

        Floor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FloorFrame();
            	frame.dispose();          		
            		

            }
        });        
        Staff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	StaffFrame();
            	frame.dispose();          		
            		

            }
        });    
        ODBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ODBookFrame();
            	frame.dispose();          		
            		

            }
        });    
        HBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	HBookFrame();
            	frame.dispose();          		
            		

            }
        });    
        COBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	COBookFrame();
            	frame.dispose();          		
            		

            }
        });    
        frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
    }    
    /**
     * Department Frame to create a result table with information about each department. Retrieves the
     * name, number, and head of each department. Also, retrieves the number of student workers and 
     * librarians in each department
     * 
     */
    public void DepartmentFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Department Information");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Department Inofrmation");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ISBN","COPY","TITLE","GENRE","PUBLISHER","SIZE"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.listNref();
			while(result.next()){
				String ISBN = result.getString("ISBN");
				int COPY = result.getInt("COPY");
				String TITLE = result.getString("TITLE");
				String GENRE = result.getString("GENRE");
				String PUBLISHER = result.getString("PUBLISHER");
				String SIZE = result.getString("SIZE");
			
			
			tableModel.addRow(new Object[] {ISBN,COPY,TITLE,GENRE,PUBLISHER,SIZE});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Management Information");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManagementInfoFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    /**
     * 
     * 
     */
    public void ODBookFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Over Due Books");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Over Due Books");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"NREF_ISBN","NREF_COPY","CO_TUID"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.overDueBooks();
			while(result.next()){
				String NREF_ISBN = result.getString("NREF_ISBN");
				String NREF_COPY = result.getString("NREF_COPY");
				String CO_TUID = result.getString("CO_TUID");
				
			
			tableModel.addRow(new Object[] {NREF_ISBN,NREF_COPY,CO_TUID});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Manage Information");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManagementInfoFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    /**
     * 
     * 
     */
    public void COBookFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Checked Out Books");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Checked Out Books");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"NREF_ISBN","NREF_COPY","CO_TUID"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.booksCheckedOut();
			while(result.next()){
				String NREF_ISBN = result.getString("NREF_ISBN");
				String NREF_COPY = result.getString("NREF_COPY");
				String CO_TUID = result.getString("CO_TUID");
			
			
			tableModel.addRow(new Object[] {NREF_ISBN,NREF_COPY,CO_TUID});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Manage Information");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManagementInfoFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    /**
     * 
     * 
     */
    public void HBookFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Books on Hold");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Books on Hold");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"NREF_ISBN","NREF_COPY","HOLD"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.booksHold();
			while(result.next()){
				String NREF_ISBN = result.getString("NREF_ISBN");
				String NREF_COPY = result.getString("NREF_COPY");
				String HOLD = result.getString("HOLD");
			
			
			tableModel.addRow(new Object[] {NREF_ISBN,NREF_COPY,HOLD});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Manage Information");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManagementInfoFrame();            	
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    /**
     * 
     * 
     */
    public void StaffFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Staff Information");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Staff Information");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ISBN","COPY","TITLE","GENRE","PUBLISHER","SIZE"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.listNref();
			while(result.next()){
				String ISBN = result.getString("ISBN");
				int COPY = result.getInt("COPY");
				String TITLE = result.getString("TITLE");
				String GENRE = result.getString("GENRE");
				String PUBLISHER = result.getString("PUBLISHER");
				String SIZE = result.getString("SIZE");
			
			
			tableModel.addRow(new Object[] {ISBN,COPY,TITLE,GENRE,PUBLISHER,SIZE});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Manage Information");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManagementInfoFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    /**
     * 
     * 
     */
    public void FloorFrame() {    	
    	JFrame frame = new JFrame("Vivlo - Floor Information");
		frame.setBounds(100, 100, 912, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JLabel lblDataResults = new JLabel("Floor Information");
		lblDataResults.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDataResults.setHorizontalAlignment(JLabel.CENTER);
		frame.add(lblDataResults,BorderLayout.NORTH);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ISBN","COPY","TITLE","GENRE","PUBLISHER","SIZE"
				}
			)); 
		table.setBounds(40, 700, 200, 200);
	    table.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    table.setRowHeight(30);
		frame.getContentPane().setBackground(tuYellow);
        frame.setVisible(true);
        
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		try {
			resizeColumnWidth(table);
			ResultSet result = query.listNref();
			while(result.next()){
				String ISBN = result.getString("ISBN");
				int COPY = result.getInt("COPY");
				String TITLE = result.getString("TITLE");
				String GENRE = result.getString("GENRE");
				String PUBLISHER = result.getString("PUBLISHER");
				String SIZE = result.getString("SIZE");
			
			
			tableModel.addRow(new Object[] {ISBN,COPY,TITLE,GENRE,PUBLISHER,SIZE});
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Failed here also");
		}
		JScrollPane scrollablearea = new JScrollPane(table);
		frame.add(scrollablearea,BorderLayout.SOUTH);
		
		JButton backBtn = new JButton("Back to Manage Information");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createManagementInfoFrame();
                frame.dispose();
            }
        });
        frame.add(backBtn,BorderLayout.CENTER);
		
		
	}
    
    
}
