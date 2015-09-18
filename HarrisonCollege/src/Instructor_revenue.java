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


@WebServlet("/Instructor_revenue")
public class Instructor_revenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String instRevMsg;

    public Instructor_revenue() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		instRevMsg = "";
		HttpSession session = request.getSession();

		instRevMsg += "<div class=\"container\"><h3>Get Instructor Revenue</h3><form class=\"form-horizontal\" role=\"form\" name=\"instRev\" id=\"instRev\" action=\"Instructor_revenue\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"staffid\">Instructor ID:</label><input type=\"text\" class=\"form-control\" name=\"staffid\" id=\"staffid\"></div>"
				+ "<div class=\"form-group\"><label for=\"semester\">Semester:</label><input type=\"text\" class=\"form-control\" name=\"semester\" id=\"semester\"></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"revInst\" id=\"revInst\">Submit</button></form>";
		
		if (session.getAttribute("instMsg") != null) {
			instRevMsg += session.getAttribute("instMsg");
		}
		session.removeAttribute("instMsg");
		
		request.setAttribute("instRevMsg", instRevMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String staffid = request.getParameter("staffid");
		String semester = request.getParameter("semester");
		String instMsg = "";
		
		try {
			HRevenue credamt = DBUtil.createQuery("SELECT h FROM HRevenue h WHERE h.semester = '" + semester + "'", HRevenue.class).getSingleResult();
			double totalHour = 0.0;
			double totalRev = 0.0;
			List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.semester = '" + semester + "' AND h.HStaff.staffId = " + staffid, HClass.class).getResultList();
			for (int i = 0; i < classes.size(); i++) {
				long studno = DBUtil.createQuery("SELECT COUNT(h) FROM HEnrollment h WHERE h.HClass.crnNumber = " + classes.get(i).getCrnNumber(), Long.class).getSingleResult();
				totalHour += classes.get(i).getHCourse().getCredits() * studno;
			}
			
			totalRev = totalHour * credamt.getCreditcost();
			
			instMsg += "<div class=\"container\"><div class=\"jumbotron\"><h3>Total revenue of Instructor #" + staffid + " in " + semester + " is $" + formattedAmt(totalRev) + ".";
		} catch (Exception e) {
			e.printStackTrace();
			instMsg += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Invalid Entry.</div></div>";
		}
			
		session.setAttribute("instMsg", instMsg);
		response.sendRedirect("/HarrisonCollege/Instructor_revenue");
	}
	
	private String formattedAmt(double rev) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String revenue = formatter.format(rev);
		return revenue;
	}

}
