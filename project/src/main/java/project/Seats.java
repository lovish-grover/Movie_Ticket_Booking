package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;
import project.model.CustomerInfoCls;
import project.model.DateSlotCls;
import project.model.MovieCls;

/**
 * Servlet implementation class Seats
 */
@WebServlet("/Seats")
public class Seats extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Seats() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    PreparedStatement stmt = null;
    Connection con = null;
    ResultSet rset = null;
    
    
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
			stmt = con.prepareStatement("select gold_seats_boked ,silver_seats_booked from seatsBoked where date_of_show = ? and slot_of_show = ?");
		
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

 
 
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		  try {
			  String slot = request.getParameter("slot");
				
				ServletContext sc = getServletContext();
				sc.setAttribute("slot", slot);
				
					
				int movID = (int)sc.getAttribute("movId");
				sc.setAttribute("movId", movID);
				
				int cusID = (int)sc.getAttribute("cusId");
				sc.setAttribute("cusId", cusID);
				
				String dos = request.getParameter("DOS");
				Date date = Date.valueOf(dos);
				sc.setAttribute("DOS", date);
				stmt.setDate(1, date);
				stmt.setString(2, slot);
				rset = stmt.executeQuery();
				String seat = "",seatS="";
				while(rset.next()) {
					seat = rset.getString("gold_seats_boked");
					seatS = rset.getString("silver_seats_booked");
				}
			  
		
				out.print("<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <form action='Ticket' method='get'>\r\n"
						+ "    \r\n"
						+ "                <h2>SILVER SEATS</h2>\r\n"
						+ "                <div class='silver'>\r\n"
						);
				
				
				 if (seatS == null || seatS.contains("S1") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S1 />S1 \r\n");
				 }
				 else if (seatS.contains("S1")) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S1 disabled/>S1 \r\n");
				  }							
				  if( seatS == null || seatS.contains("S2") == false) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S2 >S2 \r\n");

				  }else if (seatS.contains("S2") ) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S2 disabled>S2 \r\n");
				  }	
				  if(seatS == null || seatS.contains("S3") == false) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S3>S3 \r\n");

				  }else if (seatS.contains("S3")) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S3 disabled>S3 \r\n");
				  }
				  if(seatS == null || seatS.contains("S4") == false) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S4>S4 \r\n");

				  }else if (seatS.contains("S4")) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S4 disabled>S4 \r\n");
				  }	
				  if(seatS == null || seatS.contains("S5")==false) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S5/>S5 \r\n");

				  }else if (seatS.contains("S5")) {
				    out.print("<input type=\"checkbox\" name = 'seatS' value = S5/ disabled>S5 \r\n");
				  }	
				  if(seatS == null || seatS.contains("S6") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S6/>S6 \r\n");

					  }else if (seatS.contains("S6")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S6/ disabled>S6 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S7") == false ){
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S7/>S7 \r\n");

					  }else if (seatS.contains("S7")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S7/ disabled>S7 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S8") == false) {
					    
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S8/>S8 \r\n");

					  }else if (seatS.contains("S8")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S8/ disabled>S8 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S9") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S9/>S9 \r\n");

					  }else if (seatS.contains("S9")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S9/ disabled>S9 \r\n");
					  }	
					   if(seatS == null || seatS.contains("S10") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S10/>S10 <br>\r\n");
					    }else if (seatS.contains("S10")) {
					      out.print("<input type=\"checkbox\" name = 'seatS' value = S10/ disabled>S10<br> \r\n");
					    }	
					 
					   if(seatS == null || seatS.contains("S11") == false) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S11/>S11 \r\n");

					      }else if (seatS.contains("S11")) { 
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S11/ disabled>S11 \r\n");
					      }		
					    if(seatS == null || seatS.contains("S12") == false) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S12/>S12 \r\n");

					      }else if (seatS.contains("S12")) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S12/ disabled>S12 \r\n");
					      }		
					    if(seatS == null || seatS.contains("S13") == false) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S13/>S13 \r\n");

					      }else if (seatS.contains("S13")) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S13/ disabled>S13 \r\n");
					      }	
					    if(seatS == null || seatS.contains("S14") == false) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S14/>S14 \r\n");

					      }else if (seatS.contains("S14")) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S14/ disabled>S14 \r\n");
					      }		
					    if(seatS == null || seatS.contains("S15") == false) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S15/>S15 \r\n");

					      }else if (seatS.contains("S15")) {
					        out.print("<input type=\"checkbox\" name = 'seatS' value = S15/ disabled>S15 \r\n");
					      }	
					  if(seatS == null || seatS.contains("S16") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S16/>S16 \r\n");

					  }else if (seatS.contains("S16")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S16/ disabled>S16\r\n");
					  }	
					  if(seatS == null || seatS.contains("S17") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S17/>S17 \r\n");

					  }else if (seatS.contains("S17")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S17/ disabled>S17 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S18") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S18/>S18 \r\n");

					  }else if (seatS.contains("S18")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S18/ disabled>S18 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S19") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S19/>S19 \r\n");

					  }else if (seatS.contains("S19")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S19/ disabled>S19 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S20") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S20/>S20 <br>\r\n");

					  }else if (seatS.contains("S20")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S20/ disabled>S20<br> \r\n");
					  }	
					  if(seatS == null || seatS.contains("S21") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S21/>S21 \r\n");

					  }else if (seatS.contains("S21")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S21/ disabled>S21 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S22") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S22/>S22 \r\n");

					  }else if (seatS.contains("S22")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S22/ disabled>S22 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S23") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S23/>S23 \r\n");

					  }else if (seatS.contains("S23")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S23/ disabled>S23 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S24") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S24/>S24 \r\n");

					  }else if (seatS.contains("S24")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S24/ disabled>S24 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S25") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S25/>S25 \r\n");

					  }else if (seatS.contains("S25")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S25/ disabled>S25 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S26") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S26/>S26 \r\n");

					  }else if (seatS.contains("S26")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S26/ disabled>S26 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S27") == false) {
					    
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S27/>S27 \r\n");

					  }else if (seatS.contains("S27")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S27/ disabled>S27 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S28") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S28/>S28 \r\n");

					  }else if (seatS.contains("S28")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S28/ disabled>S28 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S29") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S29/>S29 \r\n");

					  }else if (seatS.contains("S29")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S29/ disabled>S29 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S30") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S30/>S30 <br>\r\n");

					  }else if (seatS.contains("S30")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S30/ disabled>S30<br> \r\n");
					  }	
					  if(seatS == null || seatS.contains("S31") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S31/>S31 \r\n");

					  }else if (seatS.contains("S31")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S31/ disabled>S31 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S32") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S32/>S32 \r\n");

					  }else if (seatS.contains("S32")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S32/ disabled>S32 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S33") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S33/>S33 \r\n");

					  }else if (seatS.contains("S33")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S33/ disabled>S33 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S34") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S34/>S34 \r\n");

					  }else if (seatS.contains("S34")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S34/ disabled>S34 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S35") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S35/>S35 \r\n");

					  }else if (seatS.contains("S35")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S35/ disabled>S35 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S36") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S36/>S36 \r\n");

					  }else if (seatS.contains("S36")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S36/ disabled>S36 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S37") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S37/>S37 \r\n");

					  }else if (seatS.contains("S37")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S37/ disabled>S37 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S38") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S38/>S38 \r\n");

					  }else if (seatS.contains("S38")) {
					    
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S38/ disabled>S38 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S39") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S39/>S39 \r\n");

					  }else if (seatS.contains("S39")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S39/ disabled>S39 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S40") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S40/>S40 <br>\r\n");

					  }else if (seatS.contains("S40")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S40/ disabled>S40<br> \r\n");
					  }	
					  if(seatS == null || seatS.contains("S41") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S41/>S41 \r\n");

					  }else if (seatS.contains("S41")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S41/ disabled>S41 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S42") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S42/>S42 \r\n");

					  }else if (seatS.contains("S42")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S42/ disabled>S42 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S43") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S43/>S43 \r\n");

					  }else if (seatS.contains("S43")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S43/ disabled>S43 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S44") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S44/>S44 \r\n");

					  }else if (seatS.contains("S44")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S44/ disabled>S44 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S45") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S45/>S45 \r\n");

					  }else if (seatS.contains("S45")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S45/ disabled>S45 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S46") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S46/>S46 \r\n");

					  }else if (seatS.contains("S46")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S46/ disabled>S46 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S47") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S47/>S47 \r\n");

					  }else if (seatS.contains("S47")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S47/ disabled>S47 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S48") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S48/>S48 \r\n");

					  }else if (seatS.contains("S48")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S48/ disabled>S48 \r\n");
					  }	
					  if(seatS == null || seatS.contains("S49") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S49/>S49 \r\n");

					  }else if (seatS.contains("S49")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S49/ disabled>S49 \r\n");
					  }
					  if(seatS == null || seatS.contains("S50") == false) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S50/>S50 <br>\r\n");

					  }else if (seatS.contains("S50")) {
					    out.print("<input type=\"checkbox\" name = 'seatS' value = S50/ disabled>S50 <br>\r\n");
					  }	
