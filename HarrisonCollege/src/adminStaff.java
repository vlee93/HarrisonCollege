

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HStaff;
import customTools.DBUtil;

/**
 * Servlet implementation class adminStaff
 */
@WebServlet("/adminStaff")
public class adminStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String adminStfMsg;
	private String adminStfSuccess;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminStaff() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminStfMsg = "";
		adminStfSuccess = "";
		HttpSession session = request.getSession();
		if (session.getAttribute("success") != null) {
			adminStfSuccess += (String) session.getAttribute("success");
		}
		session.removeAttribute("success");
		
		adminStfMsg += "<div class=\"container\"><form role=\"form\" name=\"staffType\" id=\"staffType\" action=\"adminStaff\" method=\"post\"><h3>Change Staff Type</h3><table class=\"table\"><thead><tr><th>Staff ID</th><th>Name</th><th>Department</th><th>Office #</th><th>Type</th></tr></thead><tbody>";
		List<HStaff> stf = DBUtil.createQuery("SELECT h FROM HStaff h", HStaff.class).getResultList();
		for (int i = 0; i < stf.size(); i++) {
			if (stf.get(i).getType().equalsIgnoreCase("Instructor")) {
				adminStfMsg += "<tr><td>" + stf.get(i).getStaffId() + "</td><td>" + stf.get(i).getStaffName() + "</td><td>" + stf.get(i).getHDepartment().getDeptName() + "</td><td>" + stf.get(i).getOfficeNo() + "</td><td><div class=\"form-group\"><select class=\"form-control\" name=\"" + stf.get(i).getStaffId() + "\" id=\"" + stf.get(i).getStaffId() + "\"><option selected>Instructor</option><option>Advisor</option><option>Admin</option></select></div></td></tr>";
			} else if (stf.get(i).getType().equalsIgnoreCase("Advisor")) {
				adminStfMsg += "<tr><td>" + stf.get(i).getStaffId() + "</td><td>" + stf.get(i).getStaffName() + "</td><td>" + stf.get(i).getHDepartment().getDeptName() + "</td><td>" + stf.get(i).getOfficeNo() + "</td><td><div class=\"form-group\"><select class=\"form-control\" name=\"" + stf.get(i).getStaffId() + "\" id=\"" + stf.get(i).getStaffId() + "\"><option>Instructor</option><option selected>Advisor</option><option>Admin</option></select></div></td></tr>";
			} else {
				adminStfMsg += "<tr><td>" + stf.get(i).getStaffId() + "</td><td>" + stf.get(i).getStaffName() + "</td><td>" + stf.get(i).getHDepartment().getDeptName() + "</td><td>" + stf.get(i).getOfficeNo() + "</td><td><div class=\"form-group\"><select class=\"form-control\" name=\"" + stf.get(i).getStaffId() + "\" id=\"" + stf.get(i).getStaffId() + "\"><option>Instructor</option><option>Advisor</option><option selected>Admin</option></select></div></td></tr>";
			}
		}
		adminStfMsg += "</tbody></table><div class=\"form-group\"><button type=\"submit\" class=\"btn btn-default\" name=\"type\" id=\"type\">Submit</button></div></form></div>";
		
		request.setAttribute("adminStfMsg", adminStfMsg);
		request.setAttribute("adminStfSuccess", adminStfSuccess);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("type") != null) {
			List<HStaff> stf = DBUtil.createQuery("SELECT h FROM HStaff h", HStaff.class).getResultList();
			for (int i = 0; i < stf.size(); i++) {
				String type = request.getParameter(stf.get(i).getStaffId() + "");
				stf.get(i).setType(type);
				DBUtil.updateDB(stf.get(i));
			}
			
			String success = "<div class=\"container\"><div class=\"alert alert-success\"><strong>Success!</strong> You have successfully updated the staff type.</div></div>";
			session.setAttribute("success", success);
			response.sendRedirect("/HarrisonCollege/adminStaff");
		}
	}

}
