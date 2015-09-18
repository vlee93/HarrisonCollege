

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HDepartment;
import customTools.DBUtil;

/**
 * Servlet implementation class adminDepartments
 */
@WebServlet("/adminDepartments")
public class adminDepartments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String adminDeptMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminDepartments() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminDeptMsg = "";
		
		adminDeptMsg += "<div class=\"container\"><h3>Create Department</h3><form class=\"form-horizontal\" role=\"form\" name=\"addDept\" id=\"addDept\" onsubmit=\"return validateForm4()\" action=\"adminDepartments\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"deptname\">Department Name:</label><input type=\"text\" class=\"form-control\" name=\"deptname\" id=\"deptname\"></div>"
				+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"addD\" id=\"addD\">Submit</button></form>";
		
		adminDeptMsg += "<br /><h3>Departments</h3><table class=\"table\"><thead><tr><th>Department ID</th><th>Name</th><th>Available</th><th>Edit</th></tr></thead><tbody>";
		List<HDepartment> dept = DBUtil.createQuery("SELECT h FROM HDepartment h", HDepartment.class).getResultList();
		for (int i = 0; i < dept.size(); i++) {
			String avail = "";
			if (dept.get(i).getAvailable() == 1) {
				avail = "yes";
			} else {
				avail = "no";
			}
			adminDeptMsg += "<tr><td>" + dept.get(i).getDeptId() + "</td><td>" + dept.get(i).getDeptName() + "</td><td>" + avail + "</td><td><a href=\"adminEditDetails?deptid=" + dept.get(i).getDeptId() + "\" class=\"btn btn-info\" role=\"button\">Edit</a></td></tr>";
		}
		adminDeptMsg += "</tbody></table></div>";
		request.setAttribute("adminDeptMsg", adminDeptMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("addD") != null) {
			String deptname = request.getParameter("deptname");
			int available = Integer.parseInt(request.getParameter("avail"));
			HDepartment newdept = new HDepartment();
			newdept.setAvailable(available);
			newdept.setDeptName(deptname);
			DBUtil.addToDB(newdept);
			response.sendRedirect("/HarrisonCollege/adminDepartments");
		}
	}

}
