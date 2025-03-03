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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.pool.OracleDataSource;
import oracle.net.nt.ConnectDescription;

/**
 * Servlet implementation class movie_inserted
 */
@WebServlet("/movie_inserted")
public class movie_inserted extends HttpServlet {   
	private static final long serialVersionUID = 1L;
       
	PreparedStatement stmt = null,stmt1=null,stmt2=null;
	Connection con = null;
	ResultSet rset = null;
	
	public Connection getDBCon()
	{
		Connection con = null;
		try {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con = ods.getConnection("Lovish","password");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;
	}
	public void init() 
   	{
   		
   		con=getDBCon();
   		try {
   			stmt=con.prepareStatement("insert into movie values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
   			stmt1=con.prepareStatement("INSERT INTO seatsboked VALUES(?,?,?,?,?,?,?,?,?,?)");
   			stmt2=con.prepareStatement("select max(seatsboked_id) from seatsboked");
   			
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   	}
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) 
    {
        long diffInMillies = date2.getTime() - date1.getTime();
         
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
    
   	public static Date addDays(Date d, int days)
    {
        d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
        return d;
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movie_inserted() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String strTitle,strDesc,strYear,strOL,strS9t12,strS12t15,strS15t18,strS18t21,strDS,strDE;
			String nmLid;
			String nmRat,nmGP,nmSP,nmMID;

			
			strTitle=request.getParameter("txtTit");
			strDesc=request.getParameter("txtDes");
			strYear=request.getParameter("txtYer");
			strOL=request.getParameter("txtOlang");
			strS9t12=request.getParameter("txtS9t12");
			strS12t15=request.getParameter("txtS12t15");
			strS15t18=request.getParameter("txtS15t18");
			strS18t21=request.getParameter("txtS18t21");
			strDS=request.getParameter("dateSd");
			strDE=request.getParameter("dateEd");
			
			nmLid=request.getParameter("nmLid");
			nmRat=request.getParameter("nmRat");
			nmGP=request.getParameter("nmGP");
			nmSP=request.getParameter("nmSP");
			nmMID=request.getParameter("nmMid");
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date uds=sdf.parse(strDS);
			long ms=uds.getTime();
			java.sql.Date sqDs=new java.sql.Date(ms);
			
			
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date ude=sdf1.parse(strDE);
			long ms1=ude.getTime();
			java.sql.Date sqDe=new java.sql.Date(ms1);
			
			
			stmt.setString(1, nmMID);
			stmt.setString(2, strTitle);
			stmt.setString(3, strDesc);
			stmt.setString(4, strYear);
			stmt.setString(5, nmLid);
			stmt.setString(6, strOL);
			stmt.setString(7, nmRat);
			stmt.setDate(8,  sqDs);
			stmt.setDate(9, sqDe);
			stmt.setString(10, strS9t12);
			stmt.setString(11, strS12t15);
			stmt.setString(12, strS15t18);
			stmt.setString(13, strS18t21);
			stmt.setString(14, nmGP);
			stmt.setString(15, nmSP);
			
			stmt.executeUpdate();
			
			
			long days= getDateDiff(sqDs, sqDe, TimeUnit.DAYS);

			PrintWriter out=response.getWriter();
			
			List<String> list=new ArrayList<>();
			if(strS9t12.equals("false")) {
				out.println(" not in 9to12");
				}
			else if(strS9t12.equals("true")) {
				list.add("9to12");
				}
			if(strS12t15.equals("false")) {
			out.println(" not in 12 to 15");
			}
			else if(strS12t15.equals("true")) {
			list.add("12to15");
			}
			if(strS15t18.equals("false")) {
			out.println(" not in 15to18");
			}
			else if(strS15t18.equals("true")){
			list.add("15TO18");
			}
			if(strS18t21.equals("false")) {
			out.println(" not in 18to21");
			}
			else if(strS18t21.equals("true")) {
			list.add("18TO21");
			}
			int nmSI = 0;
			rset = stmt2.executeQuery();
			while(rset.next()) {
				nmSI = rset.getInt("max(seatsboked_id)");
			}
			int nmSID=nmSI;
//			String strmid = request.getParameter("nmMid");
			Date dateofshow=sqDs;
			for(int i=0;i<=days;i++) {
				
				
				
				for (String obj : list) 
				{
					nmSID += 1;
				stmt1.setInt(1, nmSID);
				stmt1.setString(2, nmMID);
				stmt1.setInt(3, 50);
				stmt1.setInt(4, 50);
				stmt1.setString(5, null);
				stmt1.setString(6, null);
				stmt1.setDate(7, dateofshow);
				stmt1.setInt(8, 50);
				stmt1.setInt(9, 50);
				stmt1.setString(10, obj);
				stmt1.executeUpdate();
				}
				dateofshow=addDays(dateofshow, 1);
				
			}
			out.print("movie inserted");
			
		}
		catch(SQLException | ParseException se) {
			se.printStackTrace();
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
