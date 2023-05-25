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

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;


public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JTextField textField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField textField_2;
	
	

	/**
	 * Launch the application.
	 */
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}**/

	/**
	 * Create the frame.
	 */
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
		
		JButton RegButton = new JButton("Register");
		RegButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		RegButton.setBounds(310, 379, 142, 34);
		Register.add(RegButton);
		
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
					layeredPane.removeAll();
					layeredPane.add(UnauthorizedMenu);
					layeredPane.repaint();
					layeredPane.revalidate();
					Level1.endgame = false;
					Level1.removeAll();
					Level1.repaint();
					Level1.revalidate();
					
				}
			}
		});
		
		JButton ChooseLevel1 = new JButton("Level 1");
		ChooseLevel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				layeredPane.removeAll();
				layeredPane.add(Level1);
				Level1.start();
				layeredPane.repaint();
				layeredPane.revalidate();
				timer.start();
			}
		});
		ChooseLevel1.setBounds(95, 220, 134, 50);
		UnauthorizedMenu.add(ChooseLevel1);
		
		JButton ChooseLevel2 = new JButton("Level 2");
		ChooseLevel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				layeredPane.removeAll();
				layeredPane.add(Level2);
				Level2.start();
				layeredPane.repaint();
				layeredPane.revalidate();
				timer.start();
			}
		});
		ChooseLevel2.setBounds(337, 220, 134, 50);
		UnauthorizedMenu.add(ChooseLevel2);
		
		JButton ChooseLevel3 = new JButton("Level 3");
		ChooseLevel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				layeredPane.removeAll();
				layeredPane.add(Level3);
				Level3.start();
				layeredPane.repaint();
				layeredPane.revalidate();
				timer.start();
			}
		});
		ChooseLevel3.setBounds(571, 220, 134, 50);
		UnauthorizedMenu.add(ChooseLevel3);
		
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
		
		JLabel lblNewLabel_4 = new JLabel("This will show the progress of the unauthorized user");
		lblNewLabel_4.setBackground(new Color(255, 255, 128));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(93, 31, 608, 310);
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
		
		JPanel Category = new JPanel();
		layeredPane.add(Category);
		Category.setLayout(null);
		
		JButton cat5_7Button = new JButton("Change Category");
		cat5_7Button.setBounds(70, 297, 167, 58);
		Category.add(cat5_7Button);
		
		JButton cat7_9Button = new JButton("Change Category");
		cat7_9Button.setBounds(318, 295, 167, 63);
		Category.add(cat7_9Button);
		
		JButton cat9_12Button = new JButton("Change Category");
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
		lblNewLabel_2.setBounds(40, 79, 197, 177);
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
		shopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Shop);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
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
		//CategoryButton.setEnabled(false);
		CategoryButton.setBounds(583, 244, 140, 54);
		AuthorizedMenu.add(CategoryButton);
		
		JButton ProgressButton = new JButton("View Progress");
		ProgressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Progress);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		ProgressButton.setBounds(325, 244, 140, 54);
		AuthorizedMenu.add(ProgressButton);
		
		JLabel lblNewLabel_1 = new JLabel("List of Bound Accounts");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(50, 29, 155, 163);
		AuthorizedMenu.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(326, 41, 397, 54);
		AuthorizedMenu.add(textField_2);
		textField_2.setColumns(10);
		
		JButton autretButton = new JButton("Log out");
		autretButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		autretButton.setBounds(325, 392, 140, 54);
		AuthorizedMenu.add(autretButton);
		
		JButton BindButton = new JButton("Bind Account");
		BindButton.setBounds(321, 411, 178, 68);
		BindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(UnauthorizedMenu);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		BindAccount.setLayout(null);
		BindButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		BindAccount.add(BindButton);
		
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
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		Login.add(ReturnButton);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(BindAccount);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		LoginButton.setBounds(323, 358, 154, 46);
		Login.add(LoginButton);
		
		JButton RegisterButton = new JButton("Register");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
