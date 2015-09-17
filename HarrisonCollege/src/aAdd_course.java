

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


@WebServlet("/aAdd_course")
public class aAdd_course extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public aAdd_course() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//find the student
		System.err.println("TEST1");
		String qString = "select h from HStudent h where h.studentId = ?1";
		TypedQuery<HStudent> query = DBUtil.createQuery(qString, HStudent.class);
		query.setParameter(1, Long.parseLong(request.getParameter("student_id")));
		HStudent student = query.getSingleResult();
		
		
		//find the course
		qString = "select h from HClass h where h.crnNumber = ?1";
		TypedQuery<HClass> query2 = DBUtil.createQuery(qString, HClass.class);
		query2.setParameter(1, Long.parseLong(request.getParameter("crn_number")));
		HClass Class = query2.getSingleResult();
		
		//add the course to the student
		HEnrollment enrollment = new HEnrollment();
		enrollment.setHClass(Class);
		enrollment.setHStudent(student);
		
		qString = "SELECT h FROM HEnrollment h where h.HStudent = ?1 and h.HClass = ?2";
		TypedQuery<HEnrollment> query3 = DBUtil.createQuery(qString, HEnrollment.class);
		query3.setParameter(1, student).setParameter(2, Class);
		
		//Check if the class is already enrolled to the student
		HEnrollment enrollment_in_DB = new HEnrollment();
		try{
			enrollment_in_DB = query3.getSingleResult();
		}catch(Exception e){
			enrollment_in_DB = null;
		}
		

		qString = "SELECT h FROM HStudent h where h.studentId = ?1 ";
		TypedQuery<HStudent> query4 = DBUtil.createQuery(qString, HStudent.class);
		query4.setParameter(1, Long.parseLong(request.getParameter("student_id")));
		String name = query4.getSingleResult().getStudentName();
		//Check if the course is already deleted
		
		String alert = "";
		if(enrollment_in_DB != null)
			alert = "<label style=\"color:green\">Course already added to " + name + "'s schedule </label>";
		else{
			DBUtil.addToDB(enrollment);
		    alert = "<label style=\"color:blue\">Course added successfully </label>";
		}
	
		request.setAttribute("alert", alert);
		getServletContext().getRequestDispatcher("/enrollment.jsp").forward(request, response);
	}

}
