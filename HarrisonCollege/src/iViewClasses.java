

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
import model.HStaff;
import customTools.DBUtil;

/**
 * Servlet implementation class iViewClasses
 */
@WebServlet("/iViewClasses")
public class iViewClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public iViewClasses() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get instructor
		String qString = "select h from HStaff h where h.staffId = :staff";////for test
		TypedQuery<HStaff> query = DBUtil.createQuery(qString, HStaff.class);
		query.setParameter("staff", 1);
		HStaff instructor = query.getSingleResult();
		
		qString = "select h from HCourse h";
		TypedQuery<HCourse> query4 = DBUtil.createQuery(qString, HCourse.class);
		List<HCourse> courses =  query4.getResultList();
        List<HClass> class_list = new ArrayList<HClass>();
        List<HCourse> Courses_info = new ArrayList<HCourse>();
        
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
//		System.out.println(class_list.size());
		

		//establish semester options, set course codes and classes to Classes.jsp
		qString = "select distinct h.semester from HClass h";
		TypedQuery<HClass> query3 = DBUtil.createQuery(qString, HClass.class);
		List<HClass> semesters = query3.getResultList();

		HttpSession session = request.getSession();
		session.setAttribute("classes", class_list);
		session.setAttribute("Courses_info", Courses_info);
		session.setAttribute("semesters", semesters);
		

		getServletContext().getRequestDispatcher("/iClasses.jsp").forward(request, response);
	}

}
