package np.com.roshanadhikary.profilems.dao;

import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import np.com.roshanadhikary.profilems.dto.User;

public interface UserDao {
	
	public User addUser(User user) throws MySQLIntegrityConstraintViolationException;
	
	public User updateUser(User user);
	
	public List<User> getUsers();
	
	public User getUser(String username);
	
	public User getUser(int id);
	
	public void removeUser(String username);
	
}
