package pack1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(140, 53, 76, 14);
		contentPane.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(220, 50, 86, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(220, 80, 86, 20);
		txtPassword.setColumns(10);
		contentPane.add(txtPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(140, 83, 76, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(175, 132, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				UserDB userdb = new UserDB();
				ArrayList<User> userList = userdb.getUsers();
			
					if( userList
							.stream()
							.filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
							.findFirst()
							.isPresent()) 
					{
						MainPage mp = new MainPage();
						mp.setVisible(true);
						mp.setUser(userList
						.stream()
						.filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
						.findFirst()
						.get());
						
						dispose();
								
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "User is not valid!");
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		contentPane.add(btnLogin);
	}

}
