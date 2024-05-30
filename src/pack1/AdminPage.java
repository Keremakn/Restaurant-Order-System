package pack1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelAddUser;
	private JTextField txtNewUserId;
	private JTextField txtNewName;
	private JTextField txtNewSurname;
	private JTextField txtNewUsername;
	private JTextField txtNewPassword;
	
	OrderDB orderdb = new OrderDB();
	DefaultListModel<String> orderModel;
	
	public void fillTheList() throws SQLException {

		orderModel.removeAllElements();
		
		orderdb.getOrders().forEach(order -> {
			
			orderModel.addElement(order.getOrderId()+" "+
							 order.getOrderName()+" "+
							 order.getTableNum()+" "+
							 order.getPaymentMethod());
		});
	}
	
	UserDB userdb = new UserDB();
	DefaultListModel<String> userModel;
	
	public void fillTheList1() throws SQLException {

		userModel.removeAllElements();
		
		userdb.getUsers().forEach(user -> {
			
			userModel.addElement(user.getUserId()+" "+
							 user.getName()+" "+
							 user.getSurname()+" "+
							 user.getUserRole());	
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setTitle("Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelAddUser = new JPanel();
		panelAddUser.setBounds(6, 6, 215, 260);
		contentPane.add(panelAddUser);
		panelAddUser.setLayout(null);
		panelAddUser.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 24, 46, 14);
		panelAddUser.add(lblNewLabel_1);
		
		txtNewUserId = new JTextField();
		txtNewUserId.setText("");
		txtNewUserId.setBounds(115, 21, 36, 20);
		panelAddUser.add(txtNewUserId);
		txtNewUserId.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setBounds(10, 52, 46, 14);
		panelAddUser.add(lblNewLabel_1_1);
		
		txtNewName = new JTextField();
		txtNewName.setText("");
		txtNewName.setColumns(10);
		txtNewName.setBounds(115, 49, 86, 20);
		panelAddUser.add(txtNewName);
		
		JLabel lblNewLabel_1_2 = new JLabel("Surname");
		lblNewLabel_1_2.setBounds(10, 80, 70, 14);
		panelAddUser.add(lblNewLabel_1_2);
		
		txtNewSurname = new JTextField();
		txtNewSurname.setText("");
		txtNewSurname.setColumns(10);
		txtNewSurname.setBounds(115, 77, 86, 20);
		panelAddUser.add(txtNewSurname);
		
		JLabel lblNewLabel_1_3 = new JLabel("Username");
		lblNewLabel_1_3.setBounds(10, 108, 86, 14);
		panelAddUser.add(lblNewLabel_1_3);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setText("");
		txtNewUsername.setColumns(10);
		txtNewUsername.setBounds(115, 105, 86, 20);
		panelAddUser.add(txtNewUsername);
		
		JLabel lblNewLabel_1_4 = new JLabel("Password");
		lblNewLabel_1_4.setBounds(10, 135, 86, 14);
		panelAddUser.add(lblNewLabel_1_4);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setText("");
		txtNewPassword.setColumns(10);
		txtNewPassword.setBounds(115, 132, 86, 20);
		panelAddUser.add(txtNewPassword);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("User Role");
		lblNewLabel_1_4_1.setBounds(10, 160, 86, 14);
		panelAddUser.add(lblNewLabel_1_4_1);
		
		JComboBox cbNewUserRole = new JComboBox();
		cbNewUserRole.setModel(new DefaultComboBoxModel(new String[] {"admin", "normal"}));
		cbNewUserRole.setBounds(111, 157, 90, 22);
		panelAddUser.add(cbNewUserRole);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				User u = new User();
				u.setUserId( Integer.parseInt( txtNewUserId.getText() ) );
				u.setUsername(txtNewUsername.getText());
				u.setPassword(txtNewPassword.getText());
				u.setName(txtNewName.getText());
				u.setSurname(txtNewSurname.getText());
				u.setUserRole(cbNewUserRole.getSelectedItem().toString());

				userdb.saveUser(u);
				JOptionPane.showMessageDialog(contentPane, "User Saved!");
				txtNewUserId.setText("");
				txtNewName.setText("");
				txtNewSurname.setText("");
				txtNewUsername.setText("");
				txtNewPassword.setText("");
				cbNewUserRole.setSelectedIndex(0);
				fillTheList();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSave.setBounds(112, 218, 89, 23);
		panelAddUser.add(btnSave);
		
		JPanel panelUserL = new JPanel();
		panelUserL.setBounds(6, 6, 215, 260);
		contentPane.add(panelUserL);
		panelUserL.setLayout(null);
		panelUserL.setVisible(false);
		
				
				JLabel lblUserList = new JLabel("User List");
				lblUserList.setHorizontalAlignment(SwingConstants.CENTER);
				lblUserList.setBounds(6, 6, 203, 16);
				panelUserL.add(lblUserList);
				
				userModel = new DefaultListModel<String>();
				
				JList list = new JList(userModel);
				list.setBounds(6, 34, 203, 150);
				panelUserL.add(list); 
				
				JButton btnRefresh2 = new JButton("Refresh");
				btnRefresh2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							
							fillTheList1();
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				btnRefresh2.setBounds(6, 186, 97, 29);
				panelUserL.add(btnRefresh2);
				
				JButton btnDelete2 = new JButton("Delete");
				btnDelete2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							try {
						
						String[] selectedUser = list.getSelectedValue().toString().split(" ");
						
						userdb.deleteUser( Integer.parseInt( selectedUser[0] ) );
						
						fillTheList();
						
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				});
				btnDelete2.setBounds(6, 213, 97, 29);
				panelUserL.add(btnDelete2);
				
				JComboBox cbNewRole = new JComboBox();
				cbNewRole.setModel(new DefaultComboBoxModel(new String[] {"admin", "normal"}));
				cbNewRole.setBounds(115, 187, 94, 27);
				panelUserL.add(cbNewRole);
				
				JButton btnUpdate = new JButton("Update");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							
						String[] selectedUser = list.getSelectedValue().toString().split(" ");
						int selectedID = Integer.parseInt( selectedUser[0] );
						userdb.updateUser(selectedID, cbNewRole.getSelectedItem().toString());
						
						fillTheList();
						
						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnUpdate.setBounds(112, 213, 97, 29);
				panelUserL.add(btnUpdate);
				
						
		JPanel panelOrderList = new JPanel();
		panelOrderList.setBounds(6, 6, 215, 260);
		contentPane.add(panelOrderList);
		panelOrderList.setLayout(null);
		panelOrderList.setVisible(false);
		
				
				JLabel lblOrderList = new JLabel("Order List");
				lblOrderList.setHorizontalAlignment(SwingConstants.CENTER);
				lblOrderList.setBounds(6, 6, 195, 16);
				panelOrderList.add(lblOrderList);
				
				orderModel = new DefaultListModel<String>();
				
				JList listOrder = new JList(orderModel);
				listOrder.setBounds(6, 21, 203, 183);
				panelOrderList.add(listOrder);
				
				JButton btnRefresh = new JButton("Refresh");
				btnRefresh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							fillTheList();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnRefresh.setBounds(6, 203, 101, 29);
				panelOrderList.add(btnRefresh);
				
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							try {
						
						String[] selectedOrder = listOrder.getSelectedValue().toString().split(" ");
						
						orderdb.deleteOrder( Integer.parseInt( selectedOrder[0] ) );
						
						fillTheList();
						
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}
				});
				btnDelete.setBounds(100, 203, 101, 29);
				panelOrderList.add(btnDelete);
				
		JButton btnOrderL = new JButton("Order List");
		btnOrderL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelOrderList.setVisible(true);
			}
		});
		btnOrderL.setBounds(283, 17, 117, 29);
		contentPane.add(btnOrderL);
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddUser.setVisible(true);
			}
		});
		btnAddUser.setBounds(283, 58, 117, 29);
		contentPane.add(btnAddUser);
		
		JButton btnUserList = new JButton("User List");
		btnUserList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUserL.setVisible(true);
			}
		});
		btnUserList.setBounds(283, 99, 117, 29);
		contentPane.add(btnUserList);
		
		JButton btnBackUser = new JButton("Back");
		btnBackUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAddUser.setVisible(false);
				panelOrderList.setVisible(false);
				panelUserL.setVisible(false);


			}
		});
		btnBackUser.setBounds(293, 140, 89, 23);
		contentPane.add(btnBackUser);
		
		JButton btnBackToMain = new JButton("Back to Main Page");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBackToMain.setBounds(271, 222, 138, 29);
		contentPane.add(btnBackToMain);
		
		
			
		
	}

}
