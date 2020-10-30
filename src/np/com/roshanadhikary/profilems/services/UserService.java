package np.com.roshanadhikary.profilems.services;

import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import np.com.roshanadhikary.profilems.dto.User;

public interface UserService {
	
	public User getUserByUsername(String username);
	
	public boolean isRegistered(String username, String password);
	
	public List<User> listUsers();
	
	public User addUser(User user) throws MySQLIntegrityConstraintViolationException, Exception;
	
	public User updateUser(User user) throws MySQLIntegrityConstraintViolationException, Exception;
	
	public void removeUser(User user);
}
