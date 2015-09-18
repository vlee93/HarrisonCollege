

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HCourse;
import model.HDepartment;
import model.HMajor;
import customTools.DBUtil;

/**
 * Servlet implementation class adminMajors
 */
@WebServlet("/adminMajors")
public class adminMajors extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String adminMajorMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminMajors() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminMajorMsg = "";
		HttpSession session = request.getSession();
		String error = "";
		if (session.getAttribute("depterror") != null) {
			error += (String) session.getAttribute("depterror");
		}
		session.removeAttribute("depterror");
				
		adminMajorMsg += "<div class=\"container\"><h3>Create Major</h3><form class=\"form-horizontal\" role=\"form\" name=\"addMajor\" id=\"addMajor\" onsubmit=\"return validateForm3()\" action=\"adminMajors\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"majorname\">Major Name:</label><input type=\"text\" class=\"form-control\" name=\"majorname\" id=\"majorname\"></div>"
				+ "<div class=\"form-group\"><label for=\"dept\">Department:</label><input type=\"text\" class=\"form-control\" name=\"dept\" id=\"dept\"></div>"
				+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"addM\" id=\"addM\">Submit</button></form>";
		
		adminMajorMsg += "<br /><h3>Majors</h3><table class=\"table\"><thead><tr><th>Major Name</th><th>Department</th><th>Available</th><th>Edit</th></tr></thead><tbody>";
		List<HMajor> major = DBUtil.createQuery("SELECT h FROM HMajor h", HMajor.class).getResultList();
		for (int i = 0; i < major.size(); i++) {
			String avail = "";
			if (major.get(i).getAvailable() == 1) {
				avail = "yes";
			} else {
				avail = "no";
			}
			adminMajorMsg += "<tr><td>" + major.get(i).getMajorName() + "</td><td>" + major.get(i).getHDepartment().getDeptName() + "</td><td>" + avail + "</td><td><a href=\"adminEditDetails?majorid=" + major.get(i).getMajorId() + "\" class=\"btn btn-info\" role=\"button\">Edit</a></td></tr>";
		}
		adminMajorMsg += "</tbody></table></div>";
		request.setAttribute("depterror", error);
		request.setAttribute("adminMajorMsg", adminMajorMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		HttpSession session = request.getSession();
		if (request.getParameter("addM") != null) {		
			String majorname = request.getParameter("majorname");
			String strdept = request.getParameter("dept");
			int available = Integer.parseInt(request.getParameter("avail"));
			
			HMajor newmajor = new HMajor();
			newmajor.setMajorName(majorname);		
			newmajor.setAvailable(available);
			
			try {
				HDepartment dept = DBUtil.createQuery("SELECT h FROM HDepartment h WHERE h.deptName = '" + strdept + "'", HDepartment.class).getSingleResult();
				newmajor.setHDepartment(dept);
				DBUtil.addToDB(newmajor);
			} catch(Exception e) {
				e.printStackTrace();
				error += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Department does not exist.</div></div>";
				session.setAttribute("depterror", error);
			}
			response.sendRedirect("/HarrisonCollege/adminMajors");
		}
	}

}
