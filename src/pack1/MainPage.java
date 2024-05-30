package pack1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOrderId;
	User activeUser;
	
	public void setUser(User u) {
		activeUser = u;
	}
	
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setTitle("Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(220, 6, 224, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblOrderId = new JLabel("Order ID:");
		lblOrderId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrderId.setBounds(6, 11, 109, 16);
		panel.add(lblOrderId);
		
		txtOrderId = new JTextField();
		txtOrderId.setBounds(127, 6, 91, 26);
		panel.add(txtOrderId);
		txtOrderId.setColumns(10);
		
		JLabel lblOrderName = new JLabel("Order Name:");
		lblOrderName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrderName.setBounds(6, 35, 109, 16);
		panel.add(lblOrderName);
		
		JComboBox cbOrderName = new JComboBox();
		cbOrderName.setBounds(127, 31, 91, 27);
		panel.add(cbOrderName);
		cbOrderName.addItem("Grilled Chicken Breast");
		cbOrderName.addItem("Spaghetti Bolognese");
		cbOrderName.addItem("Vegetarian Pizza ");
		cbOrderName.addItem("Beef Steak");
		cbOrderName.addItem("Salmon Fillet");
		cbOrderName.addItem("Lamb Chops");
		cbOrderName.addItem("Chicken Alfredo");
		cbOrderName.addItem("Shrimp Scampi");
		cbOrderName.addItem("BBQ Ribs");
		cbOrderName.addItem("Veggie Stir-Fry");
		
		JLabel lblTableNum = new JLabel("Table Number:");
		lblTableNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTableNum.setBounds(6, 63, 109, 16);
		panel.add(lblTableNum);
		
		JComboBox cbTableNum = new JComboBox();
		for (int i = 1; i <= 20; i++) {
            cbTableNum.addItem(i);
        }
		cbTableNum.setBounds(127, 59, 91, 27);
		panel.add(cbTableNum);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method:");
		lblPaymentMethod.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaymentMethod.setBounds(6, 91, 109, 16);
		panel.add(lblPaymentMethod);
		
		JComboBox cbPayment = new JComboBox();
		cbPayment.setBounds(127, 87, 85, 23);
		panel.add(cbPayment);
		cbPayment.addItem("Cash");
		cbPayment.addItem("Card");
		
		JPanel panelOrderList = new JPanel();
		panelOrderList.setBounds(6, 6, 207, 260);
		contentPane.add(panelOrderList);
		panelOrderList.setLayout(null);
		
		JButton btnCreateOrder = new JButton("Create Order");
		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Order o = new Order();
					o.setOrderId( Integer.parseInt( txtOrderId.getText() ) );
					o.setOrderName(cbOrderName.getSelectedItem().toString());
					o.setTableNum(cbTableNum.getSelectedItem().toString());
					o.setPaymentMethod(cbPayment.getSelectedItem().toString());

					

					orderdb.saveOrder(o);
					JOptionPane.showMessageDialog(contentPane, "Order Saved!");
					txtOrderId.setText("");
					cbOrderName.setSelectedIndex(0);
					cbTableNum.setSelectedIndex(0);
					cbPayment.setSelectedIndex(0);
					fillTheList();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		btnCreateOrder.setBounds(52, 144, 117, 29);
		panel.add(btnCreateOrder);
		
		JLabel lblOrderList = new JLabel("Order List");
		lblOrderList.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderList.setBounds(6, 6, 195, 16);
		panelOrderList.add(lblOrderList);
		
		orderModel = new DefaultListModel<String>();
		
		JList listOrder = new JList(orderModel);
		listOrder.setBounds(6, 21, 195, 199);
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
		btnRefresh.setBounds(56, 225, 101, 29);
		panelOrderList.add(btnRefresh);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(327, 237, 117, 29);
		contentPane.add(btnExit);
		
		JButton btnAdminPage = new JButton("Admin Page");
		btnAdminPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(activeUser.getUserRole().equals("admin")) {
					AdminPage ap = new AdminPage();
					ap.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No admin rights!");
				}
				
				
			}
		});
		btnAdminPage.setBounds(327, 207, 117, 29);
		contentPane.add(btnAdminPage);
	}
}
