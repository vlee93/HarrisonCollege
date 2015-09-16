

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
import model.HEnrollment;
import model.HStudent;
import customTools.DBUtil;

/**
 * Servlet implementation class sAddCourse
 */
@WebServlet("/sAddClass")
public class sAddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String addClassMsg;
	private String addClassError;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sAddClass() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		addClassError = "";
		addClassMsg = "";
		if (session.getAttribute("addClassError") != null) {
			addClassError = (String) session.getAttribute("addClassError");
		}
		session.removeAttribute("addClassError");
		addClassMsg += displayEnroll(session);
		request.setAttribute("addClassError", addClassError);
		request.setAttribute("addClassMsg", addClassMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		HStudent stu = (HStudent) session.getAttribute("userStudent");
		
		if (request.getParameter("enroll") != null) {
			List<HEnrollment> enrollment = DBUtil.createQuery("SELECT h FROM HEnrollment h WHERE h.HStudent.studentId = " + stu.getStudentId(), HEnrollment.class).getResultList();
			for (HEnrollment e : enrollment) {
				DBUtil.deleteFromDB(e);
			}
			
			boolean overcap = false;
			String[] classId = request.getParameterValues("enrollCheck[]");
			for (int i = 0; i < classId.length; i++) {
				HClass myclass = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.crnNumber = " + classId[i], HClass.class).getSingleResult();
				long currentEnrollLong = DBUtil.createQuery("SELECT COUNT(h) FROM HEnrollment h WHERE h.HClass.crnNumber = " + classId[i], Long.class).getSingleResult();
				int currentEnroll = (int) currentEnrollLong;
				if (currentEnroll < myclass.getEnrollCap()) {
					long newEnrollClass = Long.parseLong(classId[i]);
					HEnrollment newEnroll = new HEnrollment();
					newEnroll.setHClass((HClass) DBUtil.createQuery("SELECT h FROM HClass h WHERE h.crnNumber = " + newEnrollClass, HClass.class).getSingleResult());
					newEnroll.setHStudent(stu);
					newEnroll.setGrade(0);
					DBUtil.addToDB(newEnroll);					
				} else {
					String addClassError = "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> One or more of your classes is over capacity.</div></div>";
					session.setAttribute("addClassError", addClassError);
					overcap = true;
					break;
				}
			}
			
			if (overcap == true) {
				response.sendRedirect("/HarrisonCollege/sAddClass");
			} else {
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}
	
	private String displayEnroll(HttpSession session) {
		String addClassMsg = "";
		HStudent stu = (HStudent) session.getAttribute("userStudent");
		List<HEnrollment> enrollment = DBUtil.createQuery("SELECT h FROM HEnrollment h WHERE h.HStudent.studentId = " + stu.getStudentId(), HEnrollment.class).getResultList();
		List<HClass> classList = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.HCourse.available = 1", HClass.class).getResultList();
		
		addClassMsg += "<div class=\"container\"><form role=\"form\" name=\"enrollForm\" id=\"enrollForm\" action=\"sAddClass\" method=\"post\"><h3>Class Enrollment</h3><table class=\"table\"><thead><tr><th>Code</th><th>Class Name</th><th>Credit #</th><th>Current Enrollment</th><th>Enrollment Cap</th><th>Enroll</th></tr></thead><tbody>";
		
		for (int i = 0; i < classList.size(); i++) {
			long currentEnrollLong = DBUtil.createQuery("SELECT COUNT(h) FROM HEnrollment h WHERE h.HClass.crnNumber = " + classList.get(i).getCrnNumber(), Long.class).getSingleResult();
			addClassMsg += "<tr><td>" + classList.get(i).getHCourse().getSubjectCode() + " " + classList.get(i).getHCourse().getCourseNo() + "</td><td>" + classList.get(i).getHCourse().getCourseName() + "</td><td>" + classList.get(i).getHCourse().getCredits() + "</td><td>" + currentEnrollLong + "</td><td>" + classList.get(i).getEnrollCap() + "</td><td><div class=\"form-group\"><input type=\"checkbox\" name=\"enrollCheck[]\" value=\"" + classList.get(i).getCrnNumber() + "\"";
			boolean exist = false;
			for (HEnrollment e : enrollment) {
				if (e.getHClass().getCrnNumber() == classList.get(i).getCrnNumber()) {
					exist = true;
				}
			}
			if (exist == true) {
				addClassMsg += " checked";
			}
			addClassMsg += "></div></td></tr>";
		}
		
		addClassMsg += "<tr></tr></tbody></table><div class=\"form-group\"><button type=\"submit\" class=\"btn btn-default\" name=\"enroll\" id=\"enroll\">Submit</button></div></form></div>";
		return addClassMsg;		
	}

}
