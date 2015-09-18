

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HClass;
import model.HRevenue;
import customTools.DBUtil;


@WebServlet("/class_revenue")
public class class_revenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String classRevMsg;

    public class_revenue() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		classRevMsg = "";
		HttpSession session = request.getSession();

		classRevMsg += "<div class=\"container\"><h3>Get Class Revenue</h3><form class=\"form-horizontal\" role=\"form\" name=\"classRev\" id=\"classRev\" action=\"class_revenue\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"classid\">CRN #:</label><input type=\"text\" class=\"form-control\" name=\"classid\" id=\"classid\"></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"revCls\" id=\"revCls\">Submit</button></form>";
		
		if (session.getAttribute("classMsg") != null) {
			classRevMsg += session.getAttribute("classMsg");
		}
		session.removeAttribute("classMsg");
		
		request.setAttribute("classRevMsg", classRevMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String classid = request.getParameter("classid");
		String classMsg = "";
		
		try {
			double totalHour = 0.0;
			double totalRev = 0.0;
			HClass classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.crnNumber = " + classid, HClass.class).getSingleResult();
			long studno = DBUtil.createQuery("SELECT COUNT(h) FROM HEnrollment h WHERE h.HClass.crnNumber = " + classes.getCrnNumber(), Long.class).getSingleResult();
			totalHour += classes.getHCourse().getCredits() * studno;
			HRevenue credamt = DBUtil.createQuery("SELECT h FROM HRevenue h WHERE h.semester = '" + classes.getSemester() + "'", HRevenue.class).getSingleResult();
			
			totalRev = totalHour * credamt.getCreditcost();
			
			classMsg += "<div class=\"container\"><div class=\"jumbotron\"><h3>Total revenue of class #" + classid + " is $" + formattedAmt(totalRev) + ".";
		} catch (Exception e) {
			e.printStackTrace();
			classMsg += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Invalid Entry.</div></div>";
		}
			
		session.setAttribute("classMsg", classMsg);
		response.sendRedirect("/HarrisonCollege/class_revenue");
	}
	
	private String formattedAmt(double rev) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String revenue = formatter.format(rev);
		return revenue;
	}

}