//					

						out.print( "<H2>GOLD SEATS</H2>\r\n");
						
						if(seat == null ||  seat.contains("G1")==false) {
							out.print("<input type=\"checkbox\" name = 'seat' value = G1/>G1 \r\n");

						}else if (seat.contains("G1")) {
							out.print("<input type=\"checkbox\" name = 'seat' value = G1/ disabled>G1 \r\n");
						}
						 if(seat == null || seat.contains("G2")==false) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G2/>G2 \r\n");

						 }else if (seat.contains("G2")) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G2/ disabled>G2 \r\n");
							  }	
						 if(seat == null || seat.contains("G3")==false) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G3/>G3 \r\n");

							  }else if (seat.contains("G3")) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G3/ disabled>G3 \r\n");
							  }
							  if(seat == null || seat.contains("G4")==false) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G4/>G4 \r\n");

							  }else if (seat.contains("G4")) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G4/ disabled>G4 \r\n");
							  }	
							  if(seat == null ||seat.contains("G5")==false) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G5/>G5 \r\n");

							  }else if (seat.contains("G5")) {
							    out.print("<input type=\"checkbox\" name = 'seat' value = G5/ disabled>G5 \r\n");
							  }	
							  if(seat == null || seat.contains("G6") == false ){
								    out.print("<input type=\"checkbox\" name = 'seat' value = G6/>G6 \r\n");

								  }else if (seat.contains("G6")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G6/ disabled>G6 \r\n");
								  }	
								  if(seat == null || seat.contains("G7") == false ){
								    out.print("<input type=\"checkbox\" name = 'seat' value = G7/>G7 \r\n");

								  }else if (seat.contains("G7")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G7/ disabled>G7 \r\n");
								  }	
								  if(seat == null || seat.contains("G8") == false ){
								    
								    out.print("<input type=\"checkbox\" name = 'seat' value = G8/>G8 \r\n");

								  }else if (seat.contains("G8")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G8/ disabled>G8 \r\n");
								  }	
								  if(seat == null || seat.contains("G9") == false ){
								    out.print("<input type=\"checkbox\" name = 'seat' value = G9/>G9 \r\n");

								  }else if (seat.contains("G9")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G9/ disabled>G9 \r\n");
								  }	
								   if(seat == null || seat.contains("G10") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G10/>G10 <br>\r\n");
								    }else if (seat.contains("G10")) {
								      out.print("<input type=\"checkbox\" name = 'seat' value = G10/ disabled>G10<br> \r\n");
								    }	
								 
								   if(seat == null || seat.contains("G11") == false) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G11/>G11 \r\n");

								      }else if (seat.contains("G11")) { 
								        out.print("<input type=\"checkbox\" name = 'seat' value = G11/ disabled>G11 \r\n");
								      }		
								    if(seat == null || seat.contains("G12") == false) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G12/>G12 \r\n");

								      }else if (seat.contains("G12")) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G12/ disabled>G12 \r\n");
								      }		
								    if(seat == null || seat.contains("G13") == false) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G13/>G13 \r\n");

								      }else if (seat.contains("G13")) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G13/ disabled>G13 \r\n");
								      }	
								    if(seat == null || seat.contains("G14") == false) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G14/>G14 \r\n");

								      }else if (seat.contains("G14")) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G14/ disabled>G14 \r\n");
								      }		
								    if(seat == null || seat.contains("G15") == false) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G15/>G15 \r\n");

								      }else if (seat.contains("G15")) {
								        out.print("<input type=\"checkbox\" name = 'seat' value = G15/ disabled>G15 \r\n");
								      }	
								  if(seat == null || seat.contains("G16") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G16/>G16 \r\n");

								  }else if (seat.contains("G16")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G16/ disabled>G16\r\n");
								  }	
								  if(seat == null || seat.contains("G17") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G17/>G17 \r\n");

								  }else if (seat.contains("G17")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G17/ disabled>G17 \r\n");
								  }	
								  if(seat == null || seat.contains("G18") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G18/>G18 \r\n");

								  }else if (seat.contains("G18")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G18/ disabled>G18 \r\n");
								  }	
								  if(seat == null || seat.contains("G19") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G19/>G19 \r\n");

								  }else if (seat.contains("G19")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G19/ disabled>G19 \r\n");
								  }	
								  if(seat == null || seat.contains("G20") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G20/>G20 <br>\r\n");

								  }else if (seat.contains("G20")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G20/ disabled>G20<br> \r\n");
								  }	
								  if(seat == null || seat.contains("G21") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G21/>G21 \r\n");

								  }else if (seat.contains("G21")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G21/ disabled>G21 \r\n");
								  }	
								  if(seat == null || seat.contains("G22") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G22/>G22 \r\n");

								  }else if (seat.contains("G22")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G22/ disabled>G22 \r\n");
								  }	
								  if(seat == null || seat.contains("G23") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G23/>G23 \r\n");

								  }else if (seat.contains("G23")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G23/ disabled>G23 \r\n");
								  }	
								  if(seat == null || seat.contains("G24") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G24/>G24 \r\n");

								  }else if (seat.contains("G24")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G24/ disabled>G24 \r\n");
								  }	
								  if(seat == null || seat.contains("G25") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G25/>G25 \r\n");

								  }else if (seat.contains("G25")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G25/ disabled>G25 \r\n");
								  }	
								  if(seat == null || seat.contains("G26") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G26/>G26 \r\n");

								  }else if (seat.contains("G26")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G26/ disabled>G26 \r\n");
								  }	
								  if(seat == null || seat.contains("G27") == false) {
								    
								    out.print("<input type=\"checkbox\" name = 'seat' value = G27/>G27 \r\n");

								  }else if (seat.contains("G27")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G27/ disabled>G27 \r\n");
								  }	
								  if(seat == null || seat.contains("G28") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G28/>G28 \r\n");

								  }else if (seat.contains("G28")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G28/ disabled>G28 \r\n");
								  }	
								  if(seat == null || seat.contains("G29") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G29/>G29 \r\n");

								  }else if (seat.contains("G29")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G29/ disabled>G29 \r\n");
								  }	
								  if(seat == null || seat.contains("G30") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G30/>G30 <br>\r\n");

								  }else if (seat.contains("G30")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G30/ disabled>G30<br> \r\n");
								  }	
								  if(seat == null || seat.contains("G31") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G31/>G31 \r\n");

								  }else if (seat.contains("G31")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G31/ disabled>G31 \r\n");
								  }	
								  if(seat == null || seat.contains("G32") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G32/>G32 \r\n");

								  }else if (seat.contains("G32")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G32/ disabled>G32 \r\n");
								  }	
								  if(seat == null || seat.contains("G33") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G33/>G33 \r\n");

								  }else if (seat.contains("G33")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G33/ disabled>G33 \r\n");
								  }	
								  if(seat == null || seat.contains("G34") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G34/>G34 \r\n");

								  }else if (seat.contains("G34")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G34/ disabled>G34 \r\n");
								  }	
								  if(seat == null || seat.contains("G35") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G35/>G35 \r\n");

								  }else if (seat.contains("G35")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G35/ disabled>G35 \r\n");
								  }	
								  if(seat == null || seat.contains("G36") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G36/>G36 \r\n");

								  }else if (seat.contains("G36")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G36/ disabled>G36 \r\n");
								  }	
								  if(seat == null || seat.contains("G37") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G37/>G37 \r\n");

								  }else if (seat.contains("G37")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G37/ disabled>G37 \r\n");
								  }	
								  if(seat == null || seat.contains("G38") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G38/>G38 \r\n");

								  }else if (seat.contains("G38")) {
								    
								    out.print("<input type=\"checkbox\" name = 'seat' value = G38/ disabled>G38 \r\n");
								  }	
								  if(seat == null || seat.contains("G39") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G39/>G39 \r\n");

								  }else if (seat.contains("G39")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G39/ disabled>G39 \r\n");
								  }	
								  if(seat == null || seat.contains("G40") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G40/>G40 <br>\r\n");

								  }else if (seat.contains("G40")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G40/ disabled>G40<br> \r\n");
								  }	
								  if(seat == null || seat.contains("G41") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G41/>G41 \r\n");

								  }else if (seat.contains("G41")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G41/ disabled>G41 \r\n");
								  }	
								  if(seat == null || seat.contains("G42") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G42/>G42 \r\n");

								  }else if (seat.contains("G42")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G42/ disabled>G42 \r\n");
								  }	
								  if(seat == null || seat.contains("G43") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G43/>G43 \r\n");

								  }else if (seat.contains("G43")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G43/ disabled>G43 \r\n");
								  }	
								  if(seat == null || seat.contains("G44") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G44/>G44 \r\n");

								  }else if (seat.contains("G44")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G44/ disabled>G44 \r\n");
								  }	
								  if(seat == null || seat.contains("G45") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G45/>G45 \r\n");

								  }else if (seat.contains("G45")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G45/ disabled>G45 \r\n");
								  }	
								  if(seat == null || seat.contains("G46") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G46/>G46 \r\n");

								  }else if (seat.contains("G46")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G46/ disabled>G46 \r\n");
								  }	
								  if(seat == null || seat.contains("G47") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G47/>G47 \r\n");

								  }else if (seat.contains("G47")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G47/ disabled>G47 \r\n");
								  }	
								  if(seat == null || seat.contains("G48") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G48/>G48 \r\n");

								  }else if (seat.contains("G48")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G48/ disabled>G48 \r\n");
								  }	
								  if(seat == null || seat.contains("G49") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G49/>G49 \r\n");

								  }else if (seat.contains("G49")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G49/ disabled>G49 \r\n");
								  }
								  if(seat == null || seat.contains("G50") == false) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G50/>G50 <br>\r\n");

								  }else if (seat.contains("G50")) {
								    out.print("<input type=\"checkbox\" name = 'seat' value = G50/ disabled>G50 <br>\r\n");
								  }	
						
						
						

						out.print("<input type='submit' name='book'/>"
						+ "    </form>\r\n"
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
		
	}

}
