package IA;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.*;

public class IAPhotoManager1 extends JFrame {

	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField Username;
	private JTextField Password;
	private JPanel Login;
	private JPanel UserMenu;
	public enum Screen { LOGINSCREEN, MENUSCREEN };
	private Screen currentscreen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IAPhotoManager1 frame = new IAPhotoManager1();
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
	
	public void switchScreen(Screen Newscreen){
		UserMenu.setVisible(false);
		Login.setVisible(false);
		switch (Newscreen){
			case LOGINSCREEN:{
				Login.setVisible(true);
				break;
			}
			case MENUSCREEN:{
				UserMenu.setVisible(true);
				break;
			}
		}
		currentscreen = Newscreen;
	}
	public IAPhotoManager1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Login = new JPanel();
		contentPane.add(Login, "name_175979140570239");
		Login.setLayout(null);
		
		Username = new JTextField();
		Username.setBounds(182, 83, 146, 26);
		Login.add(Username);
		Username.setColumns(10);
		
		Password = new JPasswordField();
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
		
		UserMenu = new JPanel();
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(213, 167, 115, 29);
		Login.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname=Username.getText();
				String pword=Password.getText();
				
				if(uname.equals("name") && pword.equals("password")){
				switchScreen(Screen.MENUSCREEN);
				}
				else{
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(78, 167, 115, 29);
		Login.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Username.setText("");
				Password.setText("");
			}
		});
		
		
		contentPane.add(UserMenu, "name_175982178153740");
		UserMenu.setVisible(false);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "name_175985911456071");
		
		JMenuBar menuBar = new JMenuBar();
		panel_2.add(menuBar);
		
		JMenu mnLogOut = new JMenu("Log Out");
		menuBar.add(mnLogOut);
		
		JMenu mnForgotYourPassword = new JMenu("Forgot your password?");
		menuBar.add(mnForgotYourPassword);
		panel_2.setVisible(false);
		
		switchScreen(Screen.LOGINSCREEN);
	}
}
