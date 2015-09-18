

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
import model.HStudent;
import customTools.DBUtil;

/**
 * Servlet implementation class instructorF
 */
@WebServlet("/instructorF")
public class instructorF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String insFMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public instructorF() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		insFMsg = "";
		HttpSession session = request.getSession();
		insFMsg += "<div class=\"container\"><h3>Instructor Search</h3><form class=\"form-inline\" role=\"form\" name=\"insf\" id=\"insf\" action=\"instructorF\" method=\"post\"><div class=\"form-group\"><label for=\"staID\">Staff ID:</label><input type=\"text\" class=\"form-control\" name=\"staID\" id=\"staID\" placeholder=\"Enter staff ID\"><div class=\"form-group\"><button type=\"submit\" class=\"btn btn-default\" name=\"room\" id=\"room\">Search Classroom</button><button type=\"submit\" class=\"btn btn-default\" name=\"class\" id=\"class\">Search Class</button><button type=\"submit\" class=\"btn btn-default\" name=\"student\" id=\"student\">Search Student</button></div></form></div>";
		
		if (session.getAttribute("insFResult") != null) {
			insFMsg += session.getAttribute("insFResult");
		}
		session.removeAttribute("insFResult");
		
		request.setAttribute("insFMsg", insFMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String insFResult = "";
		if (request.getParameter("room") != null) {
			if (request.getParameter("staID") != null && !request.getParameter("staID").equals("")) {
				List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.HStaff.staffId = " + request.getParameter("staID"), HClass.class).getResultList();
				List<HClassroom> classroom = new ArrayList<HClassroom>();
				for (HClass cls : classes) {
					classroom.add(DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = " + cls.getHClassroom().getRoomId(), HClassroom.class).getSingleResult());
				}
				
				insFResult += "<div class=\"container\"><h3>Classrooms</h3><table class=\"table\"><thead><tr><th>Building Name</th><th>Room #</th><th>Capacity</th><th>Available</th></tr></thead><tbody>";
				for (HClassroom room : classroom) {
					String avail = "";
					if (room.getAvailable() == 1) {
						avail = "yes";
					} else {
						avail = "no";
					}
					insFResult += "<tr><td>" + room.getBldgName() + "</td><td>" + room.getRoomNo() + "</td><td>" + room.getMaxCapacity() + "</td><td>" + avail + "</td></tr>";
				}
				insFResult += "</tbody></table></div>";
			} else {
				insFResult += "<div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div>";
			}
		}
		
		if (request.getParameter("class") != null) {
			if (request.getParameter("staID") != null && !request.getParameter("staID").equals("")) {
				List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.HStaff.staffId = " + request.getParameter("staID"), HClass.class).getResultList();
				insFResult += "<div class=\"container\"><h3>Classes</h3><table class=\"table\"><thead><tr><th>Code</th><th>Class Name</th><th>Credit #</th><th>Enrollment Cap</th></tr></thead><tbody>";
				for (HClass cls : classes) {
					insFResult += "<tr><td>" + cls.getHCourse().getSubjectCode() + " " + cls.getHCourse().getCourseNo() + "</td><td>" + cls.getHCourse().getCourseName() + "</td><td>" + cls.getHCourse().getCredits() + "</td><td>" + cls.getEnrollCap() + "</td></tr>";
				}
				insFResult += "</tbody></table></div>";
			} else {
				insFResult += "<div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div>";
			}
		}
		
		if (request.getParameter("student") != null) {
			if (request.getParameter("staID") != null && !request.getParameter("staID").equals("")) {
				List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.HStaff.staffId = " + request.getParameter("staID"), HClass.class).getResultList();
				List<HEnrollment> enroll = new ArrayList<HEnrollment>();
				for (HClass cls : classes) {
					enroll.add(DBUtil.createQuery("SELECT h FROM HEnrollment h WHERE h.HClass.crnNumber = " + cls.getCrnNumber(), HEnrollment.class).getSingleResult());
				}
				
				List<HStudent> student = new ArrayList<HStudent>();
				for (HEnrollment en : enroll) {
					student.add(DBUtil.createQuery("SELECT h FROM HStudent h WHERE h.studentId = " + en.getHStudent().getStudentId(), HStudent.class).getSingleResult());
				}
				
				insFResult += "<div class=\"container\"><h3>Students</h3><table class=\"table\"><thead><tr><th>Student ID</th><th>Name</th><th>Major</th><th>Year of Entry</th></tr></thead><tbody>";
				for (HStudent stu : student) {
					insFResult += "<tr><td>" + stu.getStudentId() + "</td><td>" + stu.getStudentName() + "</td><td>" + stu.getHMajor().getMajorName() + "</td><td>" + stu.getStartYear() + "</td></tr>";
				}
				insFResult += "</tbody></table></div>";
			} else {
				insFResult += "<div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div>";
			}
		}
		
		session.setAttribute("insFResult", insFResult);
		response.sendRedirect("/HarrisonCollege/instructorF");
	}

}
