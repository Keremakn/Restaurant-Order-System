package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDB {
	public Connection getConnected() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/restaurant","root","123qwe456");
	}
	
	public ArrayList<Order> getOrders() throws SQLException{
			
			Statement st = getConnected().createStatement();
			ResultSet rs = st.executeQuery("select * from orders");
			ArrayList<Order> orders = new ArrayList<Order>();
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setOrderName(rs.getString(2));
				order.setTableNum(rs.getString(3));
				order.setPaymentMethod(rs.getString(4));
				orders.add(order);
			}
			return orders;
	}
	
	public void saveOrder(Order o) throws SQLException {
			
			String query = "insert into orders values(?,?,?,?)";
			PreparedStatement ps = getConnected().prepareStatement(query);
			ps.setInt(1, o.getOrderId());
			ps.setString(2, o.getOrderName());
			ps.setString(3, o.getTableNum());
			ps.setString(4, o.getPaymentMethod());
			ps.executeUpdate();
	}
	
	public void deleteOrder(int orderId) throws SQLException {
		String query = "delete from orders where orderId=?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, orderId);
		ps.executeUpdate();
	}
	
}

