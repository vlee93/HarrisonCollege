

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

import model.*;
import customTools.DBUtil;


@WebServlet("/Department_Revenues")
public class Department_Revenues extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String deptRevMsg;
       

    public Department_Revenues() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deptRevMsg = "";
		HttpSession session = request.getSession();

		deptRevMsg += "<div class=\"container\"><h3>Get Department Revenue</h3><form class=\"form-horizontal\" role=\"form\" name=\"deptRev\" id=\"deptRev\" action=\"Department_Revenues\" method=\"post\">"
				+ "<div class=\"form-group\"><label for=\"deptname\">Department Name:</label><input type=\"text\" class=\"form-control\" name=\"deptname\" id=\"deptname\"></div>"
				+ "<div class=\"form-group\"><label for=\"semester\">Semester:</label><input type=\"text\" class=\"form-control\" name=\"semester\" id=\"semester\"></div>"
				+ "<button type=\"submit\" class=\"btn btn-default\" name=\"revD\" id=\"revD\">Submit</button></form>";
		
		if (session.getAttribute("deptMsg") != null) {
			deptRevMsg += session.getAttribute("deptMsg");
		}
		session.removeAttribute("deptMsg");
		
		request.setAttribute("deptRevMsg", deptRevMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);	     
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String deptname = request.getParameter("deptname");
		String semester = request.getParameter("semester");
		String deptMsg = "";
		
		try {
			HRevenue credamt = DBUtil.createQuery("SELECT h FROM HRevenue h WHERE h.semester = '" + semester + "'", HRevenue.class).getSingleResult();
			double totalHour = 0.0;
			double totalRev = 0.0;
			List<HClass> classes = DBUtil.createQuery("SELECT h FROM HClass h WHERE h.semester = '" + semester + "' AND h.HCourse.HDepartment.deptName = '" + deptname + "'", HClass.class).getResultList();
			for (int i = 0; i < classes.size(); i++) {
				long studno = DBUtil.createQuery("SELECT COUNT(h) FROM HEnrollment h WHERE h.HClass.crnNumber = " + classes.get(i).getCrnNumber(), Long.class).getSingleResult();
				totalHour += classes.get(i).getHCourse().getCredits() * studno;
			}
			
			totalRev = totalHour * credamt.getCreditcost();
			
			deptMsg += "<div class=\"container\"><div class=\"jumbotron\"><h3>Total revenue of " + deptname + " department in " + semester + " is $" + formattedAmt(totalRev) + ".";
		} catch (Exception e) {
			e.printStackTrace();
			deptMsg += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Invalid Entry.</div></div>";
		}
			
		session.setAttribute("deptMsg", deptMsg);
		response.sendRedirect("/HarrisonCollege/Department_Revenues");
	}
	
	private String formattedAmt(double rev) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String revenue = formatter.format(rev);
		return revenue;
	}

}
