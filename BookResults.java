

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;

public class BookResults {

	private JFrame frame;
	private JTable table;
	private JButton btnNewSearchQuery;
	private JButton btnCheckout;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BookResults window = new BookResults();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	

	/**
	 * Create the application.
	 */
	public BookResults() {
		initialize();
	}
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
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
		table = new JTable(model);
		resizeColumnWidth(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(30);
		frame.getContentPane().add(table);
		table.setBounds(33, 63, 1403, 493);
		frame.getContentPane().add(sp);
		
		btnNewSearchQuery = new JButton("New Search Query");
		btnNewSearchQuery.setBounds(590, 13, 170, 36);
		frame.getContentPane().add(btnNewSearchQuery);
		
		btnCheckout = new JButton("Checkout Book");
		btnCheckout.setBounds(804, 13, 195, 36);
		frame.getContentPane().add(btnCheckout);
		
	}
}
