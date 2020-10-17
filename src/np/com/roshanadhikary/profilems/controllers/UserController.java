package np.com.roshanadhikary.profilems.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import np.com.roshanadhikary.profilems.dto.User;
import np.com.roshanadhikary.profilems.services.UserService;
import np.com.roshanadhikary.profilems.services.UserServiceImpl;

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
        this.setUserService(new UserServiceImpl());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String	subPath = request.getPathInfo() != null
				? request.getPathInfo().substring(1)
				: "";
		switch (subPath) {
			case "list":
				response.getWriter().append(new UserServiceImpl().listUsers().toString());
				break;
			default:
				response.getWriter().append("Served at: /" + subPath);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
