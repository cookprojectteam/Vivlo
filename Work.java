/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package libraryproj;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Work extends JFrame implements ActionListener{
    JLabel JL_fname,JL_lname,JL_title,JL_isbn, JL_dept;
    JTextField JT_fname,JT_lname,JT_title,JT_isbn;
    JComboBox JCB_dept;
    JButton btn_search;

    public Work(){
        super("Search");
        JL_isbn = new JLabel("Enter ISBN:");
        JL_isbn.setBounds(20, 20, 200, 20);
        JT_isbn = new JTextField(20);
        JT_isbn.setBounds(130, 20, 150, 20);
        btn_search = new JButton("Search");
        btn_search.setBounds(300, 20, 80, 20);
        btn_search.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(450,200);

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

        setLayout(null);
        add(btn_search);
        add(JL_fname);
        add(JT_fname);
        add(JL_lname);
        add(JT_lname);
        add(JL_title);
        add(JT_title);
        add(JL_isbn);
        add(JT_isbn);
        add(JL_dept);
        add(JCB_dept);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String isbnNum = JT_isbn.getText();
        try {
            ResultSet result = query.findBook(isbnNum);
            if(result.next()) {
                JOptionPane.showMessageDialog(null, "ISBN found",
                        "ISBN found", JOptionPane.INFORMATION_MESSAGE);
                Work();
            }
            else {
                JOptionPane.showMessageDialog(null, "Not a valid ISBN#",
                        "ISBN error", JOptionPane.ERROR_MESSAGE);
                JT_isbn.setText(null);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Work();
    }

}
