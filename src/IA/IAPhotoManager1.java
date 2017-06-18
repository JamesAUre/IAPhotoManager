package IA;

//import IA.backup.IAPhotoManager1;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

public class IAPhotoManager1 extends JFrame {

	private JFrame frame = new JFrame();
	private JPanel contentPane;
	private JTextField Username;
	private JTextField Password;
	private JPanel login;
	private JPanel userMenu;
	public enum Screen { LOGINSCREEN, MENUSCREEN, OPTIONSCREEN, HELPSCREEN, PROFILEOPSCREEN, MENUOPSCREEN };
	private Screen currentscreen;
	private static JMenuBar menuBar;
	private JPanel Options;
	private JPanel profileOptions;
	private JPanel menuOptions;
	private JPanel Help;
	private JTextField txtJamesUre;
	private JTextField txtPassword;
	
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
	DatabaseConnector myDatabase = new DatabaseConnector();
	
	
	public void switchScreen(Screen Newscreen){
		//UserMenu.setVisible(false);
		
		login.setVisible(false);
		userMenu.setVisible(false);
		Options.setVisible(false);
		Help.setVisible(false);
		profileOptions.setVisible(false);
		menuOptions.setVisible(false);
		switch (Newscreen){
			case LOGINSCREEN:{
				login.setVisible(true);
				break;
			}
			case MENUSCREEN:{
				userMenu.setVisible(true);
				menuBar.setVisible(true);
				break;
			}
			case OPTIONSCREEN:{
				Options.setVisible(true);
				break;
			}
			case HELPSCREEN:{
				Help.setVisible(true);
				break;
			}
			case PROFILEOPSCREEN:{
				profileOptions.setVisible(true);
				break;
			}
			case MENUOPSCREEN:{
				menuOptions.setVisible(true);
				break;
			}
		}
		currentscreen = Newscreen;
	}
	
