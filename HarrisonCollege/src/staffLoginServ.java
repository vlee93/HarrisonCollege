

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HDepartment;
import model.HStaff;
import customTools.DBUtil;

/**
 * Servlet implementation class staffLoginServ
 */
@WebServlet("/staffLoginServ")
public class staffLoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String error;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public staffLoginServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		error = "";
		if (request.getParameter("createAcc") != null) {
			HStaff newSta= new HStaff();
			String newName = (String) request.getParameter("staName");
			String newPwd = (String) request.getParameter("staPwd");
			String newDeptName = (String) request.getParameter("department");
			newSta.setStaffName(newName);
			newSta.setUserPassword(newPwd);
			
			try {
				HDepartment newDept = DBUtil.createQuery("SELECT h FROM HDepartment h WHERE h.deptName = '" + newDeptName + "' AND h.available = 1", HDepartment.class).getSingleResult();
				newSta.setHDepartment(newDept);
			} catch (Exception e) {
				e.printStackTrace();
				error += "<div class=\"alert alert-danger\"><strong>Error!</strong> Department does not exist.</div>";
				request.setAttribute("error", error);
				getServletContext().getRequestDispatcher("/staffLogin.jsp").forward(request, response);
			}
			String newOffice = (String) request.getParameter("office");
			newSta.setOfficeNo(newOffice);
			DBUtil.addToDB(newSta);
			
			HStaff sta = DBUtil.createQuery("SELECT h FROM HStaff h WHERE h.staffName = '" + newName + "' AND h.HDepartment.deptName = '" + newDeptName + "' AND h.userPassword = '" + newPwd + "'", HStaff.class).getSingleResult();
			long newStaId = sta.getStaffId();
			error += "<div class=\"alert alert-success\"><strong>Success!</strong> Your staff ID is " + newStaId + ". Please login.</div>";
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/staffLogin.jsp").forward(request, response);
		}
		
		if (request.getParameter("login") != null) {
			long existId = Long.parseLong((String) request.getParameter("staID"));
			String existPwd = (String) request.getParameter("loginPwd");
			HStaff existSta = DBUtil.createQuery("SELECT h FROM HStaff h WHERE h.staffId = " + existId, HStaff.class).getSingleResult();
			if (existSta.getUserPassword().equals(existPwd)) {
				session.setAttribute("userStaff", existSta);
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				error += "<div class=\"alert alert-danger\"><strong>Error!</strong> Password is incorrect.</div>";
				request.setAttribute("error", error);
				getServletContext().getRequestDispatcher("/staffLogin.jsp").forward(request, response);
			}
		}
	}

}