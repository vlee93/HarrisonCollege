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
 * Servlet implementation class CourseSearch
 */
@WebServlet("/CourseSearch")
public class CourseSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//getting all departments
		List<HDepartment> departments = DBDepartment.getAvailableDepartments();
		request.setAttribute("departments", departments);
		//class start times are hard-coded
		
		getServletContext().getRequestDispatcher("/CourseFilter.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				String department = request.getParameter("departments");
				
				List<HCourse> courses = DBCourse.courseSearch(department);
				request.setAttribute("courses", courses);

				//getting all departments
				List<HDepartment> departments = DBDepartment.getAvailableDepartments();
				
				request.setAttribute("departments", departments);
				
				getServletContext().getRequestDispatcher("/CourseFilter.jsp").forward(request, response);
		
	}

}