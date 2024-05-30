package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDB {
	public Connection getConnected() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/restaurant","root","123qwe456");
		
	}
	
public ArrayList<User> getUsers() throws SQLException{
		
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from users");
		ArrayList<User> users = new ArrayList<User>();
		while(rs.next()) {
			User user = new User();
			user.setUserId(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setName(rs.getString(4));
			user.setSurname(rs.getString(5));
			user.setUserRole(rs.getString(6));
			users.add(user);
		}
		return users;
	}
	
	public void saveUser(User u) throws SQLException {
		
		String query = "insert into users values(?,?,?,?,?,?)";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, u.getUserId());
		ps.setString(2, u.getUsername());
		ps.setString(3, u.getPassword());
		ps.setString(4, u.getName());
		ps.setString(5, u.getSurname());
		ps.setString(6, u.getUserRole());
		ps.executeUpdate();
	}
	
	public void deleteUser(int userId) throws SQLException {
		String query = "delete from users where userId=?";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, userId);
		ps.executeUpdate();
	}
	
	public void updateUser(int userId, String newRole) {
		try {
			
			String query = "update users set userRole=? where userId=?";
			PreparedStatement ps = getConnected().prepareStatement(query);
			ps.setString(1, newRole);
			ps.setInt(2, userId);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
