

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HClass;
import model.HRevenue;
import customTools.DBUtil;


@WebServlet("/course_revenue")
public class course_revenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String courseRevMsg;

    public course_revenue() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		courseRevMsg = "";
		HttpSession session = request.getSession();

		courseRevMsg += "<div class=\"container\"><h3>Get Course Revenue</h3><form class=\"form-horizontal\" role=\"form\" name=\"courseRev\" id=\"courseRev\" action=\"course_revenue\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"courseid\">Course ID:</label><input type=\"text\" class=\"form-control\" name=\"courseid\" id=\"courseid\"></div>"
				+ "<div class=\"form-group\"><label for=\"semester\">Semester:</label><input type=\"text\" class=\"form-control\" name=\"semester\" id=\"semester\"></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"revCourse\" id=\"revCourse\">Submit</button></form>";
		
		if (session.getAttribute("courseMsg") != null) {
			courseRevMsg += session.getAttribute("courseMsg");
		}
		session.removeAttribute("courseMsg");
		
		request.setAttribute("courseRevMsg", courseRevMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String courseid = request.getParameter("courseid");
		String semester = request.getParameter("semester");
		String courseMsg = "";
		
		try {
			HRevenue credamt = DBUtil.createQuery("SELECT h FROM HRevenue h WHERE h.semester = '" + semester + "'", HRevenue.class).getSingleResult();
			double totalHour = 0.0;
			double totalRev = 0.0;
			List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.semester = '" + semester + "' AND h.HCourse.courseId = " + courseid, HClass.class).getResultList();
			for (int i = 0; i < classes.size(); i++) {
				long studno = DBUtil.createQuery("SELECT COUNT(h) FROM HEnrollment h WHERE h.HClass.crnNumber = " + classes.get(i).getCrnNumber(), Long.class).getSingleResult();
				totalHour += classes.get(i).getHCourse().getCredits() * studno;
			}
			
			totalRev = totalHour * credamt.getCreditcost();
			
			courseMsg += "<div class=\"container\"><div class=\"jumbotron\"><h3>Total revenue of course #" + courseid + " in " + semester + " is $" + formattedAmt(totalRev) + ".";
		} catch (Exception e) {
			e.printStackTrace();
			courseMsg += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Invalid Entry.</div></div>";
		}
			
		session.setAttribute("courseMsg", courseMsg);
		response.sendRedirect("/HarrisonCollege/course_revenue");
	}
	
	private String formattedAmt(double rev) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String revenue = formatter.format(rev);
		return revenue;
	}

}
