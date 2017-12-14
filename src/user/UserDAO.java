package user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {

	Connection conn = null;
	public UserDAO() {
		try{
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/mywdbpool"); //honinbo
			conn = dataSource.getConnection();
		}catch(NamingException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<User> search(String paramUserName) {
		ArrayList list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * FROM user WHERE userName like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, paramUserName + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {
				String userName = rs.getString("userName");
				int userAge = rs.getInt("userAge");
				String userCountry = rs.getString("userCountry");
				String userPosition = rs.getString("userPosition");
				User user = new User(userName,userAge,userCountry,userPosition);
				list.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				
			}
		}
		return list;
	}
	
	public int register(User user) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO user VALUES(?,?,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserName());
			pstmt.setInt(2, user.getUserAge());
			pstmt.setString(3, user.getUserCountry());
			pstmt.setString(4, user.getUserPosition());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
	
	
	
