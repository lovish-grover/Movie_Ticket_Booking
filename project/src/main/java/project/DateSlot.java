package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;
import project.model.MoviePageCls;
import project.model.SeatsBookedcls;

/**
 * Servlet implementation class DateSlot
 */
@WebServlet("/DateSlot")
public class DateSlot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
    DBHandler objDH = new DBHandler();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			try {
				out.println("<html>\r\n"
						+ "<head>\r\n"
						+ "<meta charset=\"ISO-8859-1\">\r\n"
						+ "<title>Insert title here</title>\r\n"
						+ "");
				String movieid=request.getParameter("mid");
				int intMovieid=Integer.parseInt(movieid);
				String cusid=request.getParameter("cusId");
				int intcusid=Integer.parseInt(cusid);
						DBHandler objDH=new DBHandler();
						String slot9t12 = "",slot12t15 = "",slot15t18 = "",slot18t21 = "";
						List<MoviePageCls > lst=objDH.getMovieByMovieId(intMovieid);
						for (MoviePageCls obj : lst) 
						{
							slot9t12 = obj.getSlot9t12();
							slot12t15 = obj.getSlot12t15();
							slot15t18 = obj.getSlot15t18();
							slot18t21 = obj.getSlot18t21();
						}
						out.println("<form action = 'Seats' method = 'get'>");
						out.println("Select Date of Show : <input type='Date' name='DOS'/><br>");
						if(slot9t12.equals("true")) {
							out.println(" <input type='checkbox' name = 'slot' value = '9to12'/>Slot_9to12");
						}else if(slot9t12.equals("false")) {
							out.println(" <input type='checkbox' disabled/>Slot_9to12");
						}if(slot12t15.equals("true")) {
							out.println(" <input type='checkbox' name = 'slot' value = '12to15'/>Slot_12to15");
						}else if(slot12t15.equals("false")){
							out.println(" <input type='checkbox' disabled/>Slot_12to15");
						}if(slot15t18.equals("true")) {
							out.println(" <input type='checkbox' name = 'slot' value = '15TO18'/>Slot_15to18");
						}else if(slot15t18.equals("false")) {
							out.println(" <input type='checkbox' disabled/>Slot_15to18");
						}if(slot18t21.equals("true")) {
							out.println(" <input type='checkbox' name = 'slot' value = '18TO21'/>Slot_18to21");
						}else if(slot18t21.equals("false")) {
							out.println(" <input type='checkbox' disabled/>Slot_18to21<br>");
						}
							out.println("<input type='submit' value = 'Book'/>");
						ServletContext sc = getServletContext();
						sc.setAttribute("movId", intMovieid);
						sc.setAttribute("cusId", intcusid);
			} catch (Exception e) {
				// TODO: handle exception
			 } 
		}

	/**
	 * @see  HttpServlet#doPost(HttpServletRequest request, ttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
	}

}
