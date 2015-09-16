

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.HCourse;
import model.HDepartment;
import model.HStaff;

/**
 * Servlet implementation class adminCourses
 */
@WebServlet("/adminCourses")
public class adminCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String adminCourMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminCourses() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminCourMsg = "";
		HttpSession session = request.getSession();
		String error = "";
		if (session.getAttribute("depterror") != null) {
			error += (String) session.getAttribute("depterror");
		}
		session.removeAttribute("depterror");
				
		adminCourMsg += "<div class=\"container\"><h3>Create Course</h3><form class=\"form-horizontal\" role=\"form\" name=\"addCourse\" id=\"addCourse\" action=\"adminCourses\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"subjcode\">Subject Code:</label><input type=\"text\" class=\"form-control\" name=\"subjcode\" id=\"subjcode\"></div>"
				+ "<div class=\"form-group\"><label for=\"coursenum\">Course Number:</label><input type=\"text\" class=\"form-control\" name=\"coursenum\" id=\"coursenum\"></div>"
				+ "<div class=\"form-group\"><label for=\"courname\">Course Name:</label><input type=\"text\" class=\"form-control\" name=\"courname\" id=\"courname\"></div>"
				+ "<div class=\"form-group\"><label for=\"descrip\">Description:</label><textarea class=\"form-control\" rows=\"5\" name=\"descrip\" id=\"descrip\"></textarea></div>"
				+ "<div class=\"form-group\"><label for=\"crednum\">Credit #:</label><input type=\"text\" class=\"form-control\" name=\"crednum\" id=\"crednum\"></div>"
				+ "<div class=\"form-group\"><label for=\"dept\">Department:</label><input type=\"text\" class=\"form-control\" name=\"dept\" id=\"dept\"></div>"
				+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"addC\" id=\"addC\">Submit</button></form>";
		
		adminCourMsg += "<br /><h3>Courses</h3><table class=\"table\"><thead><tr><th>Code</th><th>Course Name</th><th>Description</th><th>Credit #</th><th>Department</th><th>Available</th><th>Edit</th></tr></thead><tbody>";
		List<HCourse> course = DBUtil.createQuery("SELECT h FROM HCourse h", HCourse.class).getResultList();
		for (int i = 0; i < course.size(); i++) {
			String avail = "";
			if (course.get(i).getAvailable() == 1) {
				avail = "yes";
			} else {
				avail = "no";
			}
			adminCourMsg += "<tr><td>" + course.get(i).getSubjectCode() + " " + course.get(i).getCourseNo() + "</td><td>" + course.get(i).getCourseName() + "</td><td>" + course.get(i).getCourseDesc() + "</td><td>" + course.get(i).getCredits() + "</td><td>" + course.get(i).getHDepartment().getDeptName() + "</td><td>" + avail + "</td><td><a href=\"adminEditDetails?courseid=" + course.get(i).getCourseId() + "\" class=\"btn btn-info\" role=\"button\">Edit</a></td></tr>";
		}
		adminCourMsg += "</tbody></table></div>";
		request.setAttribute("depterror", error);
		request.setAttribute("adminCourMsg", adminCourMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = "";
		HttpSession session = request.getSession();
		if (request.getParameter("addC") != null) {
			String subjco = request.getParameter("subjcode");
			int courseno = Integer.parseInt(request.getParameter("coursenum"));			
			String courname = request.getParameter("courname");
			String descrip = request.getParameter("descrip");
			double crednum = Double.parseDouble(request.getParameter("crednum"));
			String strdept = request.getParameter("dept");
			int available = Integer.parseInt(request.getParameter("avail"));
			
			HCourse newcour = new HCourse();
			newcour.setSubjectCode(subjco);
			newcour.setCourseNo(courseno);
			newcour.setCourseName(courname);
			newcour.setCourseDesc(descrip);	
			newcour.setCredits(crednum);			
			newcour.setAvailable(available);
			
			try {
				HDepartment dept = DBUtil.createQuery("SELECT h FROM HDepartment h WHERE h.deptName = '" + strdept + "'", HDepartment.class).getSingleResult();
				newcour.setHDepartment(dept);
				DBUtil.addToDB(newcour);
			} catch(Exception e) {
				e.printStackTrace();
				error += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Department does not exist.</div></div>";
				session.setAttribute("depterror", error);
			}
			response.sendRedirect("/HarrisonCollege/adminCourses");
		}
	}
}
