package np.com.roshanadhikary.profilems.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import np.com.roshanadhikary.profilems.dao.UserDaoImpl;
import np.com.roshanadhikary.profilems.services.UserService;
import np.com.roshanadhikary.profilems.services.UserServiceImpl;
import np.com.roshanadhikary.profilems.util.PasswordHash;

/**
 * Servlet implementation class SignInController
 */
@WebServlet("/SignInController")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInController() {
        super();
        userService = new UserServiceImpl(new UserDaoImpl());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// use for signing out a user
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("ok iam here");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
//			byte[] hashedBytes = PasswordHash.getHash(password);
//			String hashedPassword = new String(hashedBytes, StandardCharsets.UTF_8);
			
//			System.out.println("Hashed Password: " + hashedPassword);
			if (userService.getUserByUsername(username) == null) {
				System.out.println("User by the username '" + username + "' does not exist!");
				return;
			}
			
			if (!userService.isRegistered(username, password)) {
				System.out.println("The credentials do not match");
				return;
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loggedInStatus", true);
			session.setAttribute("username", username);
			System.out.println("session[username] set:  " + session.getAttribute("username"));
			
			System.out.println("Welcome, " + username + "!");
			
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			request.setAttribute("user", userService.getUserByUsername(username));
			rd.include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
