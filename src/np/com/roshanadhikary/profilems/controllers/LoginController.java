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
import np.com.roshanadhikary.profilems.dto.User;
import np.com.roshanadhikary.profilems.services.UserService;
import np.com.roshanadhikary.profilems.services.UserServiceImpl;
import np.com.roshanadhikary.profilems.util.PasswordHash;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        userService = new UserServiceImpl(new UserDaoImpl());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		HttpSession session = null;
		RequestDispatcher rd;
		
		if (servletPath.equals("/logout")) {
			// use for signing out a user
			session = request.getSession(false);
			if (session != null) session.invalidate();
			
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else if (servletPath.equals("/login")) {
			// check if the user is already logged in, ...
			// ... if they're: redirect to their profile page ...
			// ... if they're not: redirect to the login page
			session = request.getSession(false);
			
			if (
				session != null &&
				session.getAttribute("loggedInStatus") != null &&
				session.getAttribute("username") != null &&
				(boolean) session.getAttribute("loggedInStatus")
				)
			{
				// redirect to the profile page
				rd = request.getRequestDispatcher("profile.jsp");
				
				User user = userService.getUserByUsername((String) session.getAttribute("username"));
				
				request.setAttribute("user", user);
				rd.forward(request, response);
			} else {
				// redirect to the login page
				rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// use for signing in a user
		RequestDispatcher rd = null;
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			if (userService.getUserByUsername(username) == null) {
				System.out.println("Username doesn't exist!");
				rd = request.getRequestDispatcher("?loginError=unknown-username");
				rd.forward(request, response);
				return;
			}
			
			if (!userService.isRegistered(username, password)) {
				System.out.println("Bad credentials!");
				rd = request.getRequestDispatcher("?loginError=bad-credentials");
				rd.forward(request, response);
				return;
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loggedInStatus", true);
			session.setAttribute("username", username);
			
			System.out.println("session[username] set:  " + session.getAttribute("username"));
			System.out.println("Welcome, " + username + "!");
			
			rd = request.getRequestDispatcher("profile.jsp");
			request.setAttribute("user", userService.getUserByUsername(username));
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
