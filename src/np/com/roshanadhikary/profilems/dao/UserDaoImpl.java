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
		try {
			Connection con = DbUtil.getConnection();
			String query = "INSERT INTO users(name, address, email, username, password) VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getAddress());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getUsername());
			ps.setString(5, user.getPassword());
			
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
	public User updateUser(User user) throws MySQLIntegrityConstraintViolationException {
		User updatedProfile = user;
		try {
			Connection con = DbUtil.getConnection();
			// check what fields have been updated
			String query = "SELECT username, name, email, address, password FROM users WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			
			// original field values
			String orgName = null,
				orgUsername = null,
				orgEmail = null,
				orgAddress = null,
				orgPassword = null;
			
			while(rs.next()) {
				orgName = rs.getString("name");
				orgUsername = rs.getString("username");
				orgEmail = rs.getString("email");
				orgAddress = rs.getString("address");
				orgPassword = rs.getString("password");
			}
			
			boolean isNewName = false,
					isNewUsername = false,
					isNewEmail = false,
					isNewAddress = false,
					isNewPassword = false;
			
			if (!orgUsername.equals(user.getUsername()) || !orgEmail.equals(user.getEmail())) {
				System.out.println("Update the entire record, except ID");
				// check for username and email fields because they may cause errors ...
				// ... in case of two users changing their usernames at the same time ...
				// ... to the same username, or changing their emails
				
				// update the whole record (except for id)
				query = "UPDATE users SET name = ?, address = ?, email = ?, username = ?, password = ? WHERE id = ?";
				ps = con.prepareStatement(query);
				
				ps.setString(1, user.getName());
				ps.setString(2, user.getAddress());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getUsername());
				ps.setString(5, user.getPassword());
				ps.setInt(6, user.getId());
				
				int rowsAffected = ps.executeUpdate();
				System.out.println("Rows affected: " + rowsAffected);
			} else {
				System.out.println("Update the entire record, except username, email, and ID");
				// do not update the username and email fields; update others ...
				// ... even if they are the same
				query = "UPDATE users SET name = ?, address = ?, password = ? WHERE id = ?";
				ps = con.prepareStatement(query);
				
				ps.setString(1, user.getName());
				ps.setString(2, user.getAddress());
				ps.setString(3, user.getPassword());
				ps.setInt(4, user.getId());
				
				int rowsAffected = ps.executeUpdate();
				System.out.println("Rows affected: " + rowsAffected);
			}
			return updatedProfile;
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
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
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String username) {
		User user = null;
		try {
			Connection con = DbUtil.getConnection();
			String query = "SELECT id, username, name, email, address, password FROM users WHERE username = ?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setAddress(rs.getString("address"));
				user.setPassword(rs.getString("password"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void removeUser(String username) throws Exception {
		try {
			if (getUser(username) == null) {
				throw new Exception("unknown-username");
			}
			
			Connection con = DbUtil.getConnection();
			String query = "DELETE FROM users WHERE username = ?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, username);
			int rowsAffected = ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public boolean isRegistered(String username, String password) {
		boolean isRegistered = false;
		try {
			Connection con = DbUtil.getConnection();
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			isRegistered = rs.next();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return isRegistered;
	}
	

}
