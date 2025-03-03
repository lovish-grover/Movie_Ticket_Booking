package project;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.model.CustomerInfoCls;

/** 
 * Servlet implementation class Auth2
 */
@WebServlet("/Auth2")
public class Auth2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    DBHandler objDH = new DBHandler();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNm = request.getParameter("name"),strEmal = request.getParameter("email"),strPwd = request.getParameter("password");
		
		objDH.newCustomer(strNm, strEmal, strPwd);
		List<CustomerInfoCls> lst =objDH.getCusInfo(strEmal, strPwd);
		for(CustomerInfoCls obj : lst) {
			response.sendRedirect(request.getContextPath()+"/HomePage?cusId="+obj.getCusId());
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
