package IA;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;

public class testing extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField Username;
	private JTextField Password;
	private JPanel Login;
	private JPanel UserMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testing frame = new testing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel Login = new JPanel();
		contentPane.add(Login, "name_175979140570239");
		Login.setLayout(null);
		Login.setVisible(true);
		
		Username = new JTextField();
		Username.setBounds(182, 83, 146, 26);
		Login.add(Username);
		Username.setColumns(10);
		
		Password = new JTextField();
		Password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		Password.setBounds(182, 125, 146, 26);
		Login.add(Password);
		Password.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(78, 86, 91, 20);
		Login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(78, 128, 79, 20);
		Login.add(lblPassword);
		
		JLabel lblPhotoManager = new JLabel("Photo Manager");
		lblPhotoManager.setFont(new Font("Sitka Text", Font.BOLD, 27));
		lblPhotoManager.setBounds(103, 29, 219, 41);
		Login.add(lblPhotoManager);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(213, 167, 115, 29);
		Login.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserMenu.setVisible(true);
				Login.setVisible(false);
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(78, 167, 115, 29);
		Login.add(btnCancel);
		
		JPanel UserMenu = new JPanel();
		contentPane.add(UserMenu, "name_175982178153740");
		UserMenu.setVisible(false);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "name_175985911456071");
		panel_2.setVisible(false);
			}
		}
	}
}
