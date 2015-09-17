

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.HEnrollment;
import model.HStudent;

@WebServlet("/iAddGrade")
public class iAddGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public iAddGrade() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] grades = request.getParameterValues("sGrade");
		String [] enr_id = request.getParameterValues("enrollment_id");
		//find enrollments
		String qString = "select h from HEnrollment h where h.enrId = ?1";
		TypedQuery<HEnrollment> query = DBUtil.createQuery(qString, HEnrollment.class);
		HEnrollment enrollment = new HEnrollment();
		List<HEnrollment> enrollments = new ArrayList<HEnrollment>();
		
		//update grades of these enrollment
		int index = 0;
		for(String grade:grades){
			query.setParameter(1, Long.parseLong(enr_id[index]));
			enrollment = query.getSingleResult();
			enrollment.setGrade(Double.parseDouble(grade));
			DBUtil.updateDB(enrollment);
			enrollments.add(enrollment);
			index++;
		}
		
		String alert = "<label style=\"color:green;margin-left:50px;\"> Grades updated successfully</label>";
		request.setAttribute("alert", alert);
		
		HttpSession session = request.getSession();
		session.setAttribute("enrollments", enrollments);
		getServletContext().getRequestDispatcher("/iGrades.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

}
