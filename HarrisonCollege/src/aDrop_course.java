
import java.io.IOException;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.HClass;
import model.HEnrollment;
import model.HStudent;

@WebServlet("/aDrop_course")
public class aDrop_course extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public aDrop_course() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// find the student
		String qString = "SELECT h FROM HStudent h where h.studentId = ?1";
		TypedQuery<HStudent> query = DBUtil.createQuery(qString, HStudent.class);
		query.setParameter(1, Long.parseLong(request.getParameter("student_id")));
		String alert = "";
		
		try {
			HStudent student = query.getSingleResult();
	
			// find the course
			qString = "SELECT h FROM HClass h where h.crnNumber = ?1";
			TypedQuery<HClass> query2 = DBUtil.createQuery(qString, HClass.class);
			query2.setParameter(1,
					Long.parseLong(request.getParameter("crn_number")));
			HClass Class = query2.getSingleResult();
	
			// drop the course from the student
			qString = "SELECT h FROM HEnrollment h where h.HStudent = ?1 and h.HClass = ?2";
			TypedQuery<HEnrollment> query3 = DBUtil.createQuery(qString,
					HEnrollment.class);
			query3.setParameter(1, student).setParameter(2, Class);
			HEnrollment enrollment = new HEnrollment();

			try {
				enrollment = query3.getSingleResult();
			} catch (Exception e) {
				enrollment = null;
			}
	
			qString = "SELECT h FROM HStudent h where h.studentId = ?1 ";
			TypedQuery<HStudent> query4 = DBUtil.createQuery(qString,
					HStudent.class);
			query4.setParameter(1,
					Long.parseLong(request.getParameter("student_id")));
			String name = query4.getSingleResult().getStudentName();
			// Check if the course is already deleted
			if (enrollment == null)
				alert = "<label style=\"color:red\">Course already dropped from "
						+ name + "'s schedule </label>";
			else {
				DBUtil.deleteFromDB(enrollment);
				alert = "<label style=\"color:orange\">Course dropped successfully </label>";
			}
		} catch (Exception e) {
			alert += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div></div>";
		}

		request.setAttribute("alert", alert);
		getServletContext().getRequestDispatcher("/enrollment.jsp").forward(
				request, response);

	}

}
