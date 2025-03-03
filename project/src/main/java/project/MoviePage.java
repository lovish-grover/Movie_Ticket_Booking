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
import project.model.MoviePageCls;

/**
 * Servlet implementation class MoviePage
 */
@WebServlet("/MoviePage")
public class MoviePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			out.println("<html><head><title>Category page</title></head><body>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>MovieId</th>");
			out.println("<th>Name</th>");
			out.println("<th>Description</th>");
			out.println("<th>Language Id</th>");
			out.println("<th>language</th>");
			out.println("<th>release date</th>");
			out.println("<th>End date</th>");
			out.println("<th>goldSeatPrice</th>");
			out.println("<th>silverSeatPrice</th>");
			out.println("<th>Book A Ticket</th>");
			out.println("</tr>");
			String movieid=request.getParameter("movieid");
			int intMovieid=Integer.parseInt(movieid);
			String cusid=request.getParameter("cusId");
			int intcusid=Integer.parseInt(cusid);
			System.out.println(intcusid);
			DBHandler objDH=new DBHandler();
		
			
			List<MoviePageCls > lst=objDH.getMovieByMovieId(intMovieid);
			for (MoviePageCls obj : lst) 
			{
				out.println("<tr>");
				out.println("<th>"+movieid+"</th>");
				out.println("<th>"+obj.getMovieName()+"</th>");
				out.println("<th>"+obj.getMovieDesc()+"</th>");
				out.println("<th>"+obj.getLangId()+"</th>");
				out.println("<th>"+obj.getLanguage()+"</th>");
				out.println("<th>"+obj.getReleaseDate()+"</th>");
				out.println("<th>"+obj.getEndDate()+"</th>");
				out.println("<th>"+obj.getGoldSeatPrice()+"</th>");
				out.println("<th>"+obj.getsilverSeatprice()+"</th>");
				out.println("<th><a href='DateSlot?mid="+movieid+"&cusId="+intcusid+"'>Book A Ticket</a></th>");
				out.println("</tr>");	
			}
			
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
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
