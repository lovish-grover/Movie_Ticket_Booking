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
 * Servlet implementation class Auth1
 */
@WebServlet("/Auth1")
public class Auth1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    DBHandler objDH = new DBHandler();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strEml = request.getParameter("email"),strPwd = request.getParameter("password");
		boolean res=objDH.isCustomer(strEml,strPwd);
		List<CustomerInfoCls> lst =objDH.getCusInfo(strEml, strPwd);
		if(res==true)
		{
			for(CustomerInfoCls obj : lst) {
			response.sendRedirect(request.getContextPath()+"/HomePage?cusId="+obj.getCusId());
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/ErrorPage.html");
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
