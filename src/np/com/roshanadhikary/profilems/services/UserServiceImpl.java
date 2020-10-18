package np.com.roshanadhikary.profilems.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import np.com.roshanadhikary.profilems.dao.UserDaoImpl;
import np.com.roshanadhikary.profilems.dto.User;

public class UserServiceImpl implements UserService {
	Map<String, User> users;
	
	public UserServiceImpl() {
		loadUsers();
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return users.get(username);
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return new UserDaoImpl().getUsers();
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadUsers() {
		users = new HashMap<String, User>();
		
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("user1");
		user1.setPassword("asdf");
		user1.setName("User 1");
		user1.setEmail("one@demo.com");
		user1.setAddress("Arizona");
		users.put(user1.getUsername(), user1);
		
		User user2 = new User();
		user2.setId(2);
		user2.setUsername("user2");
		user2.setPassword("asdf");
		user2.setName("User 2");
		user2.setEmail("two@demo.com");
		user2.setAddress("Texas");
		users.put(user2.getUsername(), user2);
		
		User user3 = new User();
		user3.setId(3);
		user3.setUsername("user3");
		user3.setPassword("asdf");
		user3.setName("User 3");
		user3.setEmail("three@demo.com");
		user3.setAddress("Chicago");
		users.put(user3.getUsername(), user3);
	}

}
