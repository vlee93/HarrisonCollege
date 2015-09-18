

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import customTools.DBUtil;

/**
 * Servlet implementation class adminEditDetails
 */
@WebServlet("/adminEditDetails")
public class adminEditDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String adminEdit;
	private String adminEditErr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminEditDetails() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		adminEdit = "";
		adminEditErr = "";
		if (session.getAttribute("depterror") != null) {
			adminEditErr += session.getAttribute("depterror");
		}
		session.removeAttribute("depterror");

		if (request.getParameter("courseid") != null) {
			int id = Integer.parseInt(request.getParameter("courseid"));
			HCourse course = DBUtil.createQuery("SELECT h FROM HCourse h WHERE h.courseId = " + id, HCourse.class).getSingleResult();
			adminEdit += "<div class=\"container\"><h3>Edit Course</h3><form class=\"form-horizontal\" role=\"form\" name=\"editCourse\" id=\"editCourse\" onsubmit=\"return validateForm1()\" action=\"adminEditDetails\" method=\"post\"><input type=\"hidden\" name=\"courseid\" id=\"courseid\" value=\"" + course.getCourseId() + "\">"
					+ "<div class=\"form-group\"><label for=\"subjcode\">Subject Code:</label><input type=\"text\" class=\"form-control\" name=\"subjcode\" id=\"subjcode\" value=\"" + course.getSubjectCode() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"coursenum\">Course Number:</label><input type=\"text\" class=\"form-control\" name=\"coursenum\" id=\"coursenum\" value=\"" + course.getCourseNo() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"courname\">Course Name:</label><input type=\"text\" class=\"form-control\" name=\"courname\" id=\"courname\" value=\"" + course.getCourseName() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"descrip\">Description:</label><textarea class=\"form-control\" rows=\"5\" name=\"descrip\" id=\"descrip\">" + course.getCourseDesc() + "</textarea></div>"
					+ "<div class=\"form-group\"><label for=\"crednum\">Credit #:</label><input type=\"text\" class=\"form-control\" name=\"crednum\" id=\"crednum\" value=\"" + course.getCredits() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"dept\">Department:</label><input type=\"text\" class=\"form-control\" name=\"dept\" id=\"dept\" value=\"" + course.getHDepartment().getDeptName() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
					+ "<button type=\"submit\" class=\"btn btn-default\" name=\"editC\" id=\"editC\">Submit</button></form>";
		}
		
		if (request.getParameter("roomid") != null) {
			int id = Integer.parseInt(request.getParameter("roomid"));
			HClassroom room = DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = " + id, HClassroom.class).getSingleResult();
			adminEdit += "<div class=\"container\"><h3>Edit Classroom</h3><form class=\"form-horizontal\" role=\"form\" name=\"editRoom\" id=\"editRoom\" onsubmit=\"return validateForm2()\" action=\"adminEditDetails\" method=\"post\"><input type=\"hidden\" name=\"roomid\" id=\"roomid\" value=\"" + room.getRoomId() + "\">"
					+ "<div class=\"form-group\"><label for=\"bldgname\">Building Name:</label><input type=\"text\" class=\"form-control\" name=\"bldgname\" id=\"bldgname\" value=\"" + room.getBldgName() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"roomno\">Room #:</label><input type=\"text\" class=\"form-control\" name=\"roomno\" id=\"roomno\" value=\"" + room.getRoomNo() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"capacity\">Capacity:</label><input type=\"text\" class=\"form-control\" name=\"capacity\" id=\"capacity\" value=\"" + room.getMaxCapacity() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
					+ "<button type=\"submit\" class=\"btn btn-default\" name=\"editR\" id=\"editR\">Submit</button></form>";
		}
		
		if (request.getParameter("majorid") != null) {
			int id = Integer.parseInt(request.getParameter("majorid"));
			HMajor major = DBUtil.createQuery("SELECT h FROM HMajor h WHERE h.majorId = " + id, HMajor.class).getSingleResult();
			adminEdit += "<div class=\"container\"><h3>Edit Major</h3><form class=\"form-horizontal\" role=\"form\" name=\"editMajor\" id=\"editMajor\" onsubmit=\"return validateForm3()\" action=\"adminEditDetails\" method=\"post\"><input type=\"hidden\" name=\"majorid\" id=\"majorid\" value=\"" + major.getMajorId() + "\">"
					+ "<div class=\"form-group\"><label for=\"majorname\">Major Name:</label><input type=\"text\" class=\"form-control\" name=\"majorname\" id=\"majorname\" value=\"" + major.getMajorName() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"dept\">Department:</label><input type=\"text\" class=\"form-control\" name=\"dept\" id=\"dept\" value=\"" + major.getHDepartment().getDeptName() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
					+ "<button type=\"submit\" class=\"btn btn-default\" name=\"editM\" id=\"editM\">Submit</button></form>";
		}
		
		if (request.getParameter("deptid") != null) {
			int id = Integer.parseInt(request.getParameter("deptid"));
			HDepartment dept = DBUtil.createQuery("SELECT h FROM HDepartment h WHERE h.deptId = " + id, HDepartment.class).getSingleResult();
			adminEdit += "<div class=\"container\"><h3>Edit Department</h3><form class=\"form-horizontal\" role=\"form\" name=\"editDept\" id=\"editDept\" onsubmit=\"return validateForm4()\" action=\"adminEditDetails\" method=\"post\"><input type=\"hidden\" name=\"deptid\" id=\"deptid\" value=\"" + dept.getDeptId() + "\">"
					+ "<div class=\"form-group\"><label for=\"deptname\">Department Name:</label><input type=\"text\" class=\"form-control\" name=\"deptname\" id=\"deptname\" value=\"" + dept.getDeptName() + "\"></div>"
					+ "<div class=\"form-group\"><label for=\"avail\">Availability:</label><br /><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id= \"avail\" value=\"1\">Yes</label><label class=\"radio-inline\"><input type=\"radio\" name=\"avail\" id = \"avail\" value = \"0\">No</label></div>"
					+ "<button type=\"submit\" class=\"btn btn-default\" name=\"editD\" id=\"editD\">Submit</button></form>";
		}
		
		request.setAttribute("adminEdit", adminEdit);
		request.setAttribute("adminEditErr", adminEditErr);
		getServletContext().getRequestDispatcher("/adminEdit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("editC") != null) {
			editClass(request, response);
		}
		
		if (request.getParameter("editR") != null) {
			editRoom(request, response);
		}
		
		if (request.getParameter("editM") != null) {
			editMajor(request, response);
		}
		
		if (request.getParameter("editD") != null) {
			editDept(request, response);
		}
	}
	
	private void editClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String error = "";
		long id = Long.parseLong(request.getParameter("courseid"));
		HCourse cour = DBUtil.createQuery("SELECT h FROM HCourse h WHERE h.courseId = " + id, HCourse.class).getSingleResult();
		int courseno = Integer.parseInt(request.getParameter("coursenum"));
		String subjco = request.getParameter("subjcode");
		String courname = request.getParameter("courname");
		String descrip = request.getParameter("descrip");
		double crednum = Double.parseDouble(request.getParameter("crednum"));
		String strdept = request.getParameter("dept");
		int available = Integer.parseInt(request.getParameter("avail"));
		
		cour.setAvailable(available);
		cour.setCourseDesc(descrip);
		cour.setCourseName(courname);
		cour.setCourseNo(courseno);
		cour.setCredits(crednum);
		cour.setSubjectCode(subjco);
		
		try {
			HDepartment dept = DBUtil.createQuery("SELECT h FROM HDepartment h WHERE h.deptName = '" + strdept + "'", HDepartment.class).getSingleResult();
			cour.setHDepartment(dept);
			DBUtil.updateDB(cour);
			response.sendRedirect("/HarrisonCollege/adminCourses");
		} catch(Exception e) {
			e.printStackTrace();
			error += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Department does not exist.</div></div>";
			session.setAttribute("depterror", error);
			response.sendRedirect("/HarrisonCollege/adminEditDetails?courseid=" + id);
		}
	}
	
	private void editRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("roomid"));
		HClassroom room = DBUtil.createQuery("SELECT h FROM HClassroom h WHERE h.roomId = " + id, HClassroom.class).getSingleResult();
		String bldgname = request.getParameter("bldgname");
		String roomno = request.getParameter("roomno");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int available = Integer.parseInt(request.getParameter("avail"));
		
		room.setAvailable(available);
		room.setBldgName(bldgname);
		room.setRoomNo(roomno);
		room.setMaxCapacity(capacity);
		
		DBUtil.updateDB(room);
		response.sendRedirect("/HarrisonCollege/adminClassrooms");
	}
	
	private void editMajor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String error = "";
		long id = Long.parseLong(request.getParameter("majorid"));
		HMajor major = DBUtil.createQuery("SELECT h FROM HMajor h WHERE h.majorId = " + id, HMajor.class).getSingleResult();
		String majorname = request.getParameter("majorname");
		String strdept = request.getParameter("dept");
		int available = Integer.parseInt(request.getParameter("avail"));
		
		major.setMajorName(majorname);
		major.setAvailable(available);
		
		try {
			HDepartment dept = DBUtil.createQuery("SELECT h FROM HDepartment h WHERE h.deptName = '" + strdept + "'", HDepartment.class).getSingleResult();
			major.setHDepartment(dept);
			DBUtil.updateDB(major);
			response.sendRedirect("/HarrisonCollege/adminCourses");
		} catch(Exception e) {
			e.printStackTrace();
			error += "<div class=\"container\"><div class=\"alert alert-danger\"><strong>Error!</strong> Department does not exist.</div></div>";
			session.setAttribute("depterror", error);
			response.sendRedirect("/HarrisonCollege/adminEditDetails?majorid=" + id);
		}
	}
	
	private void editDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("deptid"));
		HDepartment dept = DBUtil.createQuery("SELECT h FROM HDepartment h WHERE h.deptId = " + id, HDepartment.class).getSingleResult();
		String deptname = request.getParameter("deptname");
		int available = Integer.parseInt(request.getParameter("avail"));
		
		dept.setAvailable(available);
		dept.setDeptName(deptname);
		
		DBUtil.updateDB(dept);
		response.sendRedirect("/HarrisonCollege/adminDepartments");
	}

}
