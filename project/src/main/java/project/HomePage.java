package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.model.MovieCls;

/**
 * Servlet implementation class Ironman
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title>Insert title here</title>\r\n"
					+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"style1.css\">\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "<div class=\"header\">\r\n"
					+ "        <nav class=\"top-nav\">\r\n"
					+ "            <ul>\r\n"
					+ "            <li><a href=\"#\">HOME</a></li>\r\n"
					+ "                \r\n"
					+ "                \r\n"
					+ "\r\n"
					+ "            </ul>\r\n"
					+ "            <a href=\"AdminLog.html\">\r\n"
					+ "            <button>Insert Movie</button>\r\n"
					+ "            </a>\r\n"
					+ "        </nav>\r\n"
					+ "        <div class=\"side\">\r\n"
					+ "           <img class=\"logo\" src=\"New folder/logo.png\" width=\"110\" height=\"110\" class=\"logo\"  >\r\n"
					+ "            <h1>Book My Show</h1>\r\n"
					+ "        </div>\r\n"
					+ "        	<div class=\"container1\">\r\n"
					+ "        	<table border ='5' class=\"table\">\r\n"
					+ "        		<tr>\r\n"
					+ "        			<th>MOVIE ID</th>\r\n"
					+ "        			<th>NAME</th>\r\n"
					+ "        			<th>Image</th>\r\n"
					+ "       			</tr>");
			String cusid=request.getParameter("cusId");
			int intcusid=Integer.parseInt(cusid);
			System.out.println(intcusid);
			DBHandler objDH=new DBHandler();
			List<MovieCls> lst=objDH.getTblMovie();
			for (MovieCls obj : lst) {
			out.println("<tr>");
			out.println("<td><a href='MoviePage?movieid="+obj.getMovieId()+"&cusId="+intcusid+"'>"+obj.getMovieId()+"</a></td>");
			out.println("<td>"+obj.getMovieName()+"</td>");
			out.println("<td class='img'></td>");
			out.println("</tr>");
			
			}
			out.println("</table>\r\n"
					+ "        </div>\r\n"
					+ "        </div>\r\n"
					+ "   \r\n"
					+ "     \r\n"
					+ "        \r\n"
					+ "      \r\n"
					+ "</body>\r\n"
					+ "</html>");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
