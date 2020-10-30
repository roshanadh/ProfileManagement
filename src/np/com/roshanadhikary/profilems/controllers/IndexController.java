package np.com.roshanadhikary.profilems.controllers;

import java.io.IOException;

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

/**
 * Servlet implementation class RootController
 */
@WebServlet("/RootController")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserService userService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
        this.userService = new UserServiceImpl(new UserDaoImpl());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (
				session != null &&
				session.getAttribute("loggedInStatus") != null &&
				session.getAttribute("username") != null
				) {
			System.out.println("loggedInStatus was set: " + (boolean) session.getAttribute("loggedInStatus"));
			System.out.println("username was set: " + session.getAttribute("username"));
			
			String username = (String) session.getAttribute("username");
			
			User user = userService.getUserByUsername(username);
			
			request.setAttribute("user", user);
			
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);
		} else {
			System.out.println("Session was not set");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
