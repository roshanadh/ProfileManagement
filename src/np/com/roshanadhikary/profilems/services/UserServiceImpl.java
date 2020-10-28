package np.com.roshanadhikary.profilems.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import np.com.roshanadhikary.profilems.dao.UserDao;
import np.com.roshanadhikary.profilems.dao.UserDaoImpl;
import np.com.roshanadhikary.profilems.dto.User;
import np.com.roshanadhikary.profilems.util.PasswordHash;

public class UserServiceImpl implements UserService {
	UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	@Override
	public User addUser(User user) throws MySQLIntegrityConstraintViolationException {
		User addedUser = null;
		try {
			addedUser = userDao.addUser(user);
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addedUser;
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
}
