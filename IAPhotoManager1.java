package IA;

//import IA.backup.IAPhotoManager1;

//imports
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MouseInfo;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mysql.jdbc.Statement;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;

import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JScrollBar;

//PhotoManager class
public class IAPhotoManager1 extends JFrame implements ListSelectionListener {
	

	//public variables
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
	private JList list;
	private JScrollPane listScrollPane;
	
	List<Photo> currentselectedphotos = new ArrayList<Photo>();
	
	//Need to store login details on database
	String username = "james";
	String password = "pass";
	
	//for identifying which photo is being selected
	Photo currentphoto = null;
	
	//extending array (here to make it global)
	ArrayList<Photo> photos = new ArrayList<Photo>();
	
	//checking if photo selected
	boolean hasselectedphoto = false;
	JFileChooser filenav = new JFileChooser();
	
	//label to put photo in
	JLabel preview = null;
	
	/**
	 * Launch the application.
	 */
	
	//Main
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
	
	//mydatabase = databaseconnector
	DatabaseConnector myDatabase = new DatabaseConnector();
	private JTextField textSearch;
	
	//Switch panel method
	public void switchScreen(Screen Newscreen){
		
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
	
	//****
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
		
		//Help panel
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
		
		//Option panel
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
		
		//Menu panel
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
		Username.setBounds(182, 70, 146, 26);
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
					
					if(uname.equals(username) && pword.equals(password)){
					switchScreen(Screen.MENUSCREEN);
					}
					else{
						JOptionPane.showMessageDialog(frame, "Invalid username or password");
					}
				}
			}
		});
		Password.setBounds(182, 107, 146, 26);
		login.add(Password);
		Password.setColumns(10);
		
		//Login page username labels
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(78, 73, 91, 20);
		login.add(lblUsername);
		
		//Login page password labels
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(78, 110, 79, 20);
		login.add(lblPassword);
		
		//Login page title
		JLabel lblPhotoManager = new JLabel("Photo Manager");
		lblPhotoManager.setFont(new Font("Sitka Text", Font.BOLD, 27));
		lblPhotoManager.setBounds(102, 16, 219, 41);
		login.add(lblPhotoManager);
		
		//Usermenu panel initialization
		userMenu = new JPanel();
		userMenu.setName("Sort by:");
		
		
		//Login button
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(213, 149, 115, 29);
		login.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String uname=Username.getText();
				String pword=Password.getText();
				
				if(uname.equals(username) && pword.equals(password)){
				switchScreen(Screen.MENUSCREEN);
				}
				else{
					JOptionPane.showMessageDialog(frame, "Invalid username or password");
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(78, 149, 115, 29);
		login.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Username.setText("");
				Password.setText("");
			}
		});
		
		
		contentPane.add(userMenu, "name_175982178153740");
		userMenu.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome, " + username);
		lblWelcome.setBounds(106, 27, 165, 20);
		userMenu.add(lblWelcome);
		
		JLabel label = new JLabel("");
		label.setBounds(22, 16, 69, 20);
		userMenu.add(label);
		
		JLabel lblProfilePic = new JLabel("");
		lblProfilePic.setBounds(15, 0, 86, 75);
		userMenu.add(lblProfilePic);
		
		//read image
		Image temp = ImageIO.read(new File("C:\\Users\\James\\Desktop\\Year 13\\CS\\profilepic.jpg"));
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
		sortBox.setBounds(276, 49, 127, 26);
		userMenu.add(sortBox);
		
		JLabel label_1 = new JLabel("");
		preview = label_1;
		label_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		label_1.setBounds(15, 86, 145, 99);
		userMenu.add(label_1);
		
		//-----------------------------
		
		//connect to database
		boolean connected = myDatabase.connectphotodb("root","1234");
		String connectedmessage = "";
		if(connected == true){
			connectedmessage = "Connected";
		}
		else{
			connectedmessage = "Not connected";
		}
		
		
		//adding file to database (for testing purposes)
		/*boolean addID = myDatabase.addFile("C:\\Program Files\\Java\\jre8\\lib\\ext");
		if(addID == true){
			
			connectedmessage += "Added";
		}
		else{
			connectedmessage += "Not added";
		}
		
		boolean addUsername = myDatabase.changeUsername("IT WORKS");
		if(addID == true){
			
			connectedmessageUser += "Added";
		}
		else{
			connectedmessageUser += "Not added";
		}
		*/
		//Making extended array
		
		//making list with database
		list = new JList();
		/*
		listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScrollPane.setViewportView(list);
		*/
		//Putting photos on list*****
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setCellRenderer(new PhotoListRenderer());
		list.addListSelectionListener(this);
		refreshphotos();
		
		//initializing popupMenu for photo options
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setVisible(false);
		popupMenu.setBounds(0, 0, 69, 37);//0 0 69 37
		userMenu.add(popupMenu);
		
		JMenuItem mntmAddNewPhoto = new JMenuItem("Add new photo");
		
		//Add new photo command inside jpopup
		popupMenu.add(mntmAddNewPhoto);
		mntmAddNewPhoto.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	popupMenu.setVisible(false);
	                	//calling filenav JFileChooser
	                	int returnvalue = filenav.showOpenDialog(contentPane);
	                    //if file chosen
	                	if (returnvalue == JFileChooser.APPROVE_OPTION){
	                    	//Get path
	                		myDatabase.addFile(filenav.getSelectedFile().getAbsolutePath());
	                    	refreshphotos();
	                    }
	                }
	            }
	        );
		
		

		
		
		/*JMenuItem mntmDeletePhoto = new JMenuItem("Delete photo");
		popupMenu.add(mntmDeletePhoto);
		mntmDeletePhoto.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						myDatabase.deleteFile(list.get));
						Add delete photo option
					}
				}
				
				);*/
		
		JMenuItem mntmEditPhoto = new JMenuItem("Edit name");
		popupMenu.add(mntmEditPhoto);
		
		JMenuItem mntmTagPhoto = new JMenuItem("Tag photo");
		popupMenu.add(mntmTagPhoto);
		mntmTagPhoto.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	popupMenu.setVisible(false);
	                	String currenttags = "";
	                	if(currentselectedphotos.size() > 0 && currentselectedphotos.get(0).getTags() != null) {
	                		currenttags = currentselectedphotos.get(0).getTags(); 
	                	}
	                	String tags = (String) JOptionPane.showInputDialog(frame, "tessst", "differenttesst", JOptionPane.PLAIN_MESSAGE, null, null, currenttags);
	                	System.out.println("gotmsg " + tags);
	                	for (Photo p: currentselectedphotos) {
	                		p.setTags(tags);
	                		myDatabase.editPhoto(p);
	                	}
	                	refreshphotos();
	                }
	}
	        );
		
		JMenuItem mntmPublishPhoto = new JMenuItem("Publish selected");
		popupMenu.add(mntmPublishPhoto);
		mntmPublishPhoto.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	popupMenu.setVisible(false);
	                	//calling filenav JFileChooser
	                	if(currentselectedphotos.isEmpty()) {
	                		System.out.println("no photo selected for consolidation");
	                		return;
	                	}
	                	filenav.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	                	int returnvalue = filenav.showOpenDialog(contentPane);
	                    //if file chosen
	                	if (returnvalue == JFileChooser.APPROVE_OPTION){
	                    	//Get path
	                		
	                		System.out.println(filenav.getCurrentDirectory().getPath());
	                		
	                		boolean consolidatePhotos = FileHelper.consolidatePhotos(currentselectedphotos, filenav.getCurrentDirectory().getAbsolutePath());
	                    	if(consolidatePhotos == true) {
	                    		System.out.println("Files moved successfully");
	                    	}
	                    	else System.out.println("failed");
	                    }
	                	filenav.setFileSelectionMode(JFileChooser.FILES_ONLY);
	                }
	                
	            }
	        );
		
		
		
		
		list.setBounds(203, 84, 184, 101); 
		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setViewportView(list);
		JScrollPane myscrollpane = new JScrollPane(list);
		myscrollpane.setBounds(203, 84, 184, 101); 
		userMenu.add(myscrollpane);
		
		//rightclicklistener
		list.addMouseListener( new MouseAdapter()
	     {
	        public void mousePressed(MouseEvent e)
	        {
	        	
	            if ( SwingUtilities.isRightMouseButton(e) )
	            {
	            	
	            	//set location on point clicked
	                popupMenu.setLocation(MouseInfo.getPointerInfo().getLocation()	                		);
	                int selectedindex = list.locationToIndex(e.getPoint());
	                popupMenu.setVisible(true);
	                
	                popupMenu.addMouseListener(new MouseAdapter() {
	                	public void mousePressed(MouseEvent e) {
	                		popupMenu.setVisible(false);
	                	}
	                });
	                
	                //checks which row selected
	                System.out.println("You've selected row: " + selectedindex);   
	            }
	        }
	     });
		
		userMenu.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
            	popupMenu.setVisible(false);
            }
        });    
	                
		//Search engine
		textSearch = new JTextField();
		textSearch.setBounds(276, 10, 127, 26);
		userMenu.add(textSearch);
		textSearch.setColumns(10);
		userMenu.setVisible(false);
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("Searching..");
					String searching = textSearch.getText();
					myDatabase.searchengine(searching);
				}
			}
		});
		
		System.out.println("Search");
		myDatabase.searchengine("James");
		
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
		btnCustomizeMenu.setBounds(238, 73, 165, 29);
		Options.add(btnCustomizeMenu);
		btnCustomizeMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchScreen(Screen.MENUOPSCREEN);
			}
		});
		
		Help = new JPanel();
		contentPane.add(Help, "name_626028536091847");
		Help.setLayout(null);
		
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
		btnSetUsername.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                	myDatabase.addUsername(txtJamesUre.getText());
	                    }
	                }
	            
	        );
		
		JButton btnSetPassword = new JButton("Set password");
		btnSetPassword.setBounds(227, 58, 137, 29);
		profileOptions.add(btnSetPassword);
		btnSetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtPassword.getText().length()>5){
					password = txtPassword.getText();
				}
				else{
					JOptionPane.showMessageDialog(frame, "Password does not meet validation rules");
				}
			}
		});
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(24, 62, 80, 20);
		profileOptions.add(lblPassword_1);
		
		txtPassword = new JPasswordField();
		String password = "password";
		txtPassword.setText(password);
		txtPassword.setBounds(112, 57, 100, 26);
		profileOptions.add(txtPassword);
		txtPassword.setColumns(10);
		

		
		JLabel label_2 = new JLabel("");
		label_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		label_2.setBounds(74, 100, 131, 85);
		profileOptions.add(label_2);
		
		menuOptions = new JPanel();
		contentPane.add(menuOptions, "name_628174016463539");
		
		switchScreen(Screen.LOGINSCREEN);
	}
		
	//highlighting row on JList (selecting value check)
	public void valueChanged(ListSelectionEvent e) {
        //ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		JList list = (JList)e.getSource();
		
		//UPDATEDFORPHOTOS
		// incase this code doesn't work - list.getSelectedValues();
		currentselectedphotos.clear();
		for(Object o : list.getSelectedValuesList()) {
			currentselectedphotos.add((Photo) o);
		}
		//If user has not selected a directory
		if (currentselectedphotos.size() == 0) {
			System.out.println("Has no value");
			hasselectedphoto = false;
		}
		//If they have selected a directory
		else {
			currentphoto = currentselectedphotos.get(0);
			hasselectedphoto = true;
		}
		
		//If the list value errors/not selected
		if(list.getSelectedIndex() == -1){
			System.out.println("Has no value");
			hasselectedphoto = false;
		}
		else{
			//
	        currentphoto = (Photo)(list).getSelectedValue();
	        hasselectedphoto = true;
		}
		showpreview();
 }
	
	//Displaying preview photo in JLabel
	void showpreview(){
		if (hasselectedphoto == true){
			Image temp;
			try {
				//finding path of directory in database
				temp = ImageIO.read(new File(currentphoto.getPath()));
				preview.setIcon(new ImageIcon(temp.getScaledInstance(145,99 ,1000)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IMAGE ERROR");
				e.printStackTrace();
			}	
		}
		//otherwise no photo displayed
		else {
			preview.setIcon(null);
		}
		
	}
	
	//Replenishes JList with database
	private void refreshphotos(){
		photos = myDatabase.getPhotos();
		list.setListData(photos.toArray(new Photo[photos.size()]));
	}
	
	
	//Method calling popupmenu (menu photo options)
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			
			//when right click
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			
			//when release click
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			//displays popup with coordinates of mouse
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
	});
	}
}