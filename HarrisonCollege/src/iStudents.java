

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HClass;
import model.HEnrollment;
import model.HMajor;
import model.HStaff;
import model.HStudent;
import customTools.DBUtil;

@WebServlet("/iStudents")
public class iStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public iStudents() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get instructor
		String qString = "select h from HStaff h where h.staffId = ?1";////for test
		TypedQuery<HStaff> query = DBUtil.createQuery(qString, HStaff.class);
		query.setParameter(1, 1);
		HStaff instructor = query.getSingleResult();
		
		//find classes that belongs to that instructor
		qString = "select h from HClass h where h.HStaff = ?1";
		TypedQuery<HClass> query2 = DBUtil.createQuery(qString, HClass.class);
		query2.setParameter(1, instructor);
		List<HClass> classes = query2.getResultList();
		
		//find enrollments that belong to these classes
		
		List<HEnrollment> enrollments = new ArrayList<HEnrollment>();
		List<HEnrollment> enrollment_list = new ArrayList<HEnrollment>();
		for(HClass Class:classes){
			qString = "select h from HEnrollment h where h.HClass = ?1";
			TypedQuery<HEnrollment> query3 = DBUtil.createQuery(qString, HEnrollment.class);
			query3.setParameter(1, Class);
			enrollments = query3.getResultList();
			for(HEnrollment enrollment:enrollments){
				enrollment_list.add(enrollment);
			}
		}
		
		
		
		//find students that belong to these enrollments
		List<HStudent> students = new ArrayList<HStudent>();
		List<HStudent> students_list = new ArrayList<HStudent>();
		for(HEnrollment enrollment:enrollment_list){
			qString = "select h from HStudent h where h.studentId = ?1";
			TypedQuery<HStudent> query4 = DBUtil.createQuery(qString, HStudent.class);
			query4.setParameter(1, enrollment.getHStudent().getStudentId());
			students = query4.getResultList();
			for(HStudent student:students){
				students_list.add(student);
			}
		}

		//find students' majors.
		List<HMajor> majors = new ArrayList<HMajor>();
		List<HMajor> majors_list = new ArrayList<HMajor>();
		for(HStudent student:students_list){
			qString = "select h from HMajor h where h.majorId = ?1";
			TypedQuery<HMajor> query5 = DBUtil.createQuery(qString, HMajor.class);
			query5.setParameter(1, student.getHMajor().getMajorId());
			majors = query5.getResultList();
			for(HMajor major:majors){
				majors_list.add(major);
			}
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("students", students_list);
		session.setAttribute("majors", majors_list);
		
		getServletContext().getRequestDispatcher("/iStudents.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
