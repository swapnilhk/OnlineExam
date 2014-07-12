package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;
import util.ShowException;

public class Test extends HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;
	
	int totalQuestions;

	
	public void init(ServletConfig config) throws ServletException {
		/* Find the total no of questions and put the data into session object.
		 * Finding this data here because it is going to stay constant for all
		 * invocation of this Servlet. 
		 */
		DBConnection con = new DBConnection();	
		try{
			// Question
			ResultSet rs = con.executeQuery("SELECT COUNT(*) from questions");
			rs.next();
			totalQuestions = rs.getInt(1);
			System.out.println("TOTALQUESTIONS = "+totalQuestions);
		
		}catch (SQLException e) {
			new ShowException("Could not read recode set: "+e.getMessage());
		}  catch (NullPointerException e){
			new ShowException("Record Set Uninitialised");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("sid") == null){
			out.println("<html>Session Expired");
			out.println("<br/><a href='pages/LoginForm.jsp'>Login</a></html>");
		}			
		else{
			if(session.getAttribute("min") == null){
				session.setAttribute("min", "30");
				session.setAttribute("sec", "30");
			}
			// Get question according to the qno sent by the previous page			
			// qno are in sequential order whereas qid may not
			int qno = request.getParameter("qno") != null? Integer.parseInt(request.getParameter("qno")) : 1;
			System.out.println("qno: "+qno);
			// find qid from qno
			int qid = getQidFromQno(qno);
			System.out.println("qid: "+qid);
			DBConnection con = new DBConnection();	
			try{
				// Question
				ResultSet rs = con.executeQuery("SELECT question FROM questions WHERE qid = "+qid);
				rs.next();			
				String question =  rs.getString(1);

				//OptOptionsid-->option
				HashMap<String, String> options = new HashMap<String, String>();
				rs = con.executeQuery("SELECT oid, opt FROM options WHERE qid = "+qid);				
				while(rs.next()){
					options.put(rs.getString(1), rs.getString(2));
				}

				// Check if it is the first or last question

				boolean last = false, first = false;
				if(qno == 1)
					first = true;
				rs = con.executeQuery("SELECT COUNT(*) FROM questions");
				rs.next();
				if(rs.getInt(1) == qno)// implies last question
					last = true;
				System.out.println("first = "+first);
				System.out.println("last = "+last);
				// Adding attributes first, last, prevQno, nextQno for 'prev' and 'next' links on then page
				if(first)
					request.setAttribute("first","");// send attribute named 'first'
				else 
					request.setAttribute("prevQno", qno-1); // other send next qno
				if(last)
					request.setAttribute("last","");// send attribute named 'last'
				else 
					request.setAttribute("nextQno", qno+1); // other send next qno
				
				// Find if the question is attempted by the user. If yes, pass the answer given by the student
				int sid = (Integer)session.getAttribute("sid");
				String checkedOid = "";
				boolean attempted = false;
				rs = con.executeQuery(("SELECT oid FROM student_answers WHERE sid = "+sid+" AND qid = "+qid));				
				if(rs.next()){
					System.out.println("ATTEMPTED");
					attempted = true;
					checkedOid = rs.getString(1);
				}
				System.out.println("CHECKED: "+checkedOid);
				
				// Add qid, question and options to the request
				request.setAttribute("qno", qno);
				request.setAttribute("qid", qid);
				request.setAttribute("question", question);
				request.setAttribute("options", options);
				request.setAttribute("attempted", attempted);
				if(attempted){					
					request.setAttribute("checkedOid", checkedOid);
				}
				request.setAttribute("totalQuestions", totalQuestions);
				if(request.getRequestDispatcher("pages/Test.jsp") == null)
					System.out.println("----------------------NULL----------------------");
				request.getRequestDispatcher("pages/Test.jsp").forward(request, response);

			}catch (SQLException e) {
				new ShowException("Could not read record set: "+e.getMessage());
			}  catch (NullPointerException e){
				new ShowException("Record Set Uninitialised");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("sid") == null){
			out.println("<html>Session Expired");
			out.println("<br/><a href='pages/LoginForm.jsp'>Login</a></html>");
		}			
		else{			
			// Save answer given by the user
			// qno are in sequential order whereas qid may not
			session.setAttribute("min", request.getParameter("hiddenmin"));
			session.setAttribute("sec", request.getParameter("hiddensec"));
			int qno = request.getParameter("qno") != null? Integer.parseInt(request.getParameter("qno")) : 1;			
			System.out.println("qno: "+qno);
			int qid = getQidFromQno(qno);
			System.out.println("qid: "+qid);
			int sid = (Integer)session.getAttribute("sid");
			System.out.println("sid: "+sid);
			DBConnection con = new DBConnection();
			String oid = request.getParameter("option");
			System.out.println("oid: "+request.getParameter("option"));
			/* See if user has already answered this question.
			 * If answered, update the record, otherwise, insert new record.
			 */
			ResultSet rs = con.executeQuery(("SELECT  COUNT(*) FROM student_answers WHERE sid = "+sid+" AND qid = "+qid));	
			try {
				rs.next();
				if(rs.getInt(1) == 0){
					// Insert
					con.executeUpdate("INSERT INTO student_answers(sid, qid, oid) VALUES("+sid+","+qid+","+oid+")");
					System.out.println("INSERT INTO student_answers(sid, qid, oid) VALUES("+sid+","+qid+","+oid+")");
				}
				else{ 
					// Update
					con.executeUpdate("UPDATE student_answers SET oid = "+oid+" WHERE sid = "+sid+" and qid = "+qid);					
					System.out.println("UPDATE student_answers SET oid = "+oid+" WHERE sid = "+sid+" and qid = "+qid);
				}

			}catch (SQLException e) {
				new ShowException("Could not read record set: "+e.getMessage());
			}  catch (NullPointerException e){
				new ShowException("Record Set Uninitialised");
			}
			request.setAttribute("message", "Response Saved");
			doGet(request,response);
		}		
	}
	
	private int getQidFromQno(int qno){			
		int qid = 1;
		DBConnection con = new DBConnection();
		ResultSet rs = con.executeQuery("SELECT qid FROM questions");
		try{
			// find qid from qno
			for(int i = 1; i <= qno; i++)
				rs.next();
			qid = rs.getInt(1);
		}catch (SQLException e) {
			new ShowException("Could not read recode set: "+e.getMessage());
		}  catch (NullPointerException e){
			new ShowException("Record Set Uninitialised");
		}
		return qid;
	}

}
