package servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;
		if(username.equals("admin") && password.equals("adminpassword") ){
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			rd = request.getRequestDispatcher("pages/AdminWelcome.jsp");
		}
		else
			rd = request.getRequestDispatcher("pages/Error.jsp");
		rd.forward(request, response);
	}

}
