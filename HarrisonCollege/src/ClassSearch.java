import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import database.*;

/**
 * Servlet implementation class ClassSearch
 */
@WebServlet("/ClassSearch")
public class ClassSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//getting all semesters
		List<String> semesters = DBSemester.getAllSemesters();
		
		//getting all available subjects
		List<HCourse> subjects = DBSubject.getAllAvailableSubjects();
				
		//getting all instructors
		List<String> instructors = DBInstructor.getAllInstructors();
		
		//getting all departments
		List<HDepartment> departments = DBDepartment.getAvailableDepartments();
		
		//class start times are hard-coded
		
		request.setAttribute("semesters", semesters);
		request.setAttribute("subjects", subjects);
		request.setAttribute("instructors", instructors);
		request.setAttribute("departments", departments);
		//class start times are hard-coded
		
		getServletContext().getRequestDispatcher("/ClassFilter.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String semester = request.getParameter("semesters");
		String subject = request.getParameter("subjects");
		String instructor = request.getParameter("instructors");
		String department = request.getParameter("departments");
		String time = request.getParameter("time");
		
		List<HClass> classes = DBClass.classSearch(semester, subject, instructor, department, time);
		request.setAttribute("classes", classes);
		
		//getting all semesters
		List<String> semesters = DBSemester.getAllSemesters();
		
		//getting all available subjects
		List<HCourse> subjects = DBSubject.getAllAvailableSubjects();
				
		//getting all instructors
		List<String> instructors = DBInstructor.getAllInstructors();
		
		//getting all departments
		List<HDepartment> departments = DBDepartment.getAvailableDepartments();
		
		//class start times are hard-coded
		
		request.setAttribute("semesters", semesters);
		request.setAttribute("subjects", subjects);
		request.setAttribute("instructors", instructors);
		request.setAttribute("departments", departments);
		//class start times are hard-coded
		
		getServletContext().getRequestDispatcher("/ClassFilter.jsp").forward(request, response);
	}

}