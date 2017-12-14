package user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchServlet() {
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
		System.out.println(userName);
		response.getWriter().println(getJSON(userName).toJSONString());
		System.out.println(getJSON(userName).toJSONString());
		//System.out.println(getJSON(userName));
	}

	public JSONArray getJSON(String userName) {
		UserDAO dao = new UserDAO();
		ArrayList<User> list = dao.search(userName);
		
		JSONArray jsonArray = new JSONArray();
		for(int i=0; i<list.size(); i++) {
			JSONObject obj = new JSONObject();
			User user = (User)list.get(i);
			obj.put("userName", user.getUserName());
			obj.put("userAge", user.getUserAge());
			obj.put("userCountry", user.getUserCountry());
			obj.put("userPosition", user.getUserPosition());
			jsonArray.add(obj);
		}
		return jsonArray;
	}
}
