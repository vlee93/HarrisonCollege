

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HClass;
import model.HClassroom;
import model.HEnrollment;
import customTools.DBUtil;

/**
 * Servlet implementation class courseF
 */
@WebServlet("/courseF")
public class courseF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String courseFMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public courseF() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		courseFMsg = "";
		HttpSession session = request.getSession();
		courseFMsg += "<div class=\"container\"><h3>Course Search</h3><form class=\"form-inline\" role=\"form\" name=\"coursef\" id=\"coursef\" action=\"courseF\" method=\"post\"><div class=\"form-group\"><label for=\"courseID\">Course ID:</label><input type=\"text\" class=\"form-control\" name=\"courseID\" id=\"courseID\" placeholder=\"Enter course ID\"><div class=\"form-group\"><button type=\"submit\" class=\"btn btn-default\" name=\"room\" id=\"room\">Search Classroom</button><button type=\"submit\" class=\"btn btn-default\" name=\"class\" id=\"class\">Search Class</button></div></form></div>";
		
		if (session.getAttribute("courseFResult") != null) {
			courseFMsg += session.getAttribute("courseFResult");
		}
		session.removeAttribute("courseFResult");
		
		request.setAttribute("courseFMsg", courseFMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String courseFResult = "";
		if (request.getParameter("room") != null) {
			if (request.getParameter("courseID") != null && !request.getParameter("courseID").equals("")) {
				if (request.getParameter("courseID") != null && !request.getParameter("courseID").equals("")) {
					List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.HCourse.courseId = " + request.getParameter("courseID"), HClass.class).getResultList();
					List<HClassroom> rooms = new ArrayList<HClassroom>();
					for (HClass cls : classes) {
						rooms.add(DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = " + cls.getHClassroom().getRoomId(), HClassroom.class).getSingleResult());
					}
					
					courseFResult += "<div class=\"container\"><h3>Classrooms</h3><table class=\"table\"><thead><tr><th>Building Name</th><th>Room #</th><th>Capacity</th><th>Available</th></tr></thead><tbody>";
					for (HClassroom room : rooms) {
						String avail = "";
						if (room.getAvailable() == 1) {
							avail = "yes";
						} else {
							avail = "no";
						}
						courseFResult += "<tr><td>" + room.getBldgName() + "</td><td>" + room.getRoomNo() + "</td><td>" + room.getMaxCapacity() + "</td><td>" + avail + "</td></tr>";
					}
					courseFResult += "</tbody></table></div>";
				} else {
					courseFResult += "<div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div>";
				}
			}
		}
		
		if (request.getParameter("class") != null) {
			if (request.getParameter("courseID") != null && !request.getParameter("courseID").equals("")) {
				List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.HCourse.courseId = " + request.getParameter("courseID"), HClass.class).getResultList();
				courseFResult += "<div class=\"container\"><h3>Classes</h3><table class=\"table\"><thead><tr><th>Code</th><th>Class Name</th><th>Credit #</th><th>Enrollment Cap</th></tr></thead><tbody>";
				for (HClass cls : classes) {
					courseFResult += "<tr><td>" + cls.getHCourse().getSubjectCode() + " " + cls.getHCourse().getCourseNo() + "</td><td>" + cls.getHCourse().getCourseName() + "</td><td>" + cls.getHCourse().getCredits() + "</td><td>" + cls.getEnrollCap() + "</td></tr>";
				}
				courseFResult += "</tbody></table></div>";
			} else {
				courseFResult += "<div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div>";
			}
		}
		
		session.setAttribute("courseFResult", courseFResult);
		response.sendRedirect("/HarrisonCollege/courseF");
	}

}
