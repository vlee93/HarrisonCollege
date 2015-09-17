

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
import model.HCourse;
import model.HDepartment;
import model.HEnrollment;
import model.HStaff;
import model.HStudent;
import customTools.DBUtil;

@WebServlet("/iViewGrades")
public class iViewGrades extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public iViewGrades() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get instructor
		String qString = "select h from HStaff h where h.staffId = ?1";////for test
		TypedQuery<HStaff> query = DBUtil.createQuery(qString, HStaff.class);
		query.setParameter(1, 1);
		HStaff instructor = query.getSingleResult();
		
		//get courses
		qString = "select h from HCourse h";
		TypedQuery<HCourse> query2 = DBUtil.createQuery(qString, HCourse.class);
		List<HCourse> courses =  query2.getResultList();
        List<HClass> class_list = new ArrayList<HClass>();
        List<HCourse> Courses_info = new ArrayList<HCourse>();
        
        
        //get classes 
		for(HCourse course:courses){
			qString = "select h from HClass h where h.HStaff = ?1 and h.semester = ?2 and h.HCourse = ?3";
			TypedQuery<HClass> query3 = DBUtil.createQuery(qString, HClass.class);
			query3.setParameter(1, instructor).setParameter(2,request.getParameter("college_semester")).setParameter(3, course);
			List<HClass> classes =  query3.getResultList();
			for(HClass Class:classes){
				class_list.add(Class);
				Courses_info.add(course);
			}
	
		}
		
		HDepartment dep = new HDepartment();
		
		
		//find enrollments that belong to these classes	
		List<HEnrollment> enrollments = new ArrayList<HEnrollment>();
		List<HEnrollment> enrollment_list = new ArrayList<HEnrollment>();
		for(HClass Class:class_list){
			qString = "select h from HEnrollment h where h.HClass = ?1";
			TypedQuery<HEnrollment> query4 = DBUtil.createQuery(qString, HEnrollment.class);
			query4.setParameter(1, Class);
			enrollments = query4.getResultList();
			for(HEnrollment enrollment:enrollments){
				enrollment_list.add(enrollment);
			}
		}
		
		
		
		//find students that belong to these enrollments
		List<HStudent> students = new ArrayList<HStudent>();
		List<HStudent> students_list = new ArrayList<HStudent>();
		for(HEnrollment enrollment:enrollment_list){
			qString = "select h from HStudent h where h.studentId = ?1";
			TypedQuery<HStudent> query5 = DBUtil.createQuery(qString, HStudent.class);
			query5.setParameter(1, enrollment.getHStudent().getStudentId());
			students = query5.getResultList();
			for(HStudent student:students){
				students_list.add(student);
			}
		}
      
		System.out.println(Courses_info.size());
		
		HttpSession session = request.getSession();
		session.setAttribute("students", students_list);
		session.setAttribute("enrollments", enrollment_list);
		session.setAttribute("courses", Courses_info);
		
		getServletContext().getRequestDispatcher("/iViewGrades.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
