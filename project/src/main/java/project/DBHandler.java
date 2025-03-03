package project;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
	import java.util.List;

	import org.apache.catalina.connector.Response;

	import oracle.jdbc.pool.OracleDataSource;
import project.model.CustomerInfoCls;
import project.model.DateSlotCls;
import project.model.MovieCls;
import project.model.MoviePageCls;
import project.model.SeatsBookedcls;

	public class DBHandler {
		int cusId = 0;
		public Connection getDBCon()
		{
			Connection con=null;
			try {
				OracleDataSource ods=new OracleDataSource();
				ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
				con=ods.getConnection("Lovish","password");
				System.out.println("connection established successfully");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
		
		public boolean isAdmin(String unm,String pwd)
		{
			boolean res=false;
			Connection con=getDBCon();
			try {
				PreparedStatement stmt=con.prepareStatement("select * from tbluser where unm=? and upwd=?");
				stmt.setString(1, unm);stmt.setString(2, pwd);
				ResultSet rset=stmt.executeQuery();
				if(rset.next())
				{
					res=true;
				}
				else
				{
					res=false;
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return res;
		}
		public List<MoviePageCls> getMovieByMovieId (int movieid)
		{
			//select * from products where categoryId=4;
			List<MoviePageCls> lst=new LinkedList<>();
			Connection con=getDBCon();
			try {
				PreparedStatement stmt=con.prepareStatement("select * from movie where movie_id=?");
				stmt.setInt(1, movieid);
				ResultSet rset=stmt.executeQuery();
				while(rset.next())
				{
					String MovieName=rset.getString("title"),MovieDesc=rset.getString("description");
					int LangId=rset.getInt("Language_Id");
					double GoldSeatprice=rset.getDouble("Gold_price"),silverSeatprice=rset.getDouble("silver_price");
					Date ReleaseDate = rset.getDate("start_date"),EndDate=rset.getDate("end_date");
					String language = rset.getString("orignal_language");
					String s9t12 = rset.getString("slot_9to12"),s12t15 = rset.getString("slot_12to15"),s15t18 = rset.getString("slot_15to18"),s18t21 = rset.getString("slot_18to21");
					MoviePageCls obj=new MoviePageCls(MovieName, MovieDesc, LangId, GoldSeatprice, silverSeatprice,ReleaseDate,EndDate,language,s9t12,s12t15,s15t18,s18t21);
					lst.add(obj);
				}
				con.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return lst;
		}
		public List<SeatsBookedcls> getTicketBySBId (int movieid)
		{
			//select * from products where categoryId=4;
			List<SeatsBookedcls> lst=new LinkedList<>();
			Connection con=getDBCon();
			try {
				PreparedStatement stmt=con.prepareStatement("select * from seatsboked where movie_id=?");
				stmt.setInt(1, movieid);
				ResultSet rset=stmt.executeQuery();
				while(rset.next())
				{
					String slot = rset.getString("slot_of_show");
					Date DOS = rset.getDate("date_of_show");
					SeatsBookedcls obj=new SeatsBookedcls(DOS,slot);
					lst.add(obj);
				}
				con.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return lst;
		}
		public void newCustomer(String cusNm,String cusEmail, String cusPwd) {
			Connection con = getDBCon();
			try {
				int i =0;
				PreparedStatement stmt=con.prepareStatement("insert into customer values(?,?,?,?)");
				PreparedStatement stmt1=con.prepareStatement("select max(customer_id) from customer");
				ResultSet rset = stmt1.executeQuery();
								
				while(rset.next()) {
				 int i1 = rset.getInt("max(customer_id)");
				 i = i1 +1;
				}
				stmt.setInt(1, i);stmt.setString(2, cusNm);stmt.setString(3, cusEmail);stmt.setString(4, cusPwd);
				stmt.executeUpdate();
			
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		public void newTicket(int moviId,int cusId,Date strDE) {
			Connection con = getDBCon();
			try {
				PreparedStatement stmt = con.prepareStatement("insert into Tickect values(?,?,?,?,?,?,?,?)");
				
				
				stmt.setInt(1, 1); stmt.setInt(2, moviId);stmt.setInt(3, 1);stmt.setString(4, "G1");stmt.setString(5, "S1");stmt.setDate(6,strDE);stmt.setString(7, "9to12");stmt.setInt(8, 800);
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		public boolean isCustomer(String cusEmail, String cusPwd) {
			boolean res = false;
			Connection con = getDBCon();
			try {
				PreparedStatement stmt=con.prepareStatement("select * from customer where customer_email=? and customer_pass=?");
				stmt.setString(1, cusEmail);stmt.setString(2, cusPwd);
				ResultSet rset=stmt.executeQuery();
				
				if(rset.next())
				{
					res=true;
				}
				else
				{
					res=false;
				}
				
				
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return res;
		}
				
		public List<CustomerInfoCls> getCusInfo(String cusEmail, String cusPwd)
		{
			List<CustomerInfoCls> lst=new LinkedList<CustomerInfoCls>();
			Connection con=getDBCon();
			try {
				PreparedStatement stmt=con.prepareStatement("select * from customer where customer_email=? and customer_pass=?");
				stmt.setString(1, cusEmail);stmt.setString(2,cusPwd);
				ResultSet rset=stmt.executeQuery();
				while(rset.next()) {
					int cusId = rset.getInt("customer_id");
					String cusNm = rset.getString("customer_name");
					CustomerInfoCls objCI=new CustomerInfoCls(cusId,cusNm);
					lst.add(objCI);
				}
				con.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return lst;
		}
		
		public List<MovieCls> getTblMovie()
		{
			List<MovieCls> lst=new LinkedList<MovieCls>();
			Connection con=getDBCon();
			try {
				PreparedStatement stmt=con.prepareStatement("select * from movie");
				ResultSet rset=stmt.executeQuery();
				while(rset.next())
				{
					 int MovieId=rset.getInt("movie_id");
					 String MovieName=rset.getString("title");
					 String MovieDesc=rset.getString("description");
					 String MovieYear=rset.getString("release_year");
					 int MovieRating=rset.getInt("rating");
					 MovieCls objCat=new MovieCls(MovieId, MovieName, MovieDesc, MovieYear,MovieRating);
					 lst.add(objCat);
				}
				con.close();
				stmt.close();
				rset.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lst;
		}
		
		
		
}
