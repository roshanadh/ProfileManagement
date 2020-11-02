package np.com.roshanadhikary.profilems.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		HttpSession session = request.getSession(false);
		System.out.println("Session username: " + session.getAttribute("username"));
		
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// check for request method incoming from an <input> field with name "_method"
		
		// This is because HTML <form> doesn't support method="put", so we use ...
		// ... method = "post" even in cases of a request that needs to be PUT, but use ...
		// ... the hidden <input> field named "_method" to indicate that the request ...
		// ... is in fact to be assumed as a PUT request.
		String requestMethod = request.getParameter("_method");
		System.out.println("The requestMethod is: " + requestMethod);
		if (requestMethod.equals("post")) {
			System.out.println("inside post");
			String username = null;
			String name = null;
			String email = null;
			String address = null;
			String password = null;
			String confirmPassword = null;
			
			RequestDispatcher rd = null;
			try {
				name = request.getParameter("name");
				address = request.getParameter("address");
				email = request.getParameter("email");
				username = request.getParameter("username");
				password = request.getParameter("password");
				confirmPassword = request.getParameter("confirmPassword");
				
				if (!password.contentEquals(confirmPassword)) {
					rd = request.getRequestDispatcher("register.jsp?registerError=passwords-do-not-match");
					
					request.setAttribute("name", name);
					request.setAttribute("username", username);
					request.setAttribute("email", email);
					request.setAttribute("address", address);
					
					rd.forward(request, response);
					return;
				}
				
				User user = new User();
				user.setName(name);
				user.setEmail(email);
				user.setAddress(address);
				user.setUsername(username);
				user.setPassword(password);
				
				userService.addUser(user);
				
				doGet(request, response);
			} catch (MySQLIntegrityConstraintViolationException e) {
				String errorMessage = e.getMessage();
				request.setAttribute("name", name);
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				request.setAttribute("address", address);
				
				if (errorMessage.contains("Duplicate entry '" + username + "' for key 'username")) {
					rd = request.getRequestDispatcher("register.jsp?registerError=username-exists");
				} else if (errorMessage.contains("Duplicate entry '" + email + "' for key 'email")) {
					System.out.println("Email " + email + " already exists!");
					rd = request.getRequestDispatcher("register.jsp?registerError=email-exists");
				} else {
					e.printStackTrace();
					rd = request.getRequestDispatcher("register.jsp?registerError=constraint-violation");
				}
				rd.forward(request, response);
			} catch (Exception e) {
				request.setAttribute("name", name);
				request.setAttribute("username", username);
				request.setAttribute("email", email);
				request.setAttribute("address", address);
				
				String[] errorMessages = new String[] {
						"name-cannot-be-null",
						"username-cannot-be-null",
						"email-cannot-be-null",
						"address-cannot-be-null",
						"password-cannot-be-null",
						"password-too-short"
				};
				
				List<String> errorList = Arrays.asList(errorMessages);
				if (errorList.contains(e.getMessage())) {
					rd = request.getRequestDispatcher("register.jsp?registerError=" + e.getMessage());
				} else {
					e.printStackTrace();
					rd = request.getRequestDispatcher("register.jsp?registerError=server-error");
				}
				rd.forward(request, response);
			}
		} else if (requestMethod.equals("put")) {
			System.out.println("inside put");
			doPut(request, response);
		} else if (requestMethod.equals("delete")) {
			doDelete(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = -1;
		String username = null;
		String name = null;
		String email = null;
		String address = null;
		String password = null;
		
		RequestDispatcher rd = null;
		HttpSession session = null;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			name = request.getParameter("name");
			address = request.getParameter("address");
			email = request.getParameter("email");
			username = request.getParameter("username");
			password = request.getParameter("password");
			
			User updatedProfile = new User();
			// id remains unchanged
			updatedProfile.setId(id);
			updatedProfile.setName(name);
			updatedProfile.setEmail(email);
			updatedProfile.setAddress(address);
			updatedProfile.setUsername(username);
			updatedProfile.setPassword(password);
			
			userService.updateUser(updatedProfile);
			
			// update session
			session = request.getSession(false);
			if (session == null) {
				rd = request.getRequestDispatcher("index.jsp?sessionExpired");
				rd.forward(request, response);
				return;
			}
			
			session.removeAttribute("username");
			session.setAttribute("username", username);
			
			request.setAttribute("user", updatedProfile);
			
			rd = request.getRequestDispatcher("profile.jsp?updateStatus=true");
			rd.forward(request, response);
		} catch (MySQLIntegrityConstraintViolationException e) {
			String errorMessage = e.getMessage();
			
			session = request.getSession(false);
			if (session == null) {
				rd = request.getRequestDispatcher("index.jsp?sessionExpired");
				rd.forward(request, response);
				return;
			}
			
			User user = userService.getUserByUsername((String) session.getAttribute("username"));
			request.setAttribute("user", user);
			
			if (errorMessage.contains("Duplicate entry '" + username + "' for key 'username")) {
				System.out.println("Username " + username + " already exists!");
				rd = request.getRequestDispatcher("profile.jsp?updateError=username-exists");
			} else if (errorMessage.contains("Duplicate entry '" + email + "' for key 'email")) {
				System.out.println("Email " + email + " already exists!");
				rd = request.getRequestDispatcher("profile.jsp?updateError=email-exists");
			} else {
				e.printStackTrace();
				rd = request.getRequestDispatcher("profile.jsp?updateError=constraint-violation");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			session = request.getSession(false);
			if (session == null) {
				rd = request.getRequestDispatcher("index.jsp?sessionExpired");
				rd.forward(request, response);
				return;
			}
			
			User user = userService.getUserByUsername((String) session.getAttribute("username"));
			request.setAttribute("user", user);
			
			request.setAttribute("name", name);
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			request.setAttribute("address", address);
			
			String[] errorMessages = new String[] {
					"name-cannot-be-null",
					"username-cannot-be-null",
					"email-cannot-be-null",
					"address-cannot-be-null",
					"password-cannot-be-null",
					"password-too-short"
			};
			
			List<String> errorList = Arrays.asList(errorMessages);
			if (errorList.contains(e.getMessage())) {
				rd = request.getRequestDispatcher("profile.jsp?updateError=" + e.getMessage());
			} else {
				e.printStackTrace();
				rd = request.getRequestDispatcher("profile.jsp?updateError=server-error");
			}
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username;
		String password;
		
		RequestDispatcher rd;
		HttpSession session;
		try {
			username = request.getParameter("username");
			password = request.getParameter("password");
			userService.removeUser(username, password);
			
			// invalidate session
			session =request.getSession(false);
			session.invalidate();
			
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			String[] errorMessages = new String[] {
					"unknown-username",
					"bad-credentials"
			};
			
			List<String> errorList = Arrays.asList(errorMessages);
			if (errorList.contains(e.getMessage())) {
				rd = request.getRequestDispatcher("profile.jsp?deleteError=" + e.getMessage());
			} else {
				e.printStackTrace();
				rd = request.getRequestDispatcher("profile.jsp?deleteError=server-error");
			}
			rd.forward(request, response);
		}
	}

}
