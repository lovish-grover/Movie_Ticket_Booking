package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;
import project.model.DateSlotCls;
import project.model.MoviePageCls;

/**
 * Servlet implementation class Ticket
 */
@WebServlet("/Ticket")
public class Ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    PreparedStatement stmt = null,stmt1 = null,stmt2 = null,stmt3 = null,stmt4 =null,stm =null,s1=null,s2=null,s3=null;
    Connection con = null;
    ResultSet rset = null ,rset1 =null,rs = null;
    
    
    public Connection getDBCon() {
    	Connection con = null ; 
    	try {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con= ods.getConnection("Lovish", "password");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return con;
    }
    
    public void init() {
    	con = getDBCon();
    	try {
			stmt = con.prepareStatement("insert into tickect values(?,?,?,?,?,?,?,?)");
			stmt1 = con.prepareStatement("select max(ticket_id) from tickect");
			stm = con.prepareStatement("select gold_seats_boked , silver_seats_booked from seatsBoked where date_of_show=? and slot_of_show = ?" );
			stmt2 = con.prepareStatement("update seatsboked set gold_seats_boked = ?  where date_of_show=? and slot_of_show=?");
			stmt3 = con.prepareStatement("update seatsboked set silver_seats_booked = ?  where date_of_show=? and slot_of_show=?");
			s1 = con.prepareStatement("select gold_seats_remain,silver_seats_remain from seatsBoked where date_of_show=? and slot_of_show=?");
			s2 = con.prepareStatement("update seatsboked set gold_seats_remain=? where date_of_show=? and slot_of_show=?");
			s3 = con.prepareStatement("update seatsboked set silver_seats_remain=? where date_of_show=? and slot_of_show=?");
			stmt4 = con.prepareStatement("select * from tickect where customer_id = ?");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//  ticket_id number(2) primary key,
//  movie_id number(5),
//  customer_id number(5),
//  gold_seats_booked varchar(500),
//  silver_seats_booked varchar(500),
//  date_of_show date,
//  slot varchar(55),
//  total_price number(5),
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			DBHandler objDH = new DBHandler();
			try {
				int tickect = 0;
				ServletContext sc = getServletContext();
				int movId = (int) sc.getAttribute("movId");
				int cusId = (int)sc.getAttribute("cusId");
				String slot = (String) sc.getAttribute("slot");
				Date DOS = (Date) sc.getAttribute("DOS");
				String goldSeats[] = {};
				goldSeats = request.getParameterValues("seat");
				sc.setAttribute("seat", goldSeats);
				String sliverSeats[] = {};
				sliverSeats = request.getParameterValues("seatS");
				int ttl = 0 ;
				
				String st = "";
				if(goldSeats==null) {
					st = null;
				}else {
					for(int i=0;i<goldSeats.length;i++) {
						st =st+goldSeats[i];
					}
				}
				
				String sts = "";
				if (sliverSeats == null) {
					sts = null;
				}else {
					for(int i=0;i<sliverSeats.length;i++) {
						sts =sts+sliverSeats[i];
					}
				}
				
				List<MoviePageCls > lst=objDH.getMovieByMovieId(movId);
				for (MoviePageCls obj : lst) {
					if(goldSeats == null && sts.contains("S")) {
						ttl = (int) obj.getsilverSeatprice()*sliverSeats.length;
					}else if(st.contains("G") && sliverSeats == null) {
						ttl = (int) obj.getGoldSeatPrice()*goldSeats.length;
					}else {
						ttl = ((int) obj.getGoldSeatPrice()*goldSeats.length)+((int)obj.getsilverSeatprice()*sliverSeats.length);
					}
					}
				rset = stmt1.executeQuery();
				while(rset.next()) {
					int tid = rset.getInt("max(ticket_id)");
					tickect = tid + 1;
				}
			
				
				stmt.setInt(1, tickect);
				stmt.setInt(2, movId);
				stmt.setInt(3, cusId);
				stmt.setString(4, st);
				stmt.setString(5, sts);
				stmt.setDate(6, DOS);
				stmt.setString(7, slot);
				stmt.setInt(8, ttl);
				stmt.executeUpdate();
				
				
				s1.setDate(1, DOS);
				s1.setString(2, slot);
				
				rs = s1.executeQuery();
				int gsr =50,ssr=50;
				while(rs.next()) {
					gsr = rs.getInt("gold_seats_remain");
					ssr = rs.getInt("silver_seats_remain");
				}
				 
				int SSR = 0,GSR = 0;
				
				if(sliverSeats != null) {
					SSR = ssr-sliverSeats.length;
				}if(sliverSeats == null) {
					SSR = ssr;
				}if (goldSeats != null) {
				 GSR = gsr-goldSeats.length;
				}if (goldSeats == null) {
					GSR = gsr;
				}
				
				
				s2.setInt(1, GSR);
				s2.setDate(2, DOS);
				s2.setString(3, slot);
				
				s2.executeUpdate();
				
				
				
				s3.setInt(1, SSR);
				s3.setDate(2, DOS);
				s3.setString(3, slot);
				
				s3.executeUpdate();
				
				
				
				
				stm.setDate(1, DOS);
				stm.setString(2, slot);
				
				rset1 = stm.executeQuery();
				String gs = "",ss = "";
				while(rset1.next())
				{	String g = rset1.getString("gold_seats_boked");
					gs = gs + g;
					 ss = rset1.getString("silver_seats_booked");
					
				}
				 
				gs = gs + st;
				ss = ss + sts;
				
				
				
				
				stmt2.setString(1, gs);
				stmt2.setDate(2, DOS);
				stmt2.setString(3, slot);
				
				stmt2.executeUpdate();
				
				stmt3.setString(1, ss);
				stmt3.setDate(2, DOS);
				stmt3.setString(3, slot);
				
				stmt3.executeUpdate();
				
				stmt4.setInt(1,cusId);
				rset=stmt4.executeQuery();
				
//				
//				ticket_id number(2) primary key,
//				movie_id number(5),
//				customer_id number(5),
//				gold_seats_booked varchar(500),
//				silver_seats_booked varchar(500),
//				date_of_show date,
//				slot varchar(55),
//				total_price number(5),
				
				
				int ticketId = 0;
				
				while(rset.next()) {
					ticketId = rset.getInt("ticket_id");
				}
				
				out.print("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <center>\r\n"
						+ "      ticket<br>\r\n"
						+ "      ticket Id :"+ticketId+"<br>\r\n"
						+ "      movie id :"+movId+"<br>\r\n"
						);
				for(MoviePageCls ob :lst) {
					out.print("movie name :"+ob.getMovieName()+"<br>\r\n");
				}
				
				out.print("      slot :"+slot+"<br>\r\n"
						+ "      customer id :"+cusId+"<br>\r\n"
						+ "      date :"+DOS+"<br>\r\n"
						+ "      Gold Seat :"+st+"<br>\r\n"
						+ "      silver Seat :"+sts+"<br>\r\n"
						+ "      total price :"+ttl+"<br>\r\n"
						+ "    </center>\r\n"
						+ "</body>\r\n"
						+ "</html>");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
