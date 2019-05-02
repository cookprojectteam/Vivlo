import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import javax.swing.*;
public class Login {

	private JFrame frame;
	private JTextField tuID;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.getContentPane().setLayout(null);
		
		JLabel TU_logo = new JLabel("");
		TU_logo.setBounds(0, 0, 545, 492);
		Image img = new ImageIcon(this.getClass().getResource("towsonu-logo.png")).getImage();
		TU_logo.setIcon(new ImageIcon(img));
		frame.getContentPane().add(TU_logo);
		
		tuID = new JTextField();
		tuID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tuID.setBounds(599, 265, 252, 38);
		frame.getContentPane().add(tuID);
		tuID.setColumns(10);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				
				String towsonID = tuID.getText();
				
				if(towsonID.contains("90088996677")) { 
					JOptionPane.showMessageDialog(null, "Successfull Login!", "Login Successful!", JOptionPane.INFORMATION_MESSAGE);
				}
				else { 
					JOptionPane.showMessageDialog(null, "Not a valid TU ID#", "Login Error", JOptionPane.ERROR_MESSAGE);
					tuID.setText(null);
				}
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
		
		
		
	}
}
