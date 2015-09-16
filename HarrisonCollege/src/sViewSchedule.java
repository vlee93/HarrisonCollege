

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.HClass;
import model.HEnrollment;
import model.HStudent;

/**
 * Servlet implementation class sViewSchedule
 */
@WebServlet("/sViewSchedule")
public class sViewSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String scheduleMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sViewSchedule() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		scheduleMsg = "";
		HttpSession session = request.getSession();
		HStudent stu = (HStudent) session.getAttribute("userStudent");
		List<HEnrollment> enroll = DBUtil.createQuery("SELECT h FROM HEnrollment h WHERE h.HClass.semester = 'Fall 2015' AND h.HStudent.studentId = " + stu.getStudentId(), HEnrollment.class).getResultList();
		scheduleMsg += "<div class=\"container\"><h3>Class Schedule</h3><table class=\"table\"><thead><tr><th>Code</th><th>Class Name</th><th>Instructor</th><th>Room #</th><th>Days of Week</th><th>Time</th></tr></thead><tbody>";
		for (int i = 0; i < enroll.size(); i++) {
			HClass myclass = enroll.get(i).getHClass();
			scheduleMsg += "<tr><td>" + myclass.getHCourse().getSubjectCode() + " " + myclass.getHCourse().getCourseNo() + "</td><td>" + myclass.getHCourse().getCourseName() + "</td><td>" + myclass.getHStaff().getStaffName() + "</td><td>" + myclass.getHClassroom().getRoomNo() + "</td><td>" + myclass.getClassDays() + "</td><td>" + myclass.getStartTime() + " - " + myclass.getEndTime() + "</td></tr>";
		}
		scheduleMsg += "</tbody></table></div>";
		request.setAttribute("scheduleMsg", scheduleMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

}
