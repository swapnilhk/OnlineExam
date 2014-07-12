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

public class AddQuestion extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null){
			out.println("<html>Session Expired");
			out.println("<br/><a href='pages/AdminLogin.jsp'>Login</a></html>");
		}
		else{
			response.setContentType("text/html;charset=UTF-8");

			String question=request.getParameter("question");
			//question = "Who invented JAVA?";
			String option1=request.getParameter("option1");		
			String option2=request.getParameter("option2");
			String option3=request.getParameter("option3");
			String option4=request.getParameter("option4");		
			String correctAnswer=request.getParameter("correctanswer");
			String marks=request.getParameter("marks");

			System.out.println("question : "+question);
			System.out.println("option1 : "+option1);
			System.out.println("option2 : "+option2);
			System.out.println("option3 : "+option3);
			System.out.println("option4 : "+option4);
			System.out.println("correctanswer : "+correctAnswer);
			System.out.println("marks : "+marks);

			DBConnection con=new DBConnection();		
			ResultSet rs2;
			try {			
				con.executeUpdate("insert into questions(question) values('"+question+"')");
				System.out.println("insert into questions(question) values('"+question+"')");

				rs2=con.executeQuery("select qid from questions where question='"+question+"'");
				System.out.println("select qid from questions where question='"+question+"'");

				rs2.next();			
				int qid=rs2.getInt("qid");


				con.executeUpdate("insert into options(qid,opt) values('"+qid+"','"+option1+"')");
				con.executeUpdate("insert into options(qid,opt) value('"+qid+"','"+option2+"')");
				con.executeUpdate("insert into options(qid,opt) value('"+qid+"','"+option3+"')");
				con.executeUpdate("insert into options(qid,opt) value('"+qid+"','"+option4+"')");			

				System.out.println("insert into options(qid,opt) values('"+qid+"','"+option1+"')");

				ResultSet rs3 = null;			 
				if(correctAnswer.equalsIgnoreCase("a"))
					rs3 = con.executeQuery("select oid from options where qid = "+qid+" and opt = '"+option1+"'");
				if(correctAnswer.equalsIgnoreCase("b")){
					rs3 = con.executeQuery("select oid from options where qid = "+qid+" and opt = '"+option2+"'");
					System.out.println("select oid from options where qid = "+qid+" and opt = '"+option1+"'");
				}
				if(correctAnswer.equalsIgnoreCase("c"))
					rs3 = con.executeQuery("select oid from options where qid = "+qid+" and opt = '"+option3+"'");
				if(correctAnswer.equalsIgnoreCase("d"))
					rs3 = con.executeQuery("select oid from options where qid = "+qid+" and opt = '"+option4+"'");
				rs3.next();			

				con.executeUpdate("insert into answers(qid,oid,marks) value('"+qid+"','"+rs3.getInt("oid")+"','"+marks+"')");
			}
			catch (SQLException e) {
				new ShowException("Could not read record set: "+e.getMessage());
			}  catch (NullPointerException e){
				new ShowException("Record Set Uninitialised");
			}
			request.setAttribute("message", "Question Added");
			request.getRequestDispatcher("pages/AdminWelcome.jsp").forward(request, response);
		}
	}
}

