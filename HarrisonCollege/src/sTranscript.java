

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

import customTools.DBUtil;
import model.HEnrollment;
import model.HStudent;

/**
 * Servlet implementation class sTranscript
 */
@WebServlet("/sTranscript")
public class sTranscript extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String transMsg;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sTranscript() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		transMsg = "";
		HStudent stu = (HStudent) session.getAttribute("userStudent");
		transMsg += "<div class=\"container\"><div class=\"jumbotron\"><h3>" + stu.getStudentName() + "</h3><h5>Student ID: " + stu.getStudentId() + "</h5><h5>Start Year: " + stu.getStartYear() + "</h5><h5>Major: " + stu.getHMajor().getMajorName() + "</h5></div></div>";
		List<HEnrollment> enroll = DBUtil.createQuery("SELECT h FROM HEnrollment h WHERE h.HStudent.studentId = " + stu.getStudentId(), HEnrollment.class).getResultList();
		transMsg += "<br /><div class=\"container\"><h3>Unofficial Transcript</h3><table class=\"table\"><thead><tr><th>Code</th><th>Class Name</th><th>Instructor</th><th>Credit #</th><th>Grade</th><th>Points</th></tr></thead><tbody>";
		double totalCredits = 0.0;
		double totalPoints = 0.0;
		double gpa = 0.0;
		for (int i = 0; i < enroll.size(); i++) {
			double points = enroll.get(i).getGrade() * enroll.get(i).getHClass().getHCourse().getCredits();
			totalCredits += enroll.get(i).getHClass().getHCourse().getCredits();
			totalPoints += points;
			transMsg += "<tr><td>" + enroll.get(i).getHClass().getHCourse().getSubjectCode() + " " + enroll.get(i).getHClass().getHCourse().getCourseNo() + "</td><td>" + enroll.get(i).getHClass().getHCourse().getCourseName() + "</td><td>" + enroll.get(i).getHClass().getHStaff().getStaffName() + "</td><td>" + enroll.get(i).getHClass().getHCourse().getCredits() + "</td><td>" + enroll.get(i).getGrade()+ "</td><td>" + points + "</td></tr>";
		}
		gpa = totalPoints / totalCredits;
		transMsg += "<tr><th colspan=\"6\" style=\"text-align:right\">Total Credits: " + totalCredits + "</th></tr><tr><th colspan=\"6\" style=\"text-align:right\">Total Points: " + totalPoints + "</th></tr><tr><th colspan=\"6\" style=\"text-align:right\">GPA: " + formattedGpa(gpa) + "</th></tr></tbody></table>";
		
		transMsg += "<br /><button type=\"button\" class=\"btn btn-success\" onclick=\"alert('Your transcript has been ordered!')\">Order Transcript ($5)</button></div>";
		
		request.setAttribute("transMsg", transMsg);
		getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	private String formattedGpa (double gpa) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		String GPA = formatter.format(gpa);
		return GPA;
	}

}
