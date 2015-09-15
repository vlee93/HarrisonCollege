

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HMajor;
import model.HStudent;
import customTools.DBUtil;

/**
 * Servlet implementation class studentLoginServ
 */
@WebServlet("/studentLoginServ")
public class studentLoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String error;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentLoginServ() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		error = "";
		if (request.getParameter("createAcc") != null) {
			HStudent newStu = new HStudent();
			String newName = (String) request.getParameter("stuName");
			String newPwd = (String) request.getParameter("stuPwd");
			String newMajorName = (String) request.getParameter("major");
			newStu.setStudentName(newName);
			newStu.setUserPassword(newPwd);
			
			try {
				HMajor newMajor = DBUtil.createQuery("SELECT h FROM HMajor h WHERE h.majorName = '" + newMajorName + "' AND h.available = 1", HMajor.class).getSingleResult();
				newStu.setHMajor(newMajor);
			} catch (Exception e) {
				e.printStackTrace();
				error += "<div class=\"alert alert-danger\"><strong>Error!</strong> Major does not exist.</div>";
				request.setAttribute("error", error);
				getServletContext().getRequestDispatcher("/studentLogin.jsp").forward(request, response);
			}
			int newYear = Integer.parseInt((String) request.getParameter("year"));
			newStu.setStartYear(newYear);
			DBUtil.addToDB(newStu);
			
			HStudent stu = DBUtil.createQuery("SELECT h FROM HStudent h WHERE h.studentName = '" + newName + "' AND h.HMajor.majorName = '" + newMajorName + "' AND h.userPassword = '" + newPwd + "'", HStudent.class).getSingleResult();
			long newStuId = stu.getStudentId();
			error += "<div class=\"alert alert-success\"><strong>Success!</strong> Your student ID is " + newStuId + ". Please login.</div>";
			request.setAttribute("error", error);
			getServletContext().getRequestDispatcher("/studentLogin.jsp").forward(request, response);
		}
		
		if (request.getParameter("login") != null) {
			long existId = Long.parseLong((String) request.getParameter("stuID"));
			String existPwd = (String) request.getParameter("loginPwd");
			HStudent existStu = DBUtil.createQuery("SELECT h FROM HStudent h WHERE h.studentId = " + existId, HStudent.class).getSingleResult();
			if (existStu.getUserPassword().equals(existPwd)) {
				session.setAttribute("userStudent", existStu);
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			} else {
				error += "<div class=\"alert alert-danger\"><strong>Error!</strong> Password is incorrect.</div>";
				request.setAttribute("error", error);
				getServletContext().getRequestDispatcher("/studentLogin.jsp").forward(request, response);
			}
		}
	}

}
