package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;
import util.ShowException;

public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;
	
	private int sid;
	private String name;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		
		if(login(username, password)){
			// Begin session
			HttpSession session = request.getSession();
			session.setAttribute("sid", sid);
			session.setAttribute("name", name);
			//request.getRequestDispatcher("pages/Welcome.jsp").forward(request, response);
			
			// Check if student has already taken the test
			DBConnection con = new DBConnection();
			ResultSet rs = con.executeQuery("SELECT marks FROM student WHERE sid = "+sid);
			try {
				rs.next();				
				rs.getInt(1);
				System.out.println("ALREADY TAKEN TEST: "+rs.wasNull());
				// If the student has NOT already taken the test
				if(rs.wasNull()){
					request.getRequestDispatcher("pages/Welcome.jsp").forward(request, response);					
				}
				else{// If the student has already taken the test
					request.getRequestDispatcher("/ProcessResult").forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				new ShowException("Record Set Uninitialised");
			}
		}
		else request.getRequestDispatcher("pages/Error.jsp").forward(request, response);
	}

	public boolean login(String username, String password)
	{
		System.out.println("Validating "+username);
		DBConnection con = new DBConnection();
		ResultSet rs = con.executeQuery("SELECT sid, name, password FROM student WHERE username = '"+username+"'");
		try{
			if(rs.next()){
				if(rs.getString("password").equals(password)){
					sid = rs.getInt("sid");
					name = rs.getString("name");
					return true;
				}
				else return false;
			}
			else return false;						
		}catch (SQLException e) {
			new ShowException("Could not read recode set: "+e.getMessage());
		}  catch (NullPointerException e){
			new ShowException("Record Set Uninitialised");
		}		
		return false;
	}

}