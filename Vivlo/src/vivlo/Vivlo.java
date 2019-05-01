package vivlo;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

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
				JFrame frmVivlo = new JFrame();
				frmVivlo.setTitle("Vivlo - Choice");
				frmVivlo.setBounds(100, 100, 400, 200);
				frmVivlo.setBackground(new Color(247, 247, 247));
				frmVivlo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmVivlo.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton btnBooks = new JButton("Books");
				frmVivlo.getContentPane().add(btnBooks);
				
				JButton btnManagement = new JButton("Management");
				btnManagement.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createManagementFrame();
						frmVivlo.dispose();
					}
				});
				frmVivlo.getContentPane().add(btnManagement);
				frmVivlo.setVisible(true);
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
                // Put new frame here
            }
        });
    }

}
