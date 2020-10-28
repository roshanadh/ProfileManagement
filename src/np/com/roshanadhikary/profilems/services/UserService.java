package np.com.roshanadhikary.profilems.services;

import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import np.com.roshanadhikary.profilems.dto.User;

public interface UserService {
	
	public User getUserByUsername(String username);
	
	public List<User> listUsers();
	
	public User addUser(User user) throws MySQLIntegrityConstraintViolationException;
	
	public User updateUser(User user);
	
	public void removeUser(User user);
}
