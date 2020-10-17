package np.com.roshanadhikary.profilems.dao;

import java.util.List;

import np.com.roshanadhikary.profilems.dto.User;

public interface UserDao {
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public List<User> getUsers();
	
	public User getUser(String username);
	
	public User getUser(int id);
	
	public void removeUser(String username);
	
}
