package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;
import util.ShowException;

public class ProcessResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("sid") == null){
			out.println("<html>Session Expired");
			out.println("<br/><a href='pages/LoginForm.jsp'>Login</a></html>");
		}			
		else{
			int marks = 0;
			int correct = 0;
			int incorrect = 0;
			DBConnection con = new DBConnection();
			Integer sid=(Integer)session.getAttribute("sid");
			ResultSet rs = con.executeQuery("SELECT qid, oid FROM student_answers WHERE sid="+sid);
			try{				
				while(rs.next()){
					int qid = rs.getInt(1);
					int oid = rs.getInt(2);
					ResultSet  rs2 = con.executeQuery("SELECT oid, marks FROM answers WHERE qid = "+qid);
					rs2.next();
					if(rs2.getInt("oid") == oid){
						marks += rs2.getInt("marks");
						correct++;						
					}
					else {
						incorrect++; 
					}
				}
			}catch (SQLException e) {
				new ShowException("Could not read record set: "+e.getMessage());
			}  catch (NullPointerException e){
				new ShowException("Record Set Uninitialised");
			}
			con.executeUpdate("UPDATE student SET marks = "+marks+" where sid = "+sid);
			request.setAttribute("correct", correct);
			request.setAttribute("incorrect", incorrect);
			request.setAttribute("marks", marks);
			request.setAttribute("name", session.getAttribute("name"));
			session.invalidate();
			request.getRequestDispatcher("pages/Report.jsp").forward(request, response);
		}
	}

}
