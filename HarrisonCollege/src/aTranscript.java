import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

/**
 * Servlet implementation class sTranscript
 */
@WebServlet("/aTranscript")
public class aTranscript extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String transMsg;
       
    public aTranscript() {
        super();
    }
    
    public String CalGPA(double grade){
    	String GradeGPA = "";
    	
    	if (grade >= 90) {
    		GradeGPA += "A";
    		GradeGPA += "4";
    	} else if(grade >= 80) {
    		GradeGPA += "B";
    		GradeGPA += "3";
    	} else if(grade >= 70) {
    		GradeGPA += "C";
    		GradeGPA += "2";
    	} else if(grade >= 60) {
    		GradeGPA += "D";
    		GradeGPA += "1";
    	} else if(grade >= 0) {
    		GradeGPA += "F";
    		GradeGPA += "0";
    	} else if(grade == -1) {
    		GradeGPA += "I";
    		GradeGPA += "0";
    	} else if(grade == -2) {
    		GradeGPA += "W";
    		GradeGPA += "0";
    	}
    	return GradeGPA;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		transMsg = "";
		
		String qString = "select h from HStudent H where h.studentId = ?1";
		TypedQuery<HStudent> query = DBUtil.createQuery(qString, HStudent.class);
	    
		query.setParameter(1, Long.parseLong(request.getParameter("student_id")));
		
		try {
			HStudent stu = query.getSingleResult();
			transMsg += "<div class=\"container\"><div class=\"jumbotron\"><h3>" + stu.getStudentName() + "</h3><h5>Student ID: " + stu.getStudentId() + "</h5><h5>Start Year: " + stu.getStartYear() + "</h5><h5>Major: " + stu.getHMajor().getMajorName() + "</h5></div></div>";
			List<HEnrollment> enroll = DBUtil.createQuery("SELECT h from HEnrollment h WHERE h.HStudent.studentId = " + stu.getStudentId(), HEnrollment.class).getResultList();
			transMsg += "<br /><div class=\"container\"><h3>Unofficial Transcript</h3><table class=\"table\"><thead><tr><th>Code</th><th>Class Name</th><th>Instructor</th><th>Credit #</th><th>Grade</th><th>Points</th></tr></thead><tbody>";
			
			double totalCredits = 0.0;
			double totalPoints = 0.0;
			double gpa = 0.0;
			double course_gpa;
			String grade_letter;
			
			for (int i = 0; i < enroll.size(); i++) {
				course_gpa = Double.parseDouble(CalGPA(enroll.get(i).getGrade()).substring(1, 2));
				grade_letter = CalGPA(enroll.get(i).getGrade()).substring(0, 1);
				double points = course_gpa * enroll.get(i).getHClass().getHCourse().getCredits();
				totalCredits += enroll.get(i).getHClass().getHCourse().getCredits();
				totalPoints += points;
				transMsg += "<tr><td>" + enroll.get(i).getHClass().getHCourse().getSubjectCode() + " " + enroll.get(i).getHClass().getHCourse().getCourseNo() + "</td><td>" + enroll.get(i).getHClass().getHCourse().getCourseName() + "</td><td>" + enroll.get(i).getHClass().getHStaff().getStaffName() + "</td><td>" + enroll.get(i).getHClass().getHCourse().getCredits() + "</td><td>" + grade_letter + "</td><td>" + points + "</td></tr>";
			}
			gpa = totalPoints / totalCredits;
			transMsg += "<tr><th colspan=\"6\" style=\"text-align:right\">Total Credits: " + totalCredits + "</th></tr><tr><th colspan=\"6\" style=\"text-align:right\">Total Points: " + totalPoints + "</th></tr><tr><th colspan=\"6\" style=\"text-align:right\">GPA: " + formattedGpa(gpa) + "</th></tr></tbody></table>";
		} catch (Exception e) {
			transMsg += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Invalid entry.</div></div>";
		}
			
		request.setAttribute("transMsg", transMsg);
		getServletContext().getRequestDispatcher("/aTranscript.jsp").forward(request, response);
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