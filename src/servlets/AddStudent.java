package servlets;

import java.io.IOException;
import java.io.PrintWriter;


import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;
import util.ShowException;
public class AddStudent extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if(session == null || session.getAttribute("username") == null){
			out.println("<html>Session Expired");
			out.println("<br/><a href='pages/AdminLogin.jsp'>Login</a></html>");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
			String name2=request.getParameter("name");
			String username2=request.getParameter("username");
			String password2=request.getParameter("password");
			boolean duplicate = false;
			DBConnection con=new DBConnection();
			try {
				ResultSet rs=con.executeQuery("select username from student");
				while(rs.next()){
					if(rs.getString("username").equals(username2)){
						duplicate = true;
						System.out.println("Username Already exixts:");
						request.setAttribute("message", "Username Already exixts");
					}
				}
				if(!duplicate){
					//System.out.println("insert into student(name, username, password) values('"+name2+"', '"+username2+"', '"+password2+"')");
					con.executeUpdate("insert into student(name, username, password) values('"+name2+"', '"+username2+"', '"+password2+"')");
					request.setAttribute("message", "Student Added");
				}

			}catch (SQLException e) {
				new ShowException("Could not read/write record set: "+e.getMessage());
			}  catch (NullPointerException e){
				new ShowException("Record Set Uninitialised");
			}			
			request.getRequestDispatcher("pages/AdminWelcome.jsp").forward(request, response);
		}		
	}


	//	@Override
	//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	//			throws ServletException, IOException {
	//		response.setContentType("text/html;charset=UTF-8");
	//		List dataList=new ArrayList();
	//		DBConnection con=new DBConnection();
	//		try {
	//			ResultSet rs;
	//			rs = con.executeQuery("select sid,name,username,password from student");
	//			while(rs.next()){
	//				dataList.add(rs.getInt("sid"));
	//				dataList.add(rs.getString("name"));
	//				dataList.add(rs.getString("username"));
	//				dataList.add(rs.getInt("password"));
	//			}rs.close();
	//
	//		}
	//		catch (SQLException e) {
	//			new ShowException("Could not read recode set: "+e.getMessage());
	//		} catch (NullPointerException e){
	//			new ShowException("Record Set Uninitialised");
	//		}request.setAttribute("data",dataList);
	//		request.getRequestDispatcher("../stdDetails.jsp").forward(request, response);
	//
	//	}
}

