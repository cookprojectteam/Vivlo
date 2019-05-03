import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class Search {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Search window = new Search();
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
	public Search() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(60, 202, 507, 49);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JCheckBox Non_reference = new JCheckBox("Non-Reference Book");
		Non_reference.setBounds(251, 119, 145, 52);
		frame.getContentPane().add(Non_reference);
		
		JCheckBox Reference = new JCheckBox("Reference Book");
		Reference.setBounds(60, 119, 121, 52);
		frame.getContentPane().add(Reference);
		
		JCheckBox Holds = new JCheckBox("Not Held");
		Holds.setBounds(474, 119, 93, 52);
		frame.getContentPane().add(Holds);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(387, 277, 115, 34);
		frame.getContentPane().add(btnSearch);
		
		JLabel lblVivloBookSearch = new JLabel("Vivlo Book Search");
		lblVivloBookSearch.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblVivloBookSearch.setBounds(224, 27, 204, 25);
		frame.getContentPane().add(lblVivloBookSearch);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(181, 286, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(100, 100, 655, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
