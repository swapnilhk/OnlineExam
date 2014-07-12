package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;
import util.ShowException;

public class StdDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null){
			out.println("<html>Session Expired");
			out.println("<br/><a href='pages/AdminLogin.jsp'>Login</a></html>");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			List dataList=new ArrayList();
			DBConnection con=new DBConnection();
			try {
				ResultSet rs;
				rs = con.executeQuery("select sid,name,username,password from student");
				while(rs.next()){
					dataList.add(rs.getInt("sid"));
					dataList.add(rs.getString("name"));
					dataList.add(rs.getString("username"));
					dataList.add(rs.getString("password"));
				}rs.close();

			}
			catch (SQLException e) {
				new ShowException("Could not read recode set: "+e.getMessage());
			} catch (NullPointerException e){
				new ShowException("Record Set Uninitialised");
			}
			request.setAttribute("data",dataList);
			request.getRequestDispatcher("pages/StdDetails.jsp").forward(request, response);

		}
	}
}
