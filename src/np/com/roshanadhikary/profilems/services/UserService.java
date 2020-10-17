package np.com.roshanadhikary.profilems.services;

import java.util.List;

import np.com.roshanadhikary.profilems.dto.User;

public interface UserService {
	
	public User getUserByUsername(String username);
	
	public List<User> listUsers();
	
	public User addUser(User user);
	
	public User updateUser(User user);
	
	public void removeUser(User user);
}