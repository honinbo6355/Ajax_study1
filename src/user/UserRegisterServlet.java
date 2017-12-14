package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");
		String userCountry = request.getParameter("userCountry");
		String userPosition = request.getParameter("userPosition");
		response.getWriter().println(register(userName,userAge,userCountry,userPosition)+"");
	}
	
	public int register(String userName, String userAge, String userCountry, String userPosition) {
		User user = new User();
		UserDAO dao = new UserDAO();
		try {
			user.setUserName(userName);
			user.setUserAge(Integer.parseInt(userAge));
			user.setUserCountry(userCountry);
			user.setUserPosition(userPosition);
		} catch(Exception e) {
			return 0;
		}
		return dao.register(user); 
	}
}
