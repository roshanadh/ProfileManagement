package np.com.roshanadhikary.profilems.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import np.com.roshanadhikary.profilems.dao.UserDaoImpl;
import np.com.roshanadhikary.profilems.dto.User;
import np.com.roshanadhikary.profilems.services.UserService;
import np.com.roshanadhikary.profilems.services.UserServiceImpl;
import np.com.roshanadhikary.profilems.util.PasswordHash;

/**
 * Servlet implementation class UserController
*/
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
       
    public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        this.setUserService(new UserServiceImpl(new UserDaoImpl()));
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("userList.jsp");
		request.setAttribute("users", userService.listUsers());
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			byte[] hashedPassword = PasswordHash.getHash(password);
			
			User user = new User();
			user.setName(name);
			user.setEmail(email);
			user.setAddress(address);
			user.setUsername(username);
			user.setPassword(hashedPassword);
			
			userService.addUser(user);
			
			doGet(request, response);
		} catch (MySQLIntegrityConstraintViolationException e) {
			String errorMessage = e.getMessage();
			
			String violatedField = null;
			if (errorMessage.contains("Duplicate entry '" + username + "' for key 'username"))
				violatedField = "username";
			if (errorMessage.contains("Duplicate entry '" + email + "' for key 'email"))
				violatedField = "email";
			
			switch (violatedField) {
				case "username":
					System.out.println("Username " + username + " already exists!");
					break;
				case "email":
					System.out.println("Email " + email + " already exists!");
					break;
				default: System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
