

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HCourse;
import model.HDepartment;
import model.HMajor;
import database.DBCourse;
import database.DBDepartment;
import database.DBSubject;

/**
 * Servlet implementation class MajorSearch
 */
@WebServlet("/MajorSearch")
public class MajorSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajorSearch() {
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
				
				getServletContext().getRequestDispatcher("/MajorFilter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String department = request.getParameter("departments");

		List<HMajor> majors = DBCourse.majorSearch(department);
		request.setAttribute("majors", majors);

		//getting all departments
		List<HDepartment> departments = DBDepartment.getAvailableDepartments();
		
		request.setAttribute("departments", departments);
		
		getServletContext().getRequestDispatcher("/MajorFilter.jsp").forward(request, response);
		// TODO Auto-generated method stub
	}

}
