package np.com.roshanadhikary.profilems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import np.com.roshanadhikary.profilems.dto.User;
import np.com.roshanadhikary.profilems.util.DbUtil;

public class UserDaoImpl implements UserDao {
	
	@Override
	public User addUser(User user) throws MySQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		try {
			Connection con = DbUtil.getConnection();
			String query = "INSERT INTO users(name, address, email, username, password) VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getAddress());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getUsername());
			ps.setBytes(5, user.getPassword());
			
			int rowsAffected = ps.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
			
			return user;
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		try {
			Connection con = DbUtil.getConnection();
			String query = "SELECT username, name, email, address FROM users";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				users.add(user);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(String username) {
		// TODO Auto-generated method stub
		
	}
	

}