	public IAPhotoManager1() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		//Menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//Logout panel
		JMenuItem mnLogOut = new JMenuItem("Log Out");
		mnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mnLogOut.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnLogOut.setBackground(null);
			}
		});
		menuBar.add(mnLogOut);
		mnLogOut.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                    switchScreen(Screen.LOGINSCREEN);
	                    menuBar.setVisible(false);
	                }
	            }
	        );
		
		JMenuItem mnHelp = new JMenuItem("Help");
		menuBar.add(mnHelp);
		mnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mnHelp.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnHelp.setBackground(null);
			}
		});
		mnHelp.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                    switchScreen(Screen.HELPSCREEN);
	                }
	            }
	        );
		
		
		JMenuItem mnOptions = new JMenuItem("Options");
		menuBar.add(mnOptions);
		mnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mnOptions.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnOptions.setBackground(null);
			}
		});
		mnOptions.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                    switchScreen(Screen.OPTIONSCREEN);
	                }
	            }
	        );
		
		JMenuItem mnMenu = new JMenuItem("Menu");
		menuBar.add(mnMenu);
		mnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				mnMenu.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnMenu.setBackground(null);
			}
		});
		mnMenu.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                    switchScreen(Screen.MENUSCREEN);
	                }
	            }
	        );
		//Login panel
		login = new JPanel();
		contentPane.add(login, "name_175979140570239");
		login.setLayout(null);
		menuBar.setVisible(false);
		
		//Username field
		Username = new JTextField();
		Username.setBounds(182, 83, 146, 26);
		login.add(Username);
		Username.setColumns(10);
		
		//Password field
		Password = new JPasswordField();
		Password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String uname=Username.getText();
					String pword=Password.getText();
					
					if(uname.equals("name") && pword.equals("password")){
					switchScreen(Screen.MENUSCREEN);
					}
					else{
						JOptionPane.showMessageDialog(frame, "Invalid username or password");
					}
				}
			}
		});
		Password.setBounds(182, 125, 146, 26);
		login.add(Password);
		Password.setColumns(10);
		
		//Login page username labels
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(78, 86, 91, 20);
		login.add(lblUsername);
		
		//Login page password labels
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(78, 128, 79, 20);
		login.add(lblPassword);
		
		//Login page title
		JLabel lblPhotoManager = new JLabel("Photo Manager");
		lblPhotoManager.setFont(new Font("Sitka Text", Font.BOLD, 27));
		lblPhotoManager.setBounds(103, 29, 219, 41);
		login.add(lblPhotoManager);
		
		//Usermenu panel initialization
		userMenu = new JPanel();
		userMenu.setName("Sort by:");
		
		
		//Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(213, 167, 115, 29);
		login.add(btnLogin);
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
		login.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Username.setText("");
				Password.setText("");
			}
		});
		
		
		contentPane.add(userMenu, "name_175982178153740");
		userMenu.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome, James Ure");
		lblWelcome.setBounds(106, 27, 165, 20);
		userMenu.add(lblWelcome);
		
		JLabel label = new JLabel("");
		label.setBounds(22, 16, 69, 20);
		userMenu.add(label);
		
		JLabel lblProfilePic = new JLabel("");
		lblProfilePic.setBounds(15, 0, 86, 75);
		userMenu.add(lblProfilePic);
		Image temp = ImageIO.read(new File("C:\\Users\\James Ure\\Desktop\\photo.jpg"));
		lblProfilePic.setIcon(new ImageIcon(temp.getScaledInstance(86, 75,1000)));
		
		String optionBox[] = new String[6];
		
		optionBox[0] = "Sort by:";
		optionBox[1] = "Alphabetical order";
		optionBox[2] = "Date";
		optionBox[3] = "Location";
		optionBox[4] = "Event";
		optionBox[5] = "Tagged";
		
		JComboBox sortBox = new JComboBox(optionBox);
		sortBox.setName("Sort by:");
		sortBox.setBounds(276, 24, 127, 26);
		userMenu.add(sortBox);
		
		JLabel label_1 = new JLabel("");
		label_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		label_1.setBounds(15, 86, 145, 99);
		userMenu.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		label_2.setBounds(187, 63, 216, 122);
		userMenu.add(label_2);
		userMenu.setVisible(false);
		
		Options = new JPanel();
		contentPane.add(Options, "name_281219183087217");
		Options.setLayout(null);
		
		JButton btnProfileOptions = new JButton("Profile options");
		btnProfileOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchScreen(Screen.PROFILEOPSCREEN);
			}
		});
		btnProfileOptions.setBounds(15, 73, 165, 29);
		Options.add(btnProfileOptions);
		
		JButton btnCustomizeMenu = new JButton("Customize menu");
		btnCustomizeMenu.setBounds(212, 73, 165, 29);
		Options.add(btnCustomizeMenu);
		btnCustomizeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchScreen(Screen.MENUOPSCREEN);
			}
		});
		
		Help = new JPanel();
		contentPane.add(Help, "name_626028536091847");
		Help.setLayout(null);
		
		boolean connected = myDatabase.connect();
		String connectedmessage = "";
		if(connected == true){
			connectedmessage = "Connected";
		}
		else{
			connectedmessage = "Not connected";
		}
		boolean addID = myDatabase.addFile("C:\\Program Files\\Java\\jre8\\lib\\ext");
		if(addID == true){
			
			connectedmessage += "Added";
		}
		else{
			connectedmessage += "Not added";
		}
		JLabel lblT = new JLabel(connectedmessage);
		lblT.setBounds(47, 16, 197, 20);
		Help.add(lblT);
		
		profileOptions = new JPanel();
		contentPane.add(profileOptions, "name_628153558187998");
		profileOptions.setLayout(null);
		
		JButton btnChangeProfilePic = new JButton("Change profile pic");
		btnChangeProfilePic.setBounds(220, 105, 177, 29);
		profileOptions.add(btnChangeProfilePic);
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(24, 21, 91, 20);
		profileOptions.add(lblUsername_1);
		
		txtJamesUre = new JTextField();
		txtJamesUre.setText("name");
		txtJamesUre.setBounds(112, 19, 100, 26);
		profileOptions.add(txtJamesUre);
		txtJamesUre.setColumns(10);
		
		JButton btnSetUsername = new JButton("Set username");
		btnSetUsername.setBounds(223, 19, 141, 29);
		profileOptions.add(btnSetUsername);
		
		JButton btnSetPassword = new JButton("Set password");
		btnSetPassword.setBounds(227, 58, 137, 29);
		profileOptions.add(btnSetPassword);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(24, 62, 80, 20);
		profileOptions.add(lblPassword_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setText("password");
		txtPassword.setBounds(112, 57, 100, 26);
		profileOptions.add(txtPassword);
		txtPassword.setColumns(10);
		
		menuOptions = new JPanel();
		contentPane.add(menuOptions, "name_628174016463539");
		
		switchScreen(Screen.LOGINSCREEN);
	}
}
