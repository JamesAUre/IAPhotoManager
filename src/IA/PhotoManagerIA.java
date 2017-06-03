package IA;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;

public class PhotoManagerIA extends JFrame {

	private JPanel contentPane;
	private JPanel Menu;
	private JPanel Login;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhotoManagerIA frame = new PhotoManagerIA();
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
	public PhotoManagerIA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Login = new JPanel();
		contentPane.add(Login, "name_188673621402909");
		Login.setLayout(null);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(225, 171, 115, 29);
		Login.add(btnLogin);
		
		Menu = new JPanel();
		contentPane.add(Menu, "name_188684952140286");
	}

}
