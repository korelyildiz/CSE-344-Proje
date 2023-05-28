import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.*;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.util.List;
import javax.swing.event.*;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JTextField textField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private data myData = new data();
	
	private String userType = "";
	private String selectedUser = "";
	
	private int scoreLevel1 = 0;
	private int scoreLevel2 = 0;
	private int scoreLevel3 = 0;
	private int scoreLevel1_2 = 0;
	private int scoreLevel2_2 = 0;
	private int scoreLevel3_2 = 0;
	private int scoreLevel1_3 = 0;
	private int scoreLevel2_3 = 0;
	private int scoreLevel3_3 = 0;
	
	public Window() {
		setTitle("Group6_CSE344");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 582);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 10, 780, 525);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel() {
			ImageIcon imageIcon = new ImageIcon("CSE344-Project/src/Images/BackgroundMain.jpeg");
		    Image image = imageIcon.getImage();

		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		    }
		};
		panel_1.setBackground(new Color(255, 255, 255));
		layeredPane.add(panel_1);
		
		JPanel Login = new JPanel();
		layeredPane.add(Login);
		Login.setLayout(null);
		
		JPanel Register = new JPanel();
		layeredPane.add(Register);
		Register.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(157, 128, 465, 34);
		Register.add(textField);
		
		JLabel labelUsername_1 = new JLabel("Username");
		labelUsername_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelUsername_1.setBounds(157, 73, 465, 46);
		Register.add(labelUsername_1);
		
		JLabel labelPassword_1 = new JLabel("Password");
		labelPassword_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelPassword_1.setBounds(157, 195, 465, 46);
		Register.add(labelPassword_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordField.setBounds(157, 251, 465, 34);
		Register.add(passwordField);
		
		JRadioButton AuthorizedRadioButton = new JRadioButton("Authorized User");
		buttonGroup.add(AuthorizedRadioButton);
		AuthorizedRadioButton.setSelected(true);
		AuthorizedRadioButton.setBounds(239, 305, 132, 34);
		Register.add(AuthorizedRadioButton);
		
		JRadioButton UnauthorizedRadioButton = new JRadioButton("Unauthorized User");
		buttonGroup.add(UnauthorizedRadioButton);
		UnauthorizedRadioButton.setBounds(417, 309, 142, 27);
		Register.add(UnauthorizedRadioButton);
		
		JLabel FailedRegister = new JLabel("A user with that name already exists. Try again.");
		FailedRegister.setForeground(Color.RED);
		FailedRegister.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		FailedRegister.setBounds(157, 50, 465, 27);
		Register.add(FailedRegister);
		FailedRegister.setVisible(false);
		
		JButton RegButton = new JButton("Register");
		RegButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AuthorizedRadioButton.isSelected()) {
					String str = new String(passwordField.getPassword());
					myData.register(textField.getText(), str, AuthorizedRadioButton.getText());
				}else if(UnauthorizedRadioButton.isSelected()) {
					String str = new String(passwordField.getPassword());
					myData.register(textField.getText(), str,UnauthorizedRadioButton.getText());
				}
				if(myData.registerCorrect == false) {
					FailedRegister.setVisible(true);
				}else {
					layeredPane.removeAll();
					layeredPane.add(panel_1);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
				textField.setText("");
				passwordField.setText("");
			}
		});
		RegButton.setBounds(310, 379, 142, 34);
		Register.add(RegButton);
		
		JButton RegretButton = new JButton("Return");
		RegretButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
				FailedRegister.setVisible(false);
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		RegretButton.setBounds(22, 457, 132, 34);
		Register.add(RegretButton);
		
		
		
		JPanel AuthorizedMenu = new JPanel();
		layeredPane.add(AuthorizedMenu);
		AuthorizedMenu.setLayout(null);
		
		JPanel UnauthorizedMenu = new JPanel();
		layeredPane.add(UnauthorizedMenu);
		UnauthorizedMenu.setLayout(null);
		
		
		Level1 Level1 = new Level1();
		layeredPane.add(Level1);
		Level1.setLayout(null);
		
        Level2 Level2 = new Level2();
        layeredPane.add(Level2);
		Level1.setLayout(null);
		
		Level3 Level3 = new Level3();
        layeredPane.add(Level3);
		Level1.setLayout(null);
        
		Timer timer = new Timer(500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(Level1.endgame || Level2.endgame || Level3.endgame) {
					if(Level2.endgame) {
						scoreLevel2 = Level2.getScore();
						myData.setScore(myData.userName,myData.Category,2, scoreLevel2);
						Level2.endgame = false;
						Level2.removeAll();
						Level2.repaint();
						Level2.revalidate();
					}else if(Level3.endgame) {
						scoreLevel3 = Level3.getScore();
						myData.setScore(myData.userName,myData.Category,3, scoreLevel3);
						Level3.endgame = false;
						Level3.removeAll();
						Level3.repaint();
						Level3.revalidate();
					}else if(Level1.endgame) {
						scoreLevel1 = Level1.getScore();
						myData.setScore(myData.userName,myData.Category,1, scoreLevel1);
						Level1.endgame = false;
						Level1.removeAll();
						Level1.repaint();
						Level1.revalidate();
					}
					
					layeredPane.removeAll();
					layeredPane.add(UnauthorizedMenu);
					layeredPane.repaint();
					layeredPane.revalidate();
					
					
					
					
				}
			}
		});
		
		JButton ChooseLevel1 = new JButton("Level 1");
		ChooseLevel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(myData.Category.contentEquals("2")) {
					
				}else if(myData.Category.contentEquals("3")) {
					
				}else {
					timer.stop();
					layeredPane.removeAll();
					layeredPane.add(Level1);
					Level1.start();
					layeredPane.repaint();
					layeredPane.revalidate();
					timer.start();
				}
			}
		});
		ChooseLevel1.setBounds(95, 220, 134, 50);
		UnauthorizedMenu.add(ChooseLevel1);
		
		JButton ChooseLevel2 = new JButton("Level 2");
		ChooseLevel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(myData.Category.contentEquals("2")) {
					
				}else if(myData.Category.contentEquals("3")) {
					
				}else {
					timer.stop();
					layeredPane.removeAll();
					layeredPane.add(Level2);
					Level2.start();
					layeredPane.repaint();
					layeredPane.revalidate();
					timer.start();
				}
			}
		});
		ChooseLevel2.setBounds(337, 220, 134, 50);
		UnauthorizedMenu.add(ChooseLevel2);
		
		JButton ChooseLevel3 = new JButton("Level 3");
		ChooseLevel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(myData.Category.contentEquals("2")) {
					
				}else if(myData.Category.contentEquals("3")) {
					
				}else {
					timer.stop();
					layeredPane.removeAll();
					layeredPane.add(Level3);
					Level3.start();
					layeredPane.repaint();
					layeredPane.revalidate();
					timer.start();
				}
			}
		});
		ChooseLevel3.setBounds(571, 220, 134, 50);
		UnauthorizedMenu.add(ChooseLevel3);
		
		JButton unautretButton = new JButton("Log out");
		unautretButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
				textUsername.setText("");
				textPassword.setText("");
				myData.logout();
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		unautretButton.setBounds(337, 366, 134, 54);
		UnauthorizedMenu.add(unautretButton);
		
		JPanel Shop = new JPanel();
		layeredPane.add(Shop);
		Shop.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("This label will show a picture and \r\nthe number of questions that can be bought\r\n\r\n Such as buy 10 Questions, buy 100 Questions, etc.");
		lblNewLabel_5.setBounds(98, 60, 603, 237);
		Shop.add(lblNewLabel_5);
		
		JButton ShopretButton = new JButton("Return");
		ShopretButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(AuthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		ShopretButton.setBounds(304, 365, 179, 56);
		Shop.add(ShopretButton);
		
		JPanel Progress = new JPanel();
		layeredPane.add(Progress);
		Progress.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Progress");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_4.setBackground(new Color(255, 255, 128));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(93, 31, 608, 41);
		Progress.add(lblNewLabel_4);
		
		JButton progretButton = new JButton("Return");
		progretButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(AuthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		progretButton.setBounds(327, 401, 155, 41);
		Progress.add(progretButton);
		
		JLabel lblNewLabel_6 = new JLabel("Category 5-7");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(103, 101, 148, 31);
		Progress.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Category 7-9");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(323, 101, 148, 31);
		Progress.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_2 = new JLabel("Category 9-12");
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_6_2.setBounds(577, 101, 148, 31);
		Progress.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_7 = new JLabel("Level 1:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(113, 142, 155, 31);
		Progress.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Level 2:");
		lblNewLabel_7_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_1.setBounds(113, 183, 155, 31);
		Progress.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Level 3:");
		lblNewLabel_7_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_2.setBounds(113, 224, 155, 31);
		Progress.add(lblNewLabel_7_2);
		
		JLabel lblNewLabel_7_3 = new JLabel("Level 1:");
		lblNewLabel_7_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_3.setBounds(327, 142, 155, 31);
		Progress.add(lblNewLabel_7_3);
		
		JLabel lblNewLabel_7_4 = new JLabel("Level 2:");
		lblNewLabel_7_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_4.setBounds(327, 183, 155, 31);
		Progress.add(lblNewLabel_7_4);
		
		JLabel lblNewLabel_7_5 = new JLabel("Level 3:");
		lblNewLabel_7_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_5.setBounds(327, 224, 155, 31);
		Progress.add(lblNewLabel_7_5);
		
		JLabel lblNewLabel_7_6 = new JLabel("Level 1:");
		lblNewLabel_7_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_6.setBounds(577, 142, 155, 31);
		Progress.add(lblNewLabel_7_6);
		
		JLabel lblNewLabel_7_7 = new JLabel("Level 2:");
		lblNewLabel_7_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_7.setBounds(577, 183, 155, 31);
		Progress.add(lblNewLabel_7_7);
		
		JLabel lblNewLabel_7_8 = new JLabel("Level 3:");
		lblNewLabel_7_8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7_8.setBounds(577, 224, 155, 31);
		Progress.add(lblNewLabel_7_8);
		
		JPanel Category = new JPanel();
		layeredPane.add(Category);
		Category.setLayout(null);
		
		JButton cat5_7Button = new JButton("Change Category");
		cat5_7Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myData.changeCategory(selectedUser, "1");
				layeredPane.removeAll();
				layeredPane.add(AuthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		cat5_7Button.setBounds(46, 297, 167, 58);
		Category.add(cat5_7Button);
		
		
		JButton cat7_9Button = new JButton("Change Category");
		cat7_9Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myData.changeCategory(selectedUser, "2");
				layeredPane.removeAll();
				layeredPane.add(AuthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		cat7_9Button.setBounds(318, 295, 167, 63);
		Category.add(cat7_9Button);
		
		JButton cat9_12Button = new JButton("Change Category");
		cat9_12Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myData.changeCategory(selectedUser, "3");
				layeredPane.removeAll();
				layeredPane.add(AuthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		cat9_12Button.setBounds(564, 292, 167, 63);
		Category.add(cat9_12Button);
		
		JButton catreturnButton = new JButton("Return");
		catreturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(AuthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		catreturnButton.setBounds(318, 424, 167, 58);
		Category.add(catreturnButton);
		
		JLabel lblNewLabel_2 = new JLabel("A Picture and The age will be here");
		lblNewLabel_2.setBounds(46, 79, 197, 177);
		Category.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("A Picture and The age will be here");
		lblNewLabel_2_1.setBounds(288, 79, 197, 177);
		Category.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("A Picture and The age will be here");
		lblNewLabel_2_2.setBounds(525, 79, 206, 177);
		Category.add(lblNewLabel_2_2);
		
		JPanel BindAccount = new JPanel();
		layeredPane.add(BindAccount);
		
		JButton shopButton = new JButton("Buy Questions");
		shopButton.setEnabled(false);
		shopButton.setBounds(63, 244, 140, 54);
		AuthorizedMenu.add(shopButton);
		
		JButton CategoryButton = new JButton("Change Category");
		CategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Category);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		CategoryButton.setEnabled(false);
		CategoryButton.setBounds(583, 244, 140, 54);
		AuthorizedMenu.add(CategoryButton);
		
		JButton ProgressButton = new JButton("View Progress");
		ProgressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				lblNewLabel_7.setText("Level 1: "+myData.getScore(selectedUser,"1",1));
				lblNewLabel_7_1.setText("Level 2: "+myData.getScore(selectedUser,"1",2));
				lblNewLabel_7_2.setText("Level 3: "+myData.getScore(selectedUser,"1",3));
				lblNewLabel_7_3.setText("Level 3: "+myData.getScore(selectedUser,"2",1));
				lblNewLabel_7_4.setText("Level 3: "+myData.getScore(selectedUser,"2",2));
				lblNewLabel_7_5.setText("Level 3: "+myData.getScore(selectedUser,"2",3));
				lblNewLabel_7_6.setText("Level 3: "+myData.getScore(selectedUser,"3",1));
				lblNewLabel_7_7.setText("Level 3: "+myData.getScore(selectedUser,"3",2));
				lblNewLabel_7_8.setText("Level 3: "+myData.getScore(selectedUser,"3",3));	
				
				layeredPane.removeAll();
				layeredPane.add(Progress);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		ProgressButton.setBounds(325, 244, 140, 54);
		ProgressButton.setEnabled(false);
		AuthorizedMenu.add(ProgressButton);
		
				
		textField_1 = new JTextField();
		textField_1.setBounds(166, 196, 465, 34);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_1.setColumns(10);
		BindAccount.add(textField_1);
		
		JLabel labelUsername_2 = new JLabel("Username");
		labelUsername_2.setBounds(166, 141, 465, 46);
		labelUsername_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		BindAccount.add(labelUsername_2);
		
		JLabel labelPassword_2 = new JLabel("Password");
		labelPassword_2.setBounds(166, 263, 465, 46);
		labelPassword_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		BindAccount.add(labelPassword_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(166, 319, 465, 34);
		passwordField_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		BindAccount.add(passwordField_1);
		
		JLabel labelBind = new JLabel("Enter the account information of the account you want to bind");
		labelBind.setBounds(123, 97, 594, 39);
		labelBind.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BindAccount.add(labelBind);
		
		JLabel lblNewLabel_3 = new JLabel("You need to bind an account in order to use the functions of the authorized user.");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel_3.setBounds(52, 29, 718, 46);
		BindAccount.add(lblNewLabel_3);
		
		
		JButton AuthorizedUserButton = new JButton("Authorized User");
		AuthorizedUserButton.setBounds(301, 248, 187, 55);
		AuthorizedUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Login);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		panel_1.setLayout(null);
		panel_1.add(AuthorizedUserButton);
		
		JButton UnauthorizedUserButton = new JButton("Unauthorized User");
		UnauthorizedUserButton.setBounds(301, 325, 187, 55);
		UnauthorizedUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Login);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		panel_1.add(UnauthorizedUserButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Window.class.getResource("/Images/Yeditepelogo.jpg")));
		lblNewLabel.setBounds(262, 56, 258, 156);
		panel_1.add(lblNewLabel);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textUsername.setBounds(162, 137, 465, 34);
		Login.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelUsername.setBounds(162, 82, 465, 46);
		Login.add(labelUsername);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelPassword.setBounds(162, 204, 465, 46);
		Login.add(labelPassword);
		
		textPassword = new JPasswordField();
		textPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textPassword.setBounds(162, 260, 465, 34);
		Login.add(textPassword);
		
		JButton ReturnButton = new JButton("Return");
		ReturnButton.setBounds(653, 469, 117, 46);
		ReturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textPassword.setText("");
				textField.setText("");
				passwordField.setText("");
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		Login.add(ReturnButton);
		
		JList<String> jList = new JList<>();
		ListSelectionListener selectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the selection event is completed (not adjusting)
                if (!e.getValueIsAdjusting()) {
                    selectedUser = jList.getSelectedValue();
                    shopButton.setEnabled(true);
                    ProgressButton.setEnabled(true);
                    CategoryButton.setEnabled(true);
                    System.out.println(selectedUser);
                }
            }
        };
        jList.addListSelectionListener(selectionListener);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 56, 210, 167);
		AuthorizedMenu.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Bound Accounts");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(38, 20, 210, 27);
		AuthorizedMenu.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Bind Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(BindAccount);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(379, 56, 285, 54);
		AuthorizedMenu.add(btnNewButton);
		
		JButton btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myData.removeAccount(selectedUser);
				DefaultListModel<String> listModel = new DefaultListModel<>();

		        for (String element : myData.bound) {
		            listModel.addElement(element);
		        }

		        jList.setModel(listModel);
		        scrollPane.setViewportView(jList);
			}
		});
		btnRemoveAccount.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnRemoveAccount.setBounds(379, 150, 285, 54);
		AuthorizedMenu.add(btnRemoveAccount);
		
		JButton autretButton = new JButton("Log out");
		autretButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textPassword.setText("");
				textField.setText("");
				passwordField.setText("");
				jList.clearSelection();
				shopButton.setEnabled(false);
                ProgressButton.setEnabled(false);
                CategoryButton.setEnabled(false);
				myData.logout();
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		autretButton.setBounds(325, 392, 140, 54);
		AuthorizedMenu.add(autretButton);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = new String(textPassword.getPassword());
				myData.login(textUsername.getText(), str);
				userType = myData.userType;
				if(userType.contentEquals("Authorized User")) {
					layeredPane.removeAll();
					if(myData.Bound(myData.userName)) {
						layeredPane.add(AuthorizedMenu);
						layeredPane.repaint();
						layeredPane.revalidate();
					}else {
						layeredPane.add(BindAccount);
						layeredPane.repaint();
						layeredPane.revalidate();
					}
									
				}else if(userType.contentEquals("Unauthorized User")) {
					layeredPane.removeAll();
					layeredPane.add(UnauthorizedMenu);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
				
				DefaultListModel<String> listModel = new DefaultListModel<>();

		        for (String element : myData.bound) {
		            listModel.addElement(element);
		        }

		        jList.setModel(listModel);
		        scrollPane.setViewportView(jList);
		        
			}
		});
		LoginButton.setBounds(323, 358, 154, 46);
		Login.add(LoginButton);
		
		JButton BindButton = new JButton("Bind Account");
		BindButton.setBounds(321, 411, 178, 68);
		BindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String boundpass = new String(passwordField_1.getPassword());
				myData.bindAccount(textField_1.getText(), boundpass);
				
				DefaultListModel<String> listModel = new DefaultListModel<>();

		        for (String element : myData.bound) {
		            listModel.addElement(element);
		        }

		        jList.setModel(listModel);
		        scrollPane.setViewportView(jList);
				
				layeredPane.removeAll();
				layeredPane.add(AuthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		BindAccount.setLayout(null);
		BindButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		BindAccount.add(BindButton);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
				textUsername.setText("");
				textPassword.setText("");
				layeredPane.removeAll();
				layeredPane.add(Register);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		RegisterButton.setBounds(21, 469, 117, 46);
		Login.add(RegisterButton);
		
		JLabel labelRegister = new JLabel("Don't have an account?");
		labelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegister.setBounds(0, 441, 155, 18);
		Login.add(labelRegister);
		
		JCheckBox LoginCheckBox = new JCheckBox("Remember me");
		LoginCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		LoginCheckBox.setBounds(162, 309, 154, 21);
		Login.add(LoginCheckBox);
		
		
		
		
		
		
		
		
		
	
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
