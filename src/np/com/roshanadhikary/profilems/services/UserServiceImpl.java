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
		return userDao.getUser(username);
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	@Override
	public User addUser(User user) throws MySQLIntegrityConstraintViolationException, Exception {
		User addedUser = null;
		try {
			if (user.getName().trim().equals("")) {
				throw new Exception("name-cannot-be-null");
			} if (user.getUsername().equals("")) {
				throw new Exception("username-cannot-be-null");
			} if (user.getEmail().trim().equals("")) {
				throw new Exception("email-cannot-be-null");
			} if (user.getAddress().trim().equals("")) {
				throw new Exception("address-cannot-be-null");
			} if (user.getPassword().equals("")) {
				throw new Exception("password-cannot-be-null");
			} if (user.getPassword().length() < 8) {
				throw new Exception("password-too-short");
			}
			
			addedUser = userDao.addUser(user);
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return addedUser;
	}

	@Override
	public User updateUser(User user) throws MySQLIntegrityConstraintViolationException, Exception {
		User updatedProfile = null;
		try {
			if (user.getName().trim().equals("")) {
				throw new Exception("name-cannot-be-null");
			} if (user.getUsername().equals("")) {
				throw new Exception("username-cannot-be-null");
			} if (user.getEmail().trim().equals("")) {
				throw new Exception("email-cannot-be-null");
			} if (user.getAddress().trim().equals("")) {
				throw new Exception("address-cannot-be-null");
			} if (user.getPassword().equals("")) {
				throw new Exception("password-cannot-be-null");
			} if (user.getPassword().length() < 8) {
				throw new Exception("password-too-short");
			}
			
			updatedProfile = userDao.updateUser(user);
		} catch (MySQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return updatedProfile;
	}

	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRegistered(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.isRegistered(username, password);
	}
}
