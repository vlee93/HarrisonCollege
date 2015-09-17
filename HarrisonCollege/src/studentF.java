

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
 * Servlet implementation class studentF
 */
@WebServlet("/studentF")
public class studentF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String studFMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentF() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		studFMsg = "";
		HttpSession session = request.getSession();
		studFMsg += "<div class=\"container\"><form class=\"form-inline\" role=\"form\" name=\"studf\" id=\"studf\" action=\"studentF\" method=\"post\"><div class=\"form-group\"><label for=\"stuID\">Student ID:</label><input type=\"text\" class=\"form-control\" name=\"stuID\" id=\"stuID\" placeholder=\"Enter student ID\"><div class=\"form-group\"><button type=\"submit\" class=\"btn btn-default\" name=\"room\" id=\"room\">Search Classroom</button><button type=\"submit\" class=\"btn btn-default\" name=\"class\" id=\"class\">Search Class</button></div></form></div>";
		
		if (session.getAttribute("studFResult") != null) {
			studFMsg += session.getAttribute("studFResult");
		}
		session.removeAttribute("studFResult");
		
		request.setAttribute("studFMsg", studFMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String studFResult = "";
		if (request.getParameter("room") != null) {
			if (request.getParameter("stuID") != null && !request.getParameter("stuID").equals("")) {
				List<HEnrollment> enroll = DBUtil.createQuery("SELECT h FROM HEnrollment h WHERE h.HStudent.studentId = " + request.getParameter("stuID"), HEnrollment.class).getResultList();
				List<HClass> classes = new ArrayList<HClass>();
				for (HEnrollment enr : enroll) {
					classes.add(DBUtil.createQuery("SELECT h FROM HClass h WHERE h.crnNumber = " + enr.getHClass().getCrnNumber(), HClass.class).getSingleResult());
				}
				List<HClassroom> classroom = new ArrayList<HClassroom>();
				for (HClass cls : classes) {
					classroom.add(DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = " + cls.getHClassroom().getRoomId(), HClassroom.class).getSingleResult());
				}
				
				studFResult += "<div class=\"container\"><h3>Classrooms</h3><table class=\"table\"><thead><tr><th>Building Name</th><th>Room #</th><th>Capacity</th><th>Available</th></tr></thead><tbody>";
				for (HClassroom room : classroom) {
					String avail = "";
					if (room.getAvailable() == 1) {
						avail = "yes";
					} else {
						avail = "no";
					}
					studFResult += "<tr><td>" + room.getBldgName() + "</td><td>" + room.getRoomNo() + "</td><td>" + room.getMaxCapacity() + "</td><td>" + avail + "</td></tr>";
				}
				studFResult += "</tbody></table></div>";
			} else {
				studFResult += "<div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div>";
			}
		}
		
		if (request.getParameter("class") != null) {
			if (request.getParameter("stuID") != null && !request.getParameter("stuID").equals("")) {
				List<HEnrollment> enroll = DBUtil.createQuery("SELECT h FROM HEnrollment h WHERE h.HStudent.studentId = " + request.getParameter("stuID"), HEnrollment.class).getResultList();
				List<HClass> classes = new ArrayList<HClass>();
				for (HEnrollment enr : enroll) {
					classes.add(DBUtil.createQuery("SELECT h FROM HClass h WHERE h.crnNumber = " + enr.getHClass().getCrnNumber(), HClass.class).getSingleResult());
				}
				
				studFResult += "<div class=\"container\"><h3>Classes</h3><table class=\"table\"><thead><tr><th>Code</th><th>Class Name</th><th>Credit #</th><th>Enrollment Cap</th></tr></thead><tbody>";
				for (HClass cls : classes) {
					studFResult += "<tr><td>" + cls.getHCourse().getSubjectCode() + " " + cls.getHCourse().getCourseNo() + "</td><td>" + cls.getHCourse().getCourseName() + "</td><td>" + cls.getHCourse().getCredits() + "</td><td>" + cls.getEnrollCap() + "</td></tr>";
				}
				studFResult += "</tbody></table></div>";
			} else {
				studFResult += "<div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div>";
			}
		}
		
		session.setAttribute("studFResult", studFResult);
		response.sendRedirect("/HarrisonCollege/studentF");
	}

}
